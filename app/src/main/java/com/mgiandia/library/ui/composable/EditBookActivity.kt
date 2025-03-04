package com.mgiandia.library.ui.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mgiandia.library.R
import com.mgiandia.library.memorydao.AuthorDAOMemory
import com.mgiandia.library.memorydao.PublisherDAOMemory
import com.mgiandia.library.view.Book.AddEditBook.AddEditBookViewModel

@SuppressLint("ComposableNaming")
@Composable
fun drawEditBookPage(modifier: Modifier = Modifier, viewModel: AddEditBookViewModel)
{
    Column(modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top), horizontalAlignment = Alignment.CenterHorizontally)
    {
        welcomeText(stringResource(R.string.basic_info))

        displayRow(R.string.book_title, viewModel, "title")
        Spacer(modifier = Modifier.height(16.dp)) // add space between the above element and the next

        val publishersNameList = viewModel.findAllPublisherNames()

        displayRow(R.string.book_publisher, true, viewModel, publishersNameList)
        Spacer(modifier = Modifier.height(16.dp))

        displayRow(R.string.book_isbn, viewModel, "isbn")
        Spacer(modifier = Modifier.height(16.dp))

        displayRow(R.string.book_publication, viewModel, "publication")
        Spacer(modifier = Modifier.height(16.dp))

        displayRow(R.string.book_publicationyear, viewModel, "year")
        Spacer(modifier = Modifier.height(16.dp))

        val authorsNameList = viewModel.findAllAuthorNames()

        displayRow(R.string.authors_title_text, viewModel, authorsNameList)
        Spacer(modifier = Modifier.height(16.dp))

        displayButton(R.string.complete_registration, 50, 200, viewModel)
    }
}