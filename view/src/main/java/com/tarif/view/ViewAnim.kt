package com.tarif.view

import android.view.View

/**
 * @Author: Md Tarif Chakder
 * @Date: 07-02-2022
 */

fun View.toggleArrow(duration: Long = 200): Boolean {
    return if (rotation == 0f) {
        animate().setDuration(duration).rotation(180f)
        true
    } else {
        animate().setDuration(duration).rotation(0f)
        false
    }
}

@JvmOverloads
fun toggleArrow(show: Boolean, view: View, delay: Boolean = true): Boolean {
    return if (show) {
        view.animate().setDuration((if (delay) 200 else 0).toLong()).rotation(180f)
        true
    } else {
        view.animate().setDuration((if (delay) 200 else 0).toLong()).rotation(0f)
        false
    }
}

