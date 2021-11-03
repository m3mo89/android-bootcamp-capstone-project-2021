package com.wizeline.bootcamp.capstone.ui.bookdetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wizeline.bootcamp.capstone.R

class BookDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = BookDetailsFragment()
    }

    private lateinit var viewModel: BookDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BookDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}