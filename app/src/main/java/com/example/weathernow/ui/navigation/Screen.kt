package com.example.weathernow.ui.navigation

sealed class Screen(val route: String, var title: String) {
    data object MainScreen: Screen(route = "main_screen", title = "MainScreen")
    data object InfoScreen: Screen(route = "info_screen", title = "InfoScreen")
}