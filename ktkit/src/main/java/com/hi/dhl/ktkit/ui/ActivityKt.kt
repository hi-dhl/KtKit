package com.hi.dhl.ktkit.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable

/**
 * <pre>
 *     author: dhl
 *     date  : 2021/5/15
 *     desc  :
 * </pre>
 */

inline fun <reified T : Any> Activity.intent(
    key: String,
    crossinline defaultValue: () -> T
) = lazy(LazyThreadSafetyMode.NONE) {
    val value = intent?.extras?.get(key)
    if (value is T) value else defaultValue()
}

inline fun <reified T : Any> Activity.intent(
    key: String
) = lazy(LazyThreadSafetyMode.NONE) {
    intent?.extras?.get(key)
}

inline fun <reified T : Activity> Context.startActivity() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

inline fun <reified T : Activity> Context.startActivity(vararg params: Pair<String, Any>) {
    startActivity<T> {
        params
    }
}

inline fun <reified T : Any> Context.startActivity(params: () -> Array<out Pair<String, Any>>) {
    startActivity(makeIntent(this, T::class.java, params))
}

inline fun <reified T : Any> Context.startActivityForResult(
    requestCode: Int,
    vararg params: Pair<String, Any>
) {
    if (this is Activity) {
        startActivityForResult(
            makeIntent(this, T::class.java) {
                params
            },
            requestCode
        )
    }
}

inline fun <reified T : Any> Context.startActivityForResult(
    requestCode: Int,
    params: () -> Array<out Pair<String, Any>>
) {
    if (this is Activity) {
        startActivityForResult(makeIntent(this, T::class.java, params), requestCode)
    }
}

inline fun makeIntent(
    context: Context,
    targetClass: Class<*>,
    params: () -> Array<out Pair<String, Any>>
): Intent = with(Intent(context, targetClass)) {
    params().forEach {
        val value = it.second
        // from anko
        when (value) {
            is Int -> putExtra(it.first, value)
            is Long -> putExtra(it.first, value)
            is CharSequence -> putExtra(it.first, value)
            is String -> putExtra(it.first, value)
            is Float -> putExtra(it.first, value)
            is Double -> putExtra(it.first, value)
            is Char -> putExtra(it.first, value)
            is Short -> putExtra(it.first, value)
            is Boolean -> putExtra(it.first, value)
            is java.io.Serializable -> putExtra(it.first, value)
            is Bundle -> putExtra(it.first, value)
            is Parcelable -> putExtra(it.first, value)
            is Array<*> -> when {
                value.isArrayOf<CharSequence>() -> putExtra(it.first, value)
                value.isArrayOf<String>() -> putExtra(it.first, value)
                value.isArrayOf<Parcelable>() -> putExtra(it.first, value)
                else -> throw IllegalArgumentException("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
            }
            is IntArray -> putExtra(it.first, value)
            is LongArray -> putExtra(it.first, value)
            is FloatArray -> putExtra(it.first, value)
            is DoubleArray -> putExtra(it.first, value)
            is CharArray -> putExtra(it.first, value)
            is ShortArray -> putExtra(it.first, value)
            is BooleanArray -> putExtra(it.first, value)
            else -> throw IllegalArgumentException("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
        }
    }
    this
}
