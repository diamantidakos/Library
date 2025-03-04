package com.mgiandia.library.ui.composable

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mgiandia.library.domain.Author
import com.mgiandia.library.view.Author.ManageAuthors.ManageAuthorsViewModel

@SuppressLint("ComposableNaming")
@Composable
fun drawManageAuthorsPage(modifier: Modifier = Modifier, viewModel: ManageAuthorsViewModel)
{
    val allAuthors: ArrayList<Author> = ArrayList(viewModel.allAuthors)
    drawAuthorListScreen(modifier, viewModel, allAuthors)
}