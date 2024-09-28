package com.sharkdroid.houseme

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sharkdroid.houseme.presentation.navigation.Routes
import com.sharkdroid.whatsappclone.domain.usecases.all_use_case.AppEntryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCase: AppEntryUseCase
):ViewModel() {

    private var _startDestination = MutableStateFlow<Routes>(Routes.SigInScreen)
    val startDestination: StateFlow<Routes> = _startDestination.asStateFlow()

    init {
        viewModelScope.launch {

            appEntryUseCase.readLoginEntry().collect{

                if (it){

                    _startDestination.value=Routes.HomeScreen
                }

            }
        }


    }
}