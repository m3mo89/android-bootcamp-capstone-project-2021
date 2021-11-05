package com.wizeline.bootcamp.capstone.ui.booklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wizeline.bootcamp.capstone.databinding.FragmentBookListBinding

// view holders' height should take 1/7 of the screen
private const val VIEW_HOLDER_SCREEN_PROPORTION = 1.0 / 7.0

class BookListFragment : Fragment() {

    companion object {
        fun newInstance() = BookListFragment()
    }

    private lateinit var binding: FragmentBookListBinding
    private lateinit var navController: NavController
    private lateinit var bookListAdapter: BookListAdapter
    private val viewModel: BookListViewModel by viewModels()

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
        requestData()
        observeBookList()
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

    private fun requestData() {
        viewModel.requestData()
    }

    private fun observeBookList() {
        viewModel.bookList.observe(viewLifecycleOwner, {
            bookListAdapter.submitList(it)
        })
    }
}