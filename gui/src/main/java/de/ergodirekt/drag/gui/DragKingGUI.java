package de.ergodirekt.drag.gui;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DragKingGUI implements ActionListener {
    private JFrame frame;

    public DragKingGUI() {
        frame=new JFrame();
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

        JComboBox<Object> benutzerliste = new JComboBox<>();
        benutzerliste = new JComboBox<>(placeholder);
        panel.add(benutzerliste, BorderLayout.WEST);

        bSenden.addActionListener(this);
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