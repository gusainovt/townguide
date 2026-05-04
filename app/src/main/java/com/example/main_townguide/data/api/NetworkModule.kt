package com.example.main_townguide.data.api

import com.example.main_townguide.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val token = AuthTokenStore.get()
            val request = if (token.isNullOrBlank()) {
                chain.request()
            } else {
                chain.request()
                    .newBuilder()
                    .header("Authorization", "Bearer $token")
                    .build()
            }
            chain.proceed(request)
        }
        .addInterceptor(loggingInterceptor)
        .build()

    val api: GuideApi = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GuideApi::class.java)
}
