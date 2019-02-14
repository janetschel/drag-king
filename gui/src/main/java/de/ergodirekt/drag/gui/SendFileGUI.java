package de.ergodirekt.drag.gui;

import de.ergodirekt.drag.utils.fileicon.DateiExistiertNichtException;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.CompoundBorder;

public class SendFileGUI {
    private static final int ICONS_PER_ROW = 4;
    private JFrame frame;
    private JList<String> list;
    private List<String> selectedFiles = new ArrayList<>();

    public SendFileGUI() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //frame.setSize(600, 500);
        //frame.setMinimumSize(new Dimension(230, 200));
        frame.setTitle("Drag King");

        frame.setLayout(new BorderLayout());
        frame.setJMenuBar(getMenue());

        frame.add(getMittelScrollPane(), BorderLayout.CENTER);
        frame.add(getSuedPanel(), BorderLayout.SOUTH);
        frame.add(getNordlPanel(), BorderLayout.WEST);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private Component getNordlPanel() {
        list = new JList(); //data has type Object[]
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        //list.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));

        return listScroller;
    }


    private Component getSuedPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        List<String> auswahlListe = new ArrayList<>();

        JButton bSenden = new JButton("Senden");
        JButton bAbrechen = new JButton("Abrechen");
        String[] placeholder = {"Bitte Benutzer auswählen","Benutzer1", "Benutzer2", "Benutzer3", "Benutzer4", "Benutzer5", "Benutzer6"};
        JPanel benutzerPanel = new JPanel();
        JLabel label = new JLabel();
        JComboBox<String> benutzerliste = new JComboBox<>(placeholder);
        benutzerliste.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String auswahl = (String) e.getItem();
                    if (!auswahlListe.contains(auswahl) && !auswahl.equals("Bitte Benutzer auswählen")) {
                        auswahlListe.add(auswahl);
                        addUser(auswahlListe.toArray());

                }
            }
        });

        benutzerPanel.add(benutzerliste, BorderLayout.WEST);
        benutzerPanel.add(label, BorderLayout.WEST);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(bAbrechen);
        buttonPanel.add(bSenden);
        panel.add(buttonPanel, BorderLayout.EAST);
        panel.add(benutzerPanel, BorderLayout.WEST);
        panel.setBorder(BorderFactory.createLineBorder(frame.getContentPane().getBackground(), 10));

        return panel;
    }

    private void addUser(Object[] newUser){
        String[] s = new String[newUser.length];
        for(int i = 0; i < s.length; i++){
            s[i] = newUser[i].toString();
        }
        list.setListData(s);
    }

    private JScrollPane getMittelScrollPane() {
        JScrollPane iconsScrollPane = getIconsScrollPane();
        new DropTarget(iconsScrollPane, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent dtde) {
                Transferable tr = dtde.getTransferable();

                if (tr.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY);
                    try {
                        for (Object filePath : (List)tr.getTransferData(DataFlavor.javaFileListFlavor)) {
                            System.out.println(filePath); //TODO kopieren
                            selectedFiles.add(filePath.toString());
                            System.out.println("Added " + filePath + " to selectedFiles");
                            frame.add(getMittelScrollPane(), BorderLayout.CENTER);
                            SwingUtilities.updateComponentTreeUI(frame);
                        }
                    } catch (UnsupportedFlavorException | IOException e) {
                        showErrorDialog(e);
                    }

                    dtde.getDropTargetContext().dropComplete(true);
                } else {
                    dtde.rejectDrop();
                }
            }
        });
        iconsScrollPane.getViewport().setBackground(new Color(0xffffffff));

        return iconsScrollPane;
    }

    private JScrollPane getIconsScrollPane() {
        System.out.println("Neue iconsScrollPane mit " + selectedFiles.toString() + " erstellt");
        final int INSET = 5;
        JPanel iconsPanel = new JPanel();
        iconsPanel.setLayout(new GridBagLayout());

        java.util.List<GridBagConstraints> gbcList = new ArrayList<>();

        int helper = ((float) selectedFiles.size() / ICONS_PER_ROW) % 1 == 0 ? selectedFiles.size() / ICONS_PER_ROW : selectedFiles.size() / ICONS_PER_ROW + 1;
        GridBagConstraints gbc;
        for (int j = 0; j < helper; j++) {
            for (int i = 0; i < ICONS_PER_ROW; i++) {
                gbc = new GridBagConstraints();
                gbc.gridx = i;
                gbc.gridy = j;
                gbc.insets = new Insets(INSET, INSET, INSET, INSET);
                gbcList.add(gbc);
            }
        }

        IconPanel[] iconList = new IconPanel[selectedFiles.size()];
        for (int i = 0; i < selectedFiles.size(); i++) {
            try {
                iconList[i] = new IconPanel(selectedFiles.get(i));
                iconList[i].switchClicked();
                iconsPanel.add(iconList[i], gbcList.get(i));
            } catch (DateiExistiertNichtException e) {
                showErrorDialog(e);
            }
        }

        JScrollPane iconScrollPane = new JScrollPane(iconsPanel);
        iconScrollPane.setPreferredSize(
                new Dimension(
                        ICONS_PER_ROW*(IconPanel.ICON_WIDTH + 2*INSET)
                                + 30 /*Spacing an Seite*/,
                        400));
        iconScrollPane.getVerticalScrollBar().setUnitIncrement(20);

        iconScrollPane.setBorder(
                new CompoundBorder(
                        BorderFactory.createEmptyBorder(10, 10, 10, 10),
                        BorderFactory.createLineBorder(new Color(0xdd444444), 1)
                )
        );

        return(iconScrollPane);
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

    private void showErrorDialog(Throwable t) {
        JOptionPane.showMessageDialog(frame, t.getMessage(), "Fehler beim Drop", JOptionPane.ERROR_MESSAGE);
    }
}
