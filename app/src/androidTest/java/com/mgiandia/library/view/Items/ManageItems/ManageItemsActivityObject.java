package com.mgiandia.library.view.Items.ManageItems;

import android.content.Context;

import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import com.mgiandia.library.R;
import com.mgiandia.library.view.AbstractActivityObject;

public class ManageItemsActivityObject extends AbstractActivityObject
{
    public ManageItemsActivityObject(UiDevice mDevice)
    {
        super(mDevice);
    }

    public String getAppName()
    {
        return appName;
    }

    public void clickItem(String bookTitle, String bookID) throws UiObjectNotFoundException
    {
        UiObject booksBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.manage_items)));
        if (!booksBtn.exists())
        {
            mDevice.pressBack();
        }
        booksBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();

        UiObject bookObj = mDevice.findObject(new UiSelector().textContains(bookTitle));
        bookObj.clickAndWaitForNewWindow();
        mDevice.waitForIdle();

        UiObject bookItem = mDevice.findObject(new UiSelector().textContains(bookID));
        bookItem.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }
}
