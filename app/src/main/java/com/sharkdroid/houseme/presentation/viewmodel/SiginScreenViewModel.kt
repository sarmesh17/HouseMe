package com.sharkdroid.houseme.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.sharkdroid.houseme.domain.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SigInScreenViewModel @Inject constructor(
    private val auth: FirebaseAuth
) : ViewModel() {

    // LiveData to observe login success or failure
    private val _loginResult = MutableStateFlow<Result<String>?>(null)
    val loginResult = _loginResult.asStateFlow()

    // Function to log in the user
    fun loginUser(email: String, password: String) {

        _loginResult.value=Result.Loading()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Login success
                    _loginResult.value = Result.Success("Login Successful")
                } else {
                    // Login failure
                    _loginResult.value = Result.Error(task.exception.toString())
                }
            }
    }
}