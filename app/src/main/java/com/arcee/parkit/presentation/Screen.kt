package com.arcee.parkit.presentation

sealed class Screen(val route: String) {
    object MainScreen : Screen("/")
    object HomeScreen : Screen("home")
    object ActivitiesScreen : Screen("activities")
    object FavouriteScreen : Screen("favourites")
    object ProfileScreen : Screen("profile")
    object NotificationsScreen : Screen("notifications")
    object ProviderDetailScreen : Screen("provider")
    object ActivityDetailScreen : Screen("activity-detail")
    object ParkingSpaceLocatorScreen : Screen("parking-space-locator")
    object SignInScreen : Screen("sign-in")
    object SignUpScreen : Screen("sign-up")
    object ActiveSessionScreen : Screen("active-session")
    object BookingScreen : Screen("booking")
    object UpdateProfileScreen : Screen("update-profile")
}