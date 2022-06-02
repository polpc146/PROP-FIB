package Presentation;

import java.util.*;

/**
 * Vista de acciones disponibles
 * @author Pol Pérez Castillo
 */
public class DocumentView {

    private inout io = new inout();
    private int numOptions = 0;

    /**
     * Menú de acciones disponibles
     * @throws Exception
     */
    private void showView() throws Exception {

        io.writeln("");
        ShowMessage('/', "Documents Menu");
        io.writeln("0 - Exit");
        io.writeln("1 - Get a list of the documents"); ++numOptions;
        io.writeln("2 - Create a document"); ++numOptions;
        io.writeln("3 - Remove a document"); ++numOptions;
        io.writeln("4 - Change the name of a document"); ++numOptions;
        io.writeln("5 - View or modify a document"); ++numOptions;
        io.writeln("");
        io.writeln("Select an option");
    }

    /**
     * Muestra mensaje por pantalla
     * @param c carácter para enfatizar el mensaje
     * @param message mensaje
     * @throws Exception
     */
    public void ShowMessage (char c, String message) throws Exception {

        int n;
        if (c == '*') {

            n = message.length() + 7;
            for (int i = 0; i < n; i++) io.write(c);
            io.writeln("");
            io.writeln("ERROR: " + message);
            for (int i = 0; i < n; i++) io.write(c);
        }
        else {

            n = message.length();
            for (int i = 0; i < n; i++) io.write(c);
            io.writeln("");
            io.writeln(message);
            for (int i = 0; i < n; i++) io.write(c);
        }
        io.writeln("");
    }

    /**
     * Consulta de opción de menú de acciones disponibles
     * @return opción seleccionada
     * @throws Exception
     */
    public int GetOption() throws Exception {

        int option = -1;
        while (option < 0 || option > numOptions) {

            showView();
            option = io.readint();
        }
        String avoidError = getDocumentName();
        io.writeln("");
        return option;
    }

    /**
     * Muestra lista de documentos
     * @param docList nombres de los documentos
     * @throws Exception
     */
    public void ShowDocumentList(Vector<String> docList) throws Exception{

        if (docList.isEmpty()) io.writeln("There is no document");
        else {

            for (String s : docList)  io.writeln(s);
        }
    }

    /**
     * Consulta de String al usuario
     * @return String
     * @throws Exception
     */
    public String getDocumentName() throws Exception {

        return io.readline();
    }
}
