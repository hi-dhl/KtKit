package com.hi.dhl.ktkit.ui

import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 * <pre>
 *     author: dhl
 *     date  : 2021/3/29
 *     desc  :
 * </pre>
 */

fun View.visible() = View.VISIBLE

fun View.gone() = View.GONE

fun View.invisible() = View.INVISIBLE

fun View.showShotSnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

fun View.showLongSnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}

fun View.snackBarWithAction(
    message: String,
    actionlable: String,
    block: () -> Unit
) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        .setAction(actionlable) {
            block()
        }
}
