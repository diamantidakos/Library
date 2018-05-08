package com.mgiandia.library.view.reservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mgiandia.library.R;

public class BookReservationActivity extends AppCompatActivity {

    Button btnSearchBook;
    EditText edtBookTitle;
    EditText edtAuthorName;
    Button btnReserveBook;
    EditText edtBorrowerId;
    TextView txtReservationStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_reservation);

        // Πρόσβαση σε αντικείμενα της διεπαφής χρήστη
        btnSearchBook = findViewById(R.id.btn_search_book);
        btnReserveBook = findViewById(R.id.btn_reserve_book);
        edtBookTitle = findViewById(R.id.edt_book_title);
        edtAuthorName = findViewById(R.id.edt_author_name);
        edtBorrowerId = findViewById(R.id.edt_borrower_id);
        txtReservationStatus = findViewById(R.id.txt_reservation_status);

        // Αρχικοποίηση στοιχείων UI
        txtReservationStatus.setText("");

        // Ανάθεση χειριστών γεγονότων (click listeners)
        btnSearchBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookTitle = edtBookTitle.getText().toString();
                String authorName = edtAuthorName.getText().toString();
                showBookSearchResults();
                // κλήση μεθόδου searchBook στον presenter με παραμέτρους
                // τα στοιχεία αναζήτησης
            }
        });

        btnReserveBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String borrowerId = edtBorrowerId.getText().toString();
                // κλήση μεθόδου reserve στον presenter με παραμέτρο
                // borrowerId.
            }
        });

    }

    public void showBookSearchResults(){
        Intent intent = new Intent(this, BookSearchActivity.class);
        startActivity(intent);
    }

}

