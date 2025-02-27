package com.mgiandia.library.view.Borrower.AddEditBorrower;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.platform.ComposeView;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;
import java.util.Objects;

import com.mgiandia.library.R;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.memorydao.AuthorDAOMemory;
import com.mgiandia.library.memorydao.BorrowerCategoryDAOMemory;
import com.mgiandia.library.memorydao.BorrowerDAOMemory;
import com.mgiandia.library.memorydao.CountryDAOMemory;
import com.mgiandia.library.ui.composable.ActivitiesKt;
import com.mgiandia.library.view.Author.AddEditAuthor.AddEditAuthorPresenter;
import com.mgiandia.library.view.Author.AddEditAuthor.AddEditAuthorViewModel;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class AddEditBorrowerActivity extends AppCompatActivity implements AddEditBorrowerView
{
//    /**
//     * Εμφανίζει ένα μήνυμα τύπου alert με
//     * τίτλο title και μήνυμα message.
//     * @param title Ο τίτλος του μηνύματος
//     * @param message Το περιεχόμενο του μηνύματος
//     */
//    public void showErrorMessage(String title, String message)
//    {
//        new AlertDialog.Builder(AddEditBorrowerActivity.this)
//                .setCancelable(true)
//                .setTitle(title)
//                .setMessage(message)
//                .setPositiveButton(R.string.ok, null).create().show();
//    }
//
//    /**
//     * Το μήνυμα που εμφανίζεται όταν τελειώνει
//     * επιτυχώς ένα activity.
//     * @param message Το μήνυμα που θα εμφανίσει
//     */
//    public void successfullyFinishActivity(String message)
//    {
//        Intent retData = new Intent();
//        retData.putExtra("message_to_toast", message);
//        setResult(RESULT_OK, retData);
//        finish();
//    }
//
//    /**
//     * Επιστρέφει το πρώτο όνομα του δανειστή.
//     * @return Το πρώτο όνομα του δανειστή
//     */
//    public String getFirstName()
//    {
//        return ((EditText)findViewById(R.id.edit_text_first_name)).getText().toString().trim();
//    }
//
//    /**
//     * Επιστρέφει το επώνυμο του δανειστή.
//     * @return Το επώνυμο του δανειστή
//     */
//    public String getLastName()
//    {
//        return ((EditText)findViewById(R.id.edit_text_last_name)).getText().toString().trim();
//    }
//
//    /**
//     * Επιστρέφει την θέση της κατηγορίας.
//     * @return Η θέση της κατηγορίας
//     */
//    public Integer getCategoryPosition()
//    {
//        return ((Spinner)findViewById(R.id.edit_text_category_name)).getSelectedItemPosition()+1;
//    }
//
//    /**
//     * Επιστρέφει τον αριθμό του δανειστή.
//     * @return Ο αριθμός του δανειστή
//     */
//    public String getPhone()
//    {
//        return ((EditText)findViewById(R.id.edit_text_telephone_name)).getText().toString().trim();
//    }
//
//    /**
//     * Επιστρέφει τον αριθμό ηλεκτρονικού ταχυδρομείου.
//     * @return Ο αριθμός ηλεκτρονικού ταχυδρομείου
//     */
//    public String getEmail()
//    {
//        return ((EditText)findViewById(R.id.edit_text_email_name)).getText().toString().trim();
//    }
//
//    /**
//     * Επιστρέφει την θέση της χώρας του δανειζόμενου.
//     * @return Η θέση της χώρας
//     */
//    public Integer getCountryPosition()
//    {
//        return ((Spinner)findViewById(R.id.edit_text_country_name)).getSelectedItemPosition();
//    }
//
//    /**
//     * Επιστρέφει την πόλη της διεύθυνσης.
//     * @return Η πόλη της διεύθυνσης
//     */
//    public String getAddressCity()
//    {
//        return ((EditText)findViewById(R.id.edit_text_city_name)).getText().toString().trim();
//    }
//
//    /**
//     * Επιστρέφει την οδό της διεύθυνσης.
//     * @return Η οδός της διεύθυνσης
//     */
//    public String getAddressStreet()
//    {
//        return ((EditText)findViewById(R.id.edit_text_street_name)).getText().toString().trim();
//    }
//
//    /**
//     * Επιστρέφει τον αριθμό της διεύθυνσης.
//     * @return Ο αριθμός της διεύθυνσης
//     */
//    public String getAddressNumber()
//    {
//        return ((EditText)findViewById(R.id.edit_text_number_name)).getText().toString().trim();
//    }
//
//    /**
//     * Επιστρέφει τον ταχυδρομικό κώδικα.
//     * @return Ο ταχυδρομικός κώδικας
//     */
//    public String getAddressPostalCode()
//    {
//        return ((EditText)findViewById(R.id.edit_text_zip_name)).getText().toString().trim();
//    }
//
//    /**
//     * Επιστρέφει το id του δανειστή.
//     * @return Το id του δανειστή
//     */
//    public Integer getAttachedBorrowerID()
//    {
//        return this.getIntent().hasExtra("borrower_id") ? this.getIntent().getExtras().getInt("borrower_id") : null;
//    }
//
//    /**
//     * Θέτει το πρώτο όνομα του δανειστή.
//     * @param value Το πρώτο όνομα του δανειστή
//     */
//    public void setFirstName(String value)
//    {
//        ((EditText)findViewById(R.id.edit_text_first_name)).setText(value);
//    }
//
//    /**
//     * Θέτει το επώνυμο του συγγραφέα.
//     * @param value Το επώνυμο του συγγραφέα
//     */
//    public void setLastName(String value)
//    {
//        ((EditText)findViewById(R.id.edit_text_last_name)).setText(value);
//    }
//
//    /**
//     * Θέτει την θέση της κατηγορίας.
//     * @param value Ο αριθμός της κατηγορίας
//     */
//    public void setCategoryPosition(Integer value)
//    {
//        ((Spinner)findViewById(R.id.edit_text_category_name)).setSelection(value-1);
//    }
//
//    /**
//     * Θέτει τον αρι8μό του δανειζόμενου.
//     * @param value Ο αρι8μός του δανειζόμενου
//     */
//    public void setPhone(String value)
//    {
//        ((EditText)findViewById(R.id.edit_text_telephone_name)).setText(value);
//    }
//
//    /**
//     * Θέτει τον αριθμό ηλεκτρονικού ταχυδρομείου του δανειζόμενου.
//     * @param value Ο αρι8μός του ηλεκτρονικού ταχυδρομείου του δανειζόμενου
//     */
//    public void setEmail(String value)
//    {
//        ((EditText)findViewById(R.id.edit_text_email_name)).setText(value);
//    }
//
//    /**
//     * Θέτει την θέση της χώρας του δανειζόμενου.
//     * @param value Ο αριθμός της θέσης του δανειζόμενου
//     */
//    public void setCountryPosition(Integer value)
//    {
//        ((Spinner)findViewById(R.id.edit_text_country_name)).setSelection(value);
//    }
//
//    /**
//     * Θέτει την πόλη του δανειζόμενου.
//     * @param value Η πόλη του δανειζόμενου
//     */
//    public void setAddressCity(String value)
//    {
//        ((EditText)findViewById(R.id.edit_text_city_name)).setText(value);
//    }
//
//    /**
//     * Θέτει την οδό του δανειζόμενου.
//     * @param value Η οδός του δανειζόμενου
//     */
//    public void setAddressStreet(String value)
//    {
//        ((EditText)findViewById(R.id.edit_text_street_name)).setText(value);
//    }
//
//    /**
//     * Θέτει τον αριθμό του δανειζόμενου.
//     * @param value Ο αριθμός του δανειζόμενου
//     */
//    public void setAddressNumber(String value)
//    {
//        ((EditText)findViewById(R.id.edit_text_number_name)).setText(value);
//    }
//
//    /**
//     * Θέτει τον ταχυδρομικό κώδικα.
//     * @param value Ο ταχυδρομικός κώδικας
//     */
//    public void setAddressPostalCode(String value)
//    {
//        ((EditText)findViewById(R.id.edit_text_zip_name)).setText(value);
//    }
//
//    /**
//     * Θέτει το όνομα της σελίδας.
//     * @param value το όνομα της σελίδας
//     */
//    public void setPageName(String value)
//    {
//        getSupportActionBar().setTitle(value);
//    }
//
//    /**
//     * Θέτει από την λίστα με τα ονόματα των κατηγοριών
//     * την κατηγορία.
//     * @param names Η λίστα με τα ονόματα των κατηγοριών
//     */
//    public void setCategoryList(List<String> names)
//    {
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, names);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        ((Spinner) findViewById(R.id.edit_text_category_name)).setAdapter(adapter);
//    }
//
//    /**
//     * Θέτει από την λίστα με τα ονόματα των χωρών
//     * το όνομα.
//     * @param names Η λίστα με τα ονόματα των χωρών
//     */
//    public void setCountryList(List<String> names)
//    {
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, names);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        ((Spinner) findViewById(R.id.edit_text_country_name)).setAdapter(adapter);
//    }


    String firstName, lastName, phone, email, city, street, number, zipCode;
    int userTypePosition, countryPosition;
    AddEditBorrowerViewModel model;

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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_edit_borrower);

        model = new ViewModelProvider(this).get(AddEditBorrowerViewModel.class);
        final AddEditBorrowerPresenter presenter = model.getPresenter(this);

        ComposeView composeView = findViewById(R.id.compose_view);
        ActivitiesKt.showAddEditBorrowerView(composeView, model);

        int borrowerID = getAttachedBorrowerID();
        Borrower borrower = model.findBorrower(borrowerID);
        if (borrower != null)
        {
            model.setCompleteFields(true);
        }

        if (Boolean.TRUE.equals(model.getCompleteFields().getValue()))
        {
            assert borrower != null;
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
