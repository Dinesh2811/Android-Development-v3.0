package com.dinesh.android

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dinesh.android.app.*
import com.dinesh.android.java.dialog.Rv_as_Dialog
import com.dinesh.android.ui.theme.Material3Theme

private val TAG = "log_" + MainActivity::class.java.name.split(MainActivity::class.java.name.split(".").toTypedArray()[2] + ".").toTypedArray()[1]

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
//        val classNameAsString = getSharedPreferences("sharedPreferences_$packageName", MODE_PRIVATE).getString("classNameTesting", com.dinesh.android.root.RvMain::class.java.name)
//        Log.e(TAG, "classNameAsString: $classNameAsString")
//        launchActivity(Class.forName(classNameAsString.toString()))

//        launchActivity(com.dinesh.android.root.RvMain::class.java)
//        launchActivity(com.dinesh.android.app.user_interface.CollapsingToolbar::class.java)
        launchActivity(com.dinesh.android.app.tab_layout.MainActivity::class.java)

        logColor()

    }

    private fun logColor() {
//        Log.d(TAG, "Color code 10: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_primary10))}")
//        Log.d(TAG, "Color code 20: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_primary20))}")
//        Log.d(TAG, "Color code 30: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_primary30))}")
//        Log.d(TAG, "Color code 40: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_primary40))}")
//        Log.d(TAG, "Color code 50: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_primary50))}")
//        Log.d(TAG, "Color code 60: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_primary60))}")
//        Log.d(TAG, "Color code 70: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_primary70))}")
//        Log.d(TAG, "Color code 80: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_primary80))}")
//        Log.d(TAG, "Color code 90: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_primary90))}")
//        Log.d(TAG, "Color code 95: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_primary95))}")

//        Log.d(TAG, "Color code 10: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_secondary10))}")
//        Log.d(TAG, "Color code 20: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_secondary20))}")
//        Log.d(TAG, "Color code 30: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_secondary30))}")
//        Log.d(TAG, "Color code 40: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_secondary40))}")
//        Log.d(TAG, "Color code 50: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_secondary50))}")
//        Log.d(TAG, "Color code 60: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_secondary60))}")
//        Log.d(TAG, "Color code 70: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_secondary70))}")
//        Log.d(TAG, "Color code 80: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_secondary80))}")
//        Log.d(TAG, "Color code 90: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_secondary90))}")
//        Log.d(TAG, "Color code 95: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_secondary95))}")

//        Log.d(TAG, "Color code 10: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_tertiary10))}")
//        Log.d(TAG, "Color code 20: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_tertiary20))}")
//        Log.d(TAG, "Color code 30: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_tertiary30))}")
//        Log.d(TAG, "Color code 40: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_tertiary40))}")
//        Log.d(TAG, "Color code 50: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_tertiary50))}")
//        Log.d(TAG, "Color code 60: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_tertiary60))}")
//        Log.d(TAG, "Color code 70: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_tertiary70))}")
//        Log.d(TAG, "Color code 80: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_tertiary80))}")
//        Log.d(TAG, "Color code 90: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_tertiary90))}")
//        Log.d(TAG, "Color code 95: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_tertiary95))}")


//        Log.d(TAG, "Color code 10: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_neutral10))}")
//        Log.d(TAG, "Color code 20: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_neutral20))}")
//        Log.d(TAG, "Color code 30: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_neutral30))}")
//        Log.d(TAG, "Color code 40: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_neutral40))}")
//        Log.d(TAG, "Color code 50: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_neutral50))}")
//        Log.d(TAG, "Color code 60: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_neutral60))}")
//        Log.d(TAG, "Color code 70: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_neutral70))}")
//        Log.d(TAG, "Color code 80: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_neutral80))}")
//        Log.d(TAG, "Color code 90: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_neutral90))}")
//        Log.d(TAG, "Color code 95: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_neutral95))}")


//        Log.d(TAG, "Color code 10: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_neutral_variant10))}")
//        Log.d(TAG, "Color code 20: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_neutral_variant20))}")
//        Log.d(TAG, "Color code 30: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_neutral_variant30))}")
//        Log.d(TAG, "Color code 40: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_neutral_variant40))}")
//        Log.d(TAG, "Color code 50: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_neutral_variant50))}")
//        Log.d(TAG, "Color code 60: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_neutral_variant60))}")
//        Log.d(TAG, "Color code 70: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_neutral_variant70))}")
//        Log.d(TAG, "Color code 80: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_neutral_variant80))}")
//        Log.d(TAG, "Color code 90: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_neutral_variant90))}")
//        Log.d(TAG, "Color code 95: ${Integer.toHexString(this.getColor(com.google.android.material.R.color.material_dynamic_neutral_variant95))}")

    }

    private fun init() {
        setContent {
            Material3Theme() {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }

}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Material3Theme {
        Greeting("Android")
    }
}