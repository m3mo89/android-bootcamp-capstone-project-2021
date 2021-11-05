package com.wizeline.bootcamp.capstone.data.mapper

import com.wizeline.bootcamp.capstone.data.mock.AvailableBook
import com.wizeline.bootcamp.capstone.domain.Book
import com.wizeline.bootcamp.capstone.utils.getCryptoName

class AvailableBookResponseMapper : Mapper<AvailableBook, Book> {
    override fun map(from: AvailableBook): Book {
        var spriteUrl =
            "https://cryptoicon-api.vercel.app/api/icon/${from.book.substringBefore("_")}"

        return Book(
            id = from.book,
            name = from.book.getCryptoName(),
            spriteUrl = spriteUrl
        )
    }
}