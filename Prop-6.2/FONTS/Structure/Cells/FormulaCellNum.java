package Structure.Cells;
import java.util.*;

/**
 * Tipo de celda que contiene una fórmula y el resultado es un número
 * @author Javier Núñez Cerezuela
 */
public class FormulaCellNum extends FormulaCell{

    /**
     * Celda que contiene el resultado
     */
    private NumCell resultCell;

    /**
     * Crea una intancia de FormulaCellNum y le asigna como contenido
     * la formula y el resultado de la operacion
     * @param formula String que es la fórmula que se quiere operar
     * @param result double que es el resultado de la fórmula
     */
    public FormulaCellNum(String formula, double result){
        super(formula);
        NumCell resultCell = new NumCell(result);
        this.resultCell = resultCell;
    }

    /**
     * Método que devuelve un String con el tipo de celda.
     * @return String que es el tipo de celda.
     */
    public String getCellType(){
        return "FormulaCellNum";
    }

    /**
     * Método que devuelve el resultado de la celda
     * @return double que es el resultado de la celda
     */
    public double getNum(){
        return resultCell.getNum();
    }
}
