package com.wizeline.bootcamp.capstone.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wizeline.bootcamp.capstone.domain.AskDTO
import com.wizeline.bootcamp.capstone.domain.BidDTO
import com.wizeline.bootcamp.capstone.utils.TableNames

@Entity(tableName = "${TableNames.ORDERS}")
data class OrderBookEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val book: String,
    val asks: List<AskDTO>,
    val bids: List<BidDTO>,
    val updatedAt: String,
    val sequence: String
)