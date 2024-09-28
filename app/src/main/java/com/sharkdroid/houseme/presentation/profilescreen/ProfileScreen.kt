package com.sharkdroid.houseme.presentation.profilescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.sharkdroid.houseme.R
import com.sharkdroid.houseme.presentation.bottomnavigation.BottomNavigation
import com.sharkdroid.houseme.presentation.navigation.Routes
import com.sharkdroid.houseme.presentation.viewmodel.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavHostController,profileViewModel: ProfileViewModel) {
    val horizontalGradient = Brush.linearGradient(
        colors = listOf(
            colorResource(id = R.color.Vivid_Sky_Blue),
            colorResource(id = R.color.Sea_Green)
        )
    )
    val robotoFamily = FontFamily(
        Font(R.font.roboto_medium)
    )
    val user by profileViewModel.userProfile.observeAsState()
    val googleUser by profileViewModel.firebaseUser.observeAsState()
    val context = LocalContext.current

    Scaffold (
        topBar =  {
            TopAppBar(
                title = {
                    Text(
                        text = "Your profile",
                        color = Color.White,
                        fontFamily = robotoFamily,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 70.dp)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .size(50.dp, 50.dp)

                    ) {
                        //icon
                        Icon(
                            painter = painterResource(id = R.drawable.leftarrow),
                            contentDescription = null,
                            Modifier
                                .size(20.dp), tint = Color.White

                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(Color.Transparent),
                modifier = Modifier.background(brush = horizontalGradient)
            )

        },
        bottomBar = {

            BottomNavigation(selectedItem = 4, onClick = {index ->
                when(index){
                    0-> navController.navigate(Routes.HomeScreen)
                    1-> navController.navigate(Routes.PlacesScreen)
                    2-> navController.navigate(Routes.RoomScreen)
                    3-> navController.navigate(Routes.BookingScreen)
                    4-> { navController.navigate(Routes.ProfileScreen); }
                }
            } )
        }
    ){ it ->
        Modifier.padding(it)



        Column (
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Spacer(modifier = Modifier.height(8.dp))

            if (googleUser?.displayName.isNullOrEmpty() || googleUser?.email.isNullOrEmpty()) {
                // Fallback to showing data from Firebase Realtime Database if Google data is not available
                user?.let {
                    UserAvatar(
                        photoUrl = it.photoUrl,
                        name = it.name
                    )

                    Text(
                        text = "${it.name} ",
                        fontFamily = robotoFamily,
                        fontSize = 20.sp,
                        color = Color.Gray
                    )

                    Text(
                        text = "${it.mobileNumber}",
                        fontFamily = robotoFamily,
                        fontSize = 16.sp,
                        color = Color.Gray
                    )

                    Text(
                        text = "${it.email}",
                        fontFamily = robotoFamily,
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            } else {
                // Display Google user's profile picture, name, and email
                googleUser?.let { gUser ->
                    UserAvatar(
                        photoUrl = gUser.photoUrl?.toString(),
                        name = gUser.displayName?.split(" ")?.getOrNull(0)
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = gUser.displayName ?: "Name not available",
                        fontFamily = robotoFamily,
                        fontSize = 20.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = gUser.email ?: "Email not available",
                        fontFamily = robotoFamily,
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            }



            Box(
                modifier = Modifier.clickable { navController.navigate(Routes.UpdateAccount) }
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(brush = horizontalGradient, shape = RoundedCornerShape(12.dp))
                    .height(45.dp), contentAlignment = Alignment.Center
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.edittext),
                        contentDescription = null,
                        Modifier
                            .size(20.dp), tint = Color.White

                    )
                    Spacer(modifier = Modifier.width(30.dp))
                    Text(text = "Edit Profile", fontSize = 20.sp, color = Color.White)
                    Spacer(modifier = Modifier.weight(1f))
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

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.bookingonline),
                        contentDescription = null,
                        Modifier
                            .size(20.dp), tint = Color.White

                    )
                    Spacer(modifier = Modifier.width(30.dp))
                    Text(text = "Booking History", fontSize = 20.sp, color = Color.White)
                    Spacer(modifier = Modifier.weight(1f))
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
                modifier = Modifier.clickable { profileViewModel.shareContent(
                    context,
                    "Download now https://play.google.com/store/apps/details?id=com.example.cars24"
                ) }
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(brush = horizontalGradient, shape = RoundedCornerShape(12.dp))
                    .height(45.dp), contentAlignment = Alignment.Center
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.share),
                        contentDescription = null,
                        Modifier
                            .size(20.dp), tint = Color.White

                    )
                    Spacer(modifier = Modifier.width(30.dp))
                    Text(text = "Share App", fontSize = 20.sp, color = Color.White)
                    Spacer(modifier = Modifier.weight(1f))
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
                modifier = Modifier.clickable { profileViewModel.logout{ navController.navigate(Routes.SigInScreen)}  }
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(brush = horizontalGradient, shape = RoundedCornerShape(12.dp))
                    .height(45.dp), contentAlignment = Alignment.Center
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp), horizontalArrangement = Arrangement.Center
                ) {


                    Text(text = "Log Out", fontSize = 20.sp, color = Color.White)



                    Spacer(modifier = Modifier.height(10.dp))

                }

            }
        }
    }
}


@Composable
fun UserAvatar(photoUrl: String?, name: String?) {
    Box(
        modifier = Modifier
            .size(100.dp, 100.dp)
            .background(
                color = colorResource(id = R.color.Vivid_Sky_Blue),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        if (photoUrl != null) {
            Image(
                painter = rememberImagePainter(photoUrl),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        } else {
            Text(
                text = "${name?.firstOrNull() ?: ""}",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

