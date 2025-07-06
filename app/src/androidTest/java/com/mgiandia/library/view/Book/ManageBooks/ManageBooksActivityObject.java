package com.mgiandia.library.view.Book.ManageBooks;

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

public class ManageBooksActivityObject extends AbstractActivityObject
{
    private final Context context = super.context;
    private final String APP_NAME = super.appName;
    public final String APP_PACKAGE = super.appPackage;


    public ManageBooksActivityObject(UiDevice mDevice)
    {
        super(mDevice);
    }

    public String getAppName()
    {
        return APP_NAME;
    }

    public void clickBookItem(String bookTitle) throws UiObjectNotFoundException
    {
        UiObject booksBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.manage_books)));
        if (!booksBtn.exists())
        {
            mDevice.pressBack();
        }
        booksBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();

        UiObject bookObj = mDevice.findObject(new UiSelector().textContains(bookTitle));
        bookObj.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }
}
