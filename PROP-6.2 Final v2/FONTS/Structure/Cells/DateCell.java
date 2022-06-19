package Structure.Cells;

import Structure.Row;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Tipo de Cell que contiene una fecha
 * @author Javier Núñez Cerezuela
 */
public class DateCell extends Cell {

    private Date date;

    /**
     * Crea una instancia de DateCell y asigna la fecha entrada como su contenido.
     * @param date fecha que guardará como contenido de la celda.
     */
    public DateCell(Row fila, Date date){
        super(fila);
        this.date = date;
    }

    /**
     * Método que devuelve el contenido de la celda.
     * @return Date que es el contenido de la celda.
     */
    public Date getDate(){
        return date;
    }

    /**
     * Método que devuelve un String con el tipo de celda.
     * @return String que es el tipo de celda.
     */
    public String getCellType(){
        return "DateCell";
    }

    /**
     * Método que sobrescribe el contenido por el valor introducido.
     * @param date contenido a guardar en la celda.
     */
    public void setContent(Date date){
        this.date = date;
    }

    /**
     * Devuelve el contenido
     * @param showFormulas enseña la formula por pantalla
     * @return retorna el contenido de la celda
     */
    public String getRawContent(boolean showFormulas){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);}
}
