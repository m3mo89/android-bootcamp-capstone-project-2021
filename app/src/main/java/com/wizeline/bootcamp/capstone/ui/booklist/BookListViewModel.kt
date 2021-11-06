package com.wizeline.bootcamp.capstone.ui.booklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.bootcamp.capstone.data.mapper.AvailableBookResponseMapper
import com.wizeline.bootcamp.capstone.data.repo.AvailableBooksRepo
import com.wizeline.bootcamp.capstone.domain.Book
import kotlinx.coroutines.launch

class BookListViewModel(
    private val availableBooksRepo: AvailableBooksRepo,
    private val booksMapper: AvailableBookResponseMapper,
) : ViewModel() {
    private var _bookList: MutableLiveData<List<Book>?> = MutableLiveData<List<Book>?>()
    val bookList: LiveData<List<Book>?> = _bookList

    fun requestData() {
        viewModelScope.launch {
            val response = availableBooksRepo.getAvailableBooks().body()
            val success = response?.success ?: false

            if (success) {
                val payload = response?.payload

                if (payload != null) {
                    val listOfBooks = booksMapper.mapList(payload)

                    _bookList.postValue(listOfBooks)
                }
            }
        }
    }
}