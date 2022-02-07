package com.tarif.view

import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RelativeLayout

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

fun CompoundButton.setCheckedWithoutAnimation(checked: Boolean) {
    val beforeVisibility = visibility
    visibility = View.INVISIBLE
    isChecked = checked
    visibility = beforeVisibility
}


fun View.moveViewRelatively(left: Int, top: Int) {
    val location = getScreenLocation(this)
    val params = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    params.setMargins(left + location[0], top + location[1], 0, 0)
    val p = layoutParams
    params.width = p.width
    params.height = p.height
    layoutParams = params
}

fun View.moveView(left: Int, top: Int) {
    val params = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    params.setMargins(left, top, 0, 0)
    val p = layoutParams
    params.width = p.width
    params.height = p.height
    layoutParams = params
}

fun getScreenLocation(view: View): IntArray {
    val locations = IntArray(2)
    view.getLocationOnScreen(locations)
    return locations
}

