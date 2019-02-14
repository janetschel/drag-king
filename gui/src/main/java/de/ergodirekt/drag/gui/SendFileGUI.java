package de.ergodirekt.drag.gui;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.CompoundBorder;

public class SendFileGUI {
    private JFrame frame;
    private JList<String> list;

    public SendFileGUI() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setMinimumSize(new Dimension(230, 200));
        frame.setTitle("Drag King");
        frame.setLocationRelativeTo(null);

        frame.setLayout(new BorderLayout());
        frame.setJMenuBar(getMenue());

        frame.add(getMittelPanel(), BorderLayout.CENTER);
        frame.add(getSuedPanel(), BorderLayout.SOUTH);
        frame.add(getNordlPanel(), BorderLayout.WEST);

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
        String[] placeholder = {"","Benutzer1", "Benutzer2", "Benutzer3", "Benutzer4", "Benutzer5", "Benutzer6"};
        JPanel benutzerPanel = new JPanel();
        JLabel label = new JLabel();
        JComboBox<String> benutzerliste = new JComboBox<>(placeholder);
        benutzerliste.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String auswahl = (String) e.getItem();
                    if (!auswahlListe.contains(auswahl)) {
                        auswahlListe.add(auswahl);
                        addUser(auswahlListe.toArray());

                    }
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



    private Component getMittelPanel() {
        JScrollPane mittelScrollPane = new JScrollPane();
        mittelScrollPane.setLayout(new ScrollPaneLayout());


        mittelScrollPane.setBorder(
                new CompoundBorder(
                        BorderFactory.createEmptyBorder(10, 10, 10, 10),
                        BorderFactory.createLineBorder(new Color(0xdd444444), 1)
                )
        );
        new DropTarget(mittelScrollPane, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent dtde) {
                Transferable tr = dtde.getTransferable();

                if (tr.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY);
                    try {
                        System.out.println(dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor));
                    } catch (UnsupportedFlavorException | IOException e) {
                        e.printStackTrace();
                    }
                    dtde.getDropTargetContext().dropComplete(true);
                } else {
                    dtde.rejectDrop();
                }
            }
        });
        mittelScrollPane.getViewport().setBackground(new Color(0xffffffff));

        return mittelScrollPane;
    }


    public static void main(String[] args) {
        new SendFileGUI();
    }

    private JMenuBar getMenue() {
        JMenuBar bar = new JMenuBar();

        JMenu beenden = new JMenu("Beenden");
        bar.add(beenden);

        JMenuItem beenden1 = new JMenuItem("Beenden");
        beenden1.addActionListener(e -> System.exit(0));
        beenden.add(beenden1);

        JMenu info = new JMenu("Info");
        bar.add(info);

        JMenuItem hilfe = new JMenuItem("Hilfe");
        info.add(hilfe);
        hilfe.addActionListener(e -> hilfe());

        JMenuItem uber = new JMenuItem("Über");
        info.add(uber);
        uber.addActionListener(e -> uber());

        return bar;
    }

    private void hilfe() {
        new HilfeGUI(frame);
    }


    private void uber() {
        new UeberUnsGUI(frame);

    }


    }
