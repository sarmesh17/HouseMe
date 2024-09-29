package com.sharkdroid.houseme.presentation.places.components.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sharkdroid.houseme.R
import com.sharkdroid.houseme.presentation.places.components.model.PlaceCardData

@Composable

fun PlacesCardDesign(placeCardData: PlaceCardData) {
    val linearGradient = Brush.linearGradient(
        colors = listOf(
            colorResource(id = R.color.Vivid_Sky_Blue),
            colorResource(id = R.color.Caribbean_Green)
        )
    )


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .padding(top = 10.dp)
            .height(300.dp)
    ) {
        Image(
            painter = painterResource(id = placeCardData.PlaceImg), contentDescription = null,
            Modifier
                .fillMaxWidth()
                .height(150.dp), contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(text = placeCardData.PlaceName, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = placeCardData.PlaceDescription

            )


        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .padding(top = 6.dp)
            .background(brush = linearGradient, shape = RoundedCornerShape(12.dp))
            .height(45.dp), contentAlignment = Alignment.Center
    ) {


        Text(text = "Show", fontSize = 20.sp, color = Color.White)

    }


}
