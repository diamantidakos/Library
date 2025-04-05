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
import com.mgiandia.library.view.Author.ManageAuthors.ManageAuthorsViewModel
import com.mgiandia.library.view.Book.AddEditBook.AddEditBookViewModel
import com.mgiandia.library.view.Book.BookDetails.BookDetailsViewModel
import com.mgiandia.library.view.Book.ManageBooks.ManageBooksViewModel
import com.mgiandia.library.view.Borrower.AddEditBorrower.AddEditBorrowerViewModel
import com.mgiandia.library.view.Borrower.BorrowerDetails.BorrowerDetailsViewModel
import com.mgiandia.library.view.Borrower.ManageBorrowers.ManageBorrowersViewModel
import com.mgiandia.library.view.HomePage.HomePageViewModel
import com.mgiandia.library.view.Items.ManageItems.ManageItemsViewModel
import com.mgiandia.library.view.Loans.AddLoan.AddLoanViewModel
import com.mgiandia.library.view.Loans.ManageLoans.ManageLoansViewModel
import com.mgiandia.library.view.Publisher.AddPublisher.AddEditPublisherViewModel
import com.mgiandia.library.view.Publisher.ManagePublishers.ManagePublishersViewModel
import com.mgiandia.library.view.Publisher.PublisherDetails.PublisherDetailsViewModel
import com.mgiandia.library.view.Returns.ManageReturns.ManageReturnsViewModel


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

fun showManageItemsView(composeView: ComposeView, viewModel: ManageItemsViewModel) = showView(composeView, viewModel) { modifier, vm -> drawManageItemsPage(modifier, vm) }

fun showManageItemsViewSearch(composeView: ComposeView, viewModel: ManageItemsViewModel) = showView(composeView, viewModel) { modifier, vm -> drawManageItemsPageSearch(modifier, vm) }

fun showBorrowerDetailsView(composeView: ComposeView, viewModel: BorrowerDetailsViewModel) = showView(composeView, viewModel) { modifier, vm -> drawBorrowerDetailsPage(modifier, vm) }

fun showPublisherDetailsView(composeView: ComposeView, viewModel: PublisherDetailsViewModel) = showView(composeView, viewModel) { modifier, vm -> drawPublisherDetailsPage(modifier, vm) }

fun showManageBooksView(composeView: ComposeView, viewModel: ManageBooksViewModel) = showView(composeView, viewModel) { modifier, vm -> drawManageBooksPage(modifier, vm) }

fun showManageBooksViewSearch(composeView: ComposeView, viewModel: ManageBooksViewModel) = showView(composeView, viewModel) { modifier, vm -> drawManageBooksPageSearch(modifier, vm) }

fun showManageAuthorsView(composeView: ComposeView, viewModel: ManageAuthorsViewModel) = showView(composeView, viewModel) { modifier, vm -> drawManageAuthorsPage(modifier, vm) }

fun showManageAuthorsViewSearch(composeView: ComposeView, viewModel: ManageAuthorsViewModel) = showView(composeView, viewModel) { modifier, vm -> drawManageAuthorsPageSearch(modifier, vm) }

fun showManageBorrowersView(composeView: ComposeView, viewModel: ManageBorrowersViewModel) = showView(composeView, viewModel) { modifier, vm -> drawManageBorrowersPage(modifier, vm) }

fun showManageBorrowersViewSearch(composeView: ComposeView, viewModel: ManageBorrowersViewModel) = showView(composeView, viewModel) { modifier, vm -> drawManageBorrowersPageSearch(modifier, vm) }

fun showManagePublishersView(composeView: ComposeView, viewModel: ManagePublishersViewModel) = showView(composeView, viewModel) { modifier, vm -> drawManagePublishersPage(modifier, vm) }

fun showManagePublishersViewSearch(composeView: ComposeView, viewModel: ManagePublishersViewModel) = showView(composeView, viewModel) { modifier, vm -> drawManagePublishersPageSearch(modifier, vm) }

fun showManageLoansView(composeView: ComposeView, viewModel: ManageLoansViewModel) = showView(composeView, viewModel) { modifier, vm -> drawManageLoansPage(modifier, vm) }

fun showManageLoansViewSearch(composeView: ComposeView, viewModel: ManageLoansViewModel) = showView(composeView, viewModel) { modifier, vm -> drawManageLoansPageSearch(modifier, vm) }

fun showManageReturnsView(composeView: ComposeView, viewModel: ManageReturnsViewModel) = showView(composeView, viewModel) { modifier, vm -> drawManageReturnsPage(modifier, vm) }

fun showManageReturnsViewSearch(composeView: ComposeView, viewModel: ManageReturnsViewModel) = showView(composeView, viewModel) { modifier, vm -> drawManageReturnsPageSearch(modifier, vm) }

fun showEmptyView(composeView: ComposeView)
{
    composeView.apply {
        setContent {
            Greeting("Hello world")
        }
    }
}