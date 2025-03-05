package ru.krivenchukartem.calculatorapp.data

data class CalcUiState(
    val currentNumber: String = "",
    val resultNumber: String = "",
    val currentBase: String = "10",
    val newBase: String = "2",
    val history: List<String> = listOf()
)
