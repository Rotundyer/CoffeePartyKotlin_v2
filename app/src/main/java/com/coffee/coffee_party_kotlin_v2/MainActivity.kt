package com.coffee.coffee_party_kotlin_v2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}