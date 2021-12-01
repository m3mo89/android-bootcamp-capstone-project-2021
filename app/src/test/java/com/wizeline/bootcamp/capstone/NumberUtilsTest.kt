package com.wizeline.bootcamp.capstone

import com.wizeline.bootcamp.capstone.utils.asPrice
import junit.framework.Assert.assertEquals
import org.junit.Test

class NumberUtilsTest {

    @Test
    fun `Given a double when call asPrice function then return the double as Price`() {
        // Given
        val number: Double = 2.0

        // When
        val numberAsPrice = number.asPrice()

        // Then
        assertEquals("MX$2.00", numberAsPrice)
    }
}
