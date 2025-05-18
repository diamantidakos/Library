package com.mgiandia.library.view.Book.AddEditBook;

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

public class AddEditBookActivityObject extends AbstractActivityObject
{
    private final Context context = super.context;
    private final String APP_NAME = super.appName;
    public final String APP_PACKAGE = super.appPackage;
    private UiDevice mDevice;
    UiObject libraryAppAddEditBookActivity;

    public AddEditBookActivityObject(UiDevice mDevice)
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
        libraryAppAddEditBookActivity = mDevice.findObject(new UiSelector().packageName(APP_PACKAGE));
        assertTrue("Unable to detect Library App", libraryAppAddEditBookActivity.exists());
    }

    public void navigateToScreen() throws UiObjectNotFoundException
    {
        UiObject booksBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.manage_books)));
        booksBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();

        UiObject newBookBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.add_new_item)));
        newBookBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void fillTitleField() throws UiObjectNotFoundException
    {
        UiObject titleField = mDevice.findObject(new UiSelector().description("titleField"));
        titleField.clearTextField();
        titleField.setText("Title");
    }

    public void fillISBNField() throws UiObjectNotFoundException
    {
        UiObject isbnField = mDevice.findObject(new UiSelector().description("isbnField"));
        isbnField.clearTextField();
        isbnField.setText("5555");
    }

    public void fillPublicationField() throws UiObjectNotFoundException
    {
        UiObject publicationField = mDevice.findObject(new UiSelector().description("publicationField"));
        publicationField.clearTextField();
        publicationField.setText("Publication");
    }

    public void fillYearField() throws UiObjectNotFoundException
    {
        UiObject yearField = mDevice.findObject(new UiSelector().description("yearField"));
        yearField.clearTextField();
        yearField.setText("2025");
    }

    public void fillAuthorsField() throws UiObjectNotFoundException
    {
        UiObject dropdown = mDevice.findObject(new UiSelector().description("authorsField"));
        dropdown.click();

        UiObject authorItem = mDevice.findObject(new UiSelector().text("Ευάγγελος Αβέρωφ"));
        authorItem.click();

        mDevice.pressBack();
    }

    public void clickSaveButton() throws UiObjectNotFoundException
    {
        UiObject saveBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.complete_registration)));
        saveBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }
}
