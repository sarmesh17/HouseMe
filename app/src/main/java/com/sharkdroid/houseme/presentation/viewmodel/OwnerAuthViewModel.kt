package com.sharkdroid.houseme.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class OwnerAuthViewModel @Inject constructor(

    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val databaseReference = FirebaseDatabase.getInstance().reference.child("UserAuthForm")

     private var _userName = MutableStateFlow<String>("")
    val userName = _userName.asStateFlow()


     private var _userEmail = MutableStateFlow<String>("")
    val email = _userEmail.asStateFlow()

    private var _error = MutableStateFlow<String>("")
    val error = _error.asStateFlow()

    init {
        fetchCurrentUserDetailsFromDatabase()
    }

    private fun fetchCurrentUserDetailsFromDatabase() {
        val currentUser = firebaseAuth.currentUser

        if (currentUser != null) {
            // Reference to the 'users' node in the Realtime Database
            val userId = currentUser.uid
            val databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userId)

            // Fetch user data (name and email) from the database
            databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val name = dataSnapshot.child("name").getValue(String::class.java)
                        val email = dataSnapshot.child("email").getValue(String::class.java)

                        // Update LiveData with the fetched values
                        _userEmail.value = email ?: "No email available"
                        _userName.value = name ?: "No name available"
                    } else {
                        // Handle case where user data does not exist
                        _userEmail.value = "No user data found"
                        _userName.value = "No user data found"
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle database error
                    _userEmail.value = "Error fetching user data"
                    _userName.value = "Error fetching user data"
                }
            })
        } else {
            // Handle case where no user is logged in
            _userEmail.value = "User not logged in"
            _userName.value = "User not logged in"
        }
    }


    fun uploadImageToFirebaseStorage(imageUri: Uri,phone: String, address: String,availableRooms: String) {
        val storageReference = FirebaseStorage.getInstance().reference.child("personalDocument/${UUID.randomUUID()}.jpg")
        storageReference.putFile(imageUri)
            .addOnSuccessListener {
                storageReference.downloadUrl.addOnSuccessListener { uri ->
                    // Image uploaded, now store the image URL in Realtime Database
                    val imageUrl = uri.toString()
                    // Call the function to store data in Realtime Database
                    saveDataToFirebase(phone, address, availableRooms, imageUrl)
                }
            }
            .addOnFailureListener {
                // Handle failure
            }
    }


    private fun saveDataToFirebase(phone: String, address: String, availableRooms: String, imageUrl: String) {
        val databaseReference = FirebaseDatabase.getInstance().reference.child("UserAuthForm").push()

        val roomData = mapOf(
            "phone" to phone,
            "address" to address,
            "availableRooms" to availableRooms,
            "imageUrl" to imageUrl
        )

        databaseReference.setValue(roomData)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Data stored successfully
                } else {
                    // Failed to store data
                }
            }
    }





}
