package com.wizeline.bootcamp.capstone.data.mapper

import com.wizeline.bootcamp.capstone.data.entities.TickerEntity
import com.wizeline.bootcamp.capstone.data.mock.Ticker
import com.wizeline.bootcamp.capstone.domain.TickerDTO
import com.wizeline.bootcamp.capstone.utils.Constants
import com.wizeline.bootcamp.capstone.utils.asPrice

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
    bid = bid.let { bid.asPrice() },
)