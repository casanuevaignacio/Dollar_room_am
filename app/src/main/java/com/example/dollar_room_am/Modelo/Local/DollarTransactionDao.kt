package com.example.dollar_room_am.Modelo.Local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dollar_room_am.Modelo.Local.DollarTransaction

@Dao
interface DollarTransactionDao {


    @Insert
    suspend fun insertTransaction(transaction: DollarTransaction)

    @Query("SELECT * FROM dollar_transactions ORDER BY timestamp DESC")
    suspend fun getAllTransactions(): List<DollarTransaction>

}