package java.com.mgiandia.library.view.HomePage;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class HomePageActivityTest
{
    //private final Context context = getInstrumentation().getTargetContext();
    private UiDevice mDevice;
    private static final int LAUNCH_TIMEOUT = 5000;
    private static final String STRING_TO_BE_TYPED = "UiAutomator";
    //private com.mgiandia.library.view.HomePage.HomePageActivityObject homePage;
    public static String BASIC_SAMPLE_PACKAGE = "java.com.mgiandia.library.view.HomePage";


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

        assert intent != null;
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);    // Clear out any previous instances
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test
    public void checkPreconditions()
    {
        assertThat(mDevice, notNullValue());
    }

    @Test
    public void testChangeText_sameActivity()
    {
        // Type text and then press the button.
        //mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "editTextUserInput")).setText(STRING_TO_BE_TYPED);
        //mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, new UiSelector().resourceId("com.mgiandia.library:id/btn_borrowers"))).click();
        mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "btn_borrowers")).click();

        // Verify the test is displayed in the Ui
        //UiObject2 changedText = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "textToBeChanged")), 500 /* wait 500ms */);
        //assertThat(changedText.getText(), is(equalTo(STRING_TO_BE_TYPED)));
    }

    @Test
    public void testChangeText_newActivity()
    {
        // Type text and then press the button.
        mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "editTextUserInput")).setText(STRING_TO_BE_TYPED);
        mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "activityChangeTextBtn")).click();

        // Verify the test is displayed in the Ui
        UiObject2 changedText = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "show_text_view")), 500 /* wait 500ms */);
        assertThat(changedText.getText(), is(equalTo(STRING_TO_BE_TYPED)));
    }

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

        assert resolveInfo != null;
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
