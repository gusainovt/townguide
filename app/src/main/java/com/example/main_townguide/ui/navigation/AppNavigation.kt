package com.example.main_townguide.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.main_townguide.ui.AppViewModelFactory
import com.example.main_townguide.ui.screens.city.CityListScreen
import com.example.main_townguide.ui.screens.city.CityListViewModel
import com.example.main_townguide.ui.screens.registration.LoginScreen
import com.example.main_townguide.ui.screens.registration.LoginViewModel
import com.example.main_townguide.ui.screens.registration.RegistrationScreen
import com.example.main_townguide.ui.screens.registration.RegistrationViewModel

private object Routes {
    const val Registration = "registration"
    const val Login = "login"
    const val Cities = "cities"
}

@Composable
fun AppNavigation(factory: AppViewModelFactory) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Registration) {
        composable(Routes.Registration) {
            val viewModel: RegistrationViewModel = viewModel(factory = factory)
            RegistrationScreen(
                viewModel = viewModel,
                onRegistered = {
                    navController.navigate(Routes.Cities) {
                        popUpTo(Routes.Registration) { inclusive = true }
                    }
                },
                onLoginClick = { navController.navigate(Routes.Login) },
                onBack = {}
            )
        }
        composable(Routes.Login) {
            val viewModel: LoginViewModel = viewModel(factory = factory)
            LoginScreen(
                viewModel = viewModel,
                onLoggedIn = {
                    navController.navigate(Routes.Cities) {
                        popUpTo(Routes.Registration) { inclusive = true }
                    }
                },
                onRegisterClick = { navController.popBackStack() },
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.Cities) {
            val viewModel: CityListViewModel = viewModel(factory = factory)
            CityListScreen(viewModel = viewModel)
        }
    }
}
