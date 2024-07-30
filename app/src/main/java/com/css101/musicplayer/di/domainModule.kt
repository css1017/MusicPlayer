package com.css101.musicplayer.di

import com.css101.musicplayer.domain.usecase.GetMusicListUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetMusicListUseCase(get()) }
}