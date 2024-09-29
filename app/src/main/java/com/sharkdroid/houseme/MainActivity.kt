package com.sharkdroid.houseme

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.google.firebase.FirebaseApp
import com.sharkdroid.houseme.presentation.navigation.HouseMeNavigation
import com.sharkdroid.houseme.presentation.navigation.Routes
import com.sharkdroid.houseme.presentation.roomscreen.addroomscreen.AddRoomScreen
import com.sharkdroid.houseme.presentation.viewmodel.AddRoomFormViewModel
import com.sharkdroid.houseme.presentation.viewmodel.MainViewModel
import com.sharkdroid.houseme.presentation.viewmodel.OwnerAuthViewModel

import com.sharkdroid.houseme.ui.theme.HouseMeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    /*val ownerAuthViewModel: OwnerAuthViewModel by viewModels()
    private val addRoomFormViewModel: AddRoomFormViewModel by viewModels()*/
    private val viewModel: MainViewModel by viewModels()
    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HouseMeTheme {
                FirebaseApp.initializeApp(this)
                val startDestination=viewModel.startDestination.value

                HouseMeNavigation(startDestination = startDestination)

            }
        }
    }
}

