package com.mgiandia.library.view;

import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;

import com.mgiandia.library.R;

public abstract class AbstractActivityObject
{
    protected Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    protected String appName = context.getString(R.string.app_name);
    protected String appPackage = "com.mgiandia.library";
    protected UiDevice mDevice;

    public AbstractActivityObject(UiDevice mDevice)
    {
        this.mDevice = mDevice;
    }

    public void scrollToBottom() throws UiObjectNotFoundException
    {
        UiScrollable scrollView = new UiScrollable(new UiSelector().scrollable(true));
        if (scrollView.exists())
        {
            scrollView.scrollForward();
        }
        //mDevice.swipe(500, 500, 500, 1500, 10);
    }
}
