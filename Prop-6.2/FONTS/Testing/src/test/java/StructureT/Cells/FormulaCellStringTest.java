package StructureT.Cells;

import Structure.Cells.FormulaCellString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Javier Núñez Cerezuela
 */
class FormulaCellStringTest {

    FormulaCellString fcs1 = new FormulaCellString("=A","hola");
    FormulaCellString fcs2 = new FormulaCellString("=A","adios");

    @Test
    void testGetCellType() {
        Assertions.assertEquals("FormulaCellString", fcs1.getCellType(), "El objeto fcs1 debería responder FormulaCellString");
        Assertions.assertEquals("FormulaCellString", fcs2.getCellType(), "El objeto fcs2 debería responder FormulaCellString");
    }

    @Test
    void testGetString() {
        Assertions.assertEquals("hola", fcs1.getString(), "El objeto fcs1 debería responder hola");
        Assertions.assertEquals("adios", fcs2.getString(), "El objeto fcs2 debería responder adios");
    }
}