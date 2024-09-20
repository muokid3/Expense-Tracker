package com.dm.berxley.expensetracker.presentation.wallet

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun BillsScreen(navController: NavController) {
    repeat(20){
        Text(text = "Bills here")
    }

}