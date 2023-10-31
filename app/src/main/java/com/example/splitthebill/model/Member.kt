package com.example.splitthebill.model


data class Member(
    val name: String,
    var amountPaid: Double,
    var itemBought: String,
    var amountToReceive: Double,
    var amountToPay: Double
)
