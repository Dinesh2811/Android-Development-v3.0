package com.dinesh.android.kotlin.activity.floating_window.v0

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log


class FloatingWindowService : Service() {
    private val TAG = "log_" + FloatingWindowService::class.java.name.split(FloatingWindowService::class.java.name.split(".").toTypedArray()[2] + ".").toTypedArray()[1]

    private lateinit var floatingWindowManager: FloatingWindowManager

    override fun onCreate() {
        super.onCreate()
        floatingWindowManager = FloatingWindowManager(applicationContext)
        Log.e(TAG, "onCreate: ")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            val rootWidth = it.getIntExtra("rootWidth", 0)
            val rootHeight = it.getIntExtra("rootHeight", 0)
            floatingWindowManager.showFloatingWindow(rootWidth, rootHeight)
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        floatingWindowManager.removeFloatingWindow()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
