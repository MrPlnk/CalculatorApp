package ru.krivenchukartem.calculatorapp

import org.junit.Test
import ru.krivenchukartem.calculatorapp.logic.Convert_10_p1
import org.junit.Assert.assertEquals

class Convert_10_p1_UnitTest {
    @Test
    fun intToP_commonTest(){
        val originalNumber = 123;
        val expectedNumber = "1111011"
        val numberSystem = 2

        var resultNumber = ""
        resultNumber = Convert_10_p1.intToP(originalNumber, numberSystem)

        assertEquals(expectedNumber, resultNumber)
    }

    @Test
    fun intToP_hexadecimalSystem(){
        val originalNumber = 123
        val expectedNumber = "7B"
        val numberSystem = 16

        var resultNumber = ""
        resultNumber = Convert_10_p1.intToP(originalNumber, numberSystem)

        assertEquals(expectedNumber, resultNumber)
    }

    @Test
    fun fltToP_commonTest(){
        val originalNumber = 0.37510
        val expectedNumber = "011"
        val numberSystem = 2
        val exactness = 3
        val resultNumber = Convert_10_p1.fltToP(originalNumber, numberSystem, exactness)

        assertEquals(expectedNumber, resultNumber)
    }

    @Test
    fun fltToP_hexadecimalSystem(){
        val originalNumber = 0.37510
        val expectedNumber = "60068D"
        val numberSystem = 16
        val exactness = 6
        val resultNumber = Convert_10_p1.fltToP(originalNumber, numberSystem, exactness)

        assertEquals(expectedNumber, resultNumber)
    }

    @Test
    fun solve_commonTest(){
        val originalNumber = 123.3751
        val expectedNumber = "7B.60068D"
        val numberSystem = 16
        val exactness = 6
        val result = Convert_10_p1.solve(originalNumber, numberSystem, exactness)

        assertEquals(expectedNumber, result)
    }

    @Test
    fun solve_onlyWholePart(){
        val originalNumber = 123.0
        val expectedNumber = "7B"
        val numberSystem = 16
        val exactness = 6
        val result = Convert_10_p1.solve(originalNumber, numberSystem, exactness)

        assertEquals(expectedNumber, result)
    }

    @Test
    fun solve_negativeFractionNumber(){
        val originalNumber = -123.3751
        val expectedNumber = "-7B.60068D"
        val numberSystem = 16
        val exactness = 6
        val result = Convert_10_p1.solve(originalNumber, numberSystem, exactness)

        assertEquals(expectedNumber, result)
    }

    @Test
    fun solve_smallFractionNumber() {
        val originalNumber = 1.9
        val expectedNumber = "1.E66666"
        val numberSystem = 16
        val exactness = 6
        val result = Convert_10_p1.solve(originalNumber, numberSystem, exactness)

        assertEquals(expectedNumber, result)
    }
}