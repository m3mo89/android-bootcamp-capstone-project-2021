package com.wizeline.bootcamp.capstone.utils

import java.text.NumberFormat
import java.util.*

fun Double.asPrice(): String = NumberFormat.getCurrencyInstance(Locale.getDefault()).apply {
    maximumFractionDigits = if (this@asPrice > 1) {
        2
    } else {
        8
    }
}.format(this)
