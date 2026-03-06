package com.example.dollar_room_am.View


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dollar_room_am.Modelo.Local.DollarTransaction

import com.example.dollar_room_am.databinding.ItemDollarBinding
import java.text.SimpleDateFormat
import java.util.Locale


class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {


    // Lista interna de transacciones
    private var transaction = listOf<DollarTransaction>()


    // Método para actualizar la lista desde fuera del adapter

    fun submitList(newList: List<DollarTransaction>){

        transaction= newList
        notifyDataSetChanged() // refrescar la lista
    }

    // Inflamos el layout usando ViewBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding = ItemDollarBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder,
                                  position: Int
    ) {
        holder.bin(transaction[position])
    }

    override fun getItemCount(): Int = transaction.size

    inner class TransactionViewHolder ( private  val binding: ItemDollarBinding):
        RecyclerView.ViewHolder(binding.root){

        fun bin( transaction: DollarTransaction){

            // Mostramos tipo y monto
            binding.textTypeAmount.text ="${transaction.type} - ${transaction.amount} USD"

            // Formateamos timestamp a "dd/MM/yyyy HH:mm"
            val formatteDate = SimpleDateFormat(
                "dd/MM/yyyy HH:mm",
                Locale.getDefault()
            ).format(transaction.timestamp)

            binding.textTimestamp.text = formatteDate
        }

    }

}