package com.example.recordsapi.DI

import com.example.recordsapi.presenters.*
import dagger.Binds
import dagger.Module

@Module
 abstract class PresentersModule {

    @Binds
    abstract fun providesAllRockSongsPresenterContract(
        allRockSongsPresenter: AllRockSongsPresenter): AllRockSongsPresenterContract

    @Binds
    abstract fun providesAllClassicSongsPresenterContract(
        allClassicSongsPresenter: AllClassicSongsPresenter): AllClassicSongsPresenterContract

    @Binds
    abstract fun providesAllPopSongsPresenterContract(
        allPopSongsPresenter: AllPopSongsPresenter): AllPopSongsPresenterContract

 }