**检查网络是否连接** 

```
hasNetwork()

true: 有网
false: 无网
```

**监听蜂窝网络 - 移动网络的变化** 


```
lifecycleScope.launch {
    listenCellular().collect {
        Log.e(TAG, "listenNetwork = $it")
    }
}
```


**监听 wifi 网络的变化** 

```
lifecycleScope.launch {
    listenWifi().collect {
        Log.e(TAG, "listenNetwork = $it")
    }
}
```


**监听蓝牙网络的变化** 

```
lifecycleScope.launch {
    listenNetworkFlow().collect {
        Log.e(TAG, "listenNetwork = $it")
    }
}
```

**连接高带宽网络，以及监听当前网络的变化** 


```
lifecycleScope.launch {
    bindFastNetWorkWithWifi().collect {
        Log.e(TAG, "listenNetwork = $it")
    }
}
```

**获取当前网络的速度**

```
getBandwidthKbps()
```


**获取当前网络的类型** 

```
getNetworkType()

返回 int 值，分别对应以下网络

NETWORK_NONE = -1 // 无网络
NETWORK_MOBILE = 1 // 蜂窝网络 - 移动网络
NETWORK_WIFI = 2 // wifi 网络
NETWORK_BLUETOOTH = 3 // 蓝牙网络
NETWORK_ETHERNET = 4 //
NETWORK_VPN = 5 // VPN
NETWORK_WIFI_AWARE = 6
NETWORK_LOWPAN = 7
```

**是否是 wifi 网络**

```
isConnectedToWifi()

true: 是 wifi 网络
fase: 不是 wifi 网路
```

**是否是蓝牙网络**

```
isConnectedToBluetooth()

true: 是蓝牙网络
fase: 不是蓝牙网路
```

