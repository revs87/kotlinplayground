package com.thefloow.kotlinplayground

import org.junit.Assert.assertEquals
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ValidationTextUnitTest {

    @Test
    fun text_atLeastFour() {
        val validation = ValidationText()
        assertEquals(true, validation.isAtLeastFour("12345"))
        assertEquals(true, validation.isAtLeastFour("1234"))
        assertEquals(false, validation.isAtLeastFour("123"))
        assertEquals(false, validation.isAtLeastFour(""))
        assertEquals(false, validation.isAtLeastFour(null))
    }

    @Test
    fun text_isFourDigits() {
        val validation = ValidationText()
        assertEquals(false, validation.isFourDigits("12345"))
        assertEquals(true, validation.isFourDigits("1234"))
        assertEquals(false, validation.isFourDigits("123"))
        assertEquals(false, validation.isFourDigits(""))
        assertEquals(false, validation.isFourDigits(null))
    }

    @Test
    fun text_isNotEmpty() {
        val validation = ValidationText()
        assertEquals(true, validation.isNotEmpty("notEmpty"))
        assertEquals(false, validation.isNotEmpty(""))
        assertEquals(false, validation.isNotEmpty(null))
    }

    @Test
    fun text_isValidCreditCard() {
        val validation = ValidationText()
        assertEquals(true, validation.isValidCreditCard("4913354162115058"))
        assertEquals(false, validation.isValidCreditCard("1234567812345678"))
        assertEquals(false, validation.isValidCreditCard(""))
        assertEquals(false, validation.isValidCreditCard(null))
    }

}
