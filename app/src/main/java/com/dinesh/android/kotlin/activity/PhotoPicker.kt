package com.dinesh.android.kotlin.activity

import android.content.Intent
import android.net.Uri
import android.view.View
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.dinesh.android.R
import android.util.Log
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.Toast
import android.widget.ViewFlipper
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.dinesh.android.app.ToolbarMain

private val TAG = "log_" + PhotoPicker::class.java.name.split(PhotoPicker::class.java.name.split(".").toTypedArray()[2] + ".").toTypedArray()[1]

class PhotoPicker : ToolbarMain() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentViewLayout(R.layout.photo_picker)

//        pickMedia().launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
//        pickMultipleMedia().launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))

        test()
    }

    private fun test() {

    }

    private fun createImageView(imageResId: Int): ImageView {
        val imageView = ImageView(this)
        imageView.setImageResource(imageResId)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return imageView
    }

    private fun pickMedia() = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
        // Handle the selected photo
        if (uri != null) {
            Log.e(TAG, ": ${uri}")
        }
    }

    private fun pickMultipleMedia() = registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(5)) { uris ->
        // Handle the selected multiple photo
        if (uris.isNotEmpty()) {
            Log.d("PhotoPicker", "Number of items selected: ${uris.size}")
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }
}