package com.arcee.parkit.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arcee.parkit.presentation.Screen
import com.arcee.parkit.presentation.home.HomeScreen
import com.arcee.parkit.presentation.notifications.ActivitiesScreen
import com.arcee.parkit.presentation.notifications.NotificationsScreen
import com.arcee.parkit.presentation.profile.ProfileScreen

data class TopLevelRoute<T : Any>(val name: String, val route: T, val icon: ImageVector)

val topLevelRoutes = listOf(
    TopLevelRoute("Home", Screen.HomeScreen.route, Icons.Filled.Home),
    TopLevelRoute("Activities", Screen.ActivitiesScreen.route, Icons.Filled.Menu),
    TopLevelRoute("Notifications", Screen.NotificationsScreen.route, Icons.Filled.Notifications),
    TopLevelRoute("Profile", Screen.ProfileScreen.route, Icons.Filled.Person),
)

@Composable
fun MainScreen(
    onProviderClicked: (id: Int) -> Unit,
    onActivityClicked: (id: Int, isActive: Boolean) -> Unit,
    onSearchClicked: () -> Unit,
) {
    Content(onProviderClicked = onProviderClicked, onSearchClicked = onSearchClicked, onActivityClicked = onActivityClicked )
}

@Composable
fun Content(onProviderClicked: (id: Int) -> Unit, onActivityClicked: (id: Int, isActive: Boolean) -> Unit, onSearchClicked: () -> Unit) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                topLevelRoutes.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(imageVector = item.icon, contentDescription = item.name)
                        },
                        selected = selectedItem == index,
                        alwaysShowLabel = false,
                        onClick = {
                            navController.navigate(item.route)
                            selectedItem = index
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.outline,
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.HomeScreen.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.HomeScreen.route) {
                HomeScreen(onSearchClicked = onSearchClicked, onProviderClicked = onProviderClicked)
            }
            composable(Screen.ActivitiesScreen.route) {
                ActivitiesScreen(onActivityClicked = onActivityClicked)
            }
            composable(Screen.ProfileScreen.route) {
                ProfileScreen()
            }
            composable(Screen.NotificationsScreen.route) {
                NotificationsScreen()
            }
        }
    }
}