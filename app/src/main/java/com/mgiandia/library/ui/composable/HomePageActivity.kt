package com.mgiandia.library.ui.composable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mgiandia.library.R
import com.mgiandia.library.ui.theme.LibraryTheme

class HomePageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun drawHomePage(modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        displayLibraryIcon()
        welcomeText(stringResource(R.string.welcome_message))
        displayButton("Δανειζόμενοι")


        Row()
        {
            displayButton("Βιβλία")
            displayButton("Αντίτυπα")
        }

        Row()
        {
            displayButton("Δάνεια")
            displayButton("Επιστροφές")
        }

        Row()
        {
            displayButton("Συγγραφείς")
            displayButton("Εκδοτικοί Οίκοι")
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
fun displayButton(txt: String) {
    Button(
        onClick = {
            //I will continue...
        },
        modifier = Modifier.padding(16.dp),
        enabled = true,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Green,
            containerColor = Color.DarkGray
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
        border = BorderStroke(width = 2.dp, brush = SolidColor(Color.Green)),
        contentPadding = PaddingValues(start = 20.dp, top = 12.dp, end = 20.dp, bottom = 12.dp)
        //interactionSource = remember { MutableInteractionSource() }
    )
    {
        Text(text = txt, fontSize = 16.sp)
    }
}