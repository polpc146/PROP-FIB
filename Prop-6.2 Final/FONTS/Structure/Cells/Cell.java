package Structure.Cells;

import java.util.*;

/**
 * Una celda representa la unidad mínima que forma una Row. Contiene la informacion
 * que el usuario haya introducido y la información de todas aquellas celdas
 * que la referencien.
 * @author Javier Núñez Cerezuela
 */
public class Cell {

    /**
     * Lista de todas las celdas que referencian a esta
     */
    private LinkedList<FormulaCell> referencedBy;

    /**
     * Crea una instancia de celda (Cell).
     */
    public Cell(){
        LinkedList<FormulaCell> ref = new LinkedList<FormulaCell>();
        referencedBy = ref;
    }

    /**
     * Añade la información de la FormulaCell que referencia a esta celda.
     * @param fc FormulaCell que referencia a esta celda
     */
    public void addReferenced (FormulaCell fc){
        referencedBy.add(fc);
    }

    /**
     * Elimina la información de la FormulaCell que referencia a esta celda.
     * @param fc FormulaCell que referencia a esta celda
     */
    public void deleteReferenced (FormulaCell fc){
        referencedBy.remove(fc);
    }

    /**
     * Método que devuelve la lista de FormulaCell que referencian a esta
     * @return la lista de FormulaCell que referencian a esta
     */
    public LinkedList<FormulaCell> getReferencedBy(){
        return referencedBy;
    }

    /**
     * Método que devuelve un String con el tipo de celda.
     * @return String que es el tipo de celda.
     */
    public String getCellType(){
        return "Cell";
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

    /**
     * Que devuelve el contenido de la celda en string sea el que sea. En caso de formulas, pasa la formula y no su contenido.
     * @return
     */
    public String getRawContent(boolean showFormulas){return "";}

    /**
     * Método que no hace nada en el caso de que no se acceda al hijo con el tipo
     * de valor correcto
     * @param num valor que se quiere guardar en la celda
     */
    public void setContent(double num){};

    /**
     * Método que no hace nada en el caso de que no se acceda al hijo con el tipo
     * de valor correcto
     * @param date valor que se quiere guardar en la celda
     */
    public void setContent(Date date){};

    /**
     * Método que no hace nada en el caso de que no se acceda al hijo con el tipo
     * de valor correcto
     * @param string valor que se quiere guardar en la celda
     */
    public void setContent(String string){};

}