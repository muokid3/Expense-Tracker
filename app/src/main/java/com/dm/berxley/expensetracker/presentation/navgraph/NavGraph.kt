package com.dm.berxley.expensetracker.presentation.navgraph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.dm.berxley.expensetracker.presentation.home.HomeScreen
import com.dm.berxley.expensetracker.presentation.onboarding.intro.IntroScreen
import com.dm.berxley.expensetracker.presentation.profile.ProfileScreen
import com.dm.berxley.expensetracker.presentation.wallet.WalletScreen
import com.dm.berxley.expensetracker.presentation.wallet.addTransaction.AddTransaction

@Composable
fun NavGraph(
    navController: NavController,
    startDestination: String,
    paddingValues: PaddingValues
) {

    NavHost(
        navController = navController as NavHostController,
        startDestination = startDestination,
        modifier = Modifier.padding(paddingValues)
    ) {

        navigation(
            route = Screen.OnboardingNavigator.route,
            startDestination = Screen.IntroScreen.route
        ) {
            composable(route = Screen.IntroScreen.route) {
                IntroScreen(navController = navController)
            }

        }


        navigation(
            route = Screen.CoreNavigator.route,
            startDestination = Screen.HomeScreen.route
        ) {
            composable(route = Screen.HomeScreen.route) {
                HomeScreen(navController = navController)
            }

            composable(route = Screen.StatisticsScreen.route) {

            }

            composable(route = Screen.WalletScreen.route) {
                WalletScreen(navController = navController)
            }

            composable(route = Screen.AddTransactionScreen.route,
                arguments = listOf(
                    navArgument("transactionType") {
                        type = NavType.StringType
                    }
                )
            ) {
                AddTransaction(navController = navController)
            }

            composable(route = Screen.ProfileScreen.route) {
                ProfileScreen(navController = navController)
            }

        }
    }

}