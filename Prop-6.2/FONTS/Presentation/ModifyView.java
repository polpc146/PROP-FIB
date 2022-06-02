package Presentation;

import java.util.*;

/**
 * Vista de acciones en un documento
 * @author Pol Pérez Castillo
 */
public class ModifyView {

    private inout io = new inout();
    private int numOptions = 0;

    /**
     * Menú de acciones disponibles sobre un documento
     * @throws Exception
     */
    private void showView() throws Exception {

        io.writeln("");
        ShowMessage('/', "What to do Menu");
        io.writeln("0 - Return");
        io.writeln("1 - Add a sheet"); ++numOptions;
        io.writeln("2 - Change the name of a sheet"); ++numOptions;
        io.writeln("3 - Remove a sheet"); ++numOptions;
        io.writeln("4 - Get a list of the sheets"); ++numOptions;
        io.writeln("5 - Add a row or rows"); ++numOptions;
        io.writeln("6 - Add a column or columns"); ++numOptions;
        io.writeln("7 - Erase a row or rows"); ++numOptions;
        io.writeln("8 - Erase a column or columns"); ++numOptions;
        io.writeln("9 - Select a rank of cells"); ++numOptions;
        io.writeln("10 - Select a cell"); ++numOptions;
        io.writeln("11 - Do a operation between cells"); ++numOptions;
        io.writeln("12 - Print a sheet"); ++numOptions;
        io.writeln("");
        io.writeln("Select an option");
    }

    /**
     * Menú de acciones disponibles sobre un rango seleccionado
     * @throws Exception
     */
    private void showViewRank() throws Exception {

        io.writeln("");
        ShowMessage('/', "What to do Menu");
        io.writeln("0 - Return");
        io.writeln("1 - Sort the rank"); ++numOptions;
        io.writeln("2 - Cut and paste the rank"); ++numOptions;
        io.writeln("3 - Copy and paste the rank"); ++numOptions;
        io.writeln("4 - Do a rank operation"); ++numOptions;
        io.writeln("");
        io.writeln("Select an option");
    }

    /**
     * Menú de acciones disponibles sobre una celda seleccionada
     * @throws Exception
     */
    private void showViewCell() throws Exception {

        io.writeln("");
        ShowMessage('/', "What to do Menu");
        io.writeln("0 - Return");
        io.writeln("1 - Add a string/s"); ++numOptions;
        io.writeln("2 - Add a number"); ++numOptions;
        io.writeln("3 - Add a date"); ++numOptions;
        io.writeln("4 - Do a numeric operation"); ++numOptions;
        io.writeln("5 - Do a date operation"); ++numOptions;
        io.writeln("");
        io.writeln("Select an option");
    }

    /**
     * Menú de operaciones numéricas disponibles
     * @throws Exception
     */
    private void showViewOperationNumber() throws Exception {

        io.writeln("");
        ShowMessage('/', "Operation Number Menu");
        io.writeln("0 - Return");
        io.writeln("1 - Truncate"); ++numOptions;
        io.writeln("2 - Conversion"); ++numOptions;
        io.writeln("3 - Increase"); ++numOptions;
        io.writeln("4 - Absolut"); ++numOptions;
        io.writeln("");
        io.writeln("Select an option");
    }

    /**
     * Menú de operaciones de fecha disponibles
     * @throws Exception
     */
    private void showViewOperationDate() throws Exception {

        io.writeln("");
        ShowMessage('/', "Operation Date Menu");
        io.writeln("0 - Return");
        io.writeln("1 - Element extraction"); ++numOptions;
        io.writeln("2 - Day of the week"); ++numOptions;
        io.writeln("");
        io.writeln("Select an option");
    }

    /**
     * Menú de operaciones de texto disponibles
     * @throws Exception
     */
    private void showViewOperationText() throws Exception {

        io.writeln("");
        ShowMessage('/', "Operation Text Menu");
        io.writeln("0 - Return");
        io.writeln("1 - Replace a string"); ++numOptions;
        io.writeln("2 - Compute the size"); ++numOptions;
        io.writeln("3 - Search a string"); ++numOptions;
        io.writeln("");
        io.writeln("Select an option");
    }

    /**
     * Menú de operaciones de rango disponibles
     * @throws Exception
     */
    private void showViewOperationRank() throws Exception {

        io.writeln("");
        ShowMessage('/', "Statistical Operations Menu");
        io.writeln("0 - Return");
        io.writeln("1 - Average"); ++numOptions;
        io.writeln("2 - Median"); ++numOptions;
        io.writeln("3 - Variance"); ++numOptions;
        io.writeln("4 - Covariance"); ++numOptions;
        io.writeln("5 - Standard deviation"); ++numOptions;
        io.writeln("6 - Pearson's correlation coefficient"); ++numOptions;
        io.writeln("");
        io.writeln("Select an option");
    }

    /**
     * Menú de operaciones de celdas referenciadas disponibles
     * @throws Exception
     */
    private void showViewOpCells() throws Exception {

        io.writeln("");
        ShowMessage('/', "Operations between Cells Menu");
        io.writeln("0 - Return");
        io.writeln("1 - Add"); ++numOptions;
        io.writeln("2 - Subtract"); ++numOptions;
        io.writeln("3 - Multiply"); ++numOptions;
        io.writeln("4 - Divide"); ++numOptions;
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
     * Escribe por pantalla
     * @param message mensaje a escribir
     * @throws Exception
     */
    public void Write (String message) throws Exception {

        System.out.print(message);
    }

    /**
     * Escribe por pantalla y hace un salto de línea
     * @param message mensaje a escribir
     * @throws Exception
     */
    public void Writeln (String message) throws Exception {

        System.out.println(message);
    }

    /**
     * Consulta de opción de menú de acciones disponibles sobre un documento al usuario
     * @return opción seleccionada
     * @throws Exception
     */
    public int GetOption() throws Exception {

        int option = -1;
        while (option < 0 || option > numOptions) {

            numOptions = 0;
            showView();
            option = io.readint();
        }
        String avoidError = GetName();
        io.writeln("");
        return option;
    }

    /**
     * Consulta de String al usuario
     * @return String
     * @throws Exception
     */
    public String GetName() throws Exception {

        return io.readline();
    }

    /**
     * Consulta de int al usuario
     * @return int
     * @throws Exception
     */
    public int GetInt() throws Exception {

        return io.readint();
    }

    /**
     * Consulta de double al usuario
     * @return double
     * @throws Exception
     */
    public double GetDouble() throws Exception {

        return io.readdouble();
    }

    /**
     * Muestra lista de hojas de un documento
     * @param sheetList nombres de las hojas
     * @throws Exception
     */
    public void ShowSheetList(Vector<String> sheetList) throws Exception{

        if (sheetList.isEmpty()) io.writeln("There is no sheet in this document");
        else {

            for (String s : sheetList)  io.writeln(s);
        }
    }

    /**
     * Consulta de opción de menú de acciones disponibles sobre un rango seleccionado
     * @return opción seleccionada
     * @throws Exception
     */
    public int GetOptionRank() throws Exception {

        int option = -1;
        while (option < 0 || option > numOptions) {

            numOptions = 0;
            showViewRank();
            option = io.readint();
        }
        String avoidError = GetName();
        io.writeln("");
        return option;
    }

    /**
     * Consulta de opción de menú de acciones disponibles sobre una celda seleccionada
     * @return opción seleccionada
     * @throws Exception
     */
    public int GetOptionCell() throws Exception {

        int option = -1;
        while (option < 0 || option > numOptions) {

            numOptions = 0;
            showViewCell();
            option = io.readint();
        }
        String avoidError = GetName();
        io.writeln("");
        return option;
    }

    /**
     * Consulta de opción de menú de operaciones numéricas disponibles
     * @return opción seleccionada
     * @throws Exception
     */
    public int GetOptionOperationNumber() throws Exception {

        int option = -1;
        while (option < 0 || option > numOptions) {

            numOptions = 0;
            showViewOperationNumber();
            option = io.readint();
        }
        String avoidError = GetName();
        io.writeln("");
        return option;
    }

    /**
     * Consulta de opción de menú de operaciones de fecha disponibles
     * @return opción seleccionada
     * @throws Exception
     */
    public int GetOptionOperationDate() throws Exception {

        int option = -1;
        while (option < 0 || option > numOptions) {

            numOptions = 0;
            showViewOperationDate();
            option = io.readint();
        }
        String avoidError = GetName();
        io.writeln("");
        return option;
    }

    /**
     * Consulta de opción de menú de operaciones de texto disponibles
     * @return opción seleccionada
     * @throws Exception
     */
    public int GetOptionOperationText() throws Exception {

        int option = -1;
        while (option < 0 || option > numOptions) {

            numOptions = 0;
            showViewOperationText();
            option = io.readint();
        }
        String avoidError = GetName();
        io.writeln("");
        return option;
    }

    /**
     * Consulta de opción de menú de operaciones de rango disponibles
     * @return opción seleccionada
     * @throws Exception
     */
    public int GetOptionOperationRank() throws Exception {

        int option = -1;
        while (option < 0 || option > numOptions) {

            numOptions = 0;
            showViewOperationRank();
            option = io.readint();
        }
        String avoidError = GetName();
        io.writeln("");
        return option;
    }

    /**
     * Consulta de opción de menú de operaciones de celdas referenciadas disponibles
     * @return opción seleccionada
     * @throws Exception
     */
    public int GetOptionOpCells() throws Exception {

        int option = -1;
        while (option < 0 || option > numOptions) {

            numOptions = 0;
            showViewOpCells();
            option = io.readint();
        }
        String avoidError = GetName();
        io.writeln("");
        return option;
    }
}
