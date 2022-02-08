package com.tarif.picasso.transform

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.util.Log
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


class CropTransformation : Transformation {

    enum class GravityHorizontal {
        LEFT,
        CENTER,
        RIGHT
    }

    enum class GravityVertical {
        TOP,
        CENTER,
        BOTTOM
    }

    private var mAspectRatio = 0f
    private var mLeft = 0
    private var mTop = 0
    private var mWidth = 0
    private var mHeight = 0
    private var mWidthRatio = 0f
    private var mHeightRatio = 0f
    private var mGravityHorizontal: GravityHorizontal? = GravityHorizontal.CENTER
    private var mGravityVertical: GravityVertical? = GravityVertical.CENTER

    /**
     * Crops to the given size and offset in pixels.
     * If either width or height is zero then the original image's dimension is used.
     *
     * @param left   offset in pixels from the left edge of the source image
     * @param top    offset in pixels from the top of the source image
     * @param width  in pixels
     * @param height in pixels
     */

    constructor(left: Int, top: Int, width: Int, height: Int) {
        mLeft = left
        mTop = top
        mWidth = width
        mHeight = height
        mGravityHorizontal = null
        mGravityVertical = null
    }


    /**
     * Crops to the given width and height (in pixels) using the given gravity.
     * If either width or height is zero then the original image's dimension is used.
     *
     * @param width             in pixels
     * @param height            in pixels
     * @param gravityHorizontal position of the cropped area within the larger source image
     * @param gravityVertical   position of the cropped area within the larger source image
     */
    /**
     * Crops to the given width and height (in pixels), defaulting gravity to CENTER/CENTER.
     * If either width or height is zero then the original image's dimension is used.
     *
     * @param width  in pixels
     * @param height in pixels
     */


    @JvmOverloads
    constructor(
        width: Int, height: Int, gravityHorizontal: GravityHorizontal? = GravityHorizontal.CENTER,
        gravityVertical: GravityVertical? = GravityVertical.CENTER
    ) {
        mWidth = width
        mHeight = height
        mGravityHorizontal = gravityHorizontal
        mGravityVertical = gravityVertical
    }


    /**
     *
     * Crops to a ratio of the source image's width/height.
     *
     *
     * e.g. (0.5, 0.5, LEFT, TOP) will crop a quarter-sized box from the top left of the original.
     *
     *
     * If widthRatio or heightRatio are zero then 100% of the original image's dimension will be
     * used.
     *
     * @param widthRatio        width of the target image relative to the width of the source image; 1 =
     * 100%
     * @param heightRatio       height of the target image relative to the height of the source image; 1 =
     * 100%
     * @param gravityHorizontal position of the cropped area within the larger source image
     * @param gravityVertical   position of the cropped area within the larger source image
     */
    /**
     * Crops to a ratio of the source image's width/height, defaulting to CENTER/CENTER gravity.
     *
     *
     * e.g. (0.5, 0.5) will crop a quarter-sized box from the middle of the original.
     *
     *
     * If widthRatio or heightRatio are zero then 100% of the original image's dimension will be
     * used.
     *
     * @param widthRatio  width of the target image relative to the width of the source image; 1 =
     * 100%
     * @param heightRatio height of the target image relative to the height of the source image; 1 =
     * 100%
     */

    @JvmOverloads
    constructor(
        widthRatio: Float, heightRatio: Float,
        gravityHorizontal: GravityHorizontal? = GravityHorizontal.CENTER, gravityVertical: GravityVertical? = GravityVertical.CENTER
    ) {
        mWidthRatio = widthRatio
        mHeightRatio = heightRatio
        mGravityHorizontal = gravityHorizontal
        mGravityVertical = gravityVertical
    }


    /**
     * Crops to the desired aspectRatio driven by either width OR height in pixels.
     * If one of width/height is greater than zero the other is calculated
     * If width and height are both zero then the largest area matching the aspectRatio is returned
     * If both width and height are specified then the aspectRatio is ignored
     *
     *
     * If aspectRatio is 0 then the result will be the same as calling the version without
     * aspectRatio.
     *
     * @param width             in pixels, one of width/height should be zero
     * @param height            in pixels, one of width/height should be zero
     * @param aspectRatio       width/height: greater than 1 is landscape, less than 1 is portrait, 1 is
     * square
     * @param gravityHorizontal position of the cropped area within the larger source image
     * @param gravityVertical   position of the cropped area within the larger source image
     */
    constructor(
        width: Int, height: Int, aspectRatio: Float,
        gravityHorizontal: GravityHorizontal?, gravityVertical: GravityVertical?
    ) {
        mWidth = width
        mHeight = height
        mAspectRatio = aspectRatio
        mGravityHorizontal = gravityHorizontal
        mGravityVertical = gravityVertical
    }

    /**
     * Crops to the desired aspectRatio driven by either width OR height as a ratio to the original
     * image's dimension.
     * If one of width/height is greater than zero the other is calculated
     * If width and height are both zero then the largest area matching the aspectRatio is returned
     * If both width and height are specified then the aspectRatio is ignored
     *
     *
     * If aspectRatio is 0 then the result will be the same as calling the version without
     * aspectRatio.
     *
     *
     * e.g. to get an image with a width of 50% of the source image's width and cropped to 16:9 from
     * the center/center:
     * CropTransformation(0.5, (float)0, (float)16/9, CENTER, CENTER);
     *
     * @param widthRatio        width of the target image relative to the width of the source image; 1 =
     * 100%
     * @param heightRatio       height of the target image relative to the height of the source image; 1 =
     * 100%
     * @param aspectRatio       width/height: greater than 1 is landscape, less than 1 is portrait, 1 is
     * square
     * @param gravityHorizontal position of the cropped area within the larger source image
     * @param gravityVertical   position of the cropped area within the larger source image
     */
    constructor(
        widthRatio: Float, heightRatio: Float, aspectRatio: Float,
        gravityHorizontal: GravityHorizontal?, gravityVertical: GravityVertical?
    ) {
        mWidthRatio = widthRatio
        mHeightRatio = heightRatio
        mAspectRatio = aspectRatio
        mGravityHorizontal = gravityHorizontal
        mGravityVertical = gravityVertical
    }

    /**
     * Crops to the largest image that will fit the given aspectRatio.
     * This will effectively chop off either the top/bottom or left/right of the source image.
     *
     * @param aspectRatio       width/height: greater than 1 is landscape, less than 1 is portrait, 1 is
     * square
     * @param gravityHorizontal position of the cropped area within the larger source image
     * @param gravityVertical   position of the cropped area within the larger source image
     */
    constructor(
        aspectRatio: Float, gravityHorizontal: GravityHorizontal?,
        gravityVertical: GravityVertical?
    ) {
        mAspectRatio = aspectRatio
        mGravityHorizontal = gravityHorizontal
        mGravityVertical = gravityVertical
    }

    override fun transform(source: Bitmap): Bitmap {
        if (Log.isLoggable(TAG, Log.VERBOSE)) Log.v(TAG, "transform(): called, " + key())
        if (mWidth == 0 && mWidthRatio != 0f) {
            mWidth = (source.width.toFloat() * mWidthRatio).toInt()
        }
        if (mHeight == 0 && mHeightRatio != 0f) {
            mHeight = (source.height.toFloat() * mHeightRatio).toInt()
        }
        if (mAspectRatio != 0f) {
            if (mWidth == 0 && mHeight == 0) {
                val sourceRatio = source.width.toFloat() / source.height.toFloat()
                if (Log.isLoggable(TAG, Log.VERBOSE)) {
                    Log.v(
                        TAG,
                        "transform(): mAspectRatio: $mAspectRatio, sourceRatio: $sourceRatio"
                    )
                }
                if (sourceRatio > mAspectRatio) {
                    // source is wider than we want, restrict by height
                    mHeight = source.height
                } else {
                    // source is taller than we want, restrict by width
                    mWidth = source.width
                }
            }
            if (Log.isLoggable(TAG, Log.VERBOSE)) {
                Log.v(
                    TAG, "transform(): before setting other of h/w: mAspectRatio: " + mAspectRatio
                            + ", set one of width: " + mWidth + ", height: " + mHeight
                )
            }
            if (mWidth != 0) {
                mHeight = (mWidth.toFloat() / mAspectRatio).toInt()
            } else {
                if (mHeight != 0) {
                    mWidth = (mHeight.toFloat() * mAspectRatio).toInt()
                }
            }
            if (Log.isLoggable(TAG, Log.VERBOSE)) {
                Log.v(
                    TAG,
                    "transform(): mAspectRatio: " + mAspectRatio + ", set width: " + mWidth + ", height: "
                            + mHeight
                )
            }
        }
        if (mWidth == 0) {
            mWidth = source.width
        }
        if (mHeight == 0) {
            mHeight = source.height
        }
        if (mGravityHorizontal != null) {
            mLeft = getLeft(source)
        }
        if (mGravityVertical != null) {
            mTop = getTop(source)
        }
        val sourceRect = Rect(mLeft, mTop, mLeft + mWidth, mTop + mHeight)
        val targetRect = Rect(0, 0, mWidth, mHeight)
        if (Log.isLoggable(TAG, Log.VERBOSE)) {
            Log.v(
                TAG,
                "transform(): created sourceRect with mLeft: " + mLeft + ", mTop: " + mTop + ", right: "
                        + (mLeft + mWidth) + ", bottom: " + (mTop + mHeight)
            )
        }
        if (Log.isLoggable(TAG, Log.VERBOSE)) {
            Log.v(TAG, "transform(): created targetRect with width: $mWidth, height: $mHeight")
        }
        val bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        if (Log.isLoggable(TAG, Log.VERBOSE)) {
            Log.v(
                TAG, "transform(): copying from source with width: " + source.width + ", height: "
                        + source.height
            )
        }
        canvas.drawBitmap(source, sourceRect, targetRect, null)
        source.recycle()
        if (Log.isLoggable(TAG, Log.VERBOSE)) {
            Log.v(
                TAG, "transform(): returning bitmap with width: " + bitmap.width + ", height: "
                        + bitmap.height
            )
        }
        return bitmap
    }

    override fun key(): String {
        return ("CropTransformation(width=" + mWidth + ", height=" + mHeight + ", mWidthRatio="
                + mWidthRatio + ", mHeightRatio=" + mHeightRatio + ", mAspectRatio=" + mAspectRatio
                + ", gravityHorizontal=" + mGravityHorizontal + ", mGravityVertical=" + mGravityVertical
                + ")")
    }

    private fun getTop(source: Bitmap): Int {
        return when (mGravityVertical) {
            GravityVertical.TOP -> 0
            GravityVertical.CENTER -> (source.height - mHeight) / 2
            GravityVertical.BOTTOM -> source.height - mHeight
            else -> 0
        }
    }

    private fun getLeft(source: Bitmap): Int {
        return when (mGravityHorizontal) {
            GravityHorizontal.LEFT -> 0
            GravityHorizontal.CENTER -> (source.width - mWidth) / 2
            GravityHorizontal.RIGHT -> source.width - mWidth
            else -> 0
        }
    }

    companion object {
        private const val TAG = "PicassoTransformation"
    }
}