package com.mgiandia.library.view.Book.BookDetails;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.platform.ComposeView;
import androidx.lifecycle.ViewModelProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import com.mgiandia.library.R;
import com.mgiandia.library.domain.Author;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.Item;
import com.mgiandia.library.ui.composable.ActivitiesKt;
import com.mgiandia.library.view.Book.AddEditBook.AddEditBookActivity;
import com.mgiandia.library.view.Book.AddEditBook.AddEditBookViewModel;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class BookDetailsActivity extends AppCompatActivity implements BookDetailsView
{

//    /**
//     * Ξεκινάει να τροποποιεί το βιβλίο με
//     * id bookID
//     * @param bookID Το id του βιβλίου που θα τροποποιηθεί
//     */
//    public void startEdit(int bookID)
//    {
//        Intent intent = new Intent(this, AddEditBookActivity.class);
//        intent.putExtra("book_id", bookID);
//        startActivityForResult(intent, 2);
//    }
//
//    /**
//     * Επιστρέφει το id του βιβλίου.
//     * @return Το id του βιβλίου
//     */
//    public int getAttachedBookID()
//    {
//        return this.getIntent().hasExtra("book_id") ? this.getIntent().getExtras().getInt("book_id") : null;
//    }
//
//    /**
//     * Θέτει το id του βιβλίου.
//     * @param value Το id του βιβλίου.
//     */
//    public void setID(String value)
//    {
//        ((TextView)findViewById(R.id.text_book_id)).setText(value);
//    }
//
//    /**
//     * Θέτει τον τίτλο του βιβλίου.
//     * @param value Ο τίτλος του βιβλίου.
//     */
//    public void setBookTitle(String value)
//    {
//        ((TextView)findViewById(R.id.text_book_title)).setText(value);
//    }
//
//    /**
//     * Θέτει τον εκδότη του βιβλίου.
//     * @param value Ο εκδότης του βιβλίου.
//     */
//    public void setPublisher(String value)
//    {
//        ((TextView)findViewById(R.id.text_book_publisher)).setText(value);
//    }
//
//    /**
//     * Θέτει το ISBN του βιβλίου.
//     * @param value Το ISBN του βιβλίου.
//     */
//    public void setISBN(String value)
//    {
//        ((TextView)findViewById(R.id.text_book_isbn)).setText(value);
//    }
//
//    /**
//     * Θέτει το έτος δημοσίευσης του βιβλίου.
//     * @param value Το έτος δημοσίευσης του βιβλίου
//     */
//    public void setPublication(String value)
//    {
//        ((TextView)findViewById(R.id.text_book_publication)).setText(value);
//    }
//
//    /**
//     * Θέτει το έτος δημοσίευσης του βιβλίου.
//     * @param value Το έτος δημοσίευσης του βιβλίου
//     */
//    public void setYear(String value)
//    {
//        ((TextView)findViewById(R.id.text_book_publicationyear)).setText(value);
//    }
//
//    /**
//     * Θέτει τον αριθμό του βιβλίου.
//     * @param value Ο αριθμός του βιβλίου
//     */
//    public void setItemsNo(String value)
//    {
//        ((TextView)findViewById(R.id.text_book_copies)).setText(value);
//    }
//
//    /**
//     * Θέτει τους συγγραφείς του βιβλίου.
//     * @param author_ids Τα id των συγγραφέων
//     * @param author_names Τα ονόματα των συγγραφέων
//     */
//    public void setAuthors(List<String> author_ids, List<String> author_names)
//    {
//        TableLayout table = (TableLayout)findViewById(R.id.author_parent_layout);
//        table.removeAllViews();
//
//        for(int i = 0; i < author_ids.size(); i++)
//            table.addView(add_author_to_table(author_ids.get(i), author_names.get(i)));
//    }
//
//    /**
//     * Θέτει το όνομα της σελίδας.
//     * @param value Το όνομα της σελίδας
//     */
//    public void setPageName(String value)
//    {
//        getSupportActionBar().setTitle(value);
//    }
//
//    /**
//     * Εμφανίζει ένα Toast.
//     * @param value To περιεχόμενο που θα εμφανιστεί
//     */
//    public void showToast(String value)
//    {
//        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
//    }

    String bookID, bookTitle, publisher, ISBN, publication, year;
    List<String> authors, selectedAuthors;
    int publisherPosition, copiesNum;
    Set<Item> copies;

    @Override
    public int getAttachedBookID()
    {
        return this.getIntent().hasExtra("book_id") ? Objects.requireNonNull(this.getIntent().getExtras()).getInt("book_id") : -1;
    }

    @Override
    public void setID(String value)
    {
        bookID = value;
    }

    @Override
    public void setBookTitle(String value)
    {
        bookTitle = value;
    }

    @Override
    public void setPublisher(String value)
    {
        publisher = value;
    }

    @Override
    public void setISBN(String value)
    {
        ISBN = value;
    }

    @Override
    public void setPublication(String value)
    {
        publication = value;
    }

    @Override
    public void setYear(String value)
    {
        year = value;
    }

    @Override
    public void setItemsNo(String value)
    {
    }

    @Override
    public void setAuthors(List<String> author_ids, List<String> author_names)
    {
        authors = author_names;
        selectedAuthors = author_ids;
    }

    @Override
    public void setPageName(String value)
    {
        Objects.requireNonNull(getSupportActionBar()).setTitle(value);
    }

    @Override
    public void startEdit(int bookID)
    {
        Intent intent = new Intent(this, AddEditBookActivity.class);
        intent.putExtra("book_id", bookID);
        startActivityForResult(intent, 2);
    }

    @Override
    public void showToast(String value)
    {
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }


    BookDetailsPresenter presenter;

    /**
     * Δημιουργεί to layout και αρχικοποιεί
     * το activity.
     * @param savedInstanceState το Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        BookDetailsViewModel model = new ViewModelProvider(this).get(BookDetailsViewModel.class);
        presenter = model.getPresenter(this);

        ComposeView composeView = findViewById(R.id.compose_view);
        ActivitiesKt.showBookDetailsView(composeView, model);

        int bookID = getAttachedBookID();
        Book book = model.findBook(bookID);

        if (book != null)
        {
            model.setBookID("#" + bookID);
            model.setTitle(book.getTitle());
            model.setISBN(book.getIsbn().toString());
            model.setPublisher(book.getPublisher().getName());
            model.setPublication(book.getPublication());
            model.setPublicationYear(Integer.toString(book.getPublicationYear()));
            model.setCopies(book.getItems());

            ArrayList<String> authorsStr = new ArrayList<>();
            Set<Author> authors = book.getAuthors();
            for (Author a : authors)
            {
                authorsStr.add(a.getFirstName() + " " + a.getLastName());
            }
            model.setAuthors(authorsStr);

            model.observeClicks(this, buttonTextResId ->
            {
                if (buttonTextResId != null)
                {
                    if (buttonTextResId.equals(R.string.edit_user))
                    {
                        presenter.onStartEditButtonClick();
                    }
                }
            });
        }
    }

    /**
     * Ξανά δημιουργεί το activity με νέο instance.
     * Σε περίπτωση που ο ζητούμενος κωδικός είναι
     * 2 και ο κωδικός του αποτελέσματος είναι ok,
     * εμφανίζει ένα toast.
     * @param requestCode Ο ζητούμενος κωδικός
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

    /**
     * Δημιουργεί το κείμενο το οποίο εμφανίζεται στις λεπτομέρειες
     * του βιβλίου.
     * @param text Το κείμενο το οποίο εμφανίζεται
     * @param weight Το πόσο έντονο θα είναι το κείμενο
     * @param color_resource Το χρώμα το οποίο θα είναι
     * @return Το textview
     */
    @SuppressLint("RtlHardcoded")
    private TextView create_plain_text_view(String text, double weight, int color_resource)
    {
        TextView tx = new TextView(this);
        tx.setText(text);
        tx.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT, (float)weight));
        tx.setGravity(Gravity.LEFT);
        tx.setMaxLines(1);
        tx.setTextColor(getResources().getColor(color_resource));
        tx.setTextSize(16);
        tx.setPadding(12, 12, 12, 12);

        return tx;
    }

    /**
     * Προσθέτει συγγραφέα στο βιβλίο
     * @param title Ο αριθμός μπροστά από τον συγγραφέα
     * @param details Το όνομα του συγγραφέα
     * @return Το tablerow
     */
    private TableRow add_author_to_table(String title, String details)
    {
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tr.setWeightSum(7);

        tr.addView(create_plain_text_view(title, 3, R.color.colorWhite));
        tr.addView(create_plain_text_view(details, 4, R.color.colorSemiWhite));

        return tr;
    }
}