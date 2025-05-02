package com.mgiandia.library.ui.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mgiandia.library.R
import com.mgiandia.library.view.Author.AuthorDetails.AuthorDetailsViewModel

@SuppressLint("ComposableNaming")
@Composable
fun drawAuthorDetailsPage(modifier: Modifier = Modifier, viewModel: AuthorDetailsViewModel)
{
    Column(modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top), horizontalAlignment = Alignment.CenterHorizontally)
    {
        welcomeText(stringResource(R.string.basic_info))
        displayRow(R.string.user_id, viewModel, "code")
        displayRow(R.string.first_name, viewModel, "firstName")
        displayRow(R.string.last_name, viewModel, "lastName")
        displayRow(R.string.books_written, viewModel, "booksNum")
        welcomeText(stringResource(R.string.actions))
        displayButton(R.string.edit_user, stringResource(R.string.edit_user), 50, 200, viewModel)
        displayButton(R.string.show_books, stringResource(R.string.show_books), 50, 200, viewModel)
    }
}