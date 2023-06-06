package com.dinesh.android.kotlin.retrofit

import android.content.Context
import android.util.Log
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import kotlin.coroutines.coroutineContext

private val TAG = "log_" + ApiClient::class.java.name.split(ApiClient::class.java.name.split(".").toTypedArray()[2] + ".").toTypedArray()[1]

object ApiClient {
    //    private const val BASE_URL = "http://10.0.2.2/"
    private const val TIMEOUT_SECONDS = 30L
    private const val RETRY_COUNT = 3
    private val loggingInterceptor = HttpLoggingInterceptor { message -> Log.d(TAG, message) }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .addInterceptor(loggingInterceptor)
        .addInterceptor(ApiRetryInterceptor(RETRY_COUNT))
        .build()

    private fun createRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApiInterface(baseUrl: String = "http://10.0.2.2/"): ApiInterface {
        return createRetrofit(baseUrl).create(ApiInterface::class.java)
    }
}


class ApiRetryInterceptor(private val maxRetries: Int) : Interceptor {
    private val TAG = "log_" + ApiRetryInterceptor::class.java.name.split(ApiRetryInterceptor::class.java.name.split(".").toTypedArray()[2] + ".").toTypedArray()[1]

    private var retries = 0

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        var response: Response
        while (true) {
            try {
                response = chain.proceed(request)
                if (response.isSuccessful || retries >= maxRetries) {
                    break
                }
            } catch (e: IOException) {
                if (retries >= maxRetries) {
                    Log.e(TAG, "intercept: ${e.message}")
                }
            }
            retries++
        }

        return response
    }
}


//object ApiClient {
//    private const val BASE_URL = "http://10.0.2.2/"
//
//    private val retrofit = Retrofit.Builder()
//        .baseUrl(BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    fun getApiInterface(): ApiInterface {
//        return retrofit.create(ApiInterface::class.java)
//    }
//}

/*

object ApiClient {
    private const val BASE_URL = "http://10.0.2.2/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getApiInterface(): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }
}

 */