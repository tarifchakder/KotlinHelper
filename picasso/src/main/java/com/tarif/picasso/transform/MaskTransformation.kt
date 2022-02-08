package com.tarif.picasso.transform

import android.content.Context
import android.graphics.*
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.squareup.picasso.Transformation


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


class MaskTransformation(context: Context, maskId: Int) : Transformation {

    private val mContext: Context = context.applicationContext
    private val mMaskId: Int = maskId

    companion object {
        private val mMaskingPaint = Paint()

        init {
            mMaskingPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun transform(source: Bitmap): Bitmap {
        val width = source.width
        val height = source.height
        val result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val mask = ContextCompat.getDrawable(mContext, mMaskId)
        val canvas = Canvas(result)
        mask!!.setBounds(0, 0, width, height)
        mask.draw(canvas)
        canvas.drawBitmap(source, 0f, 0f, mMaskingPaint)
        source.recycle()
        return result
    }

    override fun key(): String {
        return ("MaskTransformation(maskId=" + mContext.resources.getResourceEntryName(mMaskId) + ")")
    }

}