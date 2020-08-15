package com.axxess.cavistaassignment.network

import com.axxess.cavistaassignment.util.CommonConstant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Api {


    var okHttpClient = OkHttpClient().run {
        newBuilder().apply {
            connectTimeout(5, TimeUnit.SECONDS);
            readTimeout(5, TimeUnit.SECONDS);
            writeTimeout(5, TimeUnit.SECONDS);
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
    }.build()

    val http = Retrofit.Builder().run {
        client(okHttpClient)
        addConverterFactory(GsonConverterFactory.create())
        baseUrl(CommonConstant.BASE_URL)
    }.build().create(ApiService::class.java)
}