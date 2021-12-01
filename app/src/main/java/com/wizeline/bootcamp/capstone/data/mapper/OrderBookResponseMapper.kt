package com.wizeline.bootcamp.capstone.data.mapper

import com.wizeline.bootcamp.capstone.data.entities.OrderBookEntity
import com.wizeline.bootcamp.capstone.data.mock.OrderBook
import com.wizeline.bootcamp.capstone.domain.OrderBookDTO

val askMapper = OrderBookAskResponseMapper()
val bidMapper = OrderBookBidResponseMapper()

fun OrderBook.toLocal(book: String) = OrderBookEntity(
    asks = askMapper.mapList(asks),
    bids = bidMapper.mapList(bids),
    updatedAt = updatedAt,
    sequence = sequence,
    book = book
)

fun OrderBookEntity.fromLocal() = OrderBookDTO(
    asks = asks,
    bids = bids
)
