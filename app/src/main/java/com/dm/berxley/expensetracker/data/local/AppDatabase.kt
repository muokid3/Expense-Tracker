package com.dm.berxley.expensetracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dm.berxley.expensetracker.domain.models.Bill
import com.dm.berxley.expensetracker.domain.models.Merchant
import com.dm.berxley.expensetracker.domain.models.Transaction
import com.dm.berxley.expensetracker.domain.models.User

@Database(entities = [User::class, Transaction::class, Merchant::class, Bill::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    //DAOs here
    abstract val appDao: AppDao


    companion object {
        const val ROOM_DB_NAME = "expense_tracker_name"
    }
}