package com.arcee.parkit.presentation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home")
    object ProfileScreen : Screen("profile")
}