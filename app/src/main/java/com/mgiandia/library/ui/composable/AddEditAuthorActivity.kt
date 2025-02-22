package com.mgiandia.library.ui.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mgiandia.library.R
import com.mgiandia.library.view.Author.AddEditAuthor.AddEditAuthorViewModel

@SuppressLint("ComposableNaming")
@Composable
fun drawAddEditAuthorPage(modifier: Modifier = Modifier, viewModel: AddEditAuthorViewModel)
{
    Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top), horizontalAlignment = Alignment.CenterHorizontally)
    {
        welcomeText(stringResource(R.string.basic_info))

        displayRow(R.string.first_name, viewModel, "name")
        Spacer(modifier = Modifier.height(16.dp)) // add space between the above element and the next

        displayRow(R.string.last_name, viewModel, "surname")
        Spacer(modifier = Modifier.height(16.dp))

        displayButton(R.string.complete_registration, 50, 200, viewModel)
    }
}