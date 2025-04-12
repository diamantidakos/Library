package com.mgiandia.library.androidTest.view.HomePage;

import static org.junit.Assert.assertTrue;
import androidx.test.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import com.mgiandia.library.R;
import org.junit.Before;


public class HomePageActivityObject
{
    public static String APP_NAME = String.valueOf(R.string.app_name);
    public static String APP_PACKAGE = "com.mgiandia.library";
    private UiDevice mDevice;
    UiObject libraryAppMainActivity;

    public HomePageActivityObject(UiDevice mDevice)
    {
        this.mDevice = mDevice;
    }

    @Before
    public void setUp() throws Exception
    {
        navigateToApp();
    }

    /*
    public void navigateToApp() throws UiObjectNotFoundException
    {
        mDevice.pressHome();
        UiObject allAppsButton = mDevice.findObject(new UiSelector().text("Apps").descriptionContains("Apps"));

        if (!allAppsButton.exists())
        {
            throw new RuntimeException("Apps button not found");
        }
        allAppsButton.clickAndWaitForNewWindow();

        UiScrollable appDrawer = new UiScrollable(new UiSelector().scrollable(true));
        appDrawer.scrollIntoView(new UiSelector().text(APP_NAME));

        UiObject libraryApp = mDevice.findObject(new UiSelector().className(android.widget.TextView.class).text(APP_NAME));
        libraryApp.clickAndWaitForNewWindow();

        mDevice.wait(Until.hasObject(By.pkg(APP_PACKAGE)), 5000);
    }
    */



    public void navigateToApp() throws UiObjectNotFoundException
    {
        // Simulate a short press on the HOME button.
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.pressHome();

        // Bring up the All Apps screen (requires English locale)
        UiObject allAppsButton = null;
        allAppsButton = mDevice.findObject(new UiSelector().text("Apps"));

        // In some devices "Apps" is part of contentDescription
        if (!allAppsButton.exists())
        {
            allAppsButton = mDevice.findObject(new UiSelector().descriptionContains("Apps"));
        }

        // Simulate a click to bring up the All Apps screen.
        allAppsButton.clickAndWaitForNewWindow();

//		UiObject appsTab = mDevice.findObject(new UiSelector().text("Apps"));
//		// Simulate a click to enter the Apps tab.
//		appsTab.click();

        // Simulate a user swiping until finding the application
//		UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));

        // Create a UiSelector to find the Library app and simulate
        // a user click to launch the app.
        UiObject libraryApp = mDevice.findObject(new UiSelector().className(android.widget.TextView.class.getName()).text(APP_NAME));
        libraryApp.clickAndWaitForNewWindow();

        // Validate that the package name is the expected one
        libraryAppMainActivity = mDevice.findObject(new UiSelector().packageName(APP_PACKAGE));
        assertTrue("Unable to detect EquationSolvingApp", libraryAppMainActivity.exists());
    }

    public void verifyHomePageVisible()
    {
        UiObject title = mDevice.findObject(new UiSelector().text(APP_NAME).className(android.widget.TextView.class));
        assertTrue(title.exists());
    }

    public void clickBorrowersButton() throws UiObjectNotFoundException
    {
        //UiObject borrowersBtn = mDevice.findObject(new UiSelector().resourceId("com.mgiandia.library:id/btn_borrowers")); // resource-id

        UiObject borrowersBtn = mDevice.findObject(new UiSelector().text(String.valueOf(R.string.manage_borrowers)).className(android.widget.Button.class));
        borrowersBtn.click();
        mDevice.waitForIdle();
    }
}
