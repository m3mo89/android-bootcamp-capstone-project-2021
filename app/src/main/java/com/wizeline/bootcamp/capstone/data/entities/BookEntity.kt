package com.wizeline.bootcamp.capstone.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wizeline.bootcamp.capstone.utils.TableNames

@Entity(tableName = "${TableNames.BOOKS}")
data class BookEntity(
    @PrimaryKey
    val book: String,
    val minimumAmount: String,
    val maximumAmount: String,
    val minimumPrice: String,
    val maximumPrice: String,
    val minimumValue: String,
    val maximumValue: String
)
