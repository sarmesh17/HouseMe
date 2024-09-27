package com.sharkdroid.houseme.presentation.roomscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sharkdroid.houseme.R

@Preview(showSystemUi =  true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomScreen() {
    val robotoFamily = FontFamily(
        Font(R.font.roboto_medium)
    )

    val horizontalGradient = Brush.linearGradient(
        colors = listOf(colorResource(id = R.color.Vivid_Sky_Blue), colorResource(id = R.color.Sea_Green))
    )

    Scaffold(
        topBar = @androidx.compose.runtime.Composable {
            TopAppBar(title = { Text(text = "Available Rooms", color = Color.White, fontFamily = robotoFamily, fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .size(50.dp, 50.dp)

                    ) {
                        //icon
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow),
                            tint = Color.White,
                            contentDescription = null,
                            modifier = Modifier
                                .size(32.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(Color.Transparent),
                modifier = Modifier.background(brush = horizontalGradient)
            )

        },
    ) {it ->
        Modifier.padding(it)

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(text = "Loading...")

        }
        Box(
            modifier = Modifier
                .padding(start = 300.dp, top = 680.dp)
                .size(65.dp)
                .background(
                    brush = horizontalGradient,
                    shape = RoundedCornerShape(16.dp)
                )

        ) {
            Text(
                text = "+",
                fontSize = 50.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
