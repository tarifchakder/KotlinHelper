package com.tarif.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.findViewTreeLifecycleOwner
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

/**
 * @Author: Tarif Chakder
 * @Date: 31-01-2022
 */

fun View.enable() {
    this.isEnabled = true
}

fun View.toggleEnabled() {
    this.isEnabled = !this.isEnabled
}

fun View.disable() {
    this.isEnabled = false
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.inVisible() {
    this.visibility = View.INVISIBLE
}

fun List<View>.gone() {
    this.forEach { it.gone() }
}

fun List<View>.invisible() {
    this.forEach { it.inVisible() }
}

fun List<View>.visible() {
    this.forEach { it.visible() }
}

fun ViewGroup.isEmpty() = childCount == 0

fun View.setClickable() {
    isClickable = true
    isFocusable = true
}

fun View.rootView(): View {
    var root = this
    while (root.parent is View) {
        root = root.parent as View
    }
    return root
}

fun View.resetFocus() {
    clearFocus()
    isFocusableInTouchMode = false
    isFocusable = false
    isFocusableInTouchMode = true
    isFocusable = true
}

object AppScope : CoroutineScope {
    override val coroutineContext = Dispatchers.Main.immediate + SupervisorJob()
}

@ExperimentalCoroutinesApi
fun View.clicks() = callbackFlow {
    setOnClickListener {
        this.trySend(Unit).isSuccess
    }
    awaitClose { setOnClickListener(null)}
}

@OptIn(ObsoleteCoroutinesApi::class)
fun View.setOnClick(action: suspend () -> Unit) {
    // launch one actor as a parent of the context job
    val scope = (context as? CoroutineScope)?: AppScope
    val eventActor = scope.actor<Unit>(capacity = Channel.CONFLATED) {
        for (event in channel) action()
    }
    // install a listener to activate this actor
    setOnClickListener { eventActor.trySend(Unit).isSuccess }
}

@OptIn(ObsoleteCoroutinesApi::class)
fun View.setOnLongClick(action: suspend () -> Unit){
    val scope = (context as? CoroutineScope) ?: AppScope
    val eventActor = scope.actor<Unit>(capacity = Channel.CONFLATED) {
        for (event in channel) action()
    }
    setOnLongClickListener{ eventActor.trySend(Unit).isSuccess }
}

@SuppressLint("ClickableViewAccessibility")
fun View.onTouchListener(
    onTouchActive: () -> Unit,
    onTouchCanceled: () -> Unit,
    onTouchFinished: () -> Unit
) {
    setOnTouchListener { _, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> onTouchActive()
            MotionEvent.ACTION_CANCEL -> onTouchCanceled()
            MotionEvent.ACTION_UP -> onTouchFinished()
        }
        return@setOnTouchListener true
    }
}


fun View.delayOnLifecycle(
    durationInMills: Long,
    dispatcher: CoroutineDispatcher = Dispatchers.Main,
    block: () -> Unit
): Job? = findViewTreeLifecycleOwner()?.let {
    it.lifecycle.coroutineScope.launch(dispatcher) {
        delay(durationInMills)
        block()
    }
}

/**
 * will show the view If Condition is true else make if INVISIBLE or GONE Based on the [makeInvisible] flag
 */
fun View.showIf(boolean: Boolean, makeInvisible: Boolean = false) {
    visibility = if (boolean) View.VISIBLE else if (makeInvisible) View.INVISIBLE else View.GONE
}

/**
 * will enable the view If Condition is true else enables It
 */
fun View.enableIf(boolean: Boolean) = run { isEnabled = boolean }

/**
 * will disable the view If Condition is true else enables It
 */
fun View.disableIf(boolean: Boolean) = run { isEnabled = boolean.not() }

fun ViewGroup.isNotEmpty() = !isEmpty()

fun MenuItem.disable() {
    this.isEnabled = false
}

fun MenuItem.enable() {
    this.isEnabled = true
}

fun MenuItem.toggleEnabled() {
    this.isEnabled = !this.isEnabled
}

fun MenuItem.check() {
    this.isChecked = true
}

fun MenuItem.unCheck() {
    this.isChecked = false
}

fun MenuItem.toggleChecked() {
    this.isChecked = !this.isChecked
}

fun View.dismissKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

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
fun View.setCustomRippleEffect(r : Float = 0f,color : Int, transparentRange : Int = 10){
    try {
        val shapeDrawable = roundShapeDrawable(r)
        val rippleDrawable = RippleDrawable(ColorStateList.valueOf(transparentAccentColor(color,transparentRange)),this.background,shapeDrawable)
        this.background = rippleDrawable
    }catch (e : Exception){

    }
}

fun View.roundShapeDrawable(r: Float): ShapeDrawable {
    val array = floatArrayOf(r, r, r, r, r, r, r, r)
    return ShapeDrawable(RoundRectShape(array, null, null))
}

fun transparentAccentColor(color: Int, transparentRange : Int = 10): Int {
    val stringColorTransparent = ColorUtil.transparentColor(color, transparentRange)
    return ColorUtil.parseColor(stringColorTransparent)
}


/**
 * For rounded bottomsheet
 * you can use rootview of the bottomsheet layout with color and corner
 * @param color color of background
 * @param r radius
 * */
fun View.setBottomSheetBackgroundColor(color : Int, r : Float = 15.0f) {
    val array = floatArrayOf(r,r,r,r,0.0f,0.0f,0.0f,0.0f)
    val shapeDrawable = ShapeDrawable(RoundRectShape(array, null, null))
    shapeDrawable.paint.color = color
    background = shapeDrawable
}

