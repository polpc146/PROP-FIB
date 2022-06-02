package StructureT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import Structure.Sheet;
import Structure.Row;
import Structure.Rank;
import Structure.Cells.*;

/**
 * @autor Pol Barco Martínez
 */
class SheetTest {

    Sheet s;

    @BeforeEach
    void setUp() {
        s = new Sheet("PrimeraHoja");
    }

    @Test
    @DisplayName("Comprueba si consulta las filas de las hojas")
    void getRowOfSheet() {
        LinkedList<Row> rows = new LinkedList<>();
        rows = s.getSheetRows();
        int numRows = rows.size();
        assertEquals(20, numRows);
    }

    @Test
    @DisplayName("Comprueba que consulte el rango de celdas actual")
    void setRowOfSheet() {
        LinkedList<Row> rows = new LinkedList<>();
        Row r = new Row(20);
        Row r1 = new Row(20);
        rows.add(r);
        rows.add(r1);
        s.setSheetRows(rows);
        assertEquals(2, s.getSize());
    }

    @Test
    @DisplayName("Comprueba que consulte el rango de celdas")
    void getProvisionalRankSheet() {
        LinkedList<Cell> rank = new LinkedList<>();
        Cell cell1 = new NumCell(22);
        Cell cell2 = new NumCell(24);
        rank.add(cell1);
        rank.add(cell2);
        Rank r = new Rank(rank, 2,1);
        s.setProvisionalRank(r);
        assertEquals(2, s.getRankSize());
    }

    @Test
    @DisplayName("Comprueba el numero de columnas de un rango en una hoja")
    void getRankColSizeOfSheet() {
        LinkedList<Cell> rank = new LinkedList<>();
        Cell cell1 = new NumCell(22);
        Cell cell2 = new NumCell(24);
        rank.add(cell1);
        rank.add(cell2);
        Rank r = new Rank(rank, 2,1);
        s.setProvisionalRank(r);
        assertEquals(1, s.getRankColSize());
    }

    @Test
    @DisplayName("Comprueba que se realice la media de un rango")
    void averageSheet() {
        LinkedList<Cell> rank = new LinkedList<>();
        Cell cell1 = new NumCell(22);
        Cell cell2 = new NumCell(24);
        rank.add(cell1);
        rank.add(cell2);
        Rank r = new Rank(rank, 2,1);
        s.setProvisionalRank(r);
        boolean result = s.Average(3,2);
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Comprueba que se realice la mediana de un rango")
    void medianSheet() {
        LinkedList<Cell> rank = new LinkedList<>();
        Cell cell1 = new NumCell(22);
        Cell cell2 = new NumCell(24);
        rank.add(cell1);
        rank.add(cell2);
        Rank r = new Rank(rank, 2,1);
        s.setProvisionalRank(r);
        boolean result = s.Median(3,2);
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Comprueba que se realice la variancia de un rango")
    void varianceSheet() {
        LinkedList<Cell> rank = new LinkedList<>();
        Cell cell1 = new NumCell(22);
        Cell cell2 = new NumCell(24);
        rank.add(cell1);
        rank.add(cell2);
        Rank r = new Rank(rank, 2,1);
        s.setProvisionalRank(r);
        boolean result = s.Variance(3,2);
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Comprueba que se realice la desviacion estandar de un rango")
    void standardDeviationSheet() {
        LinkedList<Cell> rank = new LinkedList<>();
        Cell cell1 = new NumCell(22);
        Cell cell2 = new NumCell(24);
        rank.add(cell1);
        rank.add(cell2);
        Rank r = new Rank(rank, 2,1);
        s.setProvisionalRank(r);
        boolean result = s.StandardDeviation(3,2);
        assertEquals(true, result);
    }
}