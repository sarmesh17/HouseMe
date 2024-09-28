package com.sharkdroid.houseme.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddRoomFormViewModel @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val firebaseStorage: FirebaseStorage
):ViewModel() {
        // Single method to upload both room cover image and food cover image
        fun uploadRoomAndFoodImages(
            roomImageUri: Uri?,
            foodImageUri: Uri?,
            description: String,
            checkIn: String,
            checkOut: String
        ) {
            // Store data in the same node
            val databaseReference = firebaseDatabase.reference.child("RoomForm").push()

            // If room image is provided, upload it
            roomImageUri?.let { roomUri ->
                val roomImageRef = firebaseStorage.reference.child("RoomCoverImage/${UUID.randomUUID()}.jpg")
                roomImageRef.putFile(roomUri)
                    .addOnSuccessListener {
                        roomImageRef.downloadUrl.addOnSuccessListener { roomImageUrl ->
                            // Save room image URL to the Realtime Database node
                            saveRoomData(databaseReference, roomImageUrl.toString(), description, checkIn, checkOut)
                        }
                    }
                    .addOnFailureListener {
                        // Handle failure
                    }
            }

            // If food image is provided, upload it
            foodImageUri?.let { foodUri ->
                val foodImageRef = firebaseStorage.reference.child("CoverFoodImage/${UUID.randomUUID()}.jpg")
                foodImageRef.putFile(foodUri)
                    .addOnSuccessListener {
                        foodImageRef.downloadUrl.addOnSuccessListener { foodImageUrl ->
                            // Save food image URL to the same node
                            databaseReference.child("foodImageUrl").setValue(foodImageUrl.toString())
                        }
                    }
                    .addOnFailureListener {
                        // Handle failure
                    }
            }
        }

        // Save room details to Firebase Realtime Database
        private fun saveRoomData(
            databaseReference: DatabaseReference,
            roomImageUrl: String,
            description: String,
            checkIn: String,
            checkOut: String
        ) {
            val roomData = mapOf(
                "roomImageUrl" to roomImageUrl,
                "description" to description,
                "checkIn" to checkIn,
                "checkOut" to checkOut
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

