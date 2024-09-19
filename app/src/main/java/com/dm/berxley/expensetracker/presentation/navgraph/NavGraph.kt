package com.dm.berxley.expensetracker.presentation.navgraph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dm.berxley.expensetracker.presentation.onboarding.intro.IntroScreen

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
            composable(route = Screen.IntroScreen.route){
                IntroScreen(navController = navController)
            }

        }


        navigation(
            route = Screen.CoreNavigator.route,
            startDestination = Screen.HomeScreen.route
        ) {
            composable(route = Screen.HomeScreen.route){

            }

            composable(route = Screen.StatisticsScreen.route){

            }

            composable(route = Screen.WalletScreen.route){

            }

            composable(route = Screen.ProfileScreen.route){

            }

        }
    }

}