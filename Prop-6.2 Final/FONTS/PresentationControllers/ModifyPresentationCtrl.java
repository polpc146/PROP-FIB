package PresentationControllers;

import DomainControllers.*;
import Structure.Cells.Cell;
import Views.DocumentView;
import Views.ErrorView;
import Views.SheetView;

import java.io.IOException;
import java.util.*;
/**
 * Controlador de modificación de la capa presentación
 * Contiene el controlador de modificación del dominio y el controlador de la vista de modificación
 * @author Sandra Buitrago Cervilla
 */
public class ModifyPresentationCtrl {
    private ModifyDomainCtrl ModController;
    private SheetView ViewSheet;
    private ErrorView errorView = new ErrorView();

    /**
     * Crea la instancia de la vista y del controlador de modificación de la capa presentación
     * y las inicializa
     * @param modController Controlador de modificación del dominio
     */
    public ModifyPresentationCtrl(ModifyDomainCtrl modController) throws Exception {

        ModController = modController;
    }

    /**
     * Envia al controlador de modificación de dominio que se va a crear una hoja
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * @throws Exception
     */
    public void CreateSheet() throws Exception {

        int ErrorCode = ModController.CreateSheet();
        switch (ErrorCode) {
            case 0: break;
            case -1: errorView.error("The Sheet hasn't been created."); break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a modificar el nombre de una hoja
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * @throws Exception
     */
    public void ChangeNameSheet(String NameSheet, String NewName) throws Exception {

        int ErrorCode = ModController.ChangeNameSheet(NameSheet,NewName);
        switch (ErrorCode) {
            case 0: break;
            case -1:errorView.error("There is no Sheet with that name."); break;
            case -2: errorView.error("There is a Sheet with the new name."); break;
            case -3: errorView.error("The name hasn't been changed."); break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a eliminar una hoja
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * @throws Exception
     */
    public void EraseSheet(String NameSheet) throws Exception {

        int ErrorCode = ModController.EraseSheet(NameSheet);
        switch (ErrorCode) {
            case 0: errorView.error("Sheet erased successfully"); break;
            case -1: errorView.error("The Sheet hasn't been erased"); break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a consultar la lista de hojas
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * @throws Exception
     */
    public Vector<String> getSheetList() throws Exception {

        Vector<String> res = ModController.getSheetList();
        return res;
    }

    /**
     * Envia al controlador de modificación de dominio que se van a añadir filas
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @throws Exception
     */
    public void AddRow(String nameSheet, int numRows, int indexRow) throws Exception {

        int ErrorCode = ModController.AddRow(nameSheet,numRows,indexRow);
        switch (ErrorCode) {
            case 0: break;
            case -1: errorView.error("The Sheet with the name NameSheet doesn't exist"); break;
            case -2: errorView.error("There is no Row with that IndexRow."); break;
            case -3: errorView.error("The Row/s hasn't been added."); break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }

    }

    /**
     * Envia al controlador de modificación de dominio que se van a añadir columnas
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @throws Exception
     */
    public void AddColumn(String nameSheet, int numColumns, int indexColumn) throws Exception {

        int ErrorCode = ModController.AddColumn(nameSheet,numColumns,indexColumn);
        switch (ErrorCode) {
            case 0: break;
            case -1: errorView.error("The Sheet with the name NameSheet doesn't exist"); break;
            case -2: errorView.error("There is no Column with that IndexColumn."); break;
            case -3: errorView.error("The Column/s hasn't been added."); break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se van a eliminar filas
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @throws Exception
     */
    public void EraseRow(String nameSheet, int numRows, int indexRow) throws Exception {

        int ErrorCode = ModController.EraseRow(nameSheet,numRows,indexRow);
        switch (ErrorCode) {
            case 0: break;
            case -1: errorView.error("The Sheet with the name NameSheet doesn't exist"); break;
            case -2: errorView.error("There is no Row with that IndexRow."); break;
            case -3: errorView.error("The Row/s hasn't been erased."); break;
            case -4: errorView.error("You are trying to erase too many rows"); break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se van a eliminar columnas
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @throws Exception
     */
    public void EraseColumn(String nameSheet, int numColumns, int indexColumn) throws Exception {

        int ErrorCode = ModController.EraseColumn(nameSheet,numColumns,indexColumn);
        switch (ErrorCode) {
            case 0:  break;
            case -1: errorView.error("The Sheet with the name NameSheet doesn't exist"); break;
            case -2: errorView.error("There is no Column with that IndexRow."); break;
            case -3: errorView.error("The Column/s hasn't been erased."); break;
            case -4: errorView.error("You are trying to erase too many columns"); break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a seleccionar un rango
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario.
     * El usuario puede escoger que hacer con el rango (ordenar el rango, moverlo, copiarlo o hacer una operación)
     * @throws Exception
     */
    private void RankSelection(String NameSheet, int RowCellIni, int ColumnCellIni, int RowCellFin, int ColumnCellFin, int numRank) throws Exception {

        int ErrorCode = ModController.SelectRank(NameSheet, RowCellIni, ColumnCellIni, RowCellFin, ColumnCellFin, 1);
        switch (ErrorCode) {
            case 0: break;
            case -1: errorView.error("The sheet with the name NameSheet doesn't exist"); break;
            case -2: errorView.error("The initial cell doesn't exist."); break;
            case -3: errorView.error("The rank can't be selected."); break;
            case -4: errorView.error("The final cell doesn't exist."); break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Método que dependiendo de la opción escogida por el usuario llama a funciones de operaciones de rango
     * (media, mediana, varianza, covarianza, desviación estandard, coeficiente de correlación de Pearson)
     * @param NameSheet nombre de la hoja
     * @throws  Exception
     */

    private void OperationRank(String NameSheet) throws Exception {
        /*errorView.error('-',"Statistical Operations:");
        errorView.error('-',"Tell me what operation you want to do:");
        int option = -1;
        while (option != 0) {
            option = ModView.GetOptionOperationRank();
            switch (option) {
                case 1:  Average(NameSheet); break;
                case 2:  Median(NameSheet); break;
                case 3:  Variance(NameSheet); break;
                case 4:  Covariance(NameSheet); break;
                case 5:  StandardDeviation(NameSheet); break;
                case 6:  Pearson(NameSheet); break;
                default: break;

            }
        }*/
    }

    /**
     * Envia al controlador de modificación de dominio que se va a realizar una media
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @throws Exception
     */
    public void average(String NameSheet, int RowCell, int ColumnCell, int rowI, int colI, int rowF, int colF) throws  Exception {

        if (ModController.SelectRank(NameSheet, rowI, colI, rowF, colF, 1) != 0) return;
        int ErrorCode = ModController.Average(NameSheet,RowCell,ColumnCell);
        switch (ErrorCode) {
            case 0: break;
            case -1: errorView.error("The Average hasn't been done due to an error"); break;
            case -2: errorView.error("The cell doesn't exist"); break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a realizar una mediana
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @throws Exception
     */
    public void median(String NameSheet, int RowCell, int ColumnCell, int rowI, int colI, int rowF, int colF) throws  Exception {

        if (ModController.SelectRank(NameSheet, rowI, colI, rowF, colF, 1) != 0) return;
        int ErrorCode = ModController.Median(NameSheet,RowCell,ColumnCell);
        switch (ErrorCode) {
            case 0: break;
            case -1: errorView.error("The Median hasn't been done due to an error"); break;
            case -2: errorView.error("The cell doesn't exist"); break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a realizar una varianza
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @throws Exception
     */
    public void variance(String NameSheet, int RowCell, int ColumnCell, int rowI, int colI, int rowF, int colF) throws  Exception {

        if (ModController.SelectRank(NameSheet, rowI, colI, rowF, colF, 1) != 0) return;
        int ErrorCode = ModController.Variance(NameSheet,RowCell,ColumnCell);
        switch (ErrorCode) {
            case 0: break;
            case -1: errorView.error("The Variance hasn't been done due to an error"); break;
            case -2: errorView.error("The cell doesn't exist"); break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a realizar la covarianza
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @throws Exception
     */
    public void covariance(String NameSheet, int RowCell, int ColumnCell, int rowI, int colI, int rowF, int colF, int rowI2, int colI2, int rowF2, int colF2) throws  Exception {

        if (ModController.SelectRank(NameSheet, rowI, colI, rowF, colF, 1) != 0) return;
        int ErrorCode = ModController.Covariance(NameSheet,RowCell,ColumnCell, rowI2, colI2, rowF2, colF2);
        switch (ErrorCode) {
            case 0: break;
            case -1: errorView.error("The Covariance hasn't been done due to an error"); break;
            case -2: errorView.error("The cell doesn't exist"); break;
            case -3: errorView.error("The second rank doesn't exist"); break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a realizar una desviación estandard
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @throws Exception
     */
    public void standardDeviation(String NameSheet, int RowCell, int ColumnCell, int rowI, int colI, int rowF, int colF) throws  Exception {

        if (ModController.SelectRank(NameSheet, rowI, colI, rowF, colF, 1) != 0) return;
        int ErrorCode = ModController.StandardDeviation(NameSheet,RowCell,ColumnCell);
        switch (ErrorCode) {
            case 0: break;
            case -1: errorView.error("The Standard Deviation hasn't been done due to an error"); break;
            case -2: errorView.error("The cell doesn't exist"); break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a realizar un coeficiente de correlación de Pearson
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @throws Exception
     */
    public void pearson(String NameSheet, int RowCell, int ColumnCell, int rowI, int colI, int rowF, int colF, int rowI2, int colI2, int rowF2, int colF2) throws  Exception {

        if (ModController.SelectRank(NameSheet, rowI, colI, rowF, colF, 1) != 0) return;
        int ErrorCode = ModController.Pearson(NameSheet,RowCell,ColumnCell, rowI2, colI2, rowF2, colF2);
        switch (ErrorCode) {
            case 0: break;
            case -1: errorView.error("The Pearson's correlation coefficient hasn't been done due to an error"); break;
            case -2: errorView.error("The cell doesn't exist"); break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a realizar una ordenación en el rango
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @throws Exception
     */
    private void sortRank(String NameSheet) throws Exception {
        /*errorView.error('-',"Sort the Rank:");

        String Criterium = "";
        do {
            errorView.error('-',"Tell me how do you want to sort the rank (Ascendant [A], Descendant [D],):");
            Criterium = ModView.GetName();
        }
        while (!Criterium.equalsIgnoreCase("Ascendant") && !Criterium.equalsIgnoreCase("Descendant") && !Criterium.equalsIgnoreCase("A") && !Criterium.equalsIgnoreCase("D"));

        int ErrorCode = ModController.SortRank(NameSheet, Criterium);
        switch (ErrorCode) {
            case 0: errorView.error('-', "Rank sorted successfully"); break;
            case -1: errorView.error('*', "The Rank hasn't been sorted due to an error"); break;
            default: errorView.error('*', "Unknown Error " + ErrorCode); break;
        }*/
    }

    /**
     * Envia al controlador de modificación de dominio que se va a mover un rango
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @throws Exception
     */
    private void MoveRank(String NameSheet) throws Exception {
        /*errorView.error('-',"Move a Rank:");
        errorView.error('-',"Tell me the row and the column of the initial cell you want to move the rank:");
        int RowCellIni = ModView.GetInt();
        int ColumnCellIni = ModView.GetInt();
        int ErrorCode = ModController.MoveRank(NameSheet,RowCellIni,ColumnCellIni);
        switch (ErrorCode) {
            case 0: errorView.error('-', "Rank moved successfully"); break;
            case -1: errorView.error('*', "The initial Cell doesn't exist"); break;
            case -2: errorView.error('*', "The Rank is very big"); break;
            case -3: errorView.error('*', "The Rank hasn't been moved due to an error"); break;
            default: errorView.error('*', "Unknown Error " + ErrorCode); break;
        }*/
    }

    /**
     * Envia al controlador de modificación de dominio que se va a copiar un rango
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @throws Exception
     */
    private void CopyRank(String NameSheet) throws Exception {
        /*errorView.error('-',"Copy a Rank:");
        errorView.error('-',"Tell me the row and the column of the initial cell you want to copy the rank:");
        int RowCellIni = ModView.GetInt();
        int ColumnCellIni = ModView.GetInt();
        int ErrorCode = ModController.CopyRank(NameSheet,RowCellIni,ColumnCellIni);
        switch (ErrorCode) {
            case 0: errorView.error('-', "Rank copied successfully"); break;
            case -1: errorView.error('*', "The initial Cell doesn't exist"); break;
            case -2: errorView.error('*', "The Rank is very big"); break;
            case -3: errorView.error('*', "The Rank hasn't been moved due to an error"); break;
            default: errorView.error('*', "Unknown Error " + ErrorCode); break;
        }*/
    }

    public void ModCell (String NameSheet, int RowCell, int ColumnCell, String data) throws Exception {

        int type = ModController.checkType(data);
        if (type == 0) {

            int size = data.length();
            int i = 0;
            if (data.charAt(i) == '-') ++i;
            if (data.charAt(i) == '.') {

                if (i == 1) data = "-0" + data.substring(1,size);
                else data = "0" + data;
            }
            else if (data.charAt(size-1) == ',') data += "0";
            double dob = Double.parseDouble(data);
            ModDoubleCell(NameSheet, RowCell, ColumnCell, dob);
        }
        else if (type == 1) {

            ModStringCell(NameSheet, RowCell, ColumnCell, data);
        }
        else if (type == 2) {

            char num1 = data.charAt(6);
            char num2 = data.charAt(7);
            char num3 = data.charAt(8);
            char num4 = data.charAt(9);
            String numS = String.valueOf(num1+num2+num3+num4);
            int year = Integer.parseInt(numS);
            num1 = data.charAt(0);
            num2 = data.charAt(1);
            numS = String.valueOf(num1+num2);
            int day = Integer.parseInt(numS);
            num1 = data.charAt(3);
            num2 = data.charAt(4);
            numS = String.valueOf(num1+num2);
            int month = Integer.parseInt(numS);
            ModDateCell(NameSheet, RowCell, ColumnCell, day, month, year);
        }
        else if (type == 3) {

            int size = data.length();
            if (size < 10) {

                ModStringCell(NameSheet, RowCell, ColumnCell, data);
                return;
            }
            StringBuilder colIni = null;
            StringBuilder rowIni = null;
            String formula = data.substring(1, 5);
            if (formula.equalsIgnoreCase("size")) {

                if (data.charAt(5) == '(') {

                    int i = 6;
                    while (i < size && data.charAt(i) >= 'a' && data.charAt(i) >= 'A' && data.charAt(i) <= 'z' && data.charAt(i) <= 'Z') {

                        colIni.append(data.charAt(i));
                        ++i;
                    }
                    if (i < size && data.charAt(i) == ',') {

                        ++i;
                        while (i < size && data.charAt(i) >= '0' && data.charAt(i) <= '9') {

                            rowIni.append(data.charAt(i));
                            ++i;
                        }
                        if (i == size-1 && data.charAt(i) == ')') {

                            int colI = colToInt(colIni.toString());
                            ModController.Size(NameSheet, Integer.parseInt(rowIni.toString()), colI);
                            return;
                        }
                    }
                }
            }
            if (size < 14) {

                ModStringCell(NameSheet, RowCell, ColumnCell, data);
                return;
            }
            StringBuilder colFin = null;
            StringBuilder rowFin = null;
            StringBuilder colIni2 = null;
            StringBuilder rowIni2 = null;
            StringBuilder colFin2 = null;
            StringBuilder rowFin2 = null;
            formula = data.substring(1, 8);
            if (formula.equalsIgnoreCase("average")) {

                if (data.charAt(8) == '(') {

                    int i = 9;
                    while (i < size && data.charAt(i) >= 'a' && data.charAt(i) >= 'A' && data.charAt(i) <= 'z' && data.charAt(i) <= 'Z') {

                        colIni.append(data.charAt(i));
                        ++i;
                    }
                    if (i < size && data.charAt(i) == ',') {

                        ++i;
                        while (i < size && data.charAt(i) >= '0' && data.charAt(i) <= '9') {

                            rowIni.append(data.charAt(i));
                            ++i;
                        }
                        if (i < size && data.charAt(i) == ':') {

                            ++i;
                            while (i < size && data.charAt(i) >= 'a' && data.charAt(i) >= 'A' && data.charAt(i) <= 'z' && data.charAt(i) <= 'Z') {

                                colFin.append(data.charAt(i));
                                ++i;
                            }
                            if (i < size && data.charAt(i) == ',') {

                                ++i;
                                while (i < size && data.charAt(i) >= '0' && data.charAt(i) <= '9') {

                                    rowFin.append(data.charAt(i));
                                    ++i;
                                }
                                if (i == size-1 && data.charAt(i) == ')') {

                                    int colI = colToInt(colIni.toString());
                                    int colF = colToInt(colFin.toString());
                                    RankSelection(NameSheet, Integer.parseInt(rowIni.toString()), colI, Integer.parseInt(rowFin.toString()), colF, 1);
                                    ModController.Average(NameSheet, RowCell, ColumnCell);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
            if (formula.equalsIgnoreCase("pearson")) {

                if (data.charAt(8) == '(' && data.charAt(9) == '(') {

                    int i = 10;
                    while (i < size && data.charAt(i) >= 'a' && data.charAt(i) >= 'A' && data.charAt(i) <= 'z' && data.charAt(i) <= 'Z') {

                        colIni.append(data.charAt(i));
                        ++i;
                    }
                    if (i < size && data.charAt(i) == ',') {

                        ++i;
                        while (i < size && data.charAt(i) >= '0' && data.charAt(i) <= '9') {

                            rowIni.append(data.charAt(i));
                            ++i;
                        }
                        if (i < size && data.charAt(i) == ':') {

                            ++i;
                            while (i < size && data.charAt(i) >= 'a' && data.charAt(i) >= 'A' && data.charAt(i) <= 'z' && data.charAt(i) <= 'Z') {

                                colFin.append(data.charAt(i));
                                ++i;
                            }
                            if (i < size && data.charAt(i) == ',') {

                                ++i;
                                while (i < size && data.charAt(i) >= '0' && data.charAt(i) <= '9') {

                                    rowFin.append(data.charAt(i));
                                    ++i;
                                }
                                if (i+2 < size && data.charAt(i) == ')' && data.charAt(i+1) == ',' && data.charAt(i+2) == '(') {

                                    i+=3;
                                    while (i < size && data.charAt(i) >= 'a' && data.charAt(i) >= 'A' && data.charAt(i) <= 'z' && data.charAt(i) <= 'Z') {

                                        colIni2.append(data.charAt(i));
                                        ++i;
                                    }
                                    if (i < size && data.charAt(i) == ',') {

                                        ++i;
                                        while (i < size && data.charAt(i) >= '0' && data.charAt(i) <= '9') {

                                            rowIni2.append(data.charAt(i));
                                            ++i;
                                        }
                                        if (i < size && data.charAt(i) == ':') {

                                            ++i;
                                            while (i < size && data.charAt(i) >= 'a' && data.charAt(i) >= 'A' && data.charAt(i) <= 'z' && data.charAt(i) <= 'Z') {

                                                colFin2.append(data.charAt(i));
                                                ++i;
                                            }
                                            if (i < size && data.charAt(i) == ',') {

                                                ++i;
                                                while (i < size && data.charAt(i) >= '0' && data.charAt(i) <= '9') {

                                                    rowFin2.append(data.charAt(i));
                                                    ++i;
                                                }
                                                if (i == size-2 && data.charAt(i) == ')' && data.charAt(i+1) == ')') {

                                                    int colI = colToInt(colIni.toString());
                                                    int colF = colToInt(colFin.toString());
                                                    int colI2 = colToInt(colIni2.toString());
                                                    int colF2 = colToInt(colFin2.toString());
                                                    RankSelection(NameSheet, Integer.parseInt(rowIni.toString()), colI, Integer.parseInt(rowFin.toString()), colF, 1);
                                                    ModController.Pearson(NameSheet, RowCell, ColumnCell, Integer.parseInt(rowIni2.toString()), colI2, Integer.parseInt(rowFin2.toString()), colF2);
                                                    return;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            formula = data.substring(1, 7);
            if (formula.equalsIgnoreCase("median")) {

                if (data.charAt(7) == '(') {

                    int i = 8;
                    while (i < size && data.charAt(i) >= 'a' && data.charAt(i) >= 'A' && data.charAt(i) <= 'z' && data.charAt(i) <= 'Z') {

                        colIni.append(data.charAt(i));
                        ++i;
                    }
                    if (i < size && data.charAt(i) == ',') {

                        ++i;
                        while (i < size && data.charAt(i) >= '0' && data.charAt(i) <= '9') {

                            rowIni.append(data.charAt(i));
                            ++i;
                        }
                        if (i < size && data.charAt(i) == ':') {

                            ++i;
                            while (i < size && data.charAt(i) >= 'a' && data.charAt(i) >= 'A' && data.charAt(i) <= 'z' && data.charAt(i) <= 'Z') {

                                colFin.append(data.charAt(i));
                                ++i;
                            }
                            if (i < size && data.charAt(i) == ',') {

                                ++i;
                                while (i < size && data.charAt(i) >= '0' && data.charAt(i) <= '9') {

                                    rowFin.append(data.charAt(i));
                                    ++i;
                                }
                                if (i == size-1 && data.charAt(i) == ')') {

                                    int colI = colToInt(colIni.toString());
                                    int colF = colToInt(colFin.toString());
                                    RankSelection(NameSheet, Integer.parseInt(rowIni.toString()), colI, Integer.parseInt(rowFin.toString()), colF, 1);
                                    ModController.Median(NameSheet, RowCell, ColumnCell);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
            formula = data.substring(1, 9);
            if (formula.equalsIgnoreCase("variance")) {

                if (data.charAt(9) == '(') {

                    int i = 10;
                    while (i < size && data.charAt(i) >= 'a' && data.charAt(i) >= 'A' && data.charAt(i) <= 'z' && data.charAt(i) <= 'Z') {

                        colIni.append(data.charAt(i));
                        ++i;
                    }
                    if (i < size && data.charAt(i) == ',') {

                        ++i;
                        while (i < size && data.charAt(i) >= '0' && data.charAt(i) <= '9') {

                            rowIni.append(data.charAt(i));
                            ++i;
                        }
                        if (i < size && data.charAt(i) == ':') {

                            ++i;
                            while (i < size && data.charAt(i) >= 'a' && data.charAt(i) >= 'A' && data.charAt(i) <= 'z' && data.charAt(i) <= 'Z') {

                                colFin.append(data.charAt(i));
                                ++i;
                            }
                            if (i < size && data.charAt(i) == ',') {

                                ++i;
                                while (i < size && data.charAt(i) >= '0' && data.charAt(i) <= '9') {

                                    rowFin.append(data.charAt(i));
                                    ++i;
                                }
                                if (i == size-1 && data.charAt(i) == ')') {

                                    int colI = colToInt(colIni.toString());
                                    int colF = colToInt(colFin.toString());
                                    RankSelection(NameSheet, Integer.parseInt(rowIni.toString()), colI, Integer.parseInt(rowFin.toString()), colF, 1);
                                    ModController.Variance(NameSheet, RowCell, ColumnCell);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
            formula = data.substring(1, 11);
            if (formula.equalsIgnoreCase("covariance")) {

                if (data.charAt(11) == '(' && data.charAt(12) == '(') {

                    int i = 13;
                    while (i < size && data.charAt(i) >= 'a' && data.charAt(i) >= 'A' && data.charAt(i) <= 'z' && data.charAt(i) <= 'Z') {

                        colIni.append(data.charAt(i));
                        ++i;
                    }
                    if (i < size && data.charAt(i) == ',') {

                        ++i;
                        while (i < size && data.charAt(i) >= '0' && data.charAt(i) <= '9') {

                            rowIni.append(data.charAt(i));
                            ++i;
                        }
                        if (i < size && data.charAt(i) == ':') {

                            ++i;
                            while (i < size && data.charAt(i) >= 'a' && data.charAt(i) >= 'A' && data.charAt(i) <= 'z' && data.charAt(i) <= 'Z') {

                                colFin.append(data.charAt(i));
                                ++i;
                            }
                            if (i < size && data.charAt(i) == ',') {

                                ++i;
                                while (i < size && data.charAt(i) >= '0' && data.charAt(i) <= '9') {

                                    rowFin.append(data.charAt(i));
                                    ++i;
                                }
                                if (i+2 < size && data.charAt(i) == ')' && data.charAt(i+1) == ',' && data.charAt(i+2) == '(') {

                                    i+=3;
                                    while (i < size && data.charAt(i) >= 'a' && data.charAt(i) >= 'A' && data.charAt(i) <= 'z' && data.charAt(i) <= 'Z') {

                                        colIni2.append(data.charAt(i));
                                        ++i;
                                    }
                                    if (i < size && data.charAt(i) == ',') {

                                        ++i;
                                        while (i < size && data.charAt(i) >= '0' && data.charAt(i) <= '9') {

                                            rowIni2.append(data.charAt(i));
                                            ++i;
                                        }
                                        if (i < size && data.charAt(i) == ':') {

                                            ++i;
                                            while (i < size && data.charAt(i) >= 'a' && data.charAt(i) >= 'A' && data.charAt(i) <= 'z' && data.charAt(i) <= 'Z') {

                                                colFin2.append(data.charAt(i));
                                                ++i;
                                            }
                                            if (i < size && data.charAt(i) == ',') {

                                                ++i;
                                                while (i < size && data.charAt(i) >= '0' && data.charAt(i) <= '9') {

                                                    rowFin2.append(data.charAt(i));
                                                    ++i;
                                                }
                                                if (i == size-2 && data.charAt(i) == ')' && data.charAt(i+1) == ')') {

                                                    int colI = colToInt(colIni.toString());
                                                    int colF = colToInt(colFin.toString());
                                                    int colI2 = colToInt(colIni2.toString());
                                                    int colF2 = colToInt(colFin2.toString());
                                                    RankSelection(NameSheet, Integer.parseInt(rowIni.toString()), colI, Integer.parseInt(rowFin.toString()), colF, 1);
                                                    ModController.Covariance(NameSheet, RowCell, ColumnCell, Integer.parseInt(rowIni2.toString()), colI2, Integer.parseInt(rowFin2.toString()), colF2);
                                                    return;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (size < 18) {

                ModStringCell(NameSheet, RowCell, ColumnCell, data);
                return;
            }
            formula = data.substring(1, 18);
            if (formula.equalsIgnoreCase("standarddeviation")) {

                if (data.charAt(18) == '(') {

                    int i = 19;
                    while (i < size && data.charAt(i) >= 'a' && data.charAt(i) >= 'A' && data.charAt(i) <= 'z' && data.charAt(i) <= 'Z') {

                        colIni.append(data.charAt(i));
                        ++i;
                    }
                    if (i < size && data.charAt(i) == ',') {

                        ++i;
                        while (i < size && data.charAt(i) >= '0' && data.charAt(i) <= '9') {

                            rowIni.append(data.charAt(i));
                            ++i;
                        }
                        if (i < size && data.charAt(i) == ':') {

                            ++i;
                            while (i < size && data.charAt(i) >= 'a' && data.charAt(i) >= 'A' && data.charAt(i) <= 'z' && data.charAt(i) <= 'Z') {

                                colFin.append(data.charAt(i));
                                ++i;
                            }
                            if (i < size && data.charAt(i) == ',') {

                                ++i;
                                while (i < size && data.charAt(i) >= '0' && data.charAt(i) <= '9') {

                                    rowFin.append(data.charAt(i));
                                    ++i;
                                }
                                if (i == size-1 && data.charAt(i) == ')') {

                                    int colI = colToInt(colIni.toString());
                                    int colF = colToInt(colFin.toString());
                                    RankSelection(NameSheet, Integer.parseInt(rowIni.toString()), colI, Integer.parseInt(rowFin.toString()), colF, 1);
                                    ModController.StandardDeviation(NameSheet, RowCell, ColumnCell);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
            ModStringCell(NameSheet, RowCell, ColumnCell, data);
        }
    }

    private int colToInt(String col) {

        int size = col.length();
        col = col.toLowerCase();
        int num = 0;
        int c;
        for (int i = 0; i < size; i++) {

            c = col.charAt(i) - 97;
            num += c*Math.pow(10,i);
        }
        return num;
    }

    /**
     * Envia al controlador de modificación de dominio que se va a realizar una modificación a una celda
     * añadiendo una string y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @param RowCell fila de la celda donde se hará la modificación
     * @param ColumnCell columna de la celda donde se hará la modificación
     * @throws Exception
     */
    private void ModStringCell(String NameSheet, int RowCell, int ColumnCell, String Content) throws Exception {

        int ErrorCode = ModController.ModifyString(NameSheet,RowCell,ColumnCell,Content);
        switch (ErrorCode) {
            case 0: break;
            case -1: errorView.error("The cell has not been modified due to an error"); break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a realizar una modificación a una celda
     * añadiendo un double y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @param RowCell fila de la celda donde se hará la modificación
     * @param ColumnCell columna de la celda donde se hará la modificación
     * @throws Exception
     */
    private void ModDoubleCell(String NameSheet, int RowCell, int ColumnCell, Double Content) throws Exception {

        int ErrorCode = ModController.ModifyDouble(NameSheet,RowCell,ColumnCell,Content);
        switch (ErrorCode) {
            case 0: break;
            case -1: errorView.error("The cell has not been modified due to an error"); break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a realizar una modificación a una celda
     * añadiendo una fecha y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @param RowCell fila de la celda donde se hará la modificación
     * @param ColumnCell columna de la celda donde se hará la modificación
     * @throws Exception
     */
    private void ModDateCell(String NameSheet, int RowCell, int ColumnCell, int Day, int Month, int Year) throws Exception {

        int ErrorCode = ModController.ModifyDate(NameSheet,RowCell,ColumnCell, Day, Month, Year);
        switch (ErrorCode) {
            case 0: break;
            case -1: errorView.error("The cell has not been modified due to an error"); break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a realizar una truncación a una celda,
     * el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @param row fila de la celda donde se hará la operación
     * @param col columna de la celda donde se hará la operación
     * @throws Exception
     */
    public void truncate(String NameSheet, int row, int col) throws Exception {

        int ErrorCode = ModController.Truncate(NameSheet,row,col,false, false);
        switch (ErrorCode) {
            case 0:  break;
            case -1: break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a realizar una incrementación de una celda,
     * el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param nameSheet nombre de la hoja en la que se hará la operación
     * @param row fila de la celda donde se hará la operación
     * @param col columna de la celda donde se hará la operación
     * @throws Exception
     */
    public void increase(String nameSheet, int row, int col) {

        int ErrorCode = ModController.Increase(nameSheet,row,col,false, false);
        switch (ErrorCode) {
            case 0:  break;
            case -1: break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a poner el valor absoluto de una celda,
     * el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @param Row fila de la celda donde se hará la operación
     * @param Col columna de la celda donde se hará la operación
     */
    public void absolute(String NameSheet, int Row, int Col){

        int ErrorCode = ModController.Absolut(NameSheet,Row,Col,false, false);
        switch (ErrorCode) {
            case 0: break;
            case -1: break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a realizar una conversión a una celda,
     * el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param nameSheet nombre de la hoja en la que se hará la operación
     * @param row fila de la celda donde se hará la operación
     * @param col columna de la celda donde se hará la operación
     */
    public void conversion(String nameSheet, int row, int col, String criterium){

        int ErrorCode = ModController.Conversion(nameSheet, row, col, false, false, criterium);
        switch (ErrorCode) {
            case 0: break;
            case -1: break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }
    }
/*
    /**
     * Método que dependiendo de la opción escogida por el usuario llama a funciones de operaciones de números en una celda
     * (truncación, conversión)
     * @param NameSheet nombre de la hoja
     * @param Row fila de la celda en la que se va a hacer la operación
     * @param Col columna de la celda en la que se va a hacer la operación
     * @throws  Exception
     */
   /* private void OperationNumber(String NameSheet, int Row,  int Col) throws Exception {
        errorView.error('-',"Numeric operations of a cell:");
        errorView.error('-',"Tell me what operation you want to do:");
        int option = -1;
        while (option != 0) {
            option = ModView.GetOptionOperationNumber();
            switch (option) {
                case 1:  Truncate(NameSheet, Row, Col); break;
                case 2:  Conversion(NameSheet, Row, Col); break;
                case 3:  Increase(NameSheet, Row, Col); break;
                case 4:  Absolut(NameSheet, Row, Col); break;
                default: break;

            }
        }
    }*/

    /**
     * Envia al controlador de modificación de dominio que se va a realizar una extracción de elementos de la fecha en una celda,
     * el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param nameSheet nombre de la hoja en la que se hará la operación
     * @param row fila de la celda donde se hará la operación
     * @param col columna de la celda donde se hará la operación
     * @throws Exception
     */
    public void elementExtraction(String nameSheet, int row, int col, String criterium) throws Exception {

        int ErrorCode = ModController.ExtractElement(nameSheet,row,col, false, false, criterium);
        switch (ErrorCode) {
            case 0: break;
            case -1: break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a solicitar el dia de la semana de la fecha de la celda,
     * el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param nameSheet nombre de la hoja en la que se hará la operación
     * @param row fila de la celda donde se hará la modificación
     * @param col columna de la celda donde se hará la modificación
     * @throws Exception
     */
    public void dayOfTheWeek(String nameSheet, int row, int col) throws Exception {

        int ErrorCode = ModController.DayOfTheWeek(nameSheet,row,col,false, false);
        switch (ErrorCode) {
            case 0: break;
            case -1: break;
            default: errorView.error( "Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Método que dependiendo de la opción escogida por el usuario llama a funciones de operaciones de fechas en una celda
     * (Extracción de elementos, Dia de la semana)
     * @param NameSheet nombre de la hoja
     * @param Row fila de la celda en la que se va a hacer la operación
     * @param Col columna de la celda en la que se va a hacer la operación
     * @throws  Exception
     */
    private void OperationDate(String NameSheet, int Row,  int Col) throws Exception {
        /*errorView.error('-',"Numeric operations of a cell:");
        errorView.error('-',"Tell me what operation you want to do:");
        int option = -1;
        while (option != 0) {
            option = ModView.GetOptionOperationDate();
            switch (option) {
                case 1:  ElementExtraction(NameSheet, Row, Col); break;
                case 2:  DayOfTheWeek(NameSheet, Row, Col); break;
                default: break;

            }
        }*/
    }

    /**
     * Envia al controlador de modificación de dominio que se va a reemplazar el contenido de las celdas
     * con un contenido determinado por otro,
     * el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @throws Exception
     */
    public void Replace(String NameSheet, String ElementToReplace, String ElementReplaced) throws Exception {

        int ErrorCode = ModController.Replace(NameSheet, ElementToReplace, ElementReplaced);

        switch (ErrorCode) {
            case 0: break;
            case -1: errorView.error("The replacement has not been modified due to an error"); break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que va a buscar celdas con un contenido específico
     * el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @throws Exception
     */
    private void Search(String NameSheet) throws Exception {
        /*errorView.error('-',"Search of a String:");
        errorView.error('-',"Tell me the element you want to search: ");
        String SearchS = ModView.GetName();

        int ErrorCode = ModController.Search(NameSheet, SearchS);

        switch (ErrorCode) {
            case 0: errorView.error('-', "The replacement has been modified successfully"); break;
            case -1: errorView.error('*', "The replacement has not been modified due to an error"); break;
            default: errorView.error('*', "Unknown Error " + ErrorCode); break;
        }*/
    }

    /**
     * Envia al controlador de modificación de dominio que se va a consultar la longitud del texto de una celda,
     * el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param nameSheet nombre de la hoja en la que se hará la operación
     * @throws Exception
     */
  public void size(String nameSheet, int row, int col) throws Exception {

      int ErrorCode = ModController.Size(nameSheet,row, col);
      switch (ErrorCode) {
          case 0:  break;
          case -1: break;
          default: errorView.error("Unknown Error " + ErrorCode); break;
      }
  }

    /**
     * Método que dependiendo de la opción escogida por el usuario llama a funciones de operaciones de texto en una celda
     * (reemplazar, longitud del contenido de la celda)
     * @param NameSheet nombre de la hoja
     * @throws  Exception
     */
    private void OperationText(String NameSheet) throws Exception {
        /*errorView.error('-',"Text operations of a cell:");
        errorView.error('-',"Tell me what operation you want to do:");
        int option = -1;
        while (option != 0) {
            option = ModView.GetOptionOperationText();
            switch (option) {
                case 1:  Replace(NameSheet); break;
                case 2:  Size(NameSheet); break;
                case 3:  Search(NameSheet); break;
                default: break;

            }
        }*/
    }

    /**
     * Envia al controlador de modificación de dominio que se va a seleccionar una celda,
     * el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario.
     * El usuario puede escoger que hacer con la celda (Modificar celdas, operaciones de datos, fechas, texto)
     * @throws Exception
     */
    private void CellSelection() throws Exception {
        /*errorView.error('-',"Select the Cell:");
        errorView.error('-',"Tell me the name of the Sheet you want to select:");
        String NameSheet = ModView.GetName();
        errorView.error('-',"Tell me the row and the column of the cell you want to select:");
        int RowCell = ModView.GetInt();
        int ColumnCell = ModView.GetInt();

        int ErrorCode = ModController.ExistsCell(NameSheet, RowCell, ColumnCell);

        switch (ErrorCode) {
            case 0: errorView.error('-', "Cell selected successfully"); break;
            case -1: errorView.error('*', "The sheet with the name NameSheet doesn't exist"); break;
            case -2: errorView.error('*', "The cell doesn't exist."); break;
            default: errorView.error('*', "Unknown Error " + ErrorCode); break;
        }
        if (ErrorCode == 0) {
            int option = -1;
            while (option != 0) {
                option = ModView.GetOptionCell();
                switch (option) {
                    case 1:  ModStringCell(NameSheet, RowCell, ColumnCell); break;
                    case 2:  ModDoubleCell(NameSheet, RowCell, ColumnCell); break;
                    case 3:  ModDateCell(NameSheet, RowCell, ColumnCell); break;
                    //Estas operaciones numéricas son de una celda
                    case 4:  OperationNumber(NameSheet, RowCell, ColumnCell); break;
                    case 5:  OperationDate(NameSheet, RowCell, ColumnCell); break;
                    default: break;

                }
            }
        }*/
    }

    /**
     * Envia al controlador de modificación de dominio que se va a seleccionar una celda,
     * el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario.
     * El usuario puede escoger que hacer con la celda (Modificar celdas, operaciones de datos, fechas, texto)
     * @throws Exception
     */
    private void OperationsBetweenCells() throws Exception {
        /*errorView.error('-',"OperationsBetweenCells:");
        errorView.error('-',"Tell me the name of the Sheet you want to select:");
        String NameSheet = ModView.GetName();
        errorView.error('-',"Tell me the row and the column of the first cell you want to do the operation:");
        int RowCell = ModView.GetInt();
        int ColumnCell = ModView.GetInt();
        errorView.error('-',"Tell me the row and the column of the second cell you want to do the operation:");
        int RowCell2 = ModView.GetInt();
        int ColumnCell2 = ModView.GetInt();
        errorView.error('-',"Tell me the row and the column of the cell you want the result:");
        int RowRes = ModView.GetInt();
        int ColRes = ModView.GetInt();
        String avoidError = ModView.GetName();
        errorView.error('-',"Do you want to apply the operation to all the Row? [Y/N]");
        String row = ModView.GetName();
        boolean BoolRow = false;
        boolean BoolColumn = false;
        if (row.equalsIgnoreCase("n")) {
            errorView.error('-',"Do you want to apply the operation to all the Column? [Y/N]");
            String col = ModView.GetName();
            if (col.equalsIgnoreCase("y")) {
                BoolColumn = true;
            }
        }
        else {
            BoolRow = true;
        }

        int ErrorCode = ModController.ExistsCell(NameSheet, RowCell, ColumnCell);
        int ErrorCode2 = ModController.ExistsCell(NameSheet, RowCell2, ColumnCell2);
        int ErrorCode3 = ModController.ExistsCell(NameSheet, RowRes, ColRes);

        switch (ErrorCode) {
            case 0: errorView.error('-', "Cell selected successfully"); break;
            case -1: errorView.error('*', "The sheet with the name NameSheet doesn't exist"); break;
            case -2: errorView.error('*', "The cell doesn't exist."); break;
            default: errorView.error('*', "Unknown Error " + ErrorCode); break;
        }
        if (ErrorCode == 0) {
            switch (ErrorCode2) {
                case 0:
                    errorView.error('-', "Cell2 selected successfully"); break;
                case -1:
                    errorView.error('*', "The sheet with the name NameSheet doesn't exist");break;
                case -2:
                    errorView.error('*', "The cell2 doesn't exist."); break;
                default:
                    errorView.error('*', "Unknown Error " + ErrorCode); break;
            }

        }
        if (ErrorCode == 0 && ErrorCode2 == 0) {
            switch (ErrorCode3) {
                case 0:
                    errorView.error('-', "Cell selected successfully");
                    break;
                case -1:
                    errorView.error('*', "The sheet with the name NameSheet doesn't exist");
                    break;
                case -2:
                    errorView.error('*', "The result cell  doesn't exist.");
                    break;
                default:
                    errorView.error('*', "Unknown Error " + ErrorCode);
                    break;
            }
        }
        if (ErrorCode == 0 && ErrorCode2 == 0 && ErrorCode3 == 0) {
            int option = -1;
            while (option != 0) {
                option = ModView.GetOptionOpCells();
                switch (option) {
                    case 1:  Sum(NameSheet, RowCell, ColumnCell, RowCell2, ColumnCell2, RowRes, ColRes, BoolRow,BoolColumn); break;
                    case 2:  Rest(NameSheet, RowCell, ColumnCell, RowCell2, ColumnCell2, RowRes, ColRes, BoolRow,BoolColumn); break;
                    case 3:  Multiplication(NameSheet, RowCell, ColumnCell, RowCell2, ColumnCell2, RowRes, ColRes, BoolRow,BoolColumn); break;
                    case 4:  Division(NameSheet, RowCell, ColumnCell, RowCell2, ColumnCell2, RowRes, ColRes, BoolRow,BoolColumn); break;
                    default: break;

                }
            }
        }*/
    }

    /**
     * Envia al controlador de modificación de dominio que se va a realizar una suma entre dos celdas
     * añadiendo una string y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @param RowCell fila de la celda1
     * @param ColumnCell columna de la celda1
     * @param RowCell2 fila de la celda2
     * @param ColumnCell2 columna de la celda2
     * @param RowRes fila de la celda resultado
     * @param ColRes columna de la celda resultado
     * @param BoolRow boolean para saber si pasar la operación a toda la fila
     * @param BoolCol boolean para saber si pasar la operación a toda la columna
     * @throws Exception
     */

    private void Sum(String NameSheet, int RowCell, int ColumnCell, int RowCell2, int ColumnCell2, int RowRes, int ColRes, boolean BoolRow, boolean BoolCol) throws Exception {
        /*errorView.error('-',"Addition between to cells:");

        int ErrorCode = ModController.Sum(NameSheet,RowCell,ColumnCell,RowCell2, ColumnCell2, RowRes, ColRes,BoolRow,BoolCol);
        switch (ErrorCode) {
            case 0: errorView.error('-', "The operation has been made successfully"); break;
            case -1: errorView.error('*', "The operation hasn't been done due to an error"); break;
            case -2: errorView.error('*', "The Cell1 doesn't exist"); break;
            case -3: errorView.error('*', "The Cell2 doesn't exist"); break;
            case -4: errorView.error('*', "The ResultCell doesn't exist"); break;
            default: errorView.error('*', "Unknown Error " + ErrorCode); break;
        }*/
    }

    /**
     * Envia al controlador de modificación de dominio que se va a realizar una resta entre dos celdas
     * añadiendo una string y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @param RowCell fila de la celda1
     * @param ColumnCell columna de la celda1
     * @param RowCell2 fila de la celda2
     * @param ColumnCell2 columna de la celda2
     * @param RowRes fila de la celda resultado
     * @param ColRes columna de la celda resultado
     * @param BoolRow boolean para saber si pasar la operación a toda la fila
     * @param BoolCol boolean para saber si pasar la operación a toda la columna
     * @throws Exception
     */

    private void Rest(String NameSheet, int RowCell, int ColumnCell, int RowCell2, int ColumnCell2, int RowRes, int ColRes, boolean BoolRow, boolean BoolCol) throws Exception {
        /*errorView.error('-',"Substraction between to cells:");

        int ErrorCode = ModController.Rest(NameSheet,RowCell,ColumnCell,RowCell2, ColumnCell2, RowRes, ColRes,BoolRow,BoolCol);
        switch (ErrorCode) {
            case 0: errorView.error('-', "The operation has been made successfully"); break;
            case -1: errorView.error('*', "The operation hasn't been done due to an error"); break;
            case -2: errorView.error('*', "The Cell1 doesn't exist"); break;
            case -3: errorView.error('*', "The Cell2 doesn't exist"); break;
            case -4: errorView.error('*', "The ResultCell doesn't exist"); break;
            default: errorView.error('*', "Unknown Error " + ErrorCode); break;
        }*/
    }

    /**
     * Envia al controlador de modificación de dominio que se va a realizar una multiplicación entre dos celdas
     * añadiendo una string y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @param RowCell fila de la celda1
     * @param ColumnCell columna de la celda1
     * @param RowCell2 fila de la celda2
     * @param ColumnCell2 columna de la celda2
     * @param RowRes fila de la celda resultado
     * @param ColRes columna de la celda resultado
     * @param BoolRow boolean para saber si pasar la operación a toda la fila
     * @param BoolCol boolean para saber si pasar la operación a toda la columna
     * @throws Exception
     */

    private void Multiplication(String NameSheet, int RowCell, int ColumnCell, int RowCell2, int ColumnCell2, int RowRes, int ColRes, boolean BoolRow, boolean BoolCol) throws Exception {
        /*errorView.error('-',"Multiplication between to cells:");

        int ErrorCode = ModController.Multiplication(NameSheet,RowCell,ColumnCell,RowCell2, ColumnCell2, RowRes, ColRes,BoolRow,BoolCol);
        switch (ErrorCode) {
            case 0: errorView.error('-', "The operation has been made successfully"); break;
            case -1: errorView.error('*', "The operation hasn't been done due to an error"); break;
            case -2: errorView.error('*', "The Cell1 doesn't exist"); break;
            case -3: errorView.error('*', "The Cell2 doesn't exist"); break;
            case -4: errorView.error('*', "The ResultCell doesn't exist"); break;
            default: errorView.error('*', "Unknown Error " + ErrorCode); break;
        }*/
    }

    /**
     * Envia al controlador de modificación de dominio que se va a realizar una división entre dos celdas
     * añadiendo una string y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @param RowCell fila de la celda1
     * @param ColumnCell columna de la celda1
     * @param RowCell2 fila de la celda2
     * @param ColumnCell2 columna de la celda2
     * @param RowRes fila de la celda resultado
     * @param ColRes columna de la celda resultado
     * @param BoolRow boolean para saber si pasar la operación a toda la fila
     * @param BoolCol boolean para saber si pasar la operación a toda la columna
     * @throws Exception
     */

    private void Division(String NameSheet, int RowCell, int ColumnCell, int RowCell2, int ColumnCell2, int RowRes, int ColRes, boolean BoolRow, boolean BoolCol) throws Exception {
        /*errorView.error('-',"Division between to cells:");

        int ErrorCode = ModController.Division(NameSheet,RowCell,ColumnCell,RowCell2, ColumnCell2, RowRes, ColRes,BoolRow,BoolCol);
        switch (ErrorCode) {
            case 0: errorView.error('-', "The operation has been made successfully"); break;
            case -1: errorView.error('*', "The operation hasn't been done due to an error"); break;
            case -2: errorView.error('*', "The Cell1 doesn't exist"); break;
            case -3: errorView.error('*', "The Cell2 doesn't exist"); break;
            case -4: errorView.error('*', "The ResultCell doesn't exist"); break;
            default: errorView.error('*', "Unknown Error " + ErrorCode); break;
        }*/
    }

    /**
     * Envia al controlador de modificación de dominio que se van a mostrar las celdas por pantalla
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @throws Exception
     */
    private void Print() throws  Exception {
        /*errorView.error('-',"Printing");
        errorView.error('-',"Tell me the name of the sheet you want to print:");
        String NameSheet = ModView.GetName();

        Vector<String> Cells = new Vector<>();
        Cells = ModController.Print(NameSheet);
        if (Cells.isEmpty()) {
            errorView.error('-', "The Sheet doesn't exist");
        }
        else {
            int rows = ModController.getSheetSize(NameSheet);
            int cols = ModController.getSheetColSize(NameSheet);
            int k = 0;
            ModView.Write("   ");
            for (int d = 0; d < cols; ++d) {
                ModView.Write(Integer.toString(d));
                ModView.Write("    ");
            }
            ModView.Writeln("");
            ModView.Writeln("");
            for (int i = 0; i < rows; ++i) {
                ModView.Write(Integer.toString(i));
                for (int j = 0; j < cols; ++j) {
                    String p = Cells.get(k);
                    ModView.Write("   ");
                    ModView.Write(p);
                    ++k;
                }
                ModView.Writeln("");
                ModView.Writeln("");
            }
        }*/
    }

    /**
     * Método que dependiendo de la opción escogida por el usuario llama a funciones
     * (crear hoja, cambiar nombre de la hoja, borrar hoja, ver la lista de hojas, añadir fila,
     * añadir columna, borrar fila, borrar columna, seleccionar rango, seleccionar celda)
     * @param index posición del documento a modificar
     * @throws  Exception
     */
    public void ini(int index, String docName, DocumentView documentView) throws Exception {

        ModController.setMyDocument(index);
        ModController.LoadSheet();
        ViewSheet = new SheetView(this, documentView);
        ViewSheet.setVisible(docName);
    }

    public String[][] getRawTable(String sheetName) {
        return ModController.getRawTable(sheetName, false);
    }

    public void save() throws IOException {

        ModController.Save();
    }

    public int getColumSize(String sheetName){
        return ModController.getSheetColSize(sheetName);
    }

    public int getRowSize(String sheetName){
        return ModController.getSheetSize(sheetName);
    }

    public String getRawContent (String sheetName, int row, int col){
        return ModController.getRawContent(sheetName, row, col);
    }
}
