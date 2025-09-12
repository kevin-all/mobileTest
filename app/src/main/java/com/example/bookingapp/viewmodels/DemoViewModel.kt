package com.example.bookingapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.bookingapp.api.BookingService
import com.example.bookingapp.data.BookingPagingSource
import com.example.bookingapp.data.BookingRemoteMediator
import com.example.bookingapp.data.BookingRepository
import com.example.bookingapp.data.Item
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class DemoViewModel @Inject constructor(
    val bookingService: BookingService,
    val bookingRepository: BookingRepository
) : ViewModel() {
    val bookings: StateFlow<List<String>> = bookingRepository
        .getData()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(6000),
            emptyList()
        )

    val items = Pager(
        PagingConfig(pageSize = 10)
    ) {
        BookingPagingSource(
            bookingService
        )
    }.flow.cachedIn(viewModelScope)
}