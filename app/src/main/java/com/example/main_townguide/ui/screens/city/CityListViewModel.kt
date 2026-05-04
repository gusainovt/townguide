package com.example.main_townguide.ui.screens.city

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.main_townguide.data.model.City
import com.example.main_townguide.data.repository.GuideRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CityListUiState(
    val cities: List<City> = emptyList(),
    val loading: Boolean = true,
    val error: String? = null
)

class CityListViewModel(
    private val repository: GuideRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(CityListUiState())
    val uiState: StateFlow<CityListUiState> = _uiState.asStateFlow()

    init {
        loadCities()
    }

    fun loadCities() {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, error = null) }
            repository.getCities()
                .onSuccess { cities -> _uiState.update { it.copy(cities = cities, loading = false) } }
                .onFailure { throwable -> _uiState.update { it.copy(loading = false, error = throwable.message ?: "Не удалось загрузить города") } }
        }
    }

    fun consumeError() = _uiState.update { it.copy(error = null) }
}

