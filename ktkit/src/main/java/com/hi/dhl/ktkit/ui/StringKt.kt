@file:OptIn(ExperimentalContracts::class)
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package com.hi.dhl.ktkit.ui

import android.util.Patterns
import com.google.i18n.phonenumbers.PhoneNumberUtil
import java.math.BigInteger
import java.security.MessageDigest
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * <pre>
 *     author: dhl
 *     date  : 2021/5/15
 *     desc  :
 * </pre>
 */

@SinceKotlin("1.3")
@kotlin.internal.InlineOnly
inline fun String?.isNotNullOrEmpty(): Boolean {
    contract {
        returns(true) implies (this@isNotNullOrEmpty != null)
    }

    return this != null && !this.trim().equals("null", true) && this.trim().isNotEmpty()
}

@kotlin.internal.InlineOnly
inline fun String.isValidPhone(): Boolean {
    return this.isNotNullOrEmpty() && Patterns.PHONE.matcher(this).matches()
}

/**
 * format Phone number
 *
 * Example:
 *
 * ```
 * val phontNumberStr = "044 668 18 00"
 * phontNumberStr.formatPhoneNumber("CH")
 * ```
 */
@kotlin.internal.InlineOnly
inline fun String.formatPhoneNumber(region: String): String? {
    val phoneNumberUtil = PhoneNumberUtil.getInstance()
    val number = phoneNumberUtil.parse(this, region)
    if (!phoneNumberUtil.isValidNumber(number))
        return null
    return phoneNumberUtil.format(number, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL)
}

@kotlin.internal.InlineOnly
inline fun String.isValidEmail(): Boolean {
    return this.isNotNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

@kotlin.internal.InlineOnly
inline fun String.isIPAddress(): Boolean {
    return this.isNotNullOrEmpty() && Patterns.IP_ADDRESS.matcher(this).matches()
}

@kotlin.internal.InlineOnly
inline fun String.isDomainName(): Boolean {
    return this.isNotNullOrEmpty() && Patterns.DOMAIN_NAME.matcher(this).matches()
}

@kotlin.internal.InlineOnly
inline fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}
