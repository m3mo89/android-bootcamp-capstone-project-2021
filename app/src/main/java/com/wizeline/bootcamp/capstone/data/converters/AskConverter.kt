package com.wizeline.bootcamp.capstone.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.wizeline.bootcamp.capstone.domain.AskDTO

class AskConverter {
    @TypeConverter
    fun fromAsks(value: List<AskDTO>): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toAsks(value: String): List<AskDTO> {
        return Gson().fromJson(value, Array<AskDTO>::class.java).toList()
    }
}
