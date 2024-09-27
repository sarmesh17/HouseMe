package com.sharkdroid.houseme.presentation.forgetpasswordscreen

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@Preview(showSystemUi = true)
@Composable
fun ForgetPasswordScreen() {

    val verticalGradient = Brush.verticalGradient(
        colors = listOf(
            colorResource(id = R.color.Caribbean_Green),
            colorResource(id = R.color.Royal_Blue)
        )
    )


    val linearGradient = Brush.linearGradient(
        colors = listOf(
            colorResource(id = R.color.Vivid_Sky_Blue),
            colorResource(id = R.color.Caribbean_Green)
        )
    )

    var email by remember {

        mutableStateOf("")
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = verticalGradient),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            horizontalArrangement = Arrangement.Start, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp)
        ) {

            Text(
                text = "Forgot Password?",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .width(350.dp)
                .height(620.dp)
                .background(color = Color.White, shape = RoundedCornerShape(18.dp))
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.Center

        ) {

            Column(
                modifier = Modifier.fillMaxSize().padding(top = 50.dp, start = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Please enter your registered email address to recover your password ",
                    color = Color.Gray, fontSize = 19.sp
                )


                // email-text field
                TextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    label = {
                        Text(text = "Email", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                    },
                    leadingIcon = {

                        Icon(
                            painter = painterResource(id = R.drawable.email_icon),
                            contentDescription = null,
                            tint = colorResource(
                                id = R.color.Vivid_Sky_Blue
                            ), modifier = Modifier.size(20.dp)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.LightGray,
                    ),


                    )

                Spacer(modifier = Modifier.height(16.dp))



                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(brush = linearGradient, shape = RoundedCornerShape(12.dp))
                        .height(45.dp), contentAlignment = Alignment.Center
                ) {


                    Text(text = "Sign In", fontSize = 20.sp, color = Color.White)

                }


            }

        }

    }
}