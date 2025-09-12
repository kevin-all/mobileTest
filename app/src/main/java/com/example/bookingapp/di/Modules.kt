package com.example.bookingapp.di

import android.app.Application
import android.content.Context
import com.example.bookingapp.BookingApplication
import com.example.bookingapp.MainActivity
import com.example.bookingapp.api.BookingService
import com.example.bookingapp.api.MockInterceptor
import com.example.bookingapp.data.AppDatabase
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class NetModule {
    @Singleton
    @Provides
    fun provideBookingService(interceptor: MockInterceptor): BookingService =
        BookingService.create(interceptor)
}

@Module
class DbModule {
    @Singleton
    @Provides
    fun providesDb(context: Context): AppDatabase =
        AppDatabase.getInstance(context)
}

@Module
class AppModule(private val app: Application) {
    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return app.applicationContext
    }
}

@Singleton
@Component(modules = [AppModule::class, NetModule::class, DbModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}