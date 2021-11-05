package com.wizeline.bootcamp.capstone.ui.bookdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.wizeline.bootcamp.capstone.databinding.FragmentBookDetailsBinding

class BookDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = BookDetailsFragment()
    }

    private lateinit var binding: FragmentBookDetailsBinding
    private lateinit var askListAdapter: AskListAdapter
    private lateinit var bidListAdapter: BidListAdapter
    private val viewModel: BookDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentBookDetailsBinding
            .inflate(layoutInflater, container, false)
            .apply { binding = this }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val bookId = requireArguments().getString("book_id")

        if (bookId != null) {
            initAdapters()
            requestData(bookId)
            observeTicker()
            observeAskList()
            observeBidList()
        }
    }

    private fun initAdapters() {
        initAskListAdapter()
        initBidListAdapter()
    }

    private fun initAskListAdapter() {
        askListAdapter = AskListAdapter()

        binding.askDetailsList.run {
            adapter = askListAdapter
            layoutManager = object : LinearLayoutManager(requireContext(), HORIZONTAL, false) {
            }
        }
    }

    private fun initBidListAdapter() {
        bidListAdapter = BidListAdapter()

        binding.bidDetailsList.run {
            adapter = bidListAdapter
            layoutManager = object : LinearLayoutManager(requireContext(), HORIZONTAL, false) {
            }
        }
    }

    private fun requestData(bookId: String) {
        viewModel.requestData(bookId)
        viewModel.requestOrderBookData(bookId)
    }

    private fun observeTicker() {
        viewModel.ticker.observe(viewLifecycleOwner, {
            binding.book.text = it?.id
            binding.cryptoName.text = it?.cryptoName
            binding.bookPrice.text = it?.lastPrice
            binding.dayHighLow.text = "${it?.lowPrice} - ${it?.highPrice}"
            binding.askPriceValue.text = it?.ask
            binding.bidPriceValue.text = it?.bid
            Glide.with(binding.root).load(it?.spriteUrl).into(binding.bookSpriteUrl)
        })
    }

    private fun observeAskList() {
        viewModel.askList.observe(viewLifecycleOwner, {
            askListAdapter.submitList(it)
        })
    }

    private fun observeBidList() {
        viewModel.bidList.observe(viewLifecycleOwner, {
            bidListAdapter.submitList(it)
        })
    }
}