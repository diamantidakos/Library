package com.mgiandia.library.ui.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mgiandia.library.R
import com.mgiandia.library.domain.Author
import com.mgiandia.library.domain.Book
import com.mgiandia.library.domain.Borrower
import com.mgiandia.library.domain.Item
import com.mgiandia.library.domain.Loan
import com.mgiandia.library.domain.Publisher
import com.mgiandia.library.view.Author.ManageAuthors.ManageAuthorsViewModel
import com.mgiandia.library.view.Book.ManageBooks.ManageBooksViewModel
import com.mgiandia.library.view.Borrower.ManageBorrowers.ManageBorrowersViewModel
import com.mgiandia.library.view.Items.ManageItems.ManageItemsViewModel
import com.mgiandia.library.view.Loans.ManageLoans.ManageLoansViewModel
import com.mgiandia.library.view.Publisher.ManagePublishers.ManagePublishersViewModel
import kotlin.math.absoluteValue


@SuppressLint("ComposableNaming")
@Composable
fun drawItemListScreen(modifier: Modifier = Modifier, viewModel: ManageItemsViewModel, items : ArrayList<Item>)
{
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize().padding(12.dp))
    {
        Row(modifier = modifier.fillMaxWidth().verticalScroll(rememberScrollState()), horizontalArrangement = Arrangement.SpaceBetween)
        {
            searchBar(query = searchQuery, onQueryChanged = { searchQuery = it }, modifier = modifier.height(60.dp).weight(3f))
            Spacer(modifier = modifier.width(10.dp))
            displayButton(R.string.add_new_item, 65, 120, viewModel)
        }

        LazyColumn(modifier = modifier.fillMaxSize())
        {
            items(items)
            {
                item -> itemRow(item = item.itemNumber.toString())
            }
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun drawBooksListScreen(modifier: Modifier = Modifier, viewModel: ManageBooksViewModel, books : ArrayList<Book>)
{
    Column(modifier = modifier.fillMaxSize().padding(12.dp))
    {
        Row(modifier = modifier.fillMaxWidth().verticalScroll(rememberScrollState()), horizontalArrangement = Arrangement.SpaceBetween)
        {
            searchBar(modifier = modifier.height(60.dp).weight(3f), viewModel = viewModel)
            Spacer(modifier = modifier.width(10.dp))
            displayButton(R.string.add_new_item, 65, 120, viewModel)
        }

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(modifier = modifier.fillMaxSize())
        {
            items(books)
            {
                item -> bookItem(item.title, item.publisher.name, item.id, item.authors.size, viewModel)
            }
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun bookItem(title: String, publisher: String, code: Int, authors: Int, viewModel: ManageBooksViewModel)
{
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).clickable { viewModel.setSelectedBookID(code) })
    {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
        {
            // Colored Box with initials
            Box(modifier = Modifier.size(50.dp).background(generateColor(title), RoundedCornerShape(1.dp)), contentAlignment = Alignment.Center)
            {
                Text(
                    text = "A${title.first().uppercaseChar()}",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Book details
            Column(modifier = Modifier.weight(1f))
            {
                Text(title, fontSize = 16.sp, color = Color.White)
                Text("${stringResource(R.string.from)} $publisher", color = Color.White, fontSize = 14.sp)
                Text("${stringResource(R.string.user_id)}: $code. ${stringResource(R.string.authors_title_text)}: $authors", color = Color.Gray, fontSize = 12.sp)
            }

            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Go", tint = Color.Gray)
        }

        // Thin separator line
        HorizontalDivider(modifier = Modifier.padding(top = 8.dp), thickness = 0.5.dp, color = Color.Black)
    }
}

/**
 * param --> το όνομα σε μορφή String (βιβλίου, συγγραφέα, κλπ)
 * return --> ένα χρώμα ανάλογα με το hashCode του ονόματος
 */
@Composable
fun generateColor(name: String): Color
{
    val colors = listOf(Color.Black, Color.Red, Color.Blue, Color.Green)
    val index = name.hashCode().absoluteValue % colors.size
    return colors[index]
}

@SuppressLint("ComposableNaming")
@Composable
fun drawBorrowerListScreen(modifier: Modifier = Modifier, viewModel: ManageBorrowersViewModel, borrowers : ArrayList<Borrower>)
{
    Column(modifier = modifier.fillMaxSize().padding(12.dp))
    {
        Row(modifier = modifier.fillMaxWidth().verticalScroll(rememberScrollState()), horizontalArrangement = Arrangement.SpaceBetween)
        {
            searchBar(modifier = modifier.height(60.dp).weight(3f), viewModel)
            Spacer(modifier = modifier.width(10.dp))
            displayButton(R.string.add_new_item, 65, 120, viewModel)
        }

        LazyColumn(modifier = modifier.fillMaxSize())
        {
            items(borrowers)
            {
                item -> borrowerItem(item.firstName, item.lastName, item.borrowerNo, item.loans.size, viewModel)
            }
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun borrowerItem(firstName: String, lastName: String, code: Int, loansNum: Int, viewModel: ManageBorrowersViewModel)
{
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).clickable { viewModel.setSelectedBorrowerID(code) })
    {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
        {
            // Colored Box with initials
            Box(modifier = Modifier.size(50.dp).background(generateColor(firstName), RoundedCornerShape(1.dp)), contentAlignment = Alignment.Center)
            {
                Text(
                    text = "${firstName.first().uppercaseChar()}${lastName.first().uppercaseChar()}",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Book details
            Column(modifier = Modifier.weight(1f))
            {
                Text(lastName, color = Color.White, fontSize = 14.sp)
                Text(firstName, fontSize = 16.sp, color = Color.White)
                Text("${stringResource(R.string.user_id)} $code. ${stringResource(R.string.loans_number)}: $loansNum", color = Color.Gray, fontSize = 12.sp)
            }

            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Go", tint = Color.Gray)
        }

        // Thin separator line
        HorizontalDivider(modifier = Modifier.padding(top = 8.dp), thickness = 0.5.dp, color = Color.Black)
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun drawPublisherListScreen(modifier: Modifier = Modifier, viewModel: ManagePublishersViewModel, publishers : ArrayList<Publisher>)
{
    Column(modifier = modifier.fillMaxSize().padding(12.dp))
    {
        Row(modifier = modifier.fillMaxWidth().verticalScroll(rememberScrollState()), horizontalArrangement = Arrangement.SpaceBetween)
        {
            searchBar(modifier = modifier.height(60.dp).weight(3f), viewModel)
            Spacer(modifier = modifier.width(10.dp))
            displayButton(R.string.add_new_item, 65, 120, viewModel)
        }

        LazyColumn(modifier = modifier.fillMaxSize())
        {
            items(publishers)
            {
                item -> publisherItem(item.name, item.books.size, item.id, viewModel)
            }
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun publisherItem(name: String, booksNum: Int, id: Int, viewModel: ManagePublishersViewModel)
{
    val secondChar = if (name.length > 1) name.substring(1, 2).uppercase() else ""

    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).clickable { viewModel.setSelectedPublisherID(id) })
    {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
        {
            // Colored Box with initials
            Box(modifier = Modifier.size(50.dp).background(generateColor(name), RoundedCornerShape(1.dp)), contentAlignment = Alignment.Center)
            {
                Text(
                    text = "${name.first().uppercaseChar()}${secondChar}",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Book details
            Column(modifier = Modifier.weight(1f))
            {
                Text(name, color = Color.White, fontSize = 14.sp)
                Text("${stringResource(R.string.books_number)} $booksNum", color = Color.Gray, fontSize = 12.sp)
            }

            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Go", tint = Color.Gray)
        }

        // Thin separator line
        HorizontalDivider(modifier = Modifier.padding(top = 8.dp), thickness = 0.5.dp, color = Color.Black)
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun drawAuthorListScreen(modifier: Modifier = Modifier, viewModel: ManageAuthorsViewModel, authors : ArrayList<Author>)
{
    Column(modifier = modifier.fillMaxSize().padding(12.dp))
    {
        Row(modifier = modifier.fillMaxWidth().verticalScroll(rememberScrollState()), horizontalArrangement = Arrangement.SpaceBetween)
        {
            searchBar(modifier = modifier.height(60.dp).weight(3f), viewModel)
            Spacer(modifier = modifier.width(10.dp))
            displayButton(R.string.add_new_item, 65, 120, viewModel)
        }

        LazyColumn(modifier = modifier.fillMaxSize())
        {
            items(authors)
            {
                item -> authorItem(item.firstName, item.lastName, item.books.size, item.id, viewModel)
            }
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun authorItem(firstName: String, lastName: String, booksNum: Int, id: Int, viewModel: ManageAuthorsViewModel)
{
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).clickable { viewModel.setSelectedAuthorID(id) })
    {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
        {
            // Colored Box with initials
            Box(modifier = Modifier.size(50.dp).background(generateColor(firstName), RoundedCornerShape(1.dp)), contentAlignment = Alignment.Center)
            {
                Text(
                    text = "${firstName.first().uppercaseChar()}${lastName.first().uppercaseChar()}",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Book details
            Column(modifier = Modifier.weight(1f))
            {
                Text(lastName, color = Color.White, fontSize = 14.sp)
                Text(firstName, fontSize = 16.sp, color = Color.White)
                Text("${stringResource(R.string.books_number)} $booksNum", color = Color.Gray, fontSize = 12.sp)
            }

            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Go", tint = Color.Gray)
        }

        // Thin separator line
        HorizontalDivider(modifier = Modifier.padding(top = 8.dp), thickness = 0.5.dp, color = Color.Black)
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun drawLoanListScreen(modifier: Modifier = Modifier, viewModel: ManageLoansViewModel, loans : ArrayList<Loan>)
{
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize().padding(12.dp))
    {
        Row(modifier = modifier.fillMaxWidth().verticalScroll(rememberScrollState()), horizontalArrangement = Arrangement.SpaceBetween)
        {
            searchBar(query = searchQuery, onQueryChanged = { searchQuery = it }, modifier = modifier.height(60.dp).weight(3f))
            Spacer(modifier = modifier.width(10.dp))
            displayButton(R.string.add_new_item, 65, 120, viewModel)
        }

        LazyColumn(modifier = modifier.fillMaxSize())
        {
            items(loans)
            {
                item -> itemRow(item = item.item.book.title) //........
            }
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun searchBar(modifier: Modifier = Modifier, viewModel: ManageBooksViewModel)
{
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it; viewModel.setTextOnSearchBar(it) },
        modifier = modifier.height(40.dp),
        placeholder = { Text(stringResource(R.string.search), color = Color.Black) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.Black
            )
        },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.DarkGray,
            unfocusedContainerColor = Color.DarkGray,
            disabledContainerColor = Color.DarkGray,
            errorContainerColor = Color.DarkGray,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Black,
            cursorColor = Color.Black
        )
    )
}

@SuppressLint("ComposableNaming")
@Composable
fun searchBar(modifier: Modifier = Modifier, viewModel: ManageAuthorsViewModel)
{
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange =  { text = it; viewModel.setTextOnSearchBar(it) },
        modifier = modifier.height(40.dp),
        placeholder = { Text(stringResource(R.string.search), color = Color.Black) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.Black
            )
        },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.DarkGray,
            unfocusedContainerColor = Color.DarkGray,
            disabledContainerColor = Color.DarkGray,
            errorContainerColor = Color.DarkGray,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Black,
            cursorColor = Color.Black
        )
    )
}

@SuppressLint("ComposableNaming")
@Composable
fun searchBar(modifier: Modifier = Modifier, viewModel: ManageBorrowersViewModel)
{
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange =  { text = it; viewModel.setTextOnSearchBar(it) },
        modifier = modifier.height(40.dp),
        placeholder = { Text(stringResource(R.string.search), color = Color.Black) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.Black
            )
        },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.DarkGray,
            unfocusedContainerColor = Color.DarkGray,
            disabledContainerColor = Color.DarkGray,
            errorContainerColor = Color.DarkGray,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Black,
            cursorColor = Color.Black
        )
    )
}

@SuppressLint("ComposableNaming")
@Composable
fun searchBar(modifier: Modifier = Modifier, viewModel: ManagePublishersViewModel)
{
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange =  { text = it; viewModel.setTextOnSearchBar(it) },
        modifier = modifier.height(40.dp),
        placeholder = { Text(stringResource(R.string.search), color = Color.Black) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.Black
            )
        },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.DarkGray,
            unfocusedContainerColor = Color.DarkGray,
            disabledContainerColor = Color.DarkGray,
            errorContainerColor = Color.DarkGray,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Black,
            cursorColor = Color.Black
        )
    )
}

@SuppressLint("ComposableNaming")
@Composable
fun searchBar(query: String, onQueryChanged: (String) -> Unit, modifier: Modifier = Modifier)
{
    TextField(
        value = query,
        onValueChange = onQueryChanged,
        modifier = modifier.height(40.dp),
        placeholder = { Text(stringResource(R.string.search), color = Color.Black) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.Black
            )
        },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.DarkGray,
            unfocusedContainerColor = Color.DarkGray,
            disabledContainerColor = Color.DarkGray,
            errorContainerColor = Color.DarkGray,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Black,
            cursorColor = Color.Black
        )
    )
}

@SuppressLint("ComposableNaming")
@Composable
fun itemRow(item: String)
{
    Box(modifier = Modifier.fillMaxWidth().padding(8.dp).padding(12.dp))
    {
        Text(text = item)
    }
}