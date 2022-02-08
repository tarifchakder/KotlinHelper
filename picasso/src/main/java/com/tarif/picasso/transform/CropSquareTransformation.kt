package com.tarif.picasso.transform

import android.graphics.Bitmap
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


class CropSquareTransformation : Transformation {

    private var mWidth = 0
    private var mHeight = 0

    override fun transform(source: Bitmap): Bitmap {
        val size = source.width.coerceAtMost(source.height)

        mWidth = (source.width - size) / 2
        mHeight = (source.height - size) / 2

        val bitmap = Bitmap.createBitmap(source, mWidth, mHeight, size, size)

        if (bitmap != source) {
            source.recycle()
        }

        return bitmap
    }

    override fun key(): String {
        return "CropSquareTransformation(width=$mWidth, height=$mHeight)"
    }
}