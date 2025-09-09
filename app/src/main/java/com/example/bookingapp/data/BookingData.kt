package com.example.bookingapp.data

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

data class Segment(
    val id: Int,
    val originAndDestinationPair: OriginAndDestinationPair
)

data class Booking(
    val shipReference: String,
    val shipToken: String,
    val expiryTime: Long,
    val segments: List<Segment>,
)
