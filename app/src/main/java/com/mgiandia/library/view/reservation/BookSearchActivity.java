package com.mgiandia.library.view.reservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.mgiandia.library.R;
import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.dao.Initializer;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.memorydao.BookDAOMemory;
import com.mgiandia.library.memorydao.MemoryInitializer;
import com.mgiandia.library.presenter.BookSearchPresenter;
import com.mgiandia.library.presenter.ItemSelectionListener;

import java.util.ArrayList;
import java.util.Set;

public class BookSearchActivity extends AppCompatActivity
        implements ItemSelectionListener<Book> {

    public static final String BOOK_ID_EXTRA = "book_id";
    public static final String BOOK_DESCRIPTION_EXTRA = "book_description";
    RecyclerView recyclerView;
    private BookAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private BookSearchPresenter bookSearchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search);

        Intent intent = getIntent();

        String title = intent.getStringExtra(BookReservationActivity.BOOK_TITLE_EXTRA);
        String authorName = intent.getStringExtra(BookReservationActivity.AUTHOR_NAME_EXTRA);


        bookSearchPresenter = new BookSearchPresenter();
        Set<Book> result = bookSearchPresenter.searchBooks(title, authorName);

        // Update UI with the result

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new BookAdapter(new ArrayList<Book>(result));
        recyclerView.setAdapter(mAdapter);
        // register the current activity as listener for book selection events
        mAdapter.setBookSelectionListener(this);
    }

    /**
     * The method will be called by the adapter, whenever the user clicks on a list item
     * @param item
     *
     *
     */
    @Override
    public void onItemSelected(Book item) {

        // return result to calling Activity
        Toast.makeText(this, "Selected book:" + item.getTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra(BOOK_ID_EXTRA, item.getId());
        intent.putExtra(BOOK_DESCRIPTION_EXTRA, item.toString());
        setResult(RESULT_OK, intent);
        // close activity
        finish();

    }
}
