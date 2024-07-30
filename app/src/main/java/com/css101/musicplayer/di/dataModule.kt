package com.css101.musicplayer.di

import com.css101.musicplayer.data.repository.MusicRepoImpl
import com.css101.musicplayer.domain.repository.MusicRepo
import org.koin.dsl.module

val dataModule = module {
    single<MusicRepo> { MusicRepoImpl(get()) }
}