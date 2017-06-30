package com.mgiandia.library.view.Loans.AddLoan;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import com.mgiandia.library.R;
import com.mgiandia.library.memorydao.BookDAOMemory;
import com.mgiandia.library.memorydao.BorrowerDAOMemory;
import com.mgiandia.library.memorydao.LoanDAOMemory;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class AddLoansActivity extends AppCompatActivity implements AddLoansView
{
    /**
     * Εμφανίζει ενα μήνυμα τύπου alert με
     * τίτλο title και μήνυμα message.
     * @param title Ο τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(message).setPositiveButton(R.string.ok, null).create().show();
    }

    /**
     * Το μήνυμα πoυ εμφανίζεται όταν τελειώνει
     * επιτυχώς ένα activity.
     * @param message Το μήνυμα που θα εμφανίσει
     */
    public void successfullyAddLoanAndFinishActivity(String message)
    {
        Intent retData = new Intent();
        retData.putExtra("message_to_toast", message);
        setResult(RESULT_OK, retData);
        finish();
    }

    /**
     * Επιστρέφει το id tου επιλεγόμενου βιβλίου.
     * @return Το id tου επιλεγόμενου βιβλίου
     */
    public int getSelectedBookId()
    {
        return ((Spinner)findViewById(R.id.edit_text_book)).getSelectedItemPosition()+1;
    }

    /**
     * Θέτει το id του δανιζόμενου για
     * το επιλεγόμενο βιβλίο.
     * @param value Το id του δανιζόμενου
     */
    public void setBorrowerId(String value)
    {
        ((TextView)findViewById(R.id.edit_text_borrower)).setText(value);
    }

    /**
     * Θέτει την λίστα με τα ονόματα που
     * θα εμφανίζονται.
     * @param names Η λίστα με τα ονόματα
     */
    public void setBookList(List<String> names)
    {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ((Spinner) findViewById(R.id.edit_text_book)).setAdapter(adapter);
    }

    /**
     * Εμφανίζει ένα μήνυμα τύπου alert
     * με τίτλο title και περιεχόμενο
     * message.
     * @param title Ο τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    public void showAlert(String title, String message)
    {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(message).setPositiveButton(R.string.ok, null).create().show();
    }

    /**
     * Επιστρέφει το id του βιβλίου.
     * @return Το id του βιβλίου
     */
    public int getAttachedBorrowerID()
    {
        return this.getIntent().getExtras().getInt("borrower_id");
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
     * Δημιουργεί to layout και αρχικοποιεί
     * το activity.
     * @param savedInstanceState το Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_loan);
        final AddLoansPresenter presenter = new AddLoansPresenter(this, new BookDAOMemory(), new BorrowerDAOMemory(), new LoanDAOMemory());

        findViewById(R.id.complete_registration_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                presenter.onSaveLoan();
            }
        });
    }
}
