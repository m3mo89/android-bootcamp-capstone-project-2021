package com.wizeline.bootcamp.capstone

import com.wizeline.bootcamp.capstone.data.api.AvailableBook
import com.wizeline.bootcamp.capstone.data.api.AvailableBooksResponse
import com.wizeline.bootcamp.capstone.data.remote.BookRemoteDataSource
import com.wizeline.bootcamp.capstone.data.services.AvailableBooksService
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class BookRemoteDataSourceTest {
    private lateinit var bookRemoteDataSource: BookRemoteDataSource

    @Mock
    private lateinit var availableBooksService: AvailableBooksService
    private val books = listOf(
        AvailableBook(
            book = "btc_mxn", minimumAmount = ".003",
            maximumAmount = "1000.00", minimumPrice = "100.00",
            maximumPrice = "1000000.00", minimumValue = "25.00", maximumValue = "1000000.00"
        ),
        AvailableBook(
            book = "eth_mxn", minimumAmount = ".003",
            maximumAmount = "1000.00", minimumPrice = "100.00",
            maximumPrice = "1000000.00", minimumValue = "25.00", maximumValue = "1000000.00"
        )
    )

    private val response = AvailableBooksResponse(success = true, payload = books)

    @Before
    fun setUp() {
        availableBooksService = mock()
        bookRemoteDataSource = BookRemoteDataSource(availableBooksService)
    }

    @Test
    fun `getAvailableBooks is completed without errors`() {
        // Given
        val testObserver = TestObserver<AvailableBooksResponse>()
        whenever(availableBooksService.getAvailableBooks()) doReturn Observable.just(response)

        // When
        val books = bookRemoteDataSource.getAvailableBooks()

        // Then
        books.subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
    }
}
