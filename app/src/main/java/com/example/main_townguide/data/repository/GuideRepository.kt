package com.example.main_townguide.data.repository

import com.example.main_townguide.data.api.GuideApi
import com.example.main_townguide.data.api.AuthTokenStore
import com.example.main_townguide.data.model.City
import com.example.main_townguide.data.model.LoginRequest
import com.example.main_townguide.data.model.RegisterRequest

class GuideRepository(
    private val api: GuideApi
) {
    suspend fun register(login: String, password: String): Result<Unit> = runCatching {
        val registerResponse = api.register(RegisterRequest(login = login, password = password))
        if (!registerResponse.isSuccessful) error("Ошибка регистрации: ${registerResponse.code()}")
        login(login, password).getOrThrow()
    }

    suspend fun login(login: String, password: String): Result<Unit> = runCatching {
        val response = api.login(LoginRequest(login = login, password = password))
        if (!response.isSuccessful) {
            error("Ошибка авторизации: ${response.code()}")
        }
        val token = response.body()?.bearerToken()
            ?: error("Сервер не вернул токен авторизации")
        AuthTokenStore.save(token)
    }

    suspend fun getCities(): Result<List<City>> = runCatching {
        val cities = api.getCities()
        cities
    }
}
