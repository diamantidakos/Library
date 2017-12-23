package com.mgiandia.library.view.Book.BookDetails;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import com.mgiandia.library.R;
import com.mgiandia.library.memorydao.BookDAOMemory;
import com.mgiandia.library.view.Book.AddEditBook.AddEditBookActivity;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class BookDetailsActivity extends AppCompatActivity implements BookDetailsView
{
    BookDetailsPresenter presenter;

    /**
     * Ξεκινάει να τροποποιεί το βιβλίο με
     * id bookID
     * @param bookID Το id του βιβλίου που θα τροποποιηθεί
     */
    public void startEdit(int bookID)
    {
        Intent intent = new Intent(this, AddEditBookActivity.class);
        intent.putExtra("book_id", bookID);
        startActivityForResult(intent, 2);
    }

    /**
     * Επιστρέφει το id του βιβλίου.
     * @return Το id του βιβλίου
     */
    public int getAttachedBookID()
    {
        return this.getIntent().hasExtra("book_id") ? this.getIntent().getExtras().getInt("book_id") : null;
    }

    /**
     * Θέτει το id του βιβλίου.
     * @param value Το id του βιβλίου.
     */
    public void setID(String value)
    {
        ((TextView)findViewById(R.id.text_book_id)).setText(value);
    }

    /**
     * Θέτει τον τίτλο του βιβλίου.
     * @param value Ο τίτλος του βιβλίου.
     */
    public void setBookTitle(String value)
    {
        ((TextView)findViewById(R.id.text_book_title)).setText(value);
    }

    /**
     * Θέτει τον εκδότη του βιβλίου.
     * @param value Ο εκδότης του βιβλίου.
     */
    public void setPublisher(String value)
    {
        ((TextView)findViewById(R.id.text_book_publisher)).setText(value);
    }

    /**
     * Θέτει το ISBN του βιβλίου.
     * @param value Το ISBN του βιβλίου.
     */
    public void setISBN(String value)
    {
        ((TextView)findViewById(R.id.text_book_isbn)).setText(value);
    }

    /**
     * Θέτει το έτος δημοσίευσης του βιβλίου.
     * @param value Το έτος δημοσίευσης του βιβλίου
     */
    public void setPublication(String value)
    {
        ((TextView)findViewById(R.id.text_book_publication)).setText(value);
    }

    /**
     * Θέτει το έτος δημοσίευσης του βιβλίου.
     * @param value Το έτος δημοσίευσης του βιβλίου
     */
    public void setYear(String value)
    {
        ((TextView)findViewById(R.id.text_book_publicationyear)).setText(value);
    }

    /**
     * Θέτει τον αριθμό του βιβλίου.
     * @param value Ο αριθμός του βιβλίου
     */
    public void setItemsNo(String value)
    {
        ((TextView)findViewById(R.id.text_book_copies)).setText(value);
    }

    /**
     * Θέτει τους συγγραφείς του βιβλίου.
     * @param author_ids Τα id των συγγραφέων
     * @param author_names Τα ονόματα των συγγραφέων
     */
    public void setAuthors(List<String> author_ids, List<String> author_names)
    {
        TableLayout table = (TableLayout)findViewById(R.id.author_parent_layout);
        table.removeAllViews();

        for(int i = 0; i < author_ids.size(); i++)
            table.addView(add_author_to_table(author_ids.get(i), author_names.get(i)));
    }

    /**
     * Θέτει το όνομα της σελίδας.
     * @param value Το όνομα της σελίδας
     */
    public void setPageName(String value)
    {
        getSupportActionBar().setTitle(value);
    }

    /**
     * Εμφανίζει ένα Toast.
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
        setContentView(R.layout.activity_book_details);
        presenter = new BookDetailsPresenter(this, new BookDAOMemory());

        findViewById(R.id.edit_book_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                presenter.onStartEditButtonClick();
            }
        });
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
