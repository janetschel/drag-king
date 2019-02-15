package de.ergodirekt.drag.gui;

import de.ergodirekt.drag.autocomplete.AutoCompleteUsernames;
import de.ergodirekt.drag.utils.Datei;
import de.ergodirekt.drag.utils.ErrorMessage;
import de.ergodirekt.drag.utils.DragException;
import de.ergodirekt.drag.utils.GridBagConstraintsCreator;
import de.ergodirekt.drag.utils.fileicon.DateiExistiertNichtException;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.CompoundBorder;

public class SendFileGUI {
    private static final int ICONS_PER_ROW = 4;
    private static final int INSET = 5;
    private final String[] usernames;
    private JFrame frame;
    private List<String> selectedFiles = new ArrayList<>();
    private String destinationFolder;
    private JScrollPane iconScrollPane;
    private DefaultListModel<String> model;
    private JList<String> selectedUsersList;

    public SendFileGUI(String destinationFolder) {
        this.destinationFolder = destinationFolder;
        String[] filePaths = Datei.getFilePathsFromDirectory(destinationFolder);
        usernames = new String[filePaths.length];
        for (int i = 0; i < filePaths.length; i++) {
            usernames[i] = Datei.getFileNameFromPath(filePaths[i]);
        }
        AutoCompleteUsernames autoCompleteUsernames = new AutoCompleteUsernames(usernames);
        SwingUtilities.invokeLater(autoCompleteUsernames);
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //frame.setSize(600, 500);
        //frame.setMinimumSize(new Dimension(230, 200));
        frame.setTitle("Drag King");

        frame.setLayout(new BorderLayout());
        frame.setJMenuBar(getMenue());

        frame.add(getMittelScrollPane(), BorderLayout.CENTER);
        frame.add(getSuedPanel(autoCompleteUsernames), BorderLayout.SOUTH);
        frame.add(getNordPanel(), BorderLayout.WEST);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private Component getNordPanel() {
        model = new DefaultListModel<>();
        selectedUsersList = new JList<>(model);
        selectedUsersList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        JScrollPane listScroller = new JScrollPane(selectedUsersList);
        listScroller.setPreferredSize(new Dimension(250, 80));
        addBorder(listScroller);

        return listScroller;
    }


    private Component getSuedPanel(AutoCompleteUsernames autoCompleteUsernames) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JButton bSenden = new JButton("Senden");
        bSenden.addActionListener(e -> {
            try {
                Datei.sendFiles(selectedUsersList, selectedFiles.toArray(new String[0]), destinationFolder);
            } catch (DragException de) {
                ErrorMessage.showErrorDialog(frame, de, "Datei konnte nicht geschickt werden");
            }
        });
        JButton bLoeschen = new JButton("Löschen");
        bLoeschen.addActionListener(e -> clearInputs());
        JPanel benutzerPanel = new JPanel();
        JLabel label = new JLabel();
        label.setText("Empfänger: ");

        JTextField userTextField;
        userTextField = autoCompleteUsernames.getInput();

        JTextField finalUserTextField = userTextField;
        userTextField.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                boolean userSeleceted = false;
                boolean userExists = false;
                String textFieldUsername = finalUserTextField.getText();

                for (int i = 0; i < model.getSize(); i++) {
                    if (model.get(i).equals(textFieldUsername)) {
                        userSeleceted = true;
                    }
                }

                for (String username : usernames) {
                    if (textFieldUsername.equals(username)) {
                        userExists = true;
                    }
                }
                if (!userSeleceted && userExists) model.addElement(finalUserTextField.getText());
                finalUserTextField.setText("");
            }
        });

        benutzerPanel.add(label);
        benutzerPanel.add(userTextField);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(bLoeschen);
        buttonPanel.add(bSenden);
        panel.add(buttonPanel, BorderLayout.EAST);
        panel.add(benutzerPanel, BorderLayout.WEST);
        panel.setBorder(BorderFactory.createLineBorder(frame.getContentPane().getBackground(), 5));

        return panel;
    }

    private JScrollPane getMittelScrollPane() {
        iconScrollPane = new JScrollPane(getIconsPanel());
        iconScrollPane.setPreferredSize(
                new Dimension(
                        ICONS_PER_ROW*(IconPanel.ICON_WIDTH + 2*INSET)
                                + 40 /*Spacing an Seite*/,
                        400));
        iconScrollPane.getVerticalScrollBar().setUnitIncrement(20);

        addBorder(iconScrollPane);
        new DropTarget(iconScrollPane, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent dtde) {
                Transferable tr = dtde.getTransferable();

                if (tr.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY);
                    try {
                        for (Object filePath : (List)tr.getTransferData(DataFlavor.javaFileListFlavor)) {
                            selectedFiles.add(filePath.toString());
                            iconScrollPane.setViewportView(getIconsPanel());
                        }
                    } catch (UnsupportedFlavorException | IOException e) {
                        ErrorMessage.showErrorDialog(frame, e, "Fehler beim Drop");
                    }

                    dtde.getDropTargetContext().dropComplete(true);
                } else {
                    dtde.rejectDrop();
                }
            }
        });

        return iconScrollPane;
    }

    private JPanel getIconsPanel() {
        JPanel iconsPanel = new JPanel();
        iconsPanel.setLayout(new GridBagLayout());

        java.util.List<GridBagConstraints> gbcList = GridBagConstraintsCreator.createGridBagConstraints(selectedFiles, ICONS_PER_ROW);

        IconPanel[] iconList = new IconPanel[selectedFiles.size()];
        for (int i = 0; i < selectedFiles.size(); i++) {
            try {
                iconList[i] = new IconPanel(selectedFiles.get(i));
                iconList[i].switchClicked();
                iconsPanel.add(iconList[i], gbcList.get(i));
            } catch (DateiExistiertNichtException e) {
                ErrorMessage.showErrorDialog(frame, e, "Datei nicht gefunden");
            }
        }

        iconsPanel.setBackground(new Color(0xffffffff));

        return iconsPanel;
    }

    private JMenuBar getMenue() {
        JMenuBar bar = new JMenuBar();


        JMenu beenden = new JMenu("Beenden");
        bar.add(beenden);
        JMenuItem exit = new JMenuItem("Exit");
        beenden.add(exit);
        exit.addActionListener(e ->exit());

        JMenu info = new JMenu("Info");
        bar.add(info);
        JMenuItem hilfe = new JMenuItem("Hilfe");
        JMenuItem uber = new JMenuItem("Über");
        info.add(hilfe);
        info.add(uber);
        hilfe.addActionListener(e -> hilfe());
        uber.addActionListener(e -> uber());
        return bar;
    }

    private void exit() {
        System.exit(0);
    }

    private void hilfe() {
         new HilfeGUI(frame);

    }

    private void uber() {
        new UeberUnsGUI(frame);
    }

    private void addBorder (JComponent component) {
        component.setBorder(
                new CompoundBorder(
                        BorderFactory.createEmptyBorder(10, 10, 10, 10),
                        BorderFactory.createLineBorder(Color.GRAY, 1)
                )
        );
    }

    private void clearInputs() {
        selectedFiles = new ArrayList<>();
        iconScrollPane.setViewportView(getIconsPanel());
        model = new DefaultListModel<>();
        selectedUsersList.setModel(model);
    }
}
