package com.css101.musicplayer.presentation.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.css101.musicplayer.domain.models.AudioFile
import com.css101.musicplayer.domain.usecase.GetMusicListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListViewModel(
    private val getMusicListUseCase: GetMusicListUseCase,
) : ViewModel() {

    private val _musicList = MutableLiveData<List<AudioFile>>()
    val musicList: LiveData<List<AudioFile>> = _musicList

    fun getMusicList() {
        if (_musicList.value == null) {
            viewModelScope.launch(Dispatchers.Default) {
                val data = getMusicListUseCase.execute()
                withContext(Dispatchers.Main) {
                    _musicList.value = data
                }
            }
        }
    }

}