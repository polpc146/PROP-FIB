package Persistencia;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Vector;


/**
 * Classe hija de CtrlFile que almacena las funciones de la capa de persistencia
 * @author Pol Barco Martínez
 */
public class CSV extends CtrlFile {

    private static CSV inst;
    private CtrlLoad cl;
    private CtrlWrite cw;


    /**
     * Retorna la única instancia que tiene la clase y si no tiene la crea
     *
     * @return la única instancia de la classe
     */
    public static CSV getInstance() {
        if (CSV.inst == null) inst = new CSV();
        return inst;
    }

    /**
     * Crea la instancia de la classe
     */
    public CSV() {
        super();
        cl = CtrlLoad.getInstance();
        cw = CtrlWrite.getInstance();
    }


    /*----CONSULTAR----*/

    /**
     * Consulta las hojas que tiene el documento
     * @param nameDoc nombre del documento
     * @return las hojas que tiene un documento
     * @throws IOException
     */
    @Override
    public LinkedList<String> Sheets(String nameDoc) throws IOException {
        LinkedList<String> d = new LinkedList<String>();
        File e = new File("index.csv");
        if (!e.exists()) return d;
        else {
            FileReader f = new FileReader("index.csv");
            char c;
            int n;
            String content = "";
            boolean find = false;

            n = f.read();

            //Llegeix i retorna -1 si troba el final, si passa aquí significa que el fitxer està buit
            while (n != -1 && !find) {
                c = (char) n;
                while (c != ';' && n != -1) {
                    content += c;
                    n = f.read();
                    c = (char) n;
                }

                if (content.equals(nameDoc)) {
                    find = true;
                    content = "";
                    n = f.read();
                    c = (char) n;

                    while (c != '\n' && n != -1) {
                        content += c;
                        n = f.read();
                        c = (char) n;
                        if (c == ';') {
                            d.add(content);
                            content = "";
                            n = f.read();
                            c = (char) n;
                        }
                    }
                    d.add(content);
                } else {
                    content = "";
                    n = f.read();
                    c = (char) n;
                    while (c != '\n' && n != -1) {
                        n = f.read();
                        c = (char) n;
                    }
                }
                n = f.read();
            }
            f.close();
            return d;
        }
    }


    /*----ESCRITURA----*/

    /**
     * Guarda los datos
     * @param table los datos que se quieren guardar
     * @param nameFile nombre del fichero donde se quieren guardar los datos
     * @throws IOException
     */
    private void SaveDades(String[][] table, String nameFile) throws IOException {
        File f = new File(nameFile);

        if (!f.exists()) {
            f.createNewFile();
            cw.SaveInFile(f, table);
        }
        else {
            File temp = new File("temporal");

            temp.createNewFile();
            cw.SaveInFile(temp, table);
            f.delete();
            temp.renameTo(f);
        }
    }

    /**
     * Guarda el contenido de la hoja en un .csv con el nameDoc+nameSheet
     * @param nameDoc nombre del documento donde se encuentra la hoja
     * @param nameSheet nombre de la hoja que se quiere guardar
     * @param table contenido de la hoja
     * @throws IOException
     */
    @Override
    public void SaveSheets(String nameDoc, String nameSheet, String[][] table) throws IOException {
        String name = nameDoc + nameSheet + ".csv";
        SaveDades(table, name);
    }

    /**
     * Actualizar "index.csv" añadiendo el ultimo documento que se quiere guardar
     * @param nameDoc nombre del documento
     * @param sheets conjunto de las hojas que tiene el documento
     */
    @Override
    public void SaveInIndex(String nameDoc, Vector<String> sheets) throws IOException {
        cw.SaveIndex("temporal", nameDoc, sheets);
    }


    /*----CARGAR----*/

    /**
     * Carga todos los nombres de los documentos que hayan en el fichero "index.csv"
     * @return los nombres de los documentos
     * @throws IOException
     */
    @Override
    public LinkedList<String> LoadNamesDocsIndex() throws IOException {

        File f2 = new File("index.csv");
        LinkedList<String> d = new LinkedList<String>();
        if(!f2.exists()) return d;
        else {
            FileReader f = new FileReader("index.csv");
            char c;
            int n;
            String content = "";

            n = f.read();

            //Llegeix i retorna -1 si troba el final, si passa aquí significa que el fitxer està buit
            while (n != -1) {

                c = (char) n;
                while (c != ';' && n != -1) {
                    content += c;
                    n = f.read();
                    c = (char) n;
                }
                d.add(content);
                content = "";
                n = f.read();
                c = (char) n;

                while (c != '\n' && n != -1) {
                    n = f.read();
                    c = (char) n;
                }
            }
            f.close();
            return d;
        }
    }

    /**
     * Cargar la hoja de un documento
     * @param nameDoc nombre del documento donde se encuentra la hoja
     * @param nameSheet nombre de la hoja que se quiere cargar
     * @return el contenido de la hoja
     * @throws IOException
     */
    @Override
    public LinkedList<LinkedList<String>> LoadSheet(String nameDoc, String nameSheet) throws IOException {
        String name = nameDoc + nameSheet + ".csv";
        File f = new File(name);
        return cl.Load(f);
    }

    /*----BORRAR----*/

    /**
     * Borra el documento y todas sus hojas
     * @param nameDoc nombre del documento que se quiere borrar
     * @throws IOException
     */

    public void deleteDocument(String nameDoc, LinkedList<String> nameDocs) throws IOException {

        //Borra los archivos csv de las hojas
        LinkedList<String> sheets = Sheets(nameDoc);
        for (int i = 0; i < sheets.size(); ++i) {
            deleteSheet(nameDoc,sheets.get(i));
        }

        newFile();
        //actualizar el "index.csv"
        LinkedList<String> docs = LoadNamesDocsIndex();

        for (int i = 0; i < docs.size()-1; ++i) {

            char[] n = docs.get(i).toCharArray();
            char[] m = nameDoc.toCharArray();
            String name1 = "";
            String name2 = "";

            for (int it = 0; it < n.length; ++it) {
                if ((n[it] >= 'a' && n[it] <= 'z') || (n[it] >= 'A' && n[it] <= 'Z')) {
                    name1 += n[it];
                }
            }

            for (int it = 0; it < m.length; ++it) {
                if ((m[it] >= 'a' && m[it] <= 'z') || (m[it] >= 'A' && m[it] <= 'Z')) {
                    name2 += m[it];
                }
            }

            if (!name1.equals(name2)) {
                LinkedList<String> aux = Sheets(name1);
                Vector<String> aux2 = new Vector<String>();
                for (int j = 0; j < aux.size(); ++j) {
                    aux2.add(aux.get(j));
                }
                SaveInIndex(name1, aux2);
            }
        }
        deleteOldFile();

    }

    /**
     * Borra el fichero donde se encuentra toda la información de la hoja del documento "nameDoc"
     * @param nameDoc nombre del documento donde se encuentra la hoja
     * @param nameSheet nombre de la hoja que se quiere borrar
     */
    @Override
    public void deleteSheet(String nameDoc, String nameSheet) throws IOException {
        String name = nameDoc + nameSheet + ".csv";
        File f = new File(name);
        if (f.exists()) f.delete();
    }

    /**
     * Crea un fichero
     */
    @Override
    public void newFile() throws IOException {
        File f = new File("temporal");
        f.createNewFile();
    }

    /**
     * Elimina un fichero
     */
    public void deleteOldFile() {
        File f = new File("index.csv");
        File f1 = new File("temporal");

        f.delete();
        f1.renameTo(f);
    }
}

