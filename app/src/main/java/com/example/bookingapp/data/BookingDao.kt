package com.example.bookingapp.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookingDao {
    @Query("SELECT * FROM booking")
    fun getAll(): PagingSource<Int, Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(item: List<Item>)

    @Query("DELETE FROM booking")
    suspend fun clear()
}