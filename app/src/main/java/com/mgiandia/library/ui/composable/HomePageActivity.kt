package com.mgiandia.library.ui.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mgiandia.library.R
import com.mgiandia.library.view.HomePage.HomePageViewModel


@SuppressLint("ComposableNaming")
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