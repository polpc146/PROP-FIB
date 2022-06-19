package Views;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Vector;

/**
 * Componente para seleccionar la hoja activa
 * @author Pol Pérez Castillo
 */
public class SheetListView extends JPanel {

    private SheetView sheetView;

    private DefaultListModel model;
    private JList sheetList;

    private Vector<String> list = new Vector<>();

    /**
     * Constructora del componente
     * @param sheetView vista de la hoja de cálculo
     * @throws Exception
     */
    public SheetListView(SheetView sheetView) throws Exception {

        this.sheetView = sheetView;

        setLayout(new FlowLayout(FlowLayout.LEFT));

        model = new DefaultListModel<>();
        sheetList = new JList<>(model);
        try {
            list = sheetView.getSheetList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //Vector<String> list = sheetView.getSheetList();
        if (list != null) {

            for (int i = 0; i < list.size(); ++i) model.add(i, list.get(i));
        }
        sheetList.setVisibleRowCount(10);
        sheetList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sheetList.setSelectedIndex(0);
        JScrollPane scrollPane = new JScrollPane(sheetList);
        add(scrollPane);

        ListSelectionListener changingSheets = new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {

                sheetView.changingSheets(sheetList.getSelectedIndex());
            }
        };
        sheetList.addListSelectionListener(changingSheets);
    }

    /**
     * Añadir una hoja
     * @throws Exception
     */
    public void newSheet() throws Exception {

        list = sheetView.getSheetList();
        if (list != null) {

            model.add(list.size()-1, list.get(list.size()-1));
        }
    }

    /**
     * Consulta hoja seleccionada
     * @return hoja seleccionada
     */
    public String getSelected() {

        return (String) sheetList.getSelectedValue();
    }

    /**
     * Consulta índice dela hoja seleccionada
     * @return hoja seleccionada
     */
    public int getSelectedIndex() {

        return sheetList.getSelectedIndex();
    }

    /**
     * Cambio de nombre de la hoja
     * @param index posición de la hoja
     * @param NewSheet nuevo nombre
     */
    public void changeSheet(int index, String NewSheet) {

        model.set(index, NewSheet);
    }

    /**
     * Eliminar una hoja
     * @param index posición de la hoja
     */
    public void eraseSheet(int index) {

        if (model.getSize() > 1) model.remove(index);
    }

    /**
     * Consulta del tamaño de la lista de hojas
     * @return tamaño
     */
    public int getListSize() {

        return model.getSize();
    }

    /**
     * Consulta del nombre de la hoja según un índice
     * @param index índice de la hoja
     * @return nombre de la hoja
     */
    public String getString (int index) {

        return (String) model.get(index);
    }
}
