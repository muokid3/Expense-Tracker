package com.dm.berxley.expensetracker.presentation.wallet.addTransaction

import com.dm.berxley.expensetracker.domain.models.Merchant

data class AddItemState(
    val isLoading: Boolean = false,
    val operationSucceeded: Boolean = false,
    val expenseType: String? = null,
    val selectedMerchant: Merchant? = null,
    var merchantList: List<Merchant> = emptyList()

)
