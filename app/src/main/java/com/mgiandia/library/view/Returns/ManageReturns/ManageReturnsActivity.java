package com.mgiandia.library.view.Returns.ManageReturns;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.platform.ComposeView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.mgiandia.library.R;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.ui.composable.ActivitiesKt;
import com.mgiandia.library.util.Quadruple;
import com.mgiandia.library.view.AbstractActivityObject;
import com.mgiandia.library.view.Util.AdvancedListAdapter;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */
public class ManageReturnsActivity extends AppCompatActivity implements ManageReturnsView, SearchView.OnQueryTextListener
{
    private ManageReturnsPresenter presenter;

    private ListView itemListView;
    //private SearchView searchListView;
    private AdvancedListAdapter adapter;
    private ManageReturnsViewModel model;

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
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
        setContentView(R.layout.manage_items_compose);
        adapter = new AdvancedListAdapter(this);

        model = new ViewModelProvider((ViewModelStoreOwner) this).get(ManageReturnsViewModel.class);
        presenter = model.getPresenter(this);

        ComposeView composeView = findViewById(R.id.compose_view);
        ActivitiesKt.showManageReturnsView(composeView, model);

        int borrowerID = getAttachedBorrowerID();
        model.setBorrowerID(borrowerID);

        model.getSelectedLoanID().observe((LifecycleOwner) this, value ->
        {
            if (value != null)
            {
                presenter.onClickItem(value);
            }
        });

        model.getTextOnSearchBar().observe((LifecycleOwner) this, value ->
        {
            if (value != null)
            {
                ArrayList<Loan> loans = new ArrayList<>(model.findLoansByBookTitle(value));
                model.setLoans(loans);
                ActivitiesKt.showManageReturnsViewSearch(composeView, model);
            }
        });

        model.observeClicks(this, buttonTextResId ->
        {
            if (buttonTextResId != null && buttonTextResId.equals(R.string.add_new_item))
            {
                presenter.onAddNewItem();
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
    /*
    private void clear_search_bar()
    {
        searchListView.setQuery("", false);
        searchListView.clearFocus();
        presenter.onLoadSource();
    }
    */

    /**
     * Φορτώνει την λίστα με τις επιστροφές.
     * @param input Η λιστα που θα φορτώσει
     */
    public void loadSource(List<Quadruple> input)
    {
        adapter.loadSource(input);
    }

    /**
     * Κατά την αλλαγή της κατάστασης ενός δανείου
     * εμφανίζεται ένα μήνυμα μορφής alert.
     * @param uid Το μοναδικό id του δανειζόμενου
     * @param title Ο τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    public void newLoanStateSelectAlert(int uid, String title, String message)
    {
        final int tmp = uid;

        new AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Επιστρέψτε",
            new DialogInterface.OnClickListener()
            {
                @SuppressLint("UnsafeIntentLaunch")
                public void onClick(DialogInterface dialog, int id)
                {
                    presenter.onChangeItemState(tmp, true);
                    model.removeLoan(tmp);
                    refreshActivity();
                }
            })
            .setNeutralButton("Ακύρωση",
            new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int id)
                {
                }
            })
            .setNegativeButton("Χάθηκε",
            new DialogInterface.OnClickListener()
            {
                @SuppressLint("UnsafeIntentLaunch")
                public void onClick(DialogInterface dialog, int id)
                {
                    presenter.onChangeItemState(tmp, false);
                    model.removeLoan(tmp);
                    refreshActivity();
                }
            }).create().show();
    }

    private void refreshActivity()
    {
        finish();
        startActivity(getIntent());
    }

    /**
     * Κατά την ανανέωση της σελίδας
     * διαγράφετε η μπάρα αναζήτησης
     */
    public void refresh()
    {
        //clear_search_bar();
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
     * Εμφανίζει ένα alert.
     * @param title O τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    public void showAlert(String title, String message)
    {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(message).setPositiveButton(R.string.ok, null).create().show();
    }

    /**
     * Επιστρέφει το id του δανειζόμενου.
     * @return Το id του δανειζόμενου
     */
    public int getAttachedBorrowerID()
    {
        return Objects.requireNonNull(this.getIntent().getExtras()).getInt("borrower_id");
    }

    /**
     * Θέτει το όνομα της σελίδας.
     * @param value το όνομα της σελίδας
     */
    public void setPageName(String value)
    {
        Objects.requireNonNull(getSupportActionBar()).setTitle(value);
    }
}
