package com.example.main_townguide.data.api

import com.example.main_townguide.data.model.City
import com.example.main_townguide.data.model.AuthResponse
import com.example.main_townguide.data.model.LoginRequest
import com.example.main_townguide.data.model.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface GuideApi {
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<Unit>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>

    @GET("api/v1/city")
    suspend fun getCities(): List<City>
}
