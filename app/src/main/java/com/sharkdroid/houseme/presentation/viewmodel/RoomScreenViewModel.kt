package com.sharkdroid.houseme.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.sharkdroid.houseme.domain.model.RoomData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RoomScreenViewModel @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val firebaseStorage: FirebaseStorage
):ViewModel() {

    private val _roomList = MutableStateFlow<List<RoomData>>(emptyList())
    val roomList = _roomList.asStateFlow()

    init {
        fetchRoomData()
    }

    // Method to fetch all room data from Firebase Realtime Database
    private fun fetchRoomData() {
        val roomRef = firebaseDatabase.reference.child("RoomForm")
        roomRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val roomDataList = mutableListOf<RoomData>()
                for (roomSnapshot in snapshot.children) {
                    val roomData = roomSnapshot.getValue(RoomData::class.java)
                    roomData?.let { roomDataList.add(it) }
                }
                _roomList.value = roomDataList
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle the error
            }
        })
    }

}