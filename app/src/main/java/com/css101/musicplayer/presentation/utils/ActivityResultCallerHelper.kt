package com.css101.musicplayer.presentation.utils

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.result.ActivityResultCaller
import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference

abstract class ActivityResultCallerHelper(activityResultCaller: ActivityResultCaller) {

    private val weakActivityResultCaller = WeakReference(activityResultCaller)

    protected val caller: ActivityResultCaller?
        get() = weakActivityResultCaller.get()

    protected val context: Context?
        get() = when (caller) {
            is Fragment -> (caller as? Fragment)?.context
            is ContextWrapper -> (caller as? ContextWrapper)?.baseContext
            null -> null
            else -> throw Exception("Need to provide context for $caller")
        }

}
