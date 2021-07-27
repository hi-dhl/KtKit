@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package com.hi.dhl.ktkit.ui

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

/**
 * <pre>
 *     author: dhl
 *     date  : 2021/3/29
 *     desc  :
 * </pre>
 */

@kotlin.internal.InlineOnly
inline fun View.visible() = View.VISIBLE

@kotlin.internal.InlineOnly
inline fun View.gone() = View.GONE

@kotlin.internal.InlineOnly
inline fun View.invisible() = View.INVISIBLE

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
