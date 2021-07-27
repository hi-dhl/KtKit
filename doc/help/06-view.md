
**显示 View**

```
view.visible()
```

**隐藏 View**

```
View.gone()
view.invisible()
```

**show short snackbar**

```
view.showShortSnackbar("公众号：ByteCode")
view.showShortSnackbar(R.string.app_name)
```

**long short snackbar**

```
view.showLongSnackbar("hi 我是 dhl")
view.showLongSnackbar(R.string.app_name)
```

**snackbar with action**

```
view.showActionSnackBar("公众号：ByteCode","login"){
    Log.i("MainActivity","hi 我是 dhl")
}
```



