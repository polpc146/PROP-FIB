package Views;

import PresentationControllers.ModifyPresentationCtrl;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Javier Núñez
 */
public class SheetPane extends JPanel {
    private SheetView view;
    private TableModel tM;
    private JTable tabla;



    public SheetPane(String nameSheet, SheetView view, ModifyPresentationCtrl controller) {
        this.view = view;
        setLayout(new BorderLayout());


        tM = new TableModel(controller, nameSheet, this);
        tabla = new JTable(tM);
        // La tabla se añade a un ScrollPane para que sea éste el
        // que controle automáticamente en tamaño de la tabla,
        // presentando una barra de desplazamiento cuando sea
        // necesario
        JScrollPane panel = new JScrollPane(tabla);

        tabla.setAutoResizeMode(0);
        tabla.getTableHeader().setReorderingAllowed(false);
        add(panel, BorderLayout.CENTER);


    }

    public String getSheetName() {
        return view.getSheetActivated();
    }

    private boolean errorCellNotSelected() {
        if (tabla.getSelectedColumn() ==-1) {
            ErrorView eV = new ErrorView();
            eV.error("Cell not selected");
            return true;
        }
        else {return false;}
    }

    public void addCol(int numC) throws Exception{
        if (!errorCellNotSelected()) {
            tM.addCol(numC, tabla.getSelectedColumn());
        }
    }

    public void addRow(int numR) throws Exception {
        if (!errorCellNotSelected()) {
            tM.addRow(numR, tabla.getSelectedRow());
        }
    }

    public void eraseCol(int numC) throws Exception {
        if (!errorCellNotSelected()) {
            tM.eraseCol(numC, tabla.getSelectedColumn());
        }
    }

    public void eraseRow(int numR) throws Exception {
        if (!errorCellNotSelected()) {
            tM.eraseRow(numR, tabla.getSelectedRow());
        }
    }

    public void truncate(boolean row, boolean col) throws Exception {
        if (!errorCellNotSelected()) {
            tM.truncate(row, col, tabla.getSelectedRow(), tabla.getSelectedColumn());
        }
    }

    public void absolute(boolean row, boolean col) {
        if (!errorCellNotSelected()) {
            tM.absolute(row, col, tabla.getSelectedRow(), tabla.getSelectedColumn());
        }
    }

    public void increment(boolean row, boolean col){
        if (!errorCellNotSelected()) {
            tM.increment(row, col, tabla.getSelectedRow(), tabla.getSelectedColumn());
        }
    }

    public void binary(boolean row, boolean col) {
        if (!errorCellNotSelected()) {
            tM.conversion(row, col, tabla.getSelectedRow(), tabla.getSelectedColumn(), "binary");
        }
    }

    public void hexa(boolean row, boolean col) {
        if (!errorCellNotSelected()) {
            tM.conversion(row, col, tabla.getSelectedRow(), tabla.getSelectedColumn(), "hexa");
        }
    }

    public void day(boolean row, boolean col) throws Exception {
        if (!errorCellNotSelected()) {
            tM.elementExtraction(row, col, tabla.getSelectedRow(), tabla.getSelectedColumn(), "D");
        }
    }

    public void month(boolean row, boolean col) throws Exception {
        if (!errorCellNotSelected()) {
            tM.elementExtraction(row, col, tabla.getSelectedRow(), tabla.getSelectedColumn(), "M");
        }
    }

    public void year(boolean row, boolean col) throws Exception {
        if (!errorCellNotSelected()) {
            tM.elementExtraction(row, col, tabla.getSelectedRow(), tabla.getSelectedColumn(), "Y");
        }
    }

    public void dayOfWeek(boolean row, boolean col) throws Exception {
        if (!errorCellNotSelected()) {
            tM.dayOfWeek(row, col, tabla.getSelectedRow(), tabla.getSelectedColumn());
        }
    }


    public void stringSize(boolean row, boolean col) throws Exception {
        if (!errorCellNotSelected()) {
            tM.stringSize(row, col, tabla.getSelectedRow(), tabla.getSelectedColumn());
        }
    }

    public void average(int rowI, int colI, int rowF, int colF) throws Exception {
        if (!errorCellNotSelected()) {
            tM.average(rowI, colI, rowF, colF, tabla.getSelectedRow(), tabla.getSelectedColumn());
        }
    }

    public void median(int rowI, int colI, int rowF, int colF) throws Exception {
        if (!errorCellNotSelected()) {
            tM.median(rowI, colI, rowF, colF, tabla.getSelectedRow(), tabla.getSelectedColumn());
        }
    }

    public void variance(int rowI, int colI, int rowF, int colF) throws Exception {
        if (!errorCellNotSelected()) {
            tM.variance(rowI, colI, rowF, colF, tabla.getSelectedRow(), tabla.getSelectedColumn());
        }
    }

    public void covariance(int rowI, int colI, int rowF, int colF, int rowI2, int colI2, int rowF2, int colF2) throws Exception {
        if (!errorCellNotSelected()) {
            tM.covariance(rowI, colI, rowF, colF, rowI2, colI2, rowF2, colF2, tabla.getSelectedRow(), tabla.getSelectedColumn());
        }
    }

    public void standardDeviation(int rowI, int colI, int rowF, int colF) throws Exception {
        if (!errorCellNotSelected()) {
            tM.standardDeviation(rowI, colI, rowF, colF, tabla.getSelectedRow(), tabla.getSelectedColumn());
        }
    }

    public void pearson(int rowI, int colI, int rowF, int colF, int rowI2, int colI2, int rowF2, int colF2) throws Exception {
        if (!errorCellNotSelected()) {
            tM.pearson(rowI, colI, rowF, colF, rowI2, colI2, rowF2, colF2, tabla.getSelectedRow(), tabla.getSelectedColumn());
        }
    }

    public int getSelectedRow(){return tabla.getSelectedRow();};
    public int getSelectedCol(){return tabla.getSelectedColumn();}

    public void Search(int fila, int col) {
        tabla.changeSelection(fila,col,false,false);
    }
}
