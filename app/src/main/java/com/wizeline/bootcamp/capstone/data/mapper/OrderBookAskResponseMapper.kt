package com.wizeline.bootcamp.capstone.data.mapper

import com.wizeline.bootcamp.capstone.data.mock.Asks
import com.wizeline.bootcamp.capstone.domain.AskDTO
import com.wizeline.bootcamp.capstone.utils.asPrice

class OrderBookAskResponseMapper : Mapper<Asks, AskDTO> {
    override fun map(from: Asks): AskDTO {
        val price: Double = from.price.toDouble()
        val amount: Double = from.amount.toDouble()
        val total = price * amount

        return AskDTO(
            price = price.asPrice(),
            amount = amount.asPrice(),
            total = total.asPrice()
        )
    }
}