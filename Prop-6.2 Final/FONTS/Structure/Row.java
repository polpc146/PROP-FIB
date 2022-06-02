package Structure;

import Operations.DateOperation;
import Operations.NumberOperation;
import Operations.StringOperation;
import Structure.Cells.*;
import java.util.*;

/**
 * Una fila contiene una lista de las celdas que pertenecen a la fila
 * @author Pol Pérez Castillo
 */
public class Row {

    private LinkedList<Cell> rowCells;

    /**
     * Se inicializa la lista de celdas
     * @param numColumns número de celdas que tendrá la fila
     */
    public Row (int numColumns) {

        rowCells = new LinkedList<>();
        for (int i = 0; i < numColumns; i++) rowCells.add(new Cell());
    }

    /**
     * Consulta de todas las celdas de la fila
     * @return celdas de la fila
     */
    public LinkedList<Cell> getRowCells () {

        return rowCells;
    }

    /**
     * Modifica todas las celdas de la fila
     * @param rowCells nuevas celdas de la fila
     * @return true
     */
    public boolean setRowCells(LinkedList<Cell> rowCells) {

        this.rowCells = rowCells;
        return true;
    }

    /**
     * Añade una celda vacía
     * @param pos posición donde añadir la celda en la fila
     * @return true
     */
    public boolean addCell (int pos) {

        rowCells.add(pos, new Cell());
        return true;
    }

    /**
     * Añade una celda de tipo String
     * @param pos posición donde añadir la celda en la fila
     * @param content contenido de la celda
     * @return true
     */
    public boolean addCellString (int pos, String content) {

        rowCells.add(pos, new StringCell(content));
        return true;
    }

    /**
     * Añade una celda de tipo double
     * @param pos posición donde añadir la celda en la fila
     * @param content contenido de la celda
     * @return true
     */
    public boolean addCellDouble (int pos, double content) {

        rowCells.add(pos, new NumCell(content));
        return true;
    }

    /**
     * Añade una celda de tipo Date
     * @param pos posición donde añadir la celda en la fila
     * @param date contenido de la celda
     * @return true
     */
    public boolean addCellDate (int pos, Date date) {

        rowCells.add(pos, new DateCell(date));
        return true;
    }

    /**
     * Elimina una celda
     * @param pos posición de la celda a eliminar
     * @return true
     */
    public boolean removeCell (int pos) {

        rowCells.remove(pos);
        return true;
    }

    /**
     * Consulta tamaño de la fila (número de columnas)
     * @return número de columnas
     */
    public int rowSize () {

        return rowCells.size();
    }

    /**
     * Consulta celda
     * @param pos columna de la celda
     * @return celda
     */
    public Cell getCell (int pos) {

        return rowCells.get(pos);
    }

    /**
     * Elimina celda y crea otra de tipo String
     * @param column columna de la celda
     * @param content contenido de la nueva celda
     * @return true si se añade la nueva celda, false en caso contrario
     */
    public boolean ModifyString (int column, String content) {

        if(!removeCell(column)) return false;
        return addCellString(column, content);
    }

    /**
     * Elimina celda y crea otra de tipo double
     * @param column columna de la celda
     * @param content contenido de la celda
     * @return true si se añade la nueva celda, false en caso contrario
     */
    public boolean ModifyDouble (int column, double content) {

        if(!removeCell(column)) return false;
        return addCellDouble(column, content);
    }

    /**
     * Elimina celda y crea otra de tipo Date
     * @param column columna de la celda
     * @param date contenido de la celda
     * @return true si se añade la nueva celda, false en caso contrario
     */
    public boolean ModifyDate (int column, Date date) {

        if(!removeCell(column)) return false;
        return addCellDate(column, date);
    }

    /**
     * Elimina celda y crea una nueva vacía
     * @param pos columna de la celda
     * @return true
     */
    public boolean setCell (int pos) {

        rowCells.remove(pos);
        rowCells.add(pos, new Cell());
        return true;
    }

    /**
     * Trunca el valor de una celda
     * @param pos columna de la celda
     * @return true si se trunca la celda, false en caso contrario
     */
    public boolean Truncate (int pos) {

        if (!rowCells.get(pos).getCellType().equals("NumCell")) return false;
        NumberOperation myOperation = new NumberOperation();
        double trunc = myOperation.Truncate(rowCells.get(pos).getNum());
        return ModifyDouble(pos, trunc);
    }

    /**
     * Incrementa el valor de una celda
     * @param pos columna de la celda
     * @return true si se incrementa la celda, false en caso contrario
     */
    public boolean Increase (int pos) {

        if (!rowCells.get(pos).getCellType().equals("NumCell")) return false;
        NumberOperation myOperation = new NumberOperation();
        double inc = myOperation.Increase(rowCells.get(pos).getNum());
        return ModifyDouble(pos, inc);
    }

    /**
     * Calcula valor absoluto de una celda
     * @param pos columna de la celda
     * @return true si se incrementa la celda, false en caso contrario
     */
    public boolean Absolut (int pos) {

        if (!rowCells.get(pos).getCellType().equals("NumCell")) return false;
        NumberOperation myOperation = new NumberOperation();
        double abs = myOperation.Absolute(rowCells.get(pos).getNum());
        return ModifyDouble(pos, abs);
    }

    /**
     * Conversión a binario o hexadecimal de una celda
     * @param pos columna de la celda
     * @param Criterium Binary[B] o Hexadecimal[H]
     * @return true si se hace la conversion, false en caso contrario
     */
    public boolean Conversion (int pos, String Criterium) {

        if (!rowCells.get(pos).getCellType().equals("NumCell")) return false;
        String conv = "";
        NumberOperation myOperation = new NumberOperation();
        if (Criterium.equalsIgnoreCase("B") || Criterium.equalsIgnoreCase("Binary")) {

            conv = myOperation.Binary(rowCells.get(pos).getNum());
        }
        else conv = myOperation.Hexadecimal(rowCells.get(pos).getNum());
        return ModifyString(pos, conv);
    }

    /**
     * Extrae elemento de una fecha
     * @param pos columna de la celda
     * @param Criterium Day[D], Month[M] o Year[Y]
     * @return true si se extrae el elemento, false en caso contrario
     */
    public boolean ExtractElement (int pos, String Criterium) {

        if (!rowCells.get(pos).getCellType().equals("DateCell")) return false;
        DateOperation myOperation = new DateOperation();
        if (Criterium.equalsIgnoreCase("D") || Criterium.equalsIgnoreCase("day")) {

            int day = myOperation.Day(rowCells.get(pos).getDate());
            return ModifyDouble(pos, day);
        }
        else if (Criterium.equalsIgnoreCase("M") || Criterium.equalsIgnoreCase("month")) {

            String month = myOperation.Month(rowCells.get(pos).getDate());
            return ModifyString(pos, month);
        }
        else {

            int year = myOperation.Year(rowCells.get(pos).getDate());
            return ModifyDouble(pos, year);
        }
    }

    /**
     * Consulta día de la semana
     * @param pos columna de la celda
     * @return true si se modifica la celda, false en caso contrario
     */
    public boolean DayOfTheWeek (int pos) {

        if (!rowCells.get(pos).getCellType().equals("DateCell")) return false;
        DateOperation myOperation = new DateOperation();
        String day = myOperation.DayOfWeek(rowCells.get(pos).getDate());
        return ModifyString(pos, day);
    }

    /**
     * Consulta contenido de una celda en formato String
     * @param pos columna de la celda
     * @return contenido de la celda
     */
    public String Print (int pos) {

        String type = rowCells.get(pos).getCellType();
        if (type.equals("Cell")) return " ";
        else if (type.equals("NumCell")) {
            double content = rowCells.get(pos).getNum();
            if (content != -99999) return Double.toString(content);
            else return "";
        }
        else if (type.equals("StringCell")) {

            return rowCells.get(pos).getString();
        }
        else {

            Date content = rowCells.get(pos).getDate();

            if (content.getDay() == 1 && content.getMonth() == 0 && content.getYear() == 1) return "";
            else return content.toString();
        }
    }

    /**
     * Consulta si la celda es de tipo Num
     * @param pos columna de la celda
     * @return true si es de tipo Num, false en caso contrario
     */
    public boolean isNumCell (int pos) {

        return rowCells.get(pos).getCellType().equals("NumCell");
    }

    /**
     * Consulta contenido de una celda tipo Num
     * @param pos columna de la celda
     * @return contenido
     */
    public double getNum (int pos) {

        return rowCells.get(pos).getNum();
    }

    public String[] getRawRow(boolean showFormulas){
        String[] rawRow = new String[rowSize()];

        for(int i=0; i<rowSize(); ++i){
            rawRow[i] = rowCells.get(i).getRawContent(showFormulas);
        }
        return rawRow;
    };

    public String getRawContent(int col){
        return rowCells.get(col).getRawContent(false);
    }

    public boolean size(int pos) {

        if (!rowCells.get(pos).getCellType().equals("StringCell")) return false;
        StringOperation myOperation = new StringOperation();
        double size = myOperation.Length(rowCells.get(pos));
        return ModifyDouble(pos, size);
    }
}
