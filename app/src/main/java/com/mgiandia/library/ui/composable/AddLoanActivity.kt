package com.mgiandia.library.ui.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mgiandia.library.R
import com.mgiandia.library.memorydao.BookDAOMemory
import com.mgiandia.library.view.Loans.AddLoan.AddLoanViewModel

@SuppressLint("ComposableNaming")
@Composable
fun drawAddLoan(modifier: Modifier = Modifier, viewModel: AddLoanViewModel)
{
    Column(modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top), horizontalAlignment = Alignment.CenterHorizontally)
    {
        welcomeText(stringResource(R.string.basic_info))
        Spacer(modifier = Modifier.height(16.dp))

        displayRow(R.string.borrower, viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        
        val bookDao = BookDAOMemory()
        val allBooks = bookDao.findAll()

        displayRow(R.string.book, viewModel, allBooks)
        Spacer(modifier = Modifier.height(16.dp))

        displayButton(R.string.complete_registration, 50, 200, viewModel)
    }
}