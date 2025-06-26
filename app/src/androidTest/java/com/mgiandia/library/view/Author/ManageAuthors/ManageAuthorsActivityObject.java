package com.mgiandia.library.view.Author.ManageAuthors;

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

public class ManageAuthorsActivityObject extends AbstractActivityObject
{
    private final Context context = super.context;
    private final String APP_NAME = super.appName;
    public final String APP_PACKAGE = super.appPackage;
    private UiDevice mDevice;

    public ManageAuthorsActivityObject(UiDevice mDevice)
    {
        this.mDevice = mDevice;
    }

    public String getAppName()
    {
        return APP_NAME;
    }

    public void clickAuthorItem(String authorName) throws UiObjectNotFoundException
    {
        UiObject authorsBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.manage_authors)));
        authorsBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();

        UiObject authorObj = mDevice.findObject(new UiSelector().textContains(authorName));
        authorObj.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }
}
