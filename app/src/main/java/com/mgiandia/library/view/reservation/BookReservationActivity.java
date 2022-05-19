package com.mgiandia.library.view.reservation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.mgiandia.library.R;
import com.mgiandia.library.view.Book.Search.BookSearchActivity;

public class BookReservationActivity extends AppCompatActivity implements BookReservationView {

    private BookReservationViewModel viewModel;
    private Button btnSearchBook;
    private Button btnReserveBook;
    private EditText edtBookTitle;
    private EditText edtAuthorName;
    private EditText edtBorrowerId;
    private TextView txtReservationStatus;
    private TextView txtBookDetails;

    ActivityResultLauncher<Intent> searchForBook =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),

                    new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        viewModel.getPresenter()
                                .selectBook(intent.getIntExtra(
                                        BookSearchActivity.BOOK_ID_RESULT, -1));
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_reservation);

        viewModel = new ViewModelProvider(this).get(BookReservationViewModel.class);
        BookReservationPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        // Πρόσβαση σε αντικείμενα της διεπαφής χρήστη
        btnSearchBook = findViewById(R.id.btnSearchBook);
        btnReserveBook = findViewById(R.id.btnBookReservation);
        edtBookTitle = findViewById(R.id.edtBookTitle);
        edtAuthorName = findViewById(R.id.edtAuthorName);
        edtBorrowerId = findViewById(R.id.edtBorrowerId);
        txtReservationStatus = findViewById(R.id.txtReservationStatus);
        txtBookDetails = findViewById(R.id.txtBookDetails);
        // Ανάθεση χειριστών γεγονότων (click listeners)
        btnSearchBook.setOnClickListener(v -> searchBook());
        btnReserveBook.setOnClickListener(v -> reserveBook());
    }

    private void searchBook() {
        String bookTitle = edtBookTitle.getText().toString();
        String authorName = edtAuthorName.getText().toString();
        viewModel.getPresenter().search(bookTitle, authorName);
    }

    private void reserveBook() {
        String borrowerId = edtBorrowerId.getText().toString();
        viewModel.getPresenter().submitReservationRequest(borrowerId);
    }

    @Override
    public void showSearchDialog(String bookTitle, String authorName) {
        Intent intent = new Intent(this, BookSearchActivity.class);
        intent.putExtra(BookSearchActivity.BOOK_TITLE_EXTRA, bookTitle);
        intent.putExtra(BookSearchActivity.AUTHOR_NAME_EXTRA, authorName);
        searchForBook.launch(intent);
    }

    @Override
    public void showError(String msg) {
        txtReservationStatus.setText(msg);
    }

    @Override
    public void showStatus(String msg) {
        txtReservationStatus.setText(msg);
    }
}