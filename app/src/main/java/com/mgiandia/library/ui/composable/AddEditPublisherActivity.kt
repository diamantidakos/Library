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

        // Add more fields following the pattern
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

@Composable
fun displayRow(labelRes: Int, isSpinner: Boolean, viewModel: AddEditPublisherViewModel)
{
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
    {
        Text(text = stringResource(labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))

        if (isSpinner)
        {
            val countries = Locale.getISOCountries().map { countryCode -> Locale("", countryCode).displayCountry }.sorted().toCollection(ArrayList())
            optionMenu(countries, viewModel, stringResource(R.string.publisher_default_country))
        }
    }
}

@Composable
fun displayRow(labelRes: Int, viewModel: AddEditPublisherViewModel, label : String)
{
    var text by remember { mutableStateOf("") }

    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
    {
        if (label == "name")
        {
            Text(text = stringResource(id = R.string.first_name), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = text, onValueChange = { text = it; viewModel.setName(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp))
        }
        else if (label == "phone")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = text, onValueChange = { text = it; viewModel.setPhone(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp),)
        }
        else if (label == "email")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = text, onValueChange = { text = it; viewModel.setEmail(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp),)
        }
        else if (label == "city")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = text, onValueChange = { text = it; viewModel.setCity(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp),)
        }
        else if (label == "street")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = text, onValueChange = { text = it; viewModel.setStreet(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp),)
        }
        else if (label == "number")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = text, onValueChange = { text = it; viewModel.setNumber(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp),)
        }
        else if (label == "zip")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = text, onValueChange = { text = it; viewModel.setZipCode(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp),)
        }
    }
}


@Composable
fun displayRow(labelRes: Int, isSpinner: Boolean, userType: ArrayList<String>, viewModel: AddEditBorrowerViewModel)
{
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
    {
        Text(text = stringResource(labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))

        if (isSpinner)
        {
            optionMenu(userType, viewModel)
        }
    }
}

@Composable
fun displayRow(labelRes: Int, isSpinner: Boolean, viewModel: AddEditBorrowerViewModel)
{
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
    {
        Text(text = stringResource(labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))

        if (isSpinner)
        {
            val countries = Locale.getISOCountries().map { countryCode -> Locale("", countryCode).displayCountry }.sorted().toCollection(ArrayList())
            optionMenu(countries, viewModel, stringResource(R.string.publisher_default_country))
        }
    }
}

@Composable
fun displayRow(labelRes: Int, viewModel: AddEditBorrowerViewModel, label : String)
{
    var text by remember { mutableStateOf("") }

    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
    {
        if (label == "name")
        {
            Text(text = stringResource(id = R.string.first_name), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = text, onValueChange = { text = it; viewModel.setFirstName(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp))
        }
        else if (label == "surname")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = text, onValueChange = { text = it; viewModel.setLastName(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp),)
        }
        else if (label == "phone")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = text, onValueChange = { text = it; viewModel.setPhone(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp),)
        }
        else if (label == "email")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = text, onValueChange = { text = it; viewModel.setEmail(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp),)
        }
        else if (label == "city")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = text, onValueChange = { text = it; viewModel.setCity(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp),)
        }
        else if (label == "street")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = text, onValueChange = { text = it; viewModel.setStreet(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp),)
        }
        else if (label == "number")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = text, onValueChange = { text = it; viewModel.setNumber(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp),)
        }
        else if (label == "zip")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = text, onValueChange = { text = it; viewModel.setZipCode(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp),)
        }
    }
}