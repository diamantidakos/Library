package com.mgiandia.library.view.Author.AddEditAuthor;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.mgiandia.library.R;
import com.mgiandia.library.memorydao.AuthorDAOMemory;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class AddEditAuthorActivity extends AppCompatActivity implements AddEditAuthorView
{
    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(AddEditAuthorActivity.this)
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

    public String getFirstName()
    {
        return ((EditText)findViewById(R.id.edit_text_first_name)).getText().toString().trim();
    }

    public String getLastName()
    {
        return ((EditText)findViewById(R.id.edit_text_last_name)).getText().toString().trim();
    }

    public Integer getAttachedAuthorID()
    {
        return this.getIntent().hasExtra("author_id") ? this.getIntent().getExtras().getInt("author_id") : null;
    }

    public void setFirstName(String value)
    {
        ((EditText)findViewById(R.id.edit_text_first_name)).setText(value);
    }

    public void setLastName(String value)
    {
        ((EditText)findViewById(R.id.edit_text_last_name)).setText(value);
    }

    public void setPageName(String value)
    {
        getSupportActionBar().setTitle(value);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_author);
        final AddEditAuthorPresenter presenter = new AddEditAuthorPresenter(this, new AuthorDAOMemory());

        findViewById(R.id.complete_registration_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                presenter.onSaveAuthor();
            }
        });
    }
}
