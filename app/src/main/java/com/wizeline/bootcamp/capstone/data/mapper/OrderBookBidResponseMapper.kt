package com.wizeline.bootcamp.capstone.data.mapper

import com.wizeline.bootcamp.capstone.data.mock.Bids
import com.wizeline.bootcamp.capstone.domain.BidDTO
import com.wizeline.bootcamp.capstone.utils.asPrice

class OrderBookBidResponseMapper : Mapper<Bids, BidDTO> {
    override fun map(from: Bids): BidDTO {
        val price: Double = from.price.toDouble()
        val amount: Double = from.amount.toDouble()
        val total = price * amount

        return BidDTO(
            price = price.asPrice(),
            amount = amount.asPrice(),
            total = total.asPrice()
        )
    }
}
