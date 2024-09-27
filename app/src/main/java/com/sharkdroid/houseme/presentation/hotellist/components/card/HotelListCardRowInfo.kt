package com.sharkdroid.houseme.presentation.hotellist.components.card

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sharkdroid.houseme.R
import com.sharkdroid.houseme.presentation.hotellist.components.model.HotelListCardModel

@Composable

fun TryDesign(hotelListCardModel: HotelListCardModel) {
    val gradient = Brush.linearGradient(
        colors = listOf(
            colorResource(id = R.color.AzureBlue),
            colorResource(id = R.color.VividAquaGreen)
        )
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
    ) {
        Row {


            Image(
                painter = painterResource(id = hotelListCardModel.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
                    .padding(vertical = 5.dp, horizontal = 4.dp)
                    .clip(shape = RoundedCornerShape(12.dp)), contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(all = 8.dp)) {


                Text(text = hotelListCardModel.header, fontSize = 10.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(2.dp))
                Row(modifier = Modifier) {


                    Icon(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = null,
                        Modifier.size(10.dp), tint = colorResource(id = R.color.Goldenrod)

                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "3.9", fontSize = 10.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.size(15.dp))
                    Text(text = "Reviews(200)", fontSize = 10.sp, color = Color.Gray)
                }
                Text(
                    text = hotelListCardModel.description,
                    fontSize = 10.sp,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row {


                    Text(
                        text = hotelListCardModel.discount,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(
                            id = R.color.Goldenrod
                        )
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Row {
                        Text(text = "RS", fontSize = 10.sp, fontWeight = FontWeight.SemiBold)
                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = hotelListCardModel.price,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(modifier = Modifier.width(20.dp))
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,

                    ) {


                        Box(
                            modifier = Modifier
                                .height(120.dp)
                                .width(70.dp)
                                .background(
                                    brush = gradient,
                                    shape = RoundedCornerShape(2.dp)
                                ),


                        )

                        {
                            Text(text = hotelListCardModel.button,
                                fontSize = 8.sp, modifier = Modifier.align(Alignment.Center))
                        }
                    }
                }

            }

        }


    }
}