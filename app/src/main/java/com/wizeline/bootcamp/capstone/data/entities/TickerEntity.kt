package com.wizeline.bootcamp.capstone.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ticker")
data class TickerEntity(
    @PrimaryKey
    var book: String,
    var volume: String,
    var high: String,
    var last: String,
    var low: String,
    var vwap: String,
    var ask: String,
    var bid: String,
    var createdAt: String
)