将 Flow 通过 lifecycleScope 与 Activit y/ Fragment 的生命周期绑定在一起，在 Activity / Fragment 生命周期结束时，会结束 flow , flow 结束时会将 Listener 置为 空，有效的避免内存泄漏，所要使用 lifecycleScope 需要添加一下引用。

```
implementation "androidx.lifecycle:lifecycle-runtime-ktx:${lifecycleVersion}"
```

**单击事件**


```
view.click(lifecycleScope) {
    showShortToast("公众号：ByteCode")
}
```


**延迟第一次点击事件**

```
// 默认延迟时间是 500ms
view.clickDelayed(lifecycleScope){
    showShortToast("公众号：ByteCode")
}

// or
view.clickDelayed(lifecycleScope, 1000){
    showShortToast("公众号：ByteCode")
}
```


**防止多次点击**

```
// 默认间隔时间是 500ms
view.clickTrigger(lifecycleScope){
    showShortToast("公众号：ByteCode")
}

// or
view.clickTrigger(lifecycleScope, 1000){
    showShortToast("公众号：ByteCode")
}
```



**EditText**

监听 `TextWatcher#onTextChanged` 的回调函数

```
editText.textChange(lifecycleScope) {
    Log.e(TAG, "textChange = $it")
}

// 默认间隔时间是 500ms
editText.textChange(
        lifecycle = lifecycleScope,
        timeoutMillis = 500
) {
    Log.e(TAG, "textChange = $it")
}
```

监听 ` TextWatcher#beforeTextChanged` 的回调函数

```
editText.textChangeWithbefore(lifecycleScope) {
    Log.e(TAG, "textChangeWithbefore = $it")
}

// 默认间隔时间是 500ms
editText.textChangeWithbefore(
        lifecycle = lifecycleScope,
        timeoutMillis = 500
) {
    Log.e(TAG, "textChangeWithbefore = $it")
}
```

监听  `TextWatcher#afterTextChanged` 的回调函数

```
editText.textChangeWithAfter(lifecycleScope) {
    Log.e(TAG, "textChangeWithbefore = $it")
}

// 默认间隔时间是 500ms
editText.textChangeWithAfter(
        lifecycle = lifecycleScope,
        timeoutMillis = 500
) {
    Log.e(TAG, "textChangeWithbefore = $it")
}
```

