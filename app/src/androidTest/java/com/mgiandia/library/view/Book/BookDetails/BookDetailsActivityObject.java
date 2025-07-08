package com.mgiandia.library.view.Book.BookDetails;

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

public class BookDetailsActivityObject extends AbstractActivityObject
{
    public BookDetailsActivityObject(UiDevice mDevice)
    {
        super(mDevice);
    }

    public String getAppName()
    {
        return appName;
    }

    public void clickEditButton(String bookTitle) throws UiObjectNotFoundException
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

        UiObject saveBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.edit_user)));
        saveBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void changeISBN(String isbn) throws UiObjectNotFoundException
    {
        UiObject isbnField = mDevice.findObject(new UiSelector().description("isbnField"));
        isbnField.clearTextField();
        isbnField.setText(isbn);
    }

    public void clickSaveButton() throws UiObjectNotFoundException
    {
        UiObject saveBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.complete_registration)));
        saveBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void verifyBookIsVisible(String bookTitle)
    {
        UiObject bookObj = mDevice.findObject(new UiSelector().textContains(bookTitle));
        assertTrue(bookObj.exists());
    }
}
