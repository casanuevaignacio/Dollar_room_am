package com.example.dollar_room_am.View

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dollar_room_am.Modelo.Local.DollarTransaction
import com.example.dollar_room_am.databinding.ItemDollarBinding
import java.text.SimpleDateFormat
import java.util.Locale

class TransactionAdapter :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    // Lista interna
    private var transactions = listOf<DollarTransaction>()

    // Actualizar lista
    fun submitList(newList: List<DollarTransaction>) {
        transactions = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionViewHolder {

        val binding = ItemDollarBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TransactionViewHolder,
        position: Int
    ) {

        holder.bind(transactions[position])
    }

    override fun getItemCount(): Int = transactions.size

    inner class TransactionViewHolder(
        private val binding: ItemDollarBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(transaction: DollarTransaction) {

            // Tipo y monto
            binding.textTypeAmount.text =
                "${transaction.type.uppercase()} : ${transaction.amount} USD"

            // Fecha
            val formattedDate = SimpleDateFormat(
                "dd/MM/yyyy HH:mm",
                Locale.getDefault()
            ).format(transaction.timestamp)

            binding.textTimestamp.text = formattedDate
        }
    }
}