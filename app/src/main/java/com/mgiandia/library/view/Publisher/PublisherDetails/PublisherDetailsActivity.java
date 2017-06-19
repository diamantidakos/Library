package com.mgiandia.library.view.Publisher.PublisherDetails;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mgiandia.library.R;
import com.mgiandia.library.memorydao.PublisherDAOMemory;
import com.mgiandia.library.view.Book.ManageBooks.ManageBooksActivity;
import com.mgiandia.library.view.Publisher.AddPublisher.AddEditPublisherActivity;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class PublisherDetailsActivity extends AppCompatActivity implements PublisherDetailsView
{
    PublisherDetailsPresenter presenter;

    public void startShowBooks(int authorID)
    {
        Intent intent = new Intent(this, ManageBooksActivity.class);
        intent.putExtra("publisher_id", authorID);
        startActivityForResult(intent, 100);
    }

    public void startEdit(int authorID)
    {
        Intent intent = new Intent(this, AddEditPublisherActivity.class);
        intent.putExtra("publisher_id", authorID);
        startActivityForResult(intent, 2);
    }

    public int getAttachedPublisherID()
    {
        return this.getIntent().hasExtra("publisher_id") ? this.getIntent().getExtras().getInt("publisher_id") : null;
    }

    public void setID(String value)
    {
        ((TextView)findViewById(R.id.text_user_id)).setText(value);
    }

    public void setName(String value)
    {
        ((TextView)findViewById(R.id.text_first_name)).setText(value);
    }

    public void setPhone(String value)
    {
        ((TextView)findViewById(R.id.text_telephone)).setText(value);
    }

    public void setEmail(String value)
    {
        ((TextView)findViewById(R.id.text_email)).setText(value);
    }

    public void setBooksPublished(String value)
    {
        ((TextView)findViewById(R.id.books_published_text)).setText(value);
    }

    public void setCountry(String value)
    {
        ((TextView)findViewById(R.id.text_country)).setText(value);
    }

    public void setAddressCity(String value)
    {
        ((TextView)findViewById(R.id.text_city)).setText(value);
    }

    public void setAddressStreet(String value)
    {
        ((TextView)findViewById(R.id.text_street)).setText(value);
    }

    public void setAddressNumber(String value)
    {
        ((TextView)findViewById(R.id.text_number)).setText(value);
    }

    public void setAddressPostalCode(String value)
    {
        ((TextView)findViewById(R.id.text_zip)).setText(value);
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
        setContentView(R.layout.activity_publisher_details);
        presenter = new PublisherDetailsPresenter(this, new PublisherDAOMemory());

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
