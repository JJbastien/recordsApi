package com.example.recordsapi.presenters

import com.example.recordsapi.database.LocalDataRepository
import com.example.recordsapi.domain.DomainSongs
import com.example.recordsapi.domain.mapToDomainRockSong
import com.example.recordsapi.rest.RockSongsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


    class AllClassicSongsPresenter @Inject constructor (
        private val compositeDisposable: CompositeDisposable,
        private val rockSongsRepository: RockSongsRepository,
        private val localDataRepository: LocalDataRepository
    ) : AllClassicSongsPresenterContract {
        private var songsViewContract: AllClassicSongsViewContract? = null

        override fun init(viewContract: AllClassicSongsViewContract) {
            songsViewContract = viewContract
        }

        override fun registerToNetworkState() {

        }

        override fun getClassicSongs() {
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
                    { songs -> songsViewContract?.successSongsResponse(songs ,true) },
                    { songsViewContract?.error(it) }
                )
                .also { compositeDisposable.add(it) }
        }
    }

    interface AllClassicSongsPresenterContract{
        fun init(viewContract: AllClassicSongsViewContract)
        fun registerToNetworkState()
        fun getClassicSongs()
        fun destroyPresenter()
    }

    interface AllClassicSongsViewContract{
        fun loadingSongs(isLoading: Boolean = false)
        fun successSongsResponse(songs: List<DomainSongs>, isOffLine: Boolean = false)
        fun error(error: Throwable)


    }
