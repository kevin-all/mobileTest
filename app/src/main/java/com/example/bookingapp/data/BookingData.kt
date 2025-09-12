package com.example.bookingapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("code")
    val code: String,
    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("url")
    val url: String
)

data class OriginAndDestinationPair(
    @SerializedName("destination")
    val destination: Info,
    @SerializedName("destinationCity")
    val destinationCity: String,
    @SerializedName("origin")
    val origin: Info,
    @SerializedName("originCity")
    val originCity: String,
)

@Entity(tableName = "booking")
data class Item(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("originAndDestinationPair")
    val originAndDestinationPair: OriginAndDestinationPair
)

data class Booking(
    val id: Int = 0,
    @SerializedName("shipReference")
    val shipReference: String,
    @SerializedName("shipToken")
    val shipToken: String,
    @SerializedName("expiryTime")
    val expiryTime: Long,
    @SerializedName("segments")
    val items: List<Item>,
)
