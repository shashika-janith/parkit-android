package com.arcee.parkit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arcee.parkit.presentation.Screen
import com.arcee.parkit.presentation.activity_detail.ActivityDetailScreen
import com.arcee.parkit.presentation.main.MainScreen
import com.arcee.parkit.presentation.parking_space_locator.ParkingSpaceLocatorScreen
import com.arcee.parkit.presentation.provider_detail.ProviderDetailScreen
import com.arcee.parkit.presentation.sign_in.SignInScreen
import com.arcee.parkit.ui.theme.ParkItTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ParkItTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.MainScreen.route
//                        startDestination = Screen.ParkingSpaceLocatorScreen.route
                    ) {
                        composable(Screen.MainScreen.route) {
                            MainScreen(
                                onProviderClicked = {
                                    navController.navigate(Screen.ActivityDetailScreen.route)
                                },
                                onSearchClicked = {
                                    navController.navigate(Screen.ParkingSpaceLocatorScreen.route)
                                }
                            )
                        }
                        composable(Screen.ProviderDetailScreen.route + "/{providerId}") {
                            ProviderDetailScreen()
                        }
                        composable(Screen.ActivityDetailScreen.route) {
                            ActivityDetailScreen(onNavigateBack = {})
                        }
                        composable(Screen.ParkingSpaceLocatorScreen.route) {
                            ParkingSpaceLocatorScreen(onNavigateBack = {})
                        }
                        composable(Screen.SignInScreen.route) {
                            SignInScreen()
                        }
                    }
                }
            }
        }
    }
}