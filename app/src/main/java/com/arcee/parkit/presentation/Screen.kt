package com.arcee.parkit.presentation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home")
    object ActivitiesScreen : Screen("activities")
    object ProfileScreen : Screen("profile")
    object NotificationsScreen : Screen("notifications")
}