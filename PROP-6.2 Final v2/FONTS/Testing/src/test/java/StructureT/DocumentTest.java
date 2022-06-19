package StructureT;

import Structure.Cells.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import Structure.Document;
import Structure.Sheet;

/**
 * @autor Pol Barco Martínez
 */
class DocumentTest {

    Document doc;

    @BeforeEach
    void setUp() {
        String nuevo = "PrimerDocumento";
        doc = new Document(nuevo);
    }

    @Test
    @DisplayName("Comprueba si crea nuevo documento")
    void newDocument() {
        assertEquals("PrimerDocumento", doc.getName());
    }

    @Test
    @DisplayName("Comprueba si modifica nombre del documento")
    void setNameDocument() {
        String rename = "Documento1";
        doc.setName(rename);
        assertEquals("Documento1", doc.getName());
    }

    @Test
    @DisplayName("Comprueba si se añade un grupo de hojas a la vez")
    void setSheetsOfDocument() {
        Sheet s = new Sheet("Estadistica");
        Sheet s1 = new Sheet("Numeros");
        LinkedList<Sheet> sheets = new LinkedList<>();
        sheets.add(s);
        sheets.add(s1);
        doc.setDocumentSheets(sheets);
        assertEquals(true, doc.ExistsSheet("Estadistica"));
        assertEquals(true, doc.ExistsSheet("Numeros"));
    }

    @Test
    @DisplayName("Consulta hojas documento")
    void getSheetsOfDocument() {
        LinkedList<Sheet> sheets = doc.getDocumentSheets();
        Sheet s = sheets.get(0);
        assertEquals("Sheet 0", s.getName());
    }

    @Test
    @DisplayName("Comprueba si añade nueva hoja")
    void newSheetsOfDocument() {
        doc.addSheet();
        LinkedList<Sheet> sheets = doc.getDocumentSheets();
        Sheet s = sheets.get(0);
        Sheet s1 = sheets.get(1);
        assertEquals("Sheet 0", s.getName());
        assertEquals("Sheet 1", s1.getName());
    }

    @Test
    @DisplayName("Comprueba si se borrar una hoja diciendole que hoja es")
    void removeSheetsOfDocument() {
        doc.addSheet();
        LinkedList<Sheet> sheets = doc.getDocumentSheets();
        Sheet s1 = sheets.get(1);
        doc.removeSheet(s1);
        int sizeList = sheets.size();
        assertEquals(1, sizeList);
    }

    @Test
    @DisplayName("Comprueba si se borrar una hoja dada una posición")
    void removeSheetsByPositionOfDocument() {
        for (int i = 0; i < 3; i++) {
            doc.addSheet();
        }
        LinkedList<Sheet> sheets = doc.getDocumentSheets();
        doc.removeSheetByPosition(1);
        int sizeList = sheets.size();
        assertEquals(3, sizeList);
    }

    @Test
    @DisplayName("Comprueba si se borra una hoja dado el nombre de la hoja")
    void removeSheetsByNameOfDocument() {
        for (int i = 0; i < 3; i++) {
            doc.addSheet();
        }
        assertEquals(true, doc.removeSheetByName("Sheet 0"));
        assertEquals(false, doc.removeSheetByName("Sheet 4"));
    }

    @Test
    @DisplayName("Comprueba si el índice de la hoja es correcto")
    void getIndexSheetOfDocument() {
        for (int i = 0; i < 3; i++) {
            doc.addSheet();
        }
        assertEquals(2, doc.GetIndexSheetList("Sheet 2"));
        assertNotEquals(4, doc.GetIndexSheetList("Sheet 4"));
    }

    @Test
    @DisplayName("Comprueba si existe la hoja")
    void existSheetOfDocument() {
        for (int i = 0; i < 3; i++) {
            doc.addSheet();
        }
        assertEquals(true, doc.ExistsSheet("Sheet 2"));
        assertEquals(false, doc.ExistsSheet("Sheet 4"));
    }

    @Test
    @DisplayName("Comprueba si modifica el nombre de una hoja dandole la posición de la hoja")
    void setSheetNameOfDocument() {
        doc.SetSheetName(0, "Estadisticas");
        LinkedList<Sheet> sheets = doc.getDocumentSheets();
        Sheet s = sheets.get(0);
        assertEquals("Estadisticas", s.getName());
    }

    @Test
    @DisplayName("Comprueba si existe una fila en una hoja")
    void existRowOnSheetOfDocument() {
        assertEquals(true, doc.ExistsRow(0, 12));
        assertEquals(false, doc.ExistsRow(0, 30));
    }

    @Test
    @DisplayName("Comprueba si añade filas a las hoja")
    void addRowOnSheetOfDocument() {
        doc.addRow(0, 12, 18);
        int sheetSize = doc.getSheetSize(0);
        assertEquals(32, sheetSize);
    }

    @Test
    @DisplayName("Comprueba si existe una columna en una hoja")
    void existColumnOnSheetOfDocument() {
        assertEquals(true, doc.ExistsColumn(0, 12));
        assertEquals(false, doc.ExistsColumn(0, 30));
    }

    @Test
    @DisplayName("Comprueba si añade columnas a las hoja")
    void addColumnOnSheetOfDocument() {
        doc.addColumn(0, 12, 18);
        int sheetSize = doc.getSheetColSize(0);
        assertEquals(32, sheetSize);
    }

    @Test
    @DisplayName("Comprueba si retorna todas las hojas del documento")
    void getSheetNamesOfDocument() {
        Sheet s = new Sheet("Estadistica");
        Sheet s1 = new Sheet("Numeros");
        LinkedList<Sheet> sheets = new LinkedList<>();
        sheets.add(s);
        sheets.add(s1);
        doc.setDocumentSheets(sheets);
        Vector<String> result = doc.getSheetNames();
        assertEquals("Estadistica", result.get(0));
        assertEquals("Numeros", result.get(1));
    }

    @Test
    @DisplayName("Comprueba si se borran filas de una hoja")
    void eraseRowOfSheetDocument() {
        doc.addSheet();
        assertEquals(true, doc.EraseRow(1, 2, 14));
    }

    @Test
    @DisplayName("Comprueba si se borran columnas de una hoja")
    void eraseColumnOfSheetDocument() {
        doc.addSheet();
        assertEquals(true, doc.EraseColumn(1, 2, 14));
    }

    @Test
    @DisplayName("Comprueba si selecciona un rango")
    void selectRankOfSheetDocument() {
        boolean rank = doc.SelectRank(0, 3, 5, 14, 9, 1);
        assertEquals(true, rank);
    }

    @Test
    @DisplayName("Comprueba si selecciona dos rango")
    void select2RanksOfSheetDocument() {
        boolean rank = doc.SelectRank(0, 3, 5, 14, 9, 2);
        assertEquals(true, rank);
    }

    @Test
    @DisplayName("Comprueba si puede modificar un string")
    void ModifyStringOfSheetDocument() {
        boolean modify = doc.ModifyString(0, 2, 3, "");
        assertEquals(true, modify);
    }

    @Test
    @DisplayName("Comprueba si puede modificar un double")
    void ModifyDoubleOfSheetDocument() {
        boolean modify = doc.ModifyDouble(0, 2, 3, 0.0);
        assertEquals(true, modify);
    }

    @Test
    @DisplayName("Comprueba si puede modificar un Date")
    void ModifyDateOfSheetDocument() {
        boolean modify = doc.ModifyDate(0, 2, 3, 1, 4, 2022);
        assertEquals(true, modify);
    }
}