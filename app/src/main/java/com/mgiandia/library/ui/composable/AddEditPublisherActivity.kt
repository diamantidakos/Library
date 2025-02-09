package com.mgiandia.library.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mgiandia.library.R
import com.mgiandia.library.view.Borrower.AddEditBorrower.AddEditBorrowerViewModel
import com.mgiandia.library.view.Publisher.AddPublisher.AddEditPublisherViewModel
import java.util.Locale

@Composable
fun drawAddEditPublisherPage(modifier: Modifier = Modifier, viewModel: AddEditPublisherViewModel)
{
    Column(modifier = modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text = stringResource(id = R.string.basic_info), fontSize = 18.sp, modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)

        displayRow(R.string.first_name, viewModel, "name")
        Spacer(modifier = Modifier.height(16.dp)) // add space between the above element and the next

        displayRow(R.string.telephone, viewModel, "phone")
        Spacer(modifier = Modifier.height(16.dp))

        displayRow(R.string.email, viewModel, "email")
        Spacer(modifier = Modifier.height(32.dp))


        Text(text = stringResource(id = R.string.address), fontSize = 18.sp, modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)

        displayRow(R.string.country, true, viewModel)
        Spacer(modifier = Modifier.height(16.dp))

        displayRow(R.string.city, viewModel, "city")
        Spacer(modifier = Modifier.height(16.dp))

        displayRow(R.string.street, viewModel, "street")
        Spacer(modifier = Modifier.height(16.dp))

        displayRow(R.string.number, viewModel, "number")
        Spacer(modifier = Modifier.height(16.dp))

        displayRow(R.string.zip, viewModel, "zip")
        Spacer(modifier = Modifier.height(32.dp))

        displayButton(R.string.complete_registration, 55, 160, viewModel)
    }
}