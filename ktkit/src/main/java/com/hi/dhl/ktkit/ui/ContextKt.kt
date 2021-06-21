package com.hi.dhl.ktkit.ui

import android.content.Context
import android.widget.Toast

/**
 * <pre>
 *     author: dhl
 *     date  : 2021/5/15
 *     desc  :
 * </pre>
 */

fun Context.showShotToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showLongToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
