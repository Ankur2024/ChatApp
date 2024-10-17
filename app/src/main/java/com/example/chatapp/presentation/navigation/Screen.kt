package com.example.chatapp.presentation.navigation

sealed class Screen(val route: String){
    object OnBoarding: Screen("onboarding")
    object Login: Screen("login")
    object home_screen: Screen("homeScreen")
    object add_user_detail_screen: Screen("addUserDetailScreen")

}