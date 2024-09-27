package com.sharkdroid.houseme.presentation.payment.component.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sharkdroid.houseme.R
import com.sharkdroid.houseme.presentation.payment.component.models.CardContext

@Composable
fun PaymentCard(cardContext: CardContext){
    val gradient = Brush.linearGradient(
        colors = listOf(
            colorResource(id = R.color.AzureBlue),
            colorResource(id = R.color.VividAquaGreen)
        )
    )
    var cardNo by remember {
        mutableStateOf("")
    }



    Box (modifier = Modifier
        .height(270.dp)
        .fillMaxWidth()
        .padding(all = 10.dp)
        .background(brush = gradient,
            shape = RoundedCornerShape(10.dp))) {
        Column {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.chip), contentDescription = null,
                    modifier = Modifier.size(50.dp), tint = colorResource(
                        id = R.color.Goldenrod,
                    )
                )
                Text(
                    text = cardContext.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            TextField(
                value = cardNo,
                onValueChange = { newText -> cardNo = newText },
                colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent,unfocusedIndicatorColor = Color.White),
                label = { Text(text = cardContext.cardNo, color = Color.LightGray) }, modifier = Modifier.padding(start = 8.dp))
            TextField(
                value = cardNo,
                onValueChange = { newText -> cardNo = newText },
                colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent,unfocusedIndicatorColor = Color.White),
                label = { Text(text = cardContext.cardHolderName,color = Color.LightGray) }, modifier = Modifier.padding(start = 8.dp))
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)){


                TextField(
                    value = cardNo,
                    onValueChange = { newText -> cardNo = newText },
                    colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent, unfocusedIndicatorColor = Color.White),
                    label = { Text(text = cardContext.expiryDate,color = Color.LightGray) }, modifier = Modifier.width(123.dp))
                Spacer(modifier = Modifier.width(70.dp))

                TextField(
                    value = cardNo,
                    onValueChange = { newText -> cardNo = newText },
                    colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent,unfocusedIndicatorColor = Color.White),
                    label = { Text(text =cardContext.cvv,color = Color.LightGray) }, modifier = Modifier.width(123.dp))
            }

        }
    }
}

