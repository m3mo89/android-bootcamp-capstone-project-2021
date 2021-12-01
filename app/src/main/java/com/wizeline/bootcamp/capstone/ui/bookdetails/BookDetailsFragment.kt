package com.wizeline.bootcamp.capstone.ui.bookdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.wizeline.bootcamp.capstone.R
import com.wizeline.bootcamp.capstone.data.NetworkResult
import com.wizeline.bootcamp.capstone.databinding.FragmentBookDetailsBinding
import com.wizeline.bootcamp.capstone.domain.OrderBookDTO
import com.wizeline.bootcamp.capstone.domain.TickerDTO
import com.wizeline.bootcamp.capstone.utils.Constants
import com.wizeline.bootcamp.capstone.utils.getCryptoName
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = BookDetailsFragment()
    }

    private lateinit var binding: FragmentBookDetailsBinding
    private lateinit var askListAdapter: AskListAdapter
    private lateinit var bidListAdapter: BidListAdapter

    private val viewModel: BookDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
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
            observeOrderBook()
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
    }

    private fun observeTicker() {
        viewModel.ticker.observe(viewLifecycleOwner, { result ->
            when (result) {
                is NetworkResult.Success -> tickerResultSuccess(result.data)
                is NetworkResult.Error -> resultError(result.message)
                is NetworkResult.Loading -> resultLoading()
            }
        })
    }

    private fun observeOrderBook() {
        viewModel.orderBook.observe(viewLifecycleOwner, { result ->
            when (result) {
                is NetworkResult.Success -> orderBookResultSuccess(result.data)
                is NetworkResult.Error -> resultError(result.message)
                is NetworkResult.Loading -> resultLoading()
            }
        })
    }

    private fun tickerResultSuccess(ticker: TickerDTO?) {
        hideLoadingIndicator()
        bindTickerData(ticker)
    }

    private fun orderBookResultSuccess(orderBook: OrderBookDTO?) {
        hideLoadingIndicator()
        bindOrderBookData(orderBook)
    }

    private fun resultError(message: String?) {
        hideLoadingIndicator()
        showErrorMessage(message)
    }

    private fun resultLoading() {
        showLoadingIndicator()
    }

    private fun bindTickerData(ticker: TickerDTO?) {
        ticker?.let {
            binding.book.text = it.id
            binding.cryptoName.text = it.cryptoName.getCryptoName(requireContext())
            binding.bookPrice.text = it.lastPrice
            binding.dayHighLow.text = getDayHighLowText(it.lowPrice, it.highPrice)
            binding.askPriceValue.text = it.ask
            binding.bidPriceValue.text = it.bid

            Glide.with(binding.root).load(it.spriteUrl).into(binding.bookSpriteUrl)
        }
    }

    private fun bindOrderBookData(orderBook: OrderBookDTO?) {
        orderBook?.let {
            askListAdapter.submitList(it.asks)
            bidListAdapter.submitList(it.bids)
        }
    }

    private fun showErrorMessage(message: String?) {
        var messageToDisplay = message.orEmpty()

        if (message.equals(Constants.ERROR_MESSAGE)) {
            messageToDisplay = getString(R.string.error_message)
        }

        Toast.makeText(requireContext(), messageToDisplay, Toast.LENGTH_SHORT).show()
    }

    private fun showLoadingIndicator() {
        if (!binding.loadingIndicator.isVisible)
            binding.loadingIndicator.visibility = View.VISIBLE
    }

    private fun hideLoadingIndicator() {
        if (binding.loadingIndicator.isVisible)
            binding.loadingIndicator.visibility = View.GONE
    }

    private fun getDayHighLowText(lowPrice: String?, highPrice: String?): String {
        if (lowPrice.isNullOrEmpty() || highPrice.isNullOrEmpty()) {
            return ""
        }

        return "$lowPrice - $highPrice"
    }
}
