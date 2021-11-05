package com.wizeline.bootcamp.capstone.ui.bookdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wizeline.bootcamp.capstone.databinding.ItemAskBinding
import com.wizeline.bootcamp.capstone.domain.Ask

class AskListAdapter : ListAdapter<Ask, AskListAdapter.AskViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AskViewHolder {

        return LayoutInflater.from(parent.context)
            .let { inflater -> ItemAskBinding.inflate(inflater, parent, false) }
            .let { binding -> AskViewHolder(binding) }
    }

    override fun onBindViewHolder(holder: AskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class AskViewHolder(
        private val binding: ItemAskBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(ask: Ask) {
            binding.askAmount.text = ask.amount
            binding.askPrice.text = ask.price
            binding.askTotal.text = ask.total
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Ask>() {
            override fun areItemsTheSame(oldItem: Ask, newItem: Ask): Boolean =
                oldItem.amount == newItem.amount && oldItem.price == newItem.price

            override fun areContentsTheSame(oldItem: Ask, newItem: Ask): Boolean =
                oldItem == newItem
        }
    }
}