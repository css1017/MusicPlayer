package com.css101.musicplayer.domain.utils

fun Long.toMinSec(): String {
    val minutes = this / 1000 / 60
    val seconds = (this / 1000 % 60).toInt()
    return String.format("%d:%02d", minutes, seconds)
}