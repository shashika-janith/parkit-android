package com.arcee.parkit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arcee.parkit.presentation.Screen
import com.arcee.parkit.presentation.home.HomeScreen
import com.arcee.parkit.presentation.profile.ProfileScreen
import com.arcee.parkit.ui.theme.ParkItTheme

data class TopLevelRoute<T : Any>(val name: String, val route: T, val icon: ImageVector)

val topLevelRoutes = listOf(
    TopLevelRoute("Home", Screen.HomeScreen.route, Icons.Outlined.Home),
    TopLevelRoute("Profile", Screen.ProfileScreen.route, Icons.Outlined.Person)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ParkItTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Songs", "Artists", "Playlists")
    val selectedIcons = listOf(Icons.Filled.Home, Icons.Filled.Favorite, Icons.Filled.Star)
    val unselectedIcons =
        listOf(Icons.Outlined.Home, Icons.Outlined.FavoriteBorder, Icons.Outlined.Star)

    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                topLevelRoutes.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(imageVector = item.icon, contentDescription = item.name)
                        },
                        label = { Text(item.name) },
                        selected = selectedItem == index,
                        onClick = {
                            navController.navigate(item.route)
                            selectedItem = index
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = Screen.HomeScreen.route, Modifier.padding(innerPadding)) {
            composable(Screen.HomeScreen.route) {
                HomeScreen()
            }
            composable(Screen.ProfileScreen.route) {
                ProfileScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ParkItTheme {
        Greeting()
    }
}