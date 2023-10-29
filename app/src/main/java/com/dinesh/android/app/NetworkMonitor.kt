package com.dinesh.android.app

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.util.Log

/*

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    NetworkMonitor.start(this)

    // Check for internet connectivity
    if (NetworkMonitor.isInternetConnected()) {
        // The device is connected to the internet
        // You can perform your internet-dependent operations here.
    } else {
        // The device is not connected to the internet
        // Handle the absence of internet connectivity.
    }
}

override fun onDestroy() {
    super.onDestroy()
    NetworkMonitor.stop(this)
}

 */

object NetworkMonitor {
    private const val TAG = "NetworkMonitor"
    private var isConnected = false

    fun start(context: Context) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    fun stop(context: Context) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            Log.d(TAG, "Network available")
            isConnected = true
        }

        override fun onLost(network: Network) {
            Log.d(TAG, "Network lost")
            isConnected = false
        }
    }

    fun isInternetConnected(): Boolean {
        return isConnected
    }
}

/*

object NetworkMonitor {
    private const val TAG = "NetworkMonitor"
    private var isConnected = false

    fun start(context: Context) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val networkInfo = connectivityManager?.activeNetworkInfo
        isConnected = networkInfo?.isConnected ?: false
        connectivityManager?.registerDefaultNetworkCallback(networkCallback)
    }

    fun stop(context: Context) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.unregisterNetworkCallback(networkCallback)
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: android.net.Network) {
            Log.d(TAG, "Network available")
            isConnected = true
        }

        override fun onLost(network: android.net.Network) {
            Log.d(TAG, "Network lost")
            isConnected = false
        }
    }

    fun isInternetConnected(): Boolean {
        return isConnected
    }
}

 */