package com.example.bookingapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookingapp.data.BookingRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class DemoViewModel @Inject constructor(
    val bookingRepository: BookingRepository
) : ViewModel() {
    val bookings: StateFlow<List<String>> = bookingRepository
        .getData()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(6000),
            emptyList()
        )
}