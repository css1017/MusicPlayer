package com.css101.musicplayer.domain.repository

import com.css101.musicplayer.domain.models.AudioFile

interface MusicRepo {
    suspend fun getMusicList(): List<AudioFile>
}