package com.wizeline.bootcamp.capstone.data.mapper

import com.wizeline.bootcamp.capstone.data.mock.OrderBook
import com.wizeline.bootcamp.capstone.domain.OrderBookDTO

class OrderBookResponseMapper : Mapper<OrderBook, OrderBookDTO> {
    override fun map(from: OrderBook): OrderBookDTO {
        val askMapper = OrderBookAskResponseMapper()
        val bidMapper = OrderBookBidResponseMapper()

        return OrderBookDTO(
            asks = askMapper.mapList(from.asks),
            bids = bidMapper.mapList(from.bids),
        )
    }
}