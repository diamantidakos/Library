package com.mgiandia.library.ui.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mgiandia.library.R
import com.mgiandia.library.view.Author.AddEditAuthor.AddEditAuthorViewModel
import com.mgiandia.library.view.Book.AddEditBook.AddEditBookViewModel
import com.mgiandia.library.view.HomePage.HomePageViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun drawHomePage(modifier: Modifier = Modifier, viewModel: HomePageViewModel) {
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        displayLibraryIcon()
        welcomeText(stringResource(R.string.welcome_message))
        displayButton(R.string.manage_borrowers, 50, 400, viewModel)

        Row()
        {
            displayButton(R.string.manage_books, 50, 200, viewModel)
            displayButton(R.string.manage_items, 50, 200, viewModel)
        }

        Row()
        {
            displayButton(R.string.manage_loans, 50, 200, viewModel)
            displayButton(R.string.manage_returns, 50, 200, viewModel)
        }

        Row()
        {
            displayButton(R.string.manage_authors, 50, 200, viewModel)
            displayButton(R.string.manage_publishers, 50, 200, viewModel)
        }

        /*
        FlowRow(modifier = Modifier.padding(4.dp), horizontalArrangement = Arrangement.spacedBy(4.dp), maxItemsInEachRow = 2) {
            val itemModifier = Modifier
                .padding(4.dp)
                .height(80.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Blue)
            repeat(6)
            {
                item ->

                if (item == 0)
                {
                    displayLibraryIcon()
                }
                else
                {
                    //Spacer(modifier = itemModifier.fillMaxWidth())
                }

                /*
                if ((item + 1) % 3 == 0)
                {
                    Spacer(modifier = itemModifier.fillMaxWidth())
                }
                else
                {
                    Spacer(modifier = itemModifier.weight(0.5f))
                }
                */
            }
        }
        */
    }
}

@Composable
fun displayLibraryIcon() {
    Image(
        painter = painterResource(id = R.drawable.ic_bookshelf),
        contentDescription = "library icon",
        modifier = Modifier.width(167.dp).height(124.dp)
    )
}

@Composable
fun welcomeText(txt: String) {
    Text(txt, fontSize = 20.sp)
}

@Composable
fun displayButton(@StringRes textResId: Int, height : Int, width: Int, viewModel: HomePageViewModel)
{
    Button(
        onClick = {
            viewModel.buttonClicked(textResId)
        },
        modifier = Modifier.height(height.dp).width(width.dp).padding(5.dp),
        enabled = true,
        colors = ButtonDefaults.buttonColors(Color.Gray),
        shape = RoundedCornerShape(0, 0, 0, 0)
    )
    {
        Text(text = stringResource(textResId), fontSize = 16.sp, color = Color.White)
    }
}

@Composable
fun displayButton(@StringRes textResId: Int, height : Int, width: Int, viewModel: AddEditBookViewModel) // mporw kai sthn antistoixh kt klash
{
    Button(
        onClick = {
            viewModel.buttonClicked(textResId)
        },
        modifier = Modifier.height(height.dp).width(width.dp).padding(5.dp),
        enabled = true,
        colors = ButtonDefaults.buttonColors(Color.Gray),
        shape = RoundedCornerShape(0, 0, 0, 0)
    )
    {
        Text(text = stringResource(textResId), fontSize = 16.sp, color = Color.White)
    }
}

@Composable
fun displayButton(@StringRes textResId: Int, height : Int, width: Int, viewModel: AddEditAuthorViewModel) // mporw kai sthn antistoixh kt klash
{
    Button(
        onClick = {
            viewModel.buttonClicked(textResId)
        },
        modifier = Modifier.height(height.dp).width(width.dp).padding(5.dp),
        enabled = true,
        colors = ButtonDefaults.buttonColors(Color.Gray),
        shape = RoundedCornerShape(0, 0, 0, 0)
    )
    {
        Text(text = stringResource(textResId), fontSize = 16.sp, color = Color.White)
    }
}