package com.sharkdroid.houseme.presentation.houselist.components.model

data class HouseListCardModel(
    val imageRes : Int, val header: String,val description: String, val discount :String,
    val price :String, val button :String,val city:String
)