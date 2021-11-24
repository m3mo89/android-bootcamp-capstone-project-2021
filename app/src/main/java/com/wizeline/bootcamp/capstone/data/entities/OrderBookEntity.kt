package com.wizeline.bootcamp.capstone.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wizeline.bootcamp.capstone.domain.AskDTO
import com.wizeline.bootcamp.capstone.domain.BidDTO

@Entity(tableName = "orders")
data class OrderBookEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val book: String,
    val asks: List<AskDTO>,
    val bids: List<BidDTO>,
    val updatedAt: String,
    val sequence: String
)