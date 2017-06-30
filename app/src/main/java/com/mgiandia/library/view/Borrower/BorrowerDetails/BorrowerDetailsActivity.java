package com.mgiandia.library.view.Borrower.BorrowerDetails;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mgiandia.library.R;
import com.mgiandia.library.memorydao.BorrowerDAOMemory;
import com.mgiandia.library.memorydao.LoanDAOMemory;
import com.mgiandia.library.view.Borrower.AddEditBorrower.AddEditBorrowerActivity;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class BorrowerDetailsActivity extends AppCompatActivity implements BorrowerDetailsView
{
    BorrowerDetailsPresenter presenter;

    /**
     * Ξεκινάει το activity AddEditAuthorActivity
     * με παράμετρο το id του δανιζόμενου.
     * @param borrowerID Το id του δανιζόμενου
     */
    public void startEdit(int borrowerID)
    {
        Intent intent = new Intent(this, AddEditBorrowerActivity.class);
        intent.putExtra("borrower_id", borrowerID);
        startActivityForResult(intent, 2);
    }

    /**
     * Διαγράφη του δανιζόμενου.
     * @param title Τίτλος του εμαφανιζόμενου μηνύματος
     * @param message Το περιεχόμενο του εμαφανιζόμενου μηνύματος
     */
    public void startDelete(String title, String message)
    {
        new AlertDialog.Builder(BorrowerDetailsActivity.this).setCancelable(true).setTitle(title).setMessage(message)
                .setPositiveButton(R.string.yes_delete, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        presenter.onDoDeleteAndFinish();
                    }
                })
                .setNegativeButton(R.string.cancel, null).create().show();
    }

    /**
     * Το μήνυμα που εμφανίζεται κατα την
     * ολοκλήρωση της διαγραφής.
     * @param message Το περιεχόμενο του μηνύματος
     */
    public void doDeleteAndFinish(String message)
    {
        Intent intent = new Intent();
        intent.putExtra("message_to_toast", message);
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * Επιστρέφει το id του δανιζόμενου.
     * @return Το id του δανιζόμενου
     */
    public int getAttachedBorrowerID()
    {
        return this.getIntent().hasExtra("borrower_id") ? this.getIntent().getExtras().getInt("borrower_id") : null;
    }

    /**
     * Θέτει το id του δανιζόμενου.
     * @param value Tο id του δανιζόμενου.
     */
    public void setID(String value)
    {
        ((TextView)findViewById(R.id.text_user_id)).setText(value);
    }

    /**
     * Θέτει το πρώτο όνομα του δανιζόμενου.
     * @param value Το πρώτο όνομα του δανιζόμενου
     */
    public void setFirstName(String value)
    {
        ((TextView)findViewById(R.id.text_first_name)).setText(value);
    }

    /**
     * Θέτει το επώνυμο του δανιζόμενου.
     * @param value Το επώνυμο του δανιζόμενου
     */
    public void setLastName(String value)
    {
        ((TextView)findViewById(R.id.text_last_name)).setText(value);
    }

    /**
     * Θέτει την κατηγορία του δανιζόμενου.
     * @param value Η κατηγορία του δανιζόμενου
     */
    public void setCategory(String value)
    {
        ((TextView)findViewById(R.id.text_category)).setText(value);
    }

    /**
     * Θέτει τον αρι8μό του δανιζόμενου.
     * @param value Ο αρι8μός του δανιζόμενου
     */
    public void setPhone(String value)
    {
        ((TextView)findViewById(R.id.text_telephone)).setText(value);
    }

    /**
     * Θέτει τον αριθμό ηλελτρονικό ταχυδρομίου του δανιζόμενου.
     * @param value Ο αρι8μός του ηλελτρονικό ταχυδρομίου του δανιζόμενου.
     */
    public void setEmail(String value)
    {
        ((TextView)findViewById(R.id.text_email)).setText(value);
    }

    /**
     * Θέτει την χώρα του δανιζόμενου.
     * @param value Η χώρα του δανιζόμενου
     */
    public void setCountry(String value)
    {
        ((TextView)findViewById(R.id.text_country)).setText(value);
    }

    /**
     * Θέτει την πόλη του δανιζόμενου.
     * @param value Η πόλη του δανιζόμενου
     */
    public void setAddressCity(String value)
    {
        ((TextView)findViewById(R.id.text_city)).setText(value);
    }

    /**
     * Θέτει την οδό του δανιζόμενου.
     * @param value Η οδός του δανιζόμενου
     */
    public void setAddressStreet(String value)
    {
        ((TextView)findViewById(R.id.text_street)).setText(value);
    }

    /**
     * Θέτει τον αριθμό του δανιζόμενου.
     * @param value Ο αριθμός του δανιζόμενου
     */
    public void setAddressNumber(String value)
    {
        ((TextView)findViewById(R.id.text_number)).setText(value);
    }

    /**
     * Θέτει τον ταχυδρομικό κώδικα.
     * @param value Ο ταχυδρομικός κώδικας
     */
    public void setAddressPostalCode(String value)
    {
        ((TextView)findViewById(R.id.text_zip)).setText(value);
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
     * Εμφανίζει ένα μήνυμα με περιεχόμενο
     * value.
     * @param value To περιεχόμενο που θα εμφανιστεί
     */
    public void showToast(String value)
    {
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
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
        setContentView(R.layout.activity_borrower_details);
        presenter = new BorrowerDetailsPresenter(this, new BorrowerDAOMemory(), new LoanDAOMemory());

        findViewById(R.id.edit_user_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                presenter.onStartEditButtonClick();
            }
        });

        findViewById(R.id.delete_user_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                presenter.onStartDeleteButtonClick();
            }
        });
    }

    /**
     * Ξανα δημιουργεί το activity με νεό instance.
     * Σε περίπτωση που ο ζητούμενος κώδικος είναι
     * 2 και ο κωδικός του αποτελέσματος ειναι ok,
     * εμφανίζει ενα toast.
     * @param requestCode Ο ζητούμενος κώδικος
     * @param resultCode Ο κωδικός του αποτελέσματος
     * @param data Tο intent
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2 && resultCode == Activity.RESULT_OK)
        {
            recreate();
            presenter.onShowToast(data.getStringExtra("message_to_toast"));
        }
        else if(requestCode == 100)
            recreate();
    }
}
