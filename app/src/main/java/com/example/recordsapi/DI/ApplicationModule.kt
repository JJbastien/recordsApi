package com.example.recordsapi.DI

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.recordsapi.database.SongsDao
import com.example.recordsapi.database.SongsDatabase
import com.example.recordsapi.utils.MIGRATION_1_2
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule (private val application: Application) {

    @Provides
    fun providesContext() :Context = application.applicationContext

    @Provides

    fun providesRoomDb(context: Context): SongsDatabase =
        Room.databaseBuilder(
            context,
            SongsDatabase::class.java,
            "Songs Database"
        ).build()
           // .addMigrations(MIGRATION_1_2)


    @Provides
    fun providesDao(database: SongsDatabase) : SongsDao =
        database.getSongsDao()
}