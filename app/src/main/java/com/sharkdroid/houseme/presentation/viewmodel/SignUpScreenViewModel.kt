package com.sharkdroid.houseme.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.sharkdroid.houseme.domain.model.Result
import com.sharkdroid.houseme.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val database: FirebaseDatabase
):ViewModel() {

    private val _signUpResult=MutableStateFlow<Result<Unit>>(Result.Default())
    val signUpResult=_signUpResult.asStateFlow()

    fun signUpUser(email: String, password: String, name: String, mobileNumber: String) {
        viewModelScope.launch {
            _signUpResult.value=Result.Loading()
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    try {

                        if (task.isSuccessful) {
                            // User registered successfully, store user data
                            _signUpResult.value=Result.Success(Unit)
                            val user = firebaseAuth.currentUser
                            user?.let {
                                saveUserData(it.uid, email, name, mobileNumber)
                            }
                        }
                    }catch (e: Exception){
                        _signUpResult.value=Result.Error(e.message?:"Unknown error occurred")
                    }
                }
        }
    }

    private fun saveUserData(userId: String, email: String, name: String, mobileNumber: String) {
        val userRef = database.getReference("users").child(userId)
        val user = User(email, name, mobileNumber)

        userRef.setValue(user).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                println("User data saved successfully!")
            } else {
                println("Failed to save user data: ${task.exception?.message}")
            }
        }
    }

}