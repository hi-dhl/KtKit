package com.hi.dhl.demo.ktkit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hi.dhl.binding.viewbind
import com.hi.dhl.demo.ktkit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewbind()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        with(binding) {
            btnProfile.setOnClickListener {
                ProfileActivity.startActivity(this@MainActivity)
            }
        }
    }
}