package StructureT.Cells;

import Structure.Cells.DateCell;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Javier Núñez Cerezuela
 */
class DateCellTest {

    GregorianCalendar c1 = new GregorianCalendar(2000,4,4);
    Date d = c1.getTime();
    DateCell dateCell = new DateCell(d);

    @Test
    void getDate() {
        assertEquals(d,dateCell.getDate(),"dateCell debe devolver d");
    }

    @Test
    void getCellType() {
        assertEquals("DateCell",dateCell.getCellType(),"datecell deberia devolver DateCell");
    }

    @Test
    void setContent() {
        GregorianCalendar c2 = new GregorianCalendar(1000,2,2);
        Date d2 = c2.getTime();

        dateCell.setContent(d2);
        assertEquals(d2,dateCell.getDate(),"datecell debe devolver la fecha actualizada");
    }
}