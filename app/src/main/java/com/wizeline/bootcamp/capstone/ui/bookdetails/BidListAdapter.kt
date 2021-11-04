package com.wizeline.bootcamp.capstone.ui.bookdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wizeline.bootcamp.capstone.databinding.ItemBidBinding
import com.wizeline.bootcamp.capstone.domain.Bid

class BidListAdapter : ListAdapter<Bid, BidListAdapter.BidViewHolder>(DIFF_CALLBACK)  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BidViewHolder {
        return LayoutInflater.from(parent.context)
            .let { inflater -> ItemBidBinding.inflate(inflater, parent, false) }
            .let { binding -> BidViewHolder(binding) }
    }

    override fun onBindViewHolder(holder: BidViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class BidViewHolder(
        private val binding: ItemBidBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(bid: Bid) {
            binding.bidAmount.text = bid.amount
            binding.bidPrice.text = bid.price
            binding.bidTotal.text = bid.total
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Bid>() {
            override fun areItemsTheSame(oldItem: Bid, newItem: Bid): Boolean =
                oldItem.amount == newItem.amount && oldItem.price == newItem.price

            override fun areContentsTheSame(oldItem: Bid, newItem: Bid): Boolean =
                oldItem == newItem
        }
    }
}