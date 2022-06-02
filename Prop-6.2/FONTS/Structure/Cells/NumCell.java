package Structure.Cells;

import Structure.Cells.Cell;

/**
 * Tipo de Cell que contiene un numero en formato double
 * @author Javier Núñez Cerezuela
 */
public class NumCell extends Cell {

    private double content;

    /**
     * Crea una instancia de NumCell y asigna el double entrado como su contenido.
     * @param content double que se guardará como contenido de la celda.
     */
    public NumCell (double content) {
        super();
        this.content = content;
    }

    /**
     * Método que devuelve el contenido de la celda
     * @return double que es el contenido de la celda
     */
    public double getNum(){
        return content;
    }

    /**
     * Método que devuelve un String con el tipo de celda.
     * @return String que es el tipo de celda.
     */
    public String getCellType(){
        return "NumCell";
    }

    /**
     * Método que sobrescribe el contenido por el valor introducido.
     * @param content contenido a guardar en la celda.
     */
    public void setContent(double content){
        this.content = content;
    }
}

