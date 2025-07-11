package com.mgiandia.library.ui.composable

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mgiandia.library.domain.Loan
import com.mgiandia.library.view.Returns.ManageReturns.ManageReturnsViewModel

@SuppressLint("ComposableNaming")
@Composable
fun drawManageReturnsPage(modifier: Modifier = Modifier, viewModel: ManageReturnsViewModel)
{
    val borrowerID = viewModel.borrowerID.value
    if (borrowerID != null)
    {
        val allLoans: ArrayList<Loan> = ArrayList(viewModel.getAllLoans(borrowerID))
        drawManageReturnsScreen(modifier, viewModel, allLoans)
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun drawManageReturnsPageSearch(modifier: Modifier = Modifier, viewModel: ManageReturnsViewModel)
{
    val allLoans = viewModel.loans.value
    if (allLoans != null)
    {
        drawManageReturnsScreen(modifier, viewModel, allLoans)
    }
}