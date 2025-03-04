package com.mgiandia.library.ui.composable

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mgiandia.library.domain.Book
import com.mgiandia.library.domain.Item
import com.mgiandia.library.view.Items.ManageItems.ManageItemsViewModel

@SuppressLint("ComposableNaming")
@Composable
fun drawManageItemsPage(modifier: Modifier = Modifier, viewModel: ManageItemsViewModel)
{
    val allBooks: ArrayList<Item> = ArrayList(viewModel.allItems)
    drawItemListScreen(modifier, viewModel, allBooks)
}