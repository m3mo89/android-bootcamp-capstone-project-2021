package com.wizeline.bootcamp.capstone.data.mapper

import com.wizeline.bootcamp.capstone.utils.Constants
import com.wizeline.bootcamp.capstone.utils.asPrice
import com.wizeline.bootcamp.capstone.data.mock.Ticker as TickerResponse
import com.wizeline.bootcamp.capstone.domain.Ticker as TickerDomain

class TickerResponseMapper : Mapper<TickerResponse, TickerDomain> {
    override fun map(from: TickerResponse): TickerDomain {
        val spriteUrl =
            "${Constants.CRYPTO_IMAGES_URL}${from.book.substringBefore(Constants.UNDERSCORE_DELIMITER)}"

        return TickerDomain(
            id = from.book,
            spriteUrl = spriteUrl,
            cryptoName = from.book,
            lastPrice = from.last.asPrice(),
            highPrice = from.high.asPrice(),
            lowPrice = from.low.asPrice(),
            ask = from.ask.asPrice(),
            bid = from.bid.asPrice()
        )
    }
}