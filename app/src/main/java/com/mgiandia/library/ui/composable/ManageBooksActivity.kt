package com.mgiandia.library.ui.composable

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mgiandia.library.domain.Book
import com.mgiandia.library.view.Book.ManageBooks.ManageBooksViewModel

@SuppressLint("ComposableNaming")
@Composable
fun drawManageBooksPage(modifier: Modifier = Modifier, viewModel: ManageBooksViewModel)
{
    val allBooks: ArrayList<Book> = ArrayList(viewModel.allBooks)
    drawBooksListScreen(modifier, viewModel, allBooks)
}

@SuppressLint("ComposableNaming")
@Composable
fun drawManageBooksPageSearch(modifier: Modifier = Modifier, viewModel: ManageBooksViewModel)
{
    val books = viewModel.books.value
    if (books != null)
    {
        drawBooksListScreen(modifier, viewModel, books)
    }
}