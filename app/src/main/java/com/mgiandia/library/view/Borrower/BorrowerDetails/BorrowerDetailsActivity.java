package com.mgiandia.library.view.Borrower.BorrowerDetails;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.platform.ComposeView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.mgiandia.library.R;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.ui.composable.ActivitiesKt;
import com.mgiandia.library.view.AbstractActivityObject;
import com.mgiandia.library.view.Borrower.AddEditBorrower.AddEditBorrowerActivity;
import java.util.Objects;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */
public class BorrowerDetailsActivity extends AbstractActivityObject implements BorrowerDetailsView
{
    /**
     * Ξεκινάει το activity AddEditAuthorActivity
     * με παράμετρο το id του δανειζόμενου.
     * @param borrowerID Το id του δανειζόμενου
     */
    public void startEdit(int borrowerID)
    {
        Intent intent = new Intent(this, AddEditBorrowerActivity.class);
        intent.putExtra("borrower_id", borrowerID);
        startActivityForResult(intent, 2);
    }

    /**
     * Διαγραφή του δανειζόμενου.
     * @param title Τίτλος του εμφανιζόμενου μηνύματος
     * @param message Το περιεχόμενο του εμφανιζόμενου μηνύματος
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
     * Το μήνυμα που εμφανίζεται κατά την
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
     * Επιστρέφει το id του δανειζόμενου.
     * @return Το id του δανειζόμενου
     */
    public int getAttachedBorrowerID()
    {
        return this.getIntent().hasExtra("borrower_id") ? Objects.requireNonNull(this.getIntent().getExtras()).getInt("borrower_id") : -1;
    }

    /**
     * Θέτει το id του δανειζόμενου.
     * @param value Tο id του δανειζόμενου
     */
    public void setID(String value)
    {
        model.setBorrowerNo(value);
    }

    /**
     * Θέτει το πρώτο όνομα του δανειζόμενου.
     * @param value Το πρώτο όνομα του δανειζόμενου
     */
    public void setFirstName(String value)
    {
        model.setFirstName(value);
    }

    /**
     * Θέτει το επώνυμο του δανειζόμενου.
     * @param value Το επώνυμο του δανειζόμενου
     */
    public void setLastName(String value)
    {
        model.setLastName(value);
    }

    /**
     * Θέτει την κατηγορία του δανειζόμενου.
     * @param value Η κατηγορία του δανειζόμενου
     */
    public void setCategory(String value)
    {
        model.setCategory(value);
    }

    /**
     * Θέτει τον αρι8μό του δανειζόμενου.
     * @param value Ο αρι8μός του δανειζόμενου
     */
    public void setPhone(String value)
    {
        model.setPhone(value);
    }

    /**
     * Θέτει τον αριθμό ηλεκτρονικού ταχυδρομείου του δανειζόμενου.
     * @param value Ο αρι8μός του ηλεκτρονικού ταχυδρομείου του δανειζόμενου.
     */
    public void setEmail(String value)
    {
        model.setEmail(value);
    }

    /**
     * Θέτει την χώρα του δανειζόμενου.
     * @param value Η χώρα του δανειζόμενου
     */
    public void setCountry(String value)
    {
        model.setCountry(value);
    }

    /**
     * Θέτει την πόλη του δανειζόμενου.
     * @param value Η πόλη του δανειζόμενου
     */
    public void setAddressCity(String value)
    {
        model.setCity(value);
    }

    /**
     * Θέτει την οδό του δανειζόμενου.
     * @param value Η οδός του δανειζόμενου
     */
    public void setAddressStreet(String value)
    {
        model.setStreet(value);
    }

    /**
     * Θέτει τον αριθμό του δανειζόμενου.
     * @param value Ο αριθμός του δανειζόμενου
     */
    public void setAddressNumber(String value)
    {
        model.setNumber(value);
    }

    /**
     * Θέτει τον ταχυδρομικό κώδικα.
     * @param value Ο ταχυδρομικός κώδικας
     */
    public void setAddressPostalCode(String value)
    {
        model.setPostCode(value);
    }

    /**
     * Θέτει το όνομα της σελίδας.
     * @param value το όνομα της σελίδας
     */
    public void setPageName(String value)
    {
        Objects.requireNonNull(getSupportActionBar()).setTitle(value);
    }

    /**
     * Εμφανίζει ένα Toast.
     * @param value Το περιεχόμενο που θα εμφανιστεί
     */
    public void showToast(String value)
    {
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }


    BorrowerDetailsViewModel model;
    BorrowerDetailsPresenter presenter;

    /**
     * Δημιουργεί το layout και αρχικοποιεί
     * το activity.
     * @param savedInstanceState το Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrower_details);

        model = new ViewModelProvider((ViewModelStoreOwner) this).get(BorrowerDetailsViewModel.class);
        presenter = model.getPresenter(this);

        ComposeView composeView = findViewById(R.id.compose_view);
        ActivitiesKt.showBorrowerDetailsView(composeView, model);

        int borrowerID = getAttachedBorrowerID();
        Borrower borrower = model.findBorrower(borrowerID);

        if (borrower != null)
        {
            setID("#" + borrower.getBorrowerNo());
            setFirstName(borrower.getFirstName());
            setLastName(borrower.getLastName());
            setCategory(borrower.getCategory().getDescription());
            setPhone(borrower.getTelephone().getTelephoneNumber());
            setEmail(borrower.getEmail().toString());
            setCountry(borrower.getAddress().getCountry());
            setAddressCity(borrower.getAddress().getCity());
            setAddressStreet(borrower.getAddress().getStreet());
            setAddressNumber(borrower.getAddress().getNumber());
            setAddressPostalCode(borrower.getAddress().getZipCode().getCode());
        }

        model.observeClicks(this, buttonTextResId ->
        {
            if (buttonTextResId != null)
            {
                if (buttonTextResId.equals(R.string.delete_user))
                {
                    presenter.onStartDeleteButtonClick();
                }
                else if (buttonTextResId.equals(R.string.edit_user))
                {
                    presenter.onStartEditButtonClick();
                }
            }
        });
    }

    /**
     * Ξανα δημιουργεί το activity με νεό instance.
     * Σε περίπτωση που ο ζητούμενος κωδικός είναι
     * 2 και ο κωδικός του αποτελέσματος είναι ok,
     * εμφανίζει ένα toast.
     * @param requestCode Ο ζητούμενος κωδικός
     * @param resultCode Ο κωδικός του αποτελέσματος
     * @param data Το intent
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
