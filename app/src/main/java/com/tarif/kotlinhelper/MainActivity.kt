package com.tarif.kotlinhelper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.tarif.kotlinhelper.databinding.ActivityMainBinding
import com.tarif.picasso.load
import com.tarif.picasso.transform.CropTransformation

class MainActivity : AppCompatActivity() {

    companion object {
        private const val URL =
            "https://res.cloudinary.com/twenty20/private_images/t_standard-fit/photosp/876b471e-7869-4370-a91c-3e1a745efdbb/stock-photo-nature-forest-holding-protection-agriculture-green-hand-life-human-876b471e-7869-4370-a91c-3e1a745efdbb.jpg"
    }

    private lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        bind.imageView.load(URL.toUri()) {
            transform(
                CropTransformation(
                    0.5f.toInt(),
                    .2.toInt(),
                    (4f / 3f),
                    CropTransformation.GravityHorizontal.CENTER,
                    CropTransformation.GravityVertical.CENTER
                )
            )
        }

    }
}