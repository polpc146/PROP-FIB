package Structure.Cells;

import Structure.Row;

import java.util.*;

/**
 * Tipo de celda que contiene una fórmula.
 * @author Javier Núñez Cerezuela
 */
public abstract class FormulaCell extends Cell {

    private String formula = "";

    /**
     * Vector con todas las celdas que sean referenciadas en la operacion
     */
    private LinkedList<Cell> references;

    /**
     * Crea una intancia de FormulaCell y le asigna el String entrado como su contenido.
     * @param fila  fila de la celda
     * @param content String que es la fórmula que se quiere operar
     */
    public FormulaCell(Row fila, String content){
        super(fila);
        formula = content;

        LinkedList<Cell> ref= new LinkedList<Cell>();
        references = ref;
    }

    public void setReferences(LinkedList<Cell> references) {
        this.references = references;
    }

    /**
     * Crea una intancia de FormulaCell, le asigna el String entrado como su contenido y
     * guarda las celdas a las que la formula hace referencia.
     * @param content String que es la fórmula que se quiere operar
     * @param fila fila de la celda
     * @param references  referencias de otras celdas a esta
     */
    public FormulaCell(Row fila, String content, LinkedList<Cell> references){
        super(fila);
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

    /**
     * Devuelve el contenido
     * @param showFormulas enseña la formula
     * @return devuelve el contenido
     */
    public String getRawContent(boolean showFormulas){
        if(showFormulas){return formula;}
        else{return getRawContent();}
    }

    public abstract String getRawContent();

    /**
     * Reescriu formula segons el tipus que sigui.
     */
    public void refreshFormula(){
        if(formula.indexOf("+") != -1 && references.size()==2){
            Cell cell1 = references.get(0);
            Cell cell2 = references.get(1);

            int fila1 = cell1.getRowPos();
            String col1 = intToLetters(cell1.getColPos());

            int fila2 = cell2.getRowPos();
            String col2 = intToLetters(cell2.getColPos());

            formula = "="+col1+","+String.valueOf(fila1)+"+"+col2+","+String.valueOf(fila2);
        }
        else if(formula.indexOf("+") != -1 && references.size()==1){
            Cell cell1 = references.get(0);

            int fila1 = cell1.getRowPos();
            String col1 = intToLetters(cell1.getColPos());

            formula = "="+col1+","+String.valueOf(fila1)+"+"+formula.substring(formula.indexOf("+")+1);
        }

        else if(formula.indexOf("-") != -1 && references.size()==2){
            Cell cell1 = references.get(0);
            Cell cell2 = references.get(1);

            int fila1 = cell1.getRowPos();
            String col1 = intToLetters(cell1.getColPos());

            int fila2 = cell2.getRowPos();
            String col2 = intToLetters(cell2.getColPos());

            formula = "="+col1+","+String.valueOf(fila1)+"-"+col2+","+String.valueOf(fila2);
        }
        else if(formula.indexOf("-") != -1 && references.size()==1){
            Cell cell1 = references.get(0);

            int fila1 = cell1.getRowPos();
            String col1 = intToLetters(cell1.getColPos());

            formula = "="+col1+","+String.valueOf(fila1)+"-"+formula.substring(formula.indexOf("-")+1);
        }

        else if(formula.indexOf("*") != -1 && references.size()==2){
            Cell cell1 = references.get(0);
            Cell cell2 = references.get(1);

            int fila1 = cell1.getRowPos();
            String col1 = intToLetters(cell1.getColPos());

            int fila2 = cell2.getRowPos();
            String col2 = intToLetters(cell2.getColPos());

            formula = "="+col1+","+String.valueOf(fila1)+"*"+col2+","+String.valueOf(fila2);
        }
        else if(formula.indexOf("*") != -1 && references.size()==1){
            Cell cell1 = references.get(0);

            int fila1 = cell1.getRowPos();
            String col1 = intToLetters(cell1.getColPos());

            formula = "="+col1+","+String.valueOf(fila1)+"*"+formula.substring(formula.indexOf("*")+1);
        }

        else if(formula.indexOf("/") != -1 && references.size()==2){
            Cell cell1 = references.get(0);
            Cell cell2 = references.get(1);

            int fila1 = cell1.getRowPos();
            String col1 = intToLetters(cell1.getColPos());

            int fila2 = cell2.getRowPos();
            String col2 = intToLetters(cell2.getColPos());

            formula = "="+col1+","+String.valueOf(fila1)+"/"+col2+","+String.valueOf(fila2);
        }
        else if(formula.indexOf("/") != -1 && references.size()==1){
            Cell cell1 = references.get(0);

            int fila1 = cell1.getRowPos();
            String col1 = intToLetters(cell1.getColPos());

            formula = "="+col1+","+String.valueOf(fila1)+"/"+formula.substring(formula.indexOf("/")+1);
        }

        else if(formula.indexOf(":") != -1){
            //Dos rangos
            if(formula.substring(formula.indexOf(":")+1).indexOf(":") != -1){

            }
            //Un rango

        }
        else{
            Cell cellReferenciada = references.get(0);

            int fila = cellReferenciada.getRowPos();
            int col = cellReferenciada.getColPos();

            //Pasar col a letra
            String colS = intToLetters(col);

            //Reescribir Formula cambiando la fila y la columna(en letra)
            int inici = formula.indexOf("(");
            formula = formula.substring(0,inici+1) + colS+ ","+ String.valueOf(fila)+")";
        }

    }

    /**
     * Métode que donat un numero de columna el transforma a les lletres que la identifiquen
     * @param col
     * @return
     */
    private String intToLetters(int col){
        if (col == 0){return "a";}
        else{
            String s = "";
            while(col != 0){
                char c = (char)(col%27+97);
                s = c+s;
                col = col/27;
            }
            return s;
        }
    }
}
