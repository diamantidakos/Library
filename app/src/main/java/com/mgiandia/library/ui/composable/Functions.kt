package com.mgiandia.library.ui.composable

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mgiandia.library.R
import com.mgiandia.library.view.Book.AddEditBook.AddEditBookViewModel
import com.mgiandia.library.view.Borrower.AddEditBorrower.AddEditBorrowerViewModel
import com.mgiandia.library.view.Publisher.AddPublisher.AddEditPublisherViewModel
import java.util.Locale

// All reusable functions I wrote, to create the ui

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun optionMenu(publishers: List<String>, viewModel: AddEditBookViewModel)
{
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(publishers[0]) }
    var selectedIndex by remember { mutableIntStateOf(0) }

    viewModel.setPublisher(selectedText)
    viewModel.setPublisherPosition(selectedIndex)

    Box(modifier = Modifier.fillMaxWidth())
    {
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded })
        {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false })
            {
                publishers.forEachIndexed { index, item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            selectedIndex = index
                            expanded = false
                            viewModel.setPublisher(item)
                            viewModel.setPublisherPosition(index)
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun optionMenu(publishers: List<String>, viewModel: AddEditPublisherViewModel, defaultCountry : String)
{
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(defaultCountry) }
    var selectedIndex by remember { mutableIntStateOf(publishers.indexOf(defaultCountry).takeIf { it >= 0 } ?: -1) }

    Box(modifier = Modifier.fillMaxWidth())
    {
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded })
        {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false })
            {
                publishers.forEachIndexed { index, item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            selectedIndex = index
                            expanded = false
                            viewModel.setCountryPosition(index)
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun optionMenu(publishers: List<String>, viewModel: AddEditBorrowerViewModel)
{
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(publishers[0]) }
    var selectedIndex by remember { mutableIntStateOf(0) }

    Box(modifier = Modifier.fillMaxWidth())
    {
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded })
        {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false })
            {
                publishers.forEachIndexed { index, item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            selectedIndex = index
                            expanded = false
                            viewModel.setUserTypePosition(index)
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun optionMenu(publishers: List<String>, viewModel: AddEditBorrowerViewModel, defaultCountry : String)
{
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(defaultCountry) }
    var selectedIndex by remember { mutableIntStateOf(publishers.indexOf(defaultCountry).takeIf { it >= 0 } ?: -1) }

    Box(modifier = Modifier.fillMaxWidth())
    {
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded })
        {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false })
            {
                publishers.forEachIndexed { index, item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            selectedIndex = index
                            expanded = false
                            viewModel.setCountryPosition(index)
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun multiselectiorMenu(authorsList: List<String>, viewModel: AddEditBookViewModel)
{
    var isExpanded by remember { mutableStateOf(false) }
    val selectedAuthors = remember { mutableStateListOf<String>() }

    ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = { isExpanded = it })
    {
        TextField(
            value = selectedAuthors.joinToString(", "),
            onValueChange = {},
            placeholder = {
                Text(text = "Select authors")
            },
            readOnly = true, // Makes the TextField clickable
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor() // Needed to anchor the dropdown menu
        )

        ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false })
        {
            authorsList.forEachIndexed { index, author ->
                AnimatedContent(targetState = selectedAuthors.contains(author), label = "Animate the selected item")
                {
                        isSelected ->
                    if (isSelected)
                    {
                        DropdownMenuItem(
                            text = { Text(text = author) },
                            onClick = { selectedAuthors.remove(author); viewModel.setSelectedAuthorsPositions(index) },
                            leadingIcon = { Icon(imageVector = Icons.Rounded.Check, contentDescription = null) }
                        )
                    }
                    else
                    {
                        DropdownMenuItem(text = { Text(text = author) }, onClick = { selectedAuthors.add(author); viewModel.addAuthor(author); viewModel.setSelectedAuthorsPositions(index) })
                    }
                }
            }
        }
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