package com.mgiandia.library.view.Borrower.BorrowerDetails;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mgiandia.library.R;
import com.mgiandia.library.memorydao.BorrowerDAOMemory;
import com.mgiandia.library.memorydao.LoanDAOMemory;
import com.mgiandia.library.view.Borrower.AddEditBorrower.AddEditBorrowerActivity;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class BorrowerDetailsActivity extends AppCompatActivity implements BorrowerDetailsView
{
    BorrowerDetailsPresenter presenter;

    public void startEdit(int borrowerID)
    {
        Intent intent = new Intent(this, AddEditBorrowerActivity.class);
        intent.putExtra("borrower_id", borrowerID);
        startActivityForResult(intent, 2);
    }

    public void startDelete(String title, String message)
    {
        new AlertDialog.Builder(BorrowerDetailsActivity.this).setCancelable(true).setTitle(title).setMessage(message)
                .setPositiveButton(R.string.yes_delete, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        presenter.onDoDeleteAndFinish();
                    }
                })
                .setNegativeButton(R.string.cancel, null).create().show();
    }

    public void doDeleteAndFinish(String message)
    {
        Intent intent = new Intent();
        intent.putExtra("message_to_toast", message);
        setResult(RESULT_OK, intent);
        finish();
    }

    public int getAttachedBorrowerID()
    {
        return this.getIntent().hasExtra("borrower_id") ? this.getIntent().getExtras().getInt("borrower_id") : null;
    }

    public void setID(String value)
    {
        ((TextView)findViewById(R.id.text_user_id)).setText(value);
    }

    public void setFirstName(String value)
    {
        ((TextView)findViewById(R.id.text_first_name)).setText(value);
    }

    public void setLastName(String value)
    {
        ((TextView)findViewById(R.id.text_last_name)).setText(value);
    }

    public void setCategory(String value)
    {
        ((TextView)findViewById(R.id.text_category)).setText(value);
    }

    public void setPhone(String value)
    {
        ((TextView)findViewById(R.id.text_telephone)).setText(value);
    }

    public void setEmail(String value)
    {
        ((TextView)findViewById(R.id.text_email)).setText(value);
    }

    public void setCountry(String value)
    {
        ((TextView)findViewById(R.id.text_country)).setText(value);
    }

    public void setAddressCity(String value)
    {
        ((TextView)findViewById(R.id.text_city)).setText(value);
    }

    public void setAddressStreet(String value)
    {
        ((TextView)findViewById(R.id.text_street)).setText(value);
    }

    public void setAddressNumber(String value)
    {
        ((TextView)findViewById(R.id.text_number)).setText(value);
    }

    public void setAddressPostalCode(String value)
    {
        ((TextView)findViewById(R.id.text_zip)).setText(value);
    }

    public void setPageName(String value)
    {
        getSupportActionBar().setTitle(value);
    }

    public void showToast(String value)
    {
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrower_details);
        presenter = new BorrowerDetailsPresenter(this, new BorrowerDAOMemory(), new LoanDAOMemory());

        findViewById(R.id.edit_user_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                presenter.onStartEditButtonClick();
            }
        });

        findViewById(R.id.delete_user_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                presenter.onStartDeleteButtonClick();
            }
        });
    }

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
