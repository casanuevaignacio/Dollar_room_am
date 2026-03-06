package com.example.dollar_room_am.Modelo.Local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dollar_transactions")
data class DollarTransaction (
    @PrimaryKey(autoGenerate = true)
    val id : Int=0,
    val type : String , // comprar o vender (buy o sell)
    val amount : Double,
    val timestamp: Long = System.currentTimeMillis()

)