package com.example.main_townguide.data.api

object AuthTokenStore {
    @Volatile
    private var token: String? = null

    fun save(token: String) {
        this.token = token
    }

    fun get(): String? = token

    fun clear() {
        token = null
    }
}
