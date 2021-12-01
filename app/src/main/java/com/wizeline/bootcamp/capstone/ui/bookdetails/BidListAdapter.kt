package com.wizeline.bootcamp.capstone.ui.bookdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wizeline.bootcamp.capstone.databinding.ItemBidBinding
import com.wizeline.bootcamp.capstone.domain.BidDTO

class BidListAdapter : ListAdapter<BidDTO, BidListAdapter.BidViewHolder>(DIFF_CALLBACK) {
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

        fun bind(bid: BidDTO) {
            binding.bidAmount.text = bid.amount
            binding.bidPrice.text = bid.price
            binding.bidTotal.text = bid.total
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BidDTO>() {
            override fun areItemsTheSame(oldItem: BidDTO, newItem: BidDTO): Boolean =
                oldItem.amount == newItem.amount && oldItem.price == newItem.price

            override fun areContentsTheSame(oldItem: BidDTO, newItem: BidDTO): Boolean =
                oldItem == newItem
        }
    }
}
