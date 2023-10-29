package com.dinesh.android.kotlin.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

private val TAG = "log_" + SharedPreferenceActivity::class.java.name.split(SharedPreferenceActivity::class.java.name.split(".").toTypedArray()[2] + ".").toTypedArray()[1]

class SharedPreferenceDelegate(context: Context, private val name: String, private val defaultValue: String = "") : ReadWriteProperty<Any?, String> {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MySharedPreferences", AppCompatActivity.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return sharedPreferences.getString(name, defaultValue) ?: defaultValue
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        editor.putString(name, value).apply()
    }
}

fun Context.sharedPreferences(name: String) = SharedPreferenceDelegate(this, name)

class SharedPreferenceActivity : AppCompatActivity() {
    private var theme by sharedPreferences("Theme.Material3.Light")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        theme = "Theme.Material3.Dark"
        Log.i(TAG, "onCreate: $theme")

//        usualMethod()

    }

    private fun usualMethod() {
        //  Global declaration
        val sharedPreferences: SharedPreferences = getSharedPreferences("MySharedPreferences", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        //  Save the data using shared preferences
        editor.putString("Theme", "Theme.Material3")
        editor.apply() //Important

        //  Get the shared preferences data
        val currentTheme = sharedPreferences.getString("Theme", "Theme.Material3")
        Log.e(TAG, "Get the shared preferences data: ----> $currentTheme")
    }
}