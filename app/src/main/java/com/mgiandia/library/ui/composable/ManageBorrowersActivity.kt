package com.mgiandia.library.ui.composable

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mgiandia.library.domain.Borrower
import com.mgiandia.library.view.Borrower.ManageBorrowers.ManageBorrowersViewModel

@SuppressLint("ComposableNaming")
@Composable
fun drawManageBorrowersPage(modifier: Modifier = Modifier, viewModel: ManageBorrowersViewModel)
{
    val allBorrowers: ArrayList<Borrower> = ArrayList(viewModel.allBorrowers)
    drawBorrowerListScreen(modifier, viewModel, allBorrowers)
}