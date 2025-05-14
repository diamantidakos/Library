package com.mgiandia.library.view.Author.AuthorDetails;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.platform.ComposeView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.mgiandia.library.R;
import com.mgiandia.library.domain.Author;
import com.mgiandia.library.ui.composable.ActivitiesKt;
import com.mgiandia.library.view.AbstractActivityObject;
import com.mgiandia.library.view.Author.AddEditAuthor.AddEditAuthorActivity;
import com.mgiandia.library.view.Book.ManageBooks.ManageBooksActivity;

import java.util.Objects;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */
public class AuthorDetailsActivity extends AbstractActivityObject implements AuthorDetailsView
{
    private String firstName, lastName, booksWritten, ID;

    /**
     * Ξεκινάει το activity ManageBooksActivity
     * με παράμετρο το id του συγγραφέα.
     * @param authorID Το id του συγγραφέα
     */
    public void startShowBooks(int authorID)
    {
        Intent intent = new Intent(this, ManageBooksActivity.class);
        intent.putExtra("author_id", authorID);
        startActivityForResult(intent, 100);
    }

    /**
     * Ξεκινάει το activity AddEditAuthorActivity
     * με παράμετρο το id του συγγραφέα.
     * @param authorID Το id του συγγραφέα
     */
    public void startEdit(int authorID)
    {
        Intent intent = new Intent(this, AddEditAuthorActivity.class);
        intent.putExtra("author_id", authorID);
        startActivityForResult(intent, 2);
    }

    /**
     * Επιστρέφει το id του συγγραφέα.
     * @return Το id του συγγραφέα
     */
    public int getAttachedAuthorID()
    {
        return this.getIntent().hasExtra("author_id") ? Objects.requireNonNull(this.getIntent().getExtras()).getInt("author_id") : -1;
    }

    /**
     * Θέτει το id.
     * @param value To id value
     */
    public void setID(String value)
    {
        ID = value;
    }

    public String getID()
    {
        return ID;
    }

    /**
     * Θέτει το πρώτο όνομα του συγγραφέα.
     * @param value Το πρώτο όνομα του συγγραφέα
     */
    public void setFirstName(String value)
    {
        firstName = value;
    }

    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Θέτει το επώνυμο του συγγραφέα.
     * @param value Το επώνυμο του συγγραφέα
     */
    public void setLastName(String value)
    {
        lastName = value;
    }

    public String getLastName()
    {
        return lastName;
    }

    /**
     * Θέτει τα βιβλία.
     * @param value Το βιβλίο.
     */
    public void setBooksWritten(String value)
    {
        booksWritten = value;
    }

    public String getBooksWritten()
    {
        return booksWritten;
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
     * @param value To περιεχόμενο που θα εμφανιστεί
     */
    public void showToast(String value)
    {
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }


    private AuthorDetailsPresenter presenter;

    /**
     * Δημιουργεί to layout και αρχικοποιεί
     * το activity.
     * @param savedInstanceState Το αποθηκευμένο instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_details);

        AuthorDetailsViewModel model = new ViewModelProvider((ViewModelStoreOwner) this).get(AuthorDetailsViewModel.class);
        presenter = model.getPresenter(this);

        ComposeView composeView = findViewById(R.id.compose_view);
        ActivitiesKt.showAuthorDetailsView(composeView, model);

        int authorID = getAttachedAuthorID();
        Author author = model.findAuthor(authorID);
        if (author != null)
        {
            model.setAuthorID("#" + authorID);
            model.setName(author.getFirstName());
            model.setSurname(author.getLastName());
            model.setBooksNumber(author.getBooks().size());

            model.observeClicks(this, buttonTextResId ->
            {
                if (buttonTextResId != null)
                {
                    if (buttonTextResId.equals(R.string.edit_user))
                    {
                        presenter.onStartEditButtonClick();
                    }
                    else if (buttonTextResId.equals(R.string.show_books))
                    {
                        presenter.onStartShowBooksButtonClick();
                    }
                }
            });
        }
    }

    /**
     * Ξανά δημιουργεί το activity με νεό instance.
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
    }
}
