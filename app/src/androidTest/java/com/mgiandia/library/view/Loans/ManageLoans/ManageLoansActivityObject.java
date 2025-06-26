package com.mgiandia.library.view.Loans.ManageLoans;

import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import com.mgiandia.library.R;
import com.mgiandia.library.view.AbstractActivityObject;

public class ManageLoansActivityObject extends AbstractActivityObject
{
    private final Context context = super.context;
    private final String APP_NAME = super.appName;
    public final String APP_PACKAGE = super.appPackage;
    private UiDevice mDevice;

    public ManageLoansActivityObject(UiDevice mDevice)
    {
        this.mDevice = mDevice;
    }

    public String getAppName()
    {
        return APP_NAME;
    }

    public void navigateToScreen(String borrower) throws UiObjectNotFoundException
    {
        UiObject booksBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.manage_loans)));
        if (!booksBtn.exists())
        {
            mDevice.pressBack();
        }
        booksBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();

        UiObject borrowerObj = mDevice.findObject(new UiSelector().textContains(borrower));
        borrowerObj.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void verifyLoansAreVisible(String title)
    {
        UiObject bookObj = mDevice.findObject(new UiSelector().textContains(title));
        assertTrue(bookObj.exists());
    }
}
