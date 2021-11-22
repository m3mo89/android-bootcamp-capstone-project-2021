package com.wizeline.bootcamp.capstone.data.mapper

import com.wizeline.bootcamp.capstone.data.entities.Book
import com.wizeline.bootcamp.capstone.data.mock.AvailableBook
import com.wizeline.bootcamp.capstone.domain.BookDTO
import com.wizeline.bootcamp.capstone.utils.Constants

fun AvailableBook.toLocal() = Book(
    book = book,
    minimumAmount = minimumAmount,
    maximumAmount = maximumAmount,
    minimumPrice = minimumPrice,
    maximumPrice = maximumPrice,
    minimumValue = minimumValue,
    maximumValue = maximumValue
)

fun Book.fromLocal() = BookDTO(
    id = book,
    name = book,
    spriteUrl = book.let {
        "${Constants.CRYPTO_IMAGES_URL}${book.substringBefore(Constants.UNDERSCORE_DELIMITER)}"
    }
)