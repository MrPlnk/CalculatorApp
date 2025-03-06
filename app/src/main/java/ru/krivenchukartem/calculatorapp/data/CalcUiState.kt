package ru.krivenchukartem.calculatorapp.data

val DEFAULT_CURRENT_NUMBER = "0"

data class CalcUiState(
    val currentNumber: String = DEFAULT_CURRENT_NUMBER,
    val resultNumber: String = "",
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
