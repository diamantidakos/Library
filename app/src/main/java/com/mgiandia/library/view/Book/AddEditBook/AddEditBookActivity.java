package com.mgiandia.library.view.Book.AddEditBook;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import com.mgiandia.library.R;
import com.mgiandia.library.memorydao.AuthorDAOMemory;
import com.mgiandia.library.memorydao.BookDAOMemory;
import com.mgiandia.library.memorydao.ItemDAOMemory;
import com.mgiandia.library.memorydao.PublisherDAOMemory;
import com.mgiandia.library.view.Util.MultiSelectSpinner;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class AddEditBookActivity extends AppCompatActivity implements AddEditBookView
{
    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(AddEditBookActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, null).create().show();
    }

    public void successfullyFinishActivity(String message)
    {
        Intent retData = new Intent();
        retData.putExtra("message_to_toast", message);
        setResult(RESULT_OK, retData);
        finish();
    }

    public String getBookTitle()
    {
        return ((EditText)findViewById(R.id.edit_text_book_title)).getText().toString().trim();
    }

    public String getISBN()
    {
        return ((EditText)findViewById(R.id.edit_text_isbn)).getText().toString().trim();
    }

    public String getPublication()
    {
        return ((EditText)findViewById(R.id.edit_text_publication)).getText().toString().trim();
    }

    public String getYear()
    {
        return ((EditText)findViewById(R.id.edit_text_publicationyear)).getText().toString().trim();
    }

    public Integer getPublisherPosition()
    {
        int pos = ((Spinner)findViewById(R.id.edit_text_publisher)).getSelectedItemPosition();
        return pos == 0 ? null : pos;
    }

    public List<Integer> getAuthorPositions()
    {
        List<Integer> positions = new ArrayList<>();
        boolean[] indexes = ((MultiSelectSpinner)findViewById(R.id.edit_text_authors)).getItemsIndexes();

        for(int i = 0; i < indexes.length; i++)
            if(indexes[i])
                positions.add(i+1);

        return positions;
    }

    public Integer getAttachedBookID()
    {
        return this.getIntent().hasExtra("book_id") ? this.getIntent().getExtras().getInt("book_id") : null;
    }

    public void setBookTitle(String value)
    {
        ((EditText)findViewById(R.id.edit_text_book_title)).setText(value);
    }

    public void setPublisherPosition(Integer value)
    {
        ((Spinner)findViewById(R.id.edit_text_publisher)).setSelection(value);
    }

    public void setISBN(String value)
    {
        ((EditText)findViewById(R.id.edit_text_isbn)).setText(value);
    }

    public void setPublication(String value)
    {
        ((EditText)findViewById(R.id.edit_text_publication)).setText(value);
    }

    public void setYear(String value)
    {
        ((EditText)findViewById(R.id.edit_text_publicationyear)).setText(value);
    }

    public void setAuthorPositions(List<Integer> value)
    {
        for(int i = 0; i < value.size(); i++)
            value.set(i, value.get(i)-1);

        ((MultiSelectSpinner)findViewById(R.id.edit_text_authors)).setSelectedItems(value);
    }

    public void setPageName(String value)
    {
        getSupportActionBar().setTitle(value);
    }

    public void setAuthorList(List<String> names)
    {
        ((MultiSelectSpinner) findViewById(R.id.edit_text_authors)).setItems(names);
        setAuthorPositions(new ArrayList<Integer>());
    }

    public void setPublisherList(List<String> names, String defaultName)
    {
        names.add(0, defaultName);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ((Spinner) findViewById(R.id.edit_text_publisher)).setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_book);
        final AddEditBookPresenter presenter = new AddEditBookPresenter(this, new BookDAOMemory(), new PublisherDAOMemory(), new AuthorDAOMemory(), new ItemDAOMemory());

        findViewById(R.id.complete_registration_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onSaveBook();
            }
        });
    }
}
