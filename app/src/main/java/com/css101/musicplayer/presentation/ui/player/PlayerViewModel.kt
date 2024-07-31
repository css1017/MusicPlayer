package com.css101.musicplayer.presentation.ui.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.css101.musicplayer.domain.models.AudioFile
import com.css101.musicplayer.domain.usecase.GetMusicListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayerViewModel(private val getMusicListUseCase: GetMusicListUseCase) : ViewModel() {

    private val _audioFile = MutableLiveData<AudioFile>()
    val audioFile: LiveData<AudioFile> = _audioFile

    private val _list = MutableLiveData<List<AudioFile>>()
    val list: LiveData<List<AudioFile>> = _list

    fun getMusicList() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = getMusicListUseCase.execute()
            _list.postValue(list)
        }
    }

}