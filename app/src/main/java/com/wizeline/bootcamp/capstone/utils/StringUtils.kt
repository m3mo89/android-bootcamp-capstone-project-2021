package com.wizeline.bootcamp.capstone.utils

import android.content.Context
import com.wizeline.bootcamp.capstone.R

val mapOfCoinNames = mapOf(
    "btc" to "Bitcoin",
    "eth" to "Ether",
    "xrp" to "XRP",
    "ltc" to "Litecoin",
    "bch" to "Bitcoin cash",
    "tusd" to "TrueUSD",
    "mana" to "MANA",
    "bat" to "BAT",
    "dai" to "Dai",
    "usd" to "USD stablecoins",
    "comp" to "Compound",
    "link" to "Chainlink Token",
    "uni" to "Uniswap",
    "aave" to "Aave",
    "mxn" to "Pesos mexicanos",
    "usdt" to "Tether USD",
    "ars" to "Pesos argentinos",
    "brl" to "Reales brasileños",
)

fun String?.getCryptoName(context: Context): String {
    var from = this?.substringBefore(Constants.UNDERSCORE_DELIMITER)
    var to = this?.substringAfter(Constants.UNDERSCORE_DELIMITER)

    from = mapOfCoinNames.get(from).orEmpty()
    to = mapOfCoinNames.get(to).orEmpty()

    if (from.isNullOrEmpty() || to.isNullOrEmpty()) {
        return ""
    }

    return context.resources.getString(R.string.to, from, to)
}

fun String?.asPrice(): String {
    val stringAsDouble = this?.toDouble() ?: 0.00

    return stringAsDouble.asPrice()
}