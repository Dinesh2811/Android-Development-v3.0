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
    compileSdk = 34
    compileSdkPreview = "UpsideDownCake"

    defaultConfig {
        applicationId = "com.dinesh.android"
        minSdk = 24
        targetSdk = 34
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
    kotlin {
        jvmToolchain(JavaVersion.VERSION_17.toString().toInt())
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
//        kotlinCompilerExtensionVersion = "1.4.8"
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.bundles.android)
    implementation(libs.bundles.material3)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.compose.material)
    debugImplementation(libs.bundles.debug.implementation)
    androidTestImplementation(libs.bundles.android.test.implementation)
    testImplementation(libs.junit)


    //  ViewModel & LiveData
    implementation(libs.bundles.lifecycle)

    //  Room components
    implementation(libs.bundles.room)
    ksp(libs.androidx.room.compiler)    //  kapt or ksp

    // Glide
    implementation(libs.glide)
    annotationProcessor(libs.compiler)

    // Retrofit
    implementation(libs.bundles.retrofit)

    // HTTP
    implementation(libs.bundles.okhttp)

    // Gson
    implementation(libs.bundles.gson)

    // Navigation Component
    implementation(libs.bundles.navigation.ui)

    // Lottie
    implementation(libs.lottie)

    // Volley
    implementation(libs.volley)

    // RecyclerView
    implementation(libs.androidx.swiperefreshlayout)

    // Paging
    implementation(libs.bundles.paging)

    // Location Services
    implementation(libs.play.services.location)

    // Shimmer
    implementation(libs.shimmer)
}


/*

dependencies {
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.11.0-alpha01")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    testImplementation("junit:junit:4.13.2")

    implementation("androidx.compose.material3:material3:1.2.0-alpha04")
    implementation("androidx.compose.material3:material3-android:1.2.0-alpha04")

    implementation("androidx.compose.ui:ui:1.4.3")
    implementation("androidx.compose.ui:ui-graphics:1.4.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.3")
    implementation("androidx.compose.material:material:1.4.3")

    implementation("androidx.compose.animation:animation-core:1.4.3")
    implementation("androidx.compose.animation:animation:1.4.3")
    implementation("androidx.compose.foundation:foundation:1.4.3")
    implementation("androidx.compose.ui:ui-geometry:1.4.3")
    implementation("androidx.compose.foundation:foundation-layout:1.4.3")
    implementation("androidx.compose.runtime:runtime-livedata:1.4.3")
    implementation("androidx.compose.material:material-icons-core:1.4.3")
    implementation("androidx.compose.material:material-icons-extended:1.4.3")
    implementation("androidx.compose.ui:ui-text:1.4.3")
    implementation("androidx.compose.ui:ui-util:1.4.3")
    implementation("androidx.compose.ui:ui-viewbinding:1.4.3")

    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.3")
    debugImplementation("androidx.compose.ui:ui-tooling:1.4.3")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.4.3")

    implementation("androidx.compose:compose-bom:2023.06.01")
    androidTestImplementation("androidx.compose:compose-bom:2023.06.01")


    //  Room components
    implementation("androidx.room:room-runtime:2.5.2")
//    kapt("androidx.room:room-compiler:2.5.2")
    ksp("androidx.room:room-compiler:2.5.2")
    implementation("androidx.room:room-ktx:2.5.2")

    //  ViewModel & LiveData
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.1")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.1")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:retrofit-mock:2.9.0")

    // HTTP
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.8")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.8")

    // Gson
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // Gson Converter
    implementation("com.google.code.gson:gson:2.10.1") // Used to convert Java Object into JSON representation

    // Navigation Component
    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")

    // Lottie
    implementation("com.airbnb.android:lottie:6.1.0")

    // Volley
    implementation("com.android.volley:volley:1.2.1")

    // RecyclerView
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    // Paging
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.paging:paging-runtime-ktx:3.2.0")

    // Location Services
    implementation("com.google.android.gms:play-services-location:21.0.1")

}
 */
