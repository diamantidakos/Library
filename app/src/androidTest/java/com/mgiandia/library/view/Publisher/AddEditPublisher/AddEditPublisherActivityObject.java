package com.mgiandia.library.view.Publisher.AddEditPublisher;

import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;

import com.mgiandia.library.R;

import org.junit.Before;

public class AddEditPublisherActivityObject extends AbstractActivityObject
{
    private final Context context = super.context;
    private final String APP_NAME = super.appName;
    public final String APP_PACKAGE = super.appPackage;
    private UiDevice mDevice;
    UiObject libraryAddEditPublisherActivity;

    public AddEditPublisherActivityObject(UiDevice mDevice)
    {
        this.mDevice = mDevice;
    }

    public String getAppName()
    {
        return APP_NAME;
    }

    @Before
    public void setUp() throws Exception {
        navigateToApp();
    }

    public void navigateToApp() throws UiObjectNotFoundException
    {
        // Simulate a short press on the HOME button.
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.pressHome();

        // Bring up the All Apps screen (requires English locale)
        UiObject allAppsButton = null;
        allAppsButton = mDevice.findObject(new UiSelector().text("Apps"));

        // In some devices "Apps" is part of contentDescription
        if (!allAppsButton.exists()) {
            UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
            appViews.swipeUp(10);
        }

        // Create a UiSelector to find the Library app and simulate
        // a user click to launch the app.
        UiObject libraryApp = mDevice.findObject(new UiSelector().className(android.widget.TextView.class.getName()).text(APP_NAME));
        libraryApp.clickAndWaitForNewWindow();

        // Validate that the package name is the expected one
        libraryAddEditPublisherActivity = mDevice.findObject(new UiSelector().packageName(APP_PACKAGE));
        assertTrue("Unable to detect Library App", libraryAddEditPublisherActivity.exists());
    }

    public void navigateToScreen() throws UiObjectNotFoundException
    {
        UiObject booksBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.manage_publishers)));
        booksBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();

        UiObject newBookBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.add_new_item)));
        newBookBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void fillNameField() throws UiObjectNotFoundException
    {
        UiObject nameField = mDevice.findObject(new UiSelector().description("name"));
        nameField.clearTextField();
        nameField.setText("Name");
    }

    public void fillPhoneField() throws UiObjectNotFoundException
    {
        UiObject phoneField = mDevice.findObject(new UiSelector().description("phone"));
        phoneField.clearTextField();
        phoneField.setText("6974338842");
    }

    public void fillEmailField() throws UiObjectNotFoundException
    {
        UiObject emailField = mDevice.findObject(new UiSelector().description("email"));
        emailField.clearTextField();
        emailField.setText("email@gmail.com");
    }

    public void fillCityField() throws UiObjectNotFoundException
    {
        UiObject cityField = mDevice.findObject(new UiSelector().description("city"));
        cityField.clearTextField();
        cityField.setText("Athens");
    }

    public void fillStreetField() throws UiObjectNotFoundException
    {
        UiObject streetField = mDevice.findObject(new UiSelector().description("street"));
        streetField.clearTextField();
        streetField.setText("Street");
    }

    public void fillNumberField() throws UiObjectNotFoundException
    {
        UiObject numberField = mDevice.findObject(new UiSelector().description("number"));
        numberField.clearTextField();
        numberField.setText("10");
    }

    public void fillZipCodeField() throws UiObjectNotFoundException
    {
        UiObject zipField = mDevice.findObject(new UiSelector().description("zip"));
        zipField.clearTextField();
        zipField.setText("11632");
    }

    public void clickSaveButton() throws UiObjectNotFoundException
    {
        UiObject saveBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.complete_registration)));
        saveBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }
}
