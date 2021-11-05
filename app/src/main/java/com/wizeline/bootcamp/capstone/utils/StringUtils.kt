package com.wizeline.bootcamp.capstone.utils

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

fun String?.getCryptoName(): String {
    var from = this?.substringBefore("_")
    var to = this?.substringAfter("_")

    from = mapOfCoinNames.get(from) ?: ""
    to = mapOfCoinNames.get(to) ?: ""

    if (from.isNullOrEmpty() || to.isNullOrEmpty()) {
        return ""
    }

    return "$from to $to"
}

fun String?.asPrice(): String {
    val stringAsDouble = this?.toDouble() ?: 0.00

    return stringAsDouble.asPrice()
}