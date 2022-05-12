package com.mgiandia.library.view.Borrower.AddEditBorrower;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import com.mgiandia.library.R;
import com.mgiandia.library.memorydao.BorrowerCategoryDAOMemory;
import com.mgiandia.library.memorydao.BorrowerDAOMemory;
import com.mgiandia.library.memorydao.CountryDAOMemory;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class AddEditBorrowerActivity extends AppCompatActivity implements AddEditBorrowerView
{
    /**
     * Εμφανίζει ένα μήνυμα τύπου alert με
     * τίτλο title και μήνυμα message.
     * @param title Ο τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(AddEditBorrowerActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, null).create().show();
    }

    /**
     * Το μήνυμα που εμφανίζεται όταν τελειώνει
     * επιτυχώς ένα activity.
     * @param message Το μήνυμα που θα εμφανίσει
     */
    public void successfullyFinishActivity(String message)
    {
        Intent retData = new Intent();
        retData.putExtra("message_to_toast", message);
        setResult(RESULT_OK, retData);
        finish();
    }

    /**
     * Επιστρέφει το πρώτο όνομα του δανειστή.
     * @return Το πρώτο όνομα του δανειστή
     */
    public String getFirstName()
    {
        return ((EditText)findViewById(R.id.edit_text_first_name)).getText().toString().trim();
    }

    /**
     * Επιστρέφει το επώνυμο του δανειστή.
     * @return Το επώνυμο του δανειστή
     */
    public String getLastName()
    {
        return ((EditText)findViewById(R.id.edit_text_last_name)).getText().toString().trim();
    }

    /**
     * Επιστρέφει την θέση της κατηγορίας.
     * @return Η θέση της κατηγορίας
     */
    public Integer getCategoryPosition()
    {
        return ((Spinner)findViewById(R.id.edit_text_category_name)).getSelectedItemPosition()+1;
    }

    /**
     * Επιστρέφει τον αριθμό του δανειστή.
     * @return Ο αριθμός του δανειστή
     */
    public String getPhone()
    {
        return ((EditText)findViewById(R.id.edit_text_telephone_name)).getText().toString().trim();
    }

    /**
     * Επιστρέφει τον αριθμό ηλεκτρονικού ταχυδρομείου.
     * @return Ο αριθμός ηλεκτρονικού ταχυδρομείου
     */
    public String getEmail()
    {
        return ((EditText)findViewById(R.id.edit_text_email_name)).getText().toString().trim();
    }

    /**
     * Επιστρέφει την θέση της χώρας του δανειζόμενου.
     * @return Η θέση της χώρας
     */
    public Integer getCountryPosition()
    {
        return ((Spinner)findViewById(R.id.edit_text_country_name)).getSelectedItemPosition();
    }

    /**
     * Επιστρέφει την πόλη της διεύθυνσης.
     * @return Η πόλη της διεύθυνσης
     */
    public String getAddressCity()
    {
        return ((EditText)findViewById(R.id.edit_text_city_name)).getText().toString().trim();
    }

    /**
     * Επιστρέφει την οδό της διεύθυνσης.
     * @return Η οδός της διεύθυνσης
     */
    public String getAddressStreet()
    {
        return ((EditText)findViewById(R.id.edit_text_street_name)).getText().toString().trim();
    }

    /**
     * Επιστρέφει τον αριθμό της διεύθυνσης.
     * @return Ο αριθμός της διεύθυνσης
     */
    public String getAddressNumber()
    {
        return ((EditText)findViewById(R.id.edit_text_number_name)).getText().toString().trim();
    }

    /**
     * Επιστρέφει τον ταχυδρομικό κώδικα.
     * @return Ο ταχυδρομικός κώδικας
     */
    public String getAddressPostalCode()
    {
        return ((EditText)findViewById(R.id.edit_text_zip_name)).getText().toString().trim();
    }

    /**
     * Επιστρέφει το id του δανειστή.
     * @return Το id του δανειστή
     */
    public Integer getAttachedBorrowerID()
    {
        return this.getIntent().hasExtra("borrower_id") ? this.getIntent().getExtras().getInt("borrower_id") : null;
    }

    /**
     * Θέτει το πρώτο όνομα του δανειστή.
     * @param value Το πρώτο όνομα του δανειστή
     */
    public void setFirstName(String value)
    {
        ((EditText)findViewById(R.id.edit_text_first_name)).setText(value);
    }

    /**
     * Θέτει το επώνυμο του συγγραφέα.
     * @param value Το επώνυμο του συγγραφέα
     */
    public void setLastName(String value)
    {
        ((EditText)findViewById(R.id.edit_text_last_name)).setText(value);
    }

    /**
     * Θέτει την θέση της κατηγορίας.
     * @param value Ο αριθμός της κατηγορίας
     */
    public void setCategoryPosition(Integer value)
    {
        ((Spinner)findViewById(R.id.edit_text_category_name)).setSelection(value-1);
    }

    /**
     * Θέτει τον αρι8μό του δανειζόμενου.
     * @param value Ο αρι8μός του δανειζόμενου
     */
    public void setPhone(String value)
    {
        ((EditText)findViewById(R.id.edit_text_telephone_name)).setText(value);
    }

    /**
     * Θέτει τον αριθμό ηλεκτρονικού ταχυδρομείου του δανειζόμενου.
     * @param value Ο αρι8μός του ηλεκτρονικού ταχυδρομείου του δανειζόμενου
     */
    public void setEmail(String value)
    {
        ((EditText)findViewById(R.id.edit_text_email_name)).setText(value);
    }

    /**
     * Θέτει την θέση της χώρας του δανειζόμενου.
     * @param value Ο αριθμός της θέσης του δανειζόμενου
     */
    public void setCountryPosition(Integer value)
    {
        ((Spinner)findViewById(R.id.edit_text_country_name)).setSelection(value);
    }

    /**
     * Θέτει την πόλη του δανειζόμενου.
     * @param value Η πόλη του δανειζόμενου
     */
    public void setAddressCity(String value)
    {
        ((EditText)findViewById(R.id.edit_text_city_name)).setText(value);
    }

    /**
     * Θέτει την οδό του δανειζόμενου.
     * @param value Η οδός του δανειζόμενου
     */
    public void setAddressStreet(String value)
    {
        ((EditText)findViewById(R.id.edit_text_street_name)).setText(value);
    }

    /**
     * Θέτει τον αριθμό του δανειζόμενου.
     * @param value Ο αριθμός του δανειζόμενου
     */
    public void setAddressNumber(String value)
    {
        ((EditText)findViewById(R.id.edit_text_number_name)).setText(value);
    }

    /**
     * Θέτει τον ταχυδρομικό κώδικα.
     * @param value Ο ταχυδρομικός κώδικας
     */
    public void setAddressPostalCode(String value)
    {
        ((EditText)findViewById(R.id.edit_text_zip_name)).setText(value);
    }

    /**
     * Θέτει το όνομα της σελίδας.
     * @param value το όνομα της σελίδας
     */
    public void setPageName(String value)
    {
        getSupportActionBar().setTitle(value);
    }

    /**
     * Θέτει από την λίστα με τα ονόματα των κατηγοριών
     * την κατηγορία.
     * @param names Η λίστα με τα ονόματα των κατηγοριών
     */
    public void setCategoryList(List<String> names)
    {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ((Spinner) findViewById(R.id.edit_text_category_name)).setAdapter(adapter);
    }

    /**
     * Θέτει από την λίστα με τα ονόματα των χωρών
     * το όνομα.
     * @param names Η λίστα με τα ονόματα των χωρών
     */
    public void setCountryList(List<String> names)
    {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ((Spinner) findViewById(R.id.edit_text_country_name)).setAdapter(adapter);
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
        final AddEditBorrowerPresenter presenter = new AddEditBorrowerPresenter(this, new BorrowerDAOMemory(), new BorrowerCategoryDAOMemory(), new CountryDAOMemory().getCountries());

        findViewById(R.id.complete_registration_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onSaveBorrower();
            }
        });
    }
}
