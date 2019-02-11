package de.ergodirekt.drag.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DragKingGUI extends JDialog {
    private JPanel contentPane;
    private JButton bSenden;
    private JComboBox benutzername;


    public DragKingGUI() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(bSenden);

        bSenden.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        
        
    }


    public static void main(String[] args) {
        DragKingGUI dialog = new DragKingGUI();
        //dialog.pack();
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        System.exit(0);
    }}

