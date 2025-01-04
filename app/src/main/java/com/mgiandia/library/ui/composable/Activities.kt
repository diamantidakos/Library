package com.mgiandia.library.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import com.mgiandia.library.ui.theme.LibraryTheme
import com.mgiandia.library.view.Author.AddEditAuthor.AddEditAuthorViewModel
import com.mgiandia.library.view.Book.AddEditBook.AddEditBookViewModel
import com.mgiandia.library.view.Borrower.AddEditBorrower.AddEditBorrowerViewModel
import com.mgiandia.library.view.HomePage.HomePageViewModel
import com.mgiandia.library.view.Publisher.AddPublisher.AddEditPublisherViewModel

fun showEmptyView(composeView: ComposeView)
{
    composeView.apply {
        setContent {
            Greeting("Hello world")
        }
    }
}

fun showHomePageView(composeView: ComposeView, viewModel: HomePageViewModel)
{
    composeView.apply {
        setContent {
            // TODO: Reuse the code involving LibraryTheme, Scaffold etc
            LibraryTheme {
                Scaffold(modifier = Modifier.fillMaxSize())
                { innerPadding ->
                    Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray))

                    drawHomePage(modifier = Modifier.padding(innerPadding), viewModel)
                }
            }
        }
    }
}

fun drawEditBookPage(composeView: ComposeView, viewModel: AddEditBookViewModel)
{
    composeView.apply {
        setContent {
            LibraryTheme {
                Scaffold(modifier = Modifier.fillMaxSize())
                { innerPadding ->
                    Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray))

                    drawEditBookPage(modifier = Modifier.padding(innerPadding), viewModel)
                }
            }
        }
    }
}

fun showAddEditAuthorView(composeView: ComposeView, viewModel: AddEditAuthorViewModel)
{
    composeView.apply {
        setContent {
            LibraryTheme {
                Scaffold(modifier = Modifier.fillMaxSize())
                { innerPadding ->
                    Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray))

                    drawAddEditAuthorPage(modifier = Modifier.padding(innerPadding), viewModel)
                }
            }
        }
    }
}

fun showAddEditPublisherView(composeView: ComposeView, viewModel: AddEditPublisherViewModel)
{
    composeView.apply {
        setContent {
            LibraryTheme {
                Scaffold(modifier = Modifier.fillMaxSize())
                { innerPadding ->
                    Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray))

                    drawAddEditPublisherPage(modifier = Modifier.padding(innerPadding), viewModel)
                }
            }
        }
    }
}

fun showAddEditBorrowerView(composeView: ComposeView, viewModel: AddEditBorrowerViewModel)
{
    composeView.apply {
        setContent {
            LibraryTheme {
                Scaffold(modifier = Modifier.fillMaxSize())
                { innerPadding ->
                    Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray))

                    drawAddEditBorrower(modifier = Modifier.padding(innerPadding), viewModel)
                }
            }
        }
    }
}