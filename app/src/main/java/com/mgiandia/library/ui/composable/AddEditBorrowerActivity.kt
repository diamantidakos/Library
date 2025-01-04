package com.mgiandia.library.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mgiandia.library.R
import com.mgiandia.library.view.Borrower.AddEditBorrower.AddEditBorrowerViewModel

@Composable
fun drawAddEditBorrower(modifier: Modifier = Modifier, viewModel: AddEditBorrowerViewModel)
{
    Column(modifier = modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text = stringResource(id = R.string.basic_info), fontSize = 18.sp, modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)

        displayRow(R.string.first_name, viewModel, "name")
        Spacer(modifier = Modifier.height(16.dp))

        displayRow(R.string.last_name, viewModel, "surname")
        Spacer(modifier = Modifier.height(16.dp))

        val userTypes = ArrayList<String>()
        userTypes.add(stringResource(R.string.undergraduate_student))
        userTypes.add(stringResource(R.string.graduate_student))
        userTypes.add(stringResource(R.string.master_student))
        userTypes.add(stringResource(R.string.dep_member))
        userTypes.add(stringResource(R.string.professor))

        displayRow(R.string.category, true, userTypes, viewModel)
        Spacer(modifier = Modifier.height(16.dp))

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