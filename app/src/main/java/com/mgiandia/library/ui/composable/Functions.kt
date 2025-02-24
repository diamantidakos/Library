package com.mgiandia.library.ui.composable

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mgiandia.library.R
import com.mgiandia.library.domain.Book
import com.mgiandia.library.view.Author.AddEditAuthor.AddEditAuthorViewModel
import com.mgiandia.library.view.Author.AuthorDetails.AuthorDetailsViewModel
import com.mgiandia.library.view.Book.AddEditBook.AddEditBookViewModel
import com.mgiandia.library.view.Book.BookDetails.BookDetailsViewModel
import com.mgiandia.library.view.Borrower.AddEditBorrower.AddEditBorrowerViewModel
import com.mgiandia.library.view.Borrower.BorrowerDetails.BorrowerDetailsViewModel
import com.mgiandia.library.view.HomePage.HomePageViewModel
import com.mgiandia.library.view.Items.ManageItems.ManageItemsViewModel
import com.mgiandia.library.view.Loans.AddLoan.AddLoanViewModel
import com.mgiandia.library.view.Publisher.AddPublisher.AddEditPublisherViewModel
import com.mgiandia.library.view.Publisher.PublisherDetails.PublisherDetailsViewModel
import java.util.Locale


// All reusable functions I wrote, to create the ui

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("ComposableNaming")
@Composable
fun optionMenu(publishers: List<String>, viewModel: AddEditBookViewModel)
{
    val selectedPublisher = viewModel.publisher.value ?: "select publisher"
    val selectedPublisherIndex = viewModel.publisherPosition.value ?: -1

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(selectedPublisher) }
    var selectedIndex by remember { mutableIntStateOf(selectedPublisherIndex) }

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
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(defaultCountry) }
    var selectedIndex by remember { mutableIntStateOf(publishers.indexOf(defaultCountry).takeIf { it >= 0 } ?: -1) }

    viewModel.setCountryPosition(selectedIndex)

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
@SuppressLint("ComposableNaming")
@Composable
fun optionMenu(publishers: List<String>, viewModel: AddEditBorrowerViewModel)
{
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(publishers[0]) }
    var selectedIndex by remember { mutableIntStateOf(0) }

    viewModel.setUserTypePosition(selectedIndex)

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
@SuppressLint("ComposableNaming")
@Composable
fun optionMenu(publishers: List<String>, viewModel: AddEditBorrowerViewModel, defaultCountry : String)
{
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(defaultCountry) }
    var selectedIndex by remember { mutableIntStateOf(publishers.indexOf(defaultCountry).takeIf { it >= 0 } ?: -1) }

    viewModel.setCountryPosition(selectedIndex)

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
    val selectedAuthorNames = viewModel.authors.value ?: ""

    var isExpanded by remember { mutableStateOf(false) }
    val selectedAuthors = remember { mutableStateListOf(selectedAuthorNames.toString()) }

    ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = { isExpanded = it })
    {
        TextField(
            value = selectedAuthors.joinToString(", "),
            onValueChange = {},
            placeholder = { Text(text = "Select authors") },
            readOnly = true, // Makes the TextField clickable
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor()
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
                        DropdownMenuItem(text = { Text(text = author) }, onClick = { selectedAuthors.add(author); viewModel.addAuthor(author); viewModel.setSelectedAuthorsPositions(index + 1) })
                    }
                }
            }
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun displayRow(labelRes: Int, viewModel: AddEditBookViewModel, label: String)
{
    var text by remember { mutableStateOf("") }
    val complete = viewModel.completeFields.value

    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
    {
        if (label == "title")
        {
            if (complete == true)
            {
                var title by remember { mutableStateOf(viewModel.title.value.toString()) }
                Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
                TextField(value = title, onValueChange = { title = it; viewModel.setTitle(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp))
            }
            else
            {
                Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
                TextField(value = text, onValueChange = { text = it; viewModel.setTitle(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp))
            }
        }
        else if (label == "isbn")
        {
            if (complete == true)
            {
                var isbn by remember { mutableStateOf(viewModel.isbn.value.toString()) }
                Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
                TextField(value = isbn, onValueChange = { isbn = it; viewModel.setISBN(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp),)
            }
            else
            {
                Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
                TextField(value = text, onValueChange = { text = it; viewModel.setISBN(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp),)
            }
        }
        else if (label == "publication")
        {
            if (complete == true)
            {
                var publication by remember { mutableStateOf(viewModel.publication.value.toString()) }
                Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
                TextField(value = publication, onValueChange = { publication = it; viewModel.setISBN(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp),)
            }
            else
            {
                Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
                TextField(value = text, onValueChange = { text = it; viewModel.setPublication(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp),)
            }
        }
        else if (label == "year")
        {
            if (complete == true)
            {
                var year by remember { mutableStateOf(viewModel.publicationYear.value.toString()) }
                Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
                TextField(value = year, onValueChange = { year = it; viewModel.setISBN(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp),)
            }
            else
            {
                Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
                TextField(value = text, onValueChange = { text = it; viewModel.setPublicationYear(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp),)
            }
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
    var text by remember { mutableStateOf("") }

    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
    {
        if (label == "name")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = text, onValueChange = { text = it; viewModel.setFirstName(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp))
        }
        else if (label == "surname")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
            TextField(value = text, onValueChange = { text = it; viewModel.setLastName(it) }, modifier = Modifier.fillMaxWidth().padding(start = 10.dp),)
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
            val countries = Locale.getISOCountries().map { countryCode -> Locale("", countryCode).displayCountry }.sorted().toCollection(ArrayList())
            optionMenu(countries, viewModel, stringResource(R.string.publisher_default_country))
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun displayRow(labelRes: Int, viewModel: AddEditPublisherViewModel, label : String)
{
    var text by remember { mutableStateOf("") }

    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
    {
        if (label == "name")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
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
            val countries = Locale.getISOCountries().map { countryCode -> Locale("", countryCode).displayCountry }.sorted().toCollection(ArrayList())
            optionMenu(countries, viewModel, stringResource(R.string.publisher_default_country))
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun displayRow(labelRes: Int, viewModel: AddEditBorrowerViewModel, label : String)
{
    var text by remember { mutableStateOf("") }

    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
    {
        if (label == "name")
        {
            Text(text = stringResource(id = labelRes), fontSize = 14.sp, modifier = Modifier.width(100.dp))
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

    Row(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp))
    {
        if (authorsList != null)
        {
            authorsList.forEach {
                text -> Text(text = text)
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
    Text(txt, fontSize = 20.sp)
}

@SuppressLint("ComposableNaming")
@Composable
fun displayButton(@StringRes textResId: Int, height : Int, width: Int, viewModel: HomePageViewModel)
{
    Button(
        onClick = {
            viewModel.buttonClicked(textResId)
        },
        modifier = Modifier.height(height.dp).width(width.dp).padding(5.dp),
        enabled = true,
        colors = ButtonDefaults.buttonColors(Color.Gray),
        shape = RoundedCornerShape(0, 0, 0, 0)
    )
    {
        Text(text = stringResource(textResId), fontSize = 16.sp, color = Color.White)
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun displayButton(@StringRes textResId: Int, height : Int, width: Int, viewModel: AddEditBookViewModel) // mporw kai sthn antistoixh kt klash
{
    Button(
        onClick = {
            viewModel.buttonClicked(textResId)
        },
        modifier = Modifier.height(height.dp).width(width.dp).padding(5.dp),
        enabled = true,
        colors = ButtonDefaults.buttonColors(Color.Gray),
        shape = RoundedCornerShape(0, 0, 0, 0)
    )
    {
        Text(text = stringResource(textResId), fontSize = 16.sp, color = Color.White)
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun displayButton(@StringRes textResId: Int, height : Int, width: Int, viewModel: AddEditAuthorViewModel) // mporw kai sthn antistoixh kt klash
{
    Button(
        onClick = { viewModel.buttonClicked(textResId) },
        modifier = Modifier.height(height.dp).width(width.dp).padding(5.dp),
        enabled = true,
        colors = ButtonDefaults.buttonColors(Color.Gray),
        shape = RoundedCornerShape(0, 0, 0, 0)
    )
    {
        Text(text = stringResource(textResId), fontSize = 16.sp, color = Color.White)
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun displayButton(@StringRes textResId: Int, height : Int, width: Int, viewModel: AddEditPublisherViewModel) // mporw kai sthn antistoixh kt klash
{
    Button(
        onClick = { viewModel.buttonClicked(textResId) },
        modifier = Modifier.height(height.dp).width(width.dp).padding(5.dp),
        enabled = true,
        colors = ButtonDefaults.buttonColors(Color.Gray),
        shape = RoundedCornerShape(0, 0, 0, 0)
    )
    {
        Text(text = stringResource(textResId), fontSize = 16.sp, color = Color.White)
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun displayButton(@StringRes textResId: Int, height : Int, width: Int, viewModel: AddEditBorrowerViewModel) // mporw kai sthn antistoixh kt klash
{
    Button(
        onClick = { viewModel.buttonClicked(textResId) },
        modifier = Modifier.height(height.dp).width(width.dp).padding(5.dp),
        enabled = true,
        colors = ButtonDefaults.buttonColors(Color.Gray),
        shape = RoundedCornerShape(0, 0, 0, 0)
    )
    {
        Text(text = stringResource(textResId), fontSize = 16.sp, color = Color.White)
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun displayButton(@StringRes textResId: Int, height : Int, width: Int, viewModel: BookDetailsViewModel)
{
    Button(
        onClick = {
            viewModel.buttonClicked(textResId)
        },
        modifier = Modifier.height(height.dp).width(width.dp).padding(5.dp),
        enabled = true,
        colors = ButtonDefaults.buttonColors(Color.Gray),
        shape = RoundedCornerShape(0, 0, 0, 0)
    )
    {
        Text(text = stringResource(textResId), fontSize = 16.sp, color = Color.White)
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun displayButton(@StringRes textResId: Int, height: Int, width: Int, viewModel: AddLoanViewModel)
{
    Button(
        onClick = {
            viewModel.buttonClicked(textResId)
        },
        modifier = Modifier.height(height.dp).width(width.dp).padding(5.dp),
        enabled = true,
        colors = ButtonDefaults.buttonColors(Color.Gray),
        shape = RoundedCornerShape(0, 0, 0, 0)
    )
    {
        Text(text = stringResource(textResId), fontSize = 16.sp, color = Color.White)
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun displayButton(@StringRes textResId: Int, height: Int, width: Int, viewModel: AuthorDetailsViewModel)
{
    Button(
        onClick = {
            viewModel.buttonClicked(textResId)
        },
        modifier = Modifier.height(height.dp).width(width.dp).padding(5.dp),
        enabled = true,
        colors = ButtonDefaults.buttonColors(Color.Gray),
        shape = RoundedCornerShape(0, 0, 0, 0)
    )
    {
        Text(text = stringResource(textResId), fontSize = 16.sp, color = Color.White)
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun displayButton(@StringRes textResId: Int, height: Int, width: Int, viewModel: ManageItemsViewModel)
{
    Button(
        onClick = {
            viewModel.buttonClicked(textResId)
        },
        modifier = Modifier.height(height.dp).width(width.dp).padding(5.dp),
        enabled = true,
        colors = ButtonDefaults.buttonColors(Color.Gray),
        shape = RoundedCornerShape(0, 0, 0, 0)
    )
    {
        Text(text = stringResource(textResId), fontSize = 16.sp, color = Color.White)
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun displayButton(@StringRes textResId: Int, height: Int, width: Int, viewModel: BorrowerDetailsViewModel)
{
    Button(
        onClick = {
            viewModel.buttonClicked(textResId)
        },
        modifier = Modifier.height(height.dp).width(width.dp).padding(5.dp),
        enabled = true,
        colors = ButtonDefaults.buttonColors(Color.Gray),
        shape = RoundedCornerShape(0, 0, 0, 0)
    )
    {
        Text(text = stringResource(textResId), fontSize = 16.sp, color = Color.White)
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun displayButton(@StringRes textResId: Int, height: Int, width: Int, viewModel: PublisherDetailsViewModel)
{
    Button(
        onClick = {
            viewModel.buttonClicked(textResId)
        },
        modifier = Modifier.height(height.dp).width(width.dp).padding(5.dp),
        enabled = true,
        colors = ButtonDefaults.buttonColors(Color.Gray),
        shape = RoundedCornerShape(0, 0, 0, 0)
    )
    {
        Text(text = stringResource(textResId), fontSize = 16.sp, color = Color.White)
    }
}