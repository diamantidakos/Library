package com.mgiandia.library.ui.composable

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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

        Row()
        {
            welcomeText(stringResource(R.string.book_title))
            OutlinedTextField(value = text1, onValueChange = { text1 = it }, label = { Text(stringResource(R.string.book_title), style = TextStyle(fontSize = 12.sp)) }, textStyle = TextStyle(fontSize = 15.sp), modifier = Modifier.height(58.dp).padding(vertical = 0.dp))
        }

        Row()
        {
            welcomeText(stringResource(R.string.book_publisher))
            optionMenu()
        }

        Row()
        {
            welcomeText(stringResource(R.string.book_isbn))
            OutlinedTextField(value = text2, onValueChange = { text2 = it }, label = { Text(stringResource(R.string.book_isbn), style = TextStyle(fontSize = 12.sp)) }, textStyle = TextStyle(fontSize = 15.sp), modifier = Modifier.height(58.dp).padding(vertical = 0.dp))
        }

        Row()
        {
            welcomeText(stringResource(R.string.book_publication))
            OutlinedTextField(value = text3, onValueChange = { text3 = it }, label = { Text(stringResource(R.string.book_publication), style = TextStyle(fontSize = 12.sp)) }, textStyle = TextStyle(fontSize = 15.sp), modifier = Modifier.height(58.dp).padding(vertical = 0.dp))
        }

        Row()
        {
            welcomeText(stringResource(R.string.book_publicationyear))
            OutlinedTextField(value = text4, onValueChange = { text4 = it }, label = { Text(stringResource(R.string.book_publicationyear), style = TextStyle(fontSize = 12.sp)) }, textStyle = TextStyle(fontSize = 15.sp), modifier = Modifier.height(58.dp).padding(vertical = 0.dp))
        }

        Row()
        {
            welcomeText(stringResource(R.string.authors_title_text))

            var authorsList = ArrayList<String>()
            authorsList.add("Doe John")
            authorsList.add("Fowler Martin")
            authorsList.add("Γεωργιάδης Απόστολος")
            authorsList.add("Βυζάντιος Χρήστος")
            authorsList.add("Αβέρωφ Ευάγγελος")

            multiselectiorMenu(authorsList)
        }

        displayButton(R.string.complete_registration, 50, 200, viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun optionMenu()
{
    val publishers = arrayOf("Addison Wesley", "McGraw-Hill Education")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(publishers[0]) }

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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun multiselectiorMenu(authorsList: List<String>)
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
            authorsList.forEach { author ->
                AnimatedContent(targetState = selectedAuthors.contains(author), label = "Animate the selected item")
                { isSelected ->
                    if (isSelected)
                    {
                        DropdownMenuItem(
                            text = { Text(text = author) },
                            onClick = { selectedAuthors.remove(author) },
                            leadingIcon = { Icon(imageVector = Icons.Rounded.Check, contentDescription = null) }
                        )
                    }
                    else
                    {
                        DropdownMenuItem(text = { Text(text = author) }, onClick = { selectedAuthors.add(author) },)
                    }
                }
            }
        }
    }
}