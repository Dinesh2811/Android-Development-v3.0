package com.dinesh.android.test

import android.view.View
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dinesh.android.R
import android.util.Log
import android.widget.CalendarView
import androidx.compose.ui.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.*
import androidx.activity.compose.*
import androidx.compose.material.icons.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.graphics.vector.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

private val TAG = "log_" + CurrentlyTesting::class.java.name.split(CurrentlyTesting::class.java.name.split(".").toTypedArray()[2] + ".").toTypedArray()[1]
@AndroidEntryPoint
class CurrentlyTesting : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.currently_testing)


    }
}
