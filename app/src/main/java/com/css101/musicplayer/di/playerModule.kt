package com.css101.musicplayer.di

import androidx.media3.exoplayer.ExoPlayer
import org.koin.core.qualifier.named
import org.koin.dsl.module

val playerModule = module {

    scope(named("myScope")) {
        scoped { ExoPlayer.Builder(get()).build() }
    }}