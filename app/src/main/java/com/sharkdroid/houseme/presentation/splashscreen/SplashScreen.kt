package com.sharkdroid.houseme.presentation.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sharkdroid.houseme.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout: ()-> Unit) {
    LaunchedEffect (Unit){
        delay(3000)
        onTimeout()
    }
    val gradient = Brush.linearGradient(
        colors = listOf(colorResource(id = R.color.Sea_Green), colorResource(id = R.color.Art_Blue))
    )
    val robotoFamily = FontFamily(
        Font(R.font.roboto_medium)
    )

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradient),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.component),
            contentDescription = null,
            modifier = Modifier.size(261.dp,178.dp)
        )
        Text(
            text = "House Me",
            fontSize = 59.sp,
            color = Color.White,
            fontFamily = robotoFamily
        )
    }
    Box (modifier = Modifier.padding(top = 230.dp, start = 200.dp)){
        Image(
            painter = painterResource(id = R.drawable.cloud),
            contentDescription =null,
            modifier = Modifier.size(100.dp)
        )
    }
    Box (modifier = Modifier.padding(top = 200.dp, start = 130.dp)){
        Image(
            painter = painterResource(id = R.drawable.cloud),
            contentDescription =null,
            modifier = Modifier.size(100.dp)
        )
    }
    Box (modifier = Modifier.padding(top = 260.dp, start = 80.dp)){
        Image(
            painter = painterResource(id = R.drawable.cloud),
            contentDescription =null,
            modifier = Modifier.size(100.dp)
        )
    }
}