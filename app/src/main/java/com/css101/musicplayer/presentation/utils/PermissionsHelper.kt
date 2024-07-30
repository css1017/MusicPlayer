package com.css101.musicplayer.presentation.utils

import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

class PermissionsHelper(activityResultCaller: ActivityResultCaller) :
    ActivityResultCallerHelper(activityResultCaller) {

    private val requestPermissionLauncher =
        caller?.registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted -> if (granted) doIfGranted() }

    private var doIfGranted = {}

    fun request(permission: String, doIfGranted: () -> Unit) {
        when (permission.isGranted()) {
            true -> doIfGranted.invoke()
            false -> {
                this.doIfGranted = doIfGranted
                requestPermissionLauncher?.launch(permission)
            }
            null -> Unit
        }
    }

    private fun String.isGranted() = context?.let {
        ContextCompat.checkSelfPermission(it, this) == PackageManager.PERMISSION_GRANTED
    }

}
