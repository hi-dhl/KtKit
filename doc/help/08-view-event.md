事件通过 lifecycleScope 与 Activity/Fragment 的 Lifecycle 绑定在一起，有效避免内存泄漏，所要使用 lifecycleScope 需要添加一下引用。

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

