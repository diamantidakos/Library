package com.mgiandia.library.view.Borrower.BorrowerDetails;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;

import com.mgiandia.library.R;
import com.mgiandia.library.view.AbstractActivityObject;

import org.junit.Before;

public class BorrowerDetailsActivityObject extends AbstractActivityObject
{
    private final Context context = super.context;
    private final String APP_NAME = super.appName;
    public final String APP_PACKAGE = super.appPackage;


    public BorrowerDetailsActivityObject(UiDevice mDevice)
    {
        super(mDevice);
    }

    public String getAppName()
    {
        return APP_NAME;
    }

    private void navigateToScreen() throws UiObjectNotFoundException
    {
        UiObject borrowersBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.manage_borrowers)));
        if (!borrowersBtn.exists())
        {
            mDevice.pressBack();
        }
        borrowersBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void clickEditButton(String name) throws UiObjectNotFoundException
    {
        navigateToScreen();

        UiObject borrowerObj = mDevice.findObject(new UiSelector().textContains(name));
        borrowerObj.clickAndWaitForNewWindow();
        mDevice.waitForIdle();

        super.scrollToBottom();

        UiObject saveBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.edit_user)));
        saveBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void changeSurname(String lastName) throws UiObjectNotFoundException
    {
        UiObject lastNameField = mDevice.findObject(new UiSelector().description("lastName"));
        lastNameField.clearTextField();
        lastNameField.setText(lastName);
    }

    public void clickSaveButton() throws UiObjectNotFoundException
    {
        UiObject saveBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.complete_registration)));
        saveBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void verifyBorrowerVisible(String name)
    {
        UiObject borrowerObj = mDevice.findObject(new UiSelector().textContains(name));
        assertTrue(borrowerObj.exists());
    }

    public void clickDeleteButton(String name) throws UiObjectNotFoundException
    {
        navigateToScreen();

        UiObject borrowerObj = mDevice.findObject(new UiSelector().textContains(name));
        borrowerObj.clickAndWaitForNewWindow();
        mDevice.waitForIdle();

        super.scrollToBottom();

        UiObject saveBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.delete_user)));
        saveBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void verifyBorrowerNotVisible(String name)
    {
        UiObject borrowerObj = mDevice.findObject(new UiSelector().textContains(name));
        assertFalse(borrowerObj.exists());
    }
}
