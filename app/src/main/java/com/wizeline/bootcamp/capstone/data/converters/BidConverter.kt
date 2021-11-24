package com.wizeline.bootcamp.capstone.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.wizeline.bootcamp.capstone.domain.BidDTO

class BidConverter {
    @TypeConverter
    fun fromBids(value: List<BidDTO>): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toBids(value: String): List<BidDTO> {
        return Gson().fromJson(value, Array<BidDTO>::class.java).toList()
    }
}