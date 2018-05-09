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

import java.util.ArrayList;

public class BookSearchActivity extends AppCompatActivity implements BookAdapter.ItemSelectionListener<Book> {

    public static final String BOOK_ID_EXTRA = "book_id";
    public static final String BOOK_DESCRIPTION_EXTRA = "book_description";
    RecyclerView recyclerView;
    private BookAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();
        BookDAO bookDAO = new BookDAOMemory();
        // specify an adapter (see also next example)
        mAdapter = new BookAdapter(bookDAO.findAll());
        // register the current activity as listener for book selection events
        mAdapter.setBookSelectionListener(this);

        recyclerView.setAdapter(mAdapter);

    }

    /**
     * The method will be called by the adapter, whenever the user clicks on a list item
     * @param item
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
