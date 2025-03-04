package com.mgiandia.library.ui.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mgiandia.library.domain.Publisher
import com.mgiandia.library.view.Publisher.ManagePublishers.ManagePublishersViewModel

@SuppressLint("ComposableNaming")
@Composable
fun drawManagePublishersPage(modifier: Modifier = Modifier, viewModel: ManagePublishersViewModel)
{
    val allPublishers: ArrayList<Publisher> = ArrayList(viewModel.allPublishers)
    drawPublisherListScreen(modifier, viewModel, allPublishers)
}