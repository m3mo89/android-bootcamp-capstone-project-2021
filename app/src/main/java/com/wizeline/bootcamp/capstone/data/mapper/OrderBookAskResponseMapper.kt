package com.wizeline.bootcamp.capstone.data.mapper

import com.wizeline.bootcamp.capstone.data.mock.Asks
import com.wizeline.bootcamp.capstone.domain.Ask
import com.wizeline.bootcamp.capstone.utils.asPrice

class OrderBookAskResponseMapper : Mapper<Asks, Ask> {
    override fun map(from: Asks): Ask {
        val price: Double = from.price.toDouble()
        val amount: Double = from.amount.toDouble()
        val total = price * amount

        return Ask(
            price = price.asPrice(),
            amount = amount.asPrice(),
            total = total.asPrice()
        )
    }
}