package com.mgiandia.library.view.HomePage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mgiandia.library.R;
import com.mgiandia.library.memorydao.MemoryInitializer;
import com.mgiandia.library.view.Author.ManageAuthors.ManageAuthorsActivity;
import com.mgiandia.library.view.Book.ManageBooks.ManageBooksActivity;
import com.mgiandia.library.view.Borrower.ManageBorrowers.ManageBorrowersActivity;
import com.mgiandia.library.view.Publisher.ManagePublishers.ManagePublishersActivity;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class HomePageActivity extends AppCompatActivity implements HomePageView
{
    private static boolean initialized = false;

    /**
     * Δημιουργεί to layout και αρχικοποιεί
     * το activity.
     * @param savedInstanceState το Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        final HomePagePresenter presenter = new HomePagePresenter(this);

        findViewById(R.id.manage_borrowers_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onManageBorrowers();
            }
        });

        findViewById(R.id.manage_books_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onManageBooks();
            }
        });

        findViewById(R.id.manage_authors_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onManageAuthors();
            }
        });

        findViewById(R.id.manage_publishers_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onManagePublishers();
            }
        });

        findViewById(R.id.manage_items_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onManageItems();
            }
        });

        findViewById(R.id.manage_loans_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onManageLoans();
            }
        });

        findViewById(R.id.manage_returns_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onManageReturns();
            }
        });

        if(!initialized)
        {
            new MemoryInitializer().prepareData();
            initialized = true;
        }
    }

    /**
     * Όταν πραγματοποιείται click στο ManageBorrowersActivity activity
     * ο χρηστης μεταφέρεται σε αυτό από την αρχική σελίδα.
     */
    public void manageBorrowers()
    {
        Intent intent = new Intent(HomePageActivity.this, ManageBorrowersActivity.class);
        startActivity(intent);
    }

    /**
     * Όταν πραγματοποιείται click στο ManageBooksActivity activity
     * ο χρηστης μεταφέρεται σε αυτό από την αρχική σελίδα.
     */
    public void manageBooks()
    {
        Intent intent = new Intent(HomePageActivity.this, ManageBooksActivity.class);
        startActivity(intent);
    }

    /**
     * Όταν πραγματοποιείται click στο ManageAuthorsActivity activity
     * ο χρηστης μεταφέρεται σε αυτό από την αρχική σελίδα.
     */
    public void manageAuthors()
    {
        Intent intent = new Intent(HomePageActivity.this, ManageAuthorsActivity.class);
        startActivity(intent);
    }

    /**
     * Όταν πραγματοποιείται click στο ManagePublishersActivity activity
     * ο χρηστης μεταφέρεται σε αυτό από την αρχική σελίδα.
     */
    public void managePublishers()
    {
        Intent intent = new Intent(HomePageActivity.this, ManagePublishersActivity.class);
        startActivity(intent);
    }

    /**
     * Όταν πραγματοποιείται click στο ManageBooksActivity activity
     * ο χρηστης μεταφέρεται σε αυτό από την αρχική σελίδα. Επίσης αν
     * ελέχεται αν πρέπει να φορτωθούν τα βιβλία.
     */
    public void manageItems()
    {
        Intent intent = new Intent(HomePageActivity.this, ManageBooksActivity.class);
        intent.putExtra("should_load_items", 1);
        startActivity(intent);
    }

    /**
     * Όταν πραγματοποιείται click στο ManageBorrowersActivity activity
     * ο χρηστης μεταφέρεται σε αυτό από την αρχική σελίδα. Επίσης αν
     * ελέχεται αν πρέπει να φορτωθούν τα δάνια.
     */
    public void manageLoans()
    {
        Intent intent = new Intent(HomePageActivity.this, ManageBorrowersActivity.class);
        intent.putExtra("should_load_loans", 1);
        startActivity(intent);
    }

    /**
     * Όταν πραγματοποιείται click στο ManageBorrowersActivity activity
     * ο χρηστης μεταφέρεται σε αυτό από την αρχική σελίδα. Επίσης αν
     * ελέχεται αν πρέπει να φορτωθούν οι επιστροφές.
     */
    public void manageReturns()
    {
        Intent intent = new Intent(HomePageActivity.this, ManageBorrowersActivity.class);
        intent.putExtra("should_load_returns", 1);
        startActivity(intent);
    }
}
