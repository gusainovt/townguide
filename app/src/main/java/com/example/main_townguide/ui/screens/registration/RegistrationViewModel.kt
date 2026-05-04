package com.example.main_townguide.ui.screens.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.main_townguide.data.repository.GuideRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class RegistrationUiState(
    val login: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val acceptedTerms: Boolean = false,
    val loading: Boolean = false,
    val success: Boolean = false,
    val error: String? = null
)

class RegistrationViewModel(
    private val repository: GuideRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegistrationUiState())
    val uiState: StateFlow<RegistrationUiState> = _uiState.asStateFlow()

    fun onLoginChange(value: String) = _uiState.update { it.copy(login = value, error = null) }

    fun onPasswordChange(value: String) = _uiState.update { it.copy(password = value, error = null) }

    fun onRepeatPasswordChange(value: String) = _uiState.update { it.copy(repeatPassword = value, error = null) }

    fun onTermsChange(value: Boolean) = _uiState.update { it.copy(acceptedTerms = value, error = null) }

    fun register() {
        val state = _uiState.value
        val login = state.login.trim()

        when {
            login.length < 3 -> {
                _uiState.update { it.copy(error = "Логин должен быть не короче 3 символов") }
                return
            }
            state.password.length < 4 -> {
                _uiState.update { it.copy(error = "Пароль должен быть не короче 4 символов") }
                return
            }
            state.password != state.repeatPassword -> {
                _uiState.update { it.copy(error = "Пароли не совпадают") }
                return
            }
            !state.acceptedTerms -> {
                _uiState.update { it.copy(error = "Примите пользовательское соглашение") }
                return
            }
        }

        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, error = null) }
            repository.register(login, state.password)
                .onSuccess { _uiState.update { it.copy(loading = false, success = true) } }
                .onFailure { throwable ->
                    _uiState.update {
                        it.copy(loading = false, error = throwable.message ?: "Не удалось зарегистрироваться")
                    }
                }
        }
    }

    fun consumeError() = _uiState.update { it.copy(error = null) }
}

data class LoginUiState(
    val login: String = "",
    val password: String = "",
    val loading: Boolean = false,
    val success: Boolean = false,
    val error: String? = null
)

class LoginViewModel(
    private val repository: GuideRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onLoginChange(value: String) = _uiState.update { it.copy(login = value, error = null) }

    fun onPasswordChange(value: String) = _uiState.update { it.copy(password = value, error = null) }

    fun login() {
        val state = _uiState.value
        val login = state.login.trim()

        if (login.isBlank() || state.password.isBlank()) {
            _uiState.update { it.copy(error = "Введите логин и пароль") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, error = null) }
            repository.login(login, state.password)
                .onSuccess { _uiState.update { it.copy(loading = false, success = true) } }
                .onFailure { throwable ->
                    _uiState.update {
                        it.copy(loading = false, error = throwable.message ?: "Не удалось войти")
                    }
                }
        }
    }

    fun consumeError() = _uiState.update { it.copy(error = null) }
}
