package com.mgiandia.library.view.HomePage;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.Until;

import com.mgiandia.library.view.SystemTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class HomePageActivityTest extends SystemTest
{
    private UiDevice mDevice = UiDevice.getInstance(getInstrumentation());;
    private final int LAUNCH_TIMEOUT = super.launchTimeOut;
    public final String BASIC_SAMPLE_PACKAGE = super.appPackage;
    private HomePageActivityObject homePageActivityObject = new HomePageActivityObject(mDevice);
    private Context context = getApplicationContext();

    @Before
    public void startMainActivityFromHomeScreen()
    {
        mDevice.pressHome();

        // Launch the app from the home page
        final Intent intent = new Intent();
        intent.setClassName(BASIC_SAMPLE_PACKAGE, BASIC_SAMPLE_PACKAGE + ".view.HomePage.HomePageActivity");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        // Wait for activity to appear
        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test
    public void checkPreconditions()
    {
        assertThat(mDevice, notNullValue());
    }

    @Test
    public void testBorrowersButtonClick() throws UiObjectNotFoundException
    {
        homePageActivityObject.clickBorrowersButton();
        homePageActivityObject.verifyBorrowerVisible("Γιακουμάκης");
    }

    @Test
    public void testBooksButtonClick() throws UiObjectNotFoundException
    {
        homePageActivityObject.clickBooksButton();
        homePageActivityObject.verifyBookVisible("The Odyssey");
    }

    @Test
    public void testAuthorsButtonClick() throws UiObjectNotFoundException
    {
        homePageActivityObject.clickAuthorsButton();
        homePageActivityObject.verifyAuthorVisible("Βυζάντιος");
    }

    @Test
    public void testLoansButtonClick() throws UiObjectNotFoundException
    {
        homePageActivityObject.clickLoansButton("Δραγούμης");
        homePageActivityObject.verifyLoanVisible("Don Quixote");
    }

    @Test
    public void testItemsButtonClick() throws UiObjectNotFoundException
    {
        homePageActivityObject.clickItemsButton("Don Quixote");
        homePageActivityObject.verifyItemsVisible("Don Quixote");
    }

    @Test
    public void testReturnsButtonClick() throws UiObjectNotFoundException
    {
        homePageActivityObject.clickReturnsButton();
        homePageActivityObject.verifyReturnsVisible("Don Quixote");
    }

    @Test
    public void testShowPublishersList() throws UiObjectNotFoundException
    {
        homePageActivityObject.clickPublishersButton();
        homePageActivityObject.verifyPublisherVisible("McGraw-Hill Education");
    }
}
