package com.example.bookingapp.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.bookingapp.api.BookingService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookingRepository @Inject constructor(
    val bookingService: BookingService,
    val database: AppDatabase
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

    @OptIn(ExperimentalPagingApi::class)
    fun getItems() = Pager(
        config = PagingConfig(pageSize = 10),
        remoteMediator = BookingRemoteMediator(bookingService, database)
    ) {
        BookingPagingSource(
            bookingService
        )
    }.flow
}