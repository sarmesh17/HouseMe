package com.sharkdroid.houseme.presentation.payment.component.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sharkdroid.houseme.R
import com.sharkdroid.houseme.presentation.payment.component.models.PaymentButton

@Composable

fun PaymentButton(paymentButton: PaymentButton,onClick: () -> Unit){
    val gradient = Brush.linearGradient(
        colors = listOf(
            colorResource(id = R.color.AzureBlue),
            colorResource(id = R.color.VividAquaGreen)
        )
    )


Box(

    modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .background(brush = gradient, shape = RoundedCornerShape(10.dp))
        .clickable { onClick() }
        .padding(16.dp)
){
    Text(text = paymentButton.button,
        fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.White,
        modifier = Modifier.align(Alignment.Center))
}



}


