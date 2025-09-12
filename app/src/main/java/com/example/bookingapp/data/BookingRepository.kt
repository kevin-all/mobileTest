package com.example.bookingapp.data

import com.example.bookingapp.api.BookingService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookingRepository @Inject constructor(
    val bookingService: BookingService,
) {
    fun getData(): Flow<List<String>> {
        return flow {
            runCatching {
                bookingService.fetchBookings()
            }.onSuccess { result ->

                val li = result.items.map {
                    "${it.originAndDestinationPair} -->> ${it.originAndDestinationPair}"
                }
                emit(li)
            }
        }
    }
}