package com.tarif.picasso.transform

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.renderscript.RSRuntimeException
import com.squareup.picasso.Transformation
import com.tarif.picasso.transform.internal.FastBlur
import com.tarif.picasso.transform.internal.RSBlur


/**
 * Copyright (C) 2020 Wasabeef
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


class BlurTransformation @JvmOverloads constructor(
    context: Context,
    radius: Int = MAX_RADIUS,
    sampling: Int = DEFAULT_DOWN_SAMPLING
) : Transformation {

    private val mContext: Context = context.applicationContext
    private val mRadius: Int = radius
    private val mSampling: Int = sampling

    override fun transform(source: Bitmap): Bitmap {
        val scaledWidth = source.width / mSampling
        val scaledHeight = source.height / mSampling
        var bitmap = Bitmap.createBitmap(scaledWidth, scaledHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.scale(1 / mSampling.toFloat(), 1 / mSampling.toFloat())
        val paint = Paint()
        paint.flags = Paint.FILTER_BITMAP_FLAG
        canvas.drawBitmap(source, 0f, 0f, paint)

        bitmap = try {
            RSBlur.blur(mContext, bitmap, mRadius)
        } catch (e: RSRuntimeException) {
            FastBlur.blur(bitmap, mRadius, true)
        }
        source.recycle()
        return bitmap
    }

    override fun key(): String {
        return "BlurTransformation(radius=$mRadius, sampling=$mSampling)"
    }

    companion object {
        private const val MAX_RADIUS = 25
        private const val DEFAULT_DOWN_SAMPLING = 1
    }

}