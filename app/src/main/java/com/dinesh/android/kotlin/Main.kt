package com.dinesh.android.kotlin

import android.view.View
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.dinesh.android.databinding.BasicViewBinding

private val TAG = "log_" + Main::class.java.name.split(Main::class.java.name.split(".").toTypedArray()[2] + ".").toTypedArray()[1]

class Main : AppCompatActivity() {
    private lateinit var binding: BasicViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BasicViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.backButton.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            Log.e(TAG, "onBackPressedCallback: ")
            Toast.makeText(this@Main, "onBackPressedCallback", Toast.LENGTH_SHORT).show()
        }
    }

    private fun <T : View> findId(id: Int): T {
        return findViewById<T>(id)
    }

}