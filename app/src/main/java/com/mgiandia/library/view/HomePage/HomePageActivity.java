package com.mgiandia.library.view.HomePage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.platform.ComposeView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.mgiandia.library.R;
import com.mgiandia.library.memorydao.MemoryInitializer;
import com.mgiandia.library.ui.composable.ActivitiesKt;
import com.mgiandia.library.view.Author.ManageAuthors.ManageAuthorsActivity;
import com.mgiandia.library.view.Book.ManageBooks.ManageBooksActivity;
import com.mgiandia.library.view.Borrower.ManageBorrowers.ManageBorrowersActivity;
import com.mgiandia.library.view.Publisher.ManagePublishers.ManagePublishersActivity;

/**
 * @author Νίκος Σαραντινός
 * <p>
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 */

public class HomePageActivity extends AppCompatActivity implements HomePageView {
    private static boolean initialized = false;

    /**
     * Δημιουργεί to layout και αρχικοποιεί
     * το activity.
     *
     * @param savedInstanceState το Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_compose);

        final HomePagePresenter presenter = new HomePagePresenter(this);

        HomePageViewModel model = new ViewModelProvider(this).get(HomePageViewModel.class);

        // Create the observer which updates the UI.
        final Observer<Integer> clickObserver = buttonLabelResId -> {
            if (buttonLabelResId == R.string.manage_books){
                presenter.onManageBooks();
            } else if (buttonLabelResId == R.string.manage_borrowers) {
                presenter.onManageBorrowers();
            } else if (buttonLabelResId == R.string.manage_items){
                presenter.onManageItems();
            } else if (buttonLabelResId == R.string.manage_authors){
                presenter.onManageAuthors();
            } else if (buttonLabelResId == R.string.manage_loans){
                presenter.onManageLoans();
            } else if (buttonLabelResId == R.string.manage_returns){
                presenter.onManageReturns();
            } else if (buttonLabelResId == R.string.manage_publishers){
                presenter.onManagePublishers();
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        model.observeClicks(this, clickObserver);

        EdgeToEdge.enable(this);
        // load a simple layout with a compose view
        setContentView(R.layout.activity_empty);
        // find the compose view object
        ComposeView composeView = findViewById(R.id.compose_view);
        // set the appropriate composable as content
        ActivitiesKt.showHomePageView(composeView, model);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if(!initialized)
        {
            new MemoryInitializer().prepareData();
            initialized = true;
        }
    }

    /**
     * Όταν πραγματοποιείται click στο ManageBorrowersActivity activity
     * ο χρήστης μεταφέρεται σε αυτό από την αρχική σελίδα.
     */
    public void manageBorrowers() {
        Intent intent = new Intent(HomePageActivity.this, ManageBorrowersActivity.class);
        startActivity(intent);
    }

    /**
     * Όταν πραγματοποιείται click στο ManageBooksActivity activity
     * ο χρήστης μεταφέρεται σε αυτό από την αρχική σελίδα.
     */
    public void manageBooks() {
        Intent intent = new Intent(HomePageActivity.this, ManageBooksActivity.class);
        startActivity(intent);
    }

    /**
     * Όταν πραγματοποιείται click στο ManageAuthorsActivity activity
     * ο χρήστης μεταφέρεται σε αυτό από την αρχική σελίδα.
     */
    public void manageAuthors() {
        Intent intent = new Intent(HomePageActivity.this, ManageAuthorsActivity.class);
        startActivity(intent);
    }

    /**
     * Όταν πραγματοποιείται click στο ManagePublishersActivity activity
     * ο χρήστης μεταφέρεται σε αυτό από την αρχική σελίδα.
     */
    public void managePublishers() {
        Intent intent = new Intent(HomePageActivity.this, ManagePublishersActivity.class);
        startActivity(intent);
    }

    /**
     * Όταν πραγματοποιείται click στο ManageBooksActivity activity
     * ο χρήστης μεταφέρεται σε αυτό από την αρχική σελίδα. Επίσης αν
     * ελέγχεται αν πρέπει να φορτωθούν τα βιβλία.
     */
    public void manageItems() {
        Intent intent = new Intent(HomePageActivity.this, ManageBooksActivity.class);
        intent.putExtra("should_load_items", 1);
        startActivity(intent);
    }

    /**
     * Όταν πραγματοποιείται click στο ManageBorrowersActivity activity
     * ο χρήστης μεταφέρεται σε αυτό από την αρχική σελίδα. Επίσης αν
     * ελέγχεται αν πρέπει να φορτωθούν τα δάνεια.
     */
    public void manageLoans() {
        Intent intent = new Intent(HomePageActivity.this, ManageBorrowersActivity.class);
        intent.putExtra("should_load_loans", 1);
        startActivity(intent);
    }

    /**
     * Όταν πραγματοποιείται click στο ManageBorrowersActivity activity
     * ο χρήστης μεταφέρεται σε αυτό από την αρχική σελίδα. Επίσης αν
     * ελέγχεται αν πρέπει να φορτωθούν οι επιστροφές.
     */
    public void manageReturns() {
        Intent intent = new Intent(HomePageActivity.this, ManageBorrowersActivity.class);
        intent.putExtra("should_load_returns", 1);
        startActivity(intent);
    }
}
