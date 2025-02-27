package com.mgiandia.library.view.Borrower.AddEditBorrower;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.memorydao.BorrowerCategoryDAOMemory;
import com.mgiandia.library.memorydao.BorrowerDAOMemory;
import com.mgiandia.library.memorydao.CountryDAOMemory;
import com.mgiandia.library.ui.model.ButtonClicked;

public class AddEditBorrowerViewModel extends ViewModel implements ButtonClicked
{
    private AddEditBorrowerPresenter presenter;
    private BorrowerDAO borrowerDAO = new BorrowerDAOMemory();
    private final MutableLiveData<String> firstName = new MutableLiveData<>();
    private final MutableLiveData<String> lastName = new MutableLiveData<>();
    private final MutableLiveData<Integer> userTypePosition = new MutableLiveData<>();
    private final MutableLiveData<String> phone = new MutableLiveData<>();
    private final MutableLiveData<String> email = new MutableLiveData<>();
    private final MutableLiveData<String> country = new MutableLiveData<>();
    private final MutableLiveData<Integer> countryPosition = new MutableLiveData<>();
    private final MutableLiveData<String> city = new MutableLiveData<>();
    private final MutableLiveData<String> street = new MutableLiveData<>();
    private final MutableLiveData<String> number = new MutableLiveData<>();
    private final MutableLiveData<String> zipCode = new MutableLiveData<>();
    private final MutableLiveData<Boolean> completeFields = new MutableLiveData<>();

    public AddEditBorrowerPresenter getPresenter(AddEditBorrowerView view)
    {
        presenter = new AddEditBorrowerPresenter(view, borrowerDAO, new BorrowerCategoryDAOMemory(), new CountryDAOMemory().getCountries());
        return presenter;
    }

    public Borrower findBorrower(int borrowerID)
    {
        return borrowerDAO.find(borrowerID);
    }

    public void setFirstName(String firstName)
    {
        this.firstName.setValue(firstName);
    }

    public MutableLiveData<String> getFirstName()
    {
        return firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName.setValue(lastName);
    }

    public MutableLiveData<String> getLastName()
    {
        return lastName;
    }

    public void setUserTypePosition(int userTypePosition)
    {
        this.userTypePosition.setValue(userTypePosition);
    }

    public MutableLiveData<Integer> getUserTypePosition()
    {
        return userTypePosition;
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
        return city;
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
