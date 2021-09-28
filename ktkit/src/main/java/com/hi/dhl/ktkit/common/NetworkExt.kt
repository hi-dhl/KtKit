@file:OptIn(ExperimentalContracts::class, ExperimentalCoroutinesApi::class, FlowPreview::class)
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package com.hi.dhl.ktkit.common

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.hi.dhl.ktkit.ui.safeOffer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlin.contracts.ExperimentalContracts

/**
 * <pre>
 *     author: dhl
 *     date  : 2021/8/8
 *     desc  :
 * </pre>
 */

const val NETWORK_NONE = -1 // 无网络
const val NETWORK_MOBILE = 1 // 蜂窝网络 - 移动网络
const val NETWORK_WIFI = 2 // wifi 网络
const val NETWORK_BLUETOOTH = 3 // 蓝牙网络
const val NETWORK_ETHERNET = 4 //
const val NETWORK_VPN = 5 // VPN
const val NETWORK_WIFI_AWARE = 6
const val NETWORK_LOWPAN = 7

@kotlin.internal.InlineOnly
inline fun Context.getConnectivityManager() =
    getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

@RequiresPermission(value = Manifest.permission.ACCESS_NETWORK_STATE)
@kotlin.internal.InlineOnly
inline fun Context.getActiveNetworkInfo() =
    getConnectivityManager().activeNetworkInfo

/**
 * 检查网络是否连接
 */
@RequiresPermission(value = Manifest.permission.ACCESS_NETWORK_STATE)
@kotlin.internal.InlineOnly
inline fun Context.hasNetwork(): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val connectivityManager = getConnectivityManager()
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    } else {
        val activeNetwork = getActiveNetworkInfo() ?: return false
        return activeNetwork.isConnectedOrConnecting
    }
}

/**
 * 获取当前网络类型
 */
@RequiresPermission(value = Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.getNetworkType(): Int {
    // 获取网络类型，如果为空，返回无网络

    if (!hasNetwork()) {
        return NETWORK_NONE
    }

    val connectivityManager = getConnectivityManager()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val network = connectivityManager.activeNetwork ?: return NETWORK_NONE
        val capabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return NETWORK_NONE

        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> NETWORK_WIFI
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> NETWORK_BLUETOOTH
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> NETWORK_MOBILE
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> NETWORK_ETHERNET
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> NETWORK_VPN
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI_AWARE) -> NETWORK_WIFI_AWARE
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_LOWPAN) -> NETWORK_LOWPAN
            else -> return NETWORK_NONE
        }
    }

    val networkInfo = getActiveNetworkInfo() ?: return NETWORK_NONE
    return when (networkInfo.type) {
        ConnectivityManager.TYPE_WIFI -> NETWORK_WIFI
        ConnectivityManager.TYPE_BLUETOOTH -> NETWORK_BLUETOOTH
        ConnectivityManager.TYPE_MOBILE -> NETWORK_MOBILE
        ConnectivityManager.TYPE_ETHERNET -> NETWORK_ETHERNET
        else -> NETWORK_NONE
    }
}

@RequiresPermission(value = Manifest.permission.ACCESS_NETWORK_STATE)
@kotlin.internal.InlineOnly
inline fun Context.isConnectedToWifi(): Boolean = getNetworkType() == NETWORK_WIFI

@RequiresPermission(value = Manifest.permission.ACCESS_NETWORK_STATE)
@kotlin.internal.InlineOnly
inline fun Context.isConnectedToBluetooth(): Boolean = getNetworkType() == NETWORK_BLUETOOTH

/**
 * 监听蜂窝网络 - 移动网络
 *
 * Example:
 *
 * ```
 * listenCellular().collect {
 *
 * }
 * ```
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
@RequiresPermission(value = Manifest.permission.ACCESS_NETWORK_STATE)
@kotlin.internal.InlineOnly
inline fun Context.listenCellular(): Flow<Boolean> =
    listenNetworkFlow(NetworkCapabilities.TRANSPORT_CELLULAR)

/**
 * 监听 wifi
 *
 * Example:
 *
 * ```
 * listenWifi().collect {
 *
 * }
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
@RequiresPermission(value = Manifest.permission.ACCESS_NETWORK_STATE)
@kotlin.internal.InlineOnly
inline fun Context.listenWifi(): Flow<Boolean> =
    listenNetworkFlow(NetworkCapabilities.TRANSPORT_WIFI)

/**
 * 监听蓝牙
 *
 * Example:
 *
 * ```
 * listenWifi().listenBluetooth {
 *
 * }
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
@RequiresPermission(value = Manifest.permission.ACCESS_NETWORK_STATE)
@kotlin.internal.InlineOnly
inline fun Context.listenBluetooth(): Flow<Boolean> =
    listenNetworkFlow(NetworkCapabilities.TRANSPORT_BLUETOOTH)

/**
 * 1. 动态监听指定网络的变化
 * 2. 连接高带宽网络
 *
 * @param type 网络类型
 * @param bindNetWork 是否连接链接高带宽网络
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
@RequiresPermission(value = Manifest.permission.ACCESS_NETWORK_STATE)
@kotlin.internal.InlineOnly
inline fun Context.listenNetworkFlow(type: Int, bindNetWork: Boolean = false) =
    callbackFlow<Boolean> {
        val networkRequest = NetworkRequest.Builder()
            .addTransportType(type)
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        val connectivityManager = getConnectivityManager()

        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onLost(network: Network) {
                if (bindNetWork) {
                    bindProcessToNetwork(null)
                    safeOffer(false)
                } else {
                    safeOffer(false)
                }
            }

            override fun onAvailable(network: Network) {
                if (bindNetWork) {
                    safeOffer(bindProcessToNetwork(network))
                } else {
                    safeOffer(true)
                }
            }

            private fun bindProcessToNetwork(network: Network?): Boolean {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    return connectivityManager.bindProcessToNetwork(network)
                } else {
                    return ConnectivityManager.setProcessDefaultNetwork(network)
                }
            }
        }

        if (bindNetWork) {
            connectivityManager.requestNetwork(networkRequest, callback)
        } else {
            connectivityManager.registerNetworkCallback(networkRequest, callback)
        }

        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }

/**
 * 连接高带宽网络 - wifi
 *
 * 如果曾经连接过 wifi，会强制绑定连接当前 wifi
 *
 * Example:
 *
 * ```
 * bindFastNetWorkWithWifi().listenBluetooth {
 *
 * }
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
@RequiresPermission(value = Manifest.permission.ACCESS_NETWORK_STATE)
@kotlin.internal.InlineOnly
inline fun Context.bindFastNetWorkWithWifi(): Flow<Boolean> =
    listenNetworkFlow(NetworkCapabilities.TRANSPORT_WIFI, true)

/**
 * 获取当前网络速度
 */
@RequiresApi(Build.VERSION_CODES.M)
@RequiresPermission(value = Manifest.permission.ACCESS_NETWORK_STATE)
@kotlin.internal.InlineOnly
fun Context.getBandwidthKbps(): Int {
    if (!hasNetwork()) {
        return 0
    }

    val connectivityManager = getConnectivityManager()
    val network =
        connectivityManager.getBoundNetworkForProcess()
            ?: connectivityManager.getActiveNetwork()
            ?: return 0

    val capabilities =
        connectivityManager.getNetworkCapabilities(network) ?: return 0
    return capabilities.getLinkDownstreamBandwidthKbps()
}
