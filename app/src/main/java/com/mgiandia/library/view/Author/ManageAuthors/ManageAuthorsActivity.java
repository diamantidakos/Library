package com.mgiandia.library.view.Author.ManageAuthors;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;

import com.mgiandia.library.R;
import com.mgiandia.library.memorydao.AuthorDAOMemory;
import com.mgiandia.library.util.Quadruple;
import com.mgiandia.library.view.Author.AddEditAuthor.AddEditAuthorActivity;
import com.mgiandia.library.view.Author.AuthorDetails.AuthorDetailsActivity;
import com.mgiandia.library.view.Util.AdvancedListAdapter;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class ManageAuthorsActivity extends AppCompatActivity implements ManageAuthorsView, SearchView.OnQueryTextListener
{
    ManageAuthorsPresenter presenter;

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

        presenter = new ManageAuthorsPresenter(this, new AuthorDAOMemory());

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
     * Τροποποιεί το κείμενο του συγγραφέα.
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
     * Υποβάλλει το κείμενο του συγγραφέα.
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
     * Αδείαζει την μπάρα αναζήτησης
     * @param requestCode Ο ζητούμενος κώδικος
     * @param resultCode Ο κωδικός του αποτελέσματος
     * @param data Tο intent
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
     * Φορτώνει την λίστα με τους συγγραφείς
     * @param input Η λιστα που θα φορτώσει
     */
    public void loadSource(List<Quadruple> input)
    {
        adapter.loadSource(input);
    }

    /**
     * Μεταφερει τον χρήστη στο activity AuthorDetailsActivity
     * όταν γίνει click πάνω στον συγγραφέα με id uid.
     * @param uid To μοναδικό id του συγγραφέα
     */
    public void clickItem(int uid)
    {
        Intent intent = new Intent(this, AuthorDetailsActivity.class);
        intent.putExtra("author_id", uid);
        startActivityForResult(intent, 1);
    }

    /**
     * Ξεκινάει το activity AddEditAuthorActivity
     */
    public void startAddNew()
    {
        Intent intent = new Intent(this, AddEditAuthorActivity.class);
        startActivityForResult(intent, 0);
    }

    /**
     * Εμφανίζει ένα μήνυμα με περιεχόμενο
     * value.
     * @param value To περιεχόμενο που θα εμφανιστεί
     */
    public void showToast(String value)
    {
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }
}
