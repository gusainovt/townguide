package com.example.main_townguide.data.model

data class City(
    val id: Long,
    val name: String,
    val nameEng: String? = null,
    val description: String,
    val callback: String? = null,
    val photo: String,
    val places: List<Any> = emptyList(),
    val stories: List<Any> = emptyList()
)
