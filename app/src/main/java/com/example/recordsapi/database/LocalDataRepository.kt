package com.example.recordsapi.database

import com.example.recordsapi.domain.DomainSongs
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

interface LocalDataRepository {
    fun getAllLocalSongs(): Single<List<DomainSongs>>
    fun insertSongs( songs:List<DomainSongs>) : Completable
    fun getSongById(songId: Int):Single<DomainSongs>
}

class LocalDataRepositoryImpl @Inject constructor(
    private val songsDao: SongsDao
) : LocalDataRepository {
    override fun getAllLocalSongs(): Single<List<DomainSongs>> {
    return songsDao.getAllSongs()
    }


    override fun insertSongs(songs:List<DomainSongs>): Completable {
        return songsDao.insertAllSongs(*songs.toTypedArray())

    }

   override fun getSongById(songId: Int):Single<DomainSongs>
  { return songsDao.getSongById(songId)
    }
}