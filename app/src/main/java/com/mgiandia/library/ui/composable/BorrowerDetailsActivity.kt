package com.mgiandia.library.ui.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mgiandia.library.R
import com.mgiandia.library.view.Borrower.BorrowerDetails.BorrowerDetailsViewModel

@SuppressLint("ComposableNaming")
@Composable
fun drawBorrowerDetailsPage(modifier: Modifier = Modifier, viewModel: BorrowerDetailsViewModel)
{
    Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top), horizontalAlignment = Alignment.CenterHorizontally)
    {
        welcomeText(stringResource(R.string.basic_info))

        displayRow(R.string.user_id, viewModel, "code")
        displayRow(R.string.first_name, viewModel, "firstName")
        displayRow(R.string.last_name, viewModel, "lastName")
        displayRow(R.string.category, viewModel, "category")
        displayRow(R.string.telephone, viewModel, "phone")
        displayRow(R.string. email, viewModel, "email")

        welcomeText(stringResource(R.string.address))

        displayRow(R.string.country, viewModel, "country")
        displayRow(R.string.city, viewModel, "city")
        displayRow(R.string.street, viewModel, "street")
        displayRow(R.string.number, viewModel, "number")
        displayRow(R.string.zip, viewModel, "postCode")

        welcomeText(stringResource(R.string.actions))

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
        {
            displayButton(R.string.delete_user, 50, 200, viewModel)
            displayButton(R.string.edit_user, 50, 200, viewModel)
        }
    }
}