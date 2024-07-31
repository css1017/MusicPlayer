package com.css101.musicplayer.domain.models

import android.os.Parcelable
import com.css101.musicplayer.domain.utils.toMinSec
import kotlinx.parcelize.Parcelize

@Parcelize
data class AudioFile(
    val title: String,
    val artist: String?,
    val length: Long?,
    val coverUri: String?,
    val fileUri: String
): Parcelable {
    val formattedLength: String
        get() = length?.toMinSec() ?: "0:00"
}
