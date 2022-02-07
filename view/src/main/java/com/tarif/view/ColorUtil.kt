package com.tarif.view

import android.R
import android.graphics.Color
import android.util.Log
import java.lang.Exception
import java.util.*

/**
 * @Author: Md Tarif Chakder
 * @Date: 07-02-2022
 */
object ColorUtil {

    // This default color int
    const val defaultColorID = R.color.black
    const val defaultColor = "000000"
    const val TAG = "ColorTransparentUtils"

    fun convert(trans: Int): String {
        val hexString = Integer.toHexString(Math.round((255 * trans / 100).toFloat()))
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
        return if (!color.isEmpty() && transCode < 100) {
            if (color.trim { it <= ' ' }.length == 6) {
                "#" + convert(transCode) + color
            } else {
                Log.d(TAG, "Color is already with transparency")
                convert(transCode) + color
            }
        } else "#" + Integer.toHexString(defaultColorID).uppercase(Locale.getDefault()).substring(2)
        // if color is empty or any other problem occur then we return deafult color;
    }

    fun parseColor(color: String): Int {
        return if (color.isBlank()) 0
        else Color.parseColor(color)
    }

}