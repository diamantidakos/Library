package com.mgiandia.library.view.Author.AddEditAuthor;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.platform.ComposeView;
import androidx.lifecycle.ViewModelProvider;
import com.mgiandia.library.R;
import com.mgiandia.library.domain.Author;
import com.mgiandia.library.memorydao.AuthorDAOMemory;
import com.mgiandia.library.ui.composable.ActivitiesKt;

import java.util.Objects;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */
public class AddEditAuthorActivity extends AppCompatActivity implements AddEditAuthorView
{
    private String firstName, lastName;

    @Override
    public String getFirstName()
    {
        return firstName;
    }

    @Override
    public String getLastName()
    {
        return lastName;
    }

    @Override
    public Integer getAttachedAuthorID()
    {
        return this.getIntent().hasExtra("author_id") ? Objects.requireNonNull(this.getIntent().getExtras()).getInt("author_id") : -1;
    }

    @Override
    public void setFirstName(String value)
    {
        firstName = value;
    }

    @Override
    public void setLastName(String value)
    {
        lastName = value;
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


    /**
     * Δημιουργεί to layout και αρχικοποιεί
     * το activity.
     * @param savedInstanceState το Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_author);

        AddEditAuthorViewModel model = new ViewModelProvider(this).get(AddEditAuthorViewModel.class);
        final AddEditAuthorPresenter presenter = model.getPresenter(this);

        // find the compose view object
        ComposeView composeView = findViewById(R.id.compose_view);
        // set the appropriate composable as content
        ActivitiesKt.showAddEditAuthorView(composeView, model);

        int authorID = getAttachedAuthorID();
        Author author = model.findAuthor(authorID);
        if (author != null)
        {
            model.setFirstName(author.getFirstName());
            model.setLastName(author.getLastName());
        }

        // Get what the user writes in the first name field, and save it in a variable --> firstName, removing the spaces before and after the word (trim())
        model.getFirstName().observe(this, fName ->
        {
            if (fName != null)
            {
                setFirstName(fName.trim());
            }
        });

        // Get what the user writes in the last name field, and save it in a variable --> lastName, removing the spaces before and after the word (trim())
        model.getLastName().observe(this, lName ->
        {
            if (lName != null)
            {
                setLastName(lName.trim());
            }
        });

        // if the save button is clicked, save the author
        model.observeClicks(this, buttonTextResId ->
        {
            if (buttonTextResId != null && getFirstName() != null && getLastName() != null)
            {
                presenter.onSaveAuthor();
            }
        });
    }
}
