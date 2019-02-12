package de.ergodirekt.drag.gui;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// TODO umbenennen
// TODO FCoI
public class DragKingGUI extends JFrame implements ActionListener {


    private JComboBox<Object> benutzerliste;
    private JButton bSenden;

    public DragKingGUI() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 300);
        setMinimumSize(new Dimension(230,200));
        setTitle("Drag King");
        setLocationRelativeTo(null);


        setLayout(new BorderLayout());
        //add(getNordPanel(), BorderLayout.NORTH);
        add(getMittelPanel(), BorderLayout.CENTER);
        add(getSuedPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private Component getSuedPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        bSenden = new JButton("Senden");
        String[] placeholder = {"Benutzer1", "Benutzer2", "Benutzer3", "Benutzer4", "Benutzer5", "Benutzer6"}; //TODO Windowsbenutzer ziehen

        benutzerliste = new JComboBox<>();
        benutzerliste= new JComboBox<>(placeholder);
        panel.add(benutzerliste, BorderLayout.WEST);

        bSenden.addActionListener(this);
        panel.add(bSenden, BorderLayout.EAST);

        panel.setBorder(BorderFactory.createLineBorder(getContentPane().getBackground(), 10));

        return panel;
    }

    private Component getMittelPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBorder(
                new CompoundBorder(
                        BorderFactory.createLineBorder(getContentPane().getBackground(), 10),
                        BorderFactory.createLineBorder(new Color(0xdd444444), 1)
                )
        );
        panel.setBackground(new Color(0xffffffff));

        return panel;
    }

    /* private Component getNordPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        return panel;
    } */

    public static void main(String[] args) {
        new DragKingGUI();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}