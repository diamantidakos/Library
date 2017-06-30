package com.mgiandia.library.view.Author.AuthorDetails;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mgiandia.library.R;
import com.mgiandia.library.memorydao.AuthorDAOMemory;
import com.mgiandia.library.view.Author.AddEditAuthor.AddEditAuthorActivity;
import com.mgiandia.library.view.Book.ManageBooks.ManageBooksActivity;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class AuthorDetailsActivity extends AppCompatActivity implements AuthorDetailsView
{
    AuthorDetailsPresenter presenter;

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
        return this.getIntent().hasExtra("author_id") ? this.getIntent().getExtras().getInt("author_id") : null;
    }

    /**
     * Θέτει το id.
     * @param value To id value
     */
    public void setID(String value)
    {
        ((TextView)findViewById(R.id.text_user_id)).setText(value);
    }

    /**
     * Θέτει το πρώτο όνομα του συγγραφέα.
     * @param value Το πρώτο όνομα του συγγραφέα
     */
    public void setFirstName(String value)
    {
        ((TextView)findViewById(R.id.text_first_name)).setText(value);
    }

    /**
     * Θέτει το επώνυμο του συγγραφέα.
     * @param value Το επώνυμο του συγγραφέα
     */
    public void setLastName(String value)
    {
        ((TextView)findViewById(R.id.text_last_name)).setText(value);
    }

    /**
     * Θέτει τα βιβλία.
     * @param value Το βιβλίο.
     */
    public void setBooksWritten(String value)
    {
        ((TextView)findViewById(R.id.books_published_text)).setText(value);
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
     * @param savedInstanceState Το αποθηκευμένο instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_details);
        presenter = new AuthorDetailsPresenter(this, new AuthorDAOMemory());

        findViewById(R.id.edit_user_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                presenter.onStartEditButtonClick();
            }
        });

        findViewById(R.id.display_books_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                presenter.onStartShowBooksButtonClick();
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
