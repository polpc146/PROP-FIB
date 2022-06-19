package Structure.Cells;

import Structure.Row;

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
     * @param fila  fila de la celda
     * @param formula String que es la fórmula que se quiere operar
     * @param result double que es el resultado de la fórmula
     */
    public FormulaCellNum(Row fila, String formula, double result){
        super(fila, formula);
        NumCell resultCell = new NumCell(fila,result);
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

    /**
     * Devuelve el contenido
     * @return Devuelve el contenido de la celda
     */

    @Override
    public String getRawContent(){return resultCell.getRawContent(false);}
}
