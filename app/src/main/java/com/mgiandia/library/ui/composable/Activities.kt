package com.mgiandia.library.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import com.mgiandia.library.ui.theme.LibraryTheme
import com.mgiandia.library.view.Author.AddEditAuthor.AddEditAuthorViewModel
import com.mgiandia.library.view.Author.AuthorDetails.AuthorDetailsViewModel
import com.mgiandia.library.view.Book.AddEditBook.AddEditBookViewModel
import com.mgiandia.library.view.Book.BookDetails.BookDetailsViewModel
import com.mgiandia.library.view.Borrower.AddEditBorrower.AddEditBorrowerViewModel
import com.mgiandia.library.view.Borrower.BorrowerDetails.BorrowerDetailsViewModel
import com.mgiandia.library.view.HomePage.HomePageViewModel
import com.mgiandia.library.view.Items.ManageItems.ManageItemsViewModel
import com.mgiandia.library.view.Loans.AddLoan.AddLoanViewModel
import com.mgiandia.library.view.Publisher.AddPublisher.AddEditPublisherViewModel
import com.mgiandia.library.view.Publisher.PublisherDetails.PublisherDetailsViewModel


fun <T> showView(composeView: ComposeView, viewModel: T, drawScreen: @Composable (Modifier, T) -> Unit)
{
    composeView.apply {
        setContent {
            LibraryTheme {
                Scaffold(modifier = Modifier.fillMaxSize())
                {
                    innerPadding -> Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray))
                    drawScreen(Modifier.padding(innerPadding), viewModel)
                }
            }
        }
    }
}

fun showHomePageView(composeView: ComposeView, viewModel: HomePageViewModel) = showView(composeView, viewModel) { modifier, vm -> drawHomePage(modifier, vm) }

fun drawEditBookPage(composeView: ComposeView, viewModel: AddEditBookViewModel) = showView(composeView, viewModel) { modifier, vm -> drawEditBookPage(modifier, vm) }

fun showAddEditAuthorView(composeView: ComposeView, viewModel: AddEditAuthorViewModel) = showView(composeView, viewModel) { modifier, vm -> drawAddEditAuthorPage(modifier, vm) }

fun showAddEditPublisherView(composeView: ComposeView, viewModel: AddEditPublisherViewModel) = showView(composeView, viewModel) { modifier, vm -> drawAddEditPublisherPage(modifier, vm) }

fun showAddEditBorrowerView(composeView: ComposeView, viewModel: AddEditBorrowerViewModel) = showView(composeView, viewModel) { modifier, vm -> drawAddEditBorrower(modifier, vm) }

fun showBookDetailsView(composeView: ComposeView, viewModel: BookDetailsViewModel) = showView(composeView, viewModel) { modifier, vm -> drawBookDetailsPage(modifier, vm) }

fun showAddLoanView(composeView: ComposeView, viewModel: AddLoanViewModel) = showView(composeView, viewModel) { modifier, vm -> drawAddLoan(modifier, vm) }

fun showAuthorDetailsView(composeView: ComposeView, viewModel: AuthorDetailsViewModel) = showView(composeView, viewModel) { modifier, vm -> drawAuthorDetailsPage(modifier, vm) }

fun showManageItemsView(composeView: ComposeView, viewModel: ManageItemsViewModel) = showView(composeView, viewModel) { modifier, vm -> drawItemListScreen(modifier, vm) }

fun showBorrowerDetailsView(composeView: ComposeView, viewModel: BorrowerDetailsViewModel) = showView(composeView, viewModel) { modifier, vm -> drawBorrowerDetailsPage(modifier, vm) }

fun showPublisherDetailsView(composeView: ComposeView, viewModel: PublisherDetailsViewModel) = showView(composeView, viewModel) { modifier, vm -> drawPublisherDetailsPage(modifier, vm) }


fun showEmptyView(composeView: ComposeView)
{
    composeView.apply {
        setContent {
            Greeting("Hello world")
        }
    }
}



//fun showHomePageView(composeView: ComposeView, viewModel: HomePageViewModel)
//{
//    composeView.apply {
//        setContent {
//            // TODO: Reuse the code involving LibraryTheme, Scaffold etc
//            LibraryTheme {
//                Scaffold(modifier = Modifier.fillMaxSize())
//                { innerPadding ->
//                    Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray))
//
//                    drawHomePage(modifier = Modifier.padding(innerPadding), viewModel)
//                }
//            }
//        }
//    }
//}
//
//fun drawEditBookPage(composeView: ComposeView, viewModel: AddEditBookViewModel)
//{
//    composeView.apply {
//        setContent {
//            LibraryTheme {
//                Scaffold(modifier = Modifier.fillMaxSize())
//                { innerPadding ->
//                    Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray))
//
//                    drawEditBookPage(modifier = Modifier.padding(innerPadding), viewModel)
//                }
//            }
//        }
//    }
//}
//
//fun showAddEditAuthorView(composeView: ComposeView, viewModel: AddEditAuthorViewModel)
//{
//    composeView.apply {
//        setContent {
//            LibraryTheme {
//                Scaffold(modifier = Modifier.fillMaxSize())
//                { innerPadding ->
//                    Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray))
//
//                    drawAddEditAuthorPage(modifier = Modifier.padding(innerPadding), viewModel)
//                }
//            }
//        }
//    }
//}
//
//fun showAddEditPublisherView(composeView: ComposeView, viewModel: AddEditPublisherViewModel)
//{
//    composeView.apply {
//        setContent {
//            LibraryTheme {
//                Scaffold(modifier = Modifier.fillMaxSize())
//                { innerPadding ->
//                    Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray))
//
//                    drawAddEditPublisherPage(modifier = Modifier.padding(innerPadding), viewModel)
//                }
//            }
//        }
//    }
//}
//
//fun showAddEditBorrowerView(composeView: ComposeView, viewModel: AddEditBorrowerViewModel)
//{
//    composeView.apply {
//        setContent {
//            LibraryTheme {
//                Scaffold(modifier = Modifier.fillMaxSize())
//                { innerPadding ->
//                    Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray))
//
//                    drawAddEditBorrower(modifier = Modifier.padding(innerPadding), viewModel)
//                }
//            }
//        }
//    }
//}
//
//fun showBookDetailsView(composeView: ComposeView, viewModel: BookDetailsViewModel)
//{
//    composeView.apply {
//        setContent {
//            LibraryTheme {
//                Scaffold(modifier = Modifier.fillMaxSize())
//                { innerPadding ->
//                    Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray))
//
//                    drawBookDetailsPage(modifier = Modifier.padding(innerPadding), viewModel)
//                }
//            }
//        }
//    }
//}
//
//fun showAddLoanView(composeView: ComposeView, viewModel: AddLoanViewModel)
//{
//    composeView.apply {
//        setContent {
//            LibraryTheme {
//                Scaffold(modifier = Modifier.fillMaxSize())
//                { innerPadding ->
//                    Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray))
//
//                    drawAddLoan(modifier = Modifier.padding(innerPadding), viewModel)
//                }
//            }
//        }
//    }
//}
//
//fun showAuthorDetailsView(composeView: ComposeView, viewModel: AuthorDetailsViewModel)
//{
//    composeView.apply {
//        setContent {
//            LibraryTheme {
//                Scaffold(modifier = Modifier.fillMaxSize())
//                { innerPadding ->
//                    Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray))
//
//                    drawAuthorDetailsPage(modifier = Modifier.padding(innerPadding), viewModel)
//                }
//            }
//        }
//    }
//}
//
//fun showManageItemsView(composeView: ComposeView, viewModel: ManageItemsViewModel)
//{
//    composeView.apply {
//        setContent {
//            LibraryTheme {
//                Scaffold(modifier = Modifier.fillMaxSize())
//                { innerPadding ->
//                    Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray))
//
//                    drawItemListScreen(modifier = Modifier.padding(innerPadding), viewModel)
//                }
//            }
//        }
//    }
//}
//
//fun showBorrowerDetailsView(composeView: ComposeView, viewModel: BorrowerDetailsViewModel)
//{
//    composeView.apply {
//        setContent {
//            LibraryTheme {
//                Scaffold(modifier = Modifier.fillMaxSize())
//                { innerPadding ->
//                    Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray))
//
//                    drawBorrowerDetailsPage(modifier = Modifier.padding(innerPadding), viewModel)
//                }
//            }
//        }
//    }
//}
//
//fun showPublisherDetailsView(composeView: ComposeView, viewModel: PublisherDetailsViewModel)
//{
//    composeView.apply {
//        setContent {
//            LibraryTheme {
//                Scaffold(modifier = Modifier.fillMaxSize())
//                { innerPadding ->
//                    Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray))
//
//                    drawPublisherDetailsPage(modifier = Modifier.padding(innerPadding), viewModel)
//                }
//            }
//        }
//    }
//}