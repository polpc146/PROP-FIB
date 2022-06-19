package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Componente de la barra de botones inferior
 * @author Pol Pérez Castillo
 */
public class SheetOpButtons extends JPanel {

    private SheetView sheetView;

    private JPanel checkPanel;
    private JPanel butPanel;

    private ButtonGroup group;
    private JRadioButton celCheck, rowCheck, colCheck;

    private JButton inc, trunc, abs, bin, hexa;
    private JButton day, month, year, dayOfWeek;
    private JButton siz;

    private boolean rowSelected = false;
    private boolean colSelected = false;

    /**
     * Constructora de la barra de botones
     * @param sheetView vista de la hoja de cálculo
     */
    public SheetOpButtons(SheetView sheetView) {

        this.sheetView = sheetView;

        setLayout(new BorderLayout());

        checkPanel = new JPanel();
        checkPanel.setLayout(new BoxLayout(checkPanel, BoxLayout.Y_AXIS));
        group = new ButtonGroup();
        celCheck = new JRadioButton("Apply only on cell", true);
        rowCheck = new JRadioButton("Apply on row", false);
        colCheck = new JRadioButton("Apply on column", false);

        ActionListener controlChecks = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == rowCheck) {

                    rowSelected = true;
                    colSelected = false;
                }
                else if (e.getSource() == colCheck) {

                    colSelected = true;
                    rowSelected = false;
                }
                else if (e.getSource() == celCheck) {

                    rowSelected = false;
                    colSelected = false;
                }
            }
        };
        celCheck.addActionListener(controlChecks);
        rowCheck.addActionListener(controlChecks);
        colCheck.addActionListener(controlChecks);

        group.add(celCheck);
        group.add(rowCheck);
        group.add(colCheck);
        checkPanel.add(celCheck);
        checkPanel.add(rowCheck);
        checkPanel.add(colCheck);
        add(checkPanel, BorderLayout.WEST);
        butPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inc = new JButton("Increment");
        trunc = new JButton("Truncate");
        abs = new JButton("Absolute value");
        bin = new JButton("Convert to binary");
        hexa = new JButton("Convert to hexadecimal");

        ActionListener Inc = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                sheetView.increment(rowSelected, colSelected);
            }
        };
        inc.addActionListener(Inc);

        ActionListener Trunc = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {sheetView.truncate(rowSelected, colSelected);}
                catch (Exception ex) {}
            }
        };
        trunc.addActionListener(Trunc);

        ActionListener Abs = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                sheetView.absolute(rowSelected, colSelected);
            }
        };
        abs.addActionListener(Abs);

        ActionListener Bin = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                sheetView.binary(rowSelected, colSelected);
            }
        };
        bin.addActionListener(Bin);

        ActionListener Hexa = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                sheetView.hexa(rowSelected, colSelected);
            }
        };
        hexa.addActionListener(Hexa);

        butPanel.add(inc);
        butPanel.add(trunc);
        butPanel.add(abs);
        butPanel.add(bin);
        butPanel.add(hexa);

        day = new JButton("Day");
        month = new JButton("Month");
        year = new JButton("Year");
        dayOfWeek = new JButton("Day of the week");

        ActionListener Day = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {
                    sheetView.getDay(rowSelected, colSelected);
                } catch (Exception ex) {

                }
            }
        };
        day.addActionListener(Day);

        ActionListener Month = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {
                    sheetView.getMonth(rowSelected, colSelected);
                } catch (Exception ex) {

                }
            }
        };
        month.addActionListener(Month);

        ActionListener Year = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {
                    sheetView.getYear(rowSelected, colSelected);
                } catch (Exception ex) {

                }
            }
        };
        year.addActionListener(Year);

        ActionListener dow = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {
                    sheetView.dayOfWeek(rowSelected, colSelected);
                } catch (Exception ex) {
                }
            }
        };
        dayOfWeek.addActionListener(dow);

        butPanel.add(day);
        butPanel.add(month);
        butPanel.add(year);
        butPanel.add(dayOfWeek);

        siz = new JButton("Size");

        ActionListener Siz = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {
                    sheetView.stringSize(rowSelected, colSelected);
                } catch (Exception ex) {
                }
            }
        };
        siz.addActionListener(Siz);

        butPanel.add(siz);
        add(butPanel, BorderLayout.CENTER);
    }
}
