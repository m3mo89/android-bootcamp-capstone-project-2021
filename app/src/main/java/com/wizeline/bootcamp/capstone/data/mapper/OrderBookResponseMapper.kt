package com.wizeline.bootcamp.capstone.data.mapper

import com.wizeline.bootcamp.capstone.data.entities.OrderBookEntity
import com.wizeline.bootcamp.capstone.data.mock.OrderBook
import com.wizeline.bootcamp.capstone.domain.OrderBookDTO
import javax.inject.Inject
import javax.inject.Singleton

val askMapper = OrderBookAskResponseMapper()
val bidMapper = OrderBookBidResponseMapper()

@Singleton
class OrderBookResponseMapper @Inject constructor() : Mapper<OrderBookEntity, OrderBookDTO> {
    override fun map(from: OrderBookEntity): OrderBookDTO {

        return OrderBookDTO(
            asks = from.asks,
            bids = from.bids
        )
    }
}

fun OrderBook.toLocal(book: String) = OrderBookEntity(
    asks = askMapper.mapList(asks),
    bids = bidMapper.mapList(bids),
    updatedAt = updatedAt,
    sequence = sequence,
    book = book
)

fun OrderBook.toDTO() = OrderBookDTO(
    asks = askMapper.mapList(asks),
    bids = bidMapper.mapList(bids)
)

fun OrderBookEntity.fromLocal() = OrderBookDTO(
    asks = asks,
    bids = bids
)
