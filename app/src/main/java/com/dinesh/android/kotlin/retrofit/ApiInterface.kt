package com.dinesh.android.kotlin.retrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("productsTestJsonObject")
    suspend fun getProductJsonDataAsObject(): JsonData

    @GET("productsTestJsonArray")
    suspend fun getProductJsonDataAsArray(): List<JsonData>
}