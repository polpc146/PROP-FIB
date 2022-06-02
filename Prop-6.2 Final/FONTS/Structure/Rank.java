package Structure;

import Operations.*;
import Structure.Cells.*;
import java.util.*;
/**
 * Rango
 * Representa un conjunto de celdas.
 * Contiene una lista con las celdas de ese rango, el número de filas y columnas del rango
 * @author Sandra Buitrago Cervilla
 */
public class Rank {

    private LinkedList<Cell> RankCell;
    private int numRows;
    private int numColumns;

    /**
     * Crea una instancia de rango
     * @param rankCell lista de celdas en el rango
     * @param numRows número de filas del rango
     * @param numColumns número de columnas del rango
     */
    public Rank(LinkedList<Cell> rankCell, int numRows, int numColumns) {

        RankCell = rankCell;
        this.numRows = numRows;
        this.numColumns = numColumns;
    }
    /**
     * Consulta la lista de celdas que forman el rango
     * @return lista de celdas
     */
    public LinkedList<Cell> getRankCell() {

        return RankCell;
    }
    /**
     * Modifica un rango de celdas
     * @return true
     */
    public boolean setRankCell(LinkedList<Cell> rankCell) {

        RankCell = rankCell;
        return true;
    }
    /**
     * Consulta el número de filas del rango
     * @return el número de filas del rango
     */
    public int getNumRows() {
        return numRows;
    }

    /**
     * Consulta el número de columnas del rango
     * @return el número de columnas del rango
     */
    public int getNumColumns() {
        return numColumns;
    }
    /**
     * Consulta la celda en la posición pos
     * @return celda en la posición pos
     */
    public Cell getCell(int pos) {
        return RankCell.get(pos);
    }
    /**
     * Llama a una operación de rango para que haga la media
     * @return el resultado de la operación
     */
    public double Average() {
        RankOperation myOperation = new RankOperation();
        return myOperation.Average(RankCell);
    }
    /**
     * Llama a una operación de rango para que haga la mediana
     * @return el resultado de la operación
     */
    public double Median() {
        RankOperation myOperation = new RankOperation();
        return myOperation.Median(RankCell);
    }
    /**
     * Llama a una operación de rango para que haga la varianza
     * @return el resultado de la operación
     */
    public double Variance() {
        RankOperation myOperation = new RankOperation();
        return myOperation.Variance(RankCell);
    }

    /**
     * Llama a una operación de rango para que haga la desviación estándar
     * @return el resultado de la operación
     */
    public double StandardDeviation() {
        RankOperation myOperation = new RankOperation();
        return myOperation.StandardDeviation(RankCell);
    }
    /**
     * Comprueba si todas las celdas del rango són del mismo tipo
     * @return el tipo de celdas del rango
     */
    public char CheckRank() {
        String type = RankCell.get(0).getCellType();
        if (type.equals("Cell")) return 'e';
        for (int i = 1; i < RankCell.size(); ++i) {
            if (!RankCell.get(i).getCellType().equals(type)) return 'e';
        }
        return type.toCharArray()[0];
    }
    /**
     * Devuelve una lista de celdas
     * @return el resultado de la operación es un vector con las celdas de rankcell
     */
    public LinkedList<Cell> GetAuxCells() {
        LinkedList<Cell> result = new LinkedList<>();
        String type = "";
        for (Cell cell : RankCell) {
            type = cell.getCellType();
            switch (type) {
                case "Cell":
                    result.add(new Cell());
                    break;
                case "NumCell":
                    result.add(new NumCell(cell.getNum()));
                    break;
                case "StringCell":
                    result.add(new StringCell(cell.getString()));
                    break;
                default:
                    result.add(new DateCell(cell.getDate()));
                    break;
            }
        }
        return result;
    }

    /**
     * Borra un rango
     * @return true
     */
    public boolean RemoveRank() {
        Cell cell = new Cell();

        for (int i = 0; i < RankCell.size(); i++) {
            if (RankCell.get(i).getCellType().equals("StringCell")) RankCell.get(i).setContent("");
            if (RankCell.get(i).getCellType().equals("NumCell")) RankCell.get(i).setContent(-99999);
            if (RankCell.get(i).getCellType().equals("DateCell")) {
                GregorianCalendar calendar = new GregorianCalendar(1, 0, 1);
                Date date = calendar.getTime();
                RankCell.get(i).setContent(date);
            }
        }
        return true;
    }

    /**
     * Llama a una operación de strings para que reemplace un elemento de las celdas con otro
     * @return true
     */
    public boolean Replace(String ElementToReplace, String ElementReplaced) {
        StringOperation myOperation = new StringOperation();
        for (int i = 0; i < RankCell.size(); ++i) {
            if (RankCell.get(i).getCellType().equals("StringCell")){
                myOperation.Replace(RankCell.get(i), ElementToReplace, ElementReplaced);
            }
        }
        return true;
    }

    /**
     * Llama a una operación de strings para que se haga la longitud del contenido de la celda
     * @return el resultado de la operación
     */
    public double Size() {
        StringOperation myOperation = new StringOperation();
        double size = 0;
        for (Cell cell : RankCell) {
            if (cell.getCellType().equals("StringCell")) {
                size += myOperation.Length(cell);
            }
        }
        return size;
    }

    /**
     * Llama a una operación de strings para que se haga la búsqueda de un string
     * @return true si lo ha encontrado, false no ha encontrado la palabra.
     */
    public boolean Search(String SearchS) {
        StringOperation myOperation = new StringOperation();
        for (Cell cell : RankCell) {
            if (cell.getCellType().equals("StringCell")) {
                if (myOperation.Search(cell, SearchS)) return true;
            }
        }
        return false;
    }
}
