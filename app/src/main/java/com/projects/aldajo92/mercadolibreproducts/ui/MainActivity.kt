package com.projects.aldajo92.mercadolibreproducts.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.projects.aldajo92.mercadolibreproducts.R
import com.projects.aldajo92.mercadolibreproducts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
    }
}