@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package com.hi.dhl.ktkit.core

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import java.lang.ref.WeakReference

/**
 * <pre>
 *     author: dhl
 *     date  : 2021/6/20
 *     desc  :
 * </pre>
 */

// toast
@kotlin.internal.InlineOnly
inline fun Context.showShortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

@kotlin.internal.InlineOnly
inline fun Context.showShortToast(@StringRes stringResId: Int) {
    Toast.makeText(this, getString(stringResId), Toast.LENGTH_SHORT).show()
}

@kotlin.internal.InlineOnly
inline fun Context.showLongToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

@kotlin.internal.InlineOnly
inline fun Context.showLongToast(@StringRes stringResId: Int) {
    Toast.makeText(this, getString(stringResId), Toast.LENGTH_LONG).show()
}

// 屏幕宽度(px)
inline val Context.screenWidth: Int
    get() = resources.displayMetrics.widthPixels

// 屏幕高度(px)
inline val Context.screenHeight: Int
    get() = resources.displayMetrics.heightPixels

// 屏幕的密度
inline val Context.density: Float
    get() = resources.displayMetrics.density

// dp to px
@kotlin.internal.InlineOnly
inline fun Context.dp2px(value: Int): Int = (density * value).toInt()

// px to dp
@kotlin.internal.InlineOnly
inline fun Context.px2dp(value: Int): Float = value.toFloat() / density

/**
 * 设置状态栏的颜色
 *
 * usage：
 * setSatatusBarColor(android.R.color.darker_gray)
 */
fun Context.setSatatusBarColor(@ColorRes colorResId: Int) {

    if (this is Activity) {
        setSatatusBarColor(WeakReference<Activity>(this), colorResId)
    }
}

private fun Context.setSatatusBarColor(context: WeakReference<Activity>, @ColorRes colorResId: Int) {
    context.get()?.run {
        if (Build.VERSION.SDK_INT >= 21) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = resources.getColor(colorResId)
        }
    }
}

// 获取 Drawable
@kotlin.internal.InlineOnly
inline fun Context.drawable(@DrawableRes id: Int) = ContextCompat.getDrawable(this, id)

// 获取 color
@kotlin.internal.InlineOnly
inline fun Context.color(@ColorRes id: Int) = ContextCompat.getColor(this, id)
