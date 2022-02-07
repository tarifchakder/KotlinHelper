package com.tarif.view

import android.graphics.Typeface
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.core.view.doOnLayout
import androidx.core.view.forEach
import androidx.core.widget.NestedScrollView
import androidx.viewpager.widget.ViewPager
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


fun View.roundShapeDrawable(r: Float): ShapeDrawable {
    val array = floatArrayOf(r, r, r, r, r, r, r, r)
    return ShapeDrawable(RoundRectShape(array, null, null))
}

fun NestedScrollView.nestedScrollTo(targetView: View) {
    doOnLayout { scrollTo(500, targetView.bottom) }
}

fun AutoCompleteTextView.create(
    @LayoutRes itemLayout: Int, @IdRes textViewId: Int, items: Array<String>,
    onItemSelected: (String, Int) -> Unit = { _, _ -> }
) {
    val adapter = ArrayAdapter(context, itemLayout, textViewId, items)
    setAdapter(adapter)
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            onItemSelected(items[position], position)
        }
    }
}

fun AutoCompleteTextView.create(
    @LayoutRes itemLayout: Int, @IdRes textViewId: Int, items: MutableList<String>,
    onItemSelected: (String, Int) -> Unit = { _, _ -> }
) {
    val adapter = ArrayAdapter(context, itemLayout, textViewId, items)
    setAdapter(adapter)
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            onItemSelected(items[position], position)
        }
    }
}

fun ViewPager.onPageScrollStateChanged(onPageScrollStateChanged: (Int) -> Unit) {
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
            onPageScrollStateChanged(state)
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
        }

    })
}

/** Performs the given action on each item in this menu. */
inline fun Menu.filter(action: (item: MenuItem) -> Boolean): List<MenuItem> {
    val filteredItems = mutableListOf<MenuItem>()
    this.forEach {
        if (action.invoke(it)) filteredItems.add(it)
    }
    return filteredItems
}

fun ArrayList<View>?.setFont(font: Typeface?) {
    if (font == null)
        return

    if (this == null)
        return

    val textViews = ArrayList<TextView>()
    for (view in this) {
        if (view is TextView)
            textViews.add(view)
    }

    for (v in textViews)
        v.typeface = font
}
