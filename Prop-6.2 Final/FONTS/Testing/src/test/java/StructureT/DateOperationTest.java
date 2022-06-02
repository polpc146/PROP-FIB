package StructureT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import Operations.DateOperation;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @autor Pol Barco Martínez
 */
class DateOperationTest {

    DateOperation dO;

    @BeforeEach
    void setUp() {
        dO = new DateOperation();
    }

    @Test
    @DisplayName("Comprueba si retorna el dia 20")
    void whatDay20() {
        assertEquals(20 , dO.Day(new Date(2022, 04, 20)));
    }

    @Test
    @DisplayName("Comprueba si retorna el dia 5")
    void whatDay5() {
        assertEquals(5 , dO.Day(new Date(2022, 04, 5)));
    }

    @Test
    @DisplayName("Comprueba si retorna January")
    void whatMonth1() {
        assertEquals("January" , dO.Month(new Date(2022, 1, 21)));
    }

    @Test
    @DisplayName("Comprueba si retorna February")
    void whatMonth2() {
        assertEquals("February" , dO.Month(new Date(2022, 2, 21)));
    }

    @Test
    @DisplayName("Comprueba si retorna March")
    void whatMonth3() {
        assertEquals("March" , dO.Month(new Date(2022, 3, 21)));
    }

    @Test
    @DisplayName("Comprueba si retorna April")
    void whatMonth4() {
        assertEquals("April" , dO.Month(new Date(2022, 4, 21)));
    }

    @Test
    @DisplayName("Comprueba si retorna May")
    void whatMonth5() {
        assertEquals("May" , dO.Month(new Date(2022, 5, 21)));
    }


    @Test
    @DisplayName("Comprueba si retorna June")
    void whatMonth6() {
        assertEquals("June" , dO.Month(new Date(2022, 6, 21)));
    }

    @Test
    @DisplayName("Comprueba si retorna July")
    void whatMonth7() {
        assertEquals("July" , dO.Month(new Date(2022, 7, 21)));
    }

    @Test
    @DisplayName("Comprueba si retorna August")
    void whatMonth8() {
        assertEquals("August" , dO.Month(new Date(2022, 8, 21)));
    }

    @Test
    @DisplayName("Comprueba si retorna September")
    void whatMonth9() {
        assertEquals("September" , dO.Month(new Date(2022, 9, 21)));
    }

    @Test
    @DisplayName("Comprueba si retorna October")
    void whatMonth10() {
        assertEquals("October" , dO.Month(new Date(2022, 10, 21)));
    }

    @Test
    @DisplayName("Comprueba si retorna November")
    void whatMonth11() {
        assertEquals("November" , dO.Month(new Date(2022, 11, 21)));
    }

    @Test
    @DisplayName("Comprueba si retorna December")
    void whatMonth12() {
        assertEquals("December", dO.Month(new Date(2022, 12, 21)));
    }

    @Test
    @DisplayName("Comprueba si retorna el año 2022")
    void whatYear2022() {
        assertEquals(2022, dO.Year(new Date(2022, 12, 21)));
    }

    @Test
    @DisplayName("Comprueba si retorna el año 2156")
    void whatYear2156() {
        assertEquals(2156, dO.Year(new Date(2156, 12, 21)));
    }

    @Test
    @DisplayName("Comprueba si retorna Monday")
    void monday() {
        assertEquals("Monday", dO.DayOfWeek(new Date(2022, 4, 18)));
    }

    @Test
    @DisplayName("Comprueba si retorna Tuesday")
    void tuesday() {
        assertEquals("Tuesday", dO.DayOfWeek(new Date(2022, 4, 19)));
    }

    @Test
    @DisplayName("Comprueba si retorna Wednesday")
    void wednesday() {
        assertEquals("Wednesday", dO.DayOfWeek(new Date(2022, 4, 20)));
    }

    @Test
    @DisplayName("Comprueba si retorna Thursday")
    void thursday() {
        assertEquals("Thursday", dO.DayOfWeek(new Date(2022, 4, 21)));
    }

    @Test
    @DisplayName("Comprueba si retorna Friday")
    void friday() {
        assertEquals("Friday", dO.DayOfWeek(new Date(2022, 4, 22)));
    }

    @Test
    @DisplayName("Comprueba si retorna Saturday")
    void saturday() {
        assertEquals("Saturday", dO.DayOfWeek(new Date(2022, 4, 23)));
    }

    @Test
    @DisplayName("Comprueba si retorna Sunday")
    void sunday() {
        assertEquals("Sunday", dO.DayOfWeek(new Date(2022, 4, 24)));
    }
}