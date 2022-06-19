package Structure.Cells;

import Structure.Row;

/**
 * Tipo de Cell que contiene un String.
 * @author Javier Núñez Cerezuela
 */
public class StringCell extends Cell {

    private String content;

    /**
     * Crea una instancia de StringCell y le asigna el String entrado como su contenido.
     * @param fila fila de la celda
     * @param content String que se guardará como contenido de la celda.
     */
    public StringCell (Row fila, String content) {
        super(fila);
        this.content = content;
    }

    /**
     * Método que devuelve el contenido de la celda.
     * @return String que es el contenido de la celda.
     */
    public String getString(){
        return content;
    }

    /**
     * Método que devuelve un String con el tipo de celda.
     * @return String que es el tipo de celda.
     */
    public String getCellType(){
        return "StringCell";
    }

    /**
     * Método que sobrescribe el contenido por el valor introducido.
     * @param content contenido a guardar en la celda.
     */
    public void setContent(String content){
        this.content = content;
    }

    /**
     * Devuelve el contenido
     * @return Devuelve el contenido de la celda
     */
    public String getRawContent(boolean showFormulas){return content;}
}