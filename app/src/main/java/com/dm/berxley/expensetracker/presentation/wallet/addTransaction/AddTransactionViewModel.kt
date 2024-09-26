package com.dm.berxley.expensetracker.presentation.wallet.addTransaction

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.dm.berxley.expensetracker.data.local.AppDatabase
import com.dm.berxley.expensetracker.domain.models.Merchant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val appDatabase: AppDatabase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val transactionType: String = savedStateHandle.get("transactionType") ?: ""

    private val _addTransactionState = MutableStateFlow(AddItemState())
    val addTransactionState = _addTransactionState.asStateFlow()

//    val merchants = listOf(
//        Merchant(
//            id = 1,
//            name = "Youtube",
//            icon_url = "https://www.youtube.com/s/desktop/7e8b1d69/img/favicon_144x144.png"
//        ),
//        Merchant(
//            id = 2,
//            name = "Upwork",
//            icon_url = "https://e7.pngegg.com/pngimages/257/806/png-clipart-upwork-freelancer-android-android-text-trademark-thumbnail.png"
//        ), Merchant(
//            id = 3,
//            name = "PayPal",
//            icon_url = "https://e7.pngegg.com/pngimages/665/281/png-clipart-logo-computer-icons-paypal-paypal-blue-angle-thumbnail.png"
//        ), Merchant(
//            id = 4,
//            name = "Spotify",
//            icon_url = "https://e7.pngegg.com/pngimages/158/639/png-clipart-spotify-streaming-media-logo-playlist-spotify-app-icon-logo-music-download-thumbnail.png"
//        )
//    )
    init {
        _addTransactionState.update {
            it.copy(expenseType = transactionType)
            //load initial merchant list here
        }


    }




}