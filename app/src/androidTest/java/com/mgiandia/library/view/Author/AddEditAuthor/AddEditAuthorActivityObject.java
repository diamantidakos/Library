package com.mgiandia.library.view.Author.AddEditAuthor;

import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import com.mgiandia.library.R;
import com.mgiandia.library.view.AbstractActivityObject;

public class AddEditAuthorActivityObject extends AbstractActivityObject
{
    private final Context context = super.context;
    private final String APP_NAME = super.appName;
    public final String APP_PACKAGE = super.appPackage;

    public AddEditAuthorActivityObject(UiDevice mDevice)
    {
        super(mDevice);
    }

    public String getAppName()
    {
        return APP_NAME;
    }

    public void navigateToScreen() throws UiObjectNotFoundException
    {
        UiObject booksBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.manage_authors)));
        booksBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();

        UiObject newBookBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.add_new_item)));
        newBookBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void fillFirstNameField(String firstName) throws UiObjectNotFoundException
    {
        UiObject firstNameField = mDevice.findObject(new UiSelector().description("firstNameField"));
        firstNameField.clearTextField();
        firstNameField.setText(firstName);
    }

    public void fillLastNameField(String lastName) throws UiObjectNotFoundException
    {
        UiObject lastNameField = mDevice.findObject(new UiSelector().description("lastNameField"));
        lastNameField.clearTextField();
        lastNameField.setText(lastName);
    }

    public void clickSaveButton() throws UiObjectNotFoundException
    {
        UiObject saveBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.complete_registration)));
        saveBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void verifyNewAuthorIsVisible(String name)
    {
        UiObject authorObj = mDevice.findObject(new UiSelector().textContains(name));
        assertTrue(authorObj.exists());
    }
}
