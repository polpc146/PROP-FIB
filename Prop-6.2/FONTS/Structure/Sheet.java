package Structure;

import Operations.NumberOperation;
import Operations.RankOperation;
import Structure.Cells.*;
import java.util.*;

/**
 * Una hoja tiene un nombre, la lista de filas que pertenecen a la hoja y el último rango de celdas seleccionado
 * @author Pol Pérez Castillo
 */
public class Sheet {

    private String name;
    private LinkedList<Row> sheetRows;
    private Rank provisionalRank, provisionalRank2;

    /**
     * Crea una hoja con nombre y se inicializa una lista de 20 filas de 20 columnas (valores por defecto)
     * @param sheetName nombre de la hoja
     */
    public Sheet (String sheetName) {

        name = sheetName;
        sheetRows = new LinkedList<>();
        for (int i = 0; i < 20; i++) sheetRows.add(new Row(20));
    }

    /**
     * Consulta nombre de la hoja
     * @return nombre de la hoja
     */
    public String getName() {

        return name;
    }

    /**
     * Consulta filas de la hoja
     * @return filas de la hoja
     */
    public LinkedList<Row> getSheetRows() {

        return sheetRows;
    }

    /**
     * Consulta rango de celdas actual
     * @return rango de celdas
     */
    public Rank getProvisionalRank() {

        return provisionalRank;
    }

    /**
     * Modifica nombre de la hoja
     * @param name nuevo nombre de la hoja
     * @return true
     */
    public boolean setName(String name) {

        this.name = name;
        return true;
    }

    /**
     * Modifica filas del documento
     * @param sheetRows nuevas filas del documento
     * @return true
     */
    public boolean setSheetRows(LinkedList<Row> sheetRows) {

        this.sheetRows = sheetRows;
        return true;
    }

    /**
     * Modifica rango de celdas actual
     * @param provisionalRank nuevo rango de celdas
     * @return true
     */
    public boolean setProvisionalRank(Rank provisionalRank) {

        this.provisionalRank = provisionalRank;
        return true;
    }

    /**
     * Añade una fila
     * @param pos posición donde añadir la fila
     * @return true
     */
    public boolean addRow (int pos) {

        int size = sheetRows.getFirst().rowSize();
        sheetRows.add(pos, new Row(size));
        return true;
    }

    /**
     * Elimina una fila
     * @param pos posición de la fila a eliminar
     * @return true
     */
    public boolean removeRow (int pos) {

        sheetRows.remove(pos);
        return true;
    }

    /**
     * Añade una columna
     * @param pos posición donde añadir la columna
     * @return true si se ha añadido la columna, false en caso contrario
     */
    public boolean addColumn (int pos) {

        for (Row sheetRow : sheetRows) if(!sheetRow.addCell(pos)) return false;
        return true;
    }

    /**
     * Elimina una columna
     * @param pos posición de la columna a eliminar
     * @return true si se ha eliminado la columna, false en caso contrario
     */
    public boolean removeColumn (int pos) {

        for (Row sheetRow : sheetRows) if(!sheetRow.removeCell(pos)) return false;
        return true;
    }

    /**
     * Consulta tamaño de la hoja en filas
     * @return número de filas de la hoja
     */
    public int getSize() {

        return sheetRows.size();
    }

    /**
     * Consulta tamaño de la hoja en columnas
     * @return número de columnas de la hoja
     */
    public int getRowSize() {

        return sheetRows.getFirst().rowSize();
    }

    /**
     * Consulta tamaño del bloque seleccionado actual en filas
     * @return número de filas del bloque seleccionado
     */
    public int getRankSize() {

        return provisionalRank.getNumRows();
    }

    /**
     * Consulta tamaño del bloque seleccionado actual en columnas
     * @return número de columnas del bloque seleccionado
     */
    public int getRankColSize() {

        return provisionalRank.getNumColumns();
    }

    /**
     * Creación del bloque de celdas seleccionado por el usuario
     * @param RowCellIni fila de la celda inicial
     * @param ColumnCellIni columna de la celda inicial
     * @param RowCellFin fila de la celda final
     * @param ColumnCellFin columna de la celda final
     * @return true
     */
    public boolean newRank (int RowCellIni, int ColumnCellIni, int RowCellFin, int ColumnCellFin, int rank) {

        LinkedList<Cell> rankCell = new LinkedList<>();
        for (int i = RowCellIni; i <= RowCellFin; i++) {

            for (int j = ColumnCellIni; j <= ColumnCellFin; j++) {

                rankCell.add(sheetRows.get(i).getCell(j));
            }
        }
        if (rank == 1) {

            provisionalRank = new Rank(rankCell, RowCellFin-RowCellIni+1, ColumnCellFin-ColumnCellIni+1);
            return true;
        }
        else if (rank == 2) {

            provisionalRank2 = new Rank(rankCell, RowCellFin-RowCellIni+1, ColumnCellFin-ColumnCellIni+1);
            return true;
        }
        return false;
    }

    /**
     * Ordena el bloque de celdas seleccionado por el usuario
     * @param Criterium criterio de ordenación
     * @return true
     */
    public boolean SortRank (String Criterium) {

        char checkRank = provisionalRank.CheckRank();
        if (checkRank == 'e') return false;
        int numRows = provisionalRank.getNumRows();
        int numColumns = provisionalRank.getNumColumns();
        LinkedList<Cell> aux = provisionalRank.GetAuxCells();
        for (int j = 0; j < numColumns; j++) {

            Vector<Double> sortVectorDouble = new Vector<>();
            Vector<String> sortVectorString = new Vector<>();
            Vector<Date> sortVectorDate = new Vector<>();
            for (int i = 0; i < numRows; i+= numColumns) {

                if (checkRank == 'N') sortVectorDouble.add(aux.get(j+i).getNum());
                else if (checkRank == 'S') sortVectorString.add(aux.get(j+i).getString());
                else if (checkRank == 'D') sortVectorDate.add(aux.get(j+i).getDate());
            }
            if (checkRank == 'N') mergeSortNum(sortVectorDouble,0, sortVectorDouble.size()-1, Criterium, checkRank);
            else if (checkRank == 'S') mergeSortString(sortVectorString,0, sortVectorString.size()-1, Criterium, checkRank);
            else if (checkRank == 'D') mergeSortDate(sortVectorDate,0, sortVectorDate.size()-1, Criterium, checkRank);
            int k = 0;
            for (int i = 0; i < numRows; i+= numColumns) {

                if (checkRank == 'N') provisionalRank.getCell(j+i).setContent(sortVectorDouble.get(k));
                else if (checkRank == 'S') provisionalRank.getCell(j+i).setContent(sortVectorString.get(k));
                else if (checkRank == 'D') provisionalRank.getCell(j+i).setContent(sortVectorDate.get(k));
                k++;
            }
        }
        return true;
    }

    /**
     * Método merge de ordenación recursivo
     * @param sortVector columna a ordenar
     * @param l left
     * @param r right
     * @param Criterium criterio de ordenación
     */
    private void mergeSortNum (Vector<Double> sortVector, int l, int r, String Criterium, char checkRank) {

        if (l < r) {

            int m = (l+r)/2;
            mergeSortNum(sortVector, l, m, Criterium, checkRank);
            mergeSortNum(sortVector, m+1, r, Criterium, checkRank);
            if (Criterium.equalsIgnoreCase("Ascendant") || Criterium.equalsIgnoreCase("A")) {

                mergeAscNum(sortVector, l, m, r);
            }
            else mergeDescNum(sortVector, l, m, r);
        }
    }

    /**
     * Método merge de ordenación recursivo
     * @param sortVector columna a ordenar
     * @param l left
     * @param r right
     * @param Criterium criterio de ordenación
     */
    private void mergeSortString (Vector<String> sortVector, int l, int r, String Criterium, char checkRank) {

        if (l < r) {

            int m = (l+r)/2;
            mergeSortString(sortVector, l, m, Criterium, checkRank);
            mergeSortString(sortVector, m+1, r, Criterium, checkRank);
            if (Criterium.equalsIgnoreCase("Ascendant") || Criterium.equalsIgnoreCase("A")) {

                mergeAscString(sortVector, l, m, r);
            }
            else mergeDescString(sortVector, l, m, r);
        }
    }

    /**
     * Método merge de ordenación recursivo
     * @param sortVector columna a ordenar
     * @param l left
     * @param r right
     * @param Criterium criterio de ordenación
     */
    private void mergeSortDate (Vector<Date> sortVector, int l, int r, String Criterium, char checkRank) {

        if (l < r) {

            int m = (l+r)/2;
            mergeSortDate(sortVector, l, m, Criterium, checkRank);
            mergeSortDate(sortVector, m+1, r, Criterium, checkRank);
            if (Criterium.equalsIgnoreCase("Ascendant") || Criterium.equalsIgnoreCase("A")) {

                mergeAscDate(sortVector, l, m, r);
            }
            else mergeDescDate(sortVector,l, m, r);
        }
    }

    /**
     * Método merge de ordenación con criterio ascendente
     * @param sortVector columna a ordenar
     * @param l left
     * @param m posición intermedia del vector
     * @param r right
     */
    private void mergeAscNum (Vector<Double> sortVector, int l, int m, int r) {

        int n = r - l + 1;
        Vector<Double> aux = new Vector<>(n);
        int i = l;
        int j = m+1;
        int k = 0;
        while (i <= m && j <= r) {

            if (sortVector.get(i) <= sortVector.get(j)) {

                aux.add(k, sortVector.get(i));
                i++;
            }
            else {

                aux.add(k, sortVector.get(j));
                j++;
            }
            k++;
        }
        while (i <= m) {

            aux.add(k, sortVector.get(i));
            k++;
            i++;
        }
        while (j <= r) {

            aux.add(k, sortVector.get(j));
            k++;
            j++;
        }
        for (k = 0; k < n; ++k) sortVector.set(k+l, aux.get(k));
    }

    /**
     * Método merge de ordenación con criterio descendente
     * @param sortVector columna a ordenar
     * @param l left
     * @param m posición intermedia del vector
     * @param r right
     */
    private void mergeDescNum (Vector<Double> sortVector, int l, int m, int r) {

        int n = r - l + 1;
        Vector<Double> aux = new Vector<>(n);
        int i = l;
        int j = m+1;
        int k = 0;
        while (i <= m && j <= r) {

            if (sortVector.get(i) >= sortVector.get(j)) {

                aux.add(k, sortVector.get(i));
                i++;
            }
            else {

                aux.add(k, sortVector.get(j));
                j++;
            }
            k++;
        }
        while (i <= m) {

            aux.add(k, sortVector.get(i));
            k++;
            i++;
        }
        while (j <= r) {

            aux.add(k, sortVector.get(j));
            k++;
            j++;
        }
        for (k = 0; k < n; ++k) sortVector.set(k+l, aux.get(k));
    }

    /**
     * Método merge de ordenación con criterio ascendente
     * @param sortVector columna a ordenar
     * @param l left
     * @param m posición intermedia del vector
     * @param r right
     */
    private void mergeAscString (Vector<String> sortVector, int l, int m, int r) {

        int n = r - l + 1;
        Vector<String> aux = new Vector<>(n);
        int i = l;
        int j = m+1;
        int k = 0;
        while (i <= m && j <= r) {

            if (sortVector.get(i).compareTo(sortVector.get(j)) <= 0) {

                aux.add(k, sortVector.get(i));
                i++;
            }
            else {

                aux.add(k, sortVector.get(j));
                j++;
            }
            k++;
        }
        while (i <= m) {

            aux.add(k, sortVector.get(i));
            k++;
            i++;
        }
        while (j <= r) {

            aux.add(k, sortVector.get(j));
            k++;
            j++;
        }
        for (k = 0; k < n; ++k) sortVector.set(k+l, aux.get(k));
    }

    /**
     * Método merge de ordenación con criterio descendente
     * @param sortVector columna a ordenar
     * @param l left
     * @param m posición intermedia del vector
     * @param r right
     */
    private void mergeDescString (Vector<String> sortVector, int l, int m, int r) {

        int n = r - l + 1;
        Vector<String> aux = new Vector<>(n);
        int i = l;
        int j = m+1;
        int k = 0;
        while (i <= m && j <= r) {

            if (sortVector.get(i).compareTo(sortVector.get(j)) >= 0) {

                aux.add(k, sortVector.get(i));
                i++;
            }
            else {

                aux.add(k, sortVector.get(j));
                j++;
            }
            k++;
        }
        while (i <= m) {

            aux.add(k, sortVector.get(i));
            k++;
            i++;
        }
        while (j <= r) {

            aux.add(k, sortVector.get(j));
            k++;
            j++;
        }
        for (k = 0; k < n; ++k) sortVector.set(k+l, aux.get(k));
    }

    /**
     * Método merge de ordenación con criterio ascendente
     * @param sortVector columna a ordenar
     * @param l left
     * @param m posición intermedia del vector
     * @param r right
     */
    private void mergeAscDate (Vector<Date> sortVector, int l, int m, int r) {

        int n = r - l + 1;
        Vector<Date> aux = new Vector<>(n);
        int i = l;
        int j = m+1;
        int k = 0;
        while (i <= m && j <= r) {

            if (sortVector.get(i).compareTo(sortVector.get(j)) <= 0) {

                aux.add(k, sortVector.get(i));
                i++;
            }
            else {

                aux.add(k, sortVector.get(j));
                j++;
            }
            k++;
        }
        while (i <= m) {

            aux.add(k, sortVector.get(i));
            k++;
            i++;
        }
        while (j <= r) {

            aux.add(k, sortVector.get(j));
            k++;
            j++;
        }
        for (k = 0; k < n; ++k) sortVector.set(k+l, aux.get(k));
    }

    /**
     * Método merge de ordenación con criterio descendente
     * @param sortVector columna a ordenar
     * @param l left
     * @param m posición intermedia del vector
     * @param r right
     */
    private void mergeDescDate (Vector<Date> sortVector, int l, int m, int r) {

        int n = r - l + 1;
        Vector<Date> aux = new Vector<>(n);
        int i = l;
        int j = m+1;
        int k = 0;
        while (i <= m && j <= r) {

            if (sortVector.get(i).compareTo(sortVector.get(j)) >= 0) {

                aux.add(k, sortVector.get(i));
                i++;
            }
            else {

                aux.add(k, sortVector.get(j));
                j++;
            }
            k++;
        }
        while (i <= m) {

            aux.add(k, sortVector.get(i));
            k++;
            i++;
        }
        while (j <= r) {

            aux.add(k, sortVector.get(j));
            k++;
            j++;
        }
        for (k = 0; k < n; ++k) sortVector.set(k+l, aux.get(k));
    }

    /**
     * Elimina el contenido de las celdas del bloque referenciado y lo engancha a partir de la celda seleccionada
     * por el usuario
     * @param rowIni fila de la celda donde enganchar
     * @param colIni columna de la celda donde enganchar
     * @param numRows tamaño del bloque en filas
     * @param numColumns tamaño del bloque en columnas
     * @return
     */
    public boolean PasteRank (int rowIni, int colIni, int numRows, int numColumns) {

        LinkedList<Cell> aux = provisionalRank.GetAuxCells();
        String type = "";
        int k = 0;
        for (int i = rowIni; i < rowIni+numRows; ++i) {

            for (int j = colIni; j < colIni+numColumns; ++j) {

                type = aux.get(k).getCellType();
                switch (type) {
                    case "Cell":

                        if (!sheetRows.get(i).setCell(j)) return false;
                        break;
                    case "NumCell":

                        if (!sheetRows.get(i).ModifyDouble(j, aux.get(k).getNum())) return false;
                        break;
                    case "StringCell":

                        if (!sheetRows.get(i).ModifyString(j, aux.get(k).getString())) return false;
                        break;
                    case "DateCell":

                        if (!sheetRows.get(i).ModifyDate(j, aux.get(k).getDate())) return false;
                        break;
                }
                k++;
            }
        }
        provisionalRank.RemoveRank();
        return true;
    }

    /**
     * Copia el contenido de las celdas del bloque referenciado y lo engancha a partir de la celda seleccionada
     * por el usuario
     * @param rowIni fila de la celda donde enganchar
     * @param colIni columna de la celda donde enganchar
     * @param numRows tamaño del bloque en filas
     * @param numColumns tamaño del bloque en columnas
     * @return
     */
    public boolean CopyRank (int rowIni, int colIni, int numRows, int numColumns) {

        LinkedList<Cell> aux = provisionalRank.GetAuxCells();
        String type = "";
        int k = 0;
        for (int i = rowIni; i < rowIni+numRows; ++i) {

            for (int j = colIni; j < colIni+numColumns; ++j) {

                type = aux.get(k).getCellType();
                switch (type) {
                    case "Cell":

                        if (!sheetRows.get(i).setCell(j)) return false;
                        break;
                    case "NumCell":

                        if (!sheetRows.get(i).ModifyDouble(j, aux.get(k).getNum())) return false;
                        break;
                    case "StringCell":

                        if (!sheetRows.get(i).ModifyString(j, aux.get(k).getString())) return false;
                        break;
                    case "DateCell":

                        if (!sheetRows.get(i).ModifyDate(j, aux.get(k).getDate())) return false;
                        break;
                }
                k++;
            }
        }
        return true;
    }

    /**
     * Modifica el contenido de una celda a un String
     * @param row fila de la celda
     * @param column columna de la celda
     * @param content nuevo contenido de la celda
     * @return true si se ha modificado el contenido, false en caso contrario
     */
    public boolean ModifyString (int row, int column, String content) {

        return sheetRows.get(row).ModifyString(column, content);
    }

    /**
     * Modifica el contenido de una celda a un double
     * @param row fila de la celda
     * @param column columna de la celda
     * @param content nuevo contenido de la celda
     * @return true si se ha modificado el contenido, false en caso contrario
     */
    public boolean ModifyDouble (int row, int column, double content) {

        return sheetRows.get(row).ModifyDouble(column, content);
    }

    /**
     * Modifica el contenido de una celda a un Date
     * @param row fila de la celda
     * @param column columna de la celda
     * @param date nuevo contenido de la celda
     * @return true si se ha modificado el contenido, false en caso contrario
     */
    public boolean ModifyDate (int row, int column, Date date) {

        return sheetRows.get(row).ModifyDate(column, date);
    }

    /**
     * Calcula la media del bloque seleccionado
     * @param row fila del resultado
     * @param col columna del resultado
     * @return true si se ha calculado la media, false en caso contrario
     */
    public boolean Average (int row, int col) {

        char checkRank = provisionalRank.CheckRank();
        if (checkRank != 'N') return false;
        double average = provisionalRank.Average();
        return sheetRows.get(row).ModifyDouble(col, average);
    }

    /**
     * Calcula la mediana del bloque seleccionado
     * @param row fila del resultado
     * @param col columna del resultado
     * @return true si se ha calculado la mediana, false en caso contrario
     */
    public boolean Median (int row, int col) {

        char checkRank = provisionalRank.CheckRank();
        if (checkRank != 'N') return false;
        double median = provisionalRank.Median();
        return sheetRows.get(row).ModifyDouble(col, median);
    }

    /**
     * Calcula la varianza del bloque seleccionado
     * @param row fila del resultado
     * @param col columna del resultado
     * @return true si se ha calculado la varianza, false en caso contrario
     */
    public boolean Variance (int row, int col) {

        char checkRank = provisionalRank.CheckRank();
        if (checkRank != 'N') return false;
        double variance = provisionalRank.Variance();
        return sheetRows.get(row).ModifyDouble(col, variance);
    }

    /**
     * Calcula la covarianza del bloque seleccionado
     * @param row fila del resultado
     * @param col columna del resultado
     * @return true si se ha calculado la covarianza, false en caso contrario
     */
    public boolean Covariance (int row, int col) {

        if ((provisionalRank.getNumRows() == provisionalRank2.getNumRows()) && (provisionalRank.getNumColumns() == provisionalRank2.getNumColumns())) {

            char checkRank = provisionalRank.CheckRank();
            if (checkRank != 'N') return false;
            checkRank = provisionalRank2.CheckRank();
            if (checkRank != 'N') return false;
            RankOperation myOperation = new RankOperation();
            double covariance = myOperation.Covariance(provisionalRank.getRankCell(), provisionalRank2.getRankCell());
            return sheetRows.get(row).ModifyDouble(col, covariance);
        }
        else return false;
    }

    /**
     * Calcula la desviación estándar del bloque seleccionado
     * @param row fila del resultado
     * @param col columna del resultado
     * @return true si se ha calculado la desviación estándar, false en caso contrario
     */
    public boolean StandardDeviation (int row, int col) {

        char checkRank = provisionalRank.CheckRank();
        if (checkRank != 'N') return false;
        double standardDeviation = provisionalRank.StandardDeviation();
        return sheetRows.get(row).ModifyDouble(col, standardDeviation);
    }

    /**
     * Calcula el coeficiente de correlación de Pearson del bloque seleccionado
     * @param row fila del resultado
     * @param col columna del resultado
     * @return true si se ha calculado el coeficiente de correlación de Pearson, false en caso contrario
     */
    public boolean Pearson (int row, int col) {

        if ((provisionalRank.getNumRows() == provisionalRank2.getNumRows()) && (provisionalRank.getNumColumns() == provisionalRank2.getNumColumns())) {

            char checkRank = provisionalRank.CheckRank();
            if (checkRank != 'N') return false;
            checkRank = provisionalRank2.CheckRank();
            if (checkRank != 'N') return false;
            RankOperation myOperation = new RankOperation();
            double pearson = myOperation.Pearson(provisionalRank.getRankCell(), provisionalRank2.getRankCell());
            return sheetRows.get(row).ModifyDouble(col, pearson);
        }
        else return false;
    }

    /**
     * Trunca el valor de una celda o conjunto de celdas
     * @param row fila de la celda
     * @param col columna de la celda
     * @param boolRow true si se quiere extender la operación en su fila
     * @param boolColumn true si se quiere extender la operación en su columna
     * @return true si se realiza la operación correctamente, false en caso contrario
     */
    public boolean Truncate (int row, int col, boolean boolRow, boolean boolColumn) {

        if (!boolRow && !boolColumn) {

            return sheetRows.get(row).Truncate(col);
        }
        else if (boolColumn) {

            boolean errorChecker = false;
            for (int i = row; i < sheetRows.size() && !errorChecker; i++) {

                if (!sheetRows.get(i).Truncate(col)) errorChecker = true;
            }
        }
        else {

            boolean errorChecker = false;
            for (int i =col; i < sheetRows.get(row).rowSize() && !errorChecker; i++) {

                if(!sheetRows.get(row).Truncate(i)) errorChecker = true;
            }
        }
        return true;
    }

    /**
     * Incrementa el valor de una celda o conjunto de celdas
     * @param row fila de la celda
     * @param col columna de la celda
     * @param boolRow true si se quiere extender la operación en su fila
     * @param boolColumn true si se quiere extender la operación en su columna
     * @return true si se realiza la operación correctamente, false en caso contrario
     */
    public boolean Increase (int row, int col, boolean boolRow, boolean boolColumn) {

        if (!boolRow && !boolColumn) {

            return sheetRows.get(row).Increase(col);
        }
        else if (boolColumn) {

            boolean errorChecker = false;
            for (int i = row; i < sheetRows.size() && !errorChecker; i++) {

                if (!sheetRows.get(i).Increase(col)) errorChecker = true;
            }
        }
        else {

            boolean errorChecker = false;
            for (int i = col; i < sheetRows.get(row).rowSize() && !errorChecker; i++) {

                if(!sheetRows.get(row).Increase(i)) errorChecker = true;
            }
        }
        return true;
    }

    /**
     * Calcula el valor absoluto de una celda o conjunto de celdas
     * @param row fila de la celda
     * @param col columna de la celda
     * @param boolRow true si se quiere extender la operación en su fila
     * @param boolColumn true si se quiere extender la operación en su columna
     * @return true si se realiza la operación correctamente, false en caso contrario
     */
    public boolean Absolut (int row, int col, boolean boolRow, boolean boolColumn) {

        if (!boolRow && !boolColumn) {

            return sheetRows.get(row).Absolut(col);
        }
        else if (boolColumn) {

            boolean errorChecker = false;
            for (int i = row; i < sheetRows.size() && !errorChecker; i++) {

                if (!sheetRows.get(i).Absolut(col)) errorChecker = true;
            }
        }
        else {

            boolean errorChecker = false;
            for (int i = col; i < sheetRows.get(row).rowSize() && !errorChecker; i++) {

                if(!sheetRows.get(row).Absolut(i)) errorChecker = true;
            }
        }
        return true;
    }

    /**
     * Conversión a binario o hexadecimal de una celda o conjunto de celdas
     * @param row fila de la celda
     * @param col columna de la celda
     * @param boolRow true si se quiere extender la operación en su fila
     * @param boolColumn true si se quiere extender la operación en su columna
     * @param Criterium Binary[B] o Hexadecimal[H]
     * @return true si se realiza la operación correctamente, false en caso contrario
     */
    public boolean Conversion (int row, int col, boolean boolRow, boolean boolColumn, String Criterium) {

        if (!boolRow && !boolColumn) {

            return sheetRows.get(row).Conversion(col, Criterium);
        }
        else if (boolColumn) {

            boolean errorChecker = false;
            for (int i = row; i < sheetRows.size() && !errorChecker; i++) {

                if (!sheetRows.get(i).Conversion(col, Criterium)) errorChecker = true;
            }
        }
        else {

            boolean errorChecker = false;
            for (int i = col; i < sheetRows.get(row).rowSize() && !errorChecker; i++) {

                if(!sheetRows.get(row).Conversion(i, Criterium)) errorChecker = true;
            }
        }
        return true;
    }

    /**
     * Extrae elemento de una fecha de una celda o conjunto de celdas
     * @param row fila de la celda
     * @param col columna de la celda
     * @param boolRow true si se quiere extender la operación en su fila
     * @param boolColumn true si se quiere extender la operación en su columna
     * @param Criterium Day[D], Month[M] o Year[Y]
     * @return true si se realiza la operación correctamente, false en caso contrario
     */
    public boolean ExtractElement (int row, int col, boolean boolRow, boolean boolColumn, String Criterium) {

        if (!boolRow && !boolColumn) {

            return sheetRows.get(row).ExtractElement(col, Criterium);
        }
        else if (boolColumn) {

            boolean errorChecker = false;
            for (int i = row; i < sheetRows.size() && !errorChecker; i++) {

                if (!sheetRows.get(i).ExtractElement(col, Criterium)) errorChecker = true;
            }
        }
        else {

            boolean errorChecker = false;
            for (int i = col; i < sheetRows.get(row).rowSize() && !errorChecker; i++) {

                if(!sheetRows.get(row).ExtractElement(i, Criterium)) errorChecker = true;
            }
        }
        return true;
    }

    /**
     * Consulta día de la semana de una celda o conjunto de celdas
     * @param row fila de la celda
     * @param col columna de la celda
     * @param boolRow true si se quiere extender la operación en su fila
     * @param boolColumn true si se quiere extender la operación en su columna
     * @return true si se realiza la operación correctamente, false en caso contrario
     */
    public boolean DayOfTheWeek (int row, int col, boolean boolRow, boolean boolColumn) {

        if (!boolRow && !boolColumn) {

            return sheetRows.get(row).DayOfTheWeek(col);
        }
        else if (boolColumn) {

            boolean errorChecker = false;
            for (int i = row; i < sheetRows.size() && !errorChecker; i++) {

                if (!sheetRows.get(i).DayOfTheWeek(col)) errorChecker = true;
            }
        }
        else {

            boolean errorChecker = false;
            for (int i = col; i < sheetRows.get(row).rowSize() && !errorChecker; i++) {

                if(!sheetRows.get(row).DayOfTheWeek(i)) errorChecker = true;
            }
        }
        return true;
    }

    /**
     * Llama a una operación de strings para que reemplace un elemento de las celdas con otro
     * @param ElementToReplace elemento a buscar
     * @param ElementReplaced elemento que remplaza el elemento buscado
     * @return true si se remplaza, false en caso contrario
     */
    public boolean Replace (String ElementToReplace, String ElementReplaced) {

        return provisionalRank.Replace(ElementToReplace, ElementReplaced);
    }

    /**
     * Consulta longitud de caracteres en un bloque
     * @param row fila del resultado
     * @param col columna del resultado
     * @return true si se ha modificado la celda con el resultado, false en caso contrario
     */
    public boolean Size (int row, int col) {

        double size = provisionalRank.Size();
        return sheetRows.get(row).ModifyDouble(col, size);
    }

    /**
     * Búsqueda de un elemento en un rango
     * @param SearchS elemento a buscar
     * @return true si se encuentra el elemento, false en caso contrario
     */
    public boolean Search (String SearchS) {

        return provisionalRank.Search(SearchS);
    }

    /**
     * Consulta de los elementos de toda una hoja para imprimir por pantalla
     * @return vector con los elementos en formato String
     */
    public Vector<String> Print() {

        Vector<String> sheetToPrint = new Vector<>();
        for (int i = 0; i < sheetRows.size(); i++) {

            for (int j = 0; j < sheetRows.getFirst().rowSize(); ++j) {

                sheetToPrint.add(sheetRows.get(i).Print(j));
            }
        }
        return sheetToPrint;
    }

    /**
     * Suma de dos celdas referenciadas y su derivación en fila o columna
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
    public boolean Sum (int row1, int col1, int row2, int col2, int rowRes, int colRes, boolean boolRow, boolean boolColumn) {

        if (!boolRow && !boolColumn) {

            if (sheetRows.get(row1).isNumCell(col1) && sheetRows.get(row2).isNumCell(col2)) {

                NumberOperation myOperation = new NumberOperation();
                double content = myOperation.Sum(sheetRows.get(row1).getNum(col1), sheetRows.get(row2).getNum(col2));
                return sheetRows.get(rowRes).ModifyDouble(colRes, content);
            }
        }
        else if (boolColumn) {

            boolean errorChecker = false;
            for (int i = 0; i < sheetRows.size()-rowRes && !errorChecker; i++) {

                if (!sheetRows.get(row1+i).isNumCell(col1) || !sheetRows.get(row2+i).isNumCell(col2)) errorChecker = true;
                else {

                    NumberOperation myOperation = new NumberOperation();
                    double content = myOperation.Sum(sheetRows.get(row1+i).getNum(col1), sheetRows.get(row2+i).getNum(col2));
                    if(!sheetRows.get(rowRes+i).ModifyDouble(colRes, content)) return false;
                }
            }
        }
        else {

            boolean errorChecker = false;
            for (int i =0; i < sheetRows.get(rowRes).rowSize()-colRes && !errorChecker; i++) {

                if(!sheetRows.get(row1).isNumCell(col1+i) || !sheetRows.get(row2).isNumCell(col2+i)) errorChecker = true;
                else {

                    NumberOperation myOperation = new NumberOperation();
                    double content = myOperation.Sum(sheetRows.get(row1).getNum(col1+i), sheetRows.get(row2).getNum(col2+i));
                    if(!sheetRows.get(rowRes).ModifyDouble(colRes+i, content)) return false;
                }
            }
        }
        return true;
    }

    /**
     * Resta de dos celdas referenciadas y su derivación en fila o columna
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
    public boolean Rest (int row1, int col1, int row2, int col2, int rowRes, int colRes, boolean boolRow, boolean boolColumn) {

        if (!boolRow && !boolColumn) {

            if (sheetRows.get(row1).isNumCell(col1) && sheetRows.get(row2).isNumCell(col2)) {

                NumberOperation myOperation = new NumberOperation();
                double content = myOperation.Rest(sheetRows.get(row1).getNum(col1), sheetRows.get(row2).getNum(col2));
                return sheetRows.get(rowRes).ModifyDouble(colRes, content);
            }
        }
        else if (boolColumn) {

            boolean errorChecker = false;
            for (int i = 0; i < sheetRows.size()-rowRes && !errorChecker; i++) {

                if (!sheetRows.get(row1+i).isNumCell(col1) || !sheetRows.get(row2+i).isNumCell(col2)) errorChecker = true;
                else {

                    NumberOperation myOperation = new NumberOperation();
                    double content = myOperation.Rest(sheetRows.get(row1+i).getNum(col1), sheetRows.get(row2+i).getNum(col2));
                    if(!sheetRows.get(rowRes+i).ModifyDouble(colRes, content)) return false;
                }
            }
        }
        else {

            boolean errorChecker = false;
            for (int i =0; i < sheetRows.get(rowRes).rowSize()-colRes && !errorChecker; i++) {

                if(!sheetRows.get(row1).isNumCell(col1+i) || !sheetRows.get(row2).isNumCell(col2+i)) errorChecker = true;
                else {

                    NumberOperation myOperation = new NumberOperation();
                    double content = myOperation.Rest(sheetRows.get(row1).getNum(col1+i), sheetRows.get(row2).getNum(col2+i));
                    if(!sheetRows.get(rowRes).ModifyDouble(colRes+i, content)) return false;
                }
            }
        }
        return true;
    }

    /**
     * Multiplicación de dos celdas referenciadas y su derivación en fila o columna
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
    public boolean Mult (int row1, int col1, int row2, int col2, int rowRes, int colRes, boolean boolRow, boolean boolColumn) {

        if (!boolRow && !boolColumn) {

            if (sheetRows.get(row1).isNumCell(col1) && sheetRows.get(row2).isNumCell(col2)) {

                NumberOperation myOperation = new NumberOperation();
                double content = myOperation.Multiplication(sheetRows.get(row1).getNum(col1), sheetRows.get(row2).getNum(col2));
                return sheetRows.get(rowRes).ModifyDouble(colRes, content);
            }
        }
        else if (boolColumn) {

            boolean errorChecker = false;
            for (int i = 0; i < sheetRows.size()-rowRes && !errorChecker; i++) {

                if (!sheetRows.get(row1+i).isNumCell(col1) || !sheetRows.get(row2+i).isNumCell(col2)) errorChecker = true;
                else {

                    NumberOperation myOperation = new NumberOperation();
                    double content = myOperation.Multiplication(sheetRows.get(row1+i).getNum(col1), sheetRows.get(row2+i).getNum(col2));
                    if(!sheetRows.get(rowRes+i).ModifyDouble(colRes, content)) return false;
                }
            }
        }
        else {

            boolean errorChecker = false;
            for (int i =0; i < sheetRows.get(rowRes).rowSize()-colRes && !errorChecker; i++) {

                if(!sheetRows.get(row1).isNumCell(col1+i) || !sheetRows.get(row2).isNumCell(col2+i)) errorChecker = true;
                else {

                    NumberOperation myOperation = new NumberOperation();
                    double content = myOperation.Multiplication(sheetRows.get(row1).getNum(col1+i), sheetRows.get(row2).getNum(col2+i));
                    if(!sheetRows.get(rowRes).ModifyDouble(colRes+i, content)) return false;
                }
            }
        }
        return true;
    }

    /**
     * División de dos celdas referenciadas y su derivación en fila o columna
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
    public boolean Div (int row1, int col1, int row2, int col2, int rowRes, int colRes, boolean boolRow, boolean boolColumn) {

        if (!boolRow && !boolColumn) {

            if (sheetRows.get(row1).isNumCell(col1) && sheetRows.get(row2).isNumCell(col2)) {

                NumberOperation myOperation = new NumberOperation();
                double content = myOperation.Division(sheetRows.get(row1).getNum(col1), sheetRows.get(row2).getNum(col2));
                return sheetRows.get(rowRes).ModifyDouble(colRes, content);
            }
        }
        else if (boolColumn) {

            boolean errorChecker = false;
            for (int i = 0; i < sheetRows.size()-rowRes && !errorChecker; i++) {

                if (!sheetRows.get(row1+i).isNumCell(col1) || !sheetRows.get(row2+i).isNumCell(col2)) errorChecker = true;
                else {

                    NumberOperation myOperation = new NumberOperation();
                    double content = myOperation.Division(sheetRows.get(row1+i).getNum(col1), sheetRows.get(row2+i).getNum(col2));
                    if(!sheetRows.get(rowRes+i).ModifyDouble(colRes, content)) return false;
                }
            }
        }
        else {

            boolean errorChecker = false;
            for (int i =0; i < sheetRows.get(rowRes).rowSize()-colRes && !errorChecker; i++) {

                if(!sheetRows.get(row1).isNumCell(col1+i) || !sheetRows.get(row2).isNumCell(col2+i)) errorChecker = true;
                else {

                    NumberOperation myOperation = new NumberOperation();
                    double content = myOperation.Division(sheetRows.get(row1).getNum(col1+i), sheetRows.get(row2).getNum(col2+i));
                    if(!sheetRows.get(rowRes).ModifyDouble(colRes+i, content)) return false;
                }
            }
        }
        return true;
    }
}
