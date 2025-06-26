package com.mgiandia.library.view.Loans.AddLoan;

import static org.junit.Assert.assertTrue;

import android.content.Context;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import com.mgiandia.library.R;
import com.mgiandia.library.view.AbstractActivityObject;

public class AddLoanActivityObject extends AbstractActivityObject
{
    private final Context context = super.context;
    private final String APP_NAME = super.appName;
    public final String APP_PACKAGE = super.appPackage;
    private UiDevice mDevice;

    public AddLoanActivityObject(UiDevice mDevice)
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

        UiObject newBookBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.add_new_item)));
        newBookBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void selectBook(String book) throws UiObjectNotFoundException
    {
        UiObject dropdown = mDevice.findObject(new UiSelector().description("bookField"));
        dropdown.click();

        UiObject authorItem = mDevice.findObject(new UiSelector().text(book));
        authorItem.click();
    }

    public void clickSaveButton() throws UiObjectNotFoundException
    {
        UiObject saveBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.complete_registration)));
        saveBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void verifyNewLoanIsVisible(String title)
    {
        UiObject bookObj = mDevice.findObject(new UiSelector().textContains(title));
        assertTrue(bookObj.exists());
    }
}
