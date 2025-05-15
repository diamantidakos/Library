package com.mgiandia.library.view.Items.ManageItems;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.platform.ComposeView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.mgiandia.library.R;
import com.mgiandia.library.domain.Item;
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
public class ManageItemsActivity extends AppCompatActivity implements ManageItemsView, SearchView.OnQueryTextListener
{
    private ManageItemsPresenter presenter;

    private ListView itemListView;
    //private SearchView searchListView;
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
        setContentView(R.layout.manage_items_compose);
        adapter = new AdvancedListAdapter(this);

        ManageItemsViewModel model = new ViewModelProvider((ViewModelStoreOwner) this).get(ManageItemsViewModel.class);
        presenter = model.getPresenter(this);

        ComposeView composeView = findViewById(R.id.compose_view);
        ActivitiesKt.showManageItemsView(composeView, model);

        int bookID = getAttachedBookID();
        model.setSelectedBookID(bookID);
        String bookTitle = model.getBookTitle(bookID);
        model.setSelectedBookTitle(bookTitle);

        model.getSelectedItemID().observe((LifecycleOwner) this, value ->
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
                ArrayList<Item> items = new ArrayList<>(model.findItemsByTitle(value));
                model.setItems(items);
                ActivitiesKt.showManageItemsViewSearch(composeView, model);
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
     * Φορτώνει την λίστα με τους δανειζομένους.
     * @param input Η λίστα που θα φορτώσει
     */
    public void loadSource(List<Quadruple> input)
    {
        adapter.loadSource(input);
    }

    /**
     * Δημιουργεί ένα μήνυμα τύπου alert
     * @param title Ο τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     * @param hasCancel Το κουμπί ακύρωσης
     * @param okListener Το κουμπί οκ
     */
    private void makeAlert(String title, String message, boolean hasCancel, DialogInterface.OnClickListener okListener)
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(message).setPositiveButton(R.string.ok, okListener);
        if(hasCancel) dialog.setNegativeButton(R.string.cancel, null);
        dialog.create().show();
    }

    /**
     * Δημιουργεί ένα μήνυμα τύπου alert
     * @param title Ο τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    public void newItemAddAlert(String title, String message)
    {
        makeAlert(title, message, true, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.doAddNewItem();
            }
        });
    }

    /**
     * Δημιουργεί ένα μήνυμα τύπου alert
     * @param uid Το id του αντικειμένου
     * @param title Ο τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    public void newItemStateSelectAlert(int uid, String title, String message)
    {
        final int tmp = uid;

        makeAlert(title, message, true, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.onChangeItemState(tmp);
            }
        });
    }

    /**
     * Αδειάζει το κείμενο που βρίσκεται
     * μέσα στην μπάρα αναζήτησης κατά την
     * ανανέωση της σελίδας
     */
    public void refresh()
    {
        //clear_search_bar();
    }

    /**
     * Εμφανίζει ένα μήνυμα σε μορφή Toast
     * με περιεχόμενο message.
     * @param value Το περιεχόμενο του μηνύματος
     */
    public void showToast(String value)
    {
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
        finish();
        startActivity(getIntent());
    }

    /**
     * Εμφανίζει ένα μήνυμα σε μορφή alert
     * με περιεχόμενο message και τίτλο title.
     * @param title Ο τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    public void showAlert(String title, String message)
    {
        makeAlert(title, message, false, null);
    }

    /**
     * Επιστρέφει το id του βιβλίου.
     * @return Το id του βιβλίου
     */
    public int getAttachedBookID()
    {
        //return Objects.requireNonNull(this.getIntent().getExtras()).getInt("book_id");
        return this.getIntent().hasExtra("book_id") ? Objects.requireNonNull(this.getIntent().getExtras()).getInt("book_id") : -1;
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
