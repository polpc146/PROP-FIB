package Persistencia;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Vector;

/**
 * Hace de intermediario entre la base de datos y el dominio
 * @author Sandra Buitrago Cervilla
 */
public class CtrlPersistency {

    private static CtrlPersistency inst;
    private CtrlFile cf = new CSV();

    /**
     * Retorna la única instancia que tiene la clase y si no tiene la crea
     * @return  instancia de la classe
     */
    public static CtrlPersistency getInstance() {
        if (CtrlPersistency.inst == null) inst = new CtrlPersistency();
        return inst;
    }

    /**
     * Crea la instancia de la classe
     */
    private CtrlPersistency() {}

    /*----CONSULTAR----*/

    /**
     * Retorna las hojas que tiene un documento
     * @param nameDoc Nombre del documento
     * @return Devuelve una lista de las hojas del documento
     * @throws IOException
     */
    public LinkedList<String> Sheets(String nameDoc) throws IOException {
        return cf.Sheets(nameDoc);
    }


    /*----ESCRITURA----*/

    /**
     * Guarda el contenido de una hoja en un fichero .csv
     * @param nameDoc nombre del documento donde se encuentra la hoja
     * @param nameSheet nombre de la hoja que se quiere guardar
     * @param table contenido de la hoja que se quiere guardar
     * @throws IOException
     */
    public void SaveSheets(String nameDoc, String nameSheet, String[][] table) throws IOException {
        cf.SaveSheets(nameDoc, nameSheet, table);
    }

    /**
     * Actualizar "index.csv"
     * @param nameDoc nombre del documento
     * @param sheets conjunto de las hojas que tiene el documento
     */
    public void SaveInIndex(String nameDoc, Vector<String> sheets) throws IOException {
        cf.SaveInIndex(nameDoc, sheets);
    }

    /*----CARGAR----*/

    /**
     * Consulta todos los documentos guardados
     * @return todos los documentos que haya guardados en el fichero "index.csv"
     * @throws IOException
     */
    public LinkedList<String> AllListDoc() throws IOException {
        return cf.LoadNamesDocsIndex();
    }

    /**
     * Cargar la hoja de un documento
     * @param nameDoc nombre del documento donde se encuentra la hoja
     * @param nameSheet nombre de la hoja que se quiere cargar
     * @return el contenido de la hoja
     * @throws IOException
     */
    public LinkedList<LinkedList<String>> LoadSheet(String nameDoc, String nameSheet) throws IOException {
        LinkedList<LinkedList<String>> p = cf.LoadSheet(nameDoc, nameSheet);
        return p;
    }


    /*----BORRAR----*/

    /**
     * Borra el documento y todas sus hojas
     * @param nameDoc nombre del documento que se quiere borrar
     * @param aux conjunto de hojas que tiene el documento
     * @throws IOException
     */
    public void deleteDocument(String nameDoc, LinkedList<String> aux) throws IOException {
        cf.deleteDocument(nameDoc,aux);
    }

    /**
     * Borra el fichero donde se encuentra toda la información de la hoja del documento "nameDoc"
     * @param nameDoc nombre del documento donde se encuentra la hoja
     * @param nameSheet nombre de la hoja que se quiere borrar
     */
    public void deleteSheet(String nameDoc, String nameSheet) throws IOException {
        cf.deleteSheet(nameDoc, nameSheet);
    }

    /**
     * Crea un archivo nuevo
     * @throws IOException
     */
    public void newFile() throws IOException {
        cf.newFile();
    }

    /**
     * Se borra el indice temporal antiguo
     */

    public void deleteOldFile() {
        cf.deleteOldFile();
    }
}
