package com.example.bookingapp.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.paging.RemoteMediator.MediatorResult
import androidx.room.withTransaction
import com.example.bookingapp.api.BookingService
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class BookingRemoteMediator @Inject constructor(
    val bookingService: BookingService,
    val db: AppDatabase,
): RemoteMediator<Int, Booking>() {
    @OptIn(ExperimentalPagingApi::class)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Booking>
    ): MediatorResult {
        // TODO
        val page = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> (state.lastItemOrNull()?.id?.let { (it / state.config.pageSize) + 1 } ?: 1)
        }

        return try {
            // Load page here
            val response = bookingService.fetchBookings()
            db.withTransaction {
                if (loadType == LoadType.REFRESH) db.bookingDao().clear()
                db.bookingDao().insertAll(response.items)
            }
            MediatorResult.Success(endOfPaginationReached = true)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}