package com.css101.musicplayer.di

import com.css101.musicplayer.presentation.MainViewModel
import com.css101.musicplayer.presentation.ui.list.ListViewModel
import com.css101.musicplayer.presentation.ui.player.PlayerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel() }
    viewModel { PlayerViewModel(getScope("myScope").get()) }
    viewModel { ListViewModel(get()) }
}