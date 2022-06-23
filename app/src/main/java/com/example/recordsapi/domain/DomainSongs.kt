package com.example.recordsapi.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.recordsapi.model.Songs


@Entity( tableName = "songs_table")
data class DomainSongs(
    @PrimaryKey
    val artistId: Int?,
    val artistName: String?,
    val collectionName: String?,
    val artworkUrl60: String?,
    val trackPrice :  Double?,
    val previewUrl: String?
    )


// extension function to map the network data to the domain data
fun List<Songs>.mapToDomainRockSong() : List<DomainSongs> {
 return this.map{ networkSong ->
     DomainSongs(
         artistId = networkSong.artistId?:0,
         artistName = networkSong.artistName?: " invalid name",
         collectionName = networkSong.collectionName?: " invalid collection",
         artworkUrl60 = networkSong.artworkUrl60?: "invalid url",
         trackPrice = networkSong.trackPrice?:0.0,
         previewUrl = networkSong.previewUrl?: "invalid url"
     )
 }
}
