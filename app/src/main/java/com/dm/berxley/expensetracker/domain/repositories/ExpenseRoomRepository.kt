package com.dm.berxley.expensetracker.domain.repositories

import com.dm.berxley.expensetracker.domain.models.Bill
import com.dm.berxley.expensetracker.domain.models.Merchant
import com.dm.berxley.expensetracker.domain.models.Transaction
import kotlinx.coroutines.flow.Flow

interface ExpenseRoomRepository {
    //room
    suspend fun upsertMerchant(merchant: Merchant)
    suspend fun getMerchantById(id: Int): Merchant
    fun getMerchants(): Flow<List<Merchant>>


    suspend fun upsertBill(bill: Bill)
    suspend fun getBillById(id: Int): Bill
    fun getBills(): Flow<List<Bill>>

    suspend fun upsertTransaction(transaction: Transaction)
    suspend fun getTransactionById(id: Int): Transaction
    fun getTransactions(): Flow<List<Transaction>>

    fun getTopFiveTransactions(): Flow<List<Transaction>>



}