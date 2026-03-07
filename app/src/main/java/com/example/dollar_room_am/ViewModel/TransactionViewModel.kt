package com.example.dollar_room_am.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.dollar_room_am.Modelo.Local.DollarTransaction
import com.example.dollar_room_am.Modelo.TransactionRepository
import kotlinx.coroutines.launch

class TransactionViewModel(private val repository: TransactionRepository) : ViewModel() {

    // LiveData interna para almacenar la lista de transacciones
    private val _transaction = MutableLiveData<List<DollarTransaction>>()

    // LiveData pública (solo lectura)
    val transactions: LiveData<List<DollarTransaction>> = _transaction

    // Agregar transacción
    fun addTransaction(type: String, amount: Double) {

        val transaction = DollarTransaction(
            type = type,
            amount = amount
        )

        viewModelScope.launch {

            repository.insertTransaction(transaction)

            loadTransactions()
        }
    }

    // Cargar todas las transacciones
    fun loadTransactions() {

        viewModelScope.launch {

            val list = repository.getAllTransactions()

            _transaction.postValue(list)
        }
    }
}

class TransactionViewModelFactory(
    private val repository: TransactionRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(TransactionViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return TransactionViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}