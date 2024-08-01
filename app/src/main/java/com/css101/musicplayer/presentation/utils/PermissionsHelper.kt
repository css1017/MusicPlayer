package com.css101.musicplayer.presentation.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

class PermissionsHelper(activityResultCaller: ActivityResultCaller) : ActivityResultCallerHelper(activityResultCaller) {

    private var permissionReceived = {}
    private var permissionNotReceived = {}

    private val requestPermissionLauncher =
        caller?.registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            if (granted) {
                permissionReceived()
            } else {
                permissionNotReceived()
            }
        }


    fun request(whenReceived: () -> Unit, whenNotReceived: () -> Unit) {
        this.permissionReceived = whenReceived
        this.permissionNotReceived = whenNotReceived

        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            android.Manifest.permission.READ_MEDIA_AUDIO
        } else {
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        }

        when (permission.isGranted()) {
            true -> whenReceived.invoke()
            false -> requestPermissionLauncher?.launch(permission)
            null -> Unit
        }
    }
    private fun String.isGranted() = context?.let {
        ContextCompat.checkSelfPermission(it, this) == PackageManager.PERMISSION_GRANTED
    }
    fun toAppSettings(context: Context) {
        val intent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", context.packageName, null)
        ).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }
}
