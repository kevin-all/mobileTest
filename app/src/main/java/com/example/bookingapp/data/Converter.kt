package com.example.bookingapp.data

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converter {
    val gson = Gson()

    @TypeConverter fun convertOriginAndDestinationPair(data: OriginAndDestinationPair) =
        gson.toJson(data)

    @TypeConverter fun convertString(data: String): OriginAndDestinationPair =
        gson.fromJson(data, OriginAndDestinationPair::class.java)
}