package com.mgiandia.library.view.Loans.AddLoan;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.platform.ComposeView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import java.util.List;
import java.util.Objects;
import com.mgiandia.library.R;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.ui.composable.ActivitiesKt;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */
public class AddLoansActivity extends AppCompatActivity implements AddLoansView
{
    int selectedBookID;
    boolean bookChecked = false;
    String borrowerFullName;

    public void setSelectedBookID(int selectedBookID)
    {
        this.selectedBookID = selectedBookID;
        bookChecked = true;
    }

    @Override
    public int getSelectedBookId()
    {
        return selectedBookID;
    }

    @Override
    public int getAttachedBorrowerID()
    {
        return this.getIntent().hasExtra("borrower_id") ? Objects.requireNonNull(this.getIntent().getExtras()).getInt("borrower_id") : -1;
    }

    @Override
    public void setBorrowerId(String value)
    {
    }

    @Override
    public void setPageName(String value)
    {
        Objects.requireNonNull(getSupportActionBar()).setTitle(value);
    }

    @Override
    public void successfullyAddLoanAndFinishActivity(String message)
    {
        Intent retData = new Intent();
        retData.putExtra("message_to_toast", message);
        setResult(RESULT_OK, retData);
        finish();
    }

    @Override
    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(message).setPositiveButton(R.string.ok, null).create().show();
    }

    @Override
    public void showAlert(String title, String message)
    {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(message).setPositiveButton(R.string.ok, null).create().show();
    }

    @Override
    public void setBookList(List<String> names)
    {
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
        setContentView(R.layout.activity_add_loan);

        AddLoanViewModel model = new ViewModelProvider((ViewModelStoreOwner) this).get(AddLoanViewModel.class);
        AddLoansPresenter presenter = model.getPresenter(this);

        ComposeView composeView = findViewById(R.id.compose_view);
        ActivitiesKt.showAddLoanView(composeView, model);

        int borrowerID = getAttachedBorrowerID();
        Borrower borrower = model.findBorrower(borrowerID);

        if (borrower != null)
        {
            model.setBorrowerFullName(borrower.getFirstName() + " " + borrower.getLastName());
        }

        model.getSelectedBookID().observe((LifecycleOwner) this, value ->
        {
            if (value != null)
            {
                setSelectedBookID(value);
            }
        });

        model.observeClicks(this, buttonTextResId ->
        {
            if (buttonTextResId != null && bookChecked)
            {
                presenter.onSaveLoan();
            }
        });
    }
}