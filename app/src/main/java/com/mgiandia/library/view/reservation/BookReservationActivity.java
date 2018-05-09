package com.mgiandia.library.view.reservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mgiandia.library.R;
import com.mgiandia.library.presenter.BookReservationPresenter;
import com.mgiandia.library.presenter.BookReservationView;

public class BookReservationActivity extends AppCompatActivity implements BookReservationView {

    public static final String BOOK_TITLE_EXTRA = "book_title";
    public static final String AUTHOR_NAME_EXTRA = "author_name";
    public static final int REQUEST_CODE_BOOK_SEARCH = 1;
    Button btnSearchBook;
    EditText edtBookTitle;
    EditText edtAuthorName;
    Button btnReserveBook;
    EditText edtBorrowerId;
    TextView txtReservationStatus;

    BookReservationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_reservation);

        presenter = new BookReservationPresenter(this);

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
                searchBook(bookTitle, authorName);
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

    /**
     * Start BookSearchActivity for search and book selection
     * @param bookTitle
     * @param authorName
     */
    public void searchBook(String bookTitle, String authorName){
        Intent intent = new Intent(this, BookSearchActivity.class);
        intent.putExtra(BOOK_TITLE_EXTRA, bookTitle);
        intent.putExtra(AUTHOR_NAME_EXTRA, authorName);
        startActivityForResult(intent, REQUEST_CODE_BOOK_SEARCH);
    }


    /**
     * Handle the result from BookSearchActivity
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_BOOK_SEARCH){
            if (resultCode == RESULT_OK){

                int bookId = data.getIntExtra(BookSearchActivity.BOOK_ID_EXTRA, -1);
                String bookDescription = data.getStringExtra(BookSearchActivity.BOOK_DESCRIPTION_EXTRA);

                if (bookId == -1){
                    Toast.makeText(this, "Book not found", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Selected book: " + bookDescription, Toast.LENGTH_SHORT).show();
                    presenter.selectBook(bookId);
                    // presenter load book with id
                }

            }
        }

    }

    @Override
    public void showError(String errorMsg) {
        // show error
    }
}

