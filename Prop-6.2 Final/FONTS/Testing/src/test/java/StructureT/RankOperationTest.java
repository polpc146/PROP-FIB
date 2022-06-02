package StructureT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

import Structure.Cells.*;
import Operations.RankOperation;
import Operations.NumberOperation;

/**
 * @autor Pol Barco Martínez
 */
class RankOperationTest {

    RankOperation ro;
    NumCell cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9, cell10, cell11, cell12, cell13, cell14, cell15;
    NumberOperation no;

    @BeforeEach
    void setUp() {
        ro = new RankOperation();
        no = new NumberOperation();
        cell1 = new NumCell(120);
        cell2 = new NumCell(110);
        cell3 = new NumCell(140);
        cell4 = new NumCell(130);
        cell5 = new NumCell(125);
        cell6 = new NumCell(115);
        cell7 = new NumCell(25);
        cell8 = new NumCell(30);
        cell9 = new NumCell(35);
        cell10 = new NumCell(25);
        cell11 = new NumCell(20);
        cell12 = new NumCell(20);
        cell13 = new NumCell(14);
        cell14 = new NumCell(36);
        cell15 = new NumCell(27);
    }

    @Test
    @DisplayName("Comprueba que la media truncada de RankCellX da 123.0")
    void averageRankCellX() {
        LinkedList<Cell> RankCellX = new LinkedList<>();
        RankCellX.add(cell1);
        RankCellX.add(cell2);
        RankCellX.add(cell3);
        RankCellX.add(cell4);
        RankCellX.add(cell5);
        RankCellX.add(cell6);
        double result = no.Truncate(ro.Average(RankCellX));
        assertEquals(123.0, result);
    }

    @Test
    @DisplayName("Comprueba que la media truncada de RankCellY da 25")
    void averageRankCellY() {
        LinkedList<Cell> RankCellY = new LinkedList<>();
        RankCellY.add(cell7);
        RankCellY.add(cell8);
        RankCellY.add(cell9);
        RankCellY.add(cell10);
        RankCellY.add(cell11);
        RankCellY.add(cell12);
        double result = no.Truncate(ro.Average(RankCellY));
        assertEquals(25, result);
    }

    @Test
    @DisplayName("Comprueba que la mediana truncada de RankCellX da 112.0")
    void medianRankCellX() {
        LinkedList<Cell> RankCellX = new LinkedList<>();
        RankCellX.add(cell1);
        RankCellX.add(cell2);
        RankCellX.add(cell3);
        RankCellX.add(cell4);
        RankCellX.add(cell5);
        RankCellX.add(cell6);
        double result = no.Truncate(ro.Median(RankCellX));
        assertEquals(122.0, result);
    }

    @Test
    @DisplayName("Comprueba que la mediana truncada de RankCellY da 25.0")
    void medianRankCellY() {
        LinkedList<Cell> RankCellY = new LinkedList<>();
        RankCellY.add(cell7);
        RankCellY.add(cell8);
        RankCellY.add(cell9);
        RankCellY.add(cell10);
        RankCellY.add(cell11);
        RankCellY.add(cell12);
        double result = no.Truncate(ro.Median(RankCellY));
        assertEquals(25.0, result);
    }

    @Test
    @DisplayName("Comprueba que la mediana truncada de RankCellZ da 27")
    void medianRankCellZ() {
        LinkedList<Cell> RankCellZ = new LinkedList<>();
        RankCellZ.add(cell13);
        RankCellZ.add(cell14);
        RankCellZ.add(cell15);
        double result = no.Truncate(ro.Median(RankCellZ));
        assertEquals(27, result);
    }

    @Test
    @DisplayName("Comprueba que la variancia truncada de RankCellX da 97")
    void varianceRankCellX() {
        LinkedList<Cell> RankCellX = new LinkedList<>();
        RankCellX.add(cell1);
        RankCellX.add(cell2);
        RankCellX.add(cell3);
        RankCellX.add(cell4);
        RankCellX.add(cell5);
        RankCellX.add(cell6);
        double result = no.Truncate(ro.Variance(RankCellX));
        assertEquals(97, result);
    }

    @Test
    @DisplayName("Comprueba que la variancia truncada de RankCellY da 28")
    void varianceRankCellY() {
        LinkedList<Cell> RankCellY = new LinkedList<>();
        RankCellY.add(cell7);
        RankCellY.add(cell8);
        RankCellY.add(cell9);
        RankCellY.add(cell10);
        RankCellY.add(cell11);
        RankCellY.add(cell12);
        double result = no.Truncate(ro.Variance(RankCellY));
        assertEquals(28, result);
    }

    @Test
    @DisplayName("Comprueba que la covariancia truncada de RankCellX y RankCellY da 22")
    void covarianceXY() {
        LinkedList<Cell> RankCellX = new LinkedList<>();
        RankCellX.add(cell1);
        RankCellX.add(cell2);
        RankCellX.add(cell3);
        RankCellX.add(cell4);
        RankCellX.add(cell5);
        RankCellX.add(cell6);

        LinkedList<Cell> RankCellY = new LinkedList<>();
        RankCellY.add(cell7);
        RankCellY.add(cell8);
        RankCellY.add(cell9);
        RankCellY.add(cell10);
        RankCellY.add(cell11);
        RankCellY.add(cell12);

        double result = no.Truncate(ro.Covariance(RankCellX, RankCellY));
        assertEquals(22, result);
    }

    @Test
    @DisplayName("Comprueba que la desviacion estandard truncada de RankCellX da 9")
    void standardDeviationRankCellX() {
        LinkedList<Cell> RankCellX = new LinkedList<>();
        RankCellX.add(cell1);
        RankCellX.add(cell2);
        RankCellX.add(cell3);
        RankCellX.add(cell4);
        RankCellX.add(cell5);
        RankCellX.add(cell6);
        double result = no.Truncate(ro.StandardDeviation(RankCellX));
        assertEquals(9, result);
    }

    @Test
    @DisplayName("Comprueba que la desviacion estandard truncada de RankCellY da 5")
    void standardDeviationRankCellY() {
        LinkedList<Cell> RankCellY = new LinkedList<>();
        RankCellY.add(cell7);
        RankCellY.add(cell8);
        RankCellY.add(cell9);
        RankCellY.add(cell10);
        RankCellY.add(cell11);
        RankCellY.add(cell12);
        double result = no.Truncate(ro.StandardDeviation(RankCellY));
        assertEquals(5 , result);
    }

    @Test
    @DisplayName("Comprueba que la correlacion de pearson truncada de RankCellX y RankCellY da 0.42237094187877133")
    void pearsonXY() {
        LinkedList<Cell> RankCellX = new LinkedList<>();
        RankCellX.add(cell1);
        RankCellX.add(cell2);
        RankCellX.add(cell3);
        RankCellX.add(cell4);
        RankCellX.add(cell5);
        RankCellX.add(cell6);

        LinkedList<Cell> RankCellY = new LinkedList<>();
        RankCellY.add(cell7);
        RankCellY.add(cell8);
        RankCellY.add(cell9);
        RankCellY.add(cell10);
        RankCellY.add(cell11);
        RankCellY.add(cell12);

        double result = ro.Pearson(RankCellX, RankCellY);
        assertEquals(0.42237094187877133, result);
    }
}