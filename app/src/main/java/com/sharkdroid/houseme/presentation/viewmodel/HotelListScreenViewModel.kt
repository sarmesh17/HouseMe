package com.sharkdroid.houseme.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.sharkdroid.houseme.domain.model.RoomListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelListScreenViewModel @Inject constructor(
    private val db:DatabaseReference
) :ViewModel() {


    private val _roomList = MutableStateFlow<List<RoomListResponse>>(emptyList())
    val roomList=_roomList.asStateFlow()

    private fun fetchRoomData(){

        viewModelScope.launch {

            db.addValueEventListener(object : ValueEventListener{

                override fun onDataChange(snapshot: DataSnapshot){

                    val rooms= mutableListOf<RoomListResponse>()

                    for (roomSnapShot in snapshot.children){

                        val room = roomSnapShot.getValue(RoomListResponse::class.java)
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