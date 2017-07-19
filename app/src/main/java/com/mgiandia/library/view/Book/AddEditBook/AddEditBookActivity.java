package com.mgiandia.library.view.Book.AddEditBook;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import com.mgiandia.library.R;
import com.mgiandia.library.memorydao.AuthorDAOMemory;
import com.mgiandia.library.memorydao.BookDAOMemory;
import com.mgiandia.library.memorydao.ItemDAOMemory;
import com.mgiandia.library.memorydao.PublisherDAOMemory;
import com.mgiandia.library.view.Util.MultiSelectSpinner;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class AddEditBookActivity extends AppCompatActivity implements AddEditBookView
{
    /**
     * Εμφανίζει ένα μήνυμα τύπου alert με
     * τίτλο title και μήνυμα message.
     * @param title Ο τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(AddEditBookActivity.this)
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
     * Επιστρέφει τον τίτλο του βιβλίου.
     * @return Ο τίτλος του βιβλίου
     */
    public String getBookTitle()
    {
        return ((EditText)findViewById(R.id.edit_text_book_title)).getText().toString().trim();
    }

    /**
     * Επιστρέφει το ISBN του βιβλίου.
     * @return Το ISBN του βιβλίου
     */
    public String getISBN()
    {
        return ((EditText)findViewById(R.id.edit_text_isbn)).getText().toString().trim();
    }

    /**
     * Επιστρέφει το έτος δημοσίευσης του βιβλίου.
     * @return Το έτος δημοσίευσης του βιβλίου
     */
    public String getPublication()
    {
        return ((EditText)findViewById(R.id.edit_text_publication)).getText().toString().trim();
    }

    /**
     * Επιστρέφει το έτος συγγραφής του βιβλίου.
     * @return Το έτος συγγραφής του βιβλίου
     */
    public String getYear()
    {
        return ((EditText)findViewById(R.id.edit_text_publicationyear)).getText().toString().trim();
    }

    /**
     * Επιστρέφει την θέση του συγγραφέα.
     * @return Η θέση του συγγραφέα
     */
    public Integer getPublisherPosition()
    {
        int pos = ((Spinner)findViewById(R.id.edit_text_publisher)).getSelectedItemPosition();
        return pos == 0 ? null : pos;
    }

    /**
     * Επιστρέφει τις θέσεις των βιβλίων του συγγραφέα.
     * @return Οι θέσεις των βιβλίων του συγγραφέα
     */
    public List<Integer> getAuthorPositions()
    {
        List<Integer> positions = new ArrayList<>();
        boolean[] indexes = ((MultiSelectSpinner)findViewById(R.id.edit_text_authors)).getItemsIndexes();

        for(int i = 0; i < indexes.length; i++)
            if(indexes[i])
                positions.add(i+1);

        return positions;
    }

    /**
     * Επιστρέφει το id του βιβλίου.
     * @return Το id του βιβλίου
     */
    public Integer getAttachedBookID()
    {
        return this.getIntent().hasExtra("book_id") ? this.getIntent().getExtras().getInt("book_id") : null;
    }

    /**
     * Θέτει τον τίτλο του βιβλίου
     * @param value Ο τίτλος του βιβλίου
     */
    public void setBookTitle(String value)
    {
        ((EditText)findViewById(R.id.edit_text_book_title)).setText(value);
    }

    /**
     * Θέτει την θέση του συγγραφέα.
     * @param value Η θέση του συγγραφέα.
     */
    public void setPublisherPosition(Integer value)
    {
        ((Spinner)findViewById(R.id.edit_text_publisher)).setSelection(value);
    }

    /**
     * Θέτει το ISBN του βιβλίου
     * @param value Το ISBN του βιβλίου
     */
    public void setISBN(String value)
    {
        ((EditText)findViewById(R.id.edit_text_isbn)).setText(value);
    }

    /**
     * Θέτει την ημερομηνία έκδοσης του βιβλίου
     * @param value Η ημερομηνία έκδοσης του βιβλίου
     */
    public void setPublication(String value)
    {
        ((EditText)findViewById(R.id.edit_text_publication)).setText(value);
    }

    /**
     * Θέτει το έτος του βιβλίου
     * @param value Η το έτος του βιβλίου
     */
    public void setYear(String value)
    {
        ((EditText)findViewById(R.id.edit_text_publicationyear)).setText(value);
    }

    /**
     * Θέτει τις θέσεις των συγγραφέων
     * @param value Οι θέσεις των συγγραφέων.
     */
    public void setAuthorPositions(List<Integer> value)
    {
        for(int i = 0; i < value.size(); i++)
            value.set(i, value.get(i)-1);

        ((MultiSelectSpinner)findViewById(R.id.edit_text_authors)).setSelectedItems(value);
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
     * Θέτει την λίστα των συγγραφέων.
     * @param names Τα ονόματα των συγγραφέων
     */
    public void setAuthorList(List<String> names)
    {
        ((MultiSelectSpinner) findViewById(R.id.edit_text_authors)).setItems(names);
        setAuthorPositions(new ArrayList<Integer>());
    }

    /**
     * Θέτει την λίστα των συγγραφέων με ονόματα
     * names και με όνομα προεπιλογής defaultName
     * @param names Η λίστα των ονομάτων
     * @param defaultName Το προκαθορισμένο όνομα
     */
    public void setPublisherList(List<String> names, String defaultName)
    {
        names.add(0, defaultName);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ((Spinner) findViewById(R.id.edit_text_publisher)).setAdapter(adapter);
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
        setContentView(R.layout.activity_add_edit_book);
        final AddEditBookPresenter presenter = new AddEditBookPresenter(this, new BookDAOMemory(), new PublisherDAOMemory(), new AuthorDAOMemory(), new ItemDAOMemory());

        findViewById(R.id.complete_registration_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onSaveBook();
            }
        });
    }
}
