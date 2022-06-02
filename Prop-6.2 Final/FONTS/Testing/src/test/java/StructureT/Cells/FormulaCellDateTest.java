package StructureT.Cells;

import Structure.Cells.FormulaCellDate;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Javier Núñez Cerezuela
 */
class FormulaCellDateTest {

    GregorianCalendar c1 = new GregorianCalendar(2000,4,4);
    Date d = c1.getTime();
    FormulaCellDate fcd = new FormulaCellDate("=a",d);

    @Test
    void testGetCellType() {
        assertEquals("FormulaCellDate", fcd.getCellType(),"fcd debería devolver FormulaCellDate");
    }

    @Test
    void getDate() {
        assertEquals(d,fcd.getDate(),"fcd debería devolver la fecha d");
    }
}