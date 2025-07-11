package com.mgiandia.library.view.Publisher.PublisherDetails;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mgiandia.library.dao.PublisherDAO;
import com.mgiandia.library.domain.Publisher;
import com.mgiandia.library.memorydao.PublisherDAOMemory;
import com.mgiandia.library.ui.model.ButtonClicked;

public class PublisherDetailsViewModel extends ViewModel implements ButtonClicked
{
    private PublisherDetailsPresenter presenter;
    private final PublisherDAO publisherDAO = new PublisherDAOMemory();
    private final MutableLiveData<String> publisherID = new MutableLiveData<>();
    private final MutableLiveData<String> name = new MutableLiveData<>();
    private final MutableLiveData<String> phone = new MutableLiveData<>();
    private final MutableLiveData<String> email = new MutableLiveData<>();
    private final MutableLiveData<String> published = new MutableLiveData<>();
    private final MutableLiveData<String> country = new MutableLiveData<>();
    private final MutableLiveData<String> city = new MutableLiveData<>();
    private final MutableLiveData<String> street = new MutableLiveData<>();
    private final MutableLiveData<String> number = new MutableLiveData<>();
    private final MutableLiveData<String> postCode = new MutableLiveData<>();


    public PublisherDetailsPresenter getPresenter(PublisherDetailsView view)
    {
        presenter = new PublisherDetailsPresenter(view, publisherDAO);
        return presenter;
    }

    public Publisher findPublisher(int publisherID)
    {
        return publisherDAO.find(publisherID);
    }

    public void setPublisherID(String publisherID)
    {
        this.publisherID.setValue(publisherID);
    }

    public MutableLiveData<String> getPublisherID()
    {
        return publisherID;
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

    public void setPublished(String published)
    {
        this.published.setValue(published);
    }

    public MutableLiveData<String> getPublished()
    {
        return published;
    }

    public void setCountry(String country)
    {
        this.country.setValue(country);
    }

    public MutableLiveData<String> getCountry()
    {
        return country;
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

    public void setPostCode(String postCode)
    {
        this.postCode.setValue(postCode);
    }

    public MutableLiveData<String> getPostCode()
    {
        return postCode;
    }
}