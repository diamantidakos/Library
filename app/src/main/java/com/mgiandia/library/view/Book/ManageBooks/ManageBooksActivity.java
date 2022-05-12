package com.mgiandia.library.view.Book.ManageBooks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import com.mgiandia.library.R;
import com.mgiandia.library.memorydao.AuthorDAOMemory;
import com.mgiandia.library.memorydao.BookDAOMemory;
import com.mgiandia.library.memorydao.PublisherDAOMemory;
import com.mgiandia.library.util.Quadruple;
import com.mgiandia.library.view.Book.AddEditBook.AddEditBookActivity;
import com.mgiandia.library.view.Book.BookDetails.BookDetailsActivity;
import com.mgiandia.library.view.Items.ManageItems.ManageItemsActivity;
import com.mgiandia.library.view.Util.AdvancedListAdapter;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class ManageBooksActivity extends AppCompatActivity implements ManageBooksView, SearchView.OnQueryTextListener
{
    ManageBooksPresenter presenter;

    private ListView itemListView;
    private SearchView searchListView;
    private AdvancedListAdapter adapter;

    /**
     * Δημιουργεί to layout και αρχικοποιεί
     * το activity.
     * @param savedInstanceState το Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_items);

        adapter = new AdvancedListAdapter(this);

        itemListView = (ListView) findViewById(R.id.item_list_view);
        itemListView.setAdapter(adapter);
        itemListView.setTextFilterEnabled(true);

        searchListView = (SearchView) findViewById(R.id.items_list_search_view);
        searchListView.setIconifiedByDefault(false);
        searchListView.setOnQueryTextListener(this);

        presenter = new ManageBooksPresenter(this, new BookDAOMemory(), new AuthorDAOMemory(), new PublisherDAOMemory());

        findViewById(R.id.item_add_new).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                presenter.onStartAddNew();
            }
        });

        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                presenter.onClickItem(((Quadruple)parent.getItemAtPosition(position)).getUID());
            }
        });
    }

    /**
     * Τροποποιεί το κείμενο του βιβλίου.
     * @param text Το κείμενο που θα τροποποιηθεί
     * @return true
     */
    public boolean onQueryTextChange(String text)
    {
        if (TextUtils.isEmpty(text))
            itemListView.clearTextFilter();
        else
            itemListView.setFilterText(text);

        return true;
    }

    /**
     * Υποβάλλει το κείμενο του βιβλίου.
     * @param query Θέτει το κείμενο ως query
     * @return false
     */
    public boolean onQueryTextSubmit(String query)
    {
        return false;
    }

    /**
     * Αδείαζει το κείμενο που βρίσκεται
     * μέσα στην μπάρα αναζήτησης.
     */
    private void clear_search_bar()
    {
        searchListView.setQuery("", false);
        searchListView.clearFocus();
        presenter.onLoadSource();
    }

    /**
     * Αδειάζει την μπάρα αναζήτησης
     * @param requestCode Ο ζητούμενος κωδικός
     * @param resultCode Ο κωδικός του αποτελέσματος
     * @param data Το intent
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0 && resultCode == Activity.RESULT_OK)
        {
            clear_search_bar();
            presenter.onShowToast(data.getStringExtra("message_to_toast"));
        }
        else if(requestCode == 1)
        {
            clear_search_bar();

            if(resultCode == Activity.RESULT_OK)
                presenter.onShowToast(data.getStringExtra("message_to_toast"));
        }
    }

    /**
     * Φορτώνει την λίστα με τα βιβλία
     * @param input Η λιστα που θα φορτώσει
     */
    public void loadSource(List<Quadruple> input)
    {
        adapter.loadSource(input);
    }

    /**
     * Μεταφέρει τον χρήστη στο activity BookDetailsActivity
     * όταν γίνει click πάνω στο βιβλίο με id uid.
     * @param uid Το μοναδικό id του βιβλίου
     */
    public void clickItem(int uid)
    {
        Intent intent = new Intent(this, BookDetailsActivity.class);
        intent.putExtra("book_id", uid);
        startActivityForResult(intent, 1);
    }

    /**
     * Μεταφέρει τον χρήστη στο activity ManageItemsActivity
     * όταν γίνει click πάνω στο βιβλίο με id uid.
     * @param uid Το μοναδικό id του βιβλίου
     */
    public void clickItemList(int uid)
    {
        Intent intent = new Intent(this, ManageItemsActivity.class);
        intent.putExtra("book_id", uid);
        startActivityForResult(intent, 1);
    }

    /**
     * Ξεκινάει το activity AddEditBookActivity
     */
    public void startAddNew()
    {
        Intent intent = new Intent(this, AddEditBookActivity.class);
        startActivityForResult(intent, 0);
    }

    /**
     * Εμφανίζει ένα Toast.
     * @param value Το περιεχόμενο που θα εμφανιστεί
     */
    public void showToast(String value)
    {
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
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
     * Επιστρέφει το id του συγγραφέα.
     * @return Το id του συγγραφέα
     */
    public Integer getAttachedAuthorID()
    {
        return this.getIntent().hasExtra("author_id") ? this.getIntent().getExtras().getInt("author_id") : null;
    }

    /**
     * Επιστρέφει το id του εκδότη.
     * @return Το id του εκδότη
     */
    public Integer getAttachedPublisherID()
    {
        return this.getIntent().hasExtra("publisher_id") ? this.getIntent().getExtras().getInt("publisher_id") : null;
    }

    /**
     * Αποφασίζει με ποιο με τρόπο θα φορτώσει τα
     * αντικείμενα.
     * @return Τον τρόπο που θα φορτώσει τα αντικείμενα
     */
    public boolean shouldLoadItemsOnClick()
    {
        return this.getIntent().hasExtra("should_load_items");
    }
}
