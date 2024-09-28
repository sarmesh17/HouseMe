package com.sharkdroid.houseme.presentation.siginscreen

import android.app.Activity.RESULT_OK
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sharkdroid.houseme.R
import com.sharkdroid.houseme.domain.model.Result
import com.sharkdroid.houseme.presentation.common.LoadingScreen
import com.sharkdroid.houseme.presentation.navigation.Routes
import com.sharkdroid.houseme.presentation.viewmodel.SigInScreenViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun SignInScreen(
    sigInScreenViewModel: SigInScreenViewModel,
    navHostController: NavHostController
) {

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

    val resultStatus=sigInScreenViewModel.signInResult.collectAsState()


    var email by remember {

        mutableStateOf("")
    }

    var password by remember {

        mutableStateOf("")
    }

    val coroutine= rememberCoroutineScope()
    val context = LocalContext.current

    val intentSender by sigInScreenViewModel.intentSenderLiveData.observeAsState()
    val loginSuccess by sigInScreenViewModel.loginSuccessLiveData.observeAsState()
    val error by sigInScreenViewModel.errorLiveData.observeAsState()

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartIntentSenderForResult()) { result->

            if (result.resultCode == RESULT_OK){
                sigInScreenViewModel.handleSignInResult(result.data)
            } else {
                Log.e("SignInFailure","Sign-in failed with resultCode: ${result.resultCode}")
                result.data?.let {
                    val errorMessage = it.getStringExtra("error_message")
                    Log.e("SignInFailure", "Error Message: $errorMessage")
                }
            }
        }

    LaunchedEffect(intentSender) {
        intentSender?.let{
            launcher.launch(IntentSenderRequest.Builder(it).build())
        }
    }

    LaunchedEffect(loginSuccess) {
        if (loginSuccess== true){
            navHostController.navigate(Routes.HomeScreen)
        }
    }


    when(resultStatus.value){

        is Result.Default -> {

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
                        .padding(start = 18.dp)
                ) {

                    Text(
                        text = "Sign In",
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
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {


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

                        // password-textField

                        TextField(
                            value = password,
                            onValueChange = {
                                password = it
                            },
                            label = {
                                Text(text = "Password", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                            },
                            leadingIcon = {

                                Icon(
                                    painter = painterResource(id = R.drawable.password_icon),
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

                        Text(text = "Forgot Password?", color = Color.Red, fontSize = 18.sp)

                        Spacer(modifier = Modifier.height(16.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    brush = linearGradient,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .height(45.dp)
                                .clickable {
                                    coroutine.launch {

                                        sigInScreenViewModel.signInWithEmailPassword(
                                            email,
                                            password
                                        )
                                    }
                                    email = "";
                                    password = ""
                                }, contentAlignment = Alignment.Center
                        ) {


                            Text(text = "Sign In", fontSize = 20.sp, color = Color.White)

                        }

                        Spacer(modifier = Modifier.height(100.dp))

                        Text(text = "or sign in using", color = Color.Gray)

                        Spacer(modifier = Modifier.height(10.dp))


                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {

                            //facebook-login-button
                            Button(
                                onClick = { /*TODO*/ },
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.size(160.dp, 43.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(id = R.color.Cerulean_Blue)
                                )
                            ) {

                                Text(text = "Facebook", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            // google login button
                            Button(
                                onClick = {
                                    sigInScreenViewModel.startSignIn()
                                },
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.size(160.dp, 43.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(id = R.color.Burnt_Orange)
                                )
                            ) {

                                Text(text = "Google", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

                            }


                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row {

                            Text(text = "By creating an account, you are agree to our ", color = Color.Gray, fontSize = 12.sp)
                            Text(text = "Term", color = colorResource(id = R.color.Caribbean_Green), fontWeight = FontWeight.SemiBold, fontSize = 12.sp)

                        }

                        Spacer(modifier = Modifier.height(50.dp))

                        Row {

                            Text(text = "Don't have an account? ")
                            Text(
                                text = "Sign Up ",
                                fontSize = 16.sp,
                                color = colorResource(id = R.color.Caribbean_Green),
                                modifier = Modifier.clickable { navHostController.navigate(Routes.SignUpScreen) }
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

            navHostController.navigate(Routes.HomeScreen)

        }

        is Result.Error -> {



        }


    }




}