package com.example.dollar_room_am.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.dollar_room_am.Modelo.Local.AppDatabase
import com.example.dollar_room_am.Modelo.TransactionRepository
import com.example.dollar_room_am.ViewModel.TransactionViewModel
import com.example.dollar_room_am.ViewModel.TransactionViewModelFactory
import com.example.dollar_room_am.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: TransactionViewModel
    private lateinit var repository: TransactionRepository

    private val adapter = TransactionAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Crear base de datos
        val db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java,
            "dollar_database"
        ).build()

        // Crear repository
        repository = TransactionRepository(db.dollarTransactionDao())

        // Crear ViewModel
        val factory = TransactionViewModelFactory(repository)
        viewModel = viewModels<TransactionViewModel> { factory }.value

        // Configurar RecyclerView
        binding.transactionRecyclerView.layoutManager =
            LinearLayoutManager(requireContext())

        binding.transactionRecyclerView.adapter = adapter

        // Observar cambios en LiveData
        viewModel.transactions.observe(viewLifecycleOwner) {

            adapter.submitList(it)

        }

        // Cargar datos al iniciar
        viewModel.loadTransactions()

        // Botón BUY
        binding.buyButton.setOnClickListener {

            handleTransaction("buy")

        }

        // Botón SELL
        binding.sellButton.setOnClickListener {

            handleTransaction("sell")

        }
    }

    private fun handleTransaction(type: String) {

        val amountText = binding.amountEditText.text.toString()

        if (amountText.isEmpty()) {

            Toast.makeText(requireContext(), "Ingrese un monto", Toast.LENGTH_SHORT).show()
            return
        }

        val amount = amountText.toDouble()

        viewModel.addTransaction(type, amount)

        binding.amountEditText.text.clear()

        Toast.makeText(
            requireContext(),
            "$type $amount USD registrado",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}