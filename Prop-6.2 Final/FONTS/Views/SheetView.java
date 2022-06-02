package Views;

import PresentationControllers.ModifyPresentationCtrl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Vector;

/**
 * Vista de la hoja de cálculo del programa
 * @author Pol Pérez Castillo
 */
public class SheetView extends JFrame {

    private ModifyPresentationCtrl modifyPresentationCtrl;
    private DocumentView documentView;

    private JPanel northPanel;
    private JPanel centerPanel;
    private JPanel eastPanel;
    private JPanel southPanel;

    private SheetMenuBarView menuBar;
    private SheetListView listView;
    private Vector<SheetPane> sheetPanes;
    private SheetOpButtons opButtons;

    /**
     * Constructora de la vista
     * @param modifyPresentationCtrl controlador de presentación que interactúa con la vista
     * @param documentView vista principal del programa
     * @throws Exception
     */
    public SheetView (ModifyPresentationCtrl modifyPresentationCtrl, DocumentView documentView) throws Exception {

        this.modifyPresentationCtrl = modifyPresentationCtrl;
        this.documentView = documentView;

        Toolkit myScreen = Toolkit.getDefaultToolkit();
        Dimension sizeScreen = myScreen.getScreenSize();
        int height = sizeScreen.height;
        int width = sizeScreen.width;
        setBounds(0,0,width, height-40);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {

                int selected = closeWindow();
                if (selected == 0) {

                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    try {
                        save();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    super.windowClosing(e);
                    documentView.setVisible();
                }
                else if (selected == 1) {

                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    super.windowClosing(e);
                    documentView.setVisible();
                }
            }
        });

        northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        menuBar = new SheetMenuBarView(this);
        northPanel.add(menuBar);
        add(northPanel, BorderLayout.NORTH);

        listView = new SheetListView(this);
        eastPanel = listView;
        add(eastPanel, BorderLayout.EAST);

        centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        sheetPanes = new Vector<>();
        int size = listView.getListSize();
        for (int i = 0; i < size; i++) sheetPanes.add(i, new SheetPane( listView.getString(i),this, modifyPresentationCtrl));
        centerPanel = sheetPanes.get(0);
        add(centerPanel, BorderLayout.CENTER);

        opButtons = new SheetOpButtons(this);
        southPanel = opButtons;
        add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * Muestra por pantalla la vista
     * @param docName
     */
    public void setVisible(String docName) {

        setTitle(docName);
        setVisible(true);
    }

    /**
     * Opciones de cerrado de la vista
     * @return opción seleccionada
     */
    public int closeWindow() {

        String[] options = {"Save and exit", "Exit", "Cancel"};
        return JOptionPane.showOptionDialog(null, "Closing...", null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }

    /**
     * Crea una hoja
     * @throws Exception
     */
    public void newSheet() throws Exception {

        modifyPresentationCtrl.CreateSheet();
        listView.newSheet();
        int size = listView.getListSize();
        sheetPanes.add(size -1, new SheetPane( listView.getString(size-1),this, modifyPresentationCtrl));
    }

    /**
     * Cambia el nombre de la hoja
     * @throws Exception
     */
    public void changeSheet() throws Exception {

        String nameSheet = listView.getSelected();
        int index = listView.getSelectedIndex();
        String NewName = JOptionPane.showInputDialog("New name of the sheet:");
        modifyPresentationCtrl.ChangeNameSheet(nameSheet, NewName);
        listView.changeSheet(index, NewName);
    }

    /**
     * Consulta la lista de hojas
     * @return lista de hojas
     * @throws Exception
     */
    public Vector<String> getSheetList() throws Exception {

        return modifyPresentationCtrl.getSheetList();
    }

    /**
     * Pregunta y elimina una hoja
     * @throws Exception
     */
    public void eraseSheet() throws Exception {

        int answer = JOptionPane.showConfirmDialog(null, "Are you sure? You will lose the sheet.", "WARNING", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {

            String nameSheet = listView.getSelected();
            int index = listView.getSelectedIndex();
            modifyPresentationCtrl.EraseSheet(nameSheet);
            listView.eraseSheet(index);
            sheetPanes.remove(index);
        }
    }

    /**
     * Consulta la hoja seleccionada
     * @return
     */
    public String getSheetActivated() {

        return listView.getSelected();
    }

    /**
     * Cambio de paneles activos
     * @param index panel a activar
     */
    public void changingSheets(int index) {

        if (index >= 0) {

            remove(centerPanel);
            centerPanel = sheetPanes.get(index);
            add(centerPanel, BorderLayout.CENTER);
            centerPanel.revalidate();
            centerPanel.repaint();
        }
    }

    /**
     * Añadir filas
     * @param numRows número de filas
     * @throws Exception
     */
    public void addRow(int numRows) throws Exception {

        int index = listView.getSelectedIndex();
        sheetPanes.get(index).addRow(numRows);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    /**
     * Eliminar filas
     * @param numRows número de filas
     * @throws Exception
     */
    public void remRow(int numRows) throws Exception {

        int index = listView.getSelectedIndex();
        sheetPanes.get(index).eraseRow(numRows);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    /**
     * Añadir columnas
     * @param numCols número de columnas
     * @throws Exception
     */
    public void addCol(int numCols) throws Exception {

        int index = listView.getSelectedIndex();
        sheetPanes.get(index).addCol(numCols);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    /**
     * Eliminar columnas
     * @param numCols número de columnas
     * @throws Exception
     */
    public void remCol(int numCols) throws Exception {

        int index = listView.getSelectedIndex();
        sheetPanes.get(index).eraseCol(numCols);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    /**
     * Guardar documento
     * @throws IOException
     */
    public void save() throws IOException {

        modifyPresentationCtrl.save();
    }

    /**
     * Trunca el valor de la celda
     * @param row true si aplicar en la fila
     * @param col true si aplicar en la columna
     * @throws Exception
     */
    public void truncate(boolean row, boolean col) throws Exception {

        int index = listView.getSelectedIndex();
        sheetPanes.get(index).truncate(row, col);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    /**
     * Valor absoluto de la celda
     * @param row true si aplicar en la fila
     * @param col true si aplicar en la columna
     */
    public void absolute(boolean row, boolean col) {

        int index = listView.getSelectedIndex();
        sheetPanes.get(index).absolute(row, col);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    /**
     * Incrementa el valor de la celda
     * @param row true si aplicar en la fila
     * @param col true si aplicar en la columna
     */
    public void increment(boolean row, boolean col) {

        int index = listView.getSelectedIndex();
        sheetPanes.get(index).increment(row, col);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    /**
     * Convierte a binario el valor de la celda
     * @param row true si aplicar en la fila
     * @param col true si aplicar en la columna
     */
    public void binary(boolean row, boolean col) {

        int index = listView.getSelectedIndex();
        sheetPanes.get(index).binary(row, col);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    /**
     * Convierte a hexadecimal el valor de la celda
     * @param row true si aplicar en la fila
     * @param col true si aplicar en la columna
     */
    public void hexa(boolean row, boolean col) {

        int index = listView.getSelectedIndex();
        sheetPanes.get(index).hexa(row, col);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    /**
     * Dia de una celda fecha
     * @param row true si aplicar en la fila
     * @param col true si aplicar en la columna
     */
    public void getDay(boolean row, boolean col) throws Exception {

        int index = listView.getSelectedIndex();
        sheetPanes.get(index).day(row, col);
        centerPanel.revalidate();
        centerPanel.repaint();
    }


    /**
     * Mes de una celda fecha
     * @param row true si aplicar en la fila
     * @param col true si aplicar en la columna
     */
    public void getMonth(boolean row, boolean col) throws Exception {


        int index = listView.getSelectedIndex();
        sheetPanes.get(index).month(row, col);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    /**
     * Año de una celda fecha
     * @param row true si aplicar en la fila
     * @param col true si aplicar en la columna
     */
    public void getYear(boolean row, boolean col) throws Exception {

        int index = listView.getSelectedIndex();
        sheetPanes.get(index).year(row, col);
        centerPanel.revalidate();
        centerPanel.repaint();
    }


    /**
     * Dia de la semana de una celda fecha
     * @param row true si aplicar en la fila
     * @param col true si aplicar en la columna
     */
    public void dayOfWeek(boolean row, boolean col) throws Exception {

        int index = listView.getSelectedIndex();
        sheetPanes.get(index).dayOfWeek(row, col);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    /**
     * Remplaza elementos
     * @param ElementToReplace elemento a remplazar
     * @param ElementReplaced nuevo elemento
     * @throws Exception
     */
    public void Replace (String ElementToReplace, String ElementReplaced) throws Exception {

        String nameSheet = listView.getSelected();
        modifyPresentationCtrl.Replace(nameSheet, ElementToReplace, ElementReplaced);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    /**
     * Tamaño de una string
     * @throws Exception
     */
    public void stringSize(boolean row, boolean col) throws Exception {

        int index = listView.getSelectedIndex();
        sheetPanes.get(index).stringSize(row, col);
        centerPanel.revalidate();
        centerPanel.repaint();
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

    public void average (String cell1, String cell2) throws Exception {

        int rowI = Integer.parseInt(cell1.substring(1));
        int colI = colToInt(cell1.substring(0,1));
        int rowF = Integer.parseInt(cell2.substring(1));
        int colF = colToInt(cell2.substring(0,1));
        int index = listView.getSelectedIndex();
        sheetPanes.get(index).average(rowI, colI, rowF, colF);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    public void median (String cell1, String cell2) throws Exception {

        int rowI = Integer.parseInt(cell1.substring(1));
        int colI = colToInt(cell1.substring(0,1));
        int rowF = Integer.parseInt(cell2.substring(1));
        int colF = colToInt(cell2.substring(0,1));
        int index = listView.getSelectedIndex();
        sheetPanes.get(index).median(rowI, colI, rowF, colF);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    public void variance (String cell1, String cell2) throws Exception {

        int rowI = Integer.parseInt(cell1.substring(1));
        int colI = colToInt(cell1.substring(0,1));
        int rowF = Integer.parseInt(cell2.substring(1));
        int colF = colToInt(cell2.substring(0,1));
        int index = listView.getSelectedIndex();
        sheetPanes.get(index).variance(rowI, colI, rowF, colF);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    public void covariance (String cell1, String cell2, String cell3, String cell4) throws Exception {

        int rowI = Integer.parseInt(cell1.substring(1));
        int colI = colToInt(cell1.substring(0,1));
        int rowF = Integer.parseInt(cell2.substring(1));
        int colF = colToInt(cell2.substring(0,1));
        int rowI2 = Integer.parseInt(cell3.substring(1));
        int colI2 = colToInt(cell3.substring(0,1));
        int rowF2 = Integer.parseInt(cell4.substring(1));
        int colF2 = colToInt(cell4.substring(0,1));
        int index = listView.getSelectedIndex();
        sheetPanes.get(index).covariance(rowI, colI, rowF, colF, rowI2, colI2, rowF2, colF2);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    public void sd (String cell1, String cell2) throws Exception {

        int rowI = Integer.parseInt(cell1.substring(1));
        int colI = colToInt(cell1.substring(0,1));
        int rowF = Integer.parseInt(cell2.substring(1));
        int colF = colToInt(cell2.substring(0,1));
        int index = listView.getSelectedIndex();
        sheetPanes.get(index).standardDeviation(rowI, colI, rowF, colF);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    public void pearson (String cell1, String cell2, String cell3, String cell4) throws Exception {

        int rowI = Integer.parseInt(cell1.substring(1));
        int colI = colToInt(cell1.substring(0,1));
        int rowF = Integer.parseInt(cell2.substring(1));
        int colF = colToInt(cell2.substring(0,1));
        int rowI2 = Integer.parseInt(cell3.substring(1));
        int colI2 = colToInt(cell3.substring(0,1));
        int rowF2 = Integer.parseInt(cell4.substring(1));
        int colF2 = colToInt(cell4.substring(0,1));
        int index = listView.getSelectedIndex();
        sheetPanes.get(index).pearson(rowI, colI, rowF, colF, rowI2, colI2, rowF2, colF2);
        centerPanel.revalidate();
        centerPanel.repaint();
    }
}
