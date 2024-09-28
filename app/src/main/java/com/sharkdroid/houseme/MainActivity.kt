package com.sharkdroid.houseme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.sharkdroid.houseme.presentation.navigation.HouseMeNavigation
import com.sharkdroid.houseme.presentation.navigation.Routes
import com.sharkdroid.houseme.presentation.roomscreen.addroomscreen.AddRoomScreen
import com.sharkdroid.houseme.presentation.viewmodel.AddRoomFormViewModel
import com.sharkdroid.houseme.presentation.viewmodel.OwnerAuthViewModel

import com.sharkdroid.houseme.ui.theme.HouseMeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val ownerAuthViewModel: OwnerAuthViewModel by viewModels()
    private val addRoomFormViewModel: AddRoomFormViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HouseMeTheme {
               HouseMeNavigation()

                //AddRoomScreen(addRoomFormViewModel = addRoomFormViewModel)


            }
        }
    }
}

