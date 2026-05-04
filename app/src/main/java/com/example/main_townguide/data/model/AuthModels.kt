package com.example.main_townguide.data.model

data class RegisterRequest(
    val login: String,
    val password: String
)

data class LoginRequest(
    val login: String,
    val password: String
)

data class AuthResponse(
    val token: String? = null,
    val access: String? = null,
    val accessToken: String? = null,
    val jwt: String? = null
) {
    fun bearerToken(): String? = token ?: access ?: accessToken ?: jwt
}
