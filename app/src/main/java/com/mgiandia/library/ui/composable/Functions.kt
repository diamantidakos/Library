package com.mgiandia.library.ui.composable

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mgiandia.library.R
import com.mgiandia.library.domain.Book
import com.mgiandia.library.ui.model.ButtonClicked
import com.mgiandia.library.view.Author.AddEditAuthor.AddEditAuthorViewModel
import com.mgiandia.library.view.Author.AuthorDetails.AuthorDetailsViewModel
import com.mgiandia.library.view.Book.AddEditBook.AddEditBookViewModel
import com.mgiandia.library.view.Book.BookDetails.BookDetailsViewModel
import com.mgiandia.library.view.Borrower.AddEditBorrower.AddEditBorrowerViewModel
import com.mgiandia.library.view.Borrower.BorrowerDetails.BorrowerDetailsViewModel
import com.mgiandia.library.view.Loans.AddLoan.AddLoanViewModel
import com.mgiandia.library.view.Publisher.AddPublisher.AddEditPublisherViewModel
import com.mgiandia.library.view.Publisher.PublisherDetails.PublisherDetailsViewModel


// All reusable functions I wrote, to create the ui

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("ComposableNaming")
@Composable
fun optionMenu(publishers: List<String>, viewModel: AddEditBookViewModel)
{
    val selectedPublisher = viewModel.publisher.value ?: publishers[0]
    val selectedPublisherIndex = publishers.indexOf(selectedPublisher)

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(selectedPublisher) }
    var selectedIndex by remember { mutableIntStateOf(selectedPublisherIndex) }

    viewModel.setPublisher(selectedText)
    viewModel.setPublisherPosition(selectedIndex + 1)

    Box(modifier = Modifier.fillMaxWidth())
    {
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded })
        {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                singleLine = true,
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
                            viewModel.setPublisher(selectedText)
                            viewModel.setPublisherPosition(selectedIndex + 1)
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("ComposableNaming")
@Composable
fun optionMenu(publishers: List<String>, viewModel: AddEditPublisherViewModel, defaultCountry : String)
{
    var country = defaultCountry

    if (!defaultCountry.equals(viewModel.country) && (viewModel.country.value != null))
    {
        country = viewModel.country.value.toString()
    }

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(country) }
    var selectedIndex by remember { mutableIntStateOf(publishers.indexOf(country).takeIf { it >= 0 } ?: -1) }

    viewModel.setCountryPosition(selectedIndex)

    Box(modifier = Modifier.fillMaxWidth())
    {
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded })
        {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                singleLine = true,
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
@SuppressLint("ComposableNaming")
@Composable
fun optionMenu(users: List<String>, viewModel: AddEditBorrowerViewModel)
{
    val selectedUserTypeIndex = viewModel.userTypePosition.value?.minus(1) ?: 0
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(users[selectedUserTypeIndex]) }
    var selectedIndex by remember { mutableIntStateOf(selectedUserTypeIndex) }

    viewModel.setUserTypePosition(selectedIndex + 1)

    Box(modifier = Modifier.fillMaxWidth())
    {
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded })
        {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false })
            {
                users.forEachIndexed { index, item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            selectedIndex = index
                            expanded = false
                            viewModel.setUserTypePosition(selectedIndex + 1)
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("ComposableNaming")
@Composable
fun optionMenu(countries: List<String>, viewModel: AddEditBorrowerViewModel, defaultCountry : String)
{
    var country = defaultCountry

    if (!defaultCountry.equals(viewModel.country) && (viewModel.country.value != null))
    {
        country = viewModel.country.value.toString()
    }

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(country) }
    var selectedIndex by remember { mutableIntStateOf(countries.indexOf(country).takeIf { it >= 0 } ?: -1) }

    viewModel.setCountryPosition(selectedIndex)

    Box(modifier = Modifier.fillMaxWidth())
    {
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded })
        {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false })
            {
                countries.forEachIndexed { index, item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            selectedIndex = index
                            expanded = false
                            viewModel.setCountry(selectedText)
                            viewModel.setCountryPosition(selectedIndex)
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("ComposableNaming")
@Composable
fun optionMenu(books: List<Book>, viewModel: AddLoanViewModel)
{
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("Διαλέξτε ένα βιβλίο") }
    var selectedIndex by remember { mutableIntStateOf(-1) }

    Box(modifier = Modifier.fillMaxWidth())
    {
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded })
        {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false })
            {
                books.forEachIndexed { index, item ->
                    DropdownMenuItem(
                        text = { Text(text = item.title) },
                        onClick = {
                            selectedText = item.title
                            selectedIndex = index
                            expanded = false
                            viewModel.setSelectedBookID(item.id)
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("ComposableNaming")
@Composable
fun multiselectMenu(authorsList: List<String>, viewModel: AddEditBookViewModel)
{
    val selectedAuthors by viewModel.authors.observeAsState(initial = emptyList())
    var isExpanded by remember { mutableStateOf(false) }
    var selectedAuthorNames = ArrayList<String>()
    var selectedAuthorIndexes = ArrayList<Int>()

    LaunchedEffect(authorsList, selectedAuthors)
    {
        val initialSelections = selectedAuthors.mapNotNull {
            author -> (authorsList.indexOf(author) + 1).takeIf { authorsList.indexOf(author) != -1 }
        }

        viewModel.setSelectedAuthorsPositions(initialSelections)
        selectedAuthorIndexes = initialSelections as ArrayList<Int>
    }

    LaunchedEffect(authorsList, selectedAuthors)
    {
        val initialSelections = selectedAuthors.mapNotNull {
            author -> selectedAuthorNames.add(author)
        }

        viewModel.setAuthors(selectedAuthorNames)
        selectedAuthorNames = initialSelections as ArrayList<String>
    }

    ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = { isExpanded = it })
    {
        TextField(
            value = selectedAuthors.joinToString(", "),
            onValueChange = {},
            placeholder = { Text("Επιλέξτε συγγραφείς") },
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor().semantics { contentDescription = "authorsField" }
        )

        ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false })
        {
            authorsList.forEachIndexed { index, author ->
                val isSelected = selectedAuthors.contains(author)
                DropdownMenuItem(
                    text = { Text(author) },
                    onClick = {
                        viewModel.toggleAuthor(author)

                        if (isSelected)
                        {
                            selectedAuthorIndexes.remove(index + 1)
                            selectedAuthorNames.remove(author)
                        }
                        else
                        {
                            selectedAuthorIndexes.add(index + 1)
                            selectedAuthorNames.add(author)
                        }

                        viewModel.setSelectedAuthorsPositions(selectedAuthorIndexes)
                    },
                    leadingIcon = { if (isSelected) Icon(Icons.Rounded.Check, null) }
                )
            }
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun displayRow(labelRes: Int, viewModel: AddEditBookViewModel, label: String)
{
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
    {
        if (label == "title")
        {
            var title by remember { mutableStateOf(viewModel.title.value ?: "") }
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = title, singleLine = true, onValueChange = { title = it; viewModel.setTitle(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp).semantics { contentDescription = "titleField" })
        }
        else if (label == "isbn")
        {
            var isbn by remember { mutableStateOf(viewModel.isbn.value ?: "") }
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = isbn, singleLine = true, onValueChange = { isbn = it; viewModel.setISBN(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp).semantics { contentDescription = "isbnField" })
        }
        else if (label == "publication")
        {
            var publication by remember { mutableStateOf(viewModel.publication.value ?: "") }
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = publication, singleLine = true, onValueChange = { publication = it; viewModel.setPublication(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp).semantics { contentDescription = "publicationField" })
        }
        else if (label == "year")
        {
            var year by remember { mutableStateOf(viewModel.publicationYear.value ?: "") }
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = year, singleLine = true, onValueChange = { year = it; viewModel.setPublicationYear(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp).semantics { contentDescription = "yearField" })
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun displayRow(labelRes: Int, isSpinner: Boolean, viewModel: AddEditBookViewModel, publishers: List<String>)
{
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
    {
        Text(text = stringResource(labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))

        if (isSpinner)
        {
            optionMenu(publishers, viewModel)
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun displayRow(labelRes: Int, viewModel: AddEditBookViewModel, authorsList: List<String>)
{
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
    {
        Text(text = stringResource(labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
        multiselectMenu(authorsList, viewModel)
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun displayRow(labelRes: Int, viewModel: AddEditAuthorViewModel, label: String)
{
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
    {
        if (label == "name")
        {
            var title by remember { mutableStateOf(viewModel.firstName.value ?: "") }
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = title, singleLine = true, onValueChange = { title = it; viewModel.setFirstName(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp).semantics { contentDescription = "firstNameField" })
        }
        else if (label == "surname")
        {
            var title by remember { mutableStateOf(viewModel.lastName.value ?: "") }
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = title, singleLine = true, onValueChange = { title = it; viewModel.setLastName(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp).semantics { contentDescription = "lastNameField" })
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun displayRow(labelRes: Int, isSpinner: Boolean, viewModel: AddEditPublisherViewModel)
{
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
    {
        Text(text = stringResource(labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))

        if (isSpinner)
        {
            val allCountries = viewModel.allCountries
            optionMenu(allCountries, viewModel, stringResource(R.string.publisher_default_country))
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun displayRow(labelRes: Int, viewModel: AddEditPublisherViewModel, label : String)
{
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
    {
        if (label == "name")
        {
            var title by remember { mutableStateOf(viewModel.name.value ?: "") }
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = title, singleLine = true, onValueChange = { title = it; viewModel.setName(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp).semantics { contentDescription = "name" })
        }
        else if (label == "phone")
        {
            var title by remember { mutableStateOf(viewModel.phone.value ?: "") }
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = title, singleLine = true, onValueChange = { title = it; viewModel.setPhone(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp).semantics { contentDescription = "phone" })
        }
        else if (label == "email")
        {
            var title by remember { mutableStateOf(viewModel.email.value ?: "") }
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = title, singleLine = true, onValueChange = { title = it; viewModel.setEmail(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp).semantics { contentDescription = "email" })
        }
        else if (label == "city")
        {
            var title by remember { mutableStateOf(viewModel.city.value ?: "") }
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = title, singleLine = true, onValueChange = { title = it; viewModel.setCity(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp).semantics { contentDescription = "city" })
        }
        else if (label == "street")
        {
            var title by remember { mutableStateOf(viewModel.street.value ?: "") }
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = title, singleLine = true, onValueChange = { title = it; viewModel.setStreet(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp).semantics { contentDescription = "street" })
        }
        else if (label == "number")
        {
            var title by remember { mutableStateOf(viewModel.number.value ?: "") }
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = title, singleLine = true, onValueChange = { title = it; viewModel.setNumber(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp).semantics { contentDescription = "number" })
        }
        else if (label == "zip")
        {
            var title by remember { mutableStateOf(viewModel.zipCode.value ?: "") }
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = title, singleLine = true, onValueChange = { title = it; viewModel.setZipCode(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp).semantics { contentDescription = "zip" })
        }
    }
}

@SuppressLint("ComposableNaming")
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

@SuppressLint("ComposableNaming")
@Composable
fun displayRow(labelRes: Int, isSpinner: Boolean, viewModel: AddEditBorrowerViewModel)
{
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
    {
        Text(text = stringResource(labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))

        if (isSpinner)
        {
            val allCountries = viewModel.allCountries
            optionMenu(allCountries, viewModel, stringResource(R.string.publisher_default_country))
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun displayRow(labelRes: Int, viewModel: AddEditBorrowerViewModel, label : String)
{
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
    {
        if (label == "name")
        {
            var title by remember { mutableStateOf(viewModel.firstName.value ?: "") }
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = title, singleLine = true, onValueChange = { title = it; viewModel.setFirstName(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp).semantics { contentDescription = "firstName" })
        }
        else if (label == "surname")
        {
            var title by remember { mutableStateOf(viewModel.lastName.value ?: "") }
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = title, singleLine = true, onValueChange = { title = it; viewModel.setLastName(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp).semantics { contentDescription = "lastName" })
        }
        else if (label == "phone")
        {
            var title by remember { mutableStateOf(viewModel.phone.value ?: "") }
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = title, singleLine = true, onValueChange = { title = it; viewModel.setPhone(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp).semantics { contentDescription = "phone" })
        }
        else if (label == "email")
        {
            var title by remember { mutableStateOf(viewModel.email.value ?: "") }
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = title, singleLine = true, onValueChange = { title = it; viewModel.setEmail(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp).semantics { contentDescription = "email" })
        }
        else if (label == "city")
        {
            var title by remember { mutableStateOf(viewModel.city.value ?: "") }
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = title, singleLine = true, onValueChange = { title = it; viewModel.setCity(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp).semantics { contentDescription = "city" })
        }
        else if (label == "street")
        {
            var title by remember { mutableStateOf(viewModel.street.value ?: "") }
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = title, singleLine = true, onValueChange = { title = it; viewModel.setStreet(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp).semantics { contentDescription = "street" })
        }
        else if (label == "number")
        {
            var title by remember { mutableStateOf(viewModel.number.value ?: "") }
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = title, singleLine = true, onValueChange = { title = it; viewModel.setNumber(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp).semantics { contentDescription = "number" })
        }
        else if (label == "zip")
        {
            var title by remember { mutableStateOf(viewModel.zipCode.value ?: "") }
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = title, singleLine = true, onValueChange = { title = it; viewModel.setZipCode(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp).semantics { contentDescription = "zip" })
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun displayRow(labelRes: Int, viewModel: BookDetailsViewModel, label: String)
{
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
    {
        if (label == "code")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.bookID.value.toString())
        }
        else if (label == "title")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.title.value.toString())
        }
        else if (label == "publisher")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.publisher.value.toString())
        }
        else if (label == "isbn")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.isbn.value.toString())
        }
        else if (label == "publication")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.publication.value.toString())
        }
        else if (label == "year")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.publicationYear.value.toString())
        }
        else if (label == "copies")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.copiesNum.value.toString())
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun displayRow(labelRes: Int, viewModel: AddLoanViewModel)
{
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween)
    {
        Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
        Text(text = viewModel.borrowerFullName.value.toString())
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun displayRow(labelRes: Int, viewModel: AddLoanViewModel, books : List<Book>)
{
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
    {
        Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
        optionMenu(books, viewModel)
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun displayRow(labelRes: Int, viewModel: AuthorDetailsViewModel, label: String)
{
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
    {
        if (label == "code")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.authorID.value.toString())
        }
        else if (label == "firstName")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.name.value.toString())
        }
        else if (label == "lastName")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.surname.value.toString())
        }
        else if (label == "booksNum")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.booksNumber.value.toString())
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun displayRow(labelRes: Int, viewModel: BorrowerDetailsViewModel, label: String)
{
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
    {
        if (label == "code")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.borrowerNo.value.toString())
        }
        else if (label == "firstName")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.firstName.value.toString())
        }
        else if (label == "lastName")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.lastName.value.toString())
        }
        else if (label == "category")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.category.value.toString())
        }
        else if (label == "phone")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.phone.value.toString())
        }
        else if (label == "email")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.email.value.toString())
        }
        else if (label == "country")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.country.value.toString())
        }
        else if (label == "city")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.city.value.toString())
        }
        else if (label == "street")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.street.value.toString())
        }
        else if (label == "number")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.number.value.toString())
        }
        else if (label == "postCode")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.postCode.value.toString())
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun displayRow(labelRes: Int, viewModel: PublisherDetailsViewModel, label: String)
{
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
    {
        if (label == "code")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.publisherID.value.toString())
        }
        else if (label == "name")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.name.value.toString())
        }
        else if (label == "phone")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.phone.value.toString())
        }
        else if (label == "email")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.email.value.toString())
        }
        else if (label == "published")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.published.value.toString())
        }
        else if (label == "country")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.country.value.toString())
        }
        else if (label == "city")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.city.value.toString())
        }
        else if (label == "street")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.street.value.toString())
        }
        else if (label == "number")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.number.value.toString())
        }
        else if (label == "postCode")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            Text(text = viewModel.postCode.value.toString())
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun displayAuthors(viewModel: BookDetailsViewModel)
{
    val authorsList = viewModel.authors.value

    Column(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally)
    {
        if (!authorsList.isNullOrEmpty())
        {
            authorsList.forEach {
                author -> Text(text = author, modifier = Modifier.padding(bottom = 4.dp))
            }
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun displayLibraryIcon()
{
    Image(painter = painterResource(id = R.drawable.ic_bookshelf), contentDescription = "library icon", modifier = Modifier.width(167.dp).height(124.dp))
}

@SuppressLint("ComposableNaming")
@Composable
fun welcomeText(txt: String)
{
    Text(txt)
}


@SuppressLint("ComposableNaming")
@Composable
fun displayButton(@StringRes labelResId: Int, labelText: String, height : Int, width: Int, viewModel: ButtonClicked)
{
    Button(
        onClick = { viewModel.buttonClicked(labelResId) },
        modifier = Modifier
            .height(height.dp).width(width.dp).padding(5.dp)
            .semantics { contentDescription = labelText },
        enabled = true,
        colors = ButtonDefaults.buttonColors(Color.Gray),
        shape = RoundedCornerShape(0, 0, 0, 0),
    )
    {
        Text(text = labelText, softWrap = false, overflow = TextOverflow.Visible, color = Color.White)
    }
}
