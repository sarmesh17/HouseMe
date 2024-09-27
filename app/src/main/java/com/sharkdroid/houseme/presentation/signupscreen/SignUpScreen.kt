package com.sharkdroid.houseme.presentation.signupscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sharkdroid.houseme.R
import com.sharkdroid.houseme.domain.model.Result
import com.sharkdroid.houseme.presentation.common.LoadingScreen
import com.sharkdroid.houseme.presentation.navigation.Routes
import com.sharkdroid.houseme.presentation.viewmodel.SignUpScreenViewModel

@Composable
fun SignUpForm(
    signUpScreenViewModel: SignUpScreenViewModel,
    navHostController: NavHostController
) {
    val gradient = Brush.linearGradient(
        colors = listOf(colorResource(id = R.color.Sea_Green), colorResource(id = R.color.Art_Blue))
    )

    val horizontalGradient = Brush.linearGradient(
        colors = listOf(
            colorResource(id = R.color.Vivid_Sky_Blue),
            colorResource(id = R.color.Sea_Green)
        )
    )
    var fullName by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var number by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    val robotoFamily = FontFamily(
        Font(R.font.roboto_medium)
    )

    val signUpResult = signUpScreenViewModel.signUpResult.collectAsState()

    when (signUpResult.value) {

        is Result.Default -> {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush = gradient)
                    .padding(top = 70.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp)
                ) {
                    Text(
                        text = "Sign Up",
                        fontSize = 24.sp,
                        color = Color.White,
                        fontFamily = robotoFamily
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(646.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(30.dp))
                ) {
                    Column(
                        modifier = Modifier.matchParentSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(25.dp))
                        //full name
                        OutlinedTextField(
                            value = fullName,
                            onValueChange = { fullName = it },
                            label = {
                                Text(text = "Full Name", fontFamily = robotoFamily)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.White,
                                cursorColor = Color.Black,
                            ),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.person),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp, 20.dp),
                                    tint = colorResource(id = R.color.Vivid_Sky_Blue)
                                )
                            }
                        )
                        HorizontalDivider(modifier = Modifier.padding(horizontal = 20.dp))
                        // email
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = {
                                Text(text = "Email", fontFamily = robotoFamily)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.White,
                                cursorColor = Color.Black,
                            ),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.email),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp, 20.dp),
                                    tint = colorResource(id = R.color.Vivid_Sky_Blue)
                                )
                            }
                        )
                        HorizontalDivider(modifier = Modifier.padding(horizontal = 20.dp))
                        //mobile number
                        OutlinedTextField(
                            value = number,
                            onValueChange = { number = it },
                            label = {
                                Text(text = "Mobile Number", fontFamily = robotoFamily)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.White,
                                cursorColor = Color.Black,
                            ),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.mobile),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp, 20.dp),
                                    colorResource(id = R.color.Vivid_Sky_Blue)
                                )
                            }
                        )
                        HorizontalDivider(modifier = Modifier.padding(horizontal = 20.dp))
                        // password
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = {
                                Text(text = "Password", fontFamily = robotoFamily)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.White,
                                cursorColor = Color.Black,
                            ),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.password),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp, 20.dp),
                                    colorResource(id = R.color.Vivid_Sky_Blue)
                                )
                            }
                        )
                        HorizontalDivider(modifier = Modifier.padding(horizontal = 20.dp))

                        Spacer(modifier = Modifier.height(32.dp))
                        //create account button
                        Button(
                            onClick = {
                                signUpScreenViewModel.signUpUser(
                                    email,
                                    password,
                                    fullName,
                                    number
                                )
                            },
                            modifier = Modifier
                                .size(305.dp, 45.dp)
                                .height(43.dp)
                                .background(
                                    brush = horizontalGradient,
                                    shape = RoundedCornerShape(16.dp)
                                ),
                            colors = ButtonDefaults.buttonColors(
                                Color.Transparent
                            )
                        ) {
                            Text(
                                text = "Create Account",
                                fontSize = 18.sp,
                                fontFamily = robotoFamily
                            )
                        }
                        Spacer(modifier = Modifier.height(80.dp))
                        // text
                        Text(
                            text = "or sign In using",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            fontFamily = robotoFamily
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        //for button
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.size(145.dp, 45.dp),
                                shape = RoundedCornerShape(14.dp),
                                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.Matt_blue))
                            ) {
                                Text(text = "Facebook", fontSize = 18.sp, fontFamily = robotoFamily)
                            }
                            Button(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.size(145.dp, 45.dp),
                                shape = RoundedCornerShape(14.dp),
                                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.Dark_Red))
                            ) {
                                Text(text = "Google", fontSize = 18.sp, fontFamily = robotoFamily)
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "By creating an account, you are agree to our Terms",
                            color = Color.Gray,
                            fontFamily = robotoFamily
                        )
                        Spacer(modifier = Modifier.height(50.dp))
                        Row {
                            Text(
                                text = "Already have an account?",
                                color = Color.Gray,
                                fontFamily = robotoFamily
                            )
                            Text(
                                text = " Sign In",
                                fontFamily = robotoFamily,
                                color = Color.Green,
                                modifier = Modifier.clickable { }
                            )
                        }


                    }
                }
            }

        }

        is Result.Loading -> {

            LoadingScreen()
        }

        is Result.Success -> {

            navHostController.navigate(Routes.SigInScreen)

        }

        is Result.Error -> {


        }
    }


}

