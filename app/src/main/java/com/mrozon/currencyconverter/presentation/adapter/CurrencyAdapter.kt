package com.mrozon.currencyconverter.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mrozon.currencyconverter.databinding.ItemCurrencyBinding
import com.mrozon.currencyconverter.presentation.model.CurrencyUI

class CurrencyAdapter(
    private var click: ((CurrencyUI) -> Unit)
): ListAdapter<CurrencyUI, RecyclerView.ViewHolder>(CurrencyDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        CurrencyViewHolder(
            ItemCurrencyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            click
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currency = getItem(position)
        (holder as CurrencyViewHolder).bind(currency)
    }

    class CurrencyViewHolder(
        private val binding: ItemCurrencyBinding,
        private var click: ((CurrencyUI) -> Unit)
    ):  RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CurrencyUI) {
            binding.apply {
                currencyUI = item
                executePendingBindings()
            }
            binding.setClickListener {
                click(item)
            }
        }
    }

}

private class CurrencyDiffCallback : DiffUtil.ItemCallback<CurrencyUI>() {

    override fun areItemsTheSame(oldItem: CurrencyUI, newItem: CurrencyUI): Boolean {
        return oldItem.charCode == newItem.charCode
    }

    override fun areContentsTheSame(oldItem: CurrencyUI, newItem: CurrencyUI): Boolean {
        return oldItem == newItem
    }
}
