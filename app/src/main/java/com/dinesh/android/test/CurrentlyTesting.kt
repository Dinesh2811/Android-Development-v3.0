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

    @Inject
    @Named("ApiService2")
    lateinit var apiService : ReturnType


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.currently_testing)

        Log.i(TAG, "onCreate: ${apiService}")

        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        val currentDate = Calendar.getInstance()

        val targetDate = Calendar.getInstance()
        targetDate.set(2023, Calendar.NOVEMBER, 30)

        if (currentDate == targetDate) {
            calendarView.dateTextAppearance = R.style.BlueDateTextAppearance
        }

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, month, dayOfMonth)

            if (selectedDate == targetDate) {
                calendarView.dateTextAppearance = R.style.BlueDateTextAppearance
            } else {
                calendarView.dateTextAppearance = R.style.DefaultDateTextAppearance
            }
        }

    }
}


data class Model(val data: String)
data class ReturnType(val returnValue: String, val data: String)


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    @Named("Retrofit1")
    fun providesRetrofit1() : Model {
        return Model("First")
    }
    @Singleton
    @Provides
    @Named("Retrofit2")
    fun providesRetrofit2() : Model {
        return Model("Second")
    }
    @Singleton
    @Provides
    @Named("ApiService1")
    fun providesApiService1(@Named("Retrofit1") model : Model): ReturnType {
        return ReturnType("providesApiService1", model.data)
    }
    @Singleton
    @Provides
    @Named("ApiService2")
    fun providesApiService2(@Named("Retrofit2") model : Model): ReturnType {
        return ReturnType("providesApiService2", model.data)
    }
}