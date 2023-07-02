plugins {
//    alias(libs.plugins.androidApplication)
//    alias(libs.plugins.kotlinAndroid)
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")   // ksp
    id("kotlin-kapt")    // Room plugin
}

android {
    namespace = "com.dinesh.android"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.dinesh.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.bundles.android)
    implementation(libs.bundles.material)
    androidTestImplementation(libs.bundles.android.test.implementation)
    debugImplementation(libs.bundles.debug.implementation)
    testImplementation(libs.junit)
    implementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(platform(libs.androidx.compose.bom))

    implementation(libs.bundles.lifecycle.viewmodel.livedata)
    implementation(libs.bundles.navigation.ui)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.okhttp)
    implementation(libs.bundles.gson)

    implementation(libs.bundles.room)
    ksp(libs.androidx.room.compiler)

    implementation(libs.glide)
    ksp(libs.compiler)

    implementation("com.airbnb.android:lottie:6.0.1")

    implementation(libs.volley)
    implementation(libs.androidx.swiperefreshlayout)    //  RecyclerView

    //  Paging
    implementation(libs.androidx.lifecycle.extensions)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.paging.runtime)

}