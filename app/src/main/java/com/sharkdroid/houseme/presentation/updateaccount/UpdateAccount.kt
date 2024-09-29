package com.sharkdroid.houseme.presentation.updateaccount

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sharkdroid.houseme.R

@Composable

fun UpdateAccount(navController: NavHostController) {
    val horizontalGradient = Brush.linearGradient(
        colors = listOf(
            colorResource(id = R.color.Vivid_Sky_Blue),
            colorResource(id = R.color.Sea_Green)
        )
    )
    val firstname = remember {
        mutableStateOf("")
    }
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(brush = horizontalGradient)
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .padding(10.dp)
                    .background(color = Color.White)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow),
                    contentDescription = null,
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 30.dp), horizontalArrangement = Arrangement.Center
            ) {

                Box(modifier = Modifier.size(100.dp).padding(top = 15.dp).clip(CircleShape), contentAlignment = Alignment.Center, ) {
                    Image(
                        painter = painterResource(id = R.drawable.profileimg),
                        contentDescription = null, modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Text(text = "Update Account", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Looks like you want to edit your account or connect with social accounts",
                fontSize = 17.sp, color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = firstname.value,
                onValueChange = { firstname.value = it },
                label = {
                    Text(text = "Frist Name")


                }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = firstname.value,
                onValueChange = { firstname.value = it },
                label = {
                    Text(text = "Last Name")


                }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = firstname.value,
                onValueChange = { firstname.value = it },
                label = {
                    Text(text = "Enter Email")


                }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = horizontalGradient, shape = RoundedCornerShape(12.dp))
                    .height(45.dp), contentAlignment = Alignment.Center
            ) {


                Text(text = "Update Account", fontSize = 20.sp, color = Color.White)

            }
        }
    }
}
