package Operations;

import Structure.Cells.*;

/**
 * StringOperation contiene todas las operaciones que nuestro programa podrá realizar con String
 * @author Pol Barco Martínez
 */

public class StringOperation {

    /**
     * Calcula el tamaño de un String
     * @param cell celda donde se encuentra el valor con el que haremos la operacíón
     * @return el tamaño del String que haya en la celda
     */
    public double Length (Cell cell) {
        String word = cell.getString();
        return word.length();
    }

    /**
     * Reemplaza una palabra tipo sting por otra
     * @param cell celda sobre la que realizaremos la operación
     * @param find palabra que queremos buscar en la celda
     * @param word palabra por la que queremos reemplazar
     */
    public void Replace (Cell cell, String find, String word) {
        if (Search(cell, find)) {
            String aux = cell.getString();
            String replace = aux.replaceFirst(find, word);
            cell.setContent(replace);
        }
    }

    /**
     * Busca una palabra en una celda
     * @param cell celda sobre la que haremos la operación
     * @param word palabra que queremos buscar
     * @return true si encuentra la palabra en la celda, false en caso contrario
     */
    public boolean Search (Cell cell, String word) {
        String aux = cell.getString();
        if (aux.contains(word)) return true;
        else return false;
    }
}

