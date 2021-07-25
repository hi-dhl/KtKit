package com.hi.dhl.demo.ktkit

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import com.hi.dhl.binding.viewbind
import com.hi.dhl.demo.ktkit.databinding.ActivityProfileBinding
import com.hi.dhl.demo.ktkit.model.PeopleModel
import com.hi.dhl.ktkit.ui.intent
import com.hi.dhl.ktkit.ui.setActivityResult
import com.hi.dhl.ktkit.ui.startActivityForResult

/**
 * <pre>
 *     author: dhl
 *     date  : 2021/6/20
 *     desc  :
 * </pre>
 */
class ProfileActivity : Activity(), View.OnClickListener {
    private val binding by viewbind<ActivityProfileBinding>()

    private val userName by intent<String>(KEY_USER_NAME) {
        "default"
    }
    private val userPassword by intent<String>(KEY_USER_PASSWORD)
    private val peopleModel by intent<PeopleModel>(KEY_PEOPLE_PARCELIZE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        with(binding) {
            val content = "userNnam = $userName   " + "userPassword = $userPassword + people = $peopleModel"
            textResult.setText(content)
        }

        getViews().forEach {
            it.setOnClickListener(this)
        }
    }

    override fun onClick(v: View) {
        with(binding) {
            when (v) {
                btnFinish -> {
//                    setActivityResult(Activity.RESULT_OK) {
//                       arrayOf(
//                                KEY_RESULT to "success"
//                        )
//                    }

                    setActivityResult(
                        Activity.RESULT_OK,
                        KEY_RESULT to "success",
                        KEY_USER_NAME to "ByteCode"
                    )
                    finish()
                }
            }
        }
    }

    private fun getViews() = with(binding) {
        arrayListOf<View>(btnFinish)
    }

    companion object {
        const val KEY_USER_NAME = "userName"
        const val KEY_USER_PASSWORD = "userPassWord"
        const val KEY_PEOPLE_PARCELIZE = "peopleParcelize"
        const val KEY_REQUEST_CODE = 0x01

        const val KEY_RESULT = "userResult"

        fun startActivity(context: Context) {
//            context.startActivity<ProfileActivity> {
//                arrayOf(
//                        KEY_USER_NAME to "ByteCode",
//                        KEY_USER_PASSWORD to "1024",
//                        KEY_PEOPLE_PARCELIZE to PeopleModel("hi-dhl")
//                )
//            }

//            context.startActivity<ProfileActivity>(
//                    KEY_USER_NAME to "ByteCode",
//                    KEY_USER_PASSWORD to "1024"
//            )

//            context.startActivityForResult<ProfileActivity>(KEY_REQUEST_CODE,
//                    KEY_USER_NAME to "ByteCode",
//                    KEY_USER_PASSWORD to "1024",
//                    KEY_PEOPLE_PARCELIZE to PeopleModel("hi-dhl")
//            )

            context.startActivityForResult<ProfileActivity>(KEY_REQUEST_CODE) {
                arrayOf(
                    KEY_USER_NAME to "ByteCode",
                    KEY_USER_PASSWORD to "1024",
                    KEY_PEOPLE_PARCELIZE to PeopleModel("hi-dhl")
                )
            }
        }
    }
}
