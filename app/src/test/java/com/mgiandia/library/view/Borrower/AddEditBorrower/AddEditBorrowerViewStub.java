package com.mgiandia.library.view.Borrower.AddEditBorrower;

import java.util.ArrayList;
import java.util.List;

import com.mgiandia.library.view.Book.AddEditBook.AddEditBookPresenter;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class AddEditBorrowerViewStub implements AddEditBorrowerView
{
    private String firstName, lastName, addressCity, addressStreet, addressNumber, addressPostalCode,
            email, phone, pageName, errorTitle, errorMessage, finishMessage;

    private Integer countryPosition, categoryPosition, attachedBorrowerID;
    private List<String> countryNames, categoryNames;

    private AddEditBookPresenter presenter;

    public void setPresenter(AddEditBookPresenter presenter) {
        this.presenter = presenter;
    }

    public AddEditBookPresenter getPresenter() {
        return presenter;
    }

    public AddEditBorrowerViewStub()
    {
        firstName = lastName = addressCity = addressStreet = addressNumber = addressPostalCode =
                email = phone = pageName = errorTitle = errorMessage = finishMessage = "";

        countryNames = new ArrayList<String>();
        categoryNames = new ArrayList<String>();
    }

    public void setAttachedBorrowerID(Integer val)
    {
        attachedBorrowerID = val;
    }

    public List<String> getCountryList()
    {
        return countryNames;
    }

    public List<String> getCategoryList()
    {
        return categoryNames;
    }

    public String getPageName()
    {
        return pageName;
    }

    public String getErrorTitle()
    {
        return errorTitle;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public String getFinishMessage()
    {
        return finishMessage;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPhone()
    {
        return phone;
    }

    public Integer getCategoryPosition()
    {
        return categoryPosition;
    }

    public Integer getCountryPosition()
    {
        return countryPosition;
    }

    public String getAddressCity()
    {
        return addressCity;
    }

    public String getAddressStreet()
    {
        return addressStreet;
    }

    public String getAddressNumber()
    {
        return addressNumber;
    }

    public String getAddressPostalCode()
    {
        return addressPostalCode;
    }

    public Integer getAttachedBorrowerID()
    {
        return attachedBorrowerID;
    }

    public void setCategoryList(List<String> names)
    {
        categoryNames = names;
    }

    public void setCountryList(List<String> names)
    {
        countryNames = names;
    }

    public void setCategoryPosition(Integer value)
    {
        categoryPosition = value;
    }

    public void setCountryPosition(Integer value)
    {
        countryPosition = value;
    }

    public void setAddressCity(String value)
    {
        addressCity = value;
    }

    public void setAddressStreet(String value)
    {
        addressStreet = value;
    }

    public void setAddressNumber(String value)
    {
        addressNumber = value;
    }

    public void setAddressPostalCode(String value)
    {
        addressPostalCode = value;
    }

    public void setFirstName(String value)
    {
        firstName = value;
    }

    public void setLastName(String value)
    {
        lastName = value;
    }

    public void setEmail(String value)
    {
        email = value;
    }

    public void setPhone(String value)
    {
        phone = value;
    }

    public void setPageName(String value)
    {
        pageName = value;
    }

    public void successfullyFinishActivity(String message)
    {
        finishMessage = message;
    }

    public void showErrorMessage(String title, String message)
    {
        errorTitle = title;
        errorMessage = message;
    }
}
