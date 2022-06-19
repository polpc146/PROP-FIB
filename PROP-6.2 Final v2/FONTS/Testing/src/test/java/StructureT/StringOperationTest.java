package StructureT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import Structure.Cells.StringCell;
import Operations.StringOperation;

/**
 * @autor Pol Barco Martínez
 */
class StringOperationTest {

    StringOperation so;
    StringCell cell, cell2;

    @BeforeEach
    void setUp() {
        so = new StringOperation();
        cell = new StringCell("hola hoy estoy contento");
        cell2 = new StringCell("HOLA HOY ESTOY CONTENTO");
    }

    @Test
    @DisplayName("Comprueba que el string de cell da un tamaño de 23")
    void helloLength() {
        assertEquals(23, so.Length(cell));
    }

    @Test
    @DisplayName("Comprueba que el string de cell2 da un tamaño de 23")
    void capitalLettersLength() {
        assertEquals(23, so.Length((cell2)));
    }

    @Test
    @DisplayName("Comprueba que el exista en la palabra contento y la reemplace por triste")
    void replaceContentoToTriste() {
        so.Replace(cell, "contento", "triste");
        assertEquals("hola hoy estoy triste", cell.getString(), "funciona");
    }

    @Test
    @DisplayName("Comprueba que exista en la palabra contento en la celda cell")
    void contentoSearch() {
        assertEquals(true, so.Search(cell, "contento"));
    }

    @Test
    @DisplayName("Comprueba que no exista en la palabra CONTENTO en la celda cell")
    void contentoCapitalLetterSearch() {
        assertEquals(false, so.Search(cell, "CONTENTO"));
    }
}