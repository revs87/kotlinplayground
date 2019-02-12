package com.thefloow.kotlinplayground.utils


/**
 * Created by Rui Vieira on 11/02/2019.
 * The Floow Ltd
 * rui.vieira@thefloow.com
 */

class ValidationText {
    private val length: (String) -> Int = { it.length }
    private val notEmpty: (String) -> Boolean = { !it.isEmpty() }
    private val atLeastFour: (String) -> Boolean = { it.length >= 4 }
    private val fourDigits: (String) -> Boolean = { it.matches(Regex("\\d{4}")) }
    private val validCreditCard: (String) -> Boolean = {
        Luhn.isValid(
            it
        )
    }


    private fun <T> T.validateWith(predicate: (T) -> Boolean): Boolean {
        val item = this ?: return false
        if (predicate(item)) {
            return true
        }
        return false
    }


    fun isNotEmpty(text: String?): Boolean {
        if (text == null) return false
        return text.validateWith(notEmpty)
    }

    fun isAtLeastFour(text: String?): Boolean {
        if (text == null) return false
        return text.validateWith(atLeastFour)
    }

    fun isFourDigits(text: String?): Boolean {
        if (text == null) return false
        return text.validateWith(fourDigits)
    }

    fun isValidCreditCard(text: String?): Boolean {
        if (text == null) return false
        return text.validateWith(validCreditCard)
    }

    private object Luhn {
        fun isValid(input: String): Boolean {
            val sanitizedInput = input.replace(" ", "")

            return when {
                valid(sanitizedInput) -> checksum(
                    sanitizedInput
                ) % 10 == 0
                else -> false
            }
        }

        private fun valid(input: String) = input.all(Char::isDigit) && input.length > 1

        private fun checksum(input: String) = addends(input).sum()

        private fun addends(input: String) = input.digits().mapIndexed { i, j ->
            when {
                (input.length - i + 1) % 2 == 0 -> j
                j >= 5 -> j * 2 - 9
                else -> j * 2
            }
        }

        private fun String.digits() = this.map(Character::getNumericValue)
    }
}