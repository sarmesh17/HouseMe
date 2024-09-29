package com.sharkdroid.houseme.presentation.houselist.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.sharkdroid.houseme.R
import com.sharkdroid.houseme.domain.model.RoomData
import com.sharkdroid.houseme.presentation.navigation.Routes

@Composable
fun HouseListCardRowInfo(
    roomData: RoomData, navController: NavController
) {
    val gradient = Brush.linearGradient(
        colors = listOf(
            colorResource(id = R.color.AzureBlue),
            colorResource(id = R.color.VividAquaGreen)
        )
    )
    val robotoFamily = FontFamily(
        Font(R.font.roboto_medium)
    )
    Column(
        modifier = Modifier
            .wrapContentSize()
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ){

            AsyncImage(
                model =roomData.roomImageUrl,  // Provide a default image URL
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
                    .padding(vertical = 5.dp, horizontal = 4.dp)
                    .clip(shape = RoundedCornerShape(12.dp)), contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(all = 8.dp)) {

                    roomData.roomName?.let {
                        Text(
                            text = it,  // Provide default text
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = robotoFamily
                        )
                    }


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = null,
                        Modifier.size(10.dp), tint = colorResource(id = R.color.Goldenrod)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "3.9", fontSize = 16.sp, color = Color.Gray, fontFamily = robotoFamily)
                    Spacer(modifier = Modifier.width(32.dp))
                    Text(text = "Reviews(200)", fontSize = 16.sp, color = Color.Gray, fontFamily = robotoFamily)
                }

                roomData.description?.let {
                    Text(
                        text = it,  // Handle null descriptions
                        fontSize = 16.sp,
                        color = Color.DarkGray,
                        fontFamily = robotoFamily
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))

                Row {
                    roomData.discount?.let {
                        Text(
                            text = "$it %",  // Handle null discounts
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = colorResource(id = R.color.Goldenrod),
                            fontFamily = robotoFamily
                        )
                    }

                    Spacer(modifier = Modifier.width(20.dp))

                    Row {
                        Text(text = "RS", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, fontFamily = robotoFamily)
                        Spacer(modifier = Modifier.width(4.dp))

                        roomData.price?.let {
                            Text(
                                text = it,  // Handle null prices
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = robotoFamily
                            )
                        }

                    }
                    Spacer(modifier = Modifier.width(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                        Spacer(modifier = Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .height(20.dp)
                                .width(70.dp)
                                .background(
                                    brush = gradient,
                                    shape = RoundedCornerShape(2.dp)
                                ).clickable { navController.navigate(Routes.PaymentScreen) }
                        ) {
                            Text(
                                text = "Book Now",
                                fontSize = 12.sp,
                                modifier = Modifier.align(Alignment.Center),
                                fontFamily = robotoFamily
                            )
                        }
                    }
                }

            }
        }
        HorizontalDivider()
    }
}

