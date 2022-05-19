package com.mgiandia.library.view.Book.Search;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mgiandia.library.R;
import com.mgiandia.library.dao.Initializer;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.memorydao.MemoryInitializer;

import java.util.ArrayList;
import java.util.List;

public class BookSearchActivity extends AppCompatActivity implements BookSearchView{

    public static final String BOOK_TITLE_EXTRA = "book_title";
    public static final String AUTHOR_NAME_EXTRA = "author_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search);

        BookSearchViewModel viewModel = new ViewModelProvider(this).get(BookSearchViewModel.class);
        viewModel.getPresenter().setView(this);

        // Execute book search on activity create
        if (savedInstanceState == null){
            Initializer initializer = new MemoryInitializer();
            initializer.prepareData();
            Intent intent = getIntent();
            String titleCriterion = intent.getStringExtra(BOOK_TITLE_EXTRA);
            String authorCriterion = intent.getStringExtra(AUTHOR_NAME_EXTRA);
            viewModel.getPresenter().search(titleCriterion, authorCriterion);
        }

        // ui initialization
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Book> bookList = new ArrayList<>(viewModel.getPresenter().getSearchResult());
        recyclerView.setAdapter(new BookRecyclerViewAdapter(bookList));

    }
}