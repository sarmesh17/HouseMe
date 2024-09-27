package com.sharkdroid.houseme.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sharkdroid.houseme.presentation.homescreen.HomeScreen
import com.sharkdroid.houseme.presentation.siginscreen.SignInScreen
import com.sharkdroid.houseme.presentation.signupscreen.SignUpScreen
import com.sharkdroid.houseme.presentation.viewmodel.SigInScreenViewModel
import com.sharkdroid.houseme.presentation.viewmodel.SignUpScreenViewModel

@Composable
fun HouseMeNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SignUpScreen
    ) {

        composable<Routes.HomeScreen> {
            HomeScreen(navController)
        }

        composable<Routes.SigInScreen> {

            val viewModel:SigInScreenViewModel= hiltViewModel()

          SignInScreen( sigInScreenViewModel = viewModel , navHostController = navController)
        }

        composable<Routes.SignUpScreen> {

            val viewModel:SignUpScreenViewModel= hiltViewModel()

            SignUpScreen( signUpScreenViewModel = viewModel , navHostController = navController)
        }


    }

}