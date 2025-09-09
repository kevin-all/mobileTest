package com.example.bookingapp.api

import com.example.bookingapp.data.Booking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface BookingService {

    @GET("booking/list")
    suspend fun fetchBookings(): Booking

    companion object {
        private const val BASE_URL = "https://example.com/"

        fun create(interceptor: MockInterceptor): BookingService {
            // val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BookingService::class.java)
        }
    }
}