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
import kotlinx.coroutines.launch
import java.util.Calendar

private val TAG = "log_" + CurrentlyTesting::class.java.name.split(CurrentlyTesting::class.java.name.split(".").toTypedArray()[2] + ".").toTypedArray()[1]

class CurrentlyTesting : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.currently_testing)

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

