package com.mgiandia.library.view.Book.AddEditBook;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.platform.ComposeView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.mgiandia.library.R;
import com.mgiandia.library.domain.Author;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.ui.composable.ActivitiesKt;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */
public class AddEditBookActivity extends AppCompatActivity implements AddEditBookView
{
    private AddEditBookViewModel model;
    private String bookTitle, publisher, ISBN, publication, publicationYear;
    private ArrayList<String> authors = new ArrayList<>();
    private List<Integer> authorsIndexes = new ArrayList<>();
    private int publisherPosition;

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

    @Override
    public void setBookTitle(String value)
    {
        bookTitle = value;
    }

    @Override
    public String getBookTitle()
    {
        return bookTitle;
    }

    @Override
    public void setPublisher(String value)
    {
        publisher = value;
    }

    @Override
    public String getPublisher()
    {
        return publisher;
    }

    @Override
    public void setISBN(String value)
    {
        ISBN = value;
    }

    @Override
    public String getISBN()
    {
        return ISBN;
    }

    @Override
    public void setPublication(String value)
    {
        publication = value;
    }

    @Override
    public String getPublication()
    {
        return publication;
    }

    @Override
    public void setYear(String value)
    {
        publicationYear = value;
    }

    @Override
    public String getYear()
    {
        return publicationYear;
    }

    @Override
    public void setAuthorList(List<String> names)
    {
        authors = (ArrayList<String>) names;
    }

    @Override
    public void setPublisherList(List<String> names, String defaultName) {}

    public List<String> getAuthorList()
    {
        return authors;
    }

    @Override
    public List<Integer> getAuthorPositions()
    {
        return authorsIndexes;
    }

    @Override
    public Integer getPublisherPosition()
    {
        return publisherPosition;
    }

    @Override
    public Integer getAttachedBookID()
    {
        return this.getIntent().hasExtra("book_id") ? Objects.requireNonNull(this.getIntent().getExtras()).getInt("book_id") : -1;
    }

    @Override
    public void setPublisherPosition(Integer value)
    {
        publisherPosition = value;
    }

    @Override
    public void setAuthorPositions(List<Integer> value)
    {
        authorsIndexes = value;
    }

    @Override
    public void setPageName(String value)
    {
        Objects.requireNonNull(getSupportActionBar()).setTitle(value);
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
     * @return true if user filled all fields, false otherwise
     */
    private boolean validFields()
    {
        return (getBookTitle() != null && getPublisher() != null && getPublisherPosition() != null && getISBN() != null && getPublication() != null && getYear() != null && getAuthorList() != null);
    }

    private void getValuesFromViewModel()
    {
        model.getTitle().observe((LifecycleOwner) this, bName ->
        {
            if (bName != null)
            {
                setBookTitle(bName.trim());
            }
        });

        model.getPublisher().observe((LifecycleOwner) this, i ->
        {
            if (i != null)
            {
                setPublisher(i.trim());
            }
        });

        model.getPublisherPosition().observe((LifecycleOwner) this, i ->
        {
            if (i != null)
            {
                setPublisherPosition(i);
            }
        });

        model.getISBN().observe((LifecycleOwner) this, i ->
        {
            if (i != null)
            {
                setISBN(i.trim());
            }
        });

        model.getPublication().observe((LifecycleOwner) this, i ->
        {
            if (i != null)
            {
                setPublication(i.trim());
            }
        });

        model.getPublicationYear().observe((LifecycleOwner) this, i ->
        {
            if (i != null)
            {
                setYear(i.trim());
            }
        });

        model.getAuthors().observe((LifecycleOwner) this, i ->
        {
            if (i != null)
            {
                setAuthorList(i);
            }
        });

        model.getSelectedAuthorsPositions().observe((LifecycleOwner) this, indexes ->
        {
            if (indexes != null)
            {
                setAuthorPositions(indexes);
            }
        });
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

        model = new ViewModelProvider((ViewModelStoreOwner) this).get(AddEditBookViewModel.class);
        final AddEditBookPresenter presenter = model.getPresenter(this);

        ComposeView composeView = findViewById(R.id.compose_view);
        ActivitiesKt.drawEditBookPage(composeView, model);

        int bookID = getAttachedBookID();
        Book book = model.findBook(bookID);
        if (book != null)
        {
            model.setTitle(book.getTitle());
            model.setPublisher(book.getPublisher().getName());
            model.setISBN(book.getIsbn().toString());
            model.setPublication(book.getPublication());
            model.setPublicationYear(Integer.toString(book.getPublicationYear()));

            ArrayList<String> authors = new ArrayList<>();
            for (Author a : book.getAuthors())
            {
                authors.add(a.getFirstName() + " " + a.getLastName());
            }
            model.setAuthors(authors);
        }

        getValuesFromViewModel();

        // if the save button is clicked, save the book
        model.observeClicks(this, buttonTextResId ->
        {
            if (buttonTextResId != null && validFields() && buttonTextResId.equals(R.string.complete_registration))
            {
                presenter.onSaveBook();
            }
        });
    }
}
