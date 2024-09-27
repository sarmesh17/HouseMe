package com.sharkdroid.houseme.presentation.roomdetail.components.food.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sharkdroid.houseme.presentation.roomdetail.components.food.model.FoodData

@Composable
fun FoodCardDesign(foodData: FoodData) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Card (modifier = Modifier
            .size(90.dp, 77.dp)
            .clip(shape = RoundedCornerShape(12.dp))){

            Image(painter = painterResource(id = foodData.imgRes), contentDescription = null, contentScale = ContentScale.Crop)
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = foodData.foodName)


    }

}