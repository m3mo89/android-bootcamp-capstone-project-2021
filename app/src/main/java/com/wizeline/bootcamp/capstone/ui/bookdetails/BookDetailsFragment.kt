package com.wizeline.bootcamp.capstone.ui.bookdetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.wizeline.bootcamp.capstone.R
import com.wizeline.bootcamp.capstone.data.mock.mockAsks
import com.wizeline.bootcamp.capstone.data.mock.mockBids
import com.wizeline.bootcamp.capstone.data.mock.mockTicker
import com.wizeline.bootcamp.capstone.databinding.FragmentBookDetailsBinding
import com.wizeline.bootcamp.capstone.databinding.FragmentBookListBinding
import com.wizeline.bootcamp.capstone.ui.booklist.BookListAdapter

class BookDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = BookDetailsFragment()
    }

    private var _binding: FragmentBookDetailsBinding? = null
    private val binding: FragmentBookDetailsBinding
        get() = _binding!!

    private lateinit var askListAdapter: AskListAdapter

    private lateinit var bidListAdapter: BidListAdapter

    private lateinit var viewModel: BookDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentBookDetailsBinding
            .inflate(layoutInflater, container, false)
            .apply { _binding = this }
            .root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BookDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        askListAdapter = AskListAdapter()
        bidListAdapter = BidListAdapter()

        binding.askDetailsList.run {
            adapter = askListAdapter
            layoutManager = object : LinearLayoutManager(requireContext(), HORIZONTAL, false){

            }
        }

        binding.bidDetailsList.run {
            adapter = bidListAdapter
            layoutManager = object : LinearLayoutManager(requireContext(), HORIZONTAL, false){

            }
        }

        askListAdapter.submitList(mockAsks)
        bidListAdapter.submitList(mockBids)

        binding.book.text = mockTicker.id
        binding.cryptoName.text = mockTicker.cryptoName
        binding.bookPrice.text = mockTicker.lastPrice
        binding.dayHighLow.text = "${mockTicker.lowPrice} - ${mockTicker.highPrice}"
        binding.askPriceValue.text = mockTicker.ask
        binding.bidPriceValue.text = mockTicker.bid
        Glide.with(binding.root).load(mockTicker.spriteUrl).into(binding.bookSpriteUrl)
    }

}