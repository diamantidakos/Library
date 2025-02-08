package com.mgiandia.library.view.Book.AddEditBook;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.platform.ComposeView;
import androidx.lifecycle.ViewModelProvider;
import com.mgiandia.library.R;
import com.mgiandia.library.memorydao.AuthorDAOMemory;
import com.mgiandia.library.memorydao.BookDAOMemory;
import com.mgiandia.library.ui.composable.ActivitiesKt;
import com.mgiandia.library.view.Author.AddEditAuthor.AddEditAuthorPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */
public class AddEditBookActivity extends AppCompatActivity implements AddEditBookView
{
//    /**
//     * Επιστρέφει τον τίτλο του βιβλίου.
//     * @return Ο τίτλος του βιβλίου
//     */
//    public String getBookTitle()
//    {
//        return ((EditText)findViewById(R.id.edit_text_book_title)).getText().toString().trim();
//    }
//
//    /**
//     * Επιστρέφει το ISBN του βιβλίου.
//     * @return Το ISBN του βιβλίου
//     */
//    public String getISBN()
//    {
//        return ((EditText)findViewById(R.id.edit_text_isbn)).getText().toString().trim();
//    }
//
//    /**
//     * Επιστρέφει το έτος δημοσίευσης του βιβλίου.
//     * @return Το έτος δημοσίευσης του βιβλίου
//     */
//    public String getPublication()
//    {
//        return ((EditText)findViewById(R.id.edit_text_publication)).getText().toString().trim();
//    }
//
//    /**
//     * Επιστρέφει το έτος συγγραφής του βιβλίου.
//     * @return Το έτος συγγραφής του βιβλίου
//     */
//    public String getYear()
//    {
//        return ((EditText)findViewById(R.id.edit_text_publicationyear)).getText().toString().trim();
//    }
//
//    /**
//     * Επιστρέφει την θέση του συγγραφέα.
//     * @return Η θέση του συγγραφέα
//     */
//    public Integer getPublisherPosition()
//    {
//        int pos = ((Spinner)findViewById(R.id.edit_text_publisher)).getSelectedItemPosition();
//        return pos == 0 ? null : pos;
//    }
//
//    /**
//     * Επιστρέφει τις θέσεις των βιβλίων του συγγραφέα.
//     * @return Οι θέσεις των βιβλίων του συγγραφέα
//     */
//    public List<Integer> getAuthorPositions()
//    {
//        List<Integer> positions = new ArrayList<>();
//        boolean[] indexes = ((MultiSelectSpinner)findViewById(R.id.edit_text_authors)).getItemsIndexes();
//
//        for(int i = 0; i < indexes.length; i++)
//            if(indexes[i])
//                positions.add(i+1);
//
//        return positions;
//    }
//
//    /**
//     * Επιστρέφει το id του βιβλίου.
//     * @return Το id του βιβλίου
//     */
//    public Integer getAttachedBookID()
//    {
//        return this.getIntent().hasExtra("book_id") ? this.getIntent().getExtras().getInt("book_id") : null;
//    }
//
//    /**
//     * Θέτει τον τίτλο του βιβλίου
//     * @param value Ο τίτλος του βιβλίου
//     */
//    public void setBookTitle(String value)
//    {
//        ((EditText)findViewById(R.id.edit_text_book_title)).setText(value);
//    }
//
//    /**
//     * Θέτει την θέση του συγγραφέα.
//     * @param value Η θέση του συγγραφέα.
//     */
//    public void setPublisherPosition(Integer value)
//    {
//        ((Spinner)findViewById(R.id.edit_text_publisher)).setSelection(value);
//    }
//
//    /**
//     * Θέτει το ISBN του βιβλίου
//     * @param value Το ISBN του βιβλίου
//     */
//    public void setISBN(String value)
//    {
//        ((EditText)findViewById(R.id.edit_text_isbn)).setText(value);
//    }
//
//    /**
//     * Θέτει την ημερομηνία έκδοσης του βιβλίου
//     * @param value Η ημερομηνία έκδοσης του βιβλίου
//     */
//    public void setPublication(String value)
//    {
//        ((EditText)findViewById(R.id.edit_text_publication)).setText(value);
//    }
//
//    /**
//     * Θέτει το έτος του βιβλίου
//     * @param value Η το έτος του βιβλίου
//     */
//    public void setYear(String value)
//    {
//        ((EditText)findViewById(R.id.edit_text_publicationyear)).setText(value);
//    }
//
//    /**
//     * Θέτει τις θέσεις των συγγραφέων
//     * @param value Οι θέσεις των συγγραφέων.
//     */
//    public void setAuthorPositions(List<Integer> value)
//    {
//        for(int i = 0; i < value.size(); i++)
//            value.set(i, value.get(i)-1);
//
//        ((MultiSelectSpinner)findViewById(R.id.edit_text_authors)).setSelectedItems(value);
//    }
//
//    /**
//     * Θέτει το όνομα της σελίδας.
//     * @param value το όνομα της σελίδας
//     */
//    public void setPageName(String value)
//    {
//        getSupportActionBar().setTitle(value);
//    }
//
//    /**
//     * Θέτει την λίστα των συγγραφέων.
//     * @param names Τα ονόματα των συγγραφέων
//     */
//    public void setAuthorList(List<String> names)
//    {
//        ((MultiSelectSpinner) findViewById(R.id.edit_text_authors)).setItems(names);
//        setAuthorPositions(new ArrayList<Integer>());
//    }
//
//    /**
//     * Θέτει την λίστα των συγγραφέων με ονόματα
//     * names και με όνομα προεπιλογής defaultName
//     * @param names Η λίστα των ονομάτων
//     * @param defaultName Το προκαθορισμένο όνομα
//     */
//    public void setPublisherList(List<String> names, String defaultName)
//    {
//        names.add(0, defaultName);
//
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, names);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        ((Spinner) findViewById(R.id.edit_text_publisher)).setAdapter(adapter);
//    }


    AddEditBookViewModel model;
    String bookTitle, publisher, ISBN, publication, publicationYear;
    ArrayList<String> authors = new ArrayList<>();
    List<Integer> authorsIndexes = new ArrayList<>();

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
        model.getSelectedAuthorsPositions().observe(this, indexes ->
        {
            if (indexes != null)
            {
                authorsIndexes = indexes;
            }
        });

        return authorsIndexes;
    }

    @Override
    public Integer getPublisherPosition()
    {
        return model.getPublisherPosition().getValue();
    }

    @Override
    public Integer getAttachedBookID()
    {
        return 0;
    }

    @Override
    public void setPublisherPosition(Integer value) {}

    @Override
    public void setAuthorPositions(List<Integer> value) {}

    @Override
    public void setPageName(String value) {}

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
        return (getBookTitle() != null && getPublisher() != null && getISBN() != null && getPublication() != null && getYear() != null && getAuthorList() != null);
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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_edit_book);

        //final AddEditBookPresenter presenter = new AddEditBookPresenter((AddEditBookView) this, new BookDAOMemory());
        final AddEditBookPresenter presenter = new AddEditBookPresenter(this);
        model = new ViewModelProvider(this).get(AddEditBookViewModel.class);

        ComposeView composeView = findViewById(R.id.compose_view);
        ActivitiesKt.drawEditBookPage(composeView, model);

        model.getTitle().observe(this, bName ->
        {
            if (bName != null)
            {
                setBookTitle(bName.trim());
            }
        });

        model.getPublisher().observe(this, i ->
        {
            if (i != null)
            {
                setPublisher(i.trim());
            }
        });

        model.getISBN().observe(this, i ->
        {
            if (i != null)
            {
                setISBN(i.trim());
            }
        });

        model.getPublication().observe(this, i ->
        {
            if (i != null)
            {
                setPublication(i.trim());
            }
        });

        model.getPublicationYear().observe(this, i ->
        {
            if (i != null)
            {
                setYear(i.trim());
            }
        });

        model.getAuthors().observe(this, i ->
        {
            if (i != null)
            {
                setAuthorList(i);
            }
        });

        // if the save button is clicked, save the book
        model.observeClicks(this, buttonTextResId ->
        {
            if (buttonTextResId != null && validFields())
            {
                //Toast.makeText(AddEditBookActivity.this, getPublisher(), Toast.LENGTH_SHORT).show(); // gia debbugging
                presenter.onSaveBook();
            }
        });


        // Create the observer which updates the UI.
        /*
        final Observer<Integer> clickObserver = buttonLabelResId -> {
            if (buttonLabelResId == R.string.complete_registration){
                presenter.onSaveBook();
            }
        };
        model.observeClicks(this, clickObserver);
        */


        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        */


        //final AddEditBookPresenter presenter = new AddEditBookPresenter(this, new BookDAOMemory(), new PublisherDAOMemory(), new AuthorDAOMemory(), new ItemDAOMemory());
        /*
        findViewById(R.id.complete_registration_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onSaveBook();
            }
        });
        */
    }
}
