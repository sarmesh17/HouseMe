package com.sharkdroid.houseme.presentation.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.sharkdroid.houseme.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
data class ProfileViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    @Named("root") private val databaseReference: DatabaseReference
): ViewModel(){

    private val _userProfile = MutableLiveData<User?>()
    val userProfile: MutableLiveData<User?> get() = _userProfile

    // Add a LiveData to hold the Firebase user details (Google user)
    private val _firebaseUser = MutableLiveData<FirebaseUser?>()
    val firebaseUser: MutableLiveData<FirebaseUser?> get() = _firebaseUser

    init {
        fetchUserProfile()
    }

    private fun fetchUserProfile() {
        viewModelScope.launch {
            val userId = firebaseAuth.currentUser?.uid ?: return@launch
            databaseReference.child("users").child(userId).get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = task.result.getValue(User::class.java)
                        _userProfile.value = user
                    } else {

                        _userProfile.value = null
                    }
                }
            // Fetch data from Google sign-in
            fetchGoogleUserData()
        }
    }

    // Fetch Google User Data
    private fun fetchGoogleUserData() {
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            _firebaseUser.value = currentUser
        }
    }

    // Function to update user profile data
    fun updateUserProfile(firstname: String, lastname: String, phoneNumber: String, onComplete: (Boolean) -> Unit) {
        val userId = firebaseAuth.currentUser?.uid ?: return

        val userUpdates = mapOf(
            "firstname" to firstname,
            "lastname" to lastname,
            "phoneNumber" to phoneNumber
        )

        databaseReference.child("users").child(userId).updateChildren(userUpdates)
            .addOnCompleteListener { task ->
                onComplete(task.isSuccessful)
            }
    }



    // function for sharing links form share button

    fun shareContent(context: Context, content: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"  // Content type, in this case, plain text
            putExtra(Intent.EXTRA_TEXT, content)  // Content to share
        }

        // Show the system's share sheet
        val shareIntent = Intent.createChooser(intent, null)
        context.startActivity(shareIntent)
    }

    // for contact us
    fun sendEmailWithGmail(context: Context, recipient: String, subject: String, body: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$recipient")
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, body)
            setPackage("com.google.android.gm") // Specify Gmail package
        }

        // Check if Gmail is installed and can handle the intent
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            // Fallback to general email intent if Gmail is not installed
            val fallbackIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:$recipient")
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, body)
            }
            context.startActivity(Intent.createChooser(fallbackIntent, "Send email"))
        }
    }

    //for linking privacy policy
    fun openLinkInBrowser(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

        // Verify that the intent can be resolved before launching it
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }

    fun logout(onLogoutComplete: () -> Unit) {
        firebaseAuth.signOut()  // Logs the user out
        onLogoutComplete()  // Trigger the callback to navigate after logging out
    }

}

