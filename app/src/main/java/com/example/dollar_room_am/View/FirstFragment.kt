package com.example.dollar_room_am.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.dollar_room_am.Modelo.TransactionRepository
import com.example.dollar_room_am.R
import com.example.dollar_room_am.ViewModel.TransactionViewModel
import com.example.dollar_room_am.ViewModel.TransactionViewModelFactory
import com.example.dollar_room_am.databinding.FragmentFirstBinding

/**
 * A simple [androidx.fragment.app.Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    // Adapter para RecyclerView
    private val adapter= TransactionAdapter()
    // Repository y ViewModel
    private lateinit var repository: TransactionRepository

    private val viewModel : TransactionViewModel by viewModels {

        TransactionViewModelFactory(repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    private fun handleTransaction(type :String){


        val amountText= binding.amountEditText.text.toString()
        if (amountText.isEmpty()) {
            Toast.makeText(requireContext(), "Ingrese un monto", Toast.LENGTH_SHORT).show()
            return
        }


        val amount = amountText.toDouble()
        // guadando en la bd
        viewModel.addTransaction(type,amount)  //agrega al repositorio y actualiza LiveData
        binding.amountEditText.text.clear()
        Toast.makeText(requireContext(), "$type $amount USD registrado", Toast.LENGTH_SHORT).show()
    }









    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}