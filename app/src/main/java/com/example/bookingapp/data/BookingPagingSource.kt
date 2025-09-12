package com.example.bookingapp.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.bookingapp.api.BookingService
import javax.inject.Inject

class BookingPagingSource @Inject constructor(
    val bookingService: BookingService
): PagingSource<Int, Item>() {
    override suspend fun load(
        params: LoadParams<Int>): LoadResult<Int, Item> {
        try {
            val nextpage = params.key ?: 1
            val response = bookingService.fetchBookings()
            return LoadResult.Page(
                data = response.items,
                prevKey = null,
                nextKey = nextpage + 1
            )
        } catch(e : Exception) {
            // TODO
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}