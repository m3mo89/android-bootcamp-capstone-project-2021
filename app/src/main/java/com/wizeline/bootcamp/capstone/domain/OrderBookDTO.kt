package com.wizeline.bootcamp.capstone.domain

data class OrderBookDTO(
    var asks: List<Ask>,
    var bids: List<Bid>,
)