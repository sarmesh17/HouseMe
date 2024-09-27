package com.sharkdroid.houseme.presentation.payment.components

import PaymentImageRow
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sharkdroid.houseme.R
import com.sharkdroid.houseme.presentation.payment.component.models.PaymentImage

@Composable
@Preview(showSystemUi = true)
fun PaymentScreenn() {
    val paymentImgList = listOf(
        PaymentImage(payImg = R.drawable.esewalogoo),
        PaymentImage(payImg = R.drawable.khaltilogo),
        PaymentImage(payImg = R.drawable.mastercardlogo),
        PaymentImage(payImg = R.drawable.visalogoimg)
    )

    Column(modifier = Modifier.fillMaxSize())
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 12.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.leftarrow),
                contentDescription = null, Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(text = "Payment method", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(140.dp))
            Icon(
                painter = painterResource(id = R.drawable.list),
                contentDescription = null, Modifier.size(25.dp)
            )
        }

        LazyRow(modifier = Modifier.fillMaxWidth().padding(all = 10.dp)) {
            items(paymentImgList) { image ->
                PaymentImageRow(paymentImage = image)
            }


        }
    }
}