package com.sharkdroid.houseme.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.sharkdroid.houseme.domain.model.RoomData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor() : ViewModel() {

    // Assuming you have the database reference to 'addRoom'
    private val databaseRef = FirebaseDatabase.getInstance().getReference("addRoom")

    // MutableState to hold the list of RoomData
    var roomsList = mutableStateOf<List<RoomData>>(emptyList())
        private set // Make the setter private to restrict modification outside the ViewModel

    // Function to search for rooms based on location input
    fun searchRoomsByLocation(locationInput: String) {
        // Query to match the location with the user input
        val query = databaseRef.orderByChild("location").equalTo(locationInput)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Check if data exists for the given location
                if (dataSnapshot.exists()) {
                    val roomList = mutableListOf<RoomData>()
                    for (roomSnapshot in dataSnapshot.children) {
                        // Retrieve room data
                        val roomData = roomSnapshot.getValue(RoomData::class.java)
                        roomData?.let { roomList.add(it) }
                    }
                    // Update the roomsList with the retrieved data
                    roomsList.value = roomList
                    Log.d("searchText","$roomList")
                } else {

                    // If no data exists, clear the list
                    roomsList.value = emptyList()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle the database error, e.g., log the error or show a message to the user
            }
        })
    }
}

