package com.example.recordsapi.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recordsapi.domain.DomainSongs
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface SongsDao {

   @Query("SELECT * FROM songs_table")
   fun getAllSongs(): Single<List<DomainSongs>>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun insertAllSongs(vararg songs: DomainSongs) : Completable

   @Query("SELECT * FROM songs_table WHERE artistId LIKE :songId")
   fun getSongById(songId: Int): Single<DomainSongs>
}