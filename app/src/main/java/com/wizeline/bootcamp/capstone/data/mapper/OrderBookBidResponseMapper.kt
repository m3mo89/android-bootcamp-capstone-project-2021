package com.wizeline.bootcamp.capstone.data.mapper

import com.wizeline.bootcamp.capstone.data.mock.Bids
import com.wizeline.bootcamp.capstone.domain.Bid
import com.wizeline.bootcamp.capstone.utils.asPrice

class OrderBookBidResponseMapper : Mapper<Bids, Bid> {
    override fun map(from: Bids): Bid {
        val price: Double = from.price.toDouble()
        val amount: Double = from.amount.toDouble()
        val total = price * amount

        return Bid(
            price = price.asPrice(),
            amount = amount.asPrice(),
            total = total.asPrice()
        )
    }
}