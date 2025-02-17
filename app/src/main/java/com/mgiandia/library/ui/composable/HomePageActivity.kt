package com.mgiandia.library.ui.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mgiandia.library.R
import com.mgiandia.library.view.Author.AddEditAuthor.AddEditAuthorViewModel
import com.mgiandia.library.view.Book.AddEditBook.AddEditBookViewModel
import com.mgiandia.library.view.Borrower.AddEditBorrower.AddEditBorrowerViewModel
import com.mgiandia.library.view.HomePage.HomePageViewModel
import com.mgiandia.library.view.Publisher.AddPublisher.AddEditPublisherViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun drawHomePage(modifier: Modifier = Modifier, viewModel: HomePageViewModel)
{
    Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally)
    {
        displayLibraryIcon()
        welcomeText(stringResource(R.string.welcome_message))
        displayButton(R.string.manage_borrowers, 50, 400, viewModel)

        Row()
        {
            displayButton(R.string.manage_books, 50, 200, viewModel)
            displayButton(R.string.manage_items, 50, 200, viewModel)
        }

        Row()
        {
            displayButton(R.string.manage_loans, 50, 200, viewModel)
            displayButton(R.string.manage_returns, 50, 200, viewModel)
        }

        Row()
        {
            displayButton(R.string.manage_authors, 50, 200, viewModel)
            displayButton(R.string.manage_publishers, 50, 200, viewModel)
        }
    }
}