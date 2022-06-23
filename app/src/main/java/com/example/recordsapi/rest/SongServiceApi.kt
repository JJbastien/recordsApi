package com.example.recordsapi.rest

import com.example.recordsapi.model.SongResponse
import com.example.recordsapi.model.Songs
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SongServiceApi {
    @GET(ROCKSONGS_PATH)
    fun getAllRockSongs(): Single<SongResponse>

    @GET(POPSONGS_PATH)
    fun getAllPopSongs(): Single<SongResponse>

    @GET(CLASSIC_SONGS_PATH)
    fun getAllClassicSongs(): Single<SongResponse>

    companion object {
        const val BASE_URL ="https://itunes.apple.com/"
        private const val ROCKSONGS_PATH ="search?term=rock&amp;media=music&amp;entity=song&amp;limit=50"
        private const val CLASSIC_SONGS_PATH ="search?term=classick&amp;media=music&amp;entity=song&amp;limit=50"
        private const val POPSONGS_PATH ="search?term=pop&amp;media=music&amp;entity=song&amp;limit=50"
    }
}