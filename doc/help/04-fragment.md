### API

以下两种方式根据实际情况使用即可

```
// 方式一
fun newInstance1(): Fragment {
    return LoginFragment().makeBundle(
            ProfileActivity.KEY_USER_NAME to "ByteCode",
            ProfileActivity.KEY_USER_PASSWORD to "1024",
            ProfileActivity.KEY_PEOPLE_PARCELIZE to PeopleModel("hi-dhl")
    )
}

// 方式二
fun newInstance2(): Fragment {
    return LoginFragment().makeBundle {
        arrayOf(
                KEY_USER_NAME to "ByteCode",
                KEY_USER_PASSWORD to "1024",
                KEY_PEOPLE_PARCELIZE to PeopleModel("hi-dhl")
        )
    }
}
```

### 案例


```
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by viewbind<FragmentLoginBinding>()

    private val userName by intent<String>(KEY_USER_NAME) {
        "default"
    }
    private val userPassword by intent<String>(KEY_USER_PASSWORD)
    private val peopleModel by intent<PeopleModel>(KEY_PEOPLE_PARCELIZE)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val content = "userNnam = $userName userPassword = $userPassword   people = $peopleModel"
            textResult.setText(content)
        }
    }

    companion object {
        const val KEY_USER_NAME = "userName"
        const val KEY_USER_PASSWORD = "userPassWord"
        const val KEY_PEOPLE_PARCELIZE = "peopleParcelize"

        fun newInstance1(): Fragment {
            return LoginFragment().makeBundle(
                ProfileActivity.KEY_USER_NAME to "ByteCode",
                ProfileActivity.KEY_USER_PASSWORD to "1024",
                ProfileActivity.KEY_PEOPLE_PARCELIZE to PeopleModel("hi-dhl")
            )
        }

        fun newInstance2(): Fragment {
            return LoginFragment().makeBundle {
                arrayOf(
                    KEY_USER_NAME to "ByteCode",
                    KEY_USER_PASSWORD to "1024",
                    KEY_PEOPLE_PARCELIZE to PeopleModel("hi-dhl")
                )
            }
        }
    }
}

```

