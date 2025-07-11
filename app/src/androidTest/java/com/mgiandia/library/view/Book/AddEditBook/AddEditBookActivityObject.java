package com.mgiandia.library.view.Book.AddEditBook;

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

public class AddEditBookActivityObject extends AbstractActivityObject
{
    public AddEditBookActivityObject(UiDevice mDevice)
    {
        super(mDevice);
    }

    public String getAppName()
    {
        return appName;
    }

    public void navigateToScreen() throws UiObjectNotFoundException
    {
        UiObject booksBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.manage_books)));
        if (!booksBtn.exists())
        {
            mDevice.pressBack();
        }
        booksBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();

        UiObject newBookBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.add_new_item)));
        newBookBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void fillTitleField(String title) throws UiObjectNotFoundException
    {
        UiObject titleField = mDevice.findObject(new UiSelector().description("titleField"));
        titleField.clearTextField();
        titleField.setText(title);
    }

    public void fillISBNField(String isbn) throws UiObjectNotFoundException
    {
        UiObject isbnField = mDevice.findObject(new UiSelector().description("isbnField"));
        isbnField.clearTextField();
        isbnField.setText(isbn);
    }

    public void fillPublicationField(String publication) throws UiObjectNotFoundException
    {
        UiObject publicationField = mDevice.findObject(new UiSelector().description("publicationField"));
        publicationField.clearTextField();
        publicationField.setText(publication);
    }

    public void fillYearField(String year) throws UiObjectNotFoundException
    {
        UiObject yearField = mDevice.findObject(new UiSelector().description("yearField"));
        yearField.clearTextField();
        yearField.setText(year);
    }

    public void fillAuthorsField(String author) throws UiObjectNotFoundException
    {
        UiObject dropdown = mDevice.findObject(new UiSelector().description("authorsField"));
        dropdown.click();

        UiObject authorItem = mDevice.findObject(new UiSelector().text(author));
        authorItem.click();

        mDevice.pressBack();
        mDevice.waitForIdle();
    }

    public void clickSaveButton() throws UiObjectNotFoundException
    {
        UiObject saveBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.complete_registration)));
        saveBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void verifyNewBookIsVisible(String title)
    {
        UiObject bookObj = mDevice.findObject(new UiSelector().textContains(title));
        assertTrue(bookObj.exists());
    }
}
