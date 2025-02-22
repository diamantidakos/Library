package com.mgiandia.library.ui.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mgiandia.library.R
import com.mgiandia.library.view.Items.ManageItems.ManageItemsViewModel

@SuppressLint("ComposableNaming")
@Composable
//fun ItemListScreen(items: List<String>, onSearchQueryChanged: (String) -> Unit, onAddNewItem: () -> Unit)
fun drawItemListScreen(modifier: Modifier = Modifier, viewModel: ManageItemsViewModel)
{
    var searchQuery by remember { mutableStateOf("") }

    val items = arrayListOf<String>()
    items.add("item 1") // temp data
    items.add("item 2")
    items.add("item 3")

    //var onSearchQueryChanged : String

    Column(modifier = Modifier.fillMaxSize().padding(12.dp))
    {
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp), horizontalArrangement = Arrangement.SpaceBetween)
        {
            searchBar(
                query = searchQuery,
                onQueryChanged = {
                    searchQuery = it
                    //onSearchQueryChanged(it)
                },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(10.dp))

            displayButton(R.string.add_new_item, 50, 200, viewModel)
        }

        LazyColumn(modifier = Modifier.fillMaxSize())
        {
            items(items)
            {
                item -> itemRow(item = item)
            }
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun searchBar(query: String, onQueryChanged: (String) -> Unit, modifier: Modifier = Modifier)
{
    TextField(
        value = query,
        onValueChange = onQueryChanged,
        modifier = modifier
            .height(40.dp),
        placeholder = { Text(stringResource(R.string.search)) }
    )
}

@SuppressLint("ComposableNaming")
@Composable
fun itemRow(item: String)
{
    Box(modifier = Modifier.fillMaxWidth().padding(8.dp).padding(12.dp))
    {
        Text(text = item)
    }
}