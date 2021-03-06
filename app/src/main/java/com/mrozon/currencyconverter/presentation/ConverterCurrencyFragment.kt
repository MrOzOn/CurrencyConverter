package com.mrozon.currencyconverter.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrozon.currencyconverter.databinding.FragmentConverterCurrencyBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.text.DecimalFormatSymbols

@AndroidEntryPoint
class ConverterCurrencyFragment: Fragment() {
    private val viewModel: ConverterCurrencyViewModel by viewModels()

    private var binding: FragmentConverterCurrencyBinding? = null
    private val currencyAdapter by lazy {
        CurrencyAdapter { currencyUI ->
            viewModel.selectCurrency(currencyUI.charCode)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConverterCurrencyBinding.inflate(inflater, container, false)
        binding?.rvCurrencies?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = currencyAdapter
        }
        binding?.buttonComma?.text = DecimalFormatSymbols.getInstance().decimalSeparator.toString()
        binding?.setClickListener { view ->
            val button = view as Button
            viewModel.input(button.text.toString())
        }
        return binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { list ->
                currencyAdapter.submitList(list)
            }
        }

    }



}
