package com.mgiandia.library.view.HomePage;

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


public class HomePageActivityObject extends AbstractActivityObject
{
    private final Context context = super.context;
    private final String APP_NAME = super.appName;
    public final String APP_PACKAGE = super.appPackage;
    private UiDevice mDevice;

    public HomePageActivityObject(UiDevice mDevice)
    {
        this.mDevice = mDevice;
    }

    public String getAppName()
    {
        return APP_NAME;
    }

    public void verifyBorrowerVisible(String publisher)
    {
        UiObject publisherObj = mDevice.findObject(new UiSelector().textContains(publisher));
        assertTrue(publisherObj.exists());
    }

    public void clickBorrowersButton() throws UiObjectNotFoundException
    {
        UiObject borrowersBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.manage_borrowers)));
        if (!borrowersBtn.exists())
        {
            mDevice.pressBack();
        }
        borrowersBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void verifyBookVisible(String book)
    {
        UiObject bookObj = mDevice.findObject(new UiSelector().textContains(book));
        assertTrue(bookObj.exists());
    }

    public void clickBooksButton() throws UiObjectNotFoundException
    {
        UiObject booksBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.manage_books)));
        if (!booksBtn.exists())
        {
            mDevice.pressBack();
        }
        booksBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void verifyAuthorVisible(String author)
    {
        UiObject authorObj = mDevice.findObject(new UiSelector().textContains(author));
        assertTrue(authorObj.exists());
    }

    public void clickAuthorsButton() throws UiObjectNotFoundException
    {
        mDevice.pressBack();
        UiObject authorsBtn = mDevice.findObject(new UiSelector().text(context.getString(R.string.manage_authors)));
        authorsBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void verifyLoanVisible(String loan)
    {
        UiObject loanObj = mDevice.findObject(new UiSelector().textContains(loan));
        assertTrue(loanObj.exists());
    }

    public void clickLoansButton(String borrowerName) throws UiObjectNotFoundException
    {
        UiObject borrowersBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.manage_loans)));
        if (!borrowersBtn.exists())
        {
            mDevice.pressBack();
        }
        borrowersBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();

        UiObject loansBtn = mDevice.findObject(new UiSelector().textContains(borrowerName));
        loansBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void verifyItemsVisible(String item)
    {
        UiObject itemObj = mDevice.findObject(new UiSelector().textContains(item));
        assertTrue(itemObj.exists());
    }

    public void clickItemsButton(String itemName) throws UiObjectNotFoundException
    {
        UiObject borrowersBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.manage_items)));
        if (!borrowersBtn.exists())
        {
            mDevice.pressBack();
        }
        borrowersBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();

        UiObject loansBtn = mDevice.findObject(new UiSelector().textContains(itemName));
        loansBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void verifyReturnsVisible(String returns)
    {
        UiObject returnsObj = mDevice.findObject(new UiSelector().textContains(returns));
        assertTrue(returnsObj.exists());
    }

    public void clickReturnsButton() throws UiObjectNotFoundException
    {
        UiObject borrowersBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.manage_returns)));
        if (!borrowersBtn.exists())
        {
            mDevice.pressBack();
        }
        borrowersBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();

        UiObject loansBtn = mDevice.findObject(new UiSelector().textContains("Δραγούμης"));
        loansBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void verifyPublisherVisible(String publisher)
    {
        UiObject publisherText = mDevice.findObject(new UiSelector().descriptionContains(publisher));
        assertTrue(publisherText.exists());
    }

    public void clickPublishersButton() throws UiObjectNotFoundException
    {
        UiObject publishersBtn = mDevice.findObject(new UiSelector().descriptionContains(context.getString(R.string.manage_publishers)));
        if (!publishersBtn.exists())
        {
            mDevice.pressBack();
        }
        publishersBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }
}