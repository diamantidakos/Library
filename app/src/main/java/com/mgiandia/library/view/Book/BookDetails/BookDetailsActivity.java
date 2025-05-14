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
import androidx.lifecycle.ViewModelStoreOwner;

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
import com.mgiandia.library.view.AbstractActivityObject;
import com.mgiandia.library.view.Book.AddEditBook.AddEditBookActivity;
import com.mgiandia.library.view.Book.AddEditBook.AddEditBookViewModel;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */
public class BookDetailsActivity extends AbstractActivityObject implements BookDetailsView
{
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

        BookDetailsViewModel model = new ViewModelProvider((ViewModelStoreOwner) this).get(BookDetailsViewModel.class);
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