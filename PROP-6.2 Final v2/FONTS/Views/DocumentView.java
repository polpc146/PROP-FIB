package Views;

import PresentationControllers.DocumentPresentationCtrl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Vista principal del programa
 * @author Pol Pérez Castillo
 */
public class DocumentView extends JFrame{

    private DocumentPresentationCtrl docPresCtrl;

    private JPanel northPanel;
    private JPanel centerPanel;
    private JPanel southPanel;

    private JButton createDoc = new JButton("Create a document");
    private JButton eraseDoc = new JButton("Erase a document");
    private JButton changeName = new JButton(" Change the name of a document");
    private JButton editDoc = new JButton("Edit a document");
    private JButton exit = new JButton("Exit");

    private DefaultListModel model;
    private JList docList;

    private LinkedList<String> list;

    /**
     * Constructora de la vista
     * @param docPresCtrl controlador de presentación que interactúa con la vista
     * @throws Exception
     */
    public DocumentView (DocumentPresentationCtrl docPresCtrl) throws Exception {

        this.docPresCtrl = docPresCtrl;

        Toolkit myScreen = Toolkit.getDefaultToolkit();
        Dimension sizeScreen = myScreen.getScreenSize();
        int height = sizeScreen.height;
        int width = sizeScreen.width;
        setBounds(0,0,width, height-40);
        setTitle("Main View");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        northPanel = new JPanel(new FlowLayout());
        northPanel.add(createDoc);
        northPanel.add(eraseDoc);
        northPanel.add(changeName);
        northPanel.add(editDoc);
        add(northPanel, BorderLayout.NORTH);

        centerPanel = new JPanel();
        JLabel label = new JLabel("List of the documents:");
        centerPanel.add(label);

        model = new DefaultListModel<>();
        docList = new JList<>(model);
        list = docPresCtrl.getDocumentList();
        for (int i = 0; i < list.size(); ++i) model.add(i, list.get(i));
        docList.setVisibleRowCount(10);
        docList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(docList);
        centerPanel.add(scrollPane);
        add(centerPanel, BorderLayout.CENTER);

        southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        southPanel.add(exit);
        add(southPanel, BorderLayout.SOUTH);

        ActionListener createDocument = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String newDoc = "";
                boolean correct = false;
                boolean correct2 = true;
                while (!correct || !correct2) {

                    newDoc = JOptionPane.showInputDialog("Name of the new document (don't use numbers)");
                    int size = newDoc.length();
                    correct = false;
                    correct2 = true;
                    boolean num = false;
                    for (int i = 0; i < size; i++) {

                        if (newDoc.charAt(i) >= '0' && newDoc.charAt(i) <= '9') {
                            num = true;
                            break;
                        }
                    }
                    if (!num) correct = true;
                    size = model.getSize();
                    for (int i = 0; i < size; ++i) {

                        if (newDoc.equalsIgnoreCase((String) model.get(i))) correct2 = false;
                    }
                }

                try {
                    docPresCtrl.CreateDocument(newDoc);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

               if (newDoc.length() != 0) model.add(model.size(), newDoc);
            }
        };

        ActionListener eraseDocument = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                int answer = JOptionPane.showConfirmDialog(null, "Are you sure? You will lose the sheet.", "WARNING", JOptionPane.YES_NO_CANCEL_OPTION);
                if (answer == JOptionPane.YES_OPTION) {

                    String remDoc = (String) docList.getSelectedValue();
                    int index = docList.getSelectedIndex();
                    try {
                        docPresCtrl.EraseDocument(remDoc);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    model.remove(index);
                }
            }
        };

        ActionListener changeDocument = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String chaDoc = (String) docList.getSelectedValue();
                int index = docList.getSelectedIndex();
                String newDoc = JOptionPane.showInputDialog("New name of the document:");
                try {
                    docPresCtrl.ChangeNameDocument(chaDoc, newDoc);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                model.set(index, newDoc);
            }
        };

        ActionListener selectDocument = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String selDoc = (String) docList.getSelectedValue();
                try {
                    docPresCtrl.SelectDocument(selDoc);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };

        ActionListener close = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        };

        createDoc.addActionListener(createDocument);
        eraseDoc.addActionListener(eraseDocument);
        changeName.addActionListener(changeDocument);
        editDoc.addActionListener(selectDocument);
        exit.addActionListener(close);
    }

    /**
     * Muestra por pantalla la vista
     */
    public void setVisible() {

        setVisible(true);
    }

    /**
     * Oculta la vista
     */
    public void hideView() {

        setVisible(false);
    }
}
