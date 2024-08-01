package com.css101.musicplayer.presentation.ui.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.css101.musicplayer.domain.models.AudioFile

class PlayerViewModel(private val player: ExoPlayer) : ViewModel() {

    private val _audioFile = MutableLiveData<AudioFile>()
    val audioFile: LiveData<AudioFile> = _audioFile

    init {
        addListener()
    }

    fun saveAudio(audio: AudioFile?) = audio?.let {
        if (_audioFile.value != it) {
            _audioFile.value = it
            setMediaItem(it)
            playAudio()
        }
    }

    private fun setMediaItem(audio: AudioFile) {
        val mediaItem = MediaItem.Builder()
            .setUri(audio.fileUri)
            .build()
        player.setMediaItem(mediaItem)
    }

    private var _isPlaying = MutableLiveData(false)
    val isPlaying: LiveData<Boolean> = _isPlaying

    fun playPause() {
        when (isPlaying.value) {
            false -> playAudio()
            true -> pausePlayback()
            else -> {}
        }
    }

    private fun addListener() {
        player.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                _isPlaying.postValue(isPlaying)
            }

            override fun onPlaybackStateChanged(state: Int) {
                if (state == Player.STATE_ENDED) {
                    audioFile.value?.let {
                        setMediaItem(audioFile.value!!)
                        pausePlayback()
                    }
                }
            }
        })
    } //todo remove listener on close

    private fun playAudio() {
        player.prepare()
        player.playWhenReady = true
        _isPlaying.value = true
    }

    private fun pausePlayback() {
        player.playWhenReady = false
        _isPlaying.value = false
    }

    override fun onCleared() {
//        player.release()
        super.onCleared()
    }
}