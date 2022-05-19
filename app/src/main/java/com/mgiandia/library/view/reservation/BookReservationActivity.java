package com.mgiandia.library.view.reservation;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.mgiandia.library.R;

public class BookReservationActivity extends AppCompatActivity implements BookReservationView {

    private BookReservationViewModel viewModel;
    private Button btnSearchBook;
    private Button btnReserveBook;
    private EditText edtBookTitle;
    private EditText edtAuthorName;
    private EditText edtBorrowerId;
    private TextView txtReservationStatus;
    private TextView txtBookDetails;

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

    private void searchBook(){
        String bookTitle = edtBookTitle.getText().toString();
        String authorName = edtAuthorName.getText().toString();
        viewModel.getPresenter().search(bookTitle, authorName);
    }

    private void reserveBook(){
        String borrowerId = edtBorrowerId.getText().toString();
        viewModel.getPresenter().submitReservationRequest(borrowerId);
    }
}