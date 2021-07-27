### API

以下两种方式根据实际情况使用即可

```
// 方式一
context.startActivity<ProfileActivity> {
    arrayOf(
            KEY_USER_NAME to "ByteCode",
            KEY_USER_PASSWORD to "1024",
            KEY_PEOPLE_PARCELIZE to PeopleModel("hi-dhl")
    )
}

// 方式二
context.startActivity<ProfileActivity>(
        KEY_USER_NAME to "ByteCode",
        KEY_USER_PASSWORD to "1024"
)
```

### 案例

```
class ProfileActivity : Activity() {
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
    }
    
    companion object {
        const val KEY_USER_NAME = "userName"
        const val KEY_USER_PASSWORD = "userPassWord"

//              方式一
//            context.startActivity<ProfileActivity> {
//                arrayOf(
//                        KEY_USER_NAME to "ByteCode",
//                        KEY_USER_PASSWORD to "1024",
//                        KEY_PEOPLE_PARCELIZE to PeopleModel("hi-dhl")
//                )
//            }

        // 方式二
        fun startActivity(context: Context) {
        
            context.startActivity<ProfileActivity>(
                    KEY_USER_NAME to "ByteCode",
                    KEY_USER_PASSWORD to "1024"
            )

        }
    }
}

```

