package com.arcee.parkit

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arcee.parkit.data.repository.UserPreferencesRepository
import com.arcee.parkit.domain.model.Provider
import com.arcee.parkit.presentation.Screen
import com.arcee.parkit.presentation.active_session.ActiveSessionScreen
import com.arcee.parkit.presentation.activity_detail.ActivityDetailScreen
import com.arcee.parkit.presentation.booking.BookingScreen
import com.arcee.parkit.presentation.main.MainScreen
import com.arcee.parkit.presentation.notifications.NotificationsScreen
import com.arcee.parkit.presentation.parking_space_locator.ParkingSpaceLocatorScreen
import com.arcee.parkit.presentation.provider_detail.ProviderDetailScreen
import com.arcee.parkit.presentation.sign_in.SignInScreen
import com.arcee.parkit.presentation.sign_up.SignUpScreen
import com.arcee.parkit.ui.theme.ParkItTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var userPreferencesRepo: UserPreferencesRepository

    val viewModel: LocationPermissionViewModel by viewModels<LocationPermissionViewModel>()


    private fun startNavigationGM(lat: Double, lon: Double) {
        val gmmIntentUri =
            Uri.parse("google.navigation:q=$lat,$lon")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")

        try {
            startActivity(mapIntent)
        } catch (e: ActivityNotFoundException) {
            // TODO: Define what your app should do if no activity can handle the intent.
        }
    }

    private suspend fun getStartDestination(): String {
        val preferences = userPreferencesRepo.fetchInitialPreferences()

        if (preferences?.accessToken.isNullOrBlank()) {
            return Screen.SignInScreen.route
        } else {
            return Screen.MainScreen.route
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var startDestination by remember { mutableStateOf<String?>(null) }

            LaunchedEffect(Unit) {
                startDestination = getStartDestination()
            }

            ParkItTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()

                    if (startDestination != null) {
                        NavHost(
                            navController = navController,
                            startDestination = startDestination!!
                        ) {
                            composable(Screen.MainScreen.route) {
                                MainScreen(
                                    onProviderClicked = { data: Provider ->
                                        navController.currentBackStackEntry?.savedStateHandle?.set(
                                            "provider",
                                            data
                                        )
                                        navController.navigate(Screen.ProviderDetailScreen.route + "/${id}")
                                    },
                                    onActivityClicked = { id: Int, isActive: Boolean ->
                                        if (isActive)
                                            navController.navigate(Screen.ActiveSessionScreen.route)
                                        else
                                            navController.navigate(Screen.ActivityDetailScreen.route)
                                    },
                                    onSearchClicked = {
                                        navController.navigate(Screen.ParkingSpaceLocatorScreen.route)
                                    },
                                    onNotificationsClicked = {
                                        navController.navigate(Screen.NotificationsScreen.route)
                                    }
                                )
                            }
                            composable(Screen.ProviderDetailScreen.route + "/{providerId}") {
                                val provider = navController.previousBackStackEntry
                                    ?.savedStateHandle
                                    ?.get<Provider>("provider")

                                if (provider != null) {
                                    ProviderDetailScreen(
                                        data = provider,
                                        onStartNavClicked = { lat, lon ->
                                            startNavigationGM(
                                                lat,
                                                lon
                                            )
                                        },
                                        onNavigateBack = {
                                            navController.popBackStack()
                                            navController.currentBackStackEntry?.savedStateHandle?.remove<Provider>("provider")
                                        }
                                    )
                                }
                            }
                            composable(Screen.BookingScreen.route) {
                                BookingScreen(onNavigateBack = {})
                            }
                            composable(Screen.ActivityDetailScreen.route) {
                                ActivityDetailScreen(onNavigateBack = {
                                    navController.popBackStack()
                                    navController.currentBackStackEntry?.savedStateHandle?.remove<Provider>("provider")
                                })
                            }
                            composable(Screen.ParkingSpaceLocatorScreen.route) {
                                ParkingSpaceLocatorScreen(
                                    onNavigateBack = {
                                        navController.popBackStack()
                                    },
                                    onStartNavClicked = { lat, lon ->
                                        startNavigationGM(
                                            lat,
                                            lon
                                        )
                                    }
                                )
                            }
                            composable(Screen.SignInScreen.route) {
                                SignInScreen(didSignIn = { data ->
                                    navController.navigate(Screen.MainScreen.route)
                                })
                            }
                            composable(Screen.SignUpScreen.route) {
                                SignUpScreen()
                            }
                            composable(Screen.NotificationsScreen.route) {
                                NotificationsScreen()
                            }
                            composable(Screen.ActiveSessionScreen.route) {
                                ActiveSessionScreen(onNavigateBack = {})
                            }
                        }
                    } else {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }

        viewModel.state.observe(this) { status ->
            when {
                PermissionStatus.GRANTED == status -> {
//                    TODO("Navigate to home screen")
                }

                PermissionStatus.DENIED == status -> {
//                    TODO("Show an educational UI")
                }

                else -> {
                    requestLocationPermission()
                }
            }
        }

        // Check and request location permission on start.
        checkLocationPermission()
    }

    private fun checkLocationPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                viewModel.updatePermissionStatus(PermissionStatus.GRANTED)
                println("Permission granted")
//                TODO("Navigate to home screen")
            }
//            ContextCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) == PackageManager.PERMISSION_DENIED -> {
//                viewModel.updatePermissionStatus(PermissionStatus.DENIED)
//                println("Permission denied")
//                TODO("Show an educational UI")
//            }
            else -> {
                viewModel.requestLocationPermission()
            }
        }
    }

    private val requestLocationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            viewModel.updatePermissionStatus(if (isGranted) PermissionStatus.GRANTED else PermissionStatus.DENIED)
        }

    private fun requestLocationPermission() {
        requestLocationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }
}