package com.mgiandia.library.view.Author.AddEditAuthor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.platform.ComposeView;
import androidx.lifecycle.ViewModelProvider;

import com.mgiandia.library.R;
import com.mgiandia.library.memorydao.AuthorDAOMemory;
import com.mgiandia.library.ui.composable.ActivitiesKt;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class AddEditAuthorActivity extends AppCompatActivity implements AddEditAuthorView
{
    final String[] firstName = new String[1];
    final String[] lastName = new String[1];

    @Override
    public String getFirstName()
    {
        return firstName[0];
    }

    @Override
    public String getLastName()
    {
        return lastName[0];
    }

    @Override
    public Integer getAttachedAuthorID()
    {
        return this.getIntent().hasExtra("author_id") ? this.getIntent().getExtras().getInt("author_id") : null;
    }

    @Override
    public void setFirstName(String value)
    {
        firstName[0] = value;
    }

    @Override
    public void setLastName(String value)
    {
        lastName[0] = value;
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
     * Εμφανίζει ενα μήνυμα τύπου alert με
     * τίτλο title και μήνυμα message.
     * @param title Ο τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(AddEditAuthorActivity.this)
        .setCancelable(true)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(R.string.ok, null).create().show();
    }

//    /**
//     * Επιστρέφει το πρώτο όνομα του συγγραφέα.
//     * @return Το πρώτο όνομα του συγγραφέα
//     */
//    public String getFirstName()
//    {
//        return ((EditText)findViewById(R.id.edit_text_first_name)).getText().toString().trim();
//    }
//
//    /**
//     * Επιστρέφει το επώνυμο του συγγραφέα.
//     * @return Το επώνυμο του συγγραφέα
//     */
//    public String getLastName()
//    {
//        return ((EditText)findViewById(R.id.edit_text_last_name)).getText().toString().trim();
//    }
//
//    /**
//     * Επιστρέφει το id του συγγραφέα.
//     * @return Το id του συγγραφέα
//     */
//    public Integer getAttachedAuthorID()
//    {
//        return this.getIntent().hasExtra("author_id") ? this.getIntent().getExtras().getInt("author_id") : null;
//    }
//
//    /**
//     * Θέτει το πρώτο όνομα του συγγραφέα.
//     * @param value Το πρώτο όνομα του συγγραφέα
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
        setContentView(R.layout.activity_add_edit_author);

        final AddEditAuthorPresenter presenter = new AddEditAuthorPresenter(this, new AuthorDAOMemory());
        AddEditAuthorViewModel model = new ViewModelProvider(this).get(AddEditAuthorViewModel.class);

        // find the compose view object
        ComposeView composeView = findViewById(R.id.compose_view);
        // set the appropriate composable as content
        ActivitiesKt.showAddEditAuthorView(composeView, model);

        //final String[] firstName = new String[1];
        //final String[] lastName = new String[1];

        // Get what the user writes in the first name field, and save it in one position array --> firstName
        model.getFirstName().observe(this, fName ->
        {
            if (fName != null)
            {
                setFirstName(fName.trim());
                //Log.d("Input", "First Name: " + fName);
            }
        });

        // Get what the user writes in the last name field, and save it in one position array --> lastName
        model.getLastName().observe(this, lName ->
        {
            if (lName != null)
            {
                setLastName(lName.trim());
                //Log.d("Input", "Last Name: " + lName);
            }
        });

        // See if the save button is clicked and save the author
        model.observeClicks(this, buttonTextResId ->
        {
            if (buttonTextResId != null)
            {
                // Get the string resource from the button's text resource ID
                //String buttonText = getString(buttonTextResId);

                // Handle the button click event (e.g., display a Toast)
                //Toast.makeText(this, "Button clicked: " + buttonText, Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "firstName: " + firstName[0] + " lastName: " + lastName[0], Toast.LENGTH_LONG).show();
                presenter.onSaveAuthor();
            }
        });

        /*
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_author);
        final AddEditAuthorPresenter presenter = new AddEditAuthorPresenter(this, new AuthorDAOMemory());

        findViewById(R.id.complete_registration_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                presenter.onSaveAuthor();
            }
        });
        */
    }
}
