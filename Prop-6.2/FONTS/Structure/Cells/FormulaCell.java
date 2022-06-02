package Structure.Cells;

import Structure.Cells.Cell;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Tipo de celda que contiene una fórmula.
 * @author Javier Núñez Cerezuela
 */
public class FormulaCell extends Cell {

    private String formula;

    /**
     * Vector con todas las celdas que sean referenciadas en la operacion
     */
    private LinkedList<Cell> references;

    /**
     * Crea una intancia de FormulaCell y le asigna el String entrado como su contenido.
     * @param content String que es la fórmula que se quiere operar
     */
    public FormulaCell(String content){
        super();
        formula = content;

        LinkedList<Cell> ref= new LinkedList<Cell>();
        references = ref;
    }

    /**
     * Crea una intancia de FormulaCell, le asigna el String entrado como su contenido y
     * guarda las celdas a las que la formula hace referencia.
     * @param content String que es la fórmula que se quiere operar
     */
    public FormulaCell(String content, LinkedList<Cell> references){
        super();
        formula = content;
        this.references = references;
    }

    /**
     * Método que devuelve un String con el tipo de celda.
     * @return String que es el tipo de celda.
     */
    public String getCellType(){
        return "FormulaCell";
    }

    /**
     * Método que devuelve un String con la fórmula de la casilla.
     * @return String que es la fórmula guardada en la casilla.
     */
    public String getFormula(){
        return formula;
    }

    /**
     * Método que devuelve el vector de celdas que son referenciadas en la operacion
     * @return vector de celdas que son referenciadas en la operacion
     */
    public LinkedList<Cell> getReferences(){
        return references;
    }

    /**
     * Método que devuelve un valor de error en el caso que el hijo no tenga el tipo de valor
     * solicitado
     * @return mensaje de error
     */
    public double getNum(){ return 35505;}

    /**
     * Método que devuelve un valor de error en el caso que el hijo no tenga el tipo de valor
     * solicitado
     * @return mensaje de error
     */
    public String getString(){ return "ERROR";}

    /**
     * Método que devuelve un valor de error en el caso que el hijo no tenga el tipo de valor
     * solicitado
     * @return mensaje de error
     */
    public Date getDate(){
        GregorianCalendar c = new GregorianCalendar(0,0,1);
        Date d = c.getTime();
        return d;
    }

}
