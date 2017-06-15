package com.mgiandia.library.view.Publisher.AddPublisher;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class AddEditPublisherViewStub implements AddEditPublisherView
{
    private String name, phone, email, addressCity, addressStreet, addressNumber, addressPostalCode,
            errorTitle, errorMessage, finishMessage, pageName;

    private Integer countryPosition, attachedPublisherID;
    private List<String> countryListNames;

    private AddEditPublisherPresenter presenter;

    public void setPresenter(AddEditPublisherPresenter presenter) {
        this.presenter = presenter;
    }

    public AddEditPublisherPresenter getPresenter() {
        return presenter;
    }

    public AddEditPublisherViewStub()
    {
        name = phone = email = addressCity = addressStreet = addressNumber =
                addressPostalCode = errorTitle = errorMessage = finishMessage = pageName = "";
        countryListNames = new ArrayList<String>();
    }

    public void setAttachedPublisherID(Integer val)
    {
        attachedPublisherID = val;
    }

    public Integer getAttachedPublisherID()
    {
        return attachedPublisherID;
    }

    public String getPageName()
    {
        return pageName;
    }

    public void setPageName(String value)
    {
        pageName = value;
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

    public String getName()
    {
        return name;
    }

    public String getPhone()
    {
        return phone;
    }

    public String getEmail()
    {
        return email;
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


    public void setName(String value)
    {
        name = value;
    }

    public void setPhone(String value)
    {
        phone = value;
    }

    public void setEmail(String value)
    {
        email = value;
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

    public List<String> getCountryList()
    {
        return countryListNames;
    }

    public void setCountryList(List<String> names)
    {
        countryListNames = names;
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
