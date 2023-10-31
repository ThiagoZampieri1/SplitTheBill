package com.example.splitthebill.model


data class Member(
    val name: String,
    var amountPaid: Double,
    var itemPrice1: Double,
    var itemPrice2: Double,
    var itemPrice3: Double,
    var itemBought1: String,
    var itemBought2: String,
    var itemBought3: String,
    var amountToReceive: Double,
    var amountToPay: Double
)
