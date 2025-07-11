package com.mgiandia.library.ui.composable

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mgiandia.library.domain.Item
import com.mgiandia.library.view.Items.ManageItems.ManageItemsViewModel

@SuppressLint("ComposableNaming")
@Composable
fun drawManageItemsPage(modifier: Modifier = Modifier, viewModel: ManageItemsViewModel)
{
    val allBooks: ArrayList<Item> = ArrayList(viewModel.itemsByBookTitle)
    drawItemListScreen(modifier, viewModel, allBooks)
}

@SuppressLint("ComposableNaming")
@Composable
fun drawManageItemsPageSearch(modifier: Modifier = Modifier, viewModel: ManageItemsViewModel)
{
    val allBooks = viewModel.items.value
    if (allBooks != null)
    {
        drawItemListScreen(modifier, viewModel, allBooks)
    }
}