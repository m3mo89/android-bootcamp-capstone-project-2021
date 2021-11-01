package com.wizeline.bootcamp.capstone.data.mock

import com.google.gson.annotations.SerializedName

data class TickerResponse (
    @SerializedName("success") var success : Boolean,
    @SerializedName("payload") var payload : Ticker
)

data class Ticker (

    @SerializedName("book") var book : String,
    @SerializedName("volume") var volume : String,
    @SerializedName("high") var high : String,
    @SerializedName("last") var last : String,
    @SerializedName("low") var low : String,
    @SerializedName("vwap") var vwap : String,
    @SerializedName("ask") var ask : String,
    @SerializedName("bid") var bid : String,
    @SerializedName("created_at") var createdAt : String

)
