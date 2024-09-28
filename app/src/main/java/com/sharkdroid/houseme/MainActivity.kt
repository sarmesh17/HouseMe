package com.sharkdroid.houseme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.sharkdroid.houseme.presentation.roomscreen.addroomscreen.AddRoomScreen
import com.sharkdroid.houseme.presentation.roomscreen.ownervalidationform.OwnerAuthorizationForm
import com.sharkdroid.houseme.presentation.viewmodel.AddRoomFormViewModel
import com.sharkdroid.houseme.presentation.viewmodel.OwnerAuthViewModel
import com.sharkdroid.houseme.ui.theme.HouseMeTheme

import com.google.firebase.FirebaseApp
import com.sharkdroid.houseme.presentation.navigation.HouseMeNavigation
import com.sharkdroid.houseme.presentation.siginscreen.SignInScreen
import com.sharkdroid.houseme.presentation.viewmodel.SigInScreenViewModel
import com.sharkdroid.houseme.ui.theme.HouseMeTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val ownerAuthViewModel:OwnerAuthViewModel by viewModels()
    private val addRoomFormViewModel:AddRoomFormViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HouseMeTheme {

//                OwnerAuthorizationForm(ownerAuthViewModel = ownerAuthViewModel )
                AddRoomScreen(addRoomFormViewModel = addRoomFormViewModel)

                HouseMeNavigation()
            }
        }
    }
}

