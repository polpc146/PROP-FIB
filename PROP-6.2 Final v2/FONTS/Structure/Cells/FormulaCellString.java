package Structure.Cells;

import Structure.Row;

/**
 * Tipo de celda que contiene una fórmula y el resultado es un String.
 * @author Javier Núñez Cerezuela
 */
public class FormulaCellString extends FormulaCell{

    private StringCell resultCell;

    /**
     * Crea una instancia de FormulaCellString y le asigna como contenido
     * la formula y el resultado.
     * @param fila fila de la celda
     * @param formula String que es la fórmula que se quiere operar.
     * @param result String que es el resultado de la fórmula.
     */
    public FormulaCellString(Row fila,String formula, String result){
        super(fila,formula);
        StringCell resultCell = new StringCell(fila,result);
        this.resultCell = resultCell;
    }

    /**
     * Método que devuelve un String con el tipo de celda.
     * @return String que es el tipo de celda.
     */
    public String getCellType(){
        return "FormulaCellString";
    }

    /**
     * Método que devuelve el resultado de la celda
     * @return String que es el resultado de la celda
     */
    public String getString(){
        return resultCell.getString();
    }

    /**
     * Devuelve el contenido
     * @return Devuelve el contenido de la celda
     */
    @Override
    public String getRawContent(){return resultCell.getRawContent(false);}
}
