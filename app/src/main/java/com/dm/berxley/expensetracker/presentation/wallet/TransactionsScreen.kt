package com.dm.berxley.expensetracker.presentation.wallet

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun TransactionsScreen(navController: NavController) {
    repeat(50){
        Text(text = "Transactions here")
    }

}