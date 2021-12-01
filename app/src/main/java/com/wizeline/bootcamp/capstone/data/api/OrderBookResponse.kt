package com.wizeline.bootcamp.capstone.data.api

import com.google.gson.annotations.SerializedName

data class OrderBookResponse(
    var success: Boolean?,
    var payload: OrderBook?
)

data class Asks(
    var book: String,
    var price: String,
    var amount: String,
    var oid: String
)

data class Bids(
    var book: String,
    var price: String,
    var amount: String,
    var oid: String
)

data class OrderBook(
    var asks: List<Asks>,
    var bids: List<Bids>,
    @SerializedName("updated_at") var updatedAt: String,
    var sequence: String
)
