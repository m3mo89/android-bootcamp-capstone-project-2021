package com.wizeline.bootcamp.capstone.data.mock

import com.google.gson.annotations.SerializedName

data class AvailableBooksResponse(
    var success: Boolean?,
    var payload: List<AvailableBook>?
)

data class AvailableBook(
    val book: String,
    @SerializedName("minimum_amount") val minimumAmount: String,
    @SerializedName("maximum_amount") val maximumAmount: String,
    @SerializedName("minimum_price") val minimumPrice: String,
    @SerializedName("maximum_price") val maximumPrice: String,
    @SerializedName("minimum_value") val minimumValue: String,
    @SerializedName("maximum_value") val maximumValue: String
)
