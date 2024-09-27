package com.dm.berxley.expensetracker.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Merchant(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val icon_url: String
)
