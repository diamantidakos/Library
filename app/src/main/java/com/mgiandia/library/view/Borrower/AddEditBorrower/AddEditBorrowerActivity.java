package com.mgiandia.library.view.Borrower.AddEditBorrower;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.platform.ComposeView;
import androidx.lifecycle.ViewModelProvider;
import java.util.List;
import java.util.Objects;
import com.mgiandia.library.R;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.ui.composable.ActivitiesKt;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */
public class AddEditBorrowerActivity extends AppCompatActivity implements AddEditBorrowerView
{
    private String firstName, lastName, phone, email, city, street, number, zipCode;
    private int userTypePosition, countryPosition;
    private AddEditBorrowerViewModel model;

    @Override
    public void setFirstName(String value)
    {
        firstName = value;
    }

    @Override
    public String getFirstName()
    {
        return firstName;
    }

    @Override
    public void setLastName(String value)
    {
        lastName = value;
    }

    @Override
    public String getLastName()
    {
        return lastName;
    }

    @Override
    public void setCategoryPosition(Integer value)
    {
        userTypePosition = value;
    }

    @Override
    public Integer getCategoryPosition()
    {
        return userTypePosition;
    }

    @Override
    public void setPhone(String value)
    {
        phone = value;
    }

    @Override
    public String getPhone()
    {
        return phone;
    }

    @Override
    public void setEmail(String value)
    {
        email = value;
    }

    @Override
    public String getEmail()
    {
        return email;
    }

    @Override
    public void setCountryPosition(Integer value)
    {
        countryPosition = value;
    }

    @Override
    public Integer getCountryPosition()
    {
        return countryPosition;
    }

    @Override
    public void setAddressCity(String value)
    {
        city = value;
    }

    @Override
    public String getAddressCity()
    {
        return city;
    }

    @Override
    public void setAddressStreet(String value)
    {
        street = value;
    }

    @Override
    public String getAddressStreet()
    {
        return street;
    }

    @Override
    public void setAddressNumber(String value)
    {
        number = value;
    }

    @Override
    public String getAddressNumber()
    {
        return number;
    }

    @Override
    public void setAddressPostalCode(String value)
    {
        zipCode = value;
    }

    @Override
    public String getAddressPostalCode()
    {
        return zipCode;
    }

    @Override
    public Integer getAttachedBorrowerID()
    {
        return this.getIntent().hasExtra("borrower_id") ? Objects.requireNonNull(this.getIntent().getExtras()).getInt("borrower_id") : -1;
    }

    @Override
    public void setPageName(String value)
    {
        Objects.requireNonNull(getSupportActionBar()).setTitle(value);
    }

    @Override
    public void setCategoryList(List<String> names) {}

    @Override
    public void setCountryList(List<String> names) {}

    @Override
    public void successfullyFinishActivity(String message)
    {
        Intent retData = new Intent();
        retData.putExtra("message_to_toast", message);
        setResult(RESULT_OK, retData);
        finish();
    }

    @Override
    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(AddEditBorrowerActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, null).create().show();
    }

    private boolean validFields()
    {
        return (getFirstName() != null && getLastName() != null && getCategoryPosition() != null && getPhone() != null && getEmail() != null && getCountryPosition() != null && getAddressCity() != null && getAddressStreet() != null && getAddressNumber() != null && getAddressPostalCode() != null);
    }

    private void getValuesFromViewModel()
    {
        model.getFirstName().observe(this, p ->
        {
            if (p != null)
            {
                setFirstName(p);
            }
        });

        model.getLastName().observe(this, p ->
        {
            if (p != null)
            {
                setLastName(p);
            }
        });

        model.getUserTypePosition().observe(this, p ->
        {
            if (p != null)
            {
                setCategoryPosition(p);
            }
        });

        model.getPhone().observe(this, p ->
        {
            if (p != null)
            {
                setPhone(p);
            }
        });

        model.getEmail().observe(this, p ->
        {
            if (p != null)
            {
                setEmail(p);
            }
        });

        model.getCountryPosition().observe(this, p ->
        {
            if (p != null)
            {
                setCountryPosition(p);
            }
        });

        model.getCity().observe(this, p ->
        {
            if (p != null)
            {
                setAddressCity(p);
            }
        });

        model.getStreet().observe(this, p ->
        {
            if (p != null)
            {
                setAddressStreet(p);
            }
        });

        model.getNumber().observe(this, p ->
        {
            if (p != null)
            {
                setAddressNumber(p);
            }
        });

        model.getZipCode().observe(this, p ->
        {
            if (p != null)
            {
                setAddressPostalCode(p);
            }
        });
    }


    /**
     * Δημιουργεί to layout και αρχικοποιεί
     * το activity.
     * @param savedInstanceState το Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_borrower);

        model = new ViewModelProvider(this).get(AddEditBorrowerViewModel.class);
        final AddEditBorrowerPresenter presenter = model.getPresenter(this);

        ComposeView composeView = findViewById(R.id.compose_view);
        ActivitiesKt.showAddEditBorrowerView(composeView, model);

        int borrowerID = getAttachedBorrowerID();
        Borrower borrower = model.findBorrower(borrowerID);
        if (borrower != null)
        {
            model.setFirstName(borrower.getFirstName());
            model.setLastName(borrower.getLastName());
            model.setUserTypePosition(borrower.getCategory().getId());
            model.setPhone(borrower.getTelephone().getTelephoneNumber());
            model.setEmail(borrower.getEmail().getAddress());
            model.setCountry(borrower.getAddress().getCountry());
            model.setCity(borrower.getAddress().getCity());
            model.setStreet(borrower.getAddress().getStreet());
            model.setNumber(borrower.getAddress().getNumber());
            model.setZipCode(borrower.getAddress().getZipCode().getCode());
        }

        getValuesFromViewModel();

        model.observeClicks(this, buttonTextResId ->
        {
            if (buttonTextResId != null && validFields())
            {
                presenter.onSaveBorrower();
            }
        });
    }
}
