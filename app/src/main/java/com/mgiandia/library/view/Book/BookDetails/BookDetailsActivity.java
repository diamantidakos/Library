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

    public void startEdit(int bookID)
    {
        Intent intent = new Intent(this, AddEditBookActivity.class);
        intent.putExtra("book_id", bookID);
        startActivityForResult(intent, 2);
    }

    public int getAttachedBookID()
    {
        return this.getIntent().hasExtra("book_id") ? this.getIntent().getExtras().getInt("book_id") : null;
    }

    public void setID(String value)
    {
        ((TextView)findViewById(R.id.text_book_id)).setText(value);
    }

    public void setBookTitle(String value)
    {
        ((TextView)findViewById(R.id.text_book_title)).setText(value);
    }

    public void setPublisher(String value)
    {
        ((TextView)findViewById(R.id.text_book_publisher)).setText(value);
    }

    public void setISBN(String value)
    {
        ((TextView)findViewById(R.id.text_book_isbn)).setText(value);
    }

    public void setPublication(String value)
    {
        ((TextView)findViewById(R.id.text_book_publication)).setText(value);
    }

    public void setYear(String value)
    {
        ((TextView)findViewById(R.id.text_book_publicationyear)).setText(value);
    }

    public void setItemsNo(String value)
    {
        ((TextView)findViewById(R.id.text_book_copies)).setText(value);
    }

    public void setAuthors(List<String> author_ids, List<String> author_names)
    {
        TableLayout table = (TableLayout)findViewById(R.id.author_parent_layout);
        table.removeAllViews();

        for(int i = 0; i < author_ids.size(); i++)
            table.addView(add_author_to_table(author_ids.get(i), author_names.get(i)));
    }

    public void setPageName(String value)
    {
        getSupportActionBar().setTitle(value);
    }

    public void showToast(String value)
    {
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }

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
