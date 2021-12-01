package com.wizeline.bootcamp.capstone.data.mapper

import com.wizeline.bootcamp.capstone.data.entities.TickerEntity
import com.wizeline.bootcamp.capstone.data.mock.Ticker
import com.wizeline.bootcamp.capstone.domain.TickerDTO
import com.wizeline.bootcamp.capstone.utils.Constants
import com.wizeline.bootcamp.capstone.utils.asPrice
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TickerResponseMapper @Inject constructor() : Mapper<TickerEntity, TickerDTO> {
    override fun map(from: TickerEntity): TickerDTO {
        val spriteUrl =
            "${Constants.CRYPTO_IMAGES_URL}${from.book.substringBefore(Constants.UNDERSCORE_DELIMITER)}"

        return TickerDTO(
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

fun Ticker.toLocal() = TickerEntity(
    book = book,
    volume = volume,
    high = high,
    last = last,
    low = low,
    vwap = vwap,
    ask = ask,
    bid = bid,
    createdAt = createdAt
)

fun Ticker.toDTO() = TickerDTO(
    id = book,
    cryptoName = book,
    spriteUrl = book.let {
        "${Constants.CRYPTO_IMAGES_URL}${book.substringBefore(Constants.UNDERSCORE_DELIMITER)}"
    },
    lastPrice = last.let { last.asPrice() },
    highPrice = high.let { high.asPrice() },
    lowPrice = low.let { low.asPrice() },
    ask = ask.let { ask.asPrice() },
    bid = bid.let { bid.asPrice() }
)

fun TickerEntity.fromLocal() = TickerDTO(
    id = book,
    cryptoName = book,
    spriteUrl = book.let {
        "${Constants.CRYPTO_IMAGES_URL}${book.substringBefore(Constants.UNDERSCORE_DELIMITER)}"
    },
    lastPrice = last.let { last.asPrice() },
    highPrice = high.let { high.asPrice() },
    lowPrice = low.let { low.asPrice() },
    ask = ask.let { ask.asPrice() },
    bid = bid.let { bid.asPrice() }
)
