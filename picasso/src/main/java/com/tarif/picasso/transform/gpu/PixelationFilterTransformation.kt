package com.tarif.picasso.transform.gpu

import android.content.Context
import jp.co.cyberagent.android.gpuimage.filter.GPUImagePixelationFilter


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


/**
 * Applies a Pixelation effect to the image.
 *
 *
 * The pixel with a default of 10.0.
 */

class PixelationFilterTransformation @JvmOverloads constructor(
    context: Context?,
    private val mPixel: Float = 10f
) : GPUFilterTransformation(context!!, GPUImagePixelationFilter()) {

    override fun key(): String {
        return "PixelationFilterTransformation(pixel=$mPixel)"
    }

    init {
        val filter = getFilter<GPUImagePixelationFilter>()
        filter.setPixel(mPixel)
    }
}