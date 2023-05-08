package com.dinesh.android.app

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log

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
