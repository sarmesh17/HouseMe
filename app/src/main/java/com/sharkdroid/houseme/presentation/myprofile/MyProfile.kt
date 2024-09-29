package com.sharkdroid.houseme.presentation.myprofile

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sharkdroid.houseme.R

@Composable
@Preview(showSystemUi= true)
fun MyProfile() {
    val horizontalGradient = Brush.linearGradient(
        colors = listOf(
            colorResource(id = R.color.Vivid_Sky_Blue),
            colorResource(id = R.color.Sea_Green)
        )
    )
    Column {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(brush = horizontalGradient),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.leftarrow),
                contentDescription = null,
                Modifier
                    .size(20.dp), tint = Color.White

            )
            Spacer(modifier = Modifier.width(100.dp))
            Text(
                text = "Your Profile",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.house2),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.width(40.dp))
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Krisha Gaire", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "9844366349", color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "gkrisha66@gmail.com", color = Color.Gray)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(brush = horizontalGradient, shape = RoundedCornerShape(12.dp))
                .height(45.dp), contentAlignment = Alignment.Center
        ) {

Row(modifier = Modifier
    .fillMaxWidth()
    .padding(start = 15.dp)){
    Icon(
        painter = painterResource(id = R.drawable.edittext),
        contentDescription = null,
        Modifier
            .size(20.dp), tint = Color.White

    )
Spacer(modifier = Modifier.width(30.dp))
    Text(text = "Edit Profile", fontSize = 20.sp, color = Color.White)
    Spacer(modifier = Modifier.width(170.dp))
    Icon(
        painter = painterResource(id = R.drawable.rightarrow),
        contentDescription = null,
        Modifier
            .size(20.dp), tint = Color.White

    )
    Spacer(modifier = Modifier.height(10.dp))

}

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(brush = horizontalGradient, shape = RoundedCornerShape(12.dp))
                .height(45.dp), contentAlignment = Alignment.Center
        ) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp)){
                Icon(
                    painter = painterResource(id = R.drawable.bookingonline),
                    contentDescription = null,
                    Modifier
                        .size(20.dp), tint = Color.White

                )
                Spacer(modifier = Modifier.width(30.dp))
                Text(text = "Booking History", fontSize = 20.sp, color = Color.White)
                Spacer(modifier = Modifier.width(130.dp))
                Icon(
                    painter = painterResource(id = R.drawable.rightarrow),
                    contentDescription = null,
                    Modifier
                        .size(20.dp), tint = Color.White

                )
                Spacer(modifier = Modifier.height(10.dp))

            }

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(brush = horizontalGradient, shape = RoundedCornerShape(12.dp))
                .height(45.dp), contentAlignment = Alignment.Center
        ) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp)){
                Icon(
                    painter = painterResource(id = R.drawable.share),
                    contentDescription = null,
                    Modifier
                        .size(20.dp), tint = Color.White

                )
                Spacer(modifier = Modifier.width(30.dp))
                Text(text = "Share App", fontSize = 20.sp, color = Color.White)
                Spacer(modifier = Modifier.width(170.dp))
                Icon(
                    painter = painterResource(id = R.drawable.rightarrow),
                    contentDescription = null,
                    Modifier
                        .size(20.dp), tint = Color.White

                )
                Spacer(modifier = Modifier.height(10.dp))

            }

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(brush = horizontalGradient, shape = RoundedCornerShape(12.dp))
                .height(45.dp), contentAlignment = Alignment.Center
        ) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp), horizontalArrangement = Arrangement.Center){


                Text(text = "Log Out", fontSize = 20.sp, color = Color.White)



                Spacer(modifier = Modifier.height(10.dp))

            }

        }
    }

}