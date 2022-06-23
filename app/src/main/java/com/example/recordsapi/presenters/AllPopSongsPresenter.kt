package com.example.recordsapi.presenters

import com.example.recordsapi.database.LocalDataRepository
import com.example.recordsapi.domain.DomainSongs
import com.example.recordsapi.domain.mapToDomainRockSong
import com.example.recordsapi.rest.RockSongsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


    class AllPopSongsPresenter @Inject constructor (
        private val compositeDisposable: CompositeDisposable,
        private val rockSongsRepository: RockSongsRepository,
        private val localDataRepository: LocalDataRepository
    ) : AllPopSongsPresenterContract {
        private var songsViewContract: AllPopSongsViewContract? = null

        override fun init(viewContract: AllPopSongsViewContract) {
            songsViewContract = viewContract
        }

        override fun registerToNetworkState() {

        }

        override fun getPopSongs() {
            songsViewContract?.loadingSongs(true)
            getSongsFromNetwork()
        }

        override fun destroyPresenter() {
            songsViewContract = null
            compositeDisposable.dispose()
        }

        private fun getSongsFromNetwork() {
            rockSongsRepository.getAllSongs()
                .subscribeOn(Schedulers.io()).
                flatMapCompletable {
                    localDataRepository.insertSongs(it.songs.mapToDomainRockSong())

                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {getSongsFromDb()},
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
                    { rockSongs -> songsViewContract?.successSongsResponse(rockSongs ,true) },
                    { songsViewContract?.error(it) }
                )
                .also { compositeDisposable.add(it) }
        }
    }
    interface AllPopSongsPresenterContract{
        fun init(viewContract: AllPopSongsViewContract)
        fun registerToNetworkState()
        fun getPopSongs()
        fun destroyPresenter()
    }

    interface AllPopSongsViewContract{
        fun loadingSongs(isLoading: Boolean = false)
        fun successSongsResponse(rockSongs: List<DomainSongs>, isOffLine: Boolean = false)
        fun error(error: Throwable)


    }
