package com.example.weathernow.ui

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.rememberNavController
import com.example.weathernow.ui.navigation.Navigation
import com.example.weathernow.ui.theme.WeatherNowTheme
import com.example.weathernow.ui.theme.WhiteFE

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.light(WhiteFE.toArgb(), Color.TRANSPARENT))
        setContent {
            val navController = rememberNavController()
            WeatherNowTheme {
                Navigation(navController = navController)
            }
        }
    }
}