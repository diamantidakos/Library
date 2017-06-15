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

    public void startShowBooks(int authorID)
    {
        Intent intent = new Intent(this, ManageBooksActivity.class);
        intent.putExtra("author_id", authorID);
        startActivityForResult(intent, 100);
    }

    public void startEdit(int authorID)
    {
        Intent intent = new Intent(this, AddEditAuthorActivity.class);
        intent.putExtra("author_id", authorID);
        startActivityForResult(intent, 2);
    }

    public int getAttachedAuthorID()
    {
        return this.getIntent().hasExtra("author_id") ? this.getIntent().getExtras().getInt("author_id") : null;
    }

    public void setID(String value)
    {
        ((TextView)findViewById(R.id.text_user_id)).setText(value);
    }

    public void setFirstName(String value)
    {
        ((TextView)findViewById(R.id.text_first_name)).setText(value);
    }

    public void setLastName(String value)
    {
        ((TextView)findViewById(R.id.text_last_name)).setText(value);
    }

    public void setBooksWritten(String value)
    {
        ((TextView)findViewById(R.id.books_published_text)).setText(value);
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
