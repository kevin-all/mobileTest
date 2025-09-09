package com.example.bookingapp.api

// MockInterceptor.kt
import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException
import javax.inject.Inject

class MockInterceptor @Inject constructor(
    private val context: Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        if (request.url.toString().contains("example.com")) {
            return try {
                val jsonString = context.assets.open("booking.json").bufferedReader().use { it.readText() }

                Response.Builder()
                    .code(200)
                    .message("OK")
                    .protocol(Protocol.HTTP_2)
                    .request(request)
                    .body(
                        jsonString.toResponseBody("application/json".toMediaType())
                    )
                    .build()
            } catch (e: IOException) {
                chain.proceed(request)
            }
        }

        return chain.proceed(request)
    }
}