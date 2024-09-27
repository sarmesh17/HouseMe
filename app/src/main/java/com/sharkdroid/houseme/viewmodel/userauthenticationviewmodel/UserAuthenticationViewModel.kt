package com.sharkdroid.houseme.viewmodel.userauthenticationviewmodel

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch

class UserAuthenticationViewModel : ViewModel() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseDatabase = FirebaseDatabase.getInstance().reference
    private val firebaseStorage = FirebaseStorage.getInstance().reference

    var name = mutableStateOf("")
        private set
    var email = mutableStateOf("")
        private set
    var phoneNumber = mutableStateOf("")
    var address = mutableStateOf("")
    var availableRoom = mutableStateOf("")
    var selectedOption = mutableStateOf<String?>(null)
    var selectedImageUri = mutableStateOf<Uri?>(null)

    init {
        fetchUserData()
    }

    // Fetch user name and email from FirebaseAuth
    private fun fetchUserData() {
        firebaseAuth.currentUser?.let { user ->
            name.value = user.displayName ?: ""
            email.value = user.email ?: ""
        }
    }

    // Upload image and other details to Firebase
    fun submitUserDetails() {
        viewModelScope.launch {
            val userId = firebaseAuth.currentUser?.uid ?: return@launch

            // Save other details (phone number, address, etc.)
            val userDetail = mapOf(
                "phoneNumber" to phoneNumber.value,
                "address" to address.value,
                "availableRoom" to availableRoom.value,
                "documentType" to selectedOption.value
            )
            firebaseDatabase.child("users").child(userId).setValue(userDetail)

            // Upload image if selected
            selectedImageUri.value?.let { uri ->
                val imageRef = firebaseStorage.child("users/$userId/document.jpg")
                imageRef.putFile(uri).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        imageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                            // Save the image URL in the database
                            firebaseDatabase.child("users").child(userId).child("documentImageUrl").setValue(downloadUri.toString())
                        }
                    }
                }
            }
        }
    }
}
