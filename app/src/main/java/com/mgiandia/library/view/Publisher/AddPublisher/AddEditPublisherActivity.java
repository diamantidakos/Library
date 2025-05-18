package com.mgiandia.library.view.Publisher.AddPublisher;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.platform.ComposeView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import java.util.List;
import java.util.Objects;
import com.mgiandia.library.R;
import com.mgiandia.library.domain.Publisher;
import com.mgiandia.library.ui.composable.ActivitiesKt;
import com.mgiandia.library.view.Util.AbstractLibraryActivity;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */
public class AddEditPublisherActivity extends AbstractLibraryActivity implements AddEditPublisherView
{
    private String name, phone, email, city, street, number, zipCode;
    private int countryPosition;
    private AddEditPublisherViewModel model;

    @Override
    public void setName(String value)
    {
        name = value;
    }

    @Override
    public String getName()
    {
        return name;
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
    public Integer getAttachedPublisherID()
    {
        return this.getIntent().hasExtra("publisher_id") ? Objects.requireNonNull(this.getIntent().getExtras()).getInt("publisher_id") : -1;
    }

    @Override
    public void setPageName(String value)
    {
        Objects.requireNonNull(getSupportActionBar()).setTitle(value);
    }

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
        new AlertDialog.Builder(getApplicationContext())
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, null).create().show();
    }

    private boolean validFields()
    {
        return (getName() != null && getPhone() != null && getEmail() != null && getCountryPosition() != null && getAddressCity() != null && getAddressStreet() != null && getAddressNumber() != null && getAddressPostalCode() != null);
    }

    private void getValuesFromViewModel()
    {
        model.getName().observe((LifecycleOwner) this, p ->
        {
            if (p != null)
            {
                setName(p.trim());
            }
        });

        model.getPhone().observe((LifecycleOwner) this, p ->
        {
            if (p != null)
            {
                setPhone(p.trim());
            }
        });

        model.getEmail().observe((LifecycleOwner) this, p ->
        {
            if (p != null)
            {
                setEmail(p.trim());
            }
        });

        model.getCountryPosition().observe((LifecycleOwner) this, p ->
        {
            if (p != null)
            {
                setCountryPosition(p);
            }
        });

        model.getCity().observe((LifecycleOwner) this, p ->
        {
            if (p != null)
            {
                setAddressCity(p.trim());
            }
        });

        model.getStreet().observe((LifecycleOwner) this, p ->
        {
            if (p != null)
            {
                setAddressStreet(p.trim());
            }
        });

        model.getNumber().observe((LifecycleOwner) this, p ->
        {
            if (p != null)
            {
                setAddressNumber(p.trim());
            }
        });

        model.getZipCode().observe((LifecycleOwner) this, p ->
        {
            if (p != null)
            {
                setAddressPostalCode(p.trim());
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
        setContentView(R.layout.activity_add_edit_publisher);

        model = new ViewModelProvider((ViewModelStoreOwner) this).get(AddEditPublisherViewModel.class);
        final AddEditPublisherPresenter presenter = model.getPresenter(this);

        ComposeView composeView = findViewById(R.id.compose_view);
        ActivitiesKt.showAddEditPublisherView(composeView, model);

        int publisherID = getAttachedPublisherID();
        Publisher publisher = model.findPublisher(publisherID);
        if (publisher != null)
        {
            model.setName(publisher.getName());
            model.setPhone(publisher.getTelephone().getTelephoneNumber());
            model.setEmail(publisher.getEMail().getAddress());
            model.setCountry(publisher.getAddress().getCountry());
            model.setCity(publisher.getAddress().getCity());
            model.setStreet(publisher.getAddress().getStreet());
            model.setNumber(publisher.getAddress().getNumber());
            model.setZipCode(publisher.getAddress().getZipCode().getCode());
        }

        getValuesFromViewModel();

        model.observeClicks(this, buttonTextResId ->
        {
            if (buttonTextResId != null && validFields() && buttonTextResId.equals(R.string.complete_registration))
            {
                presenter.onSavePublisher();
            }
        });
    }
}