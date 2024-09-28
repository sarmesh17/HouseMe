package com.sharkdroid.houseme.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.sharkdroid.houseme.domain.model.RoomData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HouseListScreenViewModel @Inject constructor(
    private val db:FirebaseDatabase
) :ViewModel() {

    private val databaseReference = db.getReference("RoomForm")

    private val _roomList = MutableStateFlow<List<RoomData>>(emptyList())
    val roomList=_roomList.asStateFlow()

    init {
        fetchRoomData()
    }

    private fun fetchRoomData(){

        viewModelScope.launch {

            databaseReference.addValueEventListener(object : ValueEventListener{

                override fun onDataChange(snapshot: DataSnapshot){

                    val rooms= mutableListOf<RoomData>()

                    for (roomSnapShot in snapshot.children){

                        val room = roomSnapShot.getValue(RoomData::class.java)
                        room?.let {
                            rooms.add(it)
                        }

                        _roomList.value=rooms
                    }


                }

                override fun onCancelled(error: DatabaseError){

                    // Handle database error



                }

            })


        }


    }

}