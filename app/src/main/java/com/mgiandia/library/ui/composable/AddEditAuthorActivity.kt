package com.mgiandia.library.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mgiandia.library.R
import com.mgiandia.library.view.Author.AddEditAuthor.AddEditAuthorViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun drawAddEditAuthorPage(modifier: Modifier = Modifier, viewModel: AddEditAuthorViewModel)
{
    var text by remember { mutableStateOf("") }

    Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally)
    {
        welcomeText(stringResource(R.string.basic_info))

        Row()
        {
            welcomeText(stringResource(R.string.first_name))
            OutlinedTextField(value = text, onValueChange = { text = it }, label = { Text(stringResource(R.string.first_name)) })
        }

        Row()
        {
            welcomeText(stringResource(R.string.last_name))
            OutlinedTextField(value = text, onValueChange = { text = it }, label = { Text(stringResource(R.string.last_name)) })
        }

        displayButton(R.string.manage_books, 50, 200, viewModel)
    }
}