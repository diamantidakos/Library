package com.mgiandia.library.view.Returns.ManageReturns;

import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import com.mgiandia.library.R;
import com.mgiandia.library.view.AbstractActivityObject;

public class ManageReturnsActivityObject extends AbstractActivityObject
{
    private final Context context = super.context;
    private final String APP_NAME = super.appName;
    public final String APP_PACKAGE = super.appPackage;
    private UiDevice mDevice;

    public ManageReturnsActivityObject(UiDevice mDevice)
    {
        this.mDevice = mDevice;
    }

    public String getAppName()
    {
        return APP_NAME;
    }

    public void navigateToScreen(String borrowerName) throws UiObjectNotFoundException
    {
        UiObject booksBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.manage_returns)));
        if (booksBtn.exists())
        {
            booksBtn.clickAndWaitForNewWindow();
            mDevice.waitForIdle();
        }

        super.scrollToBottom();

        UiObject bookObj = mDevice.findObject(new UiSelector().textContains(borrowerName));
        bookObj.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void clickItem(String bookName) throws UiObjectNotFoundException
    {
        UiObject bookObj = mDevice.findObject(new UiSelector().text(bookName));
        bookObj.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void clickReturnOption() throws UiObjectNotFoundException
    {
        UiObject bookObj = mDevice.findObject(new UiSelector().textContains("ΕΠΙΣΤΡΕΨΤΕ"));
        bookObj.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void clickLostOption() throws UiObjectNotFoundException
    {
        UiObject bookObj = mDevice.findObject(new UiSelector().textContains("ΧΑΘΗΚΕ"));
        bookObj.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void verifyItemIsNotVisible(String bookTitle)
    {
        UiObject bookObj = mDevice.findObject(new UiSelector().textContains(bookTitle));
        assertTrue("The item with title '" + bookTitle + "' should not be visible.", bookObj.waitUntilGone(1500));
    }
}
