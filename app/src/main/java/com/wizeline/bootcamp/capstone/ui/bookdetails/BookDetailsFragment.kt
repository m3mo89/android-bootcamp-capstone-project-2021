package com.wizeline.bootcamp.capstone.ui.bookdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.wizeline.bootcamp.capstone.data.mock.mockAsks
import com.wizeline.bootcamp.capstone.data.mock.mockBids
import com.wizeline.bootcamp.capstone.data.mock.mockTicker
import com.wizeline.bootcamp.capstone.databinding.FragmentBookDetailsBinding

class BookDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = BookDetailsFragment()
    }

    private lateinit var binding: FragmentBookDetailsBinding

    private lateinit var askListAdapter: AskListAdapter

    private lateinit var bidListAdapter: BidListAdapter

    private lateinit var viewModel: BookDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentBookDetailsBinding
            .inflate(layoutInflater, container, false)
            .apply { binding = this }
            .root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BookDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configAdapters()
        bindTickerValues()
    }

    private fun configAdapters() {
        configAskListAdapter()
        configBidListAdapter()
    }

    private fun bindTickerValues() {
        binding.book.text = mockTicker.id
        binding.cryptoName.text = mockTicker.cryptoName
        binding.bookPrice.text = mockTicker.lastPrice
        binding.dayHighLow.text = "${mockTicker.lowPrice} - ${mockTicker.highPrice}"
        binding.askPriceValue.text = mockTicker.ask
        binding.bidPriceValue.text = mockTicker.bid
        Glide.with(binding.root).load(mockTicker.spriteUrl).into(binding.bookSpriteUrl)
    }

    private fun configAskListAdapter() {
        askListAdapter = AskListAdapter()

        binding.askDetailsList.run {
            adapter = askListAdapter
            layoutManager = object : LinearLayoutManager(requireContext(), HORIZONTAL, false) {
            }
        }

        askListAdapter.submitList(mockAsks)
    }

    private fun configBidListAdapter() {
        bidListAdapter = BidListAdapter()

        binding.bidDetailsList.run {
            adapter = bidListAdapter
            layoutManager = object : LinearLayoutManager(requireContext(), HORIZONTAL, false) {
            }
        }

        bidListAdapter.submitList(mockBids)
    }
}