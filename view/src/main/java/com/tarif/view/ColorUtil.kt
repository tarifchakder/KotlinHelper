package com.tarif.view


import android.content.Context
import android.graphics.Color
import android.util.Log
import java.lang.Exception
import java.util.*
import kotlin.math.roundToInt

/**
 * @Author: Md Tarif Chakder
 * @Date: 07-02-2022
 */
object ColorUtil {

    // This default color int
    private const val defaultColorID = Color.BLACK
    private const val defaultColor = "000000"
    private const val TAG = "ColorTransparentUtils"

    fun convert(trans: Int): String {
        val hexString = Integer.toHexString((255 * trans / 100).toFloat().roundToInt())
        return (if (hexString.length < 2) "0" else "") + hexString
    }

    fun transparentColor(colorCode: Int, trans: Int): String {
        return convertIntoColor(colorCode, trans)
    }

    fun convertIntoColor(colorCode: Int, transCode: Int): String {
        // convert color code into hexa string and remove starting 2 digit
        var color = defaultColor
        try {
            color = Integer.toHexString(colorCode).uppercase(Locale.getDefault()).substring(2)
        } catch (ignored: Exception) {
        }
        return if (color.isNotEmpty() && transCode < 100) {
            if (color.trim { it <= ' ' }.length == 6) {
                "#" + convert(transCode) + color
            } else {
                Log.d(TAG, "Color is already with transparency")
                convert(transCode) + color
            }
        } else "#" + Integer.toHexString(defaultColorID).uppercase(Locale.getDefault()).substring(2)
        // if color is empty or any other problem occur then we return deafult color;
    }

    fun transparentAccentColor(color : Int, transparentRange : Int = 10): Int {
        val stringColorTransparent = transparentColor(color, transparentRange)
        return ColorUtil.parseColor(stringColorTransparent)
    }

    fun parseColor(color: String): Int {
        return if (color.isBlank()) 0
        else Color.parseColor(color)
    }

}