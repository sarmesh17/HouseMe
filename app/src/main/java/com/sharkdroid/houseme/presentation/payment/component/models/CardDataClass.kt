package com.sharkdroid.houseme.presentation.payment.component.models

data class  CardContext(
    val title :String,
    val cardNo :String,
    val cardHolderName:String,
    val expiryDate:String,
    val cvv:String
)