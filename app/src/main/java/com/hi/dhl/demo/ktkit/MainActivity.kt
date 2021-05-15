package com.hi.dhl.demo.ktkit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hi.dhl.binding.viewbind
import com.hi.dhl.demo.ktkit.databinding.ActivityMainBinding
import com.hi.dhl.ktkit.ui.gone
import com.hi.dhl.ktkit.ui.invisible
import com.hi.dhl.ktkit.ui.visible

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewbind()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        with(binding) {
            btnClick.visible()
            btnClick.gone()
            btnClick.invisible()
        }
    }
}