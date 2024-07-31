package com.css101.musicplayer.data.repository

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import com.css101.musicplayer.domain.models.AudioFile
import com.css101.musicplayer.domain.repository.MusicRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MusicRepoImpl(private val context: Context) :
    MusicRepo {

    override suspend fun getMusicList(): List<AudioFile> = withContext(Dispatchers.IO) {
        val audioList = mutableListOf<AudioFile>()

        val projection = arrayOf(
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.DATA
        )

        val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"
        val sortOrder = "${MediaStore.Audio.Media.ARTIST} ASC"

        val cursor: Cursor? = context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            sortOrder
        )

        cursor?.use {
            val titleColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val artistColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
            val durationColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
            val albumIdColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)
            val dataColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)

            while (it.moveToNext()) {
                val title = it.getString(titleColumn)
                val artist = it.getString(artistColumn)
                val duration = it.getLong(durationColumn)
                val albumId = it.getLong(albumIdColumn)
                val fileUri = it.getString(dataColumn)
                val coverUri = getAlbumArtUri(albumId)
                audioList.add(
                    AudioFile(
                        title,
                        artist,
                        duration,
                        coverUri,
                        fileUri
                    )
                )
            }
        }

        audioList
    }

    private fun getAlbumArtUri(albumId: Long): String {
        val sArtworkUri = Uri.parse("content://media/external/audio/albumart")
        return Uri.withAppendedPath(sArtworkUri, albumId.toString()).toString()
    }
}