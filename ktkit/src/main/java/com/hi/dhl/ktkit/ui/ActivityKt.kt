@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

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

/**
 * Example:
 *
 * ```
 * private val userName by intent<String>(KEY_USER_NAME) {
 *      "default"
 * }
 * ```
 */
inline fun <reified T : Any> Activity.intent(
    key: String,
    crossinline defaultValue: () -> T
) = lazy(LazyThreadSafetyMode.NONE) {
    val value = intent?.extras?.get(key)
    if (value is T) value else defaultValue()
}

/**
 *
 * Example:
 *
 * ```
 * private val userName by intent<String>(KEY_USER_NAME)
 * ```
 */
inline fun <reified T : Any> Activity.intent(
    key: String
) = lazy(LazyThreadSafetyMode.NONE) {
    intent?.extras?.get(key)
}

/**
 * Example:
 *
 * ```
 * context.startActivity<ProfileActivity>
 * ```
 */
inline fun <reified T : Activity> Context.startActivity() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

/**
 * 感谢 Kotlin/anko
 *
 * Example：
 *
 * ```
 * context.startActivity<ProfileActivity>(
 *     KEY_USER_NAME to "ByteCode",
 *     KEY_USER_PASSWORD to "1024"
 * )
 * ```
 */
inline fun <reified T : Activity> Context.startActivity(vararg params: Pair<String, Any>) {
    startActivity<T> {
        params
    }
}

/**
 * Example：
 *
 * ```
 * context.startActivity<ProfileActivity> {
 *     arrayOf(
 *         KEY_USER_NAME to "ByteCode",
 *         KEY_USER_PASSWORD to "1024",
 *         KEY_PEOPLE_PARCELIZE to PeopleModel("hi-dhl")
 *     )
 * }
 * ```
 */
inline fun <reified T : Any> Context.startActivity(params: () -> Array<out Pair<String, Any>>) {
    startActivity(makeIntent(this, T::class.java, params))
}

/**
 * Example：
 *
 * ```
 * context.startActivityForResult<ProfileActivity>(KEY_REQUEST_CODE,
 *     KEY_USER_NAME to "ByteCode",
 *     KEY_USER_PASSWORD to "1024",
 *     KEY_PEOPLE_PARCELIZE to PeopleModel("hi-dhl")
 * )
 * ```
 */
inline fun <reified T : Any> Context.startActivityForResult(
    requestCode: Int,
    vararg params: Pair<String, Any>
) {
    if (this is Activity) {
        val intent = makeIntent(this, T::class.java) {
            params
        }
        startActivityForResult(intent, requestCode)
    }
}

/**
 * Example：
 *
 * ```
 * context.startActivityForResult<ProfileActivity>(KEY_REQUEST_CODE) {
 *     arrayOf(
 *         KEY_USER_NAME to "ByteCode",
 *         KEY_USER_PASSWORD to "1024",
 *         KEY_PEOPLE_PARCELIZE to PeopleModel("hi-dhl")
 *     )
 * }
 * ```
 */
inline fun <reified T : Any> Context.startActivityForResult(
    requestCode: Int,
    params: () -> Array<out Pair<String, Any>>
) {
    if (this is Activity) {
        val intent = makeIntent(this, T::class.java, params)
        startActivityForResult(intent, requestCode)
    }
}

/**
 * Example：
 *
 * ```
 * setActivityResult(
 *     Activity.RESULT_OK,
 *     KEY_RESULT to "success",
 *     KEY_USER_NAME to "ByteCode"
 * )
 * ```
 */
@kotlin.internal.InlineOnly
inline fun Context.setActivityResult(
    resultCode: Int = Activity.RESULT_OK,
    vararg params: Pair<String, Any>
) {
    if (this is Activity) {
        val intent = makeIntent {
            params
        }
        setResult(resultCode, intent)
    }
}

/**
 * Example：
 *
 * ```
 * setActivityResult(Activity.RESULT_OK) {
 *     arrayOf(
 *         KEY_RESULT to "success"
 *     )
 * }
 * ```
 */
inline fun Context.setActivityResult(
    resultCode: Int = Activity.RESULT_OK,
    params: () -> Array<out Pair<String, Any>>
) {
    if (this is Activity) {
        setResult(resultCode, makeIntent(params))
    }
}

inline fun makeIntent(
    context: Context,
    targetClass: Class<*>,
    params: () -> Array<out Pair<String, Any>>
): Intent = Intent(context, targetClass).apply {
    val arry = params()
    for ((_, value) in arry.withIndex()) {
        makeParams(value)
    }
}

inline fun makeIntent(
    params: () -> Array<out Pair<String, Any>>
): Intent = Intent().apply {
    val arry = params()
    for ((_, value) in arry.withIndex()) {
        makeParams(value)
    }
}

// 感谢 Kotlin/anko
@kotlin.internal.InlineOnly
inline fun Intent.makeParams(it: Pair<String, Any>) {
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
