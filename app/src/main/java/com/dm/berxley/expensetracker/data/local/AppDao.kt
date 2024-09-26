package com.dm.berxley.expensetracker.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.dm.berxley.expensetracker.domain.models.Bill
import com.dm.berxley.expensetracker.domain.models.Merchant
import com.dm.berxley.expensetracker.domain.models.Transaction
import com.dm.berxley.expensetracker.domain.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Upsert
    suspend fun upsertMerchant(merchant: Merchant)

    @Query("SELECT * FROM Merchant")
    fun getAllMerchants(): Flow<List<Merchant>>

    @Query("SELECT * FROM Merchant WHERE id = :id")
    fun getMerchantById(id: Int): Merchant

    @Upsert
    suspend fun upsertBill(bill: Bill)

    @Query("SELECT * FROM Bill")
    fun getAllBills(): Flow<List<Bill>>

    @Query("SELECT * FROM Bill WHERE id = :id")
    fun getBillById(id: Int): Bill


    @Upsert
    suspend fun upsertTransaction(transaction: Transaction)

    @Query("SELECT * FROM `Transaction`")
    fun getAllTransactions(): Flow<List<Transaction>>

    @Query("SELECT * FROM `Transaction` WHERE id = :id")
    fun getTransactionById(id: Int): Transaction

    @Query("SELECT * FROM User LIMIT 1")
    fun getUser(): Flow<User>



}