package com.wizeline.bootcamp.capstone.ui.booklist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wizeline.bootcamp.capstone.data.mock.mockBooks
import com.wizeline.bootcamp.capstone.databinding.FragmentBookListBinding

// view holders' height should take 1/7 of the screen
private const val VIEW_HOLDER_SCREEN_PROPORTION = 1.0 / 7.0

class BookListFragment : Fragment() {

    companion object {
        fun newInstance() = BookListFragment()
    }

    private var _binding: FragmentBookListBinding? = null
    private val binding: FragmentBookListBinding
        get() = _binding!!

    private lateinit var navController: NavController

    private lateinit var bookListAdapter: BookListAdapter

    private lateinit var viewModel: BookListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentBookListBinding
            .inflate(layoutInflater, container, false)
            .apply { _binding = this }
            .root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BookListViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bookListAdapter = BookListAdapter { bookId ->
            BookListFragmentDirections
                .toBookDetailsFragment(bookId)
                .let { navController.navigate(it) }
        }
        navController = findNavController()
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

        bookListAdapter.submitList(mockBooks)
    }
}