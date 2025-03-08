package ru.krivenchukartem.calculatorapp

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.krivenchukartem.calculatorapp.logic.Convert_p1_10

class Convert_p1_10_UnitTest {
    @Test
    fun dval_commonTest(){
        val originalDigit = "A"
        val numberSystem = 16
        val weight = 1
        val expectedValue = 160.0

        val resultValue = Convert_p1_10.dval(originalDigit, numberSystem, weight)
        assertEquals(expectedValue, resultValue, 0.001)
    }

    @Test
    fun solve_commonTest(){
        val originalDigit = "234A.DD3"
        val numberSystem = 16
        val expectedValue = 9034.864013671875

        val resultValue = Convert_p1_10.solve(originalDigit, numberSystem)
        assertEquals(expectedValue, resultValue, 0.001)
    }

    @Test
    fun solve_numberWithMines(){
        val originalDigit = "-234A.DD3"
        val numberSystem = 16
        val expectedValue = -9034.864013671875

        val resultValue = Convert_p1_10.solve(originalDigit, numberSystem)
        assertEquals(expectedValue, resultValue, 0.001)
    }

    @Test
    fun solve_wholeNumber(){
        val originalDigit = "-234A"
        val numberSystem = 16
        val expectedValue = -9034.0

        val resultValue = Convert_p1_10.solve(originalDigit, numberSystem)
        assertEquals(expectedValue, resultValue, 0.001)
    }

    @Test
    fun solve_sameSystems(){
        val originalDigit = "-10"
        val numberSystem = 10
        val expectedValue = -10.0

        val resultValue = Convert_p1_10.solve(originalDigit, numberSystem)
        assertEquals(expectedValue, resultValue, 0.001)
    }
}