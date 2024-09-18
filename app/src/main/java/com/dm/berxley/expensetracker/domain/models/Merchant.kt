package com.dm.berxley.expensetracker.domain.models

import androidx.room.PrimaryKey

data class Merchant(
    @PrimaryKey val id: Int,
    val name: String,
    val icon_url: String
)
