package com.wizeline.bootcamp.capstone.data.mapper

import com.wizeline.bootcamp.capstone.utils.asPrice
import com.wizeline.bootcamp.capstone.utils.getCryptoName
import com.wizeline.bootcamp.capstone.data.mock.Ticker as TickerResponse
import com.wizeline.bootcamp.capstone.domain.Ticker as TickerDomain

class TickerResponseMapper : Mapper<TickerResponse, TickerDomain> {
    override fun map(from: TickerResponse): TickerDomain {
        var spriteUrl =
            "https://cryptoicon-api.vercel.app/api/icon/${from.book.substringBefore("_")}"

        return TickerDomain(
            id = from.book,
            spriteUrl = spriteUrl,
            cryptoName = from.book.getCryptoName(),
            lastPrice = from.last.asPrice(),
            highPrice = from.high.asPrice(),
            lowPrice = from.low.asPrice(),
            ask = from.ask.asPrice(),
            bid = from.bid.asPrice()
        )
    }
}