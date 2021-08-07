@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package com.hi.dhl.ktkit.lifecycle

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

/**
 * LifecycleOwner的扩展
 *
 * @author Qu Yunshuo
 * @since 2021/8/1 4:00 下午
 */

/**
 * 对LiveData订阅的简化封装
 *
 * Example:
 * ```
 *  override fun initObserve() {
 *      observeLiveData(mViewModel.stateViewLD, ::processStateViewLivaData)
 *  }
 *
 *  private fun processStateViewLivaData(data: StateLayoutEnum) {
 *      ...
 *  }
 * ```
 *
 * @receiver LifecycleOwner
 * @param liveData LiveData<T> 需要进行订阅的LiveData
 * @param action action: (t: T) -> Unit 处理订阅内容的方法
 * @return Unit
 */
@kotlin.internal.InlineOnly
inline fun <T> LifecycleOwner.observeLiveData(
    liveData: LiveData<T>,
    crossinline action: (t: T) -> Unit
) {
    liveData.observe(this, { it?.let { t -> action(t) } })
}
