package com.arcee.parkit.presentation

sealed class Screen(val route: String) {
    object MainScreen : Screen("/")
    object HomeScreen : Screen("home")
    object ActivitiesScreen : Screen("activities")
    object ProfileScreen : Screen("profile")
    object NotificationsScreen : Screen("notifications")
    object ProviderDetailScreen : Screen("provider")
    object ActivityDetailScreen: Screen("activity-detail")
    object ParkingSpaceLocatorScreen: Screen("parking-space-locator")
    object SignInScreen: Screen("sign-in")
    object ActiveSessionScreen: Screen("active-session")
}