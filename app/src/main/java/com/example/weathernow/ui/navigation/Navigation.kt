package com.example.weathernow.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weathernow.ui.screen.info.WeatherScreen
import com.example.weathernow.ui.screen.main.MainScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navigateToInfoScreen = { navController.navigate(Screen.InfoScreen.route) })
        }

        composable(route = Screen.InfoScreen.route) {
            WeatherScreen(navController = navController)
        }
    }
}