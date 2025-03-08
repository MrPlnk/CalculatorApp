package ru.krivenchukartem.calculatorapp.data

val DEFAULT_CURRENT_NUMBER = "0"
val NUMBER_OF_DECIMAL_PLACES: Int = 6
val NUMBER_OF_DIGITS_IN_EXPRESSION: Int = 7

data class CalcUiState(
    val currentNumber: String = DEFAULT_CURRENT_NUMBER,
    val resultNumber: String = DEFAULT_CURRENT_NUMBER,
    val currentBase: String = "10",
    val newBase: String = "10",
    val isFloat: Boolean = false,
    val history: List<HistoryRepresentation> = listOf()
)

data class HistoryRepresentation(
    val currentNumber: String,
    val resultNumber: String,
    val currentBase: String,
    val newBase: String,
)
