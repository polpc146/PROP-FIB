package Views;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.*;
import java.io.IOException;

/**
 * Componente de la barra de herramientas de la hoja de cálculo
 * @author Pol Pérez Castillo
 */
public class SheetMenuBarView extends JMenuBar {

    private SheetView sheetView;

    /**
     * Constructora de la barra de herramientas
     * @param sheetView vista de la hoja de cálculo
     */
    public SheetMenuBarView(SheetView sheetView) {

        this.sheetView = sheetView;

        JMenu file = new JMenu("File");
        JMenuItem newSheet = new JMenuItem("Create a new sheet");
        JMenuItem changeName = new JMenuItem("Change the name of the sheet");
        JMenuItem eraseSheet = new JMenuItem("Erase the sheet");
        file.add(newSheet);
        file.add(changeName);
        file.add(eraseSheet);
        add(file);

        ActionListener NewSheet = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {
                    sheetView.newSheet();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        newSheet.addActionListener(NewSheet);

        ActionListener ChangeName = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {
                    sheetView.changeSheet();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        changeName.addActionListener(ChangeName);

        ActionListener EraseSheet = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {
                    sheetView.eraseSheet();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        eraseSheet.addActionListener(EraseSheet);

        JMenu save = new JMenu("Save");
        JMenuItem saveDoc = new JMenuItem("Save the document");
        save.add(saveDoc);
        add(save);

        ActionListener SaveDoc = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {
                    sheetView.save();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        };
        saveDoc.addActionListener(SaveDoc);

        JMenu editRow = new JMenu("Edit row");
        JMenuItem addRow = new JMenuItem("Add row/s");
        JMenuItem remRow = new JMenuItem("Remove row/s");
        editRow.add(addRow);
        editRow.add(remRow);
        add(editRow);

        ActionListener AddRow = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                int numrows = Integer.parseInt(JOptionPane.showInputDialog("How many rows?"));
                try {
                    sheetView.addRow(numrows);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        addRow.addActionListener(AddRow);

        ActionListener RemRow = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                int numrows = Integer.parseInt(JOptionPane.showInputDialog("How many rows?"));
                try {
                    sheetView.remRow(numrows);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        remRow.addActionListener(RemRow);

        JMenu editCol = new JMenu("Edit column");
        JMenuItem addCol = new JMenuItem("Add column/s");
        JMenuItem remCol = new JMenuItem("Remove column/s");
        editCol.add(addCol);
        editCol.add(remCol);
        add(editCol);

        ActionListener AddCol = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                int numCols = Integer.parseInt(JOptionPane.showInputDialog("How many columns?"));
                try {
                    sheetView.addCol(numCols);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        addCol.addActionListener(AddCol);

        ActionListener RemCol = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                int numCols = Integer.parseInt(JOptionPane.showInputDialog("How many columns?"));
                try {
                    sheetView.remCol(numCols);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        remCol.addActionListener(RemCol);

        JMenu textOp = new JMenu("Text operations");
        JMenuItem rep = new JMenuItem("Replace string/s");
        textOp.add(rep);
        add(textOp);

        ActionListener Replace = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String str1 = JOptionPane.showInputDialog("String/s to replace");
                String str2 = JOptionPane.showInputDialog("New string/s");
                try {
                    sheetView.Replace(str1,str2);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        rep.addActionListener(Replace);

        JMenu stadOp = new JMenu("Stadistic operations");
        JMenuItem ave = new JMenuItem("Average");
        JMenuItem med = new JMenuItem("Median");
        JMenuItem var = new JMenuItem("Variance");
        JMenuItem covar = new JMenuItem("Covariance");
        JMenuItem sd = new JMenuItem("Standard deviation");
        JMenuItem pear = new JMenuItem("Pearson");
        stadOp.add(ave);
        stadOp.add(med);
        stadOp.add(var);
        stadOp.add(covar);
        stadOp.add(sd);
        stadOp.add(pear);
        add(stadOp);

        ActionListener Average = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String cell1 = JOptionPane.showInputDialog("Initial rank cell. Example: d2");
                String cell2 = JOptionPane.showInputDialog("Final rank cell. Example: d2");
                try {
                    sheetView.average(cell1,cell2);
                } catch (Exception ex) {

                }
            }
        };
        ave.addActionListener(Average);

        ActionListener Median = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String cell1 = JOptionPane.showInputDialog("Initial rank cell. Example: d2");
                String cell2 = JOptionPane.showInputDialog("Final rank cell. Example: d2");
                try {
                    sheetView.median(cell1,cell2);
                } catch (Exception ex) {

                }
            }
        };
        med.addActionListener(Median);

        ActionListener Var = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String cell1 = JOptionPane.showInputDialog("Initial rank cell. Example: d2");
                String cell2 = JOptionPane.showInputDialog("Final rank cell. Example: d2");
                try {
                    sheetView.variance(cell1,cell2);
                } catch (Exception ex) {

                }
            }
        };
        var.addActionListener(Var);

        ActionListener Covar = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String cell1 = JOptionPane.showInputDialog("Initial rank cell. Example: d2");
                String cell2 = JOptionPane.showInputDialog("Final rank cell. Example: d2");
                String cell3 = JOptionPane.showInputDialog("Second initial rank cell. Example: d2");
                String cell4 = JOptionPane.showInputDialog("Second final rank cell. Example: d2");
                try {
                    sheetView.covariance(cell1,cell2, cell3, cell4);
                } catch (Exception ex) {

                }
            }
        };
        covar.addActionListener(Covar);

        ActionListener Sd = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String cell1 = JOptionPane.showInputDialog("Initial rank cell. Example: d2");
                String cell2 = JOptionPane.showInputDialog("Final rank cell. Example: d2");
                try {
                    sheetView.sd(cell1,cell2);
                } catch (Exception ex) {
                }
            }
        };
        sd.addActionListener(Sd);

        ActionListener Pear = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String cell1 = JOptionPane.showInputDialog("Initial rank cell. Example: d2");
                String cell2 = JOptionPane.showInputDialog("Final rank cell. Example: d2");
                String cell3 = JOptionPane.showInputDialog("Second initial rank cell. Example: d2");
                String cell4 = JOptionPane.showInputDialog("Second final rank cell. Example: d2");
                try {
                    sheetView.pearson(cell1,cell2, cell3, cell4);
                } catch (Exception ex) {

                }
            }
        };
        pear.addActionListener(Pear);
    }
}
