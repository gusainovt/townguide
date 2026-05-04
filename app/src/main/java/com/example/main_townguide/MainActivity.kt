package com.example.main_townguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.main_townguide.data.api.NetworkModule
import com.example.main_townguide.data.repository.GuideRepository
import com.example.main_townguide.ui.AppViewModelFactory
import com.example.main_townguide.ui.navigation.AppNavigation
import com.example.main_townguide.ui.theme.GuideTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val repository = GuideRepository(NetworkModule.api)
        val factory = AppViewModelFactory(repository)

        setContent {
            GuideTheme {
                AppNavigation(factory = factory)
            }
        }
    }
}

