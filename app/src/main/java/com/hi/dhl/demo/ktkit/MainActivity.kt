package com.hi.dhl.demo.ktkit

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hi.dhl.binding.viewbind
import com.hi.dhl.demo.ktkit.databinding.ActivityMainBinding
import com.hi.dhl.demo.ktkit.login.LoginActivity
import com.hi.dhl.ktkit.ui.formatPhoneNumber
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val binding: ActivityMainBinding by viewbind()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getViews().forEach {
            it.setOnClickListener(this)
        }

        initView()
    }


    private fun initView() {
        with(binding){
            val phontNumberStr = "044 668 18 00"
            tvFormatPhoneNumber.setText(phontNumberStr.formatPhoneNumber("CH"))
        }
    }

    override fun onClick(v: View) {
        with(binding) {
            when (v) {
                btnProfile -> ProfileActivity.startActivity(this@MainActivity)
                btnAddFragment -> LoginActivity.startActivity(this@MainActivity)
            }
        }
    }


    private fun getViews() = with(binding) {
        arrayListOf<View>(btnProfile, btnAddFragment)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ProfileActivity.KEY_REQUEST_CODE) {
            val result = data?.getStringExtra(ProfileActivity.KEY_RESULT)
            val userName = data?.getStringExtra(ProfileActivity.KEY_USER_NAME)
            binding.textActResult.setText("${result} - ${userName}")
        }
    }

}
