package com.mgiandia.library.ui.composable

import androidx.compose.ui.platform.ComposeView

fun showEmptyView(composeView: ComposeView){
    composeView.apply {
        setContent {
            Greeting("Hello world")
        }
    }
}