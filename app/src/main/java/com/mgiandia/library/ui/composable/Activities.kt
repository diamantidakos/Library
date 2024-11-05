package com.mgiandia.library.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import com.mgiandia.library.ui.theme.LibraryTheme

fun showEmptyView(composeView: ComposeView){
    composeView.apply {
        setContent {
            Greeting("Hello world")
        }
    }
}

fun showHomePageView(composeView: ComposeView){
    composeView.apply {
        setContent {
            // TODO: Reuse the code involving LibraryTheme, Scaffold etc
            LibraryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.DarkGray)
                    )

                    drawHomePage(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}