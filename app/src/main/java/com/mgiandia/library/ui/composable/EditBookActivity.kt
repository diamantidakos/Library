package com.mgiandia.library.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mgiandia.library.R
import com.mgiandia.library.view.Book.AddEditBook.AddEditBookViewModel


@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun drawEditBookPage(modifier: Modifier = Modifier, viewModel: AddEditBookViewModel)
{
    var text1 by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }
    var text3 by remember { mutableStateOf("") }
    var text4 by remember { mutableStateOf("") }

    Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top), horizontalAlignment = Alignment.CenterHorizontally)
    {
        welcomeText(stringResource(R.string.basic_info))

        displayRow(R.string.book_title, viewModel, "title")
        Spacer(modifier = Modifier.height(16.dp)) // add space between the above element and the next

        var publishersList = ArrayList<String>()
        publishersList.add("Addison Wesley")
        publishersList.add("McGraw-Hill Education")

        displayRow(R.string.book_publisher, true, viewModel, publishersList)
        Spacer(modifier = Modifier.height(16.dp))

        displayRow(R.string.book_isbn, viewModel, "isbn")
        Spacer(modifier = Modifier.height(16.dp))

        displayRow(R.string.book_publication, viewModel, "publication")
        Spacer(modifier = Modifier.height(16.dp))

        displayRow(R.string.book_publicationyear, viewModel, "year")
        Spacer(modifier = Modifier.height(16.dp))

        var authorsList = ArrayList<String>()
        authorsList.add("Doe John")
        authorsList.add("Fowler Martin")
        authorsList.add("Γεωργιάδης Απόστολος")
        authorsList.add("Βυζάντιος Χρήστος")
        authorsList.add("Αβέρωφ Ευάγγελος")

        displayRow(R.string.authors_title_text, viewModel, authorsList)
        Spacer(modifier = Modifier.height(16.dp))

        displayButton(R.string.complete_registration, 50, 200, viewModel)
    }
}