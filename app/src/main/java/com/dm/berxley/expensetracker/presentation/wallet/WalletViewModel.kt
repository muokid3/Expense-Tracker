package com.dm.berxley.expensetracker.presentation.wallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dm.berxley.expensetracker.domain.repositories.ExpenseRoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val expenseRoomRepository: ExpenseRoomRepository
) : ViewModel() {

    private val _selectedIndex = MutableStateFlow(0)
    val selectedIndex = _selectedIndex.asStateFlow()

    private val _walletState = MutableStateFlow(WalletState())
    val walletState = _walletState.asStateFlow()

    init {
        loadTransactions()
        loadBills()
    }


    private fun loadTransactions() {
        _walletState.update {
            it.copy(isLoadingTransactions = true)
        }

        viewModelScope.launch {
            expenseRoomRepository.getTransactions().collectLatest { transactionList ->
                _walletState.update {
                    it.copy(transactionsList = transactionList, isLoadingTransactions = false)
                }
            }
        }

    }

    private fun loadBills() {


    }

    fun setSelectedIndex(index: Int) {
        _selectedIndex.value = index
    }
}