package Views;

import PresentationControllers.ModifyPresentationCtrl;
import Structure.Cells.Cell;
import Structure.Cells.FormulaCell;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;

/**
 * El Modelo de la Tabla es el que controla los
 * datos que se colocan en ella
 * @author Javier Núñez
 */
class TableModel extends AbstractTableModel {
    private ModifyPresentationCtrl controller;
    private SheetPane sP;

    //Operacions amb botons
    public void truncate(boolean extendRow, boolean extendCol, int selectedRow, int selectedColumn) throws Exception {
        if (extendCol){
            for (int i=0; i<getRowCount(); ++i){
                controller.truncate(sP.getSheetName(),i,selectedColumn);
                fireTableCellUpdated(i, selectedColumn);
            }
        }
        else if (extendRow){
            for (int i=0; i<getColumnCount(); ++i){
                controller.truncate(sP.getSheetName(),selectedRow,i);
                fireTableCellUpdated(selectedRow,i);
            }
        }
        else{
            controller.truncate(sP.getSheetName(),selectedRow,selectedColumn);
            fireTableCellUpdated(selectedRow,selectedColumn);
        }
    }

    public void increment(boolean extendRow, boolean extendCol, int selectedRow, int selectedColumn){
        if (extendCol){
            for (int i=0; i<getRowCount(); ++i){
                controller.increase(sP.getSheetName(),i,selectedColumn);
                fireTableCellUpdated(i, selectedColumn);
            }
        }
        else if (extendRow){
            for (int i=0; i<getColumnCount(); ++i){
                controller.increase(sP.getSheetName(),selectedRow,i);
                fireTableCellUpdated(selectedRow,i);
            }
        }
        else{
            controller.increase(sP.getSheetName(),selectedRow,selectedColumn);
            fireTableCellUpdated(selectedRow,selectedColumn);
        }
    }

    public void absolute(boolean extendRow, boolean extendCol, int selectedRow, int selectedColumn) {
        if (extendCol){
            for (int i=0; i<getRowCount(); ++i){
                controller.absolute(sP.getSheetName(),i,selectedColumn);
                fireTableCellUpdated(i, selectedColumn);
            }
        }
        else if (extendRow){
            for (int i=0; i<getColumnCount(); ++i){
                controller.absolute(sP.getSheetName(),selectedRow,i);
                fireTableCellUpdated(selectedRow,i);
            }
        }
        else{
            controller.absolute(sP.getSheetName(),selectedRow,selectedColumn);
            fireTableCellUpdated(selectedRow,selectedColumn);
        }
    }

    public void conversion(boolean extendRow, boolean extendCol, int selectedRow, int selectedColumn, String criterium) {
        if (extendCol){
            for (int i=0; i<getRowCount(); ++i){
                controller.conversion(sP.getSheetName(),i,selectedColumn,criterium);
                fireTableCellUpdated(i, selectedColumn);
            }
        }
        else if (extendRow){
            for (int i=0; i<getColumnCount(); ++i){
                controller.conversion(sP.getSheetName(),selectedRow,i, criterium);
                fireTableCellUpdated(selectedRow,i);
            }
        }
        else{
            controller.conversion(sP.getSheetName(),selectedRow,selectedColumn, criterium);
            fireTableCellUpdated(selectedRow,selectedColumn);
        }
    }

    public void elementExtraction(boolean extendRow, boolean extendCol, int selectedRow, int selectedColumn, String criterium) throws Exception {
        if (extendCol){
            for (int i=0; i<getRowCount(); ++i){
                controller.elementExtraction(sP.getSheetName(),i,selectedColumn,criterium);
                fireTableCellUpdated(i, selectedColumn);
            }
        }
        else if (extendRow){
            for (int i=0; i<getColumnCount(); ++i){
                controller.elementExtraction(sP.getSheetName(),selectedRow,i, criterium);
                fireTableCellUpdated(selectedRow,i);
            }
        }
        else{
            controller.elementExtraction(sP.getSheetName(),selectedRow,selectedColumn, criterium);
            fireTableCellUpdated(selectedRow,selectedColumn);
        }
    }

    public void dayOfWeek(boolean extendRow, boolean extendCol, int selectedRow, int selectedColumn) throws Exception {
        if (extendCol){
            for (int i=0; i<getRowCount(); ++i){
                controller.dayOfTheWeek(sP.getSheetName(),i,selectedColumn);
                fireTableCellUpdated(i, selectedColumn);
            }
        }
        else if (extendRow){
            for (int i=0; i<getColumnCount(); ++i){
                controller.dayOfTheWeek(sP.getSheetName(),selectedRow,i);
                fireTableCellUpdated(selectedRow,i);
            }
        }
        else{
            controller.dayOfTheWeek(sP.getSheetName(),selectedRow,selectedColumn);
            fireTableCellUpdated(selectedRow,selectedColumn);
        }

    }

    public void stringSize(boolean extendRow, boolean extendCol, int selectedRow, int selectedColumn) throws Exception {
        if (extendCol){
            for (int i=0; i<getRowCount(); ++i){
                controller.size(sP.getSheetName(),i,selectedColumn);
                fireTableCellUpdated(i, selectedColumn);
            }
        }
        else if (extendRow){
            for (int i=0; i<getColumnCount(); ++i){
                controller.size(sP.getSheetName(),selectedRow,i);
                fireTableCellUpdated(selectedRow,i);
            }
        }
        else{
            controller.size(sP.getSheetName(),selectedRow,selectedColumn);
            fireTableCellUpdated(selectedRow,selectedColumn);
        }
    }

    public void average(int rowI, int colI, int rowF, int colF, int selectedRow, int selectedColumn) throws Exception {
        controller.average(sP.getSheetName(), selectedRow, selectedColumn, rowI,colI, rowF, colF);
    }

    public void standardDeviation(int rowI, int colI, int rowF, int colF, int selectedRow, int selectedColumn) throws Exception {
        controller.standardDeviation(sP.getSheetName(),selectedRow,selectedColumn,rowI,colI,rowF,colF);
    }

    public void median(int rowI, int colI, int rowF, int colF, int selectedRow, int selectedColumn) throws Exception {
        controller.median(sP.getSheetName(),selectedRow,selectedColumn,rowI,colI,rowF,colF);
    }

    public void variance(int rowI, int colI, int rowF, int colF, int selectedRow, int selectedColumn) throws Exception {
        controller.variance(sP.getSheetName(),selectedRow,selectedColumn,rowI,colI,rowF,colF);
    }

    public void covariance(int rowI, int colI, int rowF, int colF, int rowI2, int colI2, int rowF2, int colF2, int selectedRow, int selectedColumn) throws Exception {
        controller.covariance(sP.getSheetName(),selectedRow,selectedColumn,rowI,colI,rowF,colF,rowI2,colI2,rowF2,colF2);
    }

    public void pearson(int rowI, int colI, int rowF, int colF, int rowI2, int colI2, int rowF2, int colF2, int selectedRow, int selectedColumn) throws Exception {
        controller.pearson(sP.getSheetName(),selectedRow,selectedColumn,rowI,colI,rowF,colF,rowI2,colI2,rowF2,colF2);
    }


    /**
     * Detecta los cambios en la tabla i se comunica con el controlador para pasar los cambios a dominio
     */
    private class TableListener implements TableModelListener {
        public void tableChanged(TableModelEvent evt) {
        }
    }

    // Constructor
    public TableModel(ModifyPresentationCtrl controller, String sheetName, SheetPane sP) {

        this.sP = sP;
        this.controller = controller;
        addTableModelListener(new TableListener());
    }

    /**
     * @return número de columnas de la tabla
     */
    public int getColumnCount() {
        return (controller.getColumSize(sP.getSheetName()));
    }

    /**
     * @return número de filas de la tabla
     */
    public int getRowCount() {
        return (controller.getRowSize(sP.getSheetName()));
    }

    public Object getValueAt(int fila, int col) {

        Cell cell = controller.getCell(sP.getSheetName(), fila, col);
        if (cell.getFormula() != "noFormula" && sP.getSelectedRow()==fila && sP.getSelectedCol()==col){
            return cell.getFormula();
        }
        else {return (controller.getRawContent(sP.getSheetName(), fila, col));}
    }

    // Cambia el valor que contiene una determinada casilla de
    // la tabla
    public void setValueAt(Object valor, int fila, int col) {
        //get cell
        Cell cell = controller.getCell(sP.getSheetName(), fila, col);

        //get referencedBy
        LinkedList<FormulaCell> llistaDeReferencies = cell.getReferencedBy();

        //Modify cell
        try {controller.ModCell(sP.getSheetName(), fila, col, valor.toString());}
        catch (Exception e) {}

        //refresh (getContent+modcell)
        for (int i=0; i<llistaDeReferencies.size(); ++i){
            //Reescriu la formula a les cel·les que feien referencia
            FormulaCell cellF = llistaDeReferencies.get(i);
            String formula = cellF.getFormula();

            try {controller.ModCell(sP.getSheetName(), cellF.getRowPos(), cellF.getColPos(), formula);}
            catch (Exception e) {}
        }

        fireTableDataChanged();
    }

    // Indica si la casilla identificada por fila y columna es
    // editable
    public boolean isCellEditable(int fila, int col) {
        return (true);
    }

    /**
     * add a Column to the right of the selected cell
     */
    public void addCol(int numCol, int index) throws Exception {
        controller.AddColumn(sP.getSheetName(), numCol, index);
        fireTableStructureChanged();

        refreshFormulas();
    }

    /**
     * add a Row below the selected cell
     */
    public void addRow(int numRow, int index) throws Exception {
        controller.AddRow(sP.getSheetName(), numRow, index);
        fireTableRowsInserted(index, index+numRow);

        refreshFormulas();
    }

    public void eraseRow(int numRow, int index) throws Exception {
        controller.EraseRow(sP.getSheetName(), numRow, index);
        fireTableRowsDeleted(index, index+numRow);

        refreshFormulas();
    }
    public void eraseCol(int numCol, int index) throws Exception {
        controller.EraseColumn(sP.getSheetName(), numCol, index);
        fireTableStructureChanged();

        refreshFormulas();
    }

    /**
     * Se recorre la tabla y por cada celda de tipo formula se reescribe su contenido.
     */
    public void refreshFormulas(){
        for(int i=0; i<getRowCount(); ++i){
            for(int j=0; j<getColumnCount(); ++j){
                //Celda a actualizar
                Cell cell= controller.getCell(sP.getSheetName(),i,j);

                //si es formula
                if(cell.getFormula() != "noFormula"){
                    //actualiza la formula
                    cell.refreshFormula();
                    //se recalcula
                    try {controller.ModCell(sP.getSheetName(), i, j, cell.getFormula());} catch (Exception e) {}
                }
            }
        }
    }
}