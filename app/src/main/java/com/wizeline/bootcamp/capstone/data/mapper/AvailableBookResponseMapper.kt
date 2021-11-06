package com.wizeline.bootcamp.capstone.data.mapper

import com.wizeline.bootcamp.capstone.data.mock.AvailableBook
import com.wizeline.bootcamp.capstone.domain.Book
import com.wizeline.bootcamp.capstone.utils.Constants
import com.wizeline.bootcamp.capstone.utils.getCryptoName

class AvailableBookResponseMapper : Mapper<AvailableBook, Book> {
    override fun map(from: AvailableBook): Book {
        var spriteUrl =
            "${Constants.CRYPTO_IMAGES_URL}${from.book.substringBefore(Constants.UNDERSCORE_DELIMITER)}"

        return Book(
            id = from.book,
            name = from.book.getCryptoName(),
            spriteUrl = spriteUrl
        )
    }
}