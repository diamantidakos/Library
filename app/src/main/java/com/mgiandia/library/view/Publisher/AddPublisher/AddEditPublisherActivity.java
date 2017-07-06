package com.mgiandia.library.view.Publisher.AddPublisher;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import com.mgiandia.library.R;
import com.mgiandia.library.memorydao.CountryDAOMemory;
import com.mgiandia.library.memorydao.PublisherDAOMemory;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class AddEditPublisherActivity extends AppCompatActivity implements AddEditPublisherView
{
    /**
     * Εμφανίζει ενα μήνυμα τύπου alert με
     * τίτλο title και μήνυμα message.
     * @param title Ο τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(AddEditPublisherActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, null).create().show();
    }

    /**
     * Το μήνυμα πoυ εμφανίζεται όταν τελειώνει
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
     * Επιστρέφει το όνομα του εκδότη.
     * @return Το όνομα του εκδότη
     */
    public String getName()
    {
        return ((EditText)findViewById(R.id.edit_text_first_name)).getText().toString().trim();
    }

    /**
     * Επιστρέφει το τηλέφωνο του εκδότη.
     * @return Το τηλέφωνο του εκδότη
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
     * Επιστρέφει την θέση της χώρας του εκδότη.
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
     * Επιστρέφει το id του εκδότη.
     * @return Το id του εκδότη
     */
    public Integer getAttachedPublisherID()
    {
        return this.getIntent().hasExtra("publisher_id") ? this.getIntent().getExtras().getInt("publisher_id") : null;
    }

    /**
     * Θέτει το όνομα του εκδότη.
     * @param value Το όνομα του εκδότη
     */
    public void setName(String value)
    {
        ((EditText)findViewById(R.id.edit_text_first_name)).setText(value);
    }

    /**
     * Θέτει τον αρι8μό του εκδότη.
     * @param value Ο αρι8μός του εκδότη
     */
    public void setPhone(String value)
    {
        ((EditText)findViewById(R.id.edit_text_telephone_name)).setText(value);
    }

    /**
     * Θέτει τον αριθμό ηλελτρονικό ταχυδρομίου του εκδότη.
     * @param value Ο αρι8μός του ηλελτρονικό ταχυδρομίου του εκδότη.
     */
    public void setEmail(String value)
    {
        ((EditText)findViewById(R.id.edit_text_email_name)).setText(value);
    }

    /**
     * Θέτει την θέση της χώρας του εκδότη.
     * @param value Ο αριθμός της θέσης του εκδότη
     */
    public void setCountryPosition(Integer value)
    {
        ((Spinner)findViewById(R.id.edit_text_country_name)).setSelection(value);
    }

    /**
     * Θέτει την πόλη του εκδότη.
     * @param value Η πόλη του εκδότη
     */
    public void setAddressCity(String value)
    {
        ((EditText)findViewById(R.id.edit_text_city_name)).setText(value);
    }

    /**
     * Θέτει την οδό του εκδότη.
     * @param value Η οδός του εκδότη
     */
    public void setAddressStreet(String value)
    {
        ((EditText)findViewById(R.id.edit_text_street_name)).setText(value);
    }

    /**
     * Θέτει τον αριθμό του εκδότη.
     * @param value Ο αριθμός του εκδότη
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
     * Θέτει από την λίστα με τα ονόματα των χωρών
     * το όνομα.
     * @param names Η λίστα με τα ονόματα των χωρων
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
        setContentView(R.layout.activity_add_edit_publisher);
        final AddEditPublisherPresenter presenter = new AddEditPublisherPresenter(this, new PublisherDAOMemory(), new CountryDAOMemory().getCountries());

        findViewById(R.id.complete_registration_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onSavePublisher();
            }
        });
    }
}
