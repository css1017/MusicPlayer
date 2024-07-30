package com.css101.musicplayer.domain.usecase

import com.css101.musicplayer.domain.models.AudioFile
import com.css101.musicplayer.domain.repository.MusicRepo

class GetMusicListUseCase(private val musicRepo: MusicRepo) {
    suspend fun execute(): List<AudioFile> {
        return musicRepo.getMusicList()

    }
}