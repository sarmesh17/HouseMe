package com.sharkdroid.houseme.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.sharkdroid.houseme.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val database: FirebaseDatabase
):ViewModel() {


    fun signUpUser(email: String, password: String, name: String, mobileNumber: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // User registered successfully, store user data
                    val user = firebaseAuth.currentUser
                    user?.let {
                        saveUserData(it.uid, email, name, mobileNumber)
                    }
                } else {
                    // Handle error
                    println("Error: ${task.exception?.message}")
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