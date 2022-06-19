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
    private Sheet sheet;

    /**
     * Se inicializa la lista de celdas
     * @param numColumns número de celdas que tendrá la fila
     */
    public Row (Sheet sheet ,int numColumns) {
        this.sheet=sheet;
        rowCells = new LinkedList<>();
        for (int i = 0; i < numColumns; i++) rowCells.add(new Cell(this));

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

        rowCells.add(pos, new Cell(this));
        return true;
    }

    /**
     * Añade una celda de tipo String
     * @param pos posición donde añadir la celda en la fila
     * @param content contenido de la celda
     * @return true
     */
    public boolean addCellString (int pos, String content) {

        rowCells.add(pos, new StringCell(this,content));
        return true;
    }

    /**
     * Añade una celda de tipo double
     * @param pos posición donde añadir la celda en la fila
     * @param content contenido de la celda
     * @return true
     */
    public boolean addCellDouble (int pos, double content) {

        rowCells.add(pos, new NumCell(this,content));
        return true;
    }

    /**
     * Añade una celda de tipo Date
     * @param pos posición donde añadir la celda en la fila
     * @param date contenido de la celda
     * @return true
     */
    public boolean addCellDate (int pos, Date date) {

        rowCells.add(pos, new DateCell(this,date));
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
     * Elimina la celda y crea otra de tipo formulacell
     * @param column columna de la celda
     * @param formula formula de la celda
     * @param result resultado de la formula
     * @return true si se añade la nueva celda, false en caso contrario
     */
    public boolean CreateFormulaCellNum(int column, String formula, double result) {
        if (!removeCell(column)) return false;
        rowCells.add(column, new FormulaCellNum(this,formula, result));
        return true;
    }
    /**
     * Elimina la celda y crea otra de tipo formulacellstring
     * @param column columna de la celda
     * @param formula formula de la celda
     * @param result resultado de la formula
     * @return true si se añade la nueva celda, false en caso contrario
     */
    public boolean CreateFormulaCellString(int column, String formula, String result) {
        if (!removeCell(column)) return false;
        rowCells.add(column, new FormulaCellString(this,formula, result));
        return true;
    }

    /**
     * Elimina celda y crea una nueva vacía
     * @param pos columna de la celda
     * @return true
     */
    public boolean setCell (int pos) {

        rowCells.remove(pos);
        rowCells.add(pos, new Cell(this));
        return true;
    }
    /**
     * Trunca el valor de una celda (función para el botón)
     * @param pos columna de la celda
     * @return true si se trunca la celda, false en caso contrario
     */
    public boolean Truncate2 (int pos) {
        if (!rowCells.get(pos).getCellType().equals("NumCell") && !rowCells.get(pos).getCellType().equals("FormulaCellNum")) return false;
        NumberOperation myOperation = new NumberOperation();
        double trunc = myOperation.Truncate(rowCells.get(pos).getNum());
        return ModifyDouble(pos, trunc);
    }

    /**
     * Trunca el valor de una celda
     * @param pos columna de la celda
     * @return devuelve el valor de la celda truncada
     */
    public double Truncate (int pos) {
        double trunc = -99999;
        if (!rowCells.get(pos).getCellType().equals("NumCell") && !rowCells.get(pos).getCellType().equals("FormulaCellNum")) return trunc;
        NumberOperation myOperation = new NumberOperation();
        trunc = myOperation.Truncate(rowCells.get(pos).getNum());
        return trunc;
    }
    /**
     * Incrementa el valor de una celda (función para el botón)
     * @param pos columna de la celda
     * @return true si se incrementa la celda, false en caso contrario
     */
    public boolean Increase2(int pos) {

        if (!rowCells.get(pos).getCellType().equals("NumCell") && !rowCells.get(pos).getCellType().equals("FormulaCellNum")) return false;
        NumberOperation myOperation = new NumberOperation();
        double inc = myOperation.Increase(rowCells.get(pos).getNum());
        return ModifyDouble(pos, inc);
    }

    /**
     * Incrementa el valor de una celda
     * @param pos columna de la celda
     * @return devuelve el valor de la celda incrementada
     */
    public double Increase (int pos) {
        double inc = -99999;
        if (!rowCells.get(pos).getCellType().equals("NumCell") && !rowCells.get(pos).getCellType().equals("FormulaCellNum")) return inc;
        NumberOperation myOperation = new NumberOperation();
        inc = myOperation.Increase(rowCells.get(pos).getNum());
        return inc;
    }

    /**
     * Calcula valor absoluto de una celda
     * @param pos columna de la celda
     * @return devuelve el valor absoluto de la celda
     */
    public double Absolut (int pos) {
        double abs = -99999;
        if (!rowCells.get(pos).getCellType().equals("NumCell") && !rowCells.get(pos).getCellType().equals("FormulaCellNum")) return abs;
        NumberOperation myOperation = new NumberOperation();
        abs = myOperation.Absolute(rowCells.get(pos).getNum());
        return abs;
    }

    /**
     * Calcula valor absoluto de una celda (función para el botón)
     * @param pos columna de la celda
     * @return true si se incrementa la celda, false en caso contrario
     */
    public boolean Absolut2 (int pos) {

        if (!rowCells.get(pos).getCellType().equals("NumCell") && !rowCells.get(pos).getCellType().equals("FormulaCellNum")) return false;
        NumberOperation myOperation = new NumberOperation();
        double abs = myOperation.Absolute(rowCells.get(pos).getNum());
        return ModifyDouble(pos, abs);
    }
    /**
     * Conversión a binario o hexadecimal de una celda
     * @param pos columna de la celda
     * @param Criterium Binary[B] o Hexadecimal[H]
     * @return devuelve el valor de la conversion de una celda
     */
    public String Conversion (int pos, String Criterium) {
        String conv = "";
        if (!rowCells.get(pos).getCellType().equals("NumCell") && !rowCells.get(pos).getCellType().equals("FormulaCellNum")) return conv;
        NumberOperation myOperation = new NumberOperation();
        if (Criterium.equalsIgnoreCase("B") || Criterium.equalsIgnoreCase("Binary")) {

            conv = myOperation.Binary(rowCells.get(pos).getNum());
        }
        else conv = myOperation.Hexadecimal(rowCells.get(pos).getNum());
        return conv;
    }

    /**
     * Conversión a binario o hexadecimal de una celda (función para el botón)
     * @param pos columna de la celda
     * @param Criterium Binary[B] o Hexadecimal[H]
     * @return true si se hace la conversion, false en caso contrario
     */
    public boolean Conversion2 (int pos, String Criterium) {

        if (!rowCells.get(pos).getCellType().equals("NumCell") && !rowCells.get(pos).getCellType().equals("FormulaCellNum")) return false;
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

        if (!rowCells.get(pos).getCellType().equals("DateCell") && !rowCells.get(pos).getCellType().equals("FormulaCellDate")) return false;
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
     * Extrae elemento de una fecha
     * @param pos columna de la celda
     * @param Criterium Day[D], Month[M] o Year[Y]
     * @return true si se extrae el elemento, false en caso contrario
     */
    public double ExtractElement2 (int pos, String Criterium) {
        if (!rowCells.get(pos).getCellType().equals("DateCell") && !rowCells.get(pos).getCellType().equals("FormulaCellDate")) return -99999;
        DateOperation myOperation = new DateOperation();
        if (Criterium.equalsIgnoreCase("D") || Criterium.equalsIgnoreCase("day")) {
            return myOperation.Day(rowCells.get(pos).getDate());
        }
        else if (Criterium.equalsIgnoreCase("M") || Criterium.equalsIgnoreCase("month")) {

            String month = myOperation.Month(rowCells.get(pos).getDate());
            return whatMonthis(month);
        }
        else {
            return myOperation.Year(rowCells.get(pos).getDate());
        }
    }
    private Double whatMonthis (String month) {
        double m = -1;
        if (month.equals("January")) m = 0 ;
        else if (month.equals("February")) m = 1;
        else if (month.equals("March")) m = 2;
        else if (month.equals("April")) m = 3;
        else if (month.equals("May")) m = 4;
        else if (month.equals("June")) m = 5;
        else if (month.equals("July")) m = 6;
        else if (month.equals("August")) m = 7;
        else if (month.equals("September")) m = 8;
        else if (month.equals("October")) m = 9;
        else if (month.equals("November")) m = 10;
        else m = 11;
        return m;
    }

    /**
     * Consulta día de la semana
     * @param pos columna de la celda
     * @return true si se modifica la celda, false en caso contrario
     */
    public String DayOfTheWeek (int pos) {
        String day = "";
        if (!rowCells.get(pos).getCellType().equals("DateCell") && !rowCells.get(pos).getCellType().equals("FormulaCellDate")) return day;
        DateOperation myOperation = new DateOperation();
        day = myOperation.DayOfWeek(rowCells.get(pos).getDate());
        return day;
        //return ModifyString(pos, day);
    }

    /**
     * Consulta día de la semana
     * @param pos columna de la celda
     * @return true si se modifica la celda, false en caso contrario
     */
    public boolean DayOfTheWeek2 (int pos) {

        if (!rowCells.get(pos).getCellType().equals("DateCell") && !rowCells.get(pos).getCellType().equals("FormulaCellDate")) return false;
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
     * Consulta si la celda es de tipo Formulanum
     * @param pos columna de la celda
     * @return true si es de tipo Num, false en caso contrario
     */
    public boolean isFormulaNumCell (int pos) {

        return rowCells.get(pos).getCellType().equals("FormulaCellNum");
    }

    /**
     * Consulta contenido de una celda tipo Num
     * @param pos columna de la celda
     * @return contenido
     */
    public double getNum (int pos) {

        return rowCells.get(pos).getNum();
    }

    /**
     * Devuelve la fila
     * @param showFormulas enseña la formula
     * @return devuelve la fila
     */
    public String[] getRawRow(boolean showFormulas){
        String[] rawRow = new String[rowSize()];

        for(int i=0; i<rowSize(); ++i){
            rawRow[i] = rowCells.get(i).getRawContent(showFormulas);
        }
        return rawRow;
    };

    /**
     * Devuelve la columna
     * @param col columna de la celda
     * @return devuelve la columna de la celda
     */
    public String getRawContent(int col){
        return rowCells.get(col).getRawContent(false);
    }

    /**
     * Devuelve si se ha podido hacer el size de un string
     * @param pos columna de la celda
     * @return Devuelve true si se ha podido hacer la operación, false en caso contrario
     */
    public boolean size2(int pos) {

        if (!rowCells.get(pos).getCellType().equals("StringCell") && !rowCells.get(pos).getCellType().equals("FormulaCellString")) return false;
        StringOperation myOperation = new StringOperation();
        double size = myOperation.Length(rowCells.get(pos));
        return ModifyDouble(pos, size);
    }

    /**
     * Devuelve el resultado de realizar un size
     * @param pos columna de la celda
     * @return Devuelve el resultado de la operación
     */
    public double size(int pos) {
        double size = -99999;
        if (!rowCells.get(pos).getCellType().equals("StringCell") && !rowCells.get(pos).getCellType().equals("FormulaCellString")) return size;
        StringOperation myOperation = new StringOperation();
        size = myOperation.Length(rowCells.get(pos));
        return size;
    }

    /**
     * Devuelve la columna de una celda
     * @param cell celda
     * @return devuelve la posición de la columna de una celda
     */
    public int getColPos(Cell cell) {
        return rowCells.indexOf(cell);
    }

    /**
     * Devuelve la fila de una celda
     * @param cell celda
     * @return devuelve la posición de la fila de una celda
     */
    public int getRowPos(Cell cell){
        return sheet.getRowPos(this);
    }
}
