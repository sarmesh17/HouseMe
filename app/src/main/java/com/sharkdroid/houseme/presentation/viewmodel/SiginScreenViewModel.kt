package com.sharkdroid.houseme.presentation.viewmodel

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.sharkdroid.houseme.domain.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class SigInScreenViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {


    // Result class to handle SignInState

    private val _sigInResult = MutableStateFlow<Result<Unit>>(Result.Default())
    val signInResult = _sigInResult.asStateFlow()

    fun signInWithEmailPassword( email:String, password:String){

        viewModelScope.launch {

            try {
                _sigInResult.value = Result.Loading()

                // Firebase sign-in operation
                firebaseAuth.signInWithEmailAndPassword(email, password).await()

                _sigInResult.value = Result.Success(Unit)
            }catch (e: Exception){
                _sigInResult.value=Result.Error(e.message ?: "Unknown error occurred")
            }

        }
    }

}