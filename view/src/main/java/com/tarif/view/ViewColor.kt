package com.tarif.view

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Build
import android.view.Menu
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat

/**
 * @Author: Md Tarif Chakder
 * @Date: 07-02-2022
 */


/**
 * Custom Ripple effect with corner
 * @param r radius or ripple
 * @param color set ripple color
 * @param transparentRange transparent color range
 *
 * */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun View.setCustomRippleEffect(r: Float = 0f, color: Int, transparentRange: Int = 10) {
    try {
        val shapeDrawable = roundShapeDrawable(r)
        val rippleDrawable =
            RippleDrawable(ColorStateList.valueOf(ColorUtil.transparentAccentColor(color, transparentRange)), this.background, shapeDrawable)
        this.background = rippleDrawable
    } catch (e: Exception) {

    }
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

fun Activity.setSystemBarColor(@ColorRes color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = ContextCompat.getColor(this, color)
    }
}

fun Context.setSystemBarColorDialog(dialog: Dialog, @ColorRes color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val window = dialog.window
        window!!.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = ContextCompat.getColor(this, color)
    }
}

fun Activity.setSystemBarLight() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val view = findViewById<View>(android.R.id.content)
        var flags = view.systemUiVisibility
        flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        view.systemUiVisibility = flags
    }
}

fun Dialog.setSystemBarLightDialog() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val view = this.findViewById<View>(android.R.id.content)
        var flags = view.systemUiVisibility
        flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        view.systemUiVisibility = flags
    }
}

fun Activity.clearSystemBarLight(@ColorRes color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val window = window
        window.statusBarColor = ContextCompat.getColor(this, color)
    }
}

/**
 * Making notification bar transparent
 */
fun Activity.setSystemBarTransparent() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
    }
}

@Suppress("DEPRECATION")
fun Toolbar.changeNavigationIconColor(@ColorInt color: Int) {
    val drawable = this.navigationIcon
    drawable?.apply {
        mutate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
        } else {
            setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }
}

@Suppress("DEPRECATION")
fun Menu.changeMenuIconColor(@ColorInt color: Int) {
    for (i in 0 until this.size()) {
        val drawable = this.getItem(i).icon
        drawable?.apply {
            mutate()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
            } else {
                setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
            }
        }
    }
}

@Suppress("DEPRECATION")
fun Toolbar.changeOverflowMenuIconColor(@ColorInt color: Int) {
    try {
        val drawable = this.overflowIcon
        drawable?.apply {
            mutate()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
            } else {
                setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

}

fun View.gradient(radius: Float, vararg colors: Int) {
    background = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors).apply { cornerRadius = radius }
}

@ColorInt
fun View.getCurrentColor(@ColorInt default: Int = Color.TRANSPARENT): Int = (background as? ColorDrawable)?.color
    ?: default


fun View.color(resourceId: Int): Int {
    return ContextCompat.getColor(context, resourceId)
}

var TextView.textColor: Int
    get() = 0
    set(v) = setTextColor(v)

var Button.textColor: Int
    get() = 0
    set(v) = setTextColor(v)

var View.padding: Int
    get() = 0
    inline set(value) = setPadding(value, value, value, value)

var View.backgroundColor: Int
    get() = 0
    set(v) = setBackgroundColor(v)


