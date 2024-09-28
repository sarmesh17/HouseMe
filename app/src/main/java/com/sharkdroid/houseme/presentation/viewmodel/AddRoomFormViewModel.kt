package com.sharkdroid.houseme.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.sharkdroid.houseme.domain.model.RoomData
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddRoomFormViewModel @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val firebaseStorage: FirebaseStorage
):ViewModel() {
    fun uploadRoomAndFoodImages(
        roomImageUri: Uri?,
        foodImageUri: Uri?,
        description: String,
        roomName: String,
        price: String,
        discount: String,
        checkIn: String,
        checkOut: String
    ) {
        val databaseReference = firebaseDatabase.reference.child("RoomForm").push()

        var roomImageUrl: String? = null
        var foodImageUrl: String? = null

        roomImageUri?.let { roomUri ->
            val roomImageRef = firebaseStorage.reference.child("RoomCoverImage/${UUID.randomUUID()}.jpg")
            roomImageRef.putFile(roomUri)
                .addOnSuccessListener {
                    roomImageRef.downloadUrl.addOnSuccessListener { url ->
                        roomImageUrl = url.toString()
                        // Call saveRoomData only when both image URLs are uploaded
                        saveRoomDataIfReady(
                            databaseReference,
                            roomImageUrl,
                            foodImageUrl,
                            description,
                            roomName,
                            price,
                            discount,
                            checkIn,
                            checkOut
                        )
                    }
                }
        }

        foodImageUri?.let { foodUri ->
            val foodImageRef = firebaseStorage.reference.child("CoverFoodImage/${UUID.randomUUID()}.jpg")
            foodImageRef.putFile(foodUri)
                .addOnSuccessListener {
                    foodImageRef.downloadUrl.addOnSuccessListener { url ->
                        foodImageUrl = url.toString()
                        // Call saveRoomData only when both image URLs are uploaded
                        saveRoomDataIfReady(
                            databaseReference,
                            roomImageUrl,
                            foodImageUrl,
                            description,
                            roomName,
                            price,
                            discount,
                            checkIn,
                            checkOut
                        )
                    }
                }
        }
    }

    private fun saveRoomDataIfReady(
        databaseReference: DatabaseReference,
        roomImageUrl: String?,
        foodImageUrl: String?,
        description: String,
        roomName: String,
        price: String,
        discount: String,
        checkIn: String,
        checkOut: String
    ) {
        if (roomImageUrl != null || foodImageUrl != null) {
            val roomData = RoomData(
                roomImageUrl = roomImageUrl,
                roomName = roomName,
                price = price,
                discount = discount,
                description = description,
                checkIn = checkIn,
                checkOut = checkOut,
                foodImageUrl = foodImageUrl
            )
            saveRoomData(databaseReference, roomData)
        }
    }

    private fun saveRoomData(databaseReference: DatabaseReference, roomData: RoomData) {
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

