package Persistencia;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Vector;


/**
 * Classe padre de CSV
 * @author Pol Barco Martínez
 */
public class CtrlFile {
    private static CtrlFile inst;

    private CSV csv;


    /**
     * Retorna la única instancia que tiene la clase y si no en té cap la crea
     * @return la única instancia de la classe
     */
    public static CtrlFile getInstance() {
        if (CtrlFile.inst == null) inst = new CtrlFile();
        return inst;
    }

    /**
     * Crea la instancia de la classe
     */
    public CtrlFile() {}

    /*----CONSULTAR----*/

    /**
     * Retorna las hojas que tiene un documento
     * @param nameDoc nombre de la hoja
     * @return devuleve las hojas que tiene el documento
     */
    public LinkedList<String> Sheets(String nameDoc) throws IOException {
        LinkedList<String> ListaSheets = new LinkedList<String>();
        return ListaSheets;
    }


    /*----ESCRITURA----*/

    /**
     * Guarda el contenido de una hoja en un fichero .csv
     * @param nameDoc nombre del documento donde se encuentra la hoja
     * @param nameSheet nombre de la hoja que se quiere guardar
     * @param table contenido de la hoja que se quiere guardar
     * @throws IOException
     */
    public void SaveSheets(String nameDoc, String nameSheet, String[][] table) throws IOException {}

    /**
     * Actualizar "index.csv" añadiendo el ultimo documento que se quiere guardar
     * @param nameDoc nombre del documento
     * @param sheets conjunto de las hojas que tiene el documento
     */
    public void SaveInIndex(String nameDoc, Vector<String> sheets) throws IOException {
    }

    /*----CARGAR----*/

    /**
     * Carga todos los nombres de los documentos que hayan en el fichero "index.csv"
     * @return los nombres de los documentos
     */
    public LinkedList<String> LoadNamesDocsIndex() throws IOException {
        LinkedList<String> ListaDocs = new LinkedList<String>();
        return ListaDocs;
    }

    /**
     * Cargar la hoja de un documento
     * @param nameDoc nombre del documento donde se encuentra la hoja
     * @param nameSheet nombre de la hoja que se quiere cargar
     * @return el contenido de la hoja
     */
    public LinkedList<LinkedList<String>> LoadSheet(String nameDoc, String nameSheet) throws IOException {
        LinkedList<LinkedList<String>> content = new LinkedList<>();
        return content;
    }


    /*----BORRAR----*/

    /**
     * Borra el documento y todas sus hojas
     * @param nameDoc nombre del documento que se quiere borrar
     * @throws IOException
     */
    public void deleteDocument(String nameDoc, LinkedList<String> nameDocs) throws IOException {
    }

    /**
     * Borra el fichero donde se encuentra toda la información de la hoja del documento "nameDoc"
     * @param nameDoc nombre del documento donde se encuentra la hoja
     * @param nameSheet nombre de la hoja que se quiere borrar
     */
    public void deleteSheet(String nameDoc, String nameSheet) throws IOException {
    }

    /**
     * Crea un nuevo fichero
     */
    public void newFile() throws IOException {
    }

    /**
     * Elimina un fichero
     */
    public void deleteOldFile() {
    }
}
