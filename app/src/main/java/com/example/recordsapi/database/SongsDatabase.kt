package com.example.recordsapi.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recordsapi.domain.DomainSongs

@Database(entities = [DomainSongs::class], exportSchema = false, version = 1 )
abstract class SongsDatabase : RoomDatabase() {
    abstract fun getSongsDao() : SongsDao
}