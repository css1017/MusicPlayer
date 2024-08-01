package com.css101.musicplayer.di

import com.css101.musicplayer.presentation.ui.list.ListViewModel
import com.css101.musicplayer.presentation.ui.player.PlayerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { PlayerViewModel(get()) }
    viewModel { ListViewModel(get()) }
}