package com.sharkdroid.houseme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.sharkdroid.houseme.presentation.navigation.HouseMeNavigation
import com.sharkdroid.houseme.presentation.viewmodel.AddRoomFormViewModel
import com.sharkdroid.houseme.presentation.viewmodel.OwnerAuthViewModel

import com.sharkdroid.houseme.ui.theme.HouseMeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HouseMeTheme {

                val startDestination=mainViewModel.startDestination.collectAsState()
                 HouseMeNavigation(startDestination.value)

//                AddRoomScreen(addRoomFormViewModel = addRoomFormViewModel)


            }
        }
    }
}

