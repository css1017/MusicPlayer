package com.css101.musicplayer.presentation

import androidx.lifecycle.ViewModel
import androidx.media3.exoplayer.ExoPlayer
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent.getKoin

class MainViewModel: ViewModel() {

    private var scope: Scope? = null

    fun createScope() {
        if (scope == null){
            scope = getKoin().createScope("myScope", named("myScope"))
        }
    }

    fun closeScope() {
        scope?.get<ExoPlayer>()?.release()
        scope?.close()
    }
}