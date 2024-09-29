package com.sharkdroid.houseme.presentation.viewmodel

import android.content.Intent
import android.content.IntentSender
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
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
    private val database: FirebaseDatabase,
    private val oneTapClient: SignInClient // used to handle google one-tap sign-in requests.
):ViewModel() {

    val intentSenderLiveData = MutableLiveData<IntentSender?>()
    val errorLiveData = MutableLiveData<String?>()
    val userLiveData = MutableLiveData<FirebaseUser?>()
    val loginSuccessLiveData = MutableLiveData<Boolean>()

    private val _signUpResult=MutableStateFlow<Result<Unit>>(Result.Default())
    val signUpResult=_signUpResult.asStateFlow()

    fun signUpUser(email: String, password: String, name: String, mobileNumber: String, photoUrl: String) {
        viewModelScope.launch {
            _signUpResult.value=Result.Loading()
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { task ->
                    try {
                            // User registered successfully, store user data
                            val user = firebaseAuth.currentUser
                        user?.let {
                            saveUserData(it.uid, email, name, mobileNumber, photoUrl)
                            _signUpResult.value=Result.Success(Unit)
                        }

                    }catch (e: Exception){

                        _signUpResult.value=Result.Error(e.message?:"Unknown error occurred")

                    }
                }
        }
    }

    private fun saveUserData(userId: String, email: String, name: String, mobileNumber: String, photoUrl: String) {
        viewModelScope.launch {


            val userRef = database.getReference("users").child(userId)
            val user = User(email, name, mobileNumber, photoUrl)

            userRef.setValue(user).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    println("User data saved successfully!")
                } else {
                    println("Failed to save user data: ${task.exception?.message}")
                }
            }
        }
    }

    // google sign-up

    fun startSignUp() {
        Log.d("SignIn","Starting sign-in process")

        val signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId("293246541079-41p2sf3rg5ut1oe4m60p069q1ftrfu15.apps.googleusercontent.com")
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .build()

        oneTapClient.beginSignIn(signInRequest)
            .addOnSuccessListener { result ->
                Log.d( "SignIn", "Sign-in request successfull")
                intentSenderLiveData.postValue(result.pendingIntent.intentSender)
            }
            .addOnFailureListener{e ->
                Log.d("SignIn","Sign-in request failed: ${e.localizedMessage}")
                errorLiveData.postValue(e.localizedMessage)
            }
    }

    fun handleSignUpResult(data: Intent?){
        Log.d("SignInResult", "Handling sign-in result")

        viewModelScope.launch{
            try {
                val credential = oneTapClient.getSignInCredentialFromIntent(data)
                val idToken = credential.googleIdToken

                Log.d("SignInResult", "Credential retrieved: ${credential.id}")
                if (idToken != null) {
                    Log.d("SignInResult", "ID token retrieved")

                    val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
                    firebaseAuth.signInWithCredential(firebaseCredential)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                userLiveData.postValue(firebaseAuth.currentUser)
                                viewModelScope.launch {
                                    loginSuccessLiveData.postValue(true)
                                }
                            } else {
                                Log.d(
                                    "SignInResult",
                                    "Firebase sign-in failed: ${task.exception?.localizedMessage}"
                                )
                                errorLiveData.postValue(task.exception?.localizedMessage)
                                loginSuccessLiveData.postValue(false)
                            }

                        }
                } else {
                    Log.d("SignInResult", "No ID token found")
                    errorLiveData.postValue("No ID token")
                }
            } catch (e: ApiException){
                Log.d("SignInResult","Error retrieving credentials: ${e.localizedMessage}")
                errorLiveData.postValue(e.localizedMessage)
            }
        }
    }


}