package com.wizeline.bootcamp.capstone.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wizeline.bootcamp.capstone.utils.TableNames

@Entity(tableName = "${TableNames.TICKER}")
data class TickerEntity(
    @PrimaryKey
    val book: String,
    val volume: String,
    val high: String,
    val last: String,
    val low: String,
    val vwap: String,
    val ask: String,
    val bid: String,
    val createdAt: String
)