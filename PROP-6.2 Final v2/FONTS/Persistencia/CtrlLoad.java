package Persistencia;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;



/**
 * Almacena las funciones para leer los ficheros .csv y cargarlos
 * @author Sandra Buitrago Cervilla
 */
public class CtrlLoad {

    private static CtrlLoad inst;

    /**
     * Retorna la única instancia que tiene la clase y si no en tiene la crea
     * @return la única instancia de la classe
     */
    public static CtrlLoad getInstance() {
        if(CtrlLoad.inst == null) inst = new CtrlLoad();
        return inst;
    }

    /**
     * Crea la instancia de la classe
     */
    private CtrlLoad() {}


    /**
     * Carga el contenido del fichero
     * @param fileName nombre del fichero .csv que se quiere cargar
     * @return el contenido del fichero
     */
    public LinkedList<LinkedList<String>> Load(File fileName) throws IOException {
        FileReader f = new FileReader(fileName);

        LinkedList<LinkedList<String>> d = new LinkedList<LinkedList<String>>();
        char c;
        int n;
        String content = "";
        n = f.read();
        //Llegeix i retorna -1 si troba el final, si passa aquí significa que el fitxer està buit
        while (n != -1) {
            c = (char) n;
            d.add(new LinkedList<>());
            while (c != '\n' && n != -1){
                while (c != '|') {
                    content += c;
                    n = f.read();
                    c = (char)n;
                }
                d.get(d.size()-1).add(content);
                content = "";
                n = f.read();
                c = (char) n;
                if (c == '\n') d.get(d.size()-1).add(content);
            }

           n = f.read();

        }
        f.close();
        return d;
    }
}