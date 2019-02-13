package de.ergodirekt.drag.gui;

import javax.swing.*;
import java.awt.*;

public class UberGUI extends JDialog {

    public UberGUI(JFrame fenster) {
        super(fenster);
        init();
        setLocationRelativeTo(fenster);
        setVisible(true);
    }


    private void init() {
        getContentPane().setLayout(new GridBagLayout());
        setTitle("Über");
        setModal(true);
        setSize(400, 300);

    }
}