package com.dm.berxley.expensetracker.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: String,
    val merchant_name: String,
    val merchant_icon_url: String,
    val amount: Double,
    val fee: Double,
    val total_amount: Double,
    val date: String,
    val time: String,
)