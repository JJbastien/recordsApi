package com.example.recordsapi.presenters

import android.util.Log
import com.example.recordsapi.database.LocalDataRepository
import com.example.recordsapi.domain.DomainSongs
import com.example.recordsapi.domain.mapToDomainRockSong
import com.example.recordsapi.model.SongResponse
import com.example.recordsapi.model.Songs
import com.example.recordsapi.rest.RockSongsRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject


class AllRockSongsPresenter @Inject constructor (
    private val compositeDisposable: CompositeDisposable,
    private val rockSongsRepository: RockSongsRepository,
    private val localDataRepository: LocalDataRepository
        ) : AllRockSongsPresenterContract {
    private var songsViewContract: AllRockSongsViewContract? = null

    override fun init(viewContract: AllRockSongsViewContract) {
        songsViewContract = viewContract
    }

    override fun registerToNetworkState() {

    }

    override fun getAllSongs() {
        songsViewContract?.loadingSongs(true)
        getSongsFromNetwork()
    }

    override fun destroyPresenter() {
        songsViewContract = null
        compositeDisposable.dispose()
    }

    private fun getSongsFromNetwork() {
        rockSongsRepository.getAllSongs()
            .subscribeOn(Schedulers.io())
           .flatMapCompletable {
               localDataRepository.insertSongs(it.songs.mapToDomainRockSong())
           }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { getAllSongs()},
                { error ->
                    songsViewContract?.error(error)
                    getSongsFromDb()
                }
            )
            .also { compositeDisposable.add(it) }
    }


   fun getSongsFromDb() {
        localDataRepository.getAllLocalSongs()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {  songsViewContract?.successSongsResponse(it,true) },
               { songsViewContract?.error(it) }
            )
            .also { compositeDisposable.add(it) }
    }
}

interface AllRockSongsPresenterContract{
    fun init(viewContract: AllRockSongsViewContract)
    fun registerToNetworkState()
    fun getAllSongs()
    fun destroyPresenter()
}

interface AllRockSongsViewContract{
    fun loadingSongs(isLoading: Boolean = false)
    fun successSongsResponse(songs: List<DomainSongs>, isOffLine: Boolean = false)
    fun error(error: Throwable)


}

