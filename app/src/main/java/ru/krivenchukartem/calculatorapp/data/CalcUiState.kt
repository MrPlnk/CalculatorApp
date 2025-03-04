package ru.krivenchukartem.calculatorapp.data

data class CalcUiState(
    val currentNumber: String = "",
    val resultNumber: String = "",
    val currentBase: String = "",
    val newBase: String = "",
    val history: List<String> = listOf()
)
