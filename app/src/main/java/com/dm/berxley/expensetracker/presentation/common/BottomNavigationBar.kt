package com.dm.berxley.expensetracker.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.dm.berxley.expensetracker.MainViewModel
import com.dm.berxley.expensetracker.presentation.navgraph.Screen

@Composable
fun BottomNavigationBar(navController: NavController, mainViewModel: MainViewModel) {

    val navItems = listOf<NavItem>(
        NavItem(name = "Home", selectedIcon = Icons.Filled.Home, unSelectedIcon = Icons.Outlined.Home),
        NavItem(name = "Stats", selectedIcon = Icons.Filled.BarChart, unSelectedIcon = Icons.Outlined.BarChart),
        NavItem(name = "Wallet", selectedIcon = Icons.Filled.AccountBalanceWallet, unSelectedIcon = Icons.Outlined.AccountBalanceWallet),
        NavItem(name = "Profile", selectedIcon = Icons.Filled.AccountBox, unSelectedIcon = Icons.Outlined.AccountBox),
    )

    NavigationBar(modifier = Modifier.fillMaxWidth()) {

        navItems.forEachIndexed { index, navItem ->

            NavigationBarItem(
                selected = mainViewModel.selectedBottomIndex == index,
                onClick = {
                    when (index) {
                        0 -> {
                            mainViewModel.setBottomIndex(0)
                            navController.navigate(Screen.HomeScreen.route)
                        }

                        1 -> {
                            mainViewModel.setBottomIndex(1)
                            navController.navigate(Screen.StatisticsScreen.route)
                        }

                        2 -> {
                            mainViewModel.setBottomIndex(2)
                            navController.navigate(Screen.WalletScreen.route)
                        }

                        3 -> {
                            mainViewModel.setBottomIndex(3)
                            navController.navigate(Screen.ProfileScreen.route)
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (mainViewModel.selectedBottomIndex == index) navItem.selectedIcon else navItem.unSelectedIcon,
                        contentDescription = navItem.name
                    )
                })

        }

    }
}

data class NavItem(
    val name: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
)