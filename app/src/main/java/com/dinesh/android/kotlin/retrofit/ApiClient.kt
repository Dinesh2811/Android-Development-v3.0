package com.dinesh.android.kotlin.retrofit

import android.content.Context
import android.util.Log
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import kotlin.coroutines.coroutineContext

object ApiClient {
    //    private const val BASE_URL = "http://10.0.2.2/"   http://192.168.1.5:3000/api/customers
    private const val TIMEOUT_SECONDS = 30L
    private const val RETRY_COUNT = 3
    private val loggingInterceptor = HttpLoggingInterceptor {
//            message -> Log.d("log_ApiClient", message)
    }.apply { level = HttpLoggingInterceptor.Level.BODY }

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

//    fun getApiInterface(baseUrl: String = "http://10.0.2.2/"): ApiInterface {
//        return createRetrofit(baseUrl).create(ApiInterface::class.java)
//    }

    fun <T> getApiInterface(apiInterface: Class<T>, baseUrl: String = "http://10.0.2.2/"): T {
        return createRetrofit(baseUrl).create(apiInterface)
    }

    inline fun <reified T> getApiInterface(baseUrl: String = "http://10.0.2.2/"): T {
        return getApiInterface(T::class.java, baseUrl)
    }

}


class ApiRetryInterceptor(private val maxRetries: Int) : Interceptor {
    private val TAG = "log_" + ApiRetryInterceptor::class.java.name.split(ApiRetryInterceptor::class.java.name.split(".").toTypedArray()[2] + ".").toTypedArray()[1]

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        var response: Response? = null
        for (retryCount in 0..maxRetries) {
            response = chain.proceed(request)
            if (response.isSuccessful || response.code != 404) {
                break
            } else {
                // If the response code is 404, it means the search didn't match,
                // so we should not retry the request
                break
            }
        }
        if (response == null) {
            Log.e(TAG, "No response received")
            return createErrorResponse() // Create a custom error response or return an appropriate response object
        }
        return response
    }
    private fun createErrorResponse(): Response {
        val errorBody = "{\"error\":\"No response received\"}".toResponseBody("application/json".toMediaType())
        return Response.Builder()
            .code(500) // Use an appropriate error code
            .message("Internal Server Error")
            .body(errorBody)
            .protocol(Protocol.HTTP_1_1)
//            .request(Request.Builder().url("http://dummyurl.com").build())
            .build()
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