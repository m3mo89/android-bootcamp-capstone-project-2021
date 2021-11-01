package com.wizeline.bootcamp.capstone.data.mock

import com.google.gson.annotations.SerializedName

data class OrderBookResponse (
    @SerializedName("success") var success : Boolean,
    @SerializedName("payload") var payload : OrderBook
)

data class Asks (
    @SerializedName("book") var book : String,
    @SerializedName("price") var price : String,
    @SerializedName("amount") var amount : String
)

data class Bids (
    @SerializedName("book") var book : String,
    @SerializedName("price") var price : String,
    @SerializedName("amount") var amount : String
)

data class OrderBook (
    @SerializedName("asks") var asks : List<Asks>,
    @SerializedName("bids") var bids : List<Bids>,
    @SerializedName("updated_at") var updatedAt : String,
    @SerializedName("sequence") var sequence : String
)

