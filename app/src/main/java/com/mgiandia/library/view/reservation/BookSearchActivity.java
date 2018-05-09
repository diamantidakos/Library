package com.mgiandia.library.view.reservation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mgiandia.library.R;
import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.dao.Initializer;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.memorydao.BookDAOMemory;
import com.mgiandia.library.memorydao.MemoryInitializer;

import java.util.ArrayList;

public class BookSearchActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
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

        recyclerView.setAdapter(mAdapter);

    }
}
