package de.ergodirekt.drag.gui;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class DragKingGUI {
    private JFrame frame;

    public DragKingGUI() {
        frame=new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setMinimumSize(new Dimension(230,200));
        frame.setTitle("Drag King");
        frame.setLocationRelativeTo(null);


        frame.setLayout(new BorderLayout());
        frame.add(getNordPanel(), BorderLayout.NORTH);
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
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBorder(
                new CompoundBorder(
                        BorderFactory.createLineBorder(frame.getContentPane().getBackground(), 10),
                        BorderFactory.createLineBorder(new Color(0xdd444444), 1)
                )
        );
        panel.setBackground(new Color(0xffffffff));

        return panel;
    }
    private Component getNordPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        erstelleMenue();
        return panel;
    }

    public static void main(String[] args) {
        new DragKingGUI();
    }
    private void erstelleMenue() {
        JMenuBar bar = new JMenuBar();
        frame.setJMenuBar(bar);

        JMenu einstellungen = new JMenu("Einstellungen");
        bar.add(einstellungen);
        JMenuItem item1 = new JMenuItem("a");
        einstellungen.add(item1);
        JMenuItem item2 = new JMenuItem("b");
        einstellungen.add(item2);

        JMenu gruppen = new JMenu("Gruppenverwaltung");
        bar.add(gruppen);
        JMenuItem item3 = new JMenuItem("c");
        gruppen.add(item3);
        JMenuItem item4 = new JMenuItem("d");
        gruppen.add(item4);

        JMenu info = new JMenu("Info");
        bar.add(info);
        JMenuItem item5 = new JMenuItem("e");
        info.add(item5);
        JMenuItem item6 = new JMenuItem("f");
        info.add(item6);


    }
}