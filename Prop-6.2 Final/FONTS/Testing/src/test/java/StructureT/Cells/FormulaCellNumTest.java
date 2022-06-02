package StructureT.Cells;

import Structure.Cells.FormulaCellNum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Javier Núñez Cerezuela
 */
class FormulaCellNumTest {

    FormulaCellNum fcn1 = new FormulaCellNum("=B", 10);
    FormulaCellNum fcn2 = new FormulaCellNum("=B", 20);

    @Test
    void testGetCellType() {
        assertEquals("FormulaCellNum", fcn1.getCellType(),"fcn1 debe devolver FormulaCellNum");
        assertEquals("FormulaCellNum", fcn2.getCellType(),"fcn1 debe devolver FormulaCellNum");
    }

    @Test
    void getNum() {
        assertEquals(10, fcn1.getNum(),"fcn1 debe devolver 10");
        assertEquals(20, fcn2.getNum(),"fcn1 debe devolver 20");
    }
}