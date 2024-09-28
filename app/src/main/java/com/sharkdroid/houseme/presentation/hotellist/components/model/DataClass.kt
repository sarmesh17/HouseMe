package com.sharkdroid.houseme.presentation.hotellist.components.model

import androidx.annotation.DrawableRes

data class HotelListCardModel(
    val imageRes: Int, val header: String, val description: String, val discount: String,
    val price: String, val button: String, val city: String
)