package com.example.recordsapi.DI

import com.example.recordsapi.*
import dagger.Component

@Component(
    modules = [
        ApplicationModule::class,
        RestModule::class,
        RepositoryModule::class,
        PresentersModule::class
    ]

)
interface SongsComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(rockSongsFragment: RockSongsFragment)
    fun inject(classicFragment: ClassicFragment)
    fun inject(popFragment: PopFragment)
    fun inject(detailsFragment: DetailsFragment)

}