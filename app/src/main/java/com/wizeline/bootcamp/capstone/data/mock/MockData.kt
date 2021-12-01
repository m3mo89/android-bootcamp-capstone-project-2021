package com.wizeline.bootcamp.capstone.data.mock

import com.wizeline.bootcamp.capstone.domain.AskDTO
import com.wizeline.bootcamp.capstone.domain.BidDTO
import com.wizeline.bootcamp.capstone.domain.BookDTO
import com.wizeline.bootcamp.capstone.domain.TickerDTO

val mockBooks = listOf(
    BookDTO(
        id = "btc_mxn",
        name = "Bitcoin to Pesos mexicanos",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/btc"
    ),
    BookDTO(
        id = "eth_btc",
        name = "Ether to Bitcoin",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/eth"
    ),
    BookDTO(
        id = "eth_mxn",
        name = "Ether to Pesos mexicanos",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/eth"
    ),
    BookDTO(
        id = "xrp_btc",
        name = "XRP to Bitcoin",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/xrp"
    ),
    BookDTO(
        id = "xrp_mxn",
        name = "XRP to Pesos mexicanos",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/xrp"
    ),
    BookDTO(
        id = "ltc_btc",
        name = "Litecoin to Bitcoin",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/ltc"
    ),
    BookDTO(
        id = "ltc_mxn",
        name = "Litecoin to Pesos mexicanos",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/ltc"
    ),
    BookDTO(
        id = "bch_btc",
        name = "Bitcoin cash to Bitcoin",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/bch"
    ),
    BookDTO(
        id = "bch_mxn",
        name = "Bitcoin cash to Pesos mexicanos",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/bch"
    ),
    BookDTO(
        id = "tusd_btc",
        name = "TrueUSD to Bitcoin",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/tusd"
    ),
    BookDTO(
        id = "tusd_mxn",
        name = "TrueUSD to Pesos mexicanos",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/tusd"
    ),
    BookDTO(
        id = "mana_btc",
        name = "MANA to Bitcoin",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/mana"
    ),
    BookDTO(
        id = "mana_mxn",
        name = "MANA to Pesos mexicanos",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/mana"
    ),
    BookDTO(
        id = "bat_btc",
        name = "BAT to Bitcoin",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/bat"
    ),
    BookDTO(
        id = "bat_mxn",
        name = "BAT to Pesos mexicanos",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/bat"
    ),
    BookDTO(
        id = "btc_ars",
        name = "Bitcoin to Pesos argentinos",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/btc"
    ),
    BookDTO(
        id = "btc_dai",
        name = "Bitcoin to Dai",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/btc"
    ),
    BookDTO(
        id = "dai_mxn",
        name = "Dai to Pesos argentinos",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/dai"
    ),
    BookDTO(
        id = "btc_usd",
        name = "Bitcoin to USD stablecoins",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/btc"
    ),
    BookDTO(
        id = "ltc_usd",
        name = "Litecoin to USD stablecoins",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/ltc"
    ),
    BookDTO(
        id = "comp_usd",
        name = "Compound to USD stablecoins",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/comp"
    ),
    BookDTO(
        id = "link_usd",
        name = "Chainlink Token to USD stablecoins ",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/link"
    ),
    BookDTO(
        id = "uni_usd",
        name = "Uniswap to USD stablecoins",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/uni"
    ),
    BookDTO(
        id = "aave_usd",
        name = "Aave to USD stablecoins",
        spriteUrl = "https://cryptoicon-api.vercel.app/api/icon/aave"
    )
)
val mockTicker = TickerDTO(
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
    AskDTO(price = "0.07325254", amount = "0.00815504", total = "0.00815503")
)
val mockBids = listOf(
    BidDTO(price = "0.07325251", amount = "0.00815501", total = "0.00815503"),
    BidDTO(price = "0.07325252", amount = "0.00815502", total = "0.00815503"),
    BidDTO(price = "0.07325253", amount = "0.00815503", total = "0.00815503"),
    BidDTO(price = "0.07325254", amount = "0.00815504", total = "0.00815503"),
    BidDTO(price = "0.07325251", amount = "0.00815501", total = "0.00815503"),
    BidDTO(price = "0.07325252", amount = "0.00815502", total = "0.00815503"),
    BidDTO(price = "0.07325253", amount = "0.00815503", total = "0.00815503"),
    BidDTO(price = "0.07325254", amount = "0.00815504", total = "0.00815503")
)
