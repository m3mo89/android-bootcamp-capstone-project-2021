package com.wizeline.bootcamp.capstone.data.mapper

import com.wizeline.bootcamp.capstone.data.entities.BookEntity
import com.wizeline.bootcamp.capstone.data.mock.AvailableBook
import com.wizeline.bootcamp.capstone.domain.BookDTO
import com.wizeline.bootcamp.capstone.utils.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AvailableBookResponseMapper @Inject constructor() : Mapper<BookEntity, BookDTO> {
    override fun map(from: BookEntity): BookDTO {
        val spriteUrl =
            "${Constants.CRYPTO_IMAGES_URL}${from.book.substringBefore(Constants.UNDERSCORE_DELIMITER)}"

        return BookDTO(
            id = from.book,
            name = from.book,
            spriteUrl = spriteUrl
        )
    }
}

fun AvailableBook.toLocal() = BookEntity(
    book = book,
    minimumAmount = minimumAmount,
    maximumAmount = maximumAmount,
    minimumPrice = minimumPrice,
    maximumPrice = maximumPrice,
    minimumValue = minimumValue,
    maximumValue = maximumValue
)

fun AvailableBook.toDTO() = BookDTO(
    id = book,
    name = book,
    spriteUrl = book.let {
        "${Constants.CRYPTO_IMAGES_URL}${book.substringBefore(Constants.UNDERSCORE_DELIMITER)}"
    }
)

fun BookEntity.fromLocal() = BookDTO(
    id = book,
    name = book,
    spriteUrl = book.let {
        "${Constants.CRYPTO_IMAGES_URL}${book.substringBefore(Constants.UNDERSCORE_DELIMITER)}"
    }
)
