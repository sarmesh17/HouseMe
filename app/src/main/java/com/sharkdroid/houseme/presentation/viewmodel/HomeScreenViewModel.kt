package com.sharkdroid.houseme.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.sharkdroid.houseme.domain.model.RoomData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val db: FirebaseDatabase
) : ViewModel() {

    private val databaseRef = db.getReference("RoomForm")

    // MutableState for the list of RoomData and loading state

    val data=MutableStateFlow<List<RoomData>>(emptyList())


    // Function to search for rooms based on location input with delay
    fun searchRoomsByLocation(locationInput: String) {
        viewModelScope.launch {
            val query = databaseRef.orderByChild("propertyLocation").equalTo(locationInput)
            query.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val roomList = mutableListOf<RoomData>()
                        for (roomSnapshot in dataSnapshot.children) {
                            val roomData = roomSnapshot.getValue(RoomData::class.java)
                            roomData?.let { roomList.add(it) }
                            data.value = roomList
                        }
                    } else {
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e("com.sharkdroid.houseme.presentation.viewmodel.HomeScreenViewModel", "Error fetching data", databaseError.toException())
                }
            })
        }
    }
}
