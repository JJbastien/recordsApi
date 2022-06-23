package com.example.recordsapi.DI

import android.net.Network
import com.example.recordsapi.database.LocalDataRepository
import com.example.recordsapi.database.LocalDataRepositoryImpl
import com.example.recordsapi.rest.RockSongsRepository
import com.example.recordsapi.rest.RockSongsRepositoryImpl
import dagger.Binds
import dagger.Module


@Module
abstract class RepositoryModule{
     @Binds
    abstract fun provideLocalRepository(
         localDataRepositoryImpl: LocalDataRepositoryImpl) : LocalDataRepository

    @Binds
    abstract fun provideNetWorkRepository(
        networkRepositoryImpl: RockSongsRepositoryImpl ) : RockSongsRepository


}