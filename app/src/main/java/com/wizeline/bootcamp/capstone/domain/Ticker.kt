package com.wizeline.bootcamp.capstone.domain

data class Ticker(
    val id:String,
    val spriteUrl: String,
    val cryptoName:String,
    val lastPrice: String,
    val highPrice: String,
    val lowPrice: String,
    val ask:String,
    val bid: String,
)