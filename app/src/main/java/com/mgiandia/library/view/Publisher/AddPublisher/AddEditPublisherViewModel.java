package com.mgiandia.library.view.Publisher.AddPublisher;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.mgiandia.library.dao.PublisherDAO;
import com.mgiandia.library.domain.Publisher;
import com.mgiandia.library.memorydao.CountryDAOMemory;
import com.mgiandia.library.memorydao.PublisherDAOMemory;
import com.mgiandia.library.ui.model.ButtonClicked;

public class AddEditPublisherViewModel extends ViewModel implements ButtonClicked
{
    private AddEditPublisherPresenter presenter;
    PublisherDAO publisherDAO = new PublisherDAOMemory();
    private final MutableLiveData<String> name = new MutableLiveData<>();
    private final MutableLiveData<String> phone = new MutableLiveData<>();
    private final MutableLiveData<String> email = new MutableLiveData<>();
    private final MutableLiveData<String> country = new MutableLiveData<>();
    private final MutableLiveData<Integer> countryPosition = new MutableLiveData<>();
    private final MutableLiveData<String> city = new MutableLiveData<>();
    private final MutableLiveData<String> street = new MutableLiveData<>();
    private final MutableLiveData<String> number = new MutableLiveData<>();
    private final MutableLiveData<String> zipCode = new MutableLiveData<String>();
    private final MutableLiveData<Boolean> completeFields = new MutableLiveData<>();

    public AddEditPublisherPresenter getPresenter(AddEditPublisherView view)
    {
        presenter = new AddEditPublisherPresenter(view, publisherDAO, new CountryDAOMemory().getCountries());
        return presenter;
    }

    public Publisher findPublisher(int publisherID)
    {
        return publisherDAO.find(publisherID);
    }

    public void setName(String name)
    {
        this.name.setValue(name);
    }

    public MutableLiveData<String> getName()
    {
        return name;
    }

    public void setPhone(String phone)
    {
        this.phone.setValue(phone);
    }

    public MutableLiveData<String> getPhone()
    {
        return phone;
    }

    public void setEmail(String email)
    {
        this.email.setValue(email);
    }

    public MutableLiveData<String> getEmail()
    {
        return email;
    }

    public void setCountry(String country)
    {
        this.country.setValue(country);
    }

    public MutableLiveData<String> getCountry()
    {
        return country;
    }

    public void setCountryPosition(int countryPosition)
    {
        this.countryPosition.setValue(countryPosition);
    }

    public MutableLiveData<Integer> getCountryPosition()
    {
        return countryPosition;
    }

    public void setCity(String city)
    {
        this.city.setValue(city);
    }

    public MutableLiveData<String> getCity()
    {
        return city;
    }

    public void setStreet(String street)
    {
        this.street.setValue(street);
    }

    public MutableLiveData<String> getStreet()
    {
        return street;
    }

    public void setNumber(String number)
    {
        this.number.setValue(number);
    }

    public MutableLiveData<String> getNumber()
    {
        return number;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode.setValue(zipCode);
    }

    public MutableLiveData<String> getZipCode()
    {
        return zipCode;
    }

    public void setCompleteFields(boolean value)
    {
        completeFields.setValue(value);
    }

    public MutableLiveData<Boolean> getCompleteFields()
    {
        return completeFields;
    }
}
