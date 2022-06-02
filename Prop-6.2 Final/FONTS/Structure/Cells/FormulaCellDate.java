package Structure.Cells;

import java.util.*;

/**
 * Tipo de celda que contiene una fórmula y el resultado es una fecha.
 * @author Javier Núñez Cerezuela
 */
public class FormulaCellDate extends FormulaCell{

    private DateCell resultCell;

    /**
     * Crea una instancia de FormulaCellDate y le asigna como contenido
     * la fórmula y su resultado.
     * @param formula String que es la fórmula que se quiere operar.
     * @param result Date que es el resultado de la operación.
     */
    public FormulaCellDate(String formula, Date result){
        super(formula);
        DateCell resultCell = new DateCell(result);
        this.resultCell = resultCell;
    }

    /**
     * Método que devuelve un String con el tipo de celda.
     * @return String que es el tipo de celda.
     */
    public String getCellType(){
        return "FormulaCellDate";
    }

    /**
     * Método que devuelve el resultado de la fórmula.
     * @return Date que es el resultado de la fórmula.
     */
    public Date getDate(){
        return resultCell.getDate();
    }

    @Override
    public String getRawContent(){return resultCell.getRawContent(false);}
}
