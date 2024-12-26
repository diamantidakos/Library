package com.mgiandia.library.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.mgiandia.library.view.Author.AddEditAuthor.AddEditAuthorViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun drawAddEditAuthorPage(modifier: Modifier = Modifier, viewModel: AddEditAuthorViewModel)
{
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }

    Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top), horizontalAlignment = Alignment.CenterHorizontally)
    {
        welcomeText(stringResource(R.string.basic_info))

        Row()
        {
            welcomeText(stringResource(R.string.first_name))
            OutlinedTextField(value = firstName, onValueChange = { firstName = it; viewModel.setFirstName(it) }, label = { Text(stringResource(R.string.first_name), style = TextStyle(fontSize = 12.sp)) }, textStyle = TextStyle(fontSize = 15.sp), modifier = Modifier.height(58.dp).padding(vertical = 0.dp))
        }

        Row()
        {
            welcomeText(stringResource(R.string.last_name))
            OutlinedTextField(value = lastName, onValueChange = { lastName = it; viewModel.setLastName(it) }, label = { Text(stringResource(R.string.last_name), style = TextStyle(fontSize = 12.sp)) }, textStyle = TextStyle(fontSize = 15.sp), modifier = Modifier.height(58.dp).padding(vertical = 0.dp))
        }

        displayButton(R.string.complete_registration, 50, 200, viewModel)
    }
}