package com.mrozon.currencyconverter.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mrozon.currencyconverter.databinding.FragmentConverterCurrencyBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConverterCurrencyFragment: Fragment() {
    private val viewModel: ConverterCurrencyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentConverterCurrencyBinding.inflate(inflater, container, false)
        return binding.root
    }

}
