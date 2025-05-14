package com.mgiandia.library.view.Author.ManageAuthors;

import android.app.Activity;
import android.content.Intent;
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
import com.mgiandia.library.R;
import com.mgiandia.library.domain.Author;
import com.mgiandia.library.ui.composable.ActivitiesKt;
import com.mgiandia.library.util.Quadruple;
import com.mgiandia.library.view.AbstractActivityObject;
import com.mgiandia.library.view.Author.AddEditAuthor.AddEditAuthorActivity;
import com.mgiandia.library.view.Author.AuthorDetails.AuthorDetailsActivity;
import com.mgiandia.library.view.Util.AdvancedListAdapter;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */
public class ManageAuthorsActivity extends AbstractActivityObject implements ManageAuthorsView, SearchView.OnQueryTextListener
{
    private ManageAuthorsPresenter presenter;
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
        ManageAuthorsViewModel model = new ViewModelProvider((ViewModelStoreOwner) this).get(ManageAuthorsViewModel.class);
        presenter = model.getPresenter(this);

        ComposeView composeView = findViewById(R.id.compose_view);
        ActivitiesKt.showManageAuthorsView(composeView, model);

        model.getSelectedAuthorID().observe((LifecycleOwner) this, value ->
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
                ArrayList<Author> authors = new ArrayList<>(model.findAuthors(value.trim()));
                model.setAuthors(authors);
                ActivitiesKt.showManageAuthorsViewSearch(composeView, model);
            }
        });

        model.observeClicks(this, buttonTextResId ->
        {
            if (buttonTextResId != null && buttonTextResId.equals(R.string.add_new_item))
            {
                presenter.onStartAddNew();
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
     * Αδείαζει την μπάρα αναζήτησης
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
            //clear_search_bar();
            presenter.onShowToast(data.getStringExtra("message_to_toast"));
        }
        else if(requestCode == 1)
        {
            //clear_search_bar();

            if(resultCode == Activity.RESULT_OK)
                presenter.onShowToast(data.getStringExtra("message_to_toast"));
        }

        refreshActivity();
    }

    private void refreshActivity()
    {
        finish();
        startActivity(getIntent());
    }

    /**
     * Φορτώνει την λίστα με τους συγγραφείς
     * @param input Η λίστα που θα φορτώσει
     */
    public void loadSource(List<Quadruple> input)
    {
        adapter.loadSource(input);
    }

    /**
     * Μεταφέρει τον χρήστη στο activity AuthorDetailsActivity
     * όταν γίνει click πάνω στον συγγραφέα με id uid.
     * @param uid Το μοναδικό id του συγγραφέα
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
     * Εμφανίζει ένα Toast.
     * @param value Το περιεχόμενο που θα εμφανιστεί
     */
    public void showToast(String value)
    {
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }
}
