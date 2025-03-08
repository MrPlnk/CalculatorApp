package ru.krivenchukartem.calculatorapp.data

import ru.krivenchukartem.calculatorapp.R

object DataSource {
    val digitToString = mapOf(
        0 to "0",
        1 to "1",
        2 to "2",
        3 to "3",
        4 to "4",
        5 to "5",
        6 to "6",
        7 to "7",
        8 to "8",
        9 to "9",
        10 to "A",
        11 to "B",
        12 to "C",
        13 to "D",
        14 to "E",
        15 to "F"
    )

    val stringToDigit = mapOf(
        "0" to 0,
        "1" to 1,
        "2" to 2,
        "3" to 3,
        "4" to 4,
        "5" to 5,
        "6" to 6,
        "7" to 7,
        "8" to 8,
        "9" to 9,
        "A" to 10,
        "B" to 11,
        "C" to 12,
        "D" to 13,
        "E" to 14,
        "F" to 15
    )
}