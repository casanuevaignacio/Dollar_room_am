package com.example.dollar_room_am.Modelo

import com.example.dollar_room_am.Modelo.Local.DollarTransaction
import com.example.dollar_room_am.Modelo.Local.DollarTransactionDao


class TransactionRepository (private val dollarTransactionDao: DollarTransactionDao){

    suspend fun insertTransaction(transaction: DollarTransaction){
        dollarTransactionDao.insertTransaction(transaction)
    }
    suspend fun getAllTransactions(): List<DollarTransaction>{
        return dollarTransactionDao.getAllTransactions()

    }

}