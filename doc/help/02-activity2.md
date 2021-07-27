### API

**传递参数**

以下两种方式根据实际情况使用即可

```
// 方式一
context.startActivityForResult<ProfileActivity>(KEY_REQUEST_CODE,
        KEY_USER_NAME to "ByteCode",
        KEY_USER_PASSWORD to "1024",
        KEY_PEOPLE_PARCELIZE to PeopleModel("hi-dhl")
)

// 方式二
context.startActivityForResult<ProfileActivity>(KEY_REQUEST_CODE) {
    arrayOf(
            KEY_USER_NAME to "ByteCode",
            KEY_USER_PASSWORD to "1024",
            KEY_PEOPLE_PARCELIZE to PeopleModel("hi-dhl")
    )
}
```

**回传参数**

```
// 方式一
setActivityResult(Activity.RESULT_OK) {
   arrayOf(
            KEY_RESULT to "success"
    )
}
                    
// 方式二
setActivityResult(
        Activity.RESULT_OK,
        KEY_RESULT to "success",
        KEY_USER_NAME to "ByteCode"
)
```

### 案例

在 MainActivity 中接受回传参数

```
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val binding: ActivityMainBinding by viewbind()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getViews().forEach {
            it.setOnClickListener(this)
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

    // 接受回传参数
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ProfileActivity.KEY_REQUEST_CODE) {
            val result = data?.getStringExtra(ProfileActivity.KEY_RESULT)
            val userName = data?.getStringExtra(ProfileActivity.KEY_USER_NAME)
            binding.textActResult.setText("$result - $userName")
        }
    }
}
```


**传递参数 并 解析参数**


```
class ProfileActivity : Activity(), View.OnClickListener {
    private val binding by viewbind<ActivityProfileBinding>()

    private val userName by intent<String>(KEY_USER_NAME) {
        "default"
    }
    private val userPassword by intent<String>(KEY_USER_PASSWORD)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        with(binding) {
            val content = "userNnam = $userName userPassword = $userPassword"
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
                // 方式一
//                    setActivityResult(Activity.RESULT_OK) {
//                       arrayOf(
//                                KEY_RESULT to "success"
//                        )
//                    }

                    // 方式二
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
        const val KEY_REQUEST_CODE = 0x01

        const val KEY_RESULT = "userResult"
//            context.startActivityForResult<ProfileActivity>(KEY_REQUEST_CODE,
//                    KEY_USER_NAME to "ByteCode",
//                    KEY_USER_PASSWORD to "1024"
//            )

            context.startActivityForResult<ProfileActivity>(KEY_REQUEST_CODE) {
                arrayOf(
                    KEY_USER_NAME to "ByteCode",
                    KEY_USER_PASSWORD to "1024"
                )
            }
        }
    }
}
```

