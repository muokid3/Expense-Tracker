package com.dm.berxley.expensetracker.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val id: Int,
    val name: String,
    val email: String,
    val wallet_balance: Double,
    val profile_pic_url: String
)
