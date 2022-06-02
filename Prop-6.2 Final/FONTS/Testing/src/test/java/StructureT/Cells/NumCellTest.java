package StructureT.Cells;

import Structure.Cells.NumCell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Javier Núñez Cerezuela
 */
class NumCellTest {

    NumCell nc = new NumCell(25);

    @Test
    void getNum() {
        assertEquals(25,nc.getNum(),"nc debe devolver 25");
    }

    @Test
    void getCellType() {
        assertEquals("NumCell", nc.getCellType(), "nc debe devolver NumCell");
    }

    @Test
    void setContent() {
        nc.setContent(10);
        assertEquals(10,nc.getNum(),"nc debe devolver 10");
    }
}