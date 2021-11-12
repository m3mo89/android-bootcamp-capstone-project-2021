package com.wizeline.bootcamp.capstone.data.mock

import com.wizeline.bootcamp.capstone.domain.AskDTO
import com.wizeline.bootcamp.capstone.domain.Bid
import com.wizeline.bootcamp.capstone.domain.Book
import com.wizeline.bootcamp.capstone.domain.Ticker

val mockBooks = listOf(
    Book(
        id = "btc_mxn",
        name = "Bitcoin to Pesos mexicanos",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/btc"
    ),
    Book(
        id = "eth_btc",
        name = "Ether to Bitcoin",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/eth"
    ),
    Book(
        id = "eth_mxn",
        name = "Ether to Pesos mexicanos",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/eth"
    ),
    Book(
        id = "xrp_btc",
        name = "XRP to Bitcoin",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/xrp"
    ),
    Book(
        id = "xrp_mxn",
        name = "XRP to Pesos mexicanos",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/xrp"
    ),
    Book(
        id = "ltc_btc",
        name = "Litecoin to Bitcoin",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/ltc"
    ),
    Book(
        id = "ltc_mxn",
        name = "Litecoin to Pesos mexicanos",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/ltc"
    ),
    Book(
        id = "bch_btc",
        name = "Bitcoin cash to Bitcoin",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/bch"
    ),
    Book(
        id = "bch_mxn",
        name = "Bitcoin cash to Pesos mexicanos",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/bch"
    ),
    Book(
        id = "tusd_btc",
        name = "TrueUSD to Bitcoin",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/tusd"
    ),
    Book(
        id = "tusd_mxn",
        name = "TrueUSD to Pesos mexicanos",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/tusd"
    ),
    Book(
        id = "mana_btc",
        name = "MANA to Bitcoin",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/mana"
    ),
    Book(
        id = "mana_mxn",
        name = "MANA to Pesos mexicanos",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/mana"
    ),
    Book(
        id = "bat_btc",
        name = "BAT to Bitcoin",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/bat"
    ),
    Book(
        id = "bat_mxn",
        name = "BAT to Pesos mexicanos",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/bat"
    ),
    Book(
        id = "btc_ars",
        name = "Bitcoin to Pesos argentinos",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/btc"
    ),
    Book(
        id = "btc_dai",
        name = "Bitcoin to Dai",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/btc"
    ),
    Book(
        id = "dai_mxn",
        name = "Dai to Pesos argentinos",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/dai"
    ),
    Book(
        id = "btc_usd",
        name = "Bitcoin to USD stablecoins",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/btc"
    ),
    Book(
        id = "ltc_usd",
        name = "Litecoin to USD stablecoins",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/ltc"
    ),
    Book(
        id = "comp_usd",
        name = "Compound to USD stablecoins",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/comp"
    ),
    Book(
        id = "link_usd",
        name = "Chainlink Token to USD stablecoins ",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/link"
    ),
    Book(
        id = "uni_usd",
        name = "Uniswap to USD stablecoins",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/uni"
    ),
    Book(
        id = "aave_usd",
        name = "Aave to USD stablecoins",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/aave"
    ),
)

val mockTicker = Ticker(
    id = "eth_brl",
    cryptoName = "Ether",
    lastPrice = "25,196.49",
    lowPrice = "25,179.813",
    highPrice = "26,353.86",
    ask = "25,401.53",
    bid = "25,333.08",
    spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/eth"
)

val mockAsks = listOf(
    AskDTO(price = "0.07325251", amount = "0.00815501", total = "0.00815503"),
    AskDTO(price = "0.07325252", amount = "0.00815502", total = "0.00815503"),
    AskDTO(price = "0.07325253", amount = "0.00815503", total = "0.00815503"),
    AskDTO(price = "0.07325254", amount = "0.00815504", total = "0.00815503"),
    AskDTO(price = "0.07325251", amount = "0.00815501", total = "0.00815503"),
    AskDTO(price = "0.07325252", amount = "0.00815502", total = "0.00815503"),
    AskDTO(price = "0.07325253", amount = "0.00815503", total = "0.00815503"),
    AskDTO(price = "0.07325254", amount = "0.00815504", total = "0.00815503"),
)

val mockBids = listOf(
    Bid(price = "0.07325251", amount = "0.00815501", total = "0.00815503"),
    Bid(price = "0.07325252", amount = "0.00815502", total = "0.00815503"),
    Bid(price = "0.07325253", amount = "0.00815503", total = "0.00815503"),
    Bid(price = "0.07325254", amount = "0.00815504", total = "0.00815503"),
    Bid(price = "0.07325251", amount = "0.00815501", total = "0.00815503"),
    Bid(price = "0.07325252", amount = "0.00815502", total = "0.00815503"),
    Bid(price = "0.07325253", amount = "0.00815503", total = "0.00815503"),
    Bid(price = "0.07325254", amount = "0.00815504", total = "0.00815503"),
)
