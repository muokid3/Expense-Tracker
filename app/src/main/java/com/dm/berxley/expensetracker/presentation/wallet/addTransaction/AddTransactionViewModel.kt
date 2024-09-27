package com.dm.berxley.expensetracker.presentation.wallet.addTransaction

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dm.berxley.expensetracker.domain.models.Merchant
import com.dm.berxley.expensetracker.domain.models.Transaction
import com.dm.berxley.expensetracker.domain.repositories.ExpenseRoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val expenseRoomRepository: ExpenseRoomRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val transactionType: String = savedStateHandle.get("transactionType") ?: ""

    private val _addTransactionState = MutableStateFlow(AddItemState())
    val addTransactionState = _addTransactionState.asStateFlow()

    val merchants = listOf(
        Merchant(
            id = 1,
            name = "Youtube",
            icon_url = "https://www.youtube.com/s/desktop/7e8b1d69/img/favicon_144x144.png"
        ),
        Merchant(
            id = 2,
            name = "Upwork",
            icon_url = "https://e7.pngegg.com/pngimages/257/806/png-clipart-upwork-freelancer-android-android-text-trademark-thumbnail.png"
        ), Merchant(
            id = 3,
            name = "PayPal",
            icon_url = "https://e7.pngegg.com/pngimages/665/281/png-clipart-logo-computer-icons-paypal-paypal-blue-angle-thumbnail.png"
        ), Merchant(
            id = 4,
            name = "Spotify",
            icon_url = "https://e7.pngegg.com/pngimages/158/639/png-clipart-spotify-streaming-media-logo-playlist-spotify-app-icon-logo-music-download-thumbnail.png"
        )
    )

    init {
        _addTransactionState.update {
            it.copy(expenseType = transactionType)
            //load initial merchant list here
        }

        viewModelScope.launch {
            expenseRoomRepository.getMerchants().collectLatest { merchantList ->
                _addTransactionState.update {
                    it.copy(merchantList = merchantList)
                }
            }
        }

        loadMerchantsFromApi()

    }

    private fun loadMerchantsFromApi() {
        merchants.forEach {
            viewModelScope.launch {
                expenseRoomRepository.upsertMerchant(it)
            }
        }

        //TODO("load from api. This is a dummy")
    }

    fun setSelectedMerchant(merchant: Merchant) {
        _addTransactionState.update {
            it.copy(selectedMerchant = merchant)
        }
    }

    fun resetState(){
        _addTransactionState.update {
            it.copy(isLoading = false, operationSucceeded = false, selectedMerchant = null)
        }
    }

    fun saveTransaction(amount: String, fee: String, date: String, time: String) {
        //upsert to DB
        _addTransactionState.value.selectedMerchant?.let { merchant ->

            //change state to loading
            _addTransactionState.update {
                it.copy(isLoading = true)
            }

            val transaction = Transaction(
                type = _addTransactionState.value.expenseType!!,
                merchant_name = merchant.name,
                merchant_icon_url = merchant.icon_url,
                amount = amount.toDouble(),
                fee = fee.toDouble(),
                total_amount = amount.toDouble() + fee.toDouble(),
                date = date,
                time = time
            )

            viewModelScope.launch {
                expenseRoomRepository.upsertTransaction(transaction)

                _addTransactionState.update {
                    it.copy(isLoading = false, operationSucceeded = true)
                }
            }

        }
    }


}