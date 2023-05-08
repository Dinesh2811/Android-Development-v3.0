package com.dinesh.android.app

import android.app.Application

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
//        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}