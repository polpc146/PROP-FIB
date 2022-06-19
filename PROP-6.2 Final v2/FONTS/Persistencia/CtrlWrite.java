package Persistencia;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Vector;


/**
 * Almacena las funciones para escribir en los ficheros .csv
 * @author Sandra Buitrago Cervilla
 */
public class CtrlWrite {
    private static CtrlWrite inst;

    /**
     * Retorna la única instancia que té la classe, i si no en té cap la crea
     * @return la única instancia de la classe
     */
    public static CtrlWrite getInstance() {
        if(CtrlWrite.inst == null) inst = new CtrlWrite();
        return inst;
    }

    /**
     * Crea la instancia de la classe
     */
    private CtrlWrite() {}

    /**
     * Guarda el contenido de los datos en el fichero que se especifica
     * @param file nombre del fichero donde se guardará toda la información
     * @param table es la información que se guardará en el fichero
     */
    public void SaveInFile(File file, String[][] table) throws IOException
    {
        FileWriter f = new FileWriter(file);

        for (int i = 0; i < table.length; ++i)
        {
            for (int j = 0; j < table[i].length; ++j)
            {
                String content = table[i][j];
                f.write(content, 0, content.length());
                if (j != table[i].length-1) {
                    f.write("|", 0, 1);
                }
            }

            f.write("\n", 0, 1);
        }
        f.close();
    }

    /**
     * Guarda el nombre de los documentos con sus hojas respectivas
     * @param fileName fichero donde se guardaran los nombres
     * @param nameDoc nombre del documento
     * @param sheets nombres de las hojas del documento
     */
    public void SaveIndex(String fileName, String nameDoc, Vector<String> sheets) throws IOException {
        FileWriter fw = new FileWriter(fileName, true);
        fw.write(nameDoc, 0, nameDoc.length());

        for (int i = 0; i < sheets.size(); ++i) {
            fw.write(";", 0, 1);
            String nameSheet = sheets.get(i);
            fw.write(nameSheet, 0, nameSheet.length());
        }
        fw.write("\n", 0, 1);
        fw.close();
    }
}
