package com.dm.berxley.expensetracker.data.repositories

import com.dm.berxley.expensetracker.data.local.AppDatabase
import com.dm.berxley.expensetracker.domain.models.Bill
import com.dm.berxley.expensetracker.domain.models.Merchant
import com.dm.berxley.expensetracker.domain.models.Transaction
import com.dm.berxley.expensetracker.domain.repositories.ExpenseRoomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExpenseRoomRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase

): ExpenseRoomRepository {
    override suspend fun upsertMerchant(merchant: Merchant) {
        appDatabase.appDao.upsertMerchant(merchant)
    }

    override suspend fun getMerchantById(id: Int): Merchant {
        return appDatabase.appDao.getMerchantById(id)
    }

    override fun getMerchants(): Flow<List<Merchant>> {
        return appDatabase.appDao.getAllMerchants()
    }

    override suspend fun upsertBill(bill: Bill) {
        appDatabase.appDao.upsertBill(bill)
    }

    override suspend fun getBillById(id: Int): Bill {
        return appDatabase.appDao.getBillById(id)
    }

    override fun getBills(): Flow<List<Bill>> {
       return appDatabase.appDao.getAllBills()
    }

    override suspend fun upsertTransaction(transaction: Transaction) {
        appDatabase.appDao.upsertTransaction(transaction = transaction)
    }

    override suspend fun getTransactionById(id: Int): Transaction {
        return appDatabase.appDao.getTransactionById(id)
    }

    override fun getTransactions(): Flow<List<Transaction>> {
        return appDatabase.appDao.getAllTransactions()
    }
}