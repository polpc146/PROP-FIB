package StructureT.Cells;

import Structure.Cells.StringCell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Javier Núñez Cerezuela
 */
class StringCellTest {

    StringCell sc = new StringCell("hola");

    @Test
    void getString() {
        Assertions.assertEquals("hola", sc.getString(), "sc debería responder hola");

    }

    @Test
    void getCellType() {
        Assertions.assertEquals("StringCell", sc.getCellType(), "sc debería responder StringCell");
    }

    @Test
    void setContent() {
        sc.setContent("adios");
        Assertions.assertEquals("adios",sc.getString(),"sc deberia responder adios");
    }
}