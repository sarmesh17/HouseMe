package com.sharkdroid.houseme.presentation.roomdetail.components.model

data class RoomData(
    val name: String,
    val rating: Float,
    val likes: Int,
    val location: String,
    val imageResource: Int,
    val description: String,
    val phoneNumber: String,
    val checkInTime: String,
    val checkOutTime: String,
    val pricePerNight: String,
)
