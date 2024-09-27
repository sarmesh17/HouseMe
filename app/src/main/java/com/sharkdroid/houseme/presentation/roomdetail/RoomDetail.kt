package com.sharkdroid.houseme.presentation.roomdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sharkdroid.houseme.R
import com.sharkdroid.houseme.presentation.roomdetail.components.food.card.FoodCardDesign
import com.sharkdroid.houseme.presentation.roomdetail.components.food.model.FoodData

@Composable
@Preview(showSystemUi = true)
fun HotelDetail() {

    val scrollState = rememberScrollState()

    val gradient = Brush.linearGradient(
        colors = listOf(
            colorResource(id = R.color.AzureBlue),
            colorResource(id = R.color.VividAquaGreen)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
        )
        {

            Image(
                painter = painterResource(id = R.drawable.homestayimage),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Icon(
                        painter = painterResource(id = R.drawable.leftarrow),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Whoopers", fontSize = 26.sp, color = Color.White)
                }

                Icon(
                    painter = painterResource(id = R.drawable.share),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )

            }
            // rating
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 12.dp, start = 16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.height(25.dp)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "3.9",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                ) {

                    Text(
                        text = "24/100 people like this",
                        color = Color.White,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.navigation_icon),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(16.dp))

                    Text(text = "Goa,India", color = Color.White)

                }
            }

        }

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {


            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(12.dp)
            ) {

                // Tab Button
                // Review btn
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    modifier = Modifier
                        .border(
                            1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
                        )
                        .height(40.dp)
                ) {
                    Text(text = "Review")
                }

                // photo button
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    modifier = Modifier
                        .border(
                            1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(topStart = 0.dp, bottomStart = 0.dp)
                        )
                        .height(40.dp)
                ) {
                    Text(text = "Photo")
                }

                // photo button
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    modifier = Modifier
                        .border(
                            1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(
                                topStart = 0.dp,
                                bottomStart = 0.dp,
                                topEnd = 8.dp,
                                bottomEnd = 8.dp
                            )
                        )
                        .height(40.dp)
                ) {
                    Text(text = "Near by")
                }


            }

        }

        // Hotel Description
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Room Description", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.hotel_description),
                lineHeight = 18.sp,
                textAlign = TextAlign.Center,
                color = Color.DarkGray
            )

        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.Light_Mist))
                .height(120.dp)
        ) {

            Text(
                text = "ROOM FACILITIES",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // wifi
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Icon(
                        painter = painterResource(id = R.drawable.wifi_icon),
                        contentDescription = null,
                        tint = colorResource(
                            id = R.color.Vivid_Sky_Blue
                        ), modifier = Modifier.size(36.dp)
                    )

                    Text(text = "Free Wi-Fi")
                }


                // Fitness Center
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Icon(
                        painter = painterResource(id = R.drawable.fitness_icon),
                        contentDescription = null,
                        tint = colorResource(
                            id = R.color.Vivid_Sky_Blue
                        ), modifier = Modifier.size(36.dp)
                    )

                    Text(text = "Fitness Center")
                }


                // BreakFast
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Icon(
                        painter = painterResource(id = R.drawable.breakfast_icon),
                        contentDescription = null,
                        tint = colorResource(
                            id = R.color.Vivid_Sky_Blue
                        ), modifier = Modifier.size(36.dp)
                    )

                    Text(text = "Free BreakFast")
                }


                // kid-friendly
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Icon(
                        painter = painterResource(id = R.drawable.kid_friendly_icon),
                        contentDescription = null,
                        tint = colorResource(
                            id = R.color.Vivid_Sky_Blue
                        ), modifier = Modifier.size(36.dp)
                    )

                    Text(text = "Kid friendly")
                }

            }


        }

        // Hotel detail

        Column(modifier = Modifier.padding(16.dp)) {

            // location
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.navigation_icon),
                    contentDescription = null,
                    tint = colorResource(
                        id = R.color.Vivid_Sky_Blue
                    ),
                    modifier = Modifier.size(26.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Goa,India", fontSize = 20.sp)

            }

            Spacer(modifier = Modifier.height(16.dp))

            // call
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.mobile),
                    contentDescription = null,
                    tint = colorResource(
                        id = R.color.Vivid_Sky_Blue
                    ),
                    modifier = Modifier.size(26.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "+919868731385", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                // check-in
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.checkin),
                        contentDescription = null,
                        tint = colorResource(
                            id = R.color.Vivid_Sky_Blue
                        ),
                        modifier = Modifier.size(20.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Checkin 12 PM", fontSize = 20.sp)
                }


                // check-out
                Row{

                    Icon(
                        painter = painterResource(id = R.drawable.checkout),
                        contentDescription = null,
                        tint = colorResource(
                            id = R.color.Vivid_Sky_Blue
                        ),
                        modifier = Modifier.size(20.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Checkout 11AM", fontSize = 20.sp)
                }


            }


        }

        Spacer(modifier = Modifier.height(10.dp))


        Column(modifier = Modifier.padding(horizontal = 16.dp)) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 22.dp, end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(text = "FOOD", fontSize = 16.sp)

                Text(
                    text = "VIEW ALL",
                    color = colorResource(id = R.color.Vivid_Sky_Blue),
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(12.dp))

            val foodList = listOf(
                FoodData(R.drawable.fish_thali, "Fish Thali"),
                FoodData(R.drawable.fish_thali, "Fish Thali"),
                FoodData(R.drawable.fish_thali, "Fish Thali"),

                )

            LazyRow() {
                items(foodList) {

                    Spacer(modifier = Modifier.width(24.dp))
                    FoodCardDesign(foodData = it)

                }
            }


        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Color.Gray)
        ) {

            Button(
                onClick = { /*TODO*/ },
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    Color.White
                ), modifier = Modifier.size(250.dp,80.dp)
            ) {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(text = "RS 800", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "AVG/NIGHT",)

                }


            }

            // booknow button


            Box(modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(brush = gradient), contentAlignment = Alignment.Center){

                Text(text = "BookNow", fontSize = 20.sp, color = Color.White)

            }


        }
        HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)


    }


}



