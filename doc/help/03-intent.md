### API

以下两种方式根据实际情况使用即可

```
// 方式一： 不带默认值
private val userPassword by intent<String>(KEY_USER_PASSWORD)

// 方式二：带默认值：如果获取失败，返回一个默认值
private val userName by intent<String>(KEY_USER_NAME) {
    "公众号：ByteCode"
}
```

### 案例

```
class ProfileActivity : Activity() {
    private val userPassword by intent<String>(KEY_USER_PASSWORD)

    private val userName by intent<String>(KEY_USER_NAME) {
        "公众号：ByteCode"
    }
}
```


