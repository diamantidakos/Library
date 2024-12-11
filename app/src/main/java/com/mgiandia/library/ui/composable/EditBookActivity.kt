package com.mgiandia.library.ui.composable

import android.widget.Spinner
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.foundation.text2.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mgiandia.library.R
import com.mgiandia.library.view.Book.AddEditBook.AddEditBookViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun drawEditBookPage(modifier: Modifier = Modifier, viewModel: AddEditBookViewModel)
{
    var text by remember { mutableStateOf("") }

    Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally)
    {
        welcomeText(stringResource(R.string.basic_info))

        Row()
        {
            welcomeText(stringResource(R.string.book_title))
            OutlinedTextField(value = text, onValueChange = { text = it }, label = { Text("Title") })
        }

        Row()
        {
            welcomeText(stringResource(R.string.book_publisher))
            optionMenu()
        }

        Row()
        {
            welcomeText(stringResource(R.string.book_isbn))
            OutlinedTextField(value = text, onValueChange = { text = it }, label = { Text("ISBN") })
        }

        Row()
        {
            welcomeText(stringResource(R.string.book_publication))
            OutlinedTextField(value = text, onValueChange = { text = it }, label = { Text("Book publication") })
        }

        Row()
        {
            welcomeText(stringResource(R.string.book_publicationyear))
            OutlinedTextField(value = text, onValueChange = { text = it }, label = { Text("Book publication year") })
        }

        Row()
        {
            welcomeText(stringResource(R.string.authors_title_text))
            multiselectiorMenu(viewModel)
        }

        displayButton(R.string.complete_registration, 50, 200, viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun optionMenu()
{
    val publishers = arrayOf("Addison Wesley", "McGraw-Hill Education")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(publishers[0]) }

    Box(modifier = Modifier.fillMaxWidth().padding(32.dp))
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
                publishers.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun multiselectiorMenu(viewModel: AddEditBookViewModel)
{
    /*
    val selectedItems = viewModel.getSelectedItems().map { it.text }
    Text(text = "Selected items: $selectedItems")
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), contentPadding = PaddingValues(8.dp))
    {
        itemsIndexed(
            myViewModel.myItems,
            key = { _, item: MyItem ->
                item.hashCode()
            }
        ) { index, item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        viewModel.toggleSelection(index)
                    }
                    .padding(8.dp)
            ) {
                Text("Item $index", fontSize = 20.sp)
                if (item.isSelected) {
                    Icon(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                        imageVector = Icons.Default.Check,
                        contentDescription = "Selected",
                    )
                }
            }
        }
    }
    */
}