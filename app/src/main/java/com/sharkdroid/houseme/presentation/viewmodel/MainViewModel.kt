package com.sharkdroid.houseme.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.sharkdroid.houseme.presentation.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor():ViewModel() {

    private var _startDestination=MutableStateFlow<Routes>(Routes.SplashScreen)
    val startDestination:StateFlow<Routes> = _startDestination.asStateFlow()

}