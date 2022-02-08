package com.tarif.picasso.transform.gpu

import android.content.Context
import jp.co.cyberagent.android.gpuimage.filter.GPUImageToonFilter

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
 * The threshold at which to apply the edges, default of 0.2.
 * The levels of quantization for the posterization of colors within the scene,
 * with a default of 10.0.
 */
class ToonFilterTransformation @JvmOverloads constructor(
    context: Context?,
    private val mThreshold: Float = .2f,
    private val mQuantizationLevels: Float = 10.0f
) : GPUFilterTransformation(context!!, GPUImageToonFilter()) {

    override fun key(): String {
        return "ToonFilterTransformation(threshold=" + mThreshold +
                ",quantizationLevels=" + mQuantizationLevels + ")"
    }

    init {
        val filter = getFilter<GPUImageToonFilter>()
        filter.setThreshold(mThreshold)
        filter.setQuantizationLevels(mQuantizationLevels)
    }
}