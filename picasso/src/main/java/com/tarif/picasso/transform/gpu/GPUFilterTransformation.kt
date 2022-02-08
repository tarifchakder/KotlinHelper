package com.tarif.picasso.transform.gpu

import android.content.Context
import android.graphics.Bitmap
import com.squareup.picasso.Transformation
import jp.co.cyberagent.android.gpuimage.GPUImage
import jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter


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


open class GPUFilterTransformation(
    context: Context?,
    filter: GPUImageFilter
) : Transformation {

    private val mContext: Context = context!!.applicationContext
    private val mFilter: GPUImageFilter = filter

    override fun transform(source: Bitmap): Bitmap {
        val gpuImage = GPUImage(mContext)
        gpuImage.setImage(source)
        gpuImage.setFilter(mFilter)
        val bitmap = gpuImage.bitmapWithFilterApplied
        source.recycle()
        return bitmap
    }

    override fun key(): String {
        return javaClass.simpleName
    }

    fun <T> getFilter(): T {
        return mFilter as T
    }

}