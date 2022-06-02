package StructureT.Cells;

import Structure.Cells.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Javier Núñez Cerezuela
 */
class FormulaCellTest {

    GregorianCalendar c1 = new GregorianCalendar(2000,4,4);
    Date d = c1.getTime();
    DateCell dateCell = new DateCell(d);

    FormulaCell formulaCell = new FormulaCell("=A");
    FormulaCellNum formulaCellNum = new FormulaCellNum("=B", 10);
    FormulaCellString formulaCellString = new FormulaCellString("=C", "adios");
    FormulaCellDate formulaCellDate = new FormulaCellDate("=D", d);

    @BeforeEach
    void setUp(){
        GregorianCalendar c = new GregorianCalendar(2000,4,4);
        Date d = c.getTime();
        DateCell datecell = new DateCell(d);

        FormulaCell formulaCell = new FormulaCell("=AAA");
        FormulaCellNum formulaCellNum = new FormulaCellNum("=B", 10);
        FormulaCellString formulaCellString = new FormulaCellString("=C", "adios");
        FormulaCellDate formulaCellDate = new FormulaCellDate("=D", d);
    }

    @Test
    void testGetCellType() {
        Assertions.assertEquals("FormulaCell", formulaCell.getCellType(), "El objeto formulaCell debería responder FormulaCell");
        Assertions.assertEquals("FormulaCellNum", formulaCellNum.getCellType(), "El objeto formulaCellNum debería responder FormulaCellNum");
        Assertions.assertEquals("FormulaCellString", formulaCellString.getCellType(), "El objeto formulaCellString debería responder FormulaCellString");
        Assertions.assertEquals("FormulaCellDate", formulaCellDate.getCellType(), "El objeto formulaCellDate debería responder FormulaCellDate");
    }

    @Test
    void testGetFormula() {
        Assertions.assertEquals("=A", formulaCell.getFormula(), "El objeto formulaCell debería responder =A");
        Assertions.assertEquals("=B", formulaCellNum.getFormula(), "El objeto formulaCellNum debería responder =B");
        Assertions.assertEquals("=C", formulaCellString.getFormula(), "El objeto formulaCellString debería responder =C");
        Assertions.assertEquals("=D", formulaCellDate.getFormula(), "El objeto formulaCellDate debería responder =D");
    }

    @Test
    void testGetReferences() {
        LinkedList<Cell> refs = new LinkedList<Cell>();
        refs.add(formulaCell);
        refs.add(formulaCellNum);
        refs.add(formulaCellString);
        refs.add(formulaCellDate);

        FormulaCell fcr = new FormulaCell("=E", refs);

        assertEquals(refs, fcr.getReferences(), "frc debería devolver las mismas referencias que con la que se creó");
    }

    @Test
    void testGetNum() {
        Assertions.assertEquals(10, formulaCellNum.getNum(), "El objeto formulaCellNum debería responder 10");

        Assertions.assertEquals(35505, formulaCell.getNum(), "El objeto formulaCell debería responder 35505");
        Assertions.assertEquals(35505, formulaCellString.getNum(), "El objeto formulaCellString debería responder 35505");
        Assertions.assertEquals(35505, formulaCellDate.getNum(), "El objeto formulaCellDate debería responder 35505");
    }

    @Test
    @DisplayName("getString deberia retornar el string si tiene i ERROR en caso contrario")
    void testGetString(){
        Assertions.assertEquals("adios", formulaCellString.getString(), "El objeto formulaCellString debería responder hola");

        Assertions.assertEquals("ERROR", formulaCell.getString(), "El objeto formulaCell debería responder ERROR");
        Assertions.assertEquals("ERROR", formulaCellNum.getString(), "El objeto formulaCellNum debería responder ERROR");
        Assertions.assertEquals("ERROR", formulaCellDate.getString(), "El objeto formulaCellDate debería responder ERROR");
    }

    @Test
    @DisplayName("getDate deberia retornar la date correcta i 1/1/0 en caso contario")
    void testGetDate(){
        GregorianCalendar c1 = new GregorianCalendar(0,0,1);
        Date dError = c1.getTime();
        Assertions.assertEquals(d,formulaCellDate.getDate(),"El objeto formulaCellDate debería responder d");

        Assertions.assertEquals(dError,formulaCell.getDate(),"El objeto formulaCell debería responder 1/1/0");
        Assertions.assertEquals(dError,formulaCellNum.getDate(),"El objeto formulaCellNum debería responder 1/1/0");
        Assertions.assertEquals(dError,formulaCellString.getDate(),"El objeto formulaCellString debería responder 1/1/0");
    }
}