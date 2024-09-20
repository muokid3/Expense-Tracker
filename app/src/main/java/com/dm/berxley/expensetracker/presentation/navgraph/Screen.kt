package com.dm.berxley.expensetracker.presentation.navgraph

sealed class Screen(val route: String){

    object OnboardingNavigator: Screen("onboardingNavigator")
    object IntroScreen: Screen("introScreen")



    object CoreNavigator: Screen("coreNavigator")
    object HomeScreen: Screen("homeScreen")

    object StatisticsScreen: Screen("statisticsScreen")
    object WalletScreen: Screen("walletScreen")
    object ProfileScreen: Screen("profileScreen")




}
