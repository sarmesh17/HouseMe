package com.sharkdroid.houseme.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class OwnerAuthViewModel @Inject constructor(

):ViewModel(){

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