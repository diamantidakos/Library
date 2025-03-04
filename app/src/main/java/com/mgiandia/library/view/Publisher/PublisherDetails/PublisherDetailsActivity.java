package com.mgiandia.library.view.Publisher.PublisherDetails;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.platform.ComposeView;
import androidx.lifecycle.ViewModelProvider;
import com.mgiandia.library.R;
import com.mgiandia.library.domain.Publisher;
import com.mgiandia.library.ui.composable.ActivitiesKt;
import com.mgiandia.library.view.Book.ManageBooks.ManageBooksActivity;
import com.mgiandia.library.view.Publisher.AddPublisher.AddEditPublisherActivity;
import java.util.Objects;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class PublisherDetailsActivity extends AppCompatActivity implements PublisherDetailsView
{
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
        return this.getIntent().hasExtra("publisher_id") ? Objects.requireNonNull(this.getIntent().getExtras()).getInt("publisher_id") : -1;
    }

    /**
     * Θέτει το id του εκδότη.
     * @param value Tο id του εκδότη.
     */
    public void setID(String value)
    {
        model.setPublisherID(value);
    }

    /**
     * Θέτει το όνομα του εκδότη.
     * @param value Το όνομα του εκδότη
     */
    public void setName(String value)
    {
        model.setName(value);
    }

    /**
     * Θέτει τον αρι8μό του εκδότη.
     * @param value Ο αρι8μός του εκδότη
     */
    public void setPhone(String value)
    {
        model.setPhone(value);
    }

    /**
     * Θέτει τον αριθμό ηλεκτρονικού ταχυδρομείου του εκδότη.
     * @param value Ο αρι8μός του ηλεκτρονικού ταχυδρομείου του εκδότη.
     */
    public void setEmail(String value)
    {
        model.setEmail(value);
    }

    /**
     * Θέτει τα βιβλία που έχουν εκδοθεί από τον εκδότη αυτό.
     * @param value Τα βιβλία που έχουν εκδοθεί από τον εκδότη αυτό
     */
    public void setBooksPublished(String value)
    {
        model.setPublished(value);
    }

    /**
     * Θέτει την χώρα του εκδότη.
     * @param value Η χώρα του εκδότη
     */
    public void setCountry(String value)
    {
        model.setCountry(value);
    }

    /**
     * Θέτει την πόλη του εκδότη.
     * @param value Η πόλη του εκδότη
     */
    public void setAddressCity(String value)
    {
        model.setCity(value);
    }

    /**
     * Θέτει την οδό του εκδότη.
     * @param value Η οδός του εκδότη
     */
    public void setAddressStreet(String value)
    {
        model.setStreet(value);
    }

    /**
     * Θέτει τον αριθμό του εκδότη.
     * @param value Ο αριθμός του εκδότη
     */
    public void setAddressNumber(String value)
    {
        model.setNumber(value);
    }

    /**
     * Θέτει τον ταχυδρομικό κώδικα.
     * @param value Ο ταχυδρομικός κώδικας
     */
    public void setAddressPostalCode(String value)
    {
        model.setPostCode(value);
    }

    /**
     * Θέτει το όνομα της σελίδας.
     * @param value το όνομα της σελίδας
     */
    public void setPageName(String value)
    {
        Objects.requireNonNull(getSupportActionBar()).setTitle(value);
    }

    /**
     * Εμφανίζει ένα Toast.
     * @param value To περιεχόμενο που θα εμφανιστεί
     */
    public void showToast(String value)
    {
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }


    PublisherDetailsPresenter presenter;
    PublisherDetailsViewModel model;

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

        model = new ViewModelProvider(this).get(PublisherDetailsViewModel.class);
        presenter = model.getPresenter(this);

        ComposeView composeView = findViewById(R.id.compose_view);
        ActivitiesKt.showPublisherDetailsView(composeView, model);

        int publisherID = getAttachedPublisherID();
        Publisher publisher = model.findPublisher(publisherID);

        if (publisher != null)
        {
            setID("#" + publisher.getId());
            setName(publisher.getName());
            setPhone(publisher.getTelephone().getTelephoneNumber());
            setEmail(publisher.getEMail().getAddress());
            setBooksPublished(publisher.getBooks().size() + " " + getString(R.string.books));
            setCountry(publisher.getAddress().getCountry());
            setAddressCity(publisher.getAddress().getCity());
            setAddressStreet(publisher.getAddress().getStreet());
            setAddressNumber(publisher.getAddress().getNumber());
            setAddressPostalCode(publisher.getAddress().getZipCode().getCode());

            model.observeClicks(this, buttonTextResId ->
            {
                if (buttonTextResId != null)
                {
                    if (buttonTextResId.equals(R.string.edit_user))
                    {
                        presenter.onStartEditButtonClick();
                    }
                    else if (buttonTextResId.equals(R.string.show_books))
                    {
                        presenter.onStartShowBooksButtonClick();
                    }
                }
            });
        }
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
