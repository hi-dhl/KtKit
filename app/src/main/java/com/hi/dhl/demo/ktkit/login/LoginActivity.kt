package com.hi.dhl.demo.ktkit.login

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hi.dhl.binding.viewbind
import com.hi.dhl.demo.ktkit.R
import com.hi.dhl.demo.ktkit.databinding.ActivityLoginBinding
import com.hi.dhl.ktkit.ui.startActivity

/**
 * <pre>
 *     author: dhl
 *     date  : 2021/7/18
 *     desc  :
 * </pre>
 */
class LoginActivity : AppCompatActivity() {
    private val binding by viewbind<ActivityLoginBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding){
            addFragment()
        }
    }

    private fun addFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.loginContainer, LoginFragment.newInstance2())
                .commit()
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity<LoginActivity>()
        }
    }
}