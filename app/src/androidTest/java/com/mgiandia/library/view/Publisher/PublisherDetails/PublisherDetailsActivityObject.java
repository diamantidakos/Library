package com.mgiandia.library.view.Publisher.PublisherDetails;

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

public class PublisherDetailsActivityObject extends AbstractActivityObject
{
    private final Context context = super.context;
    private final String APP_NAME = super.appName;
    public final String APP_PACKAGE = super.appPackage;


    public PublisherDetailsActivityObject(UiDevice mDevice)
    {
        super(mDevice);
    }

    public String getAppName()
    {
        return APP_NAME;
    }

    private void navigateToScreen() throws UiObjectNotFoundException
    {
        UiObject booksBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.manage_publishers)));
        if (!booksBtn.exists())
        {
            mDevice.pressBack();
        }
        booksBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void clickEditButton(String publisher) throws UiObjectNotFoundException
    {
        navigateToScreen();

        UiObject newBookBtn = mDevice.findObject(new UiSelector().textContains(publisher));
        newBookBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();

        super.scrollToBottom();

        UiObject saveBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.edit_user)));
        saveBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void changeName(String name) throws UiObjectNotFoundException
    {
        UiObject nameField = mDevice.findObject(new UiSelector().description("name"));
        nameField.clearTextField();
        nameField.setText(name);
    }

    public void clickSaveButton() throws UiObjectNotFoundException
    {
        UiObject saveBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.complete_registration)));
        saveBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void verifyPublisherVisible(String name)
    {
        UiObject borrowerObj = mDevice.findObject(new UiSelector().textContains(name));
        assertTrue(borrowerObj.exists());
    }

    public void clickShowBooksButton(String publisher) throws UiObjectNotFoundException
    {
        navigateToScreen();

        UiObject newBookBtn = mDevice.findObject(new UiSelector().textContains(publisher));
        newBookBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();

        UiScrollable scrollView = new UiScrollable(new UiSelector().scrollable(true));
        scrollView.scrollForward();

        UiObject saveBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.show_books)));
        saveBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void verifyBooksVisible(String book)
    {
        UiObject borrowerObj = mDevice.findObject(new UiSelector().textContains(book));
        assertTrue(borrowerObj.exists());
    }
}
