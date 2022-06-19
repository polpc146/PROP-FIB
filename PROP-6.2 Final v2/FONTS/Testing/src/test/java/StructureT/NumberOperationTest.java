package StructureT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import Operations.NumberOperation;

/**
 * @autor Pol Barco Martínez
 */
class NumberOperationTest {

    NumberOperation no;

    @BeforeEach
    void setUp() {
        no = new NumberOperation();
    }

    @Test
    @DisplayName("Comprueba que la suma de 2+2 de 4")
    void twoPlusTwoShouldEqualFour() {
        assertEquals(4, no.Sum(2,2));
    }

    @Test
    @DisplayName("Comprueba que la suma de 7+4 de 11")
    void sevenPlusFourShouldEqualEleven() {
        assertEquals(11, no.Sum(7,4));
    }

    @Test
    @DisplayName("Comprueba que la resta de 2-2 de 0")
    void twoMinusTwoShouldEqualZero() {
        assertEquals(0, no.Rest(2,2));
    }

    @Test
    @DisplayName("Comprueba que la resta de 7-4 de 3")
    void sevenMinusFourShouldEqualThree() {
        assertEquals(3, no.Rest(7,4));
    }

    @Test
    @DisplayName("Comprueba que la multiplicación de 2*2 de 4")
    void twoMultiplicationTwoShouldEqualFour() {
        assertEquals(4, no.Multiplication(2,2));
    }

    @Test
    @DisplayName("Comprueba que la multiplicación de 7*4 de 28")
    void sevenMultiplicationFourShouldEqualTwentyEight() {
        assertEquals(28, no.Multiplication(7,4));
    }

    @Test
    @DisplayName("Comprueba que la división de 4/2 de 2")
    void FourDivisionTwoShouldEqualTwo() {
        assertEquals(2, no.Division(4,2));
    }

    @Test
    @DisplayName("Comprueba que la división de 7/4 de 1.75")
    void sevenDivisionFourShouldEqualOne() {
        assertEquals(1.75, no.Division(7,4));
    }

    @Test
    @DisplayName("Comprueba que la conversión a binario de 21 de 10101")
    void twentyOneToBinary() {
        assertEquals("10101", no.Binary(21));
    }

    @Test
    @DisplayName("Comprueba que la conversión a binario de 97 de 1100001")
    void ninetySevenToBinary() {
        assertEquals("1100001", no.Binary(97));
    }

    @Test
    @DisplayName("Comprueba que la conversión a hexadecimal de 21 de 15")
    void twentyOneToHexadecimal() {
        assertEquals("15", no.Hexadecimal(21));
    }

    @Test
    @DisplayName("Comprueba que la conversión a hexadecimal de 97 de 61")
    void ninetySevenToHexadecimal() {
        assertEquals("61", no.Hexadecimal(97));
    }

    @Test
    @DisplayName("Comprueba que la conversión a hexadecimal de 975476 de ee274")
    void highNumberToHexadecimal() {
        assertEquals("ee274", no.Hexadecimal(975476));
    }

    @Test
    @DisplayName("Comprueba que el truncamiento de 47.67546554 de 47.0")
    void highNumberTruncate() {
        assertEquals(47.0, no.Truncate(47.67546554));
    }

    @Test
    @DisplayName("Comprueba que el truncamiento de 143241.47546554 de 143241.0")
    void NumberTruncate() {
        assertEquals(143241.0, no.Truncate(143241.47546554));
    }

    @Test
    @DisplayName("Comprueba que el valor absoluto de 47.675 de 47.675")
    void positiveNumberAbs() {
        assertEquals(47.675, no.Absolute(47.675));
    }

    @Test
    @DisplayName("Comprueba que el valor absoluto de -47.675 de 47.675")
    void negativeNumberAbs() {
        assertEquals(47.675, no.Absolute(-47.675));
    }

    @Test
    @DisplayName("Comprueba que se incrementa en uno el valor 35")
    void positiveNumberIncrease() {
        assertEquals(36, no.Increase(35));
    }

    @Test
    @DisplayName("Comprueba que se incrementa en uno el valor -47.675")
    void negativeNumberIncrease() {
        assertEquals(-46.675, no.Increase(-47.675));
    }
}