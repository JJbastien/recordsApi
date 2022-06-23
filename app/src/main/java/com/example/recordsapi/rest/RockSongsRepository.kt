package com.example.recordsapi.rest

import com.example.recordsapi.model.SongResponse
import com.example.recordsapi.model.Songs
import io.reactivex.Single
import javax.inject.Inject

interface RockSongsRepository {
    //pass the query inside the getAllSongs(query: rock, classic or pop) to change the path
    fun getAllSongs(): Single<SongResponse>
    fun getClassicSongs():Single<SongResponse>
    fun getPopSongs() : Single<SongResponse>
}
class RockSongsRepositoryImpl @Inject constructor(
    private val serviceApi: SongServiceApi
) : RockSongsRepository{

    override fun getAllSongs(): Single<SongResponse> {
       return serviceApi.getAllRockSongs()
    }

    override fun getClassicSongs(): Single<SongResponse> {
        return serviceApi.getAllClassicSongs()
    }

    override fun getPopSongs(): Single<SongResponse> {
        return serviceApi.getAllPopSongs()
    }


}