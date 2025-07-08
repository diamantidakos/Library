package com.mgiandia.library.view.Author.AuthorDetails;

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

public class AuthorDetailsActivityObject extends AbstractActivityObject
{
    public AuthorDetailsActivityObject(UiDevice mDevice)
    {
        super(mDevice);
    }

    public String getAppName()
    {
        return appName;
    }

    public void clickEditButton(String authorName) throws UiObjectNotFoundException
    {
        UiObject authorsBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.manage_authors)));
        authorsBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();

        UiObject authorObj = mDevice.findObject(new UiSelector().textContains(authorName));
        authorObj.clickAndWaitForNewWindow();
        mDevice.waitForIdle();

        UiObject saveBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.edit_user)));
        saveBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void changeSurname(String surname) throws UiObjectNotFoundException
    {
        UiObject lastNameField = mDevice.findObject(new UiSelector().description("lastNameField"));
        lastNameField.clearTextField();
        lastNameField.setText(surname);
    }

    public void clickSaveButton() throws UiObjectNotFoundException
    {
        UiObject saveBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.complete_registration)));
        saveBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void verifyAuthorIsVisible(String name)
    {
        UiObject authorObj = mDevice.findObject(new UiSelector().textContains(name));
        assertTrue(authorObj.exists());
    }

    public void clickAppearBooksButton(String authorName) throws UiObjectNotFoundException
    {
        UiObject authorsBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.manage_authors)));
        authorsBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();

        UiObject authorObj = mDevice.findObject(new UiSelector().textContains(authorName));
        authorObj.clickAndWaitForNewWindow();
        mDevice.waitForIdle();

        UiObject saveBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.show_books)));
        saveBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void verifyBookIsVisible(String bookName)
    {
        UiObject bookObj = mDevice.findObject(new UiSelector().textContains(bookName));
        assertTrue(bookObj.exists());
    }
}
