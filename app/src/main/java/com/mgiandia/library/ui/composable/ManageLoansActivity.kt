package com.mgiandia.library.ui.composable

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mgiandia.library.domain.Loan
import com.mgiandia.library.view.Loans.ManageLoans.ManageLoansViewModel

@SuppressLint("ComposableNaming")
@Composable
fun drawManageLoansPage(modifier: Modifier = Modifier, viewModel: ManageLoansViewModel)
{
    val borrowerID = viewModel.borrowerID.value
    if (borrowerID != null)
    {
        val allLoans: ArrayList<Loan> = ArrayList(viewModel.getAllLoans(borrowerID))
        drawLoanListScreen(modifier, viewModel, allLoans)
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun drawManageLoansPageSearch(modifier: Modifier = Modifier, viewModel: ManageLoansViewModel)
{
    val allLoans = viewModel.loans.value
    if (allLoans != null)
    {
        drawLoanListScreen(modifier, viewModel, allLoans)
    }
}