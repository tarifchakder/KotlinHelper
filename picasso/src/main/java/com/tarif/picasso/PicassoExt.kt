package com.tarif.picasso

import android.content.res.Resources
import android.net.Uri
import androidx.appcompat.widget.AppCompatImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import java.io.File

/**
 * @Author: Md Tarif Chakder
 * @Date: 08-02-2022
 */


@JvmSynthetic
fun AppCompatImageView.load(uri: Uri, builder: RequestCreator.() -> Unit = {}) {
    Picasso.get()
        .load(uri)
        .apply { builder() }
        .into(this)
}

@JvmSynthetic
fun AppCompatImageView.load(file: File, builder: RequestCreator.() -> Unit = {}) {
    Picasso.get()
        .load(file)
        .apply { builder() }
        .into(this)
}

@JvmSynthetic
fun AppCompatImageView.load(resId: Int, builder: RequestCreator.() -> Unit = {}) {
    Picasso.get()
        .load(resId)
        .apply { builder() }
        .into(this)
}


@JvmSynthetic
fun AppCompatImageView.load(path: String, builder: RequestCreator.() -> Unit = {}) {
    Picasso.get()
        .load(path)
        .apply { builder() }
        .into(this)
}

val Float.dp: Int get() = ((this * (Resources.getSystem().displayMetrics.density)) + 0.5f).toInt()
