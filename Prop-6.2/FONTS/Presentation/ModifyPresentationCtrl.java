package Presentation;

import DomainControllers.*;
import Structure.Row;

import java.util.*;
/**
 * Controlador de modificación de la capa presentación
 * Contiene el controlador de modificación del dominio y el controlador de la vista de modificación
 * @author Sandra Buitrago Cervilla
 */
public class ModifyPresentationCtrl {
    private ModifyDomainCtrl ModController;
    private ModifyView ModView;

    /**
     * Crea la instancia de la vista y del controlador de modificación de la capa presentación
     * y las inicializa
     * @param modController Controlador de modificación del dominio
     */
    public ModifyPresentationCtrl(ModifyDomainCtrl modController) {
        ModController = modController;
        ModView = new ModifyView();
    }

    /**
     * Envia al controlador de modificación de dominio que se va a crear una hoja
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * @throws Exception
     */
    private void CreateSheet() throws Exception {
        ModView.ShowMessage('-',"Creation of a new sheet:");
        int ErrorCode = ModController.CreateSheet();
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "Sheet created successfully."); break;
            case -1: ModView.ShowMessage('*', "The Sheet hasn't been created."); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a modificar el nombre de una hoja
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * @throws Exception
     */
    private void ChangeNameSheet() throws Exception {
        ModView.ShowMessage('-',"Changing the name of a sheet:");
        ModView.ShowMessage('-',"Tell me the name of the sheet you want to change:");
        String NameSheet = ModView.GetName();
        ModView.ShowMessage('-',"Tell me the new name of the Sheet:");
        String NewName = ModView.GetName();
        int ErrorCode = ModController.ChangeNameSheet(NameSheet,NewName);
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "Name changed successfully."); break;
            case -1: ModView.ShowMessage('*', "There is no Sheet with that name."); break;
            case -2: ModView.ShowMessage('*', "There is a Sheet with the new name."); break;
            case -3: ModView.ShowMessage('*', "The name hasn't been changed."); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a eliminar una hoja
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * @throws Exception
     */
    private void EraseSheet() throws Exception {
        ModView.ShowMessage('-',"Removal of a Sheet:");
        ModView.ShowMessage('-',"Tell me the name of the sheet you want to remove:");
        String NameSheet = ModView.GetName();
        int ErrorCode = ModController.EraseSheet(NameSheet);
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "Sheet erased successfully"); break;
            case -1: ModView.ShowMessage('*', "The Sheet hasn't been erased"); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a consultar la lista de hojas
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * @throws Exception
     */
    private void getSheetList() throws Exception {
        ModView.ShowMessage('-',"List of the Documents:");
        Vector<String> res = ModController.getSheetList();
        ModView.ShowSheetList(res);
    }

    /**
     * Envia al controlador de modificación de dominio que se van a añadir filas
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @throws Exception
     */
    private void AddRow() throws Exception {
        ModView.ShowMessage('-',"Adding a Row:");
        ModView.ShowMessage('-',"Tell me the name of the sheet you want to add the row:");
        String NameSheet = ModView.GetName();
        ModView.ShowMessage('-',"Tell me how many rows you want to add:");
        int NumRows = ModView.GetInt();
        ModView.ShowMessage('-',"Tell me the row where you want to add it:");
        int IndexRow = ModView.GetInt();

        int ErrorCode = ModController.AddRow(NameSheet,NumRows,IndexRow);
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "Row/s added successfully"); break;
            case -1: ModView.ShowMessage('*', "The Sheet with the name NameSheet doesn't exist"); break;
            case -2: ModView.ShowMessage('*', "There is no Row with that IndexRow."); break;
            case -3: ModView.ShowMessage('*', "The Row/s hasn't been added."); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se van a añadir columnas
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @throws Exception
     */
    private void AddColumn() throws Exception {

        ModView.ShowMessage('-',"Adding a Column:");
        ModView.ShowMessage('-',"Tell me the name of the sheet you want to add the column:");
        String NameSheet = ModView.GetName();
        ModView.ShowMessage('-',"Tell me how many columns you want to add:");
        int NumColumns = ModView.GetInt();
        ModView.ShowMessage('-',"Tell me the column where you want to add it:");
        int IndexColumn = ModView.GetInt();

        int ErrorCode = ModController.AddColumn(NameSheet,NumColumns,IndexColumn);
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "Column/s added successfully"); break;
            case -1: ModView.ShowMessage('*', "The Sheet with the name NameSheet doesn't exist"); break;
            case -2: ModView.ShowMessage('*', "There is no Column with that IndexColumn."); break;
            case -3: ModView.ShowMessage('*', "The Column/s hasn't been added."); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se van a eliminar filas
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @throws Exception
     */
    private void EraseRow() throws Exception {
        ModView.ShowMessage('-',"Removal of a Row:");
        ModView.ShowMessage('-',"Tell me the name of the sheet you want to erase the row:");
        String NameSheet = ModView.GetName();
        ModView.ShowMessage('-',"Tell me how many rows you want to erase:");
        int NumRows = ModView.GetInt();
        ModView.ShowMessage('-',"Tell me the row where you want to erase it:");
        int IndexRow = ModView.GetInt();

        int ErrorCode = ModController.EraseRow(NameSheet,NumRows,IndexRow);
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "Row/s erased successfully"); break;
            case -1: ModView.ShowMessage('*', "The Sheet with the name NameSheet doesn't exist"); break;
            case -2: ModView.ShowMessage('*', "There is no Row with that IndexRow."); break;
            case -3: ModView.ShowMessage('*', "The Row/s hasn't been erased."); break;
            case -4: ModView.ShowMessage('*', "You are trying to erase too many rows"); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se van a eliminar columnas
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @throws Exception
     */
    private void EraseColumn() throws Exception {
        ModView.ShowMessage('-',"Removal of a Column:");
        ModView.ShowMessage('-',"Tell me the name of the sheet you want to erase the column:");
        String NameSheet = ModView.GetName();
        ModView.ShowMessage('-',"Tell me how many columns you want to erase:");
        int NumColumns = ModView.GetInt();
        ModView.ShowMessage('-',"Tell me the column where you want to erase it:");
        int IndexColumn = ModView.GetInt();

        int ErrorCode = ModController.EraseColumn(NameSheet,NumColumns,IndexColumn);
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "Column/s erased successfully"); break;
            case -1: ModView.ShowMessage('*', "The Sheet with the name NameSheet doesn't exist"); break;
            case -2: ModView.ShowMessage('*', "There is no Column with that IndexRow."); break;
            case -3: ModView.ShowMessage('*', "The Column/s hasn't been erased."); break;
            case -4: ModView.ShowMessage('*', "You are trying to erase too many columns"); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a seleccionar un rango
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario.
     * El usuario puede escoger que hacer con el rango (ordenar el rango, moverlo, copiarlo o hacer una operación)
     * @throws Exception
     */
    private void RankSelection() throws Exception {
        ModView.ShowMessage('-',"Select the Rank:");
        ModView.ShowMessage('-',"Tell me the name of the Sheet you want to select:");
        String NameSheet = ModView.GetName();
        ModView.ShowMessage('-',"Tell me the row and the column of the initial cell you want to select:");
        int RowCellIni = ModView.GetInt();
        int ColumnCellIni = ModView.GetInt();
        ModView.ShowMessage('-',"Tell me the row and the column of the final cell you want to select:");
        int RowCellFin = ModView.GetInt();
        int ColumnCellFin = ModView.GetInt();


        int ErrorCode = ModController.SelectRank(NameSheet, RowCellIni, ColumnCellIni, RowCellFin, ColumnCellFin, 1);
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "Rank selected successfully"); break;
            case -1: ModView.ShowMessage('*', "The sheet with the name NameSheet doesn't exist"); break;
            case -2: ModView.ShowMessage('*', "The initial cell doesn't exist."); break;
            case -3: ModView.ShowMessage('*', "The rank can't be selected."); break;
            case -4: ModView.ShowMessage('*', "The final cell doesn't exist."); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
        if (ErrorCode == 0) {
            int option = -1;
            while (option != 0) {
                option = ModView.GetOptionRank();
                switch (option) {
                    case 1: SortRank(NameSheet);  break;
                    case 2: MoveRank(NameSheet);  break;
                    case 3: CopyRank(NameSheet);  break;
                    //hacer operaciones de rangos
                    case 4: OperationRank(NameSheet); break;
                    case 5: OperationText(NameSheet); break;
                    default: break;

                }
            }
        }
    }

    /**
     * Método que dependiendo de la opción escogida por el usuario llama a funciones de operaciones de rango
     * (media, mediana, varianza, covarianza, desviación estandard, coeficiente de correlación de Pearson)
     * @param NameSheet nombre de la hoja
     * @throws  Exception
     */

    private void OperationRank(String NameSheet) throws Exception {
        ModView.ShowMessage('-',"Statistical Operations:");
        ModView.ShowMessage('-',"Tell me what operation you want to do:");
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
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a realizar una media
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @throws Exception
     */
    private void Average(String NameSheet) throws  Exception {
        ModView.ShowMessage('-',"Average");
        ModView.ShowMessage('-',"Tell me the row and the column of the cell you want the result:");
        int RowCell = ModView.GetInt();
        int ColumnCell = ModView.GetInt();

        int ErrorCode = ModController.Average(NameSheet,RowCell,ColumnCell);
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "Average done successfully"); break;
            case -1: ModView.ShowMessage('*', "The Average hasn't been done due to an error"); break;
            case -2: ModView.ShowMessage('*', "The cell doesn't exist"); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a realizar una mediana
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @throws Exception
     */
    private void Median(String NameSheet) throws  Exception {
        ModView.ShowMessage('-',"Median");
        ModView.ShowMessage('-',"Tell me the row and the column of the cell you want the result:");
        int RowCell = ModView.GetInt();
        int ColumnCell = ModView.GetInt();

        int ErrorCode = ModController.Median(NameSheet,RowCell,ColumnCell);
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "Median done successfully"); break;
            case -1: ModView.ShowMessage('*', "The Median hasn't been done due to an error"); break;
            case -2: ModView.ShowMessage('*', "The cell doesn't exist"); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a realizar una varianza
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @throws Exception
     */
    private void Variance(String NameSheet) throws  Exception {
        ModView.ShowMessage('-',"Variance");
        ModView.ShowMessage('-',"Tell me the row and the column of the cell you want the result:");
        int RowCell = ModView.GetInt();
        int ColumnCell = ModView.GetInt();

        int ErrorCode = ModController.Variance(NameSheet,RowCell,ColumnCell);
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "Variance done successfully"); break;
            case -1: ModView.ShowMessage('*', "The Variance hasn't been done due to an error"); break;
            case -2: ModView.ShowMessage('*', "The cell doesn't exist"); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a realizar la covarianza
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @throws Exception
     */
    private void Covariance(String NameSheet) throws  Exception {
        ModView.ShowMessage('-',"Covariance");
        ModView.ShowMessage('-',"Tell me the row and the column of the initial cell of the other rank:");
        int RowCellIni = ModView.GetInt();
        int ColumnCellIni = ModView.GetInt();
        ModView.ShowMessage('-',"Tell me the row and the column of the last cell of the other rank:");
        int RowCellFin = ModView.GetInt();
        int ColumnCellFin = ModView.GetInt();
        ModView.ShowMessage('-',"Tell me the row and the column of the cell you want the result:");
        int RowCell = ModView.GetInt();
        int ColumnCell = ModView.GetInt();

        int ErrorCode = ModController.Covariance(NameSheet,RowCell,ColumnCell, RowCellIni, ColumnCellIni, RowCellFin, ColumnCellFin);
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "Covariance done successfully"); break;
            case -1: ModView.ShowMessage('*', "The Covariance hasn't been done due to an error"); break;
            case -2: ModView.ShowMessage('*', "The cell doesn't exist"); break;
            case -3: ModView.ShowMessage('*', "The second rank doesn't exist"); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a realizar una desviación estandard
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @throws Exception
     */
    private void StandardDeviation(String NameSheet) throws  Exception {
        ModView.ShowMessage('-',"Standard Deviation");
        ModView.ShowMessage('-',"Tell me the row and the column of the cell you want the result:");
        int RowCell = ModView.GetInt();
        int ColumnCell = ModView.GetInt();

        int ErrorCode = ModController.StandardDeviation(NameSheet,RowCell,ColumnCell);
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "Standard Deviation done successfully"); break;
            case -1: ModView.ShowMessage('*', "The Standard Deviation hasn't been done due to an error"); break;
            case -2: ModView.ShowMessage('*', "The cell doesn't exist"); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a realizar un coeficiente de correlación de Pearson
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @throws Exception
     */
    private void Pearson(String NameSheet) throws  Exception {
        ModView.ShowMessage('-',"Pearson's correlation coefficient");
        ModView.ShowMessage('-',"Tell me the row and the column of the initial cell of the other rank:");
        int RowCellIni = ModView.GetInt();
        int ColumnCellIni = ModView.GetInt();
        ModView.ShowMessage('-',"Tell me the row and the column of the last cell of the other rank:");
        int RowCellFin = ModView.GetInt();
        int ColumnCellFin = ModView.GetInt();
        ModView.ShowMessage('-',"Tell me the row and the column of the cell you want the result:");
        int RowCell = ModView.GetInt();
        int ColumnCell = ModView.GetInt();

        int ErrorCode = ModController.Pearson(NameSheet,RowCell,ColumnCell, RowCellIni, ColumnCellIni, RowCellFin, ColumnCellFin);
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "Pearson's correlation coefficient done successfully"); break;
            case -1: ModView.ShowMessage('*', "The Pearson's correlation coefficient hasn't been done due to an error"); break;
            case -2: ModView.ShowMessage('*', "The cell doesn't exist"); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a realizar una ordenación en el rango
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @throws Exception
     */
    private void SortRank(String NameSheet) throws Exception {
        ModView.ShowMessage('-',"Sort the Rank:");

        String Criterium = "";
        do {
            ModView.ShowMessage('-',"Tell me how do you want to sort the rank (Ascendant [A], Descendant [D],):");
            Criterium = ModView.GetName();
        }
        while (!Criterium.equalsIgnoreCase("Ascendant") && !Criterium.equalsIgnoreCase("Descendant") && !Criterium.equalsIgnoreCase("A") && !Criterium.equalsIgnoreCase("D"));

        int ErrorCode = ModController.SortRank(NameSheet, Criterium);
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "Rank sorted successfully"); break;
            case -1: ModView.ShowMessage('*', "The Rank hasn't been sorted due to an error"); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a mover un rango
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @throws Exception
     */
    private void MoveRank(String NameSheet) throws Exception {
        ModView.ShowMessage('-',"Move a Rank:");
        ModView.ShowMessage('-',"Tell me the row and the column of the initial cell you want to move the rank:");
        int RowCellIni = ModView.GetInt();
        int ColumnCellIni = ModView.GetInt();
        int ErrorCode = ModController.MoveRank(NameSheet,RowCellIni,ColumnCellIni);
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "Rank moved successfully"); break;
            case -1: ModView.ShowMessage('*', "The initial Cell doesn't exist"); break;
            case -2: ModView.ShowMessage('*', "The Rank is very big"); break;
            case -3: ModView.ShowMessage('*', "The Rank hasn't been moved due to an error"); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a copiar un rango
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @throws Exception
     */
    private void CopyRank(String NameSheet) throws Exception {
        ModView.ShowMessage('-',"Copy a Rank:");
        ModView.ShowMessage('-',"Tell me the row and the column of the initial cell you want to copy the rank:");
        int RowCellIni = ModView.GetInt();
        int ColumnCellIni = ModView.GetInt();
        int ErrorCode = ModController.CopyRank(NameSheet,RowCellIni,ColumnCellIni);
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "Rank copied successfully"); break;
            case -1: ModView.ShowMessage('*', "The initial Cell doesn't exist"); break;
            case -2: ModView.ShowMessage('*', "The Rank is very big"); break;
            case -3: ModView.ShowMessage('*', "The Rank hasn't been moved due to an error"); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
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
    private void ModStringCell(String NameSheet, int RowCell, int ColumnCell) throws Exception {
        ModView.ShowMessage('-',"Modifying a Cell:");
        ModView.ShowMessage('-',"Tell me what do you want to put on that Cell:");
        String Content = ModView.GetName();

        int ErrorCode = ModController.ModifyString(NameSheet,RowCell,ColumnCell,Content);
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "The Cell has been modified successfully"); break;
            case -1: ModView.ShowMessage('*', "The cell has not been modified due to an error"); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
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
    private void ModDoubleCell(String NameSheet, int RowCell, int ColumnCell) throws Exception {
        ModView.ShowMessage('-',"Modifying a Cell:");
        ModView.ShowMessage('-',"Tell me what do you want to put on that Cell:");
        Double Content = ModView.GetDouble();

        int ErrorCode = ModController.ModifyDouble(NameSheet,RowCell,ColumnCell,Content);
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "The Cell has been modified successfully"); break;
            case -1: ModView.ShowMessage('*', "The cell has not been modified due to an error"); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
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
    private void ModDateCell(String NameSheet, int RowCell, int ColumnCell) throws Exception {
        ModView.ShowMessage('-',"Modifying a Cell:");
        ModView.ShowMessage('-',"Tell me the day you want to put on that Cell:");
        int Day = ModView.GetInt();
        ModView.ShowMessage('-',"Tell me the month you want to put on that Cell:");
        int Month = ModView.GetInt();
        ModView.ShowMessage('-',"Tell me the year do you want to put on that Cell:");
        int Year = ModView.GetInt();


        int ErrorCode = ModController.ModifyDate(NameSheet,RowCell,ColumnCell, Day, Month, Year);
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "The cell has been modified successfully"); break;
            case -1: ModView.ShowMessage('*', "The cell has not been modified due to an error"); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a realizar una truncación a una celda,
     * el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @param Row fila de la celda donde se hará la operación
     * @param Col columna de la celda donde se hará la operación
     * @throws Exception
     */
    private void Truncate(String NameSheet, int Row, int Col) throws Exception {
        ModView.ShowMessage('-',"Truncating the content of a Cell:");
        ModView.ShowMessage('-',"Do you want to apply the truncation to all the Row? [Y/N]");
        String row = ModView.GetName();
        boolean TruncRow = false;
        boolean TruncColumn = false;
        if (row.equalsIgnoreCase("n")) {
            ModView.ShowMessage('-',"Do you want to apply the truncation to all the Column? [Y/N]");
            String col = ModView.GetName();
            if (col.equalsIgnoreCase("y")) {
                TruncColumn = true;
            }
        }
        else {
            TruncRow = true;
        }

        int ErrorCode = ModController.Truncate(NameSheet,Row,Col,TruncRow, TruncColumn);
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "The Truncation has been done successfully"); break;
            case -1: ModView.ShowMessage('*', "The Truncation has not been modified due to an error"); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a realizar una incrementación de una celda,
     * el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @param Row fila de la celda donde se hará la operación
     * @param Col columna de la celda donde se hará la operación
     * @throws Exception
     */
    private void Increase(String NameSheet, int Row, int Col) throws Exception {
        ModView.ShowMessage('-',"Increasing a Cell:");
        ModView.ShowMessage('-',"Do you want to apply the increase to all the Row? [Y/N]");
        String row = ModView.GetName();
        boolean IncRow = false;
        boolean IncColumn = false;
        if (row.equalsIgnoreCase("n")) {
            ModView.ShowMessage('-',"Do you want to apply the increase to all the Column? [Y/N]");
            String col = ModView.GetName();
            if (col.equalsIgnoreCase("y")) {
                IncColumn = true;
            }
        }
        else {
            IncRow = true;
        }

        int ErrorCode = ModController.Increase(NameSheet,Row,Col,IncRow, IncColumn);
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "The Increase has been done successfully"); break;
            case -1: ModView.ShowMessage('*', "The Increase has not been modified due to an error"); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a poner el valor absoluto de una celda,
     * el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @param Row fila de la celda donde se hará la operación
     * @param Col columna de la celda donde se hará la operación
     * @throws Exception
     */
    private void Absolut(String NameSheet, int Row, int Col) throws Exception {
        ModView.ShowMessage('-',"Making absolut the content of a Cell:");
        ModView.ShowMessage('-',"Do you want to apply the absolut value to all the Row? [Y/N]");
        String row = ModView.GetName();
        boolean AbRow = false;
        boolean AbColumn = false;
        if (row.equalsIgnoreCase("n")) {
            ModView.ShowMessage('-',"Do you want to apply the absolut value to all the Column? [Y/N]");
            String col = ModView.GetName();
            if (col.equalsIgnoreCase("y")) {
                AbColumn = true;
            }
        }
        else {
            AbRow = true;
        }

        int ErrorCode = ModController.Absolut(NameSheet,Row,Col,AbRow, AbColumn);
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "The Absolut value has been done successfully"); break;
            case -1: ModView.ShowMessage('*', "The Absolut value has not been modified due to an error"); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a realizar una conversión a una celda,
     * el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @param Row fila de la celda donde se hará la operación
     * @param Col columna de la celda donde se hará la operación
     * @throws Exception
     */
    private void Conversion(String NameSheet, int Row, int Col) throws Exception {
        ModView.ShowMessage('-',"Converting the content of a Cell:");
        String Criterium = "";
        do {
            ModView.ShowMessage('-',"How do you want to convert it (Binary [B] or Hexadecimal [H]):");
            Criterium = ModView.GetName();
        }
        while (!Criterium.equalsIgnoreCase("Binary") && !Criterium.equalsIgnoreCase("Hexadecimal") && !Criterium.equalsIgnoreCase("B") && !Criterium.equalsIgnoreCase("H"));
        ModView.ShowMessage('-',"Do you want to apply the conversion to all the Row? [Y/N]");
        String row = ModView.GetName();
        boolean ConvRow = false;
        boolean ConvColumn = false;
        if (row.equalsIgnoreCase("n")) {
            ModView.ShowMessage('-',"Do you want to apply the conversion to all the Column? [Y/N]");
            String col = ModView.GetName();
            if (col.equalsIgnoreCase("y")) {
                ConvColumn = true;
            }
        }
        else {
            ConvRow = true;
        }

        int ErrorCode = ModController.Conversion(NameSheet,Row,Col,ConvRow, ConvColumn, Criterium);
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "The Cell has been converted successfully"); break;
            case -1: ModView.ShowMessage('*', "The cell has not been converted due to an error"); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Método que dependiendo de la opción escogida por el usuario llama a funciones de operaciones de números en una celda
     * (truncación, conversión)
     * @param NameSheet nombre de la hoja
     * @param Row fila de la celda en la que se va a hacer la operación
     * @param Col columna de la celda en la que se va a hacer la operación
     * @throws  Exception
     */
    private void OperationNumber(String NameSheet, int Row,  int Col) throws Exception {
        ModView.ShowMessage('-',"Numeric operations of a cell:");
        ModView.ShowMessage('-',"Tell me what operation you want to do:");
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
    }

    /**
     * Envia al controlador de modificación de dominio que se va a realizar una extracción de elementos de la fecha en una celda,
     * el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @param Row fila de la celda donde se hará la operación
     * @param Col columna de la celda donde se hará la operación
     * @throws Exception
     */
    private void ElementExtraction(String NameSheet, int Row, int Col) throws Exception {
        ModView.ShowMessage('-',"Element Extraction of a cell:");
        ModView.ShowMessage('-',"Tell me the element you want to extract");
        String Criterium;
        do {
            ModView.ShowMessage('-',"What do you want to extract day [D], month [M] or year [Y]):");
            Criterium = ModView.GetName();
        }
        while (!Criterium.equalsIgnoreCase("Day") && !Criterium.equalsIgnoreCase("Month") && !Criterium.equalsIgnoreCase("Year") && !Criterium.equalsIgnoreCase("D") && !Criterium.equalsIgnoreCase("M") && !Criterium.equalsIgnoreCase("Y"));

        ModView.ShowMessage('-',"Do you want to apply the extraction to all the Row? [Y/N]");
        String row = ModView.GetName();
        boolean ExtractRow = false;
        boolean ExtractColumn = false;
        if (row.equalsIgnoreCase("n")) {
            ModView.ShowMessage('-',"Do you want to apply the extraction to all the Column? [Y/N]");
            String col = ModView.GetName();
            if (col.equalsIgnoreCase("y")) {
                ExtractColumn = true;
            }
        }
        else {
            ExtractRow = true;
        }

        int ErrorCode = ModController.ExtractElement(NameSheet,Row,Col,ExtractRow, ExtractColumn, Criterium);
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "The element of the cell has been extracted successfully"); break;
            case -1: ModView.ShowMessage('*', "The element of the cell has not been extracted due to an error"); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a solicitar el dia de la semana de la fecha de la celda,
     * el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @param Row fila de la celda donde se hará la modificación
     * @param Col columna de la celda donde se hará la modificación
     * @throws Exception
     */
    private void DayOfTheWeek(String NameSheet, int Row, int Col) throws Exception {
        ModView.ShowMessage('-',"Day of the week of a cell:");
        ModView.ShowMessage('-',"Do you want to apply the day of the cell of all the Row? [Y/N]");
        String row = ModView.GetName();
        boolean DayRow = false;
        boolean DayColumn = false;
        if (row.equalsIgnoreCase("n")) {
            ModView.ShowMessage('-',"Do you want to apply the day of the cells of all the Column? [Y/N]");
            String col = ModView.GetName();
            if (col.equalsIgnoreCase("y")) {
                DayColumn = true;
            }
        }
        else {
            DayRow = true;
        }
        int ErrorCode = ModController.DayOfTheWeek(NameSheet,Row,Col,DayRow, DayColumn);
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "The Cell has been modified successfully"); break;
            case -1: ModView.ShowMessage('*', "The cell has not been modified due to an error"); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
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
        ModView.ShowMessage('-',"Numeric operations of a cell:");
        ModView.ShowMessage('-',"Tell me what operation you want to do:");
        int option = -1;
        while (option != 0) {
            option = ModView.GetOptionOperationDate();
            switch (option) {
                case 1:  ElementExtraction(NameSheet, Row, Col); break;
                case 2:  DayOfTheWeek(NameSheet, Row, Col); break;
                default: break;

            }
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a reemplazar el contenido de las celdas
     * con un contenido determinado por otro,
     * el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @throws Exception
     */
    private void Replace(String NameSheet) throws Exception {
        ModView.ShowMessage('-',"Replacement of a String:");
        ModView.ShowMessage('-',"Tell me the element you want to replace: ");
        String ElementToReplace = ModView.GetName();
        ModView.ShowMessage('-',"Tell me what do you want to put in the cell: ");
        String ElementReplaced = ModView.GetName();

        int ErrorCode = ModController.Replace(NameSheet, ElementToReplace, ElementReplaced);

        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "The replacement has been modified successfully"); break;
            case -1: ModView.ShowMessage('*', "The replacement has not been modified due to an error"); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
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
        ModView.ShowMessage('-',"Search of a String:");
        ModView.ShowMessage('-',"Tell me the element you want to search: ");
        String SearchS = ModView.GetName();

        int ErrorCode = ModController.Search(NameSheet, SearchS);

        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "The replacement has been modified successfully"); break;
            case -1: ModView.ShowMessage('*', "The replacement has not been modified due to an error"); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a consultar la longitud del texto de una celda,
     * el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @param NameSheet nombre de la hoja en la que se hará la operación
     * @throws Exception
     */
  private void Size(String NameSheet) throws Exception {
      ModView.ShowMessage('-',"Size of the cell:");
      ModView.ShowMessage('-',"Where do you want to put the size of the cell:");
      int Row = ModView.GetInt();
      int Col = ModView.GetInt();

      int ErrorCode = ModController.Size(NameSheet,Row, Col);
      switch (ErrorCode) {
          case 0: ModView.ShowMessage('-', "The Cell has been modified successfully"); break;
          case -1: ModView.ShowMessage('*', "The cell has not been modified due to an error"); break;
          default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
      }
  }

    /**
     * Método que dependiendo de la opción escogida por el usuario llama a funciones de operaciones de texto en una celda
     * (reemplazar, longitud del contenido de la celda)
     * @param NameSheet nombre de la hoja
     * @throws  Exception
     */
    private void OperationText(String NameSheet) throws Exception {
        ModView.ShowMessage('-',"Text operations of a cell:");
        ModView.ShowMessage('-',"Tell me what operation you want to do:");
        int option = -1;
        while (option != 0) {
            option = ModView.GetOptionOperationText();
            switch (option) {
                case 1:  Replace(NameSheet); break;
                case 2:  Size(NameSheet); break;
                case 3:  Search(NameSheet); break;
                default: break;

            }
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a seleccionar una celda,
     * el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario.
     * El usuario puede escoger que hacer con la celda (Modificar celdas, operaciones de datos, fechas, texto)
     * @throws Exception
     */
    private void CellSelection() throws Exception {
        ModView.ShowMessage('-',"Select the Cell:");
        ModView.ShowMessage('-',"Tell me the name of the Sheet you want to select:");
        String NameSheet = ModView.GetName();
        ModView.ShowMessage('-',"Tell me the row and the column of the cell you want to select:");
        int RowCell = ModView.GetInt();
        int ColumnCell = ModView.GetInt();

        int ErrorCode = ModController.ExistsCell(NameSheet, RowCell, ColumnCell);

        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "Cell selected successfully"); break;
            case -1: ModView.ShowMessage('*', "The sheet with the name NameSheet doesn't exist"); break;
            case -2: ModView.ShowMessage('*', "The cell doesn't exist."); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
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
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se va a seleccionar una celda,
     * el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario.
     * El usuario puede escoger que hacer con la celda (Modificar celdas, operaciones de datos, fechas, texto)
     * @throws Exception
     */
    private void OperationsBetweenCells() throws Exception {
        ModView.ShowMessage('-',"OperationsBetweenCells:");
        ModView.ShowMessage('-',"Tell me the name of the Sheet you want to select:");
        String NameSheet = ModView.GetName();
        ModView.ShowMessage('-',"Tell me the row and the column of the first cell you want to do the operation:");
        int RowCell = ModView.GetInt();
        int ColumnCell = ModView.GetInt();
        ModView.ShowMessage('-',"Tell me the row and the column of the second cell you want to do the operation:");
        int RowCell2 = ModView.GetInt();
        int ColumnCell2 = ModView.GetInt();
        ModView.ShowMessage('-',"Tell me the row and the column of the cell you want the result:");
        int RowRes = ModView.GetInt();
        int ColRes = ModView.GetInt();
        String avoidError = ModView.GetName();
        ModView.ShowMessage('-',"Do you want to apply the operation to all the Row? [Y/N]");
        String row = ModView.GetName();
        boolean BoolRow = false;
        boolean BoolColumn = false;
        if (row.equalsIgnoreCase("n")) {
            ModView.ShowMessage('-',"Do you want to apply the operation to all the Column? [Y/N]");
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
            case 0: ModView.ShowMessage('-', "Cell selected successfully"); break;
            case -1: ModView.ShowMessage('*', "The sheet with the name NameSheet doesn't exist"); break;
            case -2: ModView.ShowMessage('*', "The cell doesn't exist."); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
        if (ErrorCode == 0) {
            switch (ErrorCode2) {
                case 0:
                    ModView.ShowMessage('-', "Cell2 selected successfully"); break;
                case -1:
                    ModView.ShowMessage('*', "The sheet with the name NameSheet doesn't exist");break;
                case -2:
                    ModView.ShowMessage('*', "The cell2 doesn't exist."); break;
                default:
                    ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
            }

        }
        if (ErrorCode == 0 && ErrorCode2 == 0) {
            switch (ErrorCode3) {
                case 0:
                    ModView.ShowMessage('-', "Cell selected successfully");
                    break;
                case -1:
                    ModView.ShowMessage('*', "The sheet with the name NameSheet doesn't exist");
                    break;
                case -2:
                    ModView.ShowMessage('*', "The result cell  doesn't exist.");
                    break;
                default:
                    ModView.ShowMessage('*', "Unknown Error " + ErrorCode);
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
        }
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
        ModView.ShowMessage('-',"Addition between to cells:");

        int ErrorCode = ModController.Sum(NameSheet,RowCell,ColumnCell,RowCell2, ColumnCell2, RowRes, ColRes,BoolRow,BoolCol);
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "The operation has been made successfully"); break;
            case -1: ModView.ShowMessage('*', "The operation hasn't been done due to an error"); break;
            case -2: ModView.ShowMessage('*', "The Cell1 doesn't exist"); break;
            case -3: ModView.ShowMessage('*', "The Cell2 doesn't exist"); break;
            case -4: ModView.ShowMessage('*', "The ResultCell doesn't exist"); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
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
        ModView.ShowMessage('-',"Substraction between to cells:");

        int ErrorCode = ModController.Rest(NameSheet,RowCell,ColumnCell,RowCell2, ColumnCell2, RowRes, ColRes,BoolRow,BoolCol);
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "The operation has been made successfully"); break;
            case -1: ModView.ShowMessage('*', "The operation hasn't been done due to an error"); break;
            case -2: ModView.ShowMessage('*', "The Cell1 doesn't exist"); break;
            case -3: ModView.ShowMessage('*', "The Cell2 doesn't exist"); break;
            case -4: ModView.ShowMessage('*', "The ResultCell doesn't exist"); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
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
        ModView.ShowMessage('-',"Multiplication between to cells:");

        int ErrorCode = ModController.Multiplication(NameSheet,RowCell,ColumnCell,RowCell2, ColumnCell2, RowRes, ColRes,BoolRow,BoolCol);
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "The operation has been made successfully"); break;
            case -1: ModView.ShowMessage('*', "The operation hasn't been done due to an error"); break;
            case -2: ModView.ShowMessage('*', "The Cell1 doesn't exist"); break;
            case -3: ModView.ShowMessage('*', "The Cell2 doesn't exist"); break;
            case -4: ModView.ShowMessage('*', "The ResultCell doesn't exist"); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
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
        ModView.ShowMessage('-',"Division between to cells:");

        int ErrorCode = ModController.Division(NameSheet,RowCell,ColumnCell,RowCell2, ColumnCell2, RowRes, ColRes,BoolRow,BoolCol);
        switch (ErrorCode) {
            case 0: ModView.ShowMessage('-', "The operation has been made successfully"); break;
            case -1: ModView.ShowMessage('*', "The operation hasn't been done due to an error"); break;
            case -2: ModView.ShowMessage('*', "The Cell1 doesn't exist"); break;
            case -3: ModView.ShowMessage('*', "The Cell2 doesn't exist"); break;
            case -4: ModView.ShowMessage('*', "The ResultCell doesn't exist"); break;
            default: ModView.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
    }

    /**
     * Envia al controlador de modificación de dominio que se van a mostrar las celdas por pantalla
     * y el controlador de modificación de vista envía los mensajes que se verán por pantalla
     * y recoge la información que le dará el usuario
     * @throws Exception
     */
    private void Print() throws  Exception {
        ModView.ShowMessage('-',"Printing");
        ModView.ShowMessage('-',"Tell me the name of the sheet you want to print:");
        String NameSheet = ModView.GetName();

        Vector<String> Cells = new Vector<>();
        Cells = ModController.Print(NameSheet);
        if (Cells.isEmpty()) {
            ModView.ShowMessage('-', "The Sheet doesn't exist");
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
        }
    }

    /**
     * Método que dependiendo de la opción escogida por el usuario llama a funciones
     * (crear hoja, cambiar nombre de la hoja, borrar hoja, ver la lista de hojas, añadir fila,
     * añadir columna, borrar fila, borrar columna, seleccionar rango, seleccionar celda)
     * @param index posición del documento a modificar
     * @throws  Exception
     */
    public void ini(int index) throws Exception {
        ModController.setMyDocument(index);
        int option = -1;
        while(option != 0) {
            option = ModView.GetOption();
            switch(option) {
                case 1: CreateSheet(); break;
                case 2: ChangeNameSheet(); break;
                case 3: EraseSheet(); break;
                case 4: getSheetList(); break;
                case 5: AddRow(); break;
                case 6: AddColumn(); break;
                case 7: EraseRow(); break;
                case 8: EraseColumn(); break;
                case 9: RankSelection(); break;
                case 10: CellSelection(); break;
                case 11: OperationsBetweenCells(); break;
                case 12: Print(); break;
                default: break;
            }
        }
    }
}
