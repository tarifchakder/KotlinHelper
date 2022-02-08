package com.tarif.kotlinhelper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tarif.kotlinhelper.databinding.ActivityMainBinding
import com.tarif.view.showIf

class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        bind.root.showIf(true, true)
    }
}