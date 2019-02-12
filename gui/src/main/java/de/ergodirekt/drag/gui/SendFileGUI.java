package de.ergodirekt.drag.gui;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.IOException;

public class SendFileGUI {
    private JFrame frame;

    public SendFileGUI() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setMinimumSize(new Dimension(230,200));
        frame.setTitle("Drag King");
        frame.setLocationRelativeTo(null);


        frame.setLayout(new BorderLayout());
        //frame.add(getNordPanel(), BorderLayout.NORTH);
        frame.add(getMittelPanel(), BorderLayout.CENTER);
        frame.add(getSuedPanel(), BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private Component getSuedPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JButton bSenden = new JButton("Senden");
        String[] placeholder = {"Benutzer1", "Benutzer2", "Benutzer3", "Benutzer4", "Benutzer5", "Benutzer6"}; //TODO Windowsbenutzer ziehen

        JComboBox<String> benutzerliste = new JComboBox<>(placeholder);
        panel.add(benutzerliste, BorderLayout.WEST);
        panel.add(bSenden, BorderLayout.EAST);

        panel.setBorder(BorderFactory.createLineBorder(frame.getContentPane().getBackground(), 10));

        return panel;
    }

    private Component getMittelPanel() {
        JScrollPane mittelScrollPane = new JScrollPane();
        mittelScrollPane.setLayout(new ScrollPaneLayout());
        mittelScrollPane.setBorder(
                new CompoundBorder(
                        BorderFactory.createEmptyBorder(10,10,10,10),
                        BorderFactory.createLineBorder(new Color(0xdd444444), 1)
                )
        );
        new DropTarget(mittelScrollPane, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent dtde) {
                Transferable tr = dtde.getTransferable();

                if ( tr.isDataFlavorSupported (DataFlavor.javaFileListFlavor) )
                {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY);
                    try {
                        System.out.println(dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor)); //TODO Dateipfad speichern
                    } catch (UnsupportedFlavorException | IOException e) {
                        e.printStackTrace();
                    }
                    dtde.getDropTargetContext().dropComplete(true);
                }
                else
                {
                    dtde.rejectDrop();
                }
            }
        });
        mittelScrollPane.getViewport().setBackground(new Color(0xffffffff));

        return mittelScrollPane;
    }
/*
    private Component getNordPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        return panel;
    }
    */

    public static void main(String[] args) {
        new SendFileGUI();
    }
}