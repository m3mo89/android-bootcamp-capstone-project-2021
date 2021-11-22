package com.wizeline.bootcamp.capstone.ui.booklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wizeline.bootcamp.capstone.R
import com.wizeline.bootcamp.capstone.data.NetworkResult
import com.wizeline.bootcamp.capstone.databinding.FragmentBookListBinding
import com.wizeline.bootcamp.capstone.di.NetworkingModule
import com.wizeline.bootcamp.capstone.domain.BookDTO
import com.wizeline.bootcamp.capstone.utils.Constants.Companion.ERROR_MESSAGE

// view holders' height should take 1/7 of the screen
private const val VIEW_HOLDER_SCREEN_PROPORTION = 1.0 / 7.0

class BookListFragment : Fragment() {

    companion object {
        fun newInstance() = BookListFragment()
    }

    private lateinit var binding: FragmentBookListBinding
    private lateinit var navController: NavController
    private lateinit var bookListAdapter: BookListAdapter

    private val retrofitClient = NetworkingModule.provideRetrofitClient()
    private val availableBooksService =
        NetworkingModule.provideAvailableBooksService(retrofitClient)
    private val database = NetworkingModule.provideDatabase(NetworkingModule.application)
    private val bookDAO = NetworkingModule.provideBookDao(database)
    private val bookRemoteDataSource =
        NetworkingModule.provideBookRemoteDataSource(availableBooksService)
    private val availableBooksRepo =
        NetworkingModule.provideRepository(bookRemoteDataSource, bookDAO)

    private val viewModel: BookListViewModel by viewModels {
        BookListViewModelFactory(this, availableBooksRepo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentBookListBinding
            .inflate(layoutInflater, container, false)
            .apply { binding = this }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initAdapter()
        observeResult()
    }

    private fun initAdapter() {
        navController = findNavController()
        bookListAdapter = BookListAdapter { bookId ->
            BookListFragmentDirections
                .toBookDetailsFragment(bookId)
                .let { navController.navigate(it) }
        }

        binding.bookList.run {
            adapter = bookListAdapter
            layoutManager = object : LinearLayoutManager(requireContext()) {
                override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
                    lp?.height = (height * VIEW_HOLDER_SCREEN_PROPORTION).toInt()
                    return true
                }
            }
            setHasFixedSize(true)
        }
    }

    private fun observeResult() {
        viewModel.result.observe(viewLifecycleOwner, { result ->
            when (result) {
                is NetworkResult.Success -> resultSuccess(result.data)
                is NetworkResult.Error -> resultError(result.message)
                is NetworkResult.Loading -> resultLoading()
            }
        })
    }

    private fun resultSuccess(books: List<BookDTO>?) {
        hideLoadingIndicator()
        bindData(books)
    }

    private fun resultError(message: String?) {
        hideLoadingIndicator()
        showErrorMessage(message)
    }

    private fun resultLoading() {
        showLoadingIndicator()
    }

    private fun bindData(books: List<BookDTO>?) {
        bookListAdapter.submitList(books)
    }

    private fun showErrorMessage(message: String?) {
        var messageToDisplay = message.orEmpty()

        if (message.equals(ERROR_MESSAGE)) {
            messageToDisplay = getString(R.string.error_message)
        }

        Toast.makeText(requireContext(), messageToDisplay, Toast.LENGTH_SHORT).show()
    }

    private fun showLoadingIndicator() {
        binding.loadingIndicator.visibility = View.VISIBLE
    }

    private fun hideLoadingIndicator() {
        binding.loadingIndicator.visibility = View.GONE
    }
}