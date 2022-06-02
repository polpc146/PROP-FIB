package Structure;

import java.util.*;

/**
 * Un documento tiene un nombre y la lista de hojas que pertenecen al documento
 * @author Pol Pérez Castillo
 */
public class Document {

    private String name;
    private LinkedList<Sheet> documentSheets;

    /**
     * Crea un documento con el nombre name y inicializa una lista de hojas del documento
     * @param name nombre que se le asigna al documento
     */
    public Document (String name) {

        this.name = name;
        documentSheets = new LinkedList<>();
        documentSheets.add(new Sheet(nextSheetName()));
    }

    /**
     * Consulta nombre del documento
     * @return nombre del documento
     */
    public String getName() {

        return name;
    }

    /**
     * Consulta hojas del documento
     * @return hojas del documento
     */
    public LinkedList<Sheet> getDocumentSheets() {

        return documentSheets;
    }

    /**
     * Modifica nombre del documento
     * @param name nuevo nombre del documento
     * @return true
     */
    public boolean setName(String name) {

        this.name = name;
        return true;
    }

    /**
     * Modifica hojas del documento
     * @param documentSheets nuevas hojas del documento
     * @return true
     */
    public boolean setDocumentSheets(LinkedList<Sheet> documentSheets) {

        this.documentSheets = documentSheets;
        return true;
    }

    /**
     * Consulta nombre que debería tener la siguiente hoja que se añada al documento
     * @return nombre de la siguiente hoja
     */
    public String nextSheetName() {

        String provisionalName = "Sheet ";
        int size = documentSheets.size();
        return provisionalName+size;
    }

    /**
     * Añade una hoja
     * @return true
     */
    public boolean addSheet () {

        documentSheets.add(new Sheet(nextSheetName()));
        return true;
    }

    /**
     * Elimina una hoja
     * @param sheetToDelete hoja a eliminar
     * @return true
     */
    public boolean removeSheet (Sheet sheetToDelete) {

        documentSheets.remove(sheetToDelete);
        return true;
    }

    /**
     * Elimina una hoja
     * @param pos posición de la hoja
     * @return true
     */
    public boolean removeSheetByPosition (int pos) {

        documentSheets.remove(pos);
        return true;
    }

    /**
     * Elimina una hoja
     * @param name nombre de la hoja
     * @return true si se ha eliminado la hoja, false en caso contrario
     */
    public boolean removeSheetByName (String name) {

        int size = documentSheets.size();
        for (int i = 0; i < size; i++) {

            if (documentSheets.get(i).getName().equalsIgnoreCase(name)) {

                documentSheets.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Consulta posición de la hoja en la lista ed hojas
     * @param nameSheet nombre de la hoja
     * @return posición de la hoja en la lista de hojas si existe la hoja, -1 en caso contrario
     */
    public int GetIndexSheetList (String nameSheet) {

        for (int i = 0; i < documentSheets.size(); i++) {

            if (documentSheets.get(i).getName().equalsIgnoreCase(nameSheet)) return i;
        }
        return -1;
    }

    /**
     * Consulta existencia de una hoja
     * @param nameSheet nombre de la hoja
     * @return true si existe la hoja, false en caso contrario
     */
    public boolean ExistsSheet (String nameSheet) {

        for (Sheet s: documentSheets) {

            if (s.getName().equalsIgnoreCase(nameSheet)) return true;
        }
        return false;
    }

    /**
     * Modifica nombre de una hoja
     * @param index posición de la hoja
     * @param newName nuevo nombre de la hoja
     * @return true
     */
    public boolean SetSheetName (int index, String newName) {

        documentSheets.get(index).setName(newName);
        return true;
    }

    /**
     * Consulta existencia de una fila en una hoja
     * @param index posición de la hoja
     * @param IndexRow posición de la fila
     * @return true si existe la fila, false en caso contrario
     */
    public boolean ExistsRow(int index, int IndexRow) {

        return IndexRow < documentSheets.get(index).getSize() && IndexRow >= 0;
    }

    /**
     * Añade fila/s en una hoja
     * @param index posición de la hoja
     * @param NumRows número de filas a añadir
     * @param IndexRow posición de la primera fila a añadir
     * @return true
     */
    public boolean addRow(int index, int NumRows,int IndexRow) {

        for (int i = 0; i < NumRows; i++) if(!documentSheets.get(index).addRow(IndexRow)) return false;
        return true;
    }

    /**
     * Consulta existencia de una columna en una hoja
     * @param index posición de la hoja
     * @param IndexColumn posición de la columna
     * @return true si existe la columna, false en caso contrario
     */
    public boolean ExistsColumn (int index, int IndexColumn) {

        return IndexColumn < documentSheets.get(index).getRowSize() && IndexColumn >= 0;
    }

    /**
     * Añade columna/s en una hoja
     * @param index posición de la hoja
     * @param NumColumns número de columnas a añadir
     * @param IndexColum posición de la primera columna a añadir
     * @return true
     */
    public boolean addColumn (int index, int NumColumns, int IndexColum) {

        for (int i = 0; i < NumColumns; i++) if(!documentSheets.get(index).addColumn(IndexColum+i)) return false;
        return true;
    }

    /**
     * Consulta nombres de las hojas del documento
     * @return nombres de las hojas del documento
     */
    public Vector<String> getSheetNames() {

        Vector<String> SheetNames = new Vector<>();
        for (Sheet s : documentSheets) SheetNames.add(s.getName());
        return SheetNames;
    }

    /**
     * Consulta tamaño de una hoja en filas
     * @param index posición de la hoja
     * @return número de filas de la hoja
     */
    public int getSheetSize (int index) {

        return documentSheets.get(index).getSize();
    }

    /**
     * Consulta tamaño de una hoja en columnas
     * @param index posición de la hoja
     * @return número de columnas de la hoja
     */
    public int getSheetColSize (int index) {

        return documentSheets.get(index).getRowSize();
    }

    /**
     * Consulta tamaño del rango seleccionado en filas
     * @param index posición de la hoja
     * @return número de filas del rango
     */
    public int getRankSize (int index) {

        return documentSheets.get(index).getRankSize();
    }

    /**
     * Consulta tamaño del rango seleccionado en columnas
     * @param index posición de la hoja
     * @return número de columnas del rango
     */
    public int getRankColSize (int index) {

        return documentSheets.get(index).getRankColSize();
    }

    /**
     * Elimina fila/s en una hoja
     * @param index posición de la hoja
     * @param NumRows número de filas a eliminar
     * @param IndexRow posición de la primera fila a eliminar
     * @return true
     */
    public boolean EraseRow (int index, int NumRows, int IndexRow) {

        for (int i = 0; i < NumRows; i++) if(!documentSheets.get(index).removeRow(IndexRow)) return false;
        return true;
    }

    /**
     * Elimina columna/s en una hoja
     * @param index posición de la hoja
     * @param NumColumns número de columnas a eliminar
     * @param IndexColum posición de la primera columna a eliminar
     * @return true
     */
    public boolean EraseColumn (int index, int NumColumns, int IndexColum) {

        for (int i = 0; i < NumColumns; i++) if(!documentSheets.get(index).removeColumn(IndexColum)) return false;
        return true;
    }

    /**
     * Crea nuevo bloque de celdas
     * @param index posición de la hoja
     * @param RowCellIni fila de la celda inicial
     * @param ColumnCellIni columna de la celda inicial
     * @param RowCellFin fila de la celda final
     * @param ColumnCellFin columna de la celda final
     * @return true si se crea el bloque, false en caso contrario
     */
    public boolean SelectRank(int index, int RowCellIni, int ColumnCellIni, int RowCellFin, int ColumnCellFin, int rank) {

        return documentSheets.get(index).newRank(RowCellIni, ColumnCellIni, RowCellFin, ColumnCellFin, rank);
    }

    /**
     * Ordena bloque de celdas
     * @param NameSheet nombre de la hoja
     * @param Criterium criterio de ordenación
     * @return true si se ha ordenado el bloque, false en caso contrario
     */
    public boolean SortRank (String NameSheet, String Criterium) {

        int index = GetIndexSheetList(NameSheet);
        return documentSheets.get(index).SortRank(Criterium);
    }

    /**
     * Elimina el contenido de las celdas del bloque referenciado y lo engancha a partir de la celda seleccionada
     * por el usuario
     * @param index posición de la hoja
     * @param rowIni fila de la celda donde enganchar
     * @param colIni columna de la celda donde enganchar
     * @param numRows tamaño del bloque en filas
     * @param numColumns tamaño del bloque en columnas
     * @return true si se ha cortado y pegado el bloque, false en caso contrario
     */
    public boolean PasteRank (int index, int rowIni, int colIni, int numRows, int numColumns) {

        return documentSheets.get(index).PasteRank(rowIni, colIni, numRows, numColumns);
    }

    /**
     * Copia el contenido de las celdas del bloque referenciado y lo engancha a partir de la celda seleccionada
     * por el usuario
     * @param index posición de la hoja
     * @param rowIni fila de la celda donde enganchar
     * @param colIni columna de la celda donde enganchar
     * @param numRows tamaño del bloque en filas
     * @param numColumns tamaño del bloque en columnas
     * @return true si se ha copiado el bloque, false en caso contrario
     */
    public boolean CopyRank (int index, int rowIni, int colIni, int numRows, int numColumns) {

        return documentSheets.get(index).CopyRank(rowIni, colIni, numRows, numColumns);
    }

    /**
     * Modifica el contenido de una celda a un String
     * @param index posición de la hoja
     * @param row fila de la celda
     * @param column columna de la celda
     * @param content nuevo contenido de la celda
     * @return true si se ha modificado el contenido, false en caso contrario
     */
    public boolean ModifyString (int index, int row, int column, String content) {

        return documentSheets.get(index).ModifyString(row, column, content);
    }

    /**
     * Modifica el contenido de una celda a un double
     * @param index posición de la hoja
     * @param row fila de la celda
     * @param column columna de la celda
     * @param content nuevo contenido de la celda
     * @return true si se ha modificado el contenido, false en caso contrario
     */
    public boolean ModifyDouble (int index, int row, int column, double content) {

        return documentSheets.get(index).ModifyDouble(row, column, content);
    }

    /**
     * Modifica el contenido de una celda a una Date
     * @param index posición de la hoja
     * @param row fila de la celda
     * @param column columna de la celda
     * @param day dia
     * @param month mes
     * @param year año
     * @return true si se ha modificado el contenido, false en caso contrario
     */
    public boolean ModifyDate (int index, int row, int column, int day, int month, int year) {

        GregorianCalendar calendar = new GregorianCalendar(year, month-1, day);
        Date date = calendar.getTime();
        return documentSheets.get(index).ModifyDate(row, column, date);
    }

    /**
     * Calcula la media del bloque seleccionado
     * @param index posición de la hoja
     * @param row fila del resultado
     * @param col columna del resultado
     * @return true si se ha calculado la media, false en caso contrario
     */
    public boolean Average (int index, int row, int col) {

        return documentSheets.get(index).Average(row, col);
    }

    /**
     * Calcula la mediana del bloque seleccionado
     * @param index posición de la hoja
     * @param row fila del resultado
     * @param col columna del resultado
     * @return true si se ha calculado la mediana, false en caso contrario
     */
    public boolean Median (int index, int row, int col) {

        return documentSheets.get(index).Median(row, col);
    }

    /**
     * Calcula la varianza del bloque seleccionado
     * @param index posición de la hoja
     * @param row fila del resultado
     * @param col columna del resultado
     * @return true si se ha calculado la varianza, false en caso contrario
     */
    public boolean Variance (int index, int row, int col) {

        return documentSheets.get(index).Variance(row, col);
    }

    /**
     * Calcula la covarianza del bloque seleccionado
     * @param index posición de la hoja
     * @param row fila del resultado
     * @param col columna del resultado
     * @return true si se ha calculado la covarianza, false en caso contrario
     */
    public boolean Covariance (int index, int row, int col) {

        return documentSheets.get(index).Covariance(row, col);
    }

    /**
     * Calcula la desviación estándar del bloque seleccionado
     * @param index posición de la hoja
     * @param row fila del resultado
     * @param col columna del resultado
     * @return true si se ha calculado la desviación estándar, false en caso contrario
     */
    public boolean StandardDeviation (int index, int row, int col) {

        return documentSheets.get(index).StandardDeviation(row, col);
    }

    /**
     * Calcula el coeficiente de correlación de Pearson del bloque seleccionado
     * @param index posición de la hoja
     * @param row fila del resultado
     * @param col columna del resultado
     * @return true si se ha calculado el coeficiente de correlación de Pearson, false en caso contrario
     */
    public boolean Pearson (int index, int row, int col) {

        return documentSheets.get(index).Pearson(row, col);
    }

    /**
     * Trunca el valor de una celda o conjunto de celdas
     * @param index posición de la hoja
     * @param Row fila de la celda
     * @param Col columna de la celda
     * @param boolRow true si se quiere extender la operación en su fila
     * @param boolColumn true si se quiere extender la operación en su columna
     * @return true si se realiza la operación correctamente, false en caso contrario
     */
    public boolean Truncate(int index, int Row, int Col, boolean boolRow, boolean boolColumn) {

        return documentSheets.get(index).Truncate(Row, Col, boolRow, boolColumn);
    }

    /**
     * Incrementa el valor de una celda o conjunto de celdas
     * @param index posición de la hoja
     * @param Row fila de la celda
     * @param Col columna de la celda
     * @param boolRow true si se quiere extender la operación en su fila
     * @param boolColumn true si se quiere extender la operación en su columna
     * @return true si se realiza la operación correctamente, false en caso contrario
     */
    public boolean Increase(int index, int Row, int Col, boolean boolRow, boolean boolColumn) {

        return documentSheets.get(index).Increase(Row, Col, boolRow, boolColumn);
    }

    /**
     * Calcula el valor absoluto de una celda o conjunto de celdas
     * @param index posición de la hoja
     * @param Row fila de la celda
     * @param Col columna de la celda
     * @param boolRow true si se quiere extender la operación en su fila
     * @param boolColumn true si se quiere extender la operación en su columna
     * @return true si se realiza la operación correctamente, false en caso contrario
     */
    public boolean Absolut(int index, int Row, int Col, boolean boolRow, boolean boolColumn) {

        return documentSheets.get(index).Absolut(Row, Col, boolRow, boolColumn);
    }

    /**
     * Conversión a binario o hexadecimal de una celda o conjunto de celdas
     * @param index posición de la hoja
     * @param Row fila de la celda
     * @param Col columna de la celda
     * @param boolRow true si se quiere extender la operación en su fila
     * @param boolColumn true si se quiere extender la operación en su columna
     * @param Criterium Binary[B] o Hexadecimal[H]
     * @return true si se realiza la operación correctamente, false en caso contrario
     */
    public boolean Conversion(int index, int Row, int Col, boolean boolRow, boolean boolColumn, String Criterium) {

        return documentSheets.get(index).Conversion(Row, Col, boolRow, boolColumn, Criterium);
    }

    /**
     * Extrae elemento de una fecha de una celda o conjunto de celdas
     * @param index posición de la hoja
     * @param Row fila de la celda
     * @param Col columna de la celda
     * @param boolRow true si se quiere extender la operación en su fila
     * @param boolColumn true si se quiere extender la operación en su columna
     * @param Criterium Day[D], Month[M] o Year[Y]
     * @return true si se realiza la operación correctamente, false en caso contrario
     */
    public boolean ExtractElement(int index, int Row, int Col, boolean boolRow, boolean boolColumn, String Criterium) {

        return documentSheets.get(index).ExtractElement(Row, Col, boolRow, boolColumn, Criterium);
    }

    /**
     * Consulta día de la semana de una celda o conjunto de celdas
     * @param index posición de la hoja
     * @param Row fila de la celda
     * @param Col columna de la celda
     * @param boolRow true si se quiere extender la operación en su fila
     * @param boolColumn true si se quiere extender la operación en su columna
     * @return true si se realiza la operación correctamente, false en caso contrario
     */
    public boolean DayOfTheWeek(int index, int Row, int Col, boolean boolRow, boolean boolColumn) {

        return documentSheets.get(index).DayOfTheWeek(Row, Col, boolRow, boolColumn);
    }

    /**
     * Llama a una operación de strings para que reemplace un elemento de las celdas con otro
     * @param index posición de la hoja
     * @param ElementToReplace elemento a buscar
     * @param ElementReplaced elemento que remplaza el elemento buscado
     * @return true si se remplaza, false en caso contrario
     */
    public boolean Replace(int index, String ElementToReplace, String ElementReplaced) {

        return documentSheets.get(index).Replace(ElementToReplace, ElementReplaced);
    }

    /**
     * Conuslta longitud de caracteres en un bloque
     * @param index posición de la hoja
     * @param row fila del resultado
     * @param col columna del resultado
     * @return true si se ha modificado la celda con el resultado, false en caso contrario
     */
    public boolean Size(int index, int row, int col) {

        return documentSheets.get(index).Size(row, col);
    }

    /**
     * Búsqueda de un elemento en un rango
     * @param index posición de la hoja
     * @param SearchS elemento a buscar
     * @return true si se encuentra el elemento, false en caso contrario
     */
    public boolean Search(int index, String SearchS) {

        return documentSheets.get(index).Search(SearchS);
    }

    /**
     * Consulta de los elementos de toda una hoja para imprimir por pantalla
     * @param index posición de la hoja
     * @return vector con los elementos en formato String
     */
    public Vector<String> Print (int index) {

        Vector<String> sheetToPrint = documentSheets.get(index).Print();
        return sheetToPrint;
    }

    /**
     * Suma de dos celdas referenciadas y su derivación en fila o columna
     * @param index posición de la hoja
     * @param row1 fila de la primera celda referenciada
     * @param col1 columna de la primera celda referenciada
     * @param row2 fila de la segunda celda referenciada
     * @param col2 columna de la segunda celda referenciada
     * @param rowRes fila de la celda resultado
     * @param colRes columna de la celda resultado
     * @param boolRow true si se quiere extender la operación en su fila
     * @param boolColumn true si se quiere extender la operación en su columna
     * @return true si se realiza la operación correctamente, false en caso contrario
     */
    public boolean Sum (int index, int row1, int col1, int row2, int col2, int rowRes, int colRes, boolean boolRow, boolean boolColumn) {

        return documentSheets.get(index).Sum(row1,col1,row2,col2,rowRes,colRes,boolRow,boolColumn);
    }

    /**
     * Resta de dos celdas referenciadas y su derivación en fila o columna
     * @param index posición de la hoja
     * @param row1 fila de la primera celda referenciada
     * @param col1 columna de la primera celda referenciada
     * @param row2 fila de la segunda celda referenciada
     * @param col2 columna de la segunda celda referenciada
     * @param rowRes fila de la celda resultado
     * @param colRes columna de la celda resultado
     * @param boolRow true si se quiere extender la operación en su fila
     * @param boolColumn true si se quiere extender la operación en su columna
     * @return true si se realiza la operación correctamente, false en caso contrario
     */
    public boolean Rest (int index, int row1, int col1, int row2, int col2, int rowRes, int colRes, boolean boolRow, boolean boolColumn) {

        return documentSheets.get(index).Rest(row1,col1,row2,col2,rowRes,colRes,boolRow,boolColumn);
    }

    /**
     * Multiplicación de dos celdas referenciadas y su derivación en fila o columna
     * @param index posición de la hoja
     * @param row1 fila de la primera celda referenciada
     * @param col1 columna de la primera celda referenciada
     * @param row2 fila de la segunda celda referenciada
     * @param col2 columna de la segunda celda referenciada
     * @param rowRes fila de la celda resultado
     * @param colRes columna de la celda resultado
     * @param boolRow true si se quiere extender la operación en su fila
     * @param boolColumn true si se quiere extender la operación en su columna
     * @return true si se realiza la operación correctamente, false en caso contrario
     */
    public boolean Multiplication (int index, int row1, int col1, int row2, int col2, int rowRes, int colRes, boolean boolRow, boolean boolColumn) {

        return documentSheets.get(index).Mult(row1,col1,row2,col2,rowRes,colRes,boolRow,boolColumn);
    }

    /**
     * División de dos celdas referenciadas y su derivación en fila o columna
     * @param index posición de la hoja
     * @param row1 fila de la primera celda referenciada
     * @param col1 columna de la primera celda referenciada
     * @param row2 fila de la segunda celda referenciada
     * @param col2 columna de la segunda celda referenciada
     * @param rowRes fila de la celda resultado
     * @param colRes columna de la celda resultado
     * @param boolRow true si se quiere extender la operación en su fila
     * @param boolColumn true si se quiere extender la operación en su columna
     * @return true si se realiza la operación correctamente, false en caso contrario
     */
    public boolean Division (int index, int row1, int col1, int row2, int col2, int rowRes, int colRes, boolean boolRow, boolean boolColumn) {

        return documentSheets.get(index).Div(row1,col1,row2,col2,rowRes,colRes,boolRow,boolColumn);
    }
}
