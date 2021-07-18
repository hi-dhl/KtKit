package com.hi.dhl.ktkit.core

import android.content.Context
import android.widget.Toast

/**
 * <pre>
 *     author: dhl
 *     date  : 2021/6/20
 *     desc  :
 * </pre>
 */

// toast
fun Context.showShotToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showLongToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
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
inline fun Context.dp2px(value: Int): Int = (density * value).toInt()

// px to dp
inline fun Context.px2dp(value: Int): Float = value.toFloat() / density
