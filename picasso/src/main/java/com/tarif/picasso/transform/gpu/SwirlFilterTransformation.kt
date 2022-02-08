package com.tarif.picasso.transform.gpu

import android.content.Context
import android.graphics.PointF
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSwirlFilter


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
 * Creates a swirl distortion on the image.
 */

class SwirlFilterTransformation @JvmOverloads constructor(
    context: Context?,
    private val mRadius: Float = .5f,
    private val mAngle: Float = 1.0f,
    private val mCenter: PointF = PointF(.5f, .5f)
) : GPUFilterTransformation(
    context!!, GPUImageSwirlFilter()
) {

    override fun key(): String {
        return "SwirlFilterTransformation(radius=" + mRadius +
                ",angle=" + mAngle + ",center=" + mCenter.toString() + ")"
    }

    /**
     * @param radius from 0.0 to 1.0, default 0.5
     * @param angle  minimum 0.0, default 1.0
     * @param center default (0.5, 0.5)
     */
    init {
        val filter = getFilter<GPUImageSwirlFilter>()
        filter.setRadius(mRadius)
        filter.setAngle(mAngle)
        filter.setCenter(mCenter)
    }
}