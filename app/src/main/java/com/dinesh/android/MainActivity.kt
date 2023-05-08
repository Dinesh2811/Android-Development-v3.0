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
        launchActivity(com.dinesh.android.app.user_interface.CollapsingToolbar::class.java)

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