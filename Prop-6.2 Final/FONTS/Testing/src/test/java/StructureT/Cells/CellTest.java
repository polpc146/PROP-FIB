package StructureT.Cells;

import Structure.Cells.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Javier Núñez Cerezuela
 */
class CellTest {

    Cell cell = new Cell();
    NumCell numCell = new NumCell(90);
    StringCell stringCell = new StringCell("hola");

    GregorianCalendar c1 = new GregorianCalendar(0,0,1);
    Date dError = c1.getTime();
    DateCell datecell = new DateCell(dError);
    GregorianCalendar c2 = new GregorianCalendar(2000,4,4);
    Date d = c2.getTime();
    DateCell dateCell = new DateCell(d);

    FormulaCell formulaCell = new FormulaCell("=AAA");
    FormulaCellNum formulaCellNum = new FormulaCellNum("=B", 10);
    FormulaCellString formulaCellString = new FormulaCellString("=C", "adios");
    FormulaCellDate formulaCellDate = new FormulaCellDate("=D", d);

    @BeforeEach
    void setUp(){
        Cell cell = new Cell();
        NumCell numcell = new NumCell(90);
        StringCell stringCell = new StringCell("hola");

        GregorianCalendar c = new GregorianCalendar(2000,4,4);
        Date d = c.getTime();
        DateCell datecell = new DateCell(d);

        FormulaCell formulaCell = new FormulaCell("=AAA");
        FormulaCellNum formulaCellNum = new FormulaCellNum("=B", 10);
        FormulaCellString formulaCellString = new FormulaCellString("=C", "adios");
        FormulaCellDate formulaCellDate = new FormulaCellDate("=D", d);
    }

    @Test
    @DisplayName("getCellType deberia retornar el tipo de celda")
    void testGetCellType(){
        Assertions.assertEquals("Cell", cell.getCellType(), "El objeto cell debería responder Cell");
        Assertions.assertEquals("NumCell", numCell.getCellType(), "El objeto numCell debería responder NumCell");
        Assertions.assertEquals("StringCell", stringCell.getCellType(), "El objeto stringCell debería responder StringCell");
        Assertions.assertEquals("DateCell", dateCell.getCellType(), "El objeto dateCell debería responder DateCell");

        Assertions.assertEquals("FormulaCell", formulaCell.getCellType(), "El objeto formulaCell debería responder FormulaCell");
        Assertions.assertEquals("FormulaCellNum", formulaCellNum.getCellType(), "El objeto formulaCellNum debería responder FormulaCellNum");
        Assertions.assertEquals("FormulaCellString", formulaCellString.getCellType(), "El objeto formulaCellString debería responder FormulaCellString");
        Assertions.assertEquals("FormulaCellDate", formulaCellDate.getCellType(), "El objeto formulaCellDate debería responder FormulaCellDate");
    }

    @Test
    @DisplayName("getNum deberia retornar el double si tiene i 35505 en caso contrario")
    void testGetNum(){
        Assertions.assertEquals(90, numCell.getNum(), "El objeto numCell debería responder 90");
        Assertions.assertEquals(10, formulaCellNum.getNum(), "El objeto formulaCellNum debería responder 10");

        Assertions.assertEquals(35505, cell.getNum(), "El objeto cell debería responder 35505");
        Assertions.assertEquals(35505, stringCell.getNum(), "El objeto stringCell debería responder 35505");
        Assertions.assertEquals(35505, dateCell.getNum(), "El objeto dateCell debería responder 35505");

        Assertions.assertEquals(35505, formulaCell.getNum(), "El objeto formulaCell debería responder 35505");
        Assertions.assertEquals(35505, formulaCellString.getNum(), "El objeto formulaCellString debería responder 35505");
        Assertions.assertEquals(35505, formulaCellDate.getNum(), "El objeto formulaCellDate debería responder 35505");
    }

    @Test
    @DisplayName("getString deberia retornar el string si tiene i ERROR en caso contrario")
    void testGetString(){
        Assertions.assertEquals("hola", stringCell.getString(), "El objeto stringCell debería responder hola");
        Assertions.assertEquals("adios", formulaCellString.getString(), "El objeto formulaCellString debería responder hola");

        Assertions.assertEquals("ERROR", cell.getString(), "El objeto cell debería responder ERROR");
        Assertions.assertEquals("ERROR", numCell.getString(), "El objeto numCell debería responder ERROR");
        Assertions.assertEquals("ERROR", dateCell.getString(), "El objeto dateCell debería responder ERROR");

        Assertions.assertEquals("ERROR", formulaCell.getString(), "El objeto formulaCell debería responder ERROR");
        Assertions.assertEquals("ERROR", formulaCellNum.getString(), "El objeto formulaCellNum debería responder ERROR");
        Assertions.assertEquals("ERROR", formulaCellDate.getString(), "El objeto formulaCellDate debería responder ERROR");
    }

    @Test
    @DisplayName("getDate deberia retornar la date correcta i 1/1/0 en caso contario")
    void testGetDate(){
        Assertions.assertEquals(d,dateCell.getDate(),"El objeto dateCell debería responder d");
        Assertions.assertEquals(d,formulaCellDate.getDate(),"El objeto formulaCellDate debería responder d");

        Assertions.assertEquals(dError,cell.getDate(),"El objeto dateCell debería responder 1/1/0");
        Assertions.assertEquals(dError,numCell.getDate(),"El objeto numCell debería responder 1/1/0");
        Assertions.assertEquals(dError,stringCell.getDate(),"El objeto stringCell debería responder 1/1/0");

        Assertions.assertEquals(dError,formulaCell.getDate(),"El objeto formulaCell debería responder 1/1/0");
        Assertions.assertEquals(dError,formulaCellNum.getDate(),"El objeto formulaCellNum debería responder 1/1/0");
        Assertions.assertEquals(dError,formulaCellString.getDate(),"El objeto formulaCellString debería responder 1/1/0");
    }

    @Test
    @DisplayName("setContent(double) deberia modificar el contenido de la celda solo si se trata de una NumCell")
    void testSetContentDb(){
        cell.setContent(99);
        numCell.setContent(99);
        stringCell.setContent(99);
        dateCell.setContent(99);

        formulaCell.setContent(99);
        formulaCellNum.setContent(99);
        formulaCellString.setContent(99);
        formulaCellDate.setContent(99);

        Assertions.assertEquals(99,numCell.getNum(),"El objeto numCell deberia responder 99");
        Assertions.assertEquals(10,formulaCellNum.getNum(),"El objeto formulaCellNum deberia responder 10");

        Assertions.assertEquals(35505, cell.getNum(), "El objeto cell debería responder 35505");
        Assertions.assertEquals(35505, stringCell.getNum(), "El objeto stringCell debería responder 35505");
        Assertions.assertEquals(35505, dateCell.getNum(), "El objeto dateCell debería responder 35505");

        Assertions.assertEquals(35505, formulaCell.getNum(), "El objeto formulaCell debería responder 35505");
        Assertions.assertEquals(35505, formulaCellString.getNum(), "El objeto formulaCellString debería responder 35505");
        Assertions.assertEquals(35505, formulaCellDate.getNum(), "El objeto formulaCellDate debería responder 35505");

    }

    @Test
    @DisplayName("setContent(String) deberia modificar el contenido de la celda solo si se trata de una StringCell")
    void testSetContentS(){
        cell.setContent("hi");
        numCell.setContent("hi");
        stringCell.setContent("hi");
        dateCell.setContent("hi");

        formulaCell.setContent("hi");
        formulaCellNum.setContent("hi");
        formulaCellString.setContent("hi");
        formulaCellDate.setContent("hi");

        Assertions.assertEquals("hi", stringCell.getString(), "El objeto stringCell debería responder hi");
        Assertions.assertEquals("adios", formulaCellString.getString(), "El objeto formulaCellString debería responder hola");

        Assertions.assertEquals("ERROR", cell.getString(), "El objeto cell debería responder ERROR");
        Assertions.assertEquals("ERROR", numCell.getString(), "El objeto numCell debería responder ERROR");
        Assertions.assertEquals("ERROR", dateCell.getString(), "El objeto dateCell debería responder ERROR");

        Assertions.assertEquals("ERROR", formulaCell.getString(), "El objeto formulaCell debería responder ERROR");
        Assertions.assertEquals("ERROR", formulaCellNum.getString(), "El objeto formulaCellNum debería responder ERROR");
        Assertions.assertEquals("ERROR", formulaCellDate.getString(), "El objeto formulaCellDate debería responder ERROR");
    }

    @Test
    @DisplayName("setContent(Data) deberia modificar el contendido de la celda solo si se trata de una DateCell")
    void testSetContentsDt(){
        GregorianCalendar c3 = new GregorianCalendar(50,10,15);
        Date dMod = c3.getTime();

        cell.setContent(dMod);
        numCell.setContent(dMod);
        stringCell.setContent(dMod);
        dateCell.setContent(dMod);

        formulaCell.setContent(dMod);
        formulaCellNum.setContent(dMod);
        formulaCellString.setContent(dMod);
        formulaCellDate.setContent(dMod);

        Assertions.assertEquals(dMod,dateCell.getDate(),"El objeto dateCell debería responder dMod");
        Assertions.assertEquals(d,formulaCellDate.getDate(),"El objeto formulaCellDate debería responder d");

        Assertions.assertEquals(dError,cell.getDate(),"El objeto dateCell debería responder 1/1/0");
        Assertions.assertEquals(dError,numCell.getDate(),"El objeto numCell debería responder 1/1/0");
        Assertions.assertEquals(dError,stringCell.getDate(),"El objeto stringCell debería responder 1/1/0");

        Assertions.assertEquals(dError,formulaCell.getDate(),"El objeto formulaCell debería responder 1/1/0");
        Assertions.assertEquals(dError,formulaCellNum.getDate(),"El objeto formulaCellNum debería responder 1/1/0");
        Assertions.assertEquals(dError,formulaCellString.getDate(),"El objeto formulaCellString debería responder 1/1/0");


    }

    @Test
    @DisplayName("addReferenced debería añadir celdas a referencedBy, " +
            "getReferencedBy debería devolver cell.referencedBy y" +
            "deleteReferenced debería eliminar la celda que se le indique")
    void testAddDeleteReferenced(){
        cell.addReferenced(formulaCell);
        cell.addReferenced(formulaCellDate);
        cell.addReferenced(formulaCellNum);
        cell.addReferenced(formulaCellString);

        LinkedList<FormulaCell> expectedList = new LinkedList<FormulaCell>();
        expectedList.add(formulaCell);
        expectedList.add(formulaCellDate);
        expectedList.add(formulaCellNum);
        expectedList.add(formulaCellString);

        Assertions.assertEquals(expectedList,cell.getReferencedBy(),"expectedList debería ser igual a el return de getReferencedBy");

        expectedList.remove(formulaCellNum);
        cell.deleteReferenced(formulaCellNum);
        Assertions.assertEquals(expectedList,cell.getReferencedBy(),"expectedList debería ser igual a el return de getReferencedBy");

        expectedList.remove(formulaCellString);
        cell.deleteReferenced(formulaCellString);
        Assertions.assertEquals(expectedList,cell.getReferencedBy(),"expectedList debería ser igual a el return de getReferencedBy");

        expectedList.remove(formulaCell);
        cell.deleteReferenced(formulaCell);
        Assertions.assertEquals(expectedList,cell.getReferencedBy(),"expectedList debería ser igual a el return de getReferencedBy");

        expectedList.remove(formulaCellDate);
        cell.deleteReferenced(formulaCellDate);
        Assertions.assertEquals(expectedList,cell.getReferencedBy(),"expectedList debería ser igual a el return de getReferencedBy");

    }

}