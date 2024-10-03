package com.dm.berxley.expensetracker.presentation.wallet

import com.dm.berxley.expensetracker.domain.models.Bill
import com.dm.berxley.expensetracker.domain.models.Transaction

data class WalletState(
    val transactionsList: List<Transaction> = emptyList(),
    val isLoadingTransactions: Boolean = false,
    val billsList: List<Bill> = emptyList(),
    val isLoadingBills: Boolean = false,

    )
