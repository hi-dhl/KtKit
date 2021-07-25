package com.hi.dhl.ktkit.ui

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment

/**
 * <pre>
 *     author: dhl
 *     date  : 2021/6/20
 *     desc  :
 * </pre>
 */

/**
 *
 *  usage：
 *
 *  private val userName by intent<String>(KEY_USER_NAME) {
 *      "default"
 *  }
 *
 */
inline fun <reified T : Any> Fragment.intent(
    key: String,
    crossinline defaultValue: () -> Unit
) = lazy(LazyThreadSafetyMode.NONE) {
    val value = arguments?.get(key)
    if (value is T) value else defaultValue()
}

/**
 *
 *  usage：
 *
 *  private val userPassword by intent<String>(KEY_USER_PASSWORD)
 *
 */
inline fun <reified T : Any> Fragment.intent(
    key: String
) = lazy(LazyThreadSafetyMode.NONE) {
    arguments?.get(key)
}

/**
 *
 *  usage：
 *
 *  fun newInstance(): Fragment {
 *      return LoginFragment().makeBundle(
 *          ProfileActivity.KEY_USER_NAME to "ByteCode",
 *          ProfileActivity.KEY_USER_PASSWORD to "1024",
 *          ProfileActivity.KEY_PEOPLE_PARCELIZE to PeopleModel("hi-dhl")
 *      )
 *  }
 *
 */
inline fun Fragment.makeBundle(
    vararg params: Pair<String, Any>
): Fragment {
    makeBundle {
        params
    }
    return this
}

/**
 *
 *  usage：
 *
 *  fun newInstance(): Fragment {
 *      return LoginFragment().makeBundle {
 *          arrayOf(
 *              KEY_USER_NAME to "ByteCode",
 *              KEY_USER_PASSWORD to "1024",
 *              KEY_PEOPLE_PARCELIZE to PeopleModel("hi-dhl")
 *          )
 *      }
 *  }
 *
 */

inline fun Fragment.makeBundle(
    params: () -> Array<out Pair<String, Any>>
): Fragment {
    arguments = with(Bundle()) {
        params().forEach {
            makeParams(it)
        }
        this
    }
    return this
}

// from anko
fun Bundle.makeParams(it: Pair<String, Any>) {
    val value = it.second
    when (value) {
        is Int -> putInt(it.first, value)
        is Long -> putLong(it.first, value)
        is CharSequence -> putCharSequence(it.first, value)
        is String -> putString(it.first, value)
        is Float -> putFloat(it.first, value)
        is Double -> putDouble(it.first, value)
        is Char -> putChar(it.first, value)
        is Short -> putShort(it.first, value)
        is Boolean -> putBoolean(it.first, value)
        is java.io.Serializable -> putSerializable(it.first, value)
        is Bundle -> putBundle(it.first, value)
        is Parcelable -> putParcelable(it.first, value)
        is IntArray -> putIntArray(it.first, value)
        is LongArray -> putLongArray(it.first, value)
        is FloatArray -> putFloatArray(it.first, value)
        is DoubleArray -> putDoubleArray(it.first, value)
        is CharArray -> putCharArray(it.first, value)
        is ShortArray -> putShortArray(it.first, value)
        is BooleanArray -> putBooleanArray(it.first, value)
        else -> throw IllegalArgumentException("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
    }
}
