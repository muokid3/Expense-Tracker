package com.dm.berxley.expensetracker.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dm.berxley.expensetracker.domain.repositories.ExpenseRoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val expenseRoomRepository: ExpenseRoomRepository
) : ViewModel() {
    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()


    init {
        loadWalletStats()
        loadWalletTransactions()
    }

    private fun loadWalletStats() {
        viewModelScope.launch(Dispatchers.IO) {

            val expenses = expenseRoomRepository.getSumAmount("EXPENSE")
            val income = expenseRoomRepository.getSumAmount("INCOME")
            val walletBalance = income - expenses

            Log.d("HOME EXPENSES", "loadWalletStats: $expenses")
            Log.d("HOME INCOME", "loadWalletStats: $income")

            _homeState.update {
                it.copy(
                    totalBalance = walletBalance,
                    totalIncome = income,
                    totalExpenses = expenses
                )
            }
        }
    }

    private fun loadWalletTransactions() {
        viewModelScope.launch {
            expenseRoomRepository.getTopFiveTransactions().collectLatest { topFiveTransactions ->
                _homeState.update {
                    it.copy(transactionHistory = topFiveTransactions)
                }
            }
        }

    }
}
