package com.tarif.view

import android.content.res.ColorStateList
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 * @Author: Md Tarif Chakder
 * @Date: 07-02-2022
 */


fun View.snackbar(
    message: Int, duration: Int = Snackbar.LENGTH_SHORT,
    actionName: Int = 0, actionTextColor: Int = 0, action: (View) -> Unit = {}
): Snackbar {
    val snackbar = Snackbar.make(this, message, duration)

    if (actionName != 0 && action != {}) snackbar.setAction(actionName, action)
    if (actionTextColor != 0) snackbar.setActionTextColor(actionTextColor)

    snackbar.show()
    return snackbar
}

fun View.snackbar(
    message: Int, duration: Int = Snackbar.LENGTH_SHORT,
    actionName: String = "", actionTextColor: Int = 0, action: (View) -> Unit = {}
): Snackbar {
    val snackbar = Snackbar.make(this, message, duration)

    if (actionName != "" && action != {}) snackbar.setAction(actionName, action)
    if (actionTextColor != 0) snackbar.setActionTextColor(actionTextColor)

    snackbar.show()
    return snackbar
}

fun View.snackbar(
    message: String, duration: Int = Snackbar.LENGTH_SHORT,
    actionName: Int = 0, actionTextColor: Int = 0, action: (View) -> Unit = {}
): Snackbar {
    val snack = Snackbar.make(this, message, duration)

    if (actionName != 0 && action != {}) snack.setAction(actionName, action)
    if (actionTextColor != 0) snack.setActionTextColor(actionTextColor)

    snack.show()
    return snack
}

fun View.snackbar(
    message: String, duration: Int = Snackbar.LENGTH_SHORT,
    actionName: String = "", actionTextColor: Int = 0, action: (View) -> Unit = {}
): Snackbar {
    val snack = Snackbar.make(this, message, duration)

    if (actionName != "" && action != {}) snack.setAction(actionName, action)
    if (actionTextColor != 0) snack.setActionTextColor(actionTextColor)

    snack.show()
    return snack
}


/**
 * Custom Ripple effect with corner
 * @param r radius or ripple
 * @param color set ripple color
 * @param transparentRange transparent color range
 *
 * */
fun View.setCustomRippleEffect(r: Float = 0f, color: Int, transparentRange: Int = 10) {
    try {
        val shapeDrawable = roundShapeDrawable(r)
        val rippleDrawable =
            RippleDrawable(ColorStateList.valueOf(ColorUtil.transparentAccentColor(color, transparentRange)), this.background, shapeDrawable)
        this.background = rippleDrawable
    } catch (e: Exception) {

    }
}

fun View.roundShapeDrawable(r: Float): ShapeDrawable {
    val array = floatArrayOf(r, r, r, r, r, r, r, r)
    return ShapeDrawable(RoundRectShape(array, null, null))
}

/**
 * For rounded bottomsheet
 * you can use rootview of the bottomsheet layout with color and corner
 * @param color color of background
 * @param r radius
 * */
fun View.setBottomSheetBackgroundColor(color: Int, r: Float = 15.0f) {
    val array = floatArrayOf(r, r, r, r, 0.0f, 0.0f, 0.0f, 0.0f)
    val shapeDrawable = ShapeDrawable(RoundRectShape(array, null, null))
    shapeDrawable.paint.color = color
    background = shapeDrawable
}
