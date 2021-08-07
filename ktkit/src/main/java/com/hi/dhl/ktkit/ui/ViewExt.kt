@file:OptIn(ExperimentalContracts::class, ExperimentalCoroutinesApi::class, FlowPreview::class)
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package com.hi.dhl.ktkit.ui

import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlin.contracts.ExperimentalContracts

/**
 * <pre>
 *     author: dhl
 *     date  : 2021/3/29
 *     desc  :
 * </pre>
 */

@kotlin.internal.InlineOnly
inline fun View.visible() {
    visibility = View.VISIBLE
}

@kotlin.internal.InlineOnly
inline fun View.gone() {
    visibility = View.GONE
}

@kotlin.internal.InlineOnly
inline fun View.invisible() {
    visibility = View.INVISIBLE
}

@kotlin.internal.InlineOnly
inline fun View.showShortSnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

@kotlin.internal.InlineOnly
inline fun View.showShortSnackbar(@StringRes stringResId: Int) {
    Snackbar.make(this, stringResId, Snackbar.LENGTH_SHORT).show()
}

@kotlin.internal.InlineOnly
inline fun View.showLongSnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}

@kotlin.internal.InlineOnly
inline fun View.showLongSnackbar(@StringRes stringResId: Int) {
    Snackbar.make(this, stringResId, Snackbar.LENGTH_LONG).show()
}

/**
 * Example：
 *
 * ```
 * btn.showActionSnackBar("公众号：ByteCode", "click me") {
 *      showLongToast("hi 我是 dhl")
 * }
 * ```
 */
@kotlin.internal.InlineOnly
inline fun View.showActionSnackBar(
    message: String,
    actionName: String,
    noinline block: () -> Unit
) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        .setAction(actionName) {
            block()
        }.show()
}

/**
 * 快捷设置View的自定义纯色带圆角背景
 *
 * @receiver View
 * @param color Int 颜色值
 * @param cornerRadius Float 圆角 单位px
 */
@kotlin.internal.InlineOnly
inline fun View.setRoundRectBg(
    @ColorInt color: Int,
    cornerRadius: Float = 15F
) {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
        background = GradientDrawable().apply {
            setColor(color)
            setCornerRadius(cornerRadius)
        }
    }
}
