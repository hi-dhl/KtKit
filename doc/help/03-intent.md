### API

以下两种方式根据实际情况使用即可

```
// 方式一： 根据 key 获取参数
private val userPassword by intent<String>(KEY_USER_PASSWORD)

// 方式二：如果获取失败，返回一个默认值
private val userName by intent<String>(KEY_USER_NAME) {
    "公众号：ByteCode"
}

```



