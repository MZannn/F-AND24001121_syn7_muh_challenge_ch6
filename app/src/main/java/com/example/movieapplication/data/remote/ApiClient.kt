package com.example.movieapplication.data.remote

import com.example.movieapplication.ApiKey
import com.example.movieapplication.common.Constants.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val loggingInterceptor: HttpLoggingInterceptor
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            return httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }

    private val headerInterceptor = Interceptor { chain ->
        val originalRequest = chain.request()
        val requestWithHeaders = originalRequest.newBuilder()
            .header("Authorization", "Bearer ${ApiKey.apiKey}")
            .build()
        chain.proceed(requestWithHeaders)
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(headerInterceptor)
        .build()

    fun create(): ApiService  {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

       return retrofit.create(ApiService::class.java)
    }
}