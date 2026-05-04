package com.example.main_townguide.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.main_townguide.data.repository.GuideRepository
import com.example.main_townguide.ui.screens.city.CityListViewModel
import com.example.main_townguide.ui.screens.registration.LoginViewModel
import com.example.main_townguide.ui.screens.registration.RegistrationViewModel

class AppViewModelFactory(
    private val repository: GuideRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RegistrationViewModel::class.java) -> RegistrationViewModel(repository) as T
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(repository) as T
            modelClass.isAssignableFrom(CityListViewModel::class.java) -> CityListViewModel(repository) as T
            else -> error("Unknown ViewModel: ${modelClass.name}")
        }
    }
}
