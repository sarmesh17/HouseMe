package com.sharkdroid.houseme.presentation.roomscreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sharkdroid.houseme.R
import com.sharkdroid.houseme.domain.model.RoomData
import com.sharkdroid.houseme.presentation.bottomnavigation.BottomNavigation
import com.sharkdroid.houseme.presentation.houselist.components.card.HouseListCardRowInfo
import com.sharkdroid.houseme.presentation.navigation.Routes
import com.sharkdroid.houseme.presentation.viewmodel.HouseListScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomScreen(navController: NavHostController,houseListScreenViewModel: HouseListScreenViewModel) {
    val robotoFamily = FontFamily(
        Font(R.font.roboto_medium)
    )

    val horizontalGradient = Brush.linearGradient(
        colors = listOf(
            colorResource(id = R.color.Vivid_Sky_Blue),
            colorResource(id = R.color.Sea_Green)
        )
    )

    val list = houseListScreenViewModel.roomList.collectAsState()
    Scaffold(
        topBar =  {
            TopAppBar(
                title = {
                    Text(
                        text = "Available Rooms",
                        color = Color.White,
                        fontFamily = robotoFamily,
                        fontSize = 18.sp
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
                            painter = painterResource(id = R.drawable.ic_arrow),
                            tint = Color.White,
                            contentDescription = null,
                            modifier = Modifier
                                .size(32.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(Color.Transparent),
                modifier = Modifier.background(brush = horizontalGradient)
            )

        },
        bottomBar = {

            BottomNavigation(selectedItem = 2, onClick = { index ->
                when (index) {
                    0 -> navController.navigate(Routes.HomeScreen)
                    1 -> navController.navigate(Routes.PlacesScreen)
                    2 -> {
                        navController.navigate(Routes.RoomScreen); }

                    3 -> navController.navigate(Routes.BookingHistory)
                    4 -> navController.navigate(Routes.ProfileScreen)
                }
            })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Routes.OwnerValidationScreen) },
                containerColor = colorResource(id = R.color.Vivid_Sky_Blue),
                modifier = Modifier
                    .size(65.dp)
                    .background(
                        brush = horizontalGradient,
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                Text(text = "+", fontSize = 40.sp, color = Color.White) // You can also use an Icon instead of Text
            }
        },
        floatingActionButtonPosition = FabPosition.End

    ) {

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

//


            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(list.value){data ->
                    HouseListCardRowInfo(roomData = data,navController)
                }
            }

        }
    }
}
//
//        Box(
//            modifier = Modifier
//                .padding(start = 300.dp, top = 650.dp)
//                .size(65.dp)
//                .background(
//                    brush = horizontalGradient,
//                    shape = RoundedCornerShape(16.dp)
//                )
//                .clickable { navController.navigate(Routes.OwnerValidationScreen) }
//
//        ) {
//            Text(
//                text = "+",
//                fontSize = 50.sp,
//                color = Color.White,
//                modifier = Modifier.align(Alignment.Center)
//            )
//        }
//    }
//}
