package com.dm.berxley.expensetracker.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bill(
    @PrimaryKey val id: Int,
    val merchant_name: String,
    val merchant_icon_url: String,
    val amount: Double,
    val due_date: String,
    val status: String,
)