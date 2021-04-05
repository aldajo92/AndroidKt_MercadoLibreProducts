package com.projects.aldajo92.mercadolibreproducts.presentation.utils

import android.app.Activity
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowInsets

fun calculateBestSpanCount(activity: Activity, posterWidth: Int): Int {
    val screenWidth = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        activity.windowManager?.currentWindowMetrics?.let { windowMetrics ->
            val insets = windowMetrics
                .windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.width() - insets.left - insets.right
        } ?: 0
    } else {
        val displayMetrics = DisplayMetrics()
        activity.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        displayMetrics.widthPixels
    }
    return (screenWidth / posterWidth)
}