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
import com.wizeline.bootcamp.capstone.data.mapper.OrderBookResponseMapper
import com.wizeline.bootcamp.capstone.data.mapper.TickerResponseMapper
import com.wizeline.bootcamp.capstone.data.repo.OrderBookRepo
import com.wizeline.bootcamp.capstone.data.repo.TickerRepo
import com.wizeline.bootcamp.capstone.databinding.FragmentBookDetailsBinding
import com.wizeline.bootcamp.capstone.di.NetworkingModule
import com.wizeline.bootcamp.capstone.domain.OrderBookDTO
import com.wizeline.bootcamp.capstone.domain.TickerDTO
import com.wizeline.bootcamp.capstone.utils.Constants
import com.wizeline.bootcamp.capstone.utils.getCryptoName

class BookDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = BookDetailsFragment()
    }

    private lateinit var binding: FragmentBookDetailsBinding
    private lateinit var askListAdapter: AskListAdapter
    private lateinit var bidListAdapter: BidListAdapter

    private val retrofitClient = NetworkingModule.provideRetrofitClient()
    private val tickerService = NetworkingModule.provideTickerService(retrofitClient)
    private val orderBookService = NetworkingModule.provideOrderBookService(retrofitClient)
    private val tickerRepo: TickerRepo = TickerRepo(tickerService)
    private val orderBookRepo: OrderBookRepo = OrderBookRepo(orderBookService)
    private val tickerMapper = TickerResponseMapper()
    private val orderbookMapper = OrderBookResponseMapper()

    private val viewModel: BookDetailsViewModel by viewModels {
        BookDetailsViewModelFactory(
            this,
            tickerRepo,
            orderBookRepo,
            tickerMapper,
            orderbookMapper,
        )
    }

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
        viewModel.requestOrderBookData(bookId)
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
        binding.book.text = ticker?.id
        binding.cryptoName.text = ticker?.cryptoName.getCryptoName(requireContext())
        binding.bookPrice.text = ticker?.lastPrice
        binding.dayHighLow.text = getDayHighLowText(ticker?.lowPrice, ticker?.highPrice)
        binding.askPriceValue.text = ticker?.ask
        binding.bidPriceValue.text = ticker?.bid
        Glide.with(binding.root).load(ticker?.spriteUrl).into(binding.bookSpriteUrl)
    }

    private fun bindOrderBookData(orderBook: OrderBookDTO?) {
        askListAdapter.submitList(orderBook?.asks)
        bidListAdapter.submitList(orderBook?.bids)
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