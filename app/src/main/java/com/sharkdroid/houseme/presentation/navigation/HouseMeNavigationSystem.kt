package com.sharkdroid.houseme.presentation.navigation

import com.sharkdroid.houseme.presentation.houselist.HouseListScreen
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sharkdroid.houseme.presentation.bookinghistory.BookingHistory
import com.sharkdroid.houseme.presentation.homescreen.HomeScreen
import com.sharkdroid.houseme.presentation.profilescreen.ProfileScreen
import com.sharkdroid.houseme.presentation.roomscreen.RoomScreen
import com.sharkdroid.houseme.presentation.roomscreen.addroomscreen.AddRoomScreen
import com.sharkdroid.houseme.presentation.roomscreen.ownervalidationform.OwnerValidationScreen
import com.sharkdroid.houseme.presentation.siginscreen.SignInScreen
import com.sharkdroid.houseme.presentation.signupscreen.SignUpScreen
import com.sharkdroid.houseme.presentation.splashscreen.SplashScreen
import com.sharkdroid.houseme.presentation.updateaccount.UpdateAccount
import com.sharkdroid.houseme.presentation.viewmodel.AddRoomFormViewModel
import com.sharkdroid.houseme.presentation.viewmodel.HomeScreenViewModel
import com.sharkdroid.houseme.presentation.viewmodel.HouseListScreenViewModel
import com.sharkdroid.houseme.presentation.viewmodel.OwnerAuthViewModel
import com.sharkdroid.houseme.presentation.viewmodel.ProfileViewModel
import com.sharkdroid.houseme.presentation.viewmodel.SigInScreenViewModel
import com.sharkdroid.houseme.presentation.viewmodel.SignUpScreenViewModel

@Composable
fun HouseMeNavigation(startDestination: Routes) {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Routes.SplashScreen> {
            SplashScreen(
                onTimeout = {
                    navController.navigate(Routes.SigInScreen){
                        popUpTo<Routes.SplashScreen> {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<Routes.HomeScreen> {
            val homeScreenViewModel:HomeScreenViewModel = hiltViewModel()
            HomeScreen(navController, homeScreenViewModel)
        }

        composable<Routes.SigInScreen> {

            val viewModel:SigInScreenViewModel= hiltViewModel()

          SignInScreen( viewModel , navHostController = navController)
        }

        composable<Routes.SignUpScreen> {

            val viewModel:SignUpScreenViewModel= hiltViewModel()

            SignUpScreen( viewModel , navHostController = navController)
        }

        composable<Routes.RoomScreen> {
            val houseListScreenViewModel:HouseListScreenViewModel= hiltViewModel()
            RoomScreen(navController,houseListScreenViewModel)
        }
        composable<Routes.OwnerValidationScreen> {
            val ownerAuthValidation: OwnerAuthViewModel = hiltViewModel()
            OwnerValidationScreen(navController, ownerAuthValidation)
        }

        composable<Routes.AddRoomScreen> {
            val addRoomScreen:AddRoomFormViewModel = hiltViewModel()
            AddRoomScreen(addRoomFormViewModel = addRoomScreen)
        }

        composable<Routes.ProfileScreen> {
            val profileViewModel:ProfileViewModel = hiltViewModel()
            ProfileScreen(navController, profileViewModel)
        }

        composable<Routes.UpdateAccount> {
            UpdateAccount(navController)
        }

        composable<Routes.BookingHistory> {
            BookingHistory(navController)
        }

        composable<Routes.HotelListScreen> {
            val homeScreenViewModel:HomeScreenViewModel= hiltViewModel()
            HouseListScreen( homeScreenViewModel)
        }


    }

}