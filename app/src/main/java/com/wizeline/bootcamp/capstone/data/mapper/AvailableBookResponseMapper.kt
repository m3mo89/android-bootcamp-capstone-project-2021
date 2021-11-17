package com.wizeline.bootcamp.capstone.data.mapper

import com.wizeline.bootcamp.capstone.data.mock.AvailableBook
import com.wizeline.bootcamp.capstone.domain.BookDTO
import com.wizeline.bootcamp.capstone.utils.Constants

class AvailableBookResponseMapper : Mapper<AvailableBook, BookDTO> {
    override fun map(from: AvailableBook): BookDTO {
        val spriteUrl =
            "${Constants.CRYPTO_IMAGES_URL}${from.book.substringBefore(Constants.UNDERSCORE_DELIMITER)}"

        return BookDTO(
            id = from.book,
            name = from.book,
            spriteUrl = spriteUrl
        )
    }
}