package com.wizeline.bootcamp.capstone.data.mock

import com.google.gson.annotations.SerializedName

data class TickerResponse(
    var success: Boolean,
    var payload: Ticker?
)

data class Ticker(
    var book: String,
    var volume: String,
    var high: String,
    var last: String,
    var low: String,
    var vwap: String,
    var ask: String,
    var bid: String,
    @SerializedName("created_at") var createdAt: String
)
