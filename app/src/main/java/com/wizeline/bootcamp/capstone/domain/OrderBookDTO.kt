package com.wizeline.bootcamp.capstone.domain

data class OrderBookDTO(
    var asks: List<AskDTO>,
    var bids: List<BidDTO>
)
