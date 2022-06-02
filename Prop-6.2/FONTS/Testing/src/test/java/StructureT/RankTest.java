package StructureT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import Structure.Rank;
import Structure.Cells.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @autor Pol Barco Martínez
 */
class RankTest {

    Rank r;

    @BeforeEach
    void setUp() {
        Cell cell1 = new NumCell(22);
        Cell cell2 = new NumCell(24);
        LinkedList<Cell> cells = new LinkedList<>();
        cells.add(cell1);
        cells.add(cell2);
        r = new Rank(cells, 2, 1);
    }

    @Test
    @DisplayName("Comprueba que devuelve las celdas del rango")
    void getCellsOfRank() {
        LinkedList<Cell> result = new LinkedList<>();
        result = r.getRankCell();
        assertEquals(22, result.get(0).getNum());
        assertEquals(24, result.get(1).getNum());
    }

    @Test
    @DisplayName("Comprueba que modifica las celdas del rango")
    void setCellsOfRank() {
        Cell cell3 = new NumCell(27);
        Cell cell4 = new NumCell(28);
        LinkedList<Cell> modify = new LinkedList<>();
        modify.add(cell3);
        modify.add(cell4);
        r.setRankCell(modify);
        assertEquals(27, modify.get(0).getNum());
        assertEquals(28, modify.get(1).getNum());
    }

    @Test
    @DisplayName("Comprueba el numero de filas del rango")
    void getNumRowsOfRank() {
        assertEquals(2, r.getNumRows());
    }

    @Test
    @DisplayName("Comprueba el numero de columnas del rango")
    void getNumColumnsOfRank() {
        assertEquals(1, r.getNumColumns());
    }

    @Test
    @DisplayName("Comprueba que las celdas del rango esten correctas")
    void getCellOfRank() {
        Cell aux = r.getCell(0);
        assertEquals(22, aux.getNum());
        aux = r.getCell(1);
        assertEquals(24, aux.getNum());
    }

    @Test
    @DisplayName("Comprueba que retorne la media del rango")
    void averageRank() {
        double result = r.Average();
        assertEquals(23, result);
    }

    @Test
    @DisplayName("Comprueba que retorne la mediana del rango")
    void medianRank() {
        double result = r.Median();
        assertEquals(23, result);
    }

    @Test
    @DisplayName("Comprueba que retorne la variancia del rango")
    void varianceRank() {
        double result = r.Variance();
        assertEquals(1, result);
    }

    @Test
    @DisplayName("Comprueba que retorne la desviacion estandard del rango")
    void standardDeviationRank() {
        double result = r.StandardDeviation();
        assertEquals(1, result);
    }

    @Test
    @DisplayName("Comprueba que retorne el canvio de un string por otro en un rango")
    void replaceCellRank() {
        Cell cell1 = new StringCell("estoy bien");
        LinkedList<Cell> cells = new LinkedList<>();
        cells.add(cell1);
        Rank r1 = new Rank(cells, 2, 1);
        r1.Replace("bien", "mal");
        assertEquals("estoy mal", r1.getCell(0).getString());
    }

    @Test
    @DisplayName("Comprueba que retorne el tamaño de un string en un rango")
    void sizeCellRank() {
        Cell cell1 = new StringCell("estoy bien");
        LinkedList<Cell> cells = new LinkedList<>();
        cells.add(cell1);
        Rank r1 = new Rank(cells, 2, 1);
        double result = r1.Size();
        assertEquals(10, result);
    }

    @Test
    @DisplayName("Comprueba que retorne true si encuentra el string en el rango y false en caso contrario")
    void searchCellRank() {
        Cell cell1 = new StringCell("estoy bien");
        LinkedList<Cell> cells = new LinkedList<>();
        cells.add(cell1);
        Rank r1 = new Rank(cells, 2, 1);
        boolean result = r1.Search("estoy bien");
        assertEquals(true, result);
        result = r1.Search("estoy mal");
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Comprueba que todas las celdas de un rango son del mismo tipo")
    void checkCellRank() {
        assertEquals('N', r.CheckRank());
    }

    @Test
    @DisplayName("Comprueba que retorne una lista con las celdas num del rango")
    void getAuxCellsNumRank() {
        LinkedList<Cell> result = r.GetAuxCells();
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Comprueba que retorne una lista con las celdas string del rango")
    void getAuxCellsStringRank() {
        Cell cell1 = new StringCell("hola");
        Cell cell2 = new StringCell("adios");
        LinkedList<Cell> cells = new LinkedList<>();
        cells.add(cell1);
        cells.add(cell2);
        Rank r1 = new Rank(cells, 2, 1);
        LinkedList<Cell> result = r1.GetAuxCells();
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Comprueba que retorne una lista con las celdas Date del rango")
    void getAuxCellsDateRank() {
        Cell cell1 = new DateCell(new Date(2022, 04, 24));
        Cell cell2 = new DateCell(new Date(2022, 04, 25));
        LinkedList<Cell> cells = new LinkedList<>();
        cells.add(cell1);
        cells.add(cell2);
        Rank r1 = new Rank(cells, 2, 1);
        LinkedList<Cell> result = r1.GetAuxCells();
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Comprueba que retorne una lista con las celdas vacias del rango")
    void getAuxCellsRank() {
        Cell cell1 = new Cell();
        LinkedList<Cell> cells = new LinkedList<>();
        cells.add(cell1);
        Rank r1 = new Rank(cells, 1, 1);
        LinkedList<Cell> result = r1.GetAuxCells();
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Comprueba que retorne el rango con celdas vacias")
    void removeCellRank() {
        Cell cell1 = new StringCell("hola");
        Cell cell2 = new StringCell("adios");
        LinkedList<Cell> cells = new LinkedList<>();
        cells.add(cell1);
        cells.add(cell2);
        Rank r1 = new Rank(cells, 2, 1);
        r1.RemoveRank();
        assertEquals("", r1.getCell(0).getString());
        assertEquals("", r1.getCell(1).getString());
    }
}