plugins {
//    alias(libs.plugins.androidApplication) apply false
//    alias(libs.plugins.kotlinAndroid) apply false

    id("com.android.application") version "8.2.0" apply false
    id("com.android.library") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.21" apply false
    id("com.google.devtools.ksp") version "1.9.21-1.0.15" apply false

    id("com.google.dagger.hilt.android") version "2.49" apply false
}

//val compileSdk by extra(34)     //  rootProject.extra["compileSdk"] as Int
//val kotlinCompilerExtensionVersion by extra("1.5.6")    //  rootProject.extra["kotlinCompilerExtensionVersion"] as String

