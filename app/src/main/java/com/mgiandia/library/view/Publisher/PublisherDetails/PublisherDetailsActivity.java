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

    /**
     * Ξεκινάει το activity ManageBooksActivity
     * με παράμετρο το id του εκδότη.
     * @param authorID Το id του εκδότη
     */
    public void startShowBooks(int authorID)
    {
        Intent intent = new Intent(this, ManageBooksActivity.class);
        intent.putExtra("publisher_id", authorID);
        startActivityForResult(intent, 100);
    }

    /**
     * Ξεκινάει το activity AddEditPublisherActivity
     * με παράμετρο το id του εκδότη.
     * @param authorID Το id του εκδότη
     */
    public void startEdit(int authorID)
    {
        Intent intent = new Intent(this, AddEditPublisherActivity.class);
        intent.putExtra("publisher_id", authorID);
        startActivityForResult(intent, 2);
    }

    /**
     * Επιστρέφει το id του εκδότη.
     * @return Το id του εκδότη
     */
    public int getAttachedPublisherID()
    {
        return this.getIntent().hasExtra("publisher_id") ? this.getIntent().getExtras().getInt("publisher_id") : null;
    }

    /**
     * Θέτει το id του εκδότη.
     * @param value Tο id του εκδότη.
     */
    public void setID(String value)
    {
        ((TextView)findViewById(R.id.text_user_id)).setText(value);
    }

    /**
     * Θέτει το όνομα του εκδότη.
     * @param value Το όνομα του εκδότη
     */
    public void setName(String value)
    {
        ((TextView)findViewById(R.id.text_first_name)).setText(value);
    }

    /**
     * Θέτει τον αρι8μό του εκδότη.
     * @param value Ο αρι8μός του εκδότη
     */
    public void setPhone(String value)
    {
        ((TextView)findViewById(R.id.text_telephone)).setText(value);
    }

    /**
     * Θέτει τον αριθμό ηλεκτρονικού ταχυδρομείου του εκδότη.
     * @param value Ο αρι8μός του ηλεκτρονικού ταχυδρομείου του εκδότη.
     */
    public void setEmail(String value)
    {
        ((TextView)findViewById(R.id.text_email)).setText(value);
    }

    /**
     * Θέτει τα βιβλία που έχουν εκδοθεί από τον εκδότη αυτό.
     * @param value Τα βιβλία που έχουν εκδοθεί από τον εκδότη αυτό
     */
    public void setBooksPublished(String value)
    {
        ((TextView)findViewById(R.id.books_published_text)).setText(value);
    }

    /**
     * Θέτει την χώρα του εκδότη.
     * @param value Η χώρα του εκδότη
     */
    public void setCountry(String value)
    {
        ((TextView)findViewById(R.id.text_country)).setText(value);
    }

    /**
     * Θέτει την πόλη του εκδότη.
     * @param value Η πόλη του εκδότη
     */
    public void setAddressCity(String value)
    {
        ((TextView)findViewById(R.id.text_city)).setText(value);
    }

    /**
     * Θέτει την οδό του εκδότη.
     * @param value Η οδός του εκδότη
     */
    public void setAddressStreet(String value)
    {
        ((TextView)findViewById(R.id.text_street)).setText(value);
    }

    /**
     * Θέτει τον αριθμό του εκδότη.
     * @param value Ο αριθμός του εκδότη
     */
    public void setAddressNumber(String value)
    {
        ((TextView)findViewById(R.id.text_number)).setText(value);
    }

    /**
     * Θέτει τον ταχυδρομικό κώδικα.
     * @param value Ο ταχυδρομικός κώδικας
     */
    public void setAddressPostalCode(String value)
    {
        ((TextView)findViewById(R.id.text_zip)).setText(value);
    }

    /**
     * Θέτει το όνομα της σελίδας.
     * @param value το όνομα της σελίδας
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

    /**
     * Ξανά δημιουργεί το activity με νεό instance.
     * Σε περίπτωση που ο ζητούμενος κωδικός είναι
     * 2 και ο κωδικός του αποτελέσματος είναι ok,
     * εμφανίζει ένα toast.
     * @param requestCode Ο ζητούμενος κωδικός
     * @param resultCode Ο κωδικός του αποτελέσματος
     * @param data Το intent
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
}
