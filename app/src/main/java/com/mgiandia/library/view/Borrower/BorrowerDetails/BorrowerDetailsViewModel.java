package com.mgiandia.library.view.Borrower.BorrowerDetails;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.memorydao.BorrowerDAOMemory;
import com.mgiandia.library.memorydao.LoanDAOMemory;
import com.mgiandia.library.ui.model.ButtonClicked;

public class BorrowerDetailsViewModel extends ViewModel implements ButtonClicked
{
    private BorrowerDetailsPresenter presenter;
    private final MutableLiveData<String> borrowerNo = new MutableLiveData<>();
    private final MutableLiveData<String> firstName = new MutableLiveData<>();
    private final MutableLiveData<String> lastName = new MutableLiveData<>();
    private final MutableLiveData<String> category = new MutableLiveData<>();
    private final MutableLiveData<String> phone = new MutableLiveData<>();
    private final MutableLiveData<String> email = new MutableLiveData<>();
    private final MutableLiveData<String> country = new MutableLiveData<>();
    private final MutableLiveData<String> city = new MutableLiveData<>();
    private final MutableLiveData<String> street = new MutableLiveData<>();
    private final MutableLiveData<String> number = new MutableLiveData<>();
    private final MutableLiveData<String> postCode = new MutableLiveData<>();

    private BorrowerDAO borrowerDAO = new BorrowerDAOMemory();

    public BorrowerDetailsPresenter getPresenter(BorrowerDetailsView view)
    {
        presenter = new BorrowerDetailsPresenter(view, borrowerDAO, new LoanDAOMemory());
        return presenter;
    }

    public Borrower findBorrower(int borrowerID)
    {
        return borrowerDAO.find(borrowerID);
    }

    public void setBorrowerNo(String borrowerNo)
    {
        this.borrowerNo.setValue(borrowerNo);
    }

    public MutableLiveData<String> getBorrowerNo()
    {
        return borrowerNo;
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

    public void setCategory(String category)
    {
        this.category.setValue(category);
    }

    public MutableLiveData<String> getCategory()
    {
        return category;
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