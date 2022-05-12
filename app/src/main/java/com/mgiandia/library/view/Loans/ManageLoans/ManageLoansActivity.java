package com.mgiandia.library.view.Loans.ManageLoans;

import android.app.Activity;
import android.app.AlertDialog;
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
import com.mgiandia.library.memorydao.BorrowerDAOMemory;
import com.mgiandia.library.memorydao.LoanDAOMemory;
import com.mgiandia.library.util.Quadruple;
import com.mgiandia.library.view.Loans.AddLoan.AddLoansActivity;
import com.mgiandia.library.view.Util.AdvancedListAdapter;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class ManageLoansActivity extends AppCompatActivity implements ManageLoansView, SearchView.OnQueryTextListener
{
    ManageLoansPresenter presenter;

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

        presenter = new ManageLoansPresenter(this, new LoanDAOMemory(), new BorrowerDAOMemory());

        findViewById(R.id.item_add_new).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                presenter.onAddNewItem();
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
     * Τροποποιεί το κείμενο.
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
     * Υποβάλλει το κείμενο.
     * @param query Θέτει το κείμενο ως query
     * @return false
     */
    public boolean onQueryTextSubmit(String query)
    {
        return false;
    }

    /**
     * Αδειάζει το κείμενο που βρίσκεται
     * μέσα στην μπάρα αναζήτησης.
     */
    private void clear_search_bar()
    {
        searchListView.setQuery("", false);
        searchListView.clearFocus();
        presenter.onLoadSource();
    }

    /**
     * Φορτώνει την λίστα με τους δανειζομένους.
     * @param input Η λίστα που θα φορτώσει
     */
    public void loadSource(List<Quadruple> input)
    {
        adapter.loadSource(input);
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
     * Εμφανίζει ένα μήνυμα σε μορφή alert
     * με περιεχόμενο message και τίτλο title.
     * @param title Ο τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    public void showAlert(String title, String message)
    {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(message).setPositiveButton(R.string.ok, null).create().show();
    }

    /**
     * Ξεκινάει το activity AddLoansActivity
     */
    public void startAddNew(int uid)
    {
        Intent intent = new Intent(this, AddLoansActivity.class);
        intent.putExtra("borrower_id", uid);
        startActivityForResult(intent, 0);
    }

    /**
     * Επιστρέφει το id του δανειζόμενου
     * @return Το id του δανειζόμενου
     */
    public int getAttachedBorrowerID()
    {
        return this.getIntent().getExtras().getInt("borrower_id");
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
     * Αδειάζει την μπάρα αναζήτησης
     * @param requestCode Ο ζητούμενος κωδικός
     * @param resultCode Ο κωδικός του αποτελέσματος
     * @param data Το intent
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK)
        {
            clear_search_bar();
            presenter.onShowToast(data.getStringExtra("message_to_toast"));
        }
    }
}
