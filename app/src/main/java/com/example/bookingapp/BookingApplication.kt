package com.example.bookingapp

import android.app.Application
import com.example.bookingapp.di.AppComponent
import com.example.bookingapp.di.AppModule
import com.example.bookingapp.di.DaggerAppComponent

class BookingApplication : Application() {
    val appComponent: AppComponent = DaggerAppComponent
        .builder()
        .appModule(AppModule(this))
        .build()
}