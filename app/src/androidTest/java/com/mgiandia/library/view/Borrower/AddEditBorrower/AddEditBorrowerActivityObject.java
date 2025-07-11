package com.mgiandia.library.view.Borrower.AddEditBorrower;

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

public class AddEditBorrowerActivityObject extends AbstractActivityObject
{
    public AddEditBorrowerActivityObject(UiDevice mDevice)
    {
        super(mDevice);
    }

    public String getAppName()
    {
        return appName;
    }

    public void navigateToScreen() throws UiObjectNotFoundException
    {
        UiObject booksBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.manage_borrowers)));
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

    public void fillFirstNameField(String firstName) throws UiObjectNotFoundException
    {
        UiObject firstNameField = mDevice.findObject(new UiSelector().description("firstName"));
        firstNameField.clearTextField();
        firstNameField.setText(firstName);
    }

    public void fillLastNameField(String lastName) throws UiObjectNotFoundException
    {
        UiObject lastNameField = mDevice.findObject(new UiSelector().description("lastName"));
        lastNameField.clearTextField();
        lastNameField.setText(lastName);
    }

    public void fillPhoneField(String phone) throws UiObjectNotFoundException
    {
        UiObject phoneField = mDevice.findObject(new UiSelector().description("phone"));
        phoneField.clearTextField();
        phoneField.setText(phone);
    }

    public void fillEmailField(String email) throws UiObjectNotFoundException
    {
        UiObject emailField = mDevice.findObject(new UiSelector().description("email"));
        emailField.clearTextField();
        emailField.setText(email);
    }

    public void fillCityField(String city) throws UiObjectNotFoundException
    {
        UiObject cityField = mDevice.findObject(new UiSelector().description("city"));
        cityField.clearTextField();
        cityField.setText(city);
    }

    public void fillStreetField(String street) throws UiObjectNotFoundException
    {
        UiObject streetField = mDevice.findObject(new UiSelector().description("street"));
        streetField.clearTextField();
        streetField.setText(street);
    }

    public void fillNumberField(String number) throws UiObjectNotFoundException
    {
        UiObject numberField = mDevice.findObject(new UiSelector().description("number"));
        numberField.clearTextField();
        numberField.setText(number);
    }

    public void fillZipCodeField(String zipCode) throws UiObjectNotFoundException
    {
        UiObject zipField = mDevice.findObject(new UiSelector().description("zip"));
        zipField.clearTextField();
        zipField.setText(zipCode);
    }

    public void clickSaveButton() throws UiObjectNotFoundException
    {
        UiObject saveBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.complete_registration)));
        saveBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void verifyNewBorrowerIsVisible(String borrowerName)
    {
        UiObject borrowerObj = mDevice.findObject(new UiSelector().textContains(borrowerName));
        assertTrue(borrowerObj.waitForExists(10000));
    }
}
