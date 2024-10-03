package com.dm.berxley.expensetracker.presentation.home

import com.dm.berxley.expensetracker.domain.models.Transaction

data class HomeState(
    val transactionHistory: List<Transaction> = emptyList(),
    val totalBalance: Double = 0.0,
    val totalIncome: Double = 0.0,
    val totalExpenses: Double = 0.0
)
