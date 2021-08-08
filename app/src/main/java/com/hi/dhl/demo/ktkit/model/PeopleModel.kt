package com.hi.dhl.demo.ktkit.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * <pre>
 *     author: dhl
 *     date  : 2021/6/20
 *     desc  :
 * </pre>
 */

@Parcelize
data class PeopleModel(
    val name: String? = null,
    val address: String? = null
) : Parcelable
