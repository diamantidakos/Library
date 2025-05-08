package com.mgiandia.library.view.HomePage;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import com.mgiandia.library.R;
import com.mgiandia.library.view.SystemTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;


@RunWith(AndroidJUnit4.class)
public class HomePageActivityTest extends SystemTest
{
    private UiDevice mDevice;
    private static final int LAUNCH_TIMEOUT = 5000;
    public static String BASIC_SAMPLE_PACKAGE = "com.mgiandia.library";

    private HomePageActivityObject homePageActivityObject;

    @Before
    public void startMainActivityFromHomeScreen()
    {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(getInstrumentation());

        // Start from the home screen
        mDevice.pressHome();

        // Wait for launcher
        final String launcherPackage = getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);

        // Launch the blueprint app
        Context context = getApplicationContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);    // Clear out any previous instances
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT);
    }

    @After
    public void tearDown(){
        mDevice.pressBack();
        mDevice.pressBack();
    }

    @Test
    public void checkPreconditions()
    {
        assertThat(mDevice, notNullValue());
    }

    @Test
    public void clickBorrowersButton() throws UiObjectNotFoundException {
        // Type text and then press the button.
        //mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "editTextUserInput")).setText(STRING_TO_BE_TYPED);
        //mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, new UiSelector().resourceId("com.mgiandia.library:id/btn_borrowers"))).click();
        //mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "manage_borrowers")).click();

        homePageActivityObject = new HomePageActivityObject(mDevice);
        homePageActivityObject.clickBorrowersButton();
        homePageActivityObject.verifyBorrowerVisible("Γιακουμάκης");


//        UiObject2 borrowersButton = mDevice.wait(Until.findObject(By.text(getApplicationContext().getResources().getString(R.string.manage_borrowers))), LAUNCH_TIMEOUT);
//        borrowersButton.click();
//        assertThat(borrowersButton, notNullValue());
        //mDevice.pressBack();

        // Verify the test is displayed in the Ui
        //UiObject2 changedText = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "textToBeChanged")), 500);
        //assertThat(changedText.getText(), is(equalTo(STRING_TO_BE_TYPED)));
    }

    @Test
    public void clickBooksButton()
    {
        UiObject2 booksBtn = mDevice.wait(Until.findObject(By.text(getApplicationContext().getResources().getString(R.string.manage_books))), LAUNCH_TIMEOUT);
        booksBtn.click();
        assertThat(booksBtn, notNullValue());
        //mDevice.pressBack();
    }

    @Test
    public void clickAuthorsButton()
    {
        UiObject2 authorsBtn = mDevice.wait(Until.findObject(By.text(getApplicationContext().getResources().getString(R.string.manage_authors))), LAUNCH_TIMEOUT);
        authorsBtn.click();
        assertThat(authorsBtn, notNullValue());
        //mDevice.pressBack();
    }

    @Test
    public void clickLoansButton()
    {
        UiObject2 loansBtn = mDevice.wait(Until.findObject(By.text(getApplicationContext().getResources().getString(R.string.manage_loans))), LAUNCH_TIMEOUT);
        loansBtn.click();
        assertThat(loansBtn, notNullValue());
        //mDevice.pressBack();
    }

    @Test
    public void clickItemsButton()
    {
        UiObject2 itemsBtn = mDevice.wait(Until.findObject(By.text(getApplicationContext().getResources().getString(R.string.manage_items))), LAUNCH_TIMEOUT);
        itemsBtn.click();
        assertThat(itemsBtn, notNullValue());
        //mDevice.pressBack();
    }

    @Test
    public void clickReturnsButton()
    {
        UiObject2 returnsBtn = mDevice.wait(Until.findObject(By.text(getApplicationContext().getResources().getString(R.string.manage_returns))), LAUNCH_TIMEOUT);
        returnsBtn.click();
        assertThat(returnsBtn, notNullValue());
        //mDevice.pressBack();
    }

    @Test
    public void testShowPublishersList() throws UiObjectNotFoundException {
        homePageActivityObject = new HomePageActivityObject(mDevice);
        homePageActivityObject.clickPublishersButton();
        homePageActivityObject.verifyPublisherVisible("McGraw-Hill Education");
    }

    /*
    @Test
    public void testChangeText_newActivity()
    {
        // Type text and then press the button.
        mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "editTextUserInput")).setText(STRING_TO_BE_TYPED);
        mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "activityChangeTextBtn")).click();

        // Verify the test is displayed in the Ui
        UiObject2 changedText = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "show_text_view")), 500);
        assertThat(changedText.getText(), is(equalTo(STRING_TO_BE_TYPED)));
    }
    */

    /**
     * Uses package manager to find the package name of the device launcher. Usually this package
     * is "com.android.launcher" but can be different at times. This is a generic solution which
     * works on all platforms.`
     */
    private String getLauncherPackageName()
    {
        // Create launcher Intent
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        // Use PackageManager to get the launcher package name
        PackageManager pm = getApplicationContext().getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.activityInfo.packageName;
    }


    /*
    @Before
    public void setUp() throws Exception
    {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        homePage = new HomePageActivityObject(mDevice);
        homePage.navigateToApp();
    }

    @Test
    public void testBorrowersButtonNavigation() throws Exception
    {
        homePage.verifyHomePageVisible();
        homePage.clickBorrowersButton();
        UiObject borrowersTitle = mDevice
                .findObject(
                        new UiSelector()
                                .text(context.getString(R.string.manage_borrowers)));
        assertTrue(borrowersTitle.exists());
    }
    */
}
