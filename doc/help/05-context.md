**short toast**

```
showShortToast("公众号：ByteCode")
showShortToast(R.string.app_name)
```

**long toast**

```
showLongToast("hi 我是 dhl")
showLongToast(R.string.app_name)
```

**设置状态栏的颜色**

```
setSatatusBarColor(android.R.color.darker_gray)
```

**获取屏幕宽度、高度、密度**

```
"width = ${screenWidth} height = ${screenHeight} density = ${density}"
```

**dp 转 px**

```
"dp2px = ${dp2px(10)}"
```

**px 转 dp**

```
"px2dp = ${px2dp(10)}"
```

**检查是否有网**

```
hasNetwork()
```

**通过 drawableResId 获取 drawable**

```
drawable(R.drawable.id)
```

**通过 colorResId 获取 color**

```
color(R.color.id)
```
