package com.wizeline.bootcamp.capstone.ui.bookdetails

import android.graphics.Color
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
import com.google.android.material.snackbar.Snackbar
import com.wizeline.bootcamp.capstone.R
import com.wizeline.bootcamp.capstone.data.NetworkResult
import com.wizeline.bootcamp.capstone.databinding.FragmentBookDetailsBinding
import com.wizeline.bootcamp.capstone.domain.OrderBookDTO
import com.wizeline.bootcamp.capstone.domain.TickerDTO
import com.wizeline.bootcamp.capstone.utils.Constants
import com.wizeline.bootcamp.capstone.utils.checkForInternet
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
    private var _ticker: TickerDTO? = null
    private var _orderBook: OrderBookDTO? = null

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
            requestData(bookId, view)
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

    private fun requestData(bookId: String, view: View) {
        if (checkForInternet(requireContext())) {
            viewModel.requestTickerRemoteData(bookId)
            viewModel.requestOrderBookRemoteData(bookId)
        } else {
            viewModel.requestTickerLocalData(bookId)
            viewModel.requestOrderBookLocalData(bookId)

            showSnackBar(bookId, view)
        }
    }

    private fun showSnackBar(bookId: String, view: View)
    {
        val noInternetConnectionSnackBar = Snackbar.make(view, R.string.error_internet_connection, Snackbar.LENGTH_INDEFINITE)
        noInternetConnectionSnackBar.setActionTextColor(Color.WHITE)
        noInternetConnectionSnackBar.setAction(R.string.try_again) {
            requestData(bookId, view)
        }
        noInternetConnectionSnackBar.show()
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
        _ticker = ticker
        hideLoadingIndicator()
        bindTickerData(ticker)
        showErrorNoDataMessage()
    }

    private fun orderBookResultSuccess(orderBook: OrderBookDTO?) {
        _orderBook = orderBook
        hideLoadingIndicator()
        bindOrderBookData(orderBook)
        showErrorNoDataMessage()
    }

    private fun resultError(message: String?) {
        hideLoadingIndicator()
        showErrorMessage(message)
    }

    private fun resultLoading() {
        hideNoDataMessage()
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

    private fun showErrorNoDataMessage() {
        if (_ticker?.id?.isEmpty() == true || (_orderBook?.asks?.isEmpty() == true && _orderBook?.bids?.isEmpty() == true))
            showNoDataMessage()
        else
            hideNoDataMessage()
    }

    private fun showErrorMessage(message: String?) {
        var messageToDisplay = message.orEmpty()

        if (message.equals(Constants.ERROR_MESSAGE)) {
            messageToDisplay = getString(R.string.error_message)
        }

        Toast.makeText(requireContext(), messageToDisplay, Toast.LENGTH_SHORT).show()
    }

    private fun showNoDataMessage()
    {
        binding.noDataDetailMessage.visibility = View.VISIBLE
        binding.cryptoName.visibility = View.GONE
        binding.bookSpriteUrl.visibility = View.GONE
        binding.book.visibility = View.GONE
        binding.bookPrice.visibility = View.GONE
        binding.dayHighLow.visibility = View.GONE
        binding.askPriceLabel.visibility = View.GONE
        binding.askPriceValue.visibility = View.GONE
        binding.separator.visibility = View.GONE
        binding.bidPriceLabel.visibility = View.GONE
        binding.bidPriceValue.visibility = View.GONE
        binding.buyOrders.visibility = View.GONE
        binding.sellOrders.visibility = View.GONE
        binding.askDetailsList.visibility = View.GONE
        binding.askDetailsListHeader.root.visibility = View.GONE
        binding.bidDetailsList.visibility = View.GONE
        binding.bidDetailsListHeader.root.visibility = View.GONE
    }

    private fun hideNoDataMessage()
    {
        binding.noDataDetailMessage.visibility = View.GONE
        binding.cryptoName.visibility = View.VISIBLE
        binding.bookSpriteUrl.visibility = View.VISIBLE
        binding.book.visibility = View.VISIBLE
        binding.bookPrice.visibility = View.VISIBLE
        binding.dayHighLow.visibility = View.VISIBLE
        binding.askPriceLabel.visibility = View.VISIBLE
        binding.askPriceValue.visibility = View.VISIBLE
        binding.separator.visibility = View.VISIBLE
        binding.bidPriceLabel.visibility = View.VISIBLE
        binding.bidPriceValue.visibility = View.VISIBLE
        binding.buyOrders.visibility = View.VISIBLE
        binding.sellOrders.visibility = View.VISIBLE
        binding.askDetailsList.visibility = View.VISIBLE
        binding.askDetailsListHeader.root.visibility = View.VISIBLE
        binding.bidDetailsList.visibility = View.VISIBLE
        binding.bidDetailsListHeader.root.visibility = View.VISIBLE
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
