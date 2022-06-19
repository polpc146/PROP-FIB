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
     * @param NameSheet nombre de la hoja
     * @param NewName nombre nuevo de la hoja
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
     * @param NameSheet nombre de la hoja
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
     */
    public Vector<String> getSheetList() {

        Vector<String> res = ModController.getSheetList();
        return res;
    }

    /**
     * Envia al controlador de modificación de dominio que se van a añadir filas
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param nameSheet nombre de la hoja
     * @param numRows número de filas
     * @param indexRow indice de la fila a añadir
     */
    public void AddRow(String nameSheet, int numRows, int indexRow) {

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
     * @param nameSheet nombre de la hoja
     * @param numColumns numero de columnas a añadir
     * @param indexColumn indice de la columna a añádir
     */
    public void AddColumn(String nameSheet, int numColumns, int indexColumn) {

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
     * @param nameSheet nombre de la hoja
     * @param numRows numero de filas a borrar
     * @param indexRow indice de la fila a borrar
     */
    public void EraseRow(String nameSheet, int numRows, int indexRow) {

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
     * y recoge la información que le dará el usuari
     * @param nameSheet nombre de la hoja
     * @param numColumns numero de columnas a borrar
     * @param indexColumn indice de la columna a borrar
     */
    public void EraseColumn(String nameSheet, int numColumns, int indexColumn) {

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
     * @param NameSheet nombre de la hoja
     * @param RowCellIni fila de la celda inicial del rango
     * @param ColumnCellIni columna de la celda inicial del rango
     * @param RowCellFin fila de la celda final del rango
     * @param ColumnCellFin columna de la celda final del rango
     * @param numRank numero de rangos
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
     * Envia al controlador de modificación de dominio que se va a realizar una media
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @param RowCell fila de la celda
     * @param ColumnCell columna de la celda
     * @param rowI fila de la celda inicial
     * @param colI columna de la celda inicial
     * @param rowF fila de la celda final
     * @param colF columna de la celda final
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
     * @param RowCell fila de la celda
     * @param ColumnCell columna de la celda
     * @param rowI fila de la celda inicial
     * @param colI columna de la celda inicial
     * @param rowF fila de la celda final
     * @param colF columna de la celda final
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
     * @param RowCell fila de la celda
     * @param ColumnCell columna de la celda
     * @param rowI fila de la celda inicial
     * @param colI columna de la celda inicial
     * @param rowF fila de la celda final
     * @param colF columna de la celda final
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
     * @param RowCell fila de la celda
     * @param ColumnCell columna de la celda
     * @param rowI fila de la celda inicial
     * @param colI columna de la celda inicial
     * @param rowF fila de la celda final
     * @param colF columna de la celda final
     * @param rowI2 fila de la celda inicial del rango 2
     * @param colI2 columna de la celda inicial del rango 2
     * @param rowF2 fila de la celda final del rango 2
     * @param colF2 columna de la celda final del rango 2
     */
    public void covariance(String NameSheet, int RowCell, int ColumnCell, int rowI, int colI, int rowF, int colF, int rowI2, int colI2, int rowF2, int colF2) {

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
     * @param RowCell fila de la celda
     * @param ColumnCell columna de la celda
     * @param rowI fila de la celda inicial
     * @param colI columna de la celda inicial
     * @param rowF fila de la celda final
     * @param colF columna de la celda final
     */
    public void standardDeviation(String NameSheet, int RowCell, int ColumnCell, int rowI, int colI, int rowF, int colF) {

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
     * @param RowCell fila de la celda
     * @param ColumnCell columna de la celda
     * @param rowI fila de la celda inicial
     * @param colI columna de la celda inicial
     * @param rowF fila de la celda final
     * @param colF columna de la celda final
     * @param rowI2 fila de la celda inicial del rango 2
     * @param colI2 columna de la celda inicial del rango 2
     * @param rowF2 fila de la celda final del rango 2
     * @param colF2 columna de la celda final del rango 2
     */
    public void pearson(String NameSheet, int RowCell, int ColumnCell, int rowI, int colI, int rowF, int colF, int rowI2, int colI2, int rowF2, int colF2) {

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
     * @param RowCellIni fila de la celda inicial
     * @param ColumnCellIni columna de la celda inicial
     * @param RowCellFin fila de la celda final
     * @param ColumnCellFin columna de la celda final
     * @param Criterium Criterio ascendente o descendiente
     */
    public void sortRank(String NameSheet, int RowCellIni, int ColumnCellIni, int RowCellFin, int ColumnCellFin, String Criterium) throws Exception {

        RankSelection(NameSheet, RowCellIni, ColumnCellIni, RowCellFin, ColumnCellFin, 1);
        int ErrorCode = ModController.SortRank(NameSheet, Criterium);
        switch (ErrorCode) {
            case 0: break;
            case -1: errorView.error("The Rank hasn't been sorted due to an error"); break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a mover un rango
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @param rowI fila de la celda inicial
     * @param colI columna de la celda inicial
     * @param rowF fila de la celda final
     * @param colF columna de la celda final
     * @param RowCellIni fila de la celda inicial donde se moverá el rango
     * @param ColumnCellIni columna de la celda inicial donde se moverá el rango
     */
    public void MoveRank(String NameSheet, int rowI, int colI, int rowF, int colF, int RowCellIni, int ColumnCellIni) {

        if (ModController.SelectRank(NameSheet, rowI, colI, rowF, colF, 1) != 0) return;
        int ErrorCode = ModController.MoveRank(NameSheet,RowCellIni,ColumnCellIni);
        switch (ErrorCode) {
            case 0: break;
            case -1: errorView.error("The initial Cell doesn't exist"); break;
            case -2: errorView.error("The Rank is very big"); break;
            case -3: errorView.error("The Rank hasn't been moved due to an error"); break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a copiar un rango
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @param rowI fila de la celda inicial
     * @param colI columna de la celda inicial
     * @param rowF fila de la celda final
     * @param colF columna de la celda final
     * @param RowCellIni fila de la celda inicial donde se copiará el rango
     * @param ColumnCellIni columna de la celda inicial donde se copiará el rango
     */
    public void CopyRank(String NameSheet, int rowI, int colI, int rowF, int colF, int RowCellIni, int ColumnCellIni) {

        if (ModController.SelectRank(NameSheet, rowI, colI, rowF, colF, 1) != 0) return;
        int ErrorCode = ModController.CopyRank(NameSheet,RowCellIni,ColumnCellIni);
        switch (ErrorCode) {
            case 0: break;
            case -1: errorView.error("The initial Cell doesn't exist"); break;
            case -2: errorView.error("The Rank is very big"); break;
            case -3: errorView.error("The Rank hasn't been moved due to an error"); break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Modifica la celda según el tipo que sea (Double, String, Date, Formula)
     * @param NameSheet nombre de la hoja
     * @param RowCell fila de la celda
     * @param ColumnCell columna de la celda
     * @param data contenido de la celda
     * @throws Exception
     */
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
            String num11= String.valueOf(num1);
            char num2 = data.charAt(7);
            String num22= String.valueOf(num2);
            char num3 = data.charAt(8);
            String num33= String.valueOf(num3);
            char num4 = data.charAt(9);
            String num44= String.valueOf(num4);
            String numS = num11+num22+num33+num44;
            int year = Integer.parseInt(numS);
            num1 = data.charAt(0);
            num11= String.valueOf(num1);
            num2 = data.charAt(1);
            num22= String.valueOf(num2);
            numS = num11+num22;
            int day = Integer.parseInt(numS);
            num1 = data.charAt(3);
            num11= String.valueOf(num1);
            num2 = data.charAt(4);
            num22= String.valueOf(num2);
            numS = num11+num22;
            int month = Integer.parseInt(numS);
            ModDateCell(NameSheet, RowCell, ColumnCell, day, month, year);
        }
        else if (type == 3) {

            int size = data.length();

            if (size < 6) {

                ModStringCell(NameSheet, RowCell, ColumnCell, data);
                return;
            }
            StringBuilder colIni = new StringBuilder();
            StringBuilder rowIni = new StringBuilder();
            StringBuilder colFin = new StringBuilder();
            StringBuilder rowFin = new StringBuilder();
            if((data.charAt(1) >= 'a' && data.charAt(1) <= 'z') || (data.charAt(1) >= 'A' && data.charAt(1) <= 'Z')) {

                int i = 1;
                colIni.append(data.charAt(i));
                ++i;
                while (i < size && ((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z'))) {

                    colIni.append(data.charAt(i));
                    ++i;
                }
                if (i < size && data.charAt(i) == ',') {

                    ++i;
                    if(i < size && data.charAt(i) >= '0' && data.charAt(i) <= '9') {

                        rowIni.append(data.charAt(i));
                        ++i;
                        while (i < size && data.charAt(i) >= '0' && data.charAt(i) <= '9') {

                            rowIni.append(data.charAt(i));
                            ++i;
                        }
                        if (i < size && (data.charAt(i) == '+' || data.charAt(i) == '-' || data.charAt(i) == '*' || data.charAt(i) == '/')) {

                            char op = data.charAt(i);
                            ++i;
                            if((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z')) {

                                colFin.append(data.charAt(i));
                                ++i;
                                while (i < size && ((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z'))) {

                                    colFin.append(data.charAt(i));
                                    ++i;
                                }
                                if (i < size && data.charAt(i) == ',') {

                                    ++i;
                                    if(i < size && data.charAt(i) >= '0' && data.charAt(i) <= '9') {

                                        rowFin.append(data.charAt(i));
                                        ++i;
                                        while (i < size && data.charAt(i) >= '0' && data.charAt(i) <= '9') {

                                            rowFin.append(data.charAt(i));
                                            ++i;
                                        }
                                        if (i == size) {

                                            int colI = colToInt(colIni.toString());
                                            int colF = colToInt(colFin.toString());
                                            if (op == '+') ModController.Sum(NameSheet, Integer.parseInt(rowIni.toString()), colI, Integer.parseInt(rowFin.toString()), colF, RowCell, ColumnCell, false, false, data);
                                            else if (op == '-') ModController.Rest(NameSheet, Integer.parseInt(rowIni.toString()), colI, Integer.parseInt(rowFin.toString()), colF, RowCell, ColumnCell, false, false, data);
                                            else if (op == '*') ModController.Multiplication(NameSheet, Integer.parseInt(rowIni.toString()), colI, Integer.parseInt(rowFin.toString()), colF, RowCell, ColumnCell, false, false, data);
                                            else if (op == '/') ModController.Division(NameSheet, Integer.parseInt(rowIni.toString()), colI, Integer.parseInt(rowFin.toString()), colF, RowCell, ColumnCell, false, false, data);
                                            return;
                                        }
                                    }
                                }
                            }
                            else if (data.charAt(i) >= '0' && data.charAt(i) <= '9') {

                                rowFin.append(data.charAt(i));
                                ++i;
                                while (i < size && data.charAt(i) >= '0' && data.charAt(i) <= '9') {

                                    rowFin.append(data.charAt(i));
                                    ++i;
                                }
                                if (i == size) {

                                    int colI = colToInt(colIni.toString());
                                    if (op == '+') ModController.Sum(NameSheet, Integer.parseInt(rowIni.toString()), colI, Integer.parseInt(rowFin.toString()), RowCell, ColumnCell, false, false, data);
                                    else if (op == '-') ModController.Rest(NameSheet, Integer.parseInt(rowIni.toString()), colI, Integer.parseInt(rowFin.toString()), RowCell, ColumnCell, false, false, data);
                                    else if (op == '*') ModController.Multiplication(NameSheet, Integer.parseInt(rowIni.toString()), colI, Integer.parseInt(rowFin.toString()), RowCell, ColumnCell, false, false, data);
                                    else if (op == '/') ModController.Division(NameSheet, Integer.parseInt(rowIni.toString()), colI, Integer.parseInt(rowFin.toString()), RowCell, ColumnCell, false, false, data);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
            colIni = new StringBuilder();
            rowIni = new StringBuilder();
            colFin = new StringBuilder();
            rowFin = new StringBuilder();
            String formula = data.substring(1, 4);
            if (formula.equalsIgnoreCase("day")) {

                if (data.charAt(4) == '(') {

                    int i = 5;
                    while (i < size && ((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z'))) {

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
                            ModController.ExtractElement(NameSheet, Integer.parseInt(rowIni.toString()), colI, false, false, "D", RowCell, ColumnCell, data);
                            return;
                        }
                    }
                }
            }
            formula = data.substring(1, 5);
            if (formula.equalsIgnoreCase("year")) {

                if (data.charAt(5) == '(') {

                    int i = 6;
                    while (i < size && ((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z'))) {

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
                            ModController.ExtractElement(NameSheet, Integer.parseInt(rowIni.toString()), colI, false, false, "Y", RowCell, ColumnCell, data);
                            return;
                        }
                    }
                }
            }
            formula = data.substring(1, 5);
            if (formula.equalsIgnoreCase("size")) {

                if (data.charAt(5) == '(') {

                    int i = 6;
                    while (i < size && ((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z'))) {

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
                            ModController.Size(NameSheet, Integer.parseInt(rowIni.toString()), colI, RowCell, ColumnCell, data);
                            return;
                        }
                    }
                }
            }
            formula = data.substring(1, 6);
            if (formula.equalsIgnoreCase("month")) {

                if (data.charAt(6) == '(') {

                    int i = 7;
                    while (i < size && ((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z'))) {

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
                            ModController.ExtractElement(NameSheet, Integer.parseInt(rowIni.toString()), colI, false, false, "M", RowCell, ColumnCell, data);
                            return;
                        }
                    }
                }
            }
            formula = data.substring(1, 9);
            if (formula.equalsIgnoreCase("truncate")) {

                if (data.charAt(9) == '(') {

                    int i = 10;
                    while (i < size && ((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z'))) {

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
                            ModController.Truncate(NameSheet, Integer.parseInt(rowIni.toString()), colI, false, false, RowCell, ColumnCell, data);
                            return;
                        }
                    }
                }
            }
            formula = data.substring(1, 9);
            if (formula.equalsIgnoreCase("increase")) {

                if (data.charAt(9) == '(') {

                    int i = 10;
                    while (i < size && ((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z'))) {

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
                            ModController.Increase(NameSheet, Integer.parseInt(rowIni.toString()), colI, false, false, RowCell, ColumnCell, data);
                            return;
                        }
                    }
                }
            }
            formula = data.substring(1, 9);
            if (formula.equalsIgnoreCase("absolute")) {

                if (data.charAt(9) == '(') {

                    int i = 10;
                    while (i < size && ((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z'))) {

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
                            ModController.Absolut(NameSheet, Integer.parseInt(rowIni.toString()), colI, false, false, RowCell, ColumnCell, data);
                            return;
                        }
                    }
                }
            }
            formula = data.substring(1, 7);
            if (formula.equalsIgnoreCase("binary")) {

                if (data.charAt(7) == '(') {

                    int i = 8;
                    while (i < size && ((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z'))) {

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
                            ModController.Conversion(NameSheet, Integer.parseInt(rowIni.toString()), colI, false, false, "b",RowCell, ColumnCell, data);
                            return;
                        }
                    }
                }
            }
            formula = data.substring(1, 12);
            if (formula.equalsIgnoreCase("hexadecimal")) {

                if (data.charAt(12) == '(') {

                    int i = 13;
                    while (i < size && ((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z'))) {

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
                            ModController.Conversion(NameSheet, Integer.parseInt(rowIni.toString()), colI, false, false, "h", RowCell, ColumnCell, data);
                            return;
                        }
                    }
                }
            }
            formula = data.substring(1, 13);
            if (formula.equalsIgnoreCase("dayOfTheWeek")) {

                if (data.charAt(13) == '(') {

                    int i = 14;
                    while (i < size && ((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z'))) {

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
                            ModController.DayOfTheWeek(NameSheet, Integer.parseInt(rowIni.toString()), colI, false, false, RowCell, ColumnCell, data);
                            return;
                        }
                    }
                }
            }
            StringBuilder colIni2 = new StringBuilder();
            StringBuilder rowIni2 = new StringBuilder();
            StringBuilder colFin2 = new StringBuilder();
            StringBuilder rowFin2 = new StringBuilder();
            formula = data.substring(1, 8);
            if (formula.equalsIgnoreCase("average")) {

                if (data.charAt(8) == '(') {

                    int i = 9;
                    while (i < size && ((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z'))) {

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
                            while (i < size && ((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z'))) {

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
                                    ModController.Average(NameSheet, RowCell, ColumnCell, data);
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
                    while (i < size && ((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z'))) {

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
                            while (i < size && ((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z'))) {

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
                                    while (i < size && ((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z'))) {

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
                                            while (i < size && ((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z'))) {

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
                                                    ModController.Pearson(NameSheet, RowCell, ColumnCell, Integer.parseInt(rowIni2.toString()), colI2, Integer.parseInt(rowFin2.toString()), colF2, data);
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
                    while (i < size && ((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z'))) {

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
                            while (i < size && ((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z'))) {

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
                                    ModController.Median(NameSheet, RowCell, ColumnCell,data);
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
                    while (i < size && ((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z'))) {

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
                            while (i < size && ((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z'))) {

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
                                    ModController.Variance(NameSheet, RowCell, ColumnCell,data);
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
                    while (i < size && ((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z'))) {

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
                            while (i < size && ((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z'))) {

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
                                    while (i < size && ((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z'))) {

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
                                            while (i < size && ((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z'))) {

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
                                                    ModController.Covariance(NameSheet, RowCell, ColumnCell, Integer.parseInt(rowIni2.toString()), colI2, Integer.parseInt(rowFin2.toString()), colF2,data);
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
                    while (i < size && ((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z'))) {

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
                            while (i < size && ((data.charAt(i) >= 'a' && data.charAt(i) <= 'z') || (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z'))) {

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
                                    ModController.StandardDeviation(NameSheet, RowCell, ColumnCell,data);
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

    /**
     * Recibe una letra como parametro y dice que número de columna es
     * @param col columna de la celda
     * @return devuelve el número de la columna
     */
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
     * @param Content contenido de la celda
     */
    private void ModStringCell(String NameSheet, int RowCell, int ColumnCell, String Content) {

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
     * @param Content contenido de la celda
     */
    private void ModDoubleCell(String NameSheet, int RowCell, int ColumnCell, Double Content) {

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
     * @param Day  Dia que se va a poner en la celda
     * @param Month Mes que se va a poner en la celda
     * @param Year Año que se va a poner en la celda
     */
    private void ModDateCell(String NameSheet, int RowCell, int ColumnCell, int Day, int Month, int Year) {

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
     * @param criterium Binary[B] o Hexadecimal[H]
     */
    public void conversion(String nameSheet, int row, int col, String criterium){

        int ErrorCode = ModController.Conversion(nameSheet, row, col, false, false, criterium);
        switch (ErrorCode) {
            case 0: break;
            case -1: break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a realizar una extracción de elementos de la fecha en una celda,
     * el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param nameSheet nombre de la hoja en la que se hará la operación
     * @param row fila de la celda donde se hará la operación
     * @param col columna de la celda donde se hará la operación
     */
    public void elementExtraction(String nameSheet, int row, int col, String criterium) {

        int ErrorCode = ModController.ExtractElement(nameSheet,row,col, false, false, criterium);
        switch (ErrorCode) {
            case 0: break;
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
     */
    public void dayOfTheWeek(String nameSheet, int row, int col) {

        int ErrorCode = ModController.DayOfTheWeek(nameSheet,row,col,false, false);
        switch (ErrorCode) {
            case 0: break;
            case -1: break;
            default: errorView.error( "Unknown Error " + ErrorCode); break;
        }
    }


    /**
     * Envia al controlador de modificación de dominio que se va a reemplazar el contenido de las celdas
     * con un contenido determinado por otro,
     * el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @param ElementToReplace Elemento a reemplazar
     * @param ElementReplaced Elemento que vamos a añadir
     */
    public void Replace(String NameSheet, String ElementToReplace, String ElementReplaced) {

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
    public int[] Search(String NameSheet, String SearchS) throws Exception {

        int[] ErrorCode;
        ErrorCode = ModController.Search(NameSheet, SearchS);

        if (ErrorCode[0] == -1) {
            errorView.error("The specified text does not exist");
        }
        else if (ErrorCode[0] < -1){
            errorView.error("Unknown Error " + ErrorCode[0]);
        }
        return ErrorCode;
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
          case 0:
          case -1:
              break;
          default: errorView.error("Unknown Error " + ErrorCode); break;
      }
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

    /**
     * Guarda el documento
     */
    public void save() throws IOException {
        ModController.Save();
    }

    /**
     * Consulta el número de columnas
     * @param sheetName nombre de la hoja
     * @return devuelve el número de columnas de la hoja
     */
    public int getColumSize(String sheetName){
        return ModController.getSheetColSize(sheetName);
    }

    /**
     * Consulta el número de filas
     * @param sheetName nombre de la hoja
     * @return devuelve el número de filas de la hoja
     */
    public int getRowSize(String sheetName){
        return ModController.getSheetSize(sheetName);
    }

    /**
     * Devuelve el contenido de la celda en una hoja concreta
     * @param sheetName nombre de la hoja
     * @param row fila de la celda
     * @param col columna de la celda
     * @return devuelve el contenido
     */
    public String getRawContent (String sheetName, int row, int col){
        return ModController.getRawContent(sheetName, row, col);
    }

    /**
     * Consulta una celda
     * @param sheetName nombre de la hoja
     * @param fila fila de la celda
     * @param col columna de la celda
     * @return retorna la celda en una fila y columna concretas
     */
    public Cell getCell(String sheetName, int fila, int col) {
        return ModController.getCell(sheetName, fila, col);
    }

}
