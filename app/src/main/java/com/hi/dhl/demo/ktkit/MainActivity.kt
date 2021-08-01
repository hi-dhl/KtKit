package com.hi.dhl.demo.ktkit

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.hi.dhl.binding.viewbind
import com.hi.dhl.demo.ktkit.databinding.ActivityMainBinding
import com.hi.dhl.demo.ktkit.login.LoginActivity
import com.hi.dhl.ktkit.core.*
import com.hi.dhl.ktkit.ui.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewbind()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getViews().forEach {
            it.click(lifecycleScope) {
                onClick(it)
            }
        }

        binding.btnDebounce.clickTrigger(lifecycleScope, 1000) {
            onClick(it)
        }

        initView()
    }

    private fun initView() {
        with(binding) {
            val phontNumberStr = "044 668 18 00"
            tvFormatPhoneNumber.setText(phontNumberStr.formatPhoneNumber("CH"))

            val screen = "width = $screenWidth height = $screenHeight density = $density dp2px = ${dp2px(10)} px2dp = ${px2dp(10)}"
            tvScreen.setText(screen)
//            tvScreen.append("hasNetwork = ${hasNetwork()}")

            imgScreen.setBackgroundColor(color(R.color.purple_500))
            imgScreen.setImageDrawable(drawable(R.drawable.ic_launcher_foreground))
        }
    }

    fun onClick(v: View) {
        with(binding) {
            when (v) {
                btnProfile -> ProfileActivity.startActivity(this@MainActivity)
                btnAddFragment -> LoginActivity.startActivity(this@MainActivity)
                btnToast -> {
                    showShortToast("公众号：ByteCode")
//                    showShortToast(R.string.app_name)
//                    showLongToast("hi 我是 dhl")
//                    showLongToast(R.string.app_name)
                }
                btnSnackBar -> {
//                    btnToast.showShortSnackbar("公众号：ByteCode")
//                    btnToast.showShortSnackbar(R.string.app_name)
//                    btnToast.showLongSnackbar("hi 我是 dhl")
//                    btnToast.showLongSnackbar(R.string.app_name)
                    btnToast.showActionSnackBar("公众号：ByteCode", "click me") {
                        showLongToast("hi 我是 dhl")
                    }
                }
                btnDebounce -> showShortToast("公众号：ByteCode")
            }
        }
    }

    private fun getViews() = with(binding) {
        arrayListOf<View>(btnProfile, btnAddFragment, btnToast, btnSnackBar)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ProfileActivity.KEY_REQUEST_CODE) {
            val result = data?.getStringExtra(ProfileActivity.KEY_RESULT)
            val userName = data?.getStringExtra(ProfileActivity.KEY_USER_NAME)
            binding.tvActResult.setText("$result - $userName")
        }
    }
}
