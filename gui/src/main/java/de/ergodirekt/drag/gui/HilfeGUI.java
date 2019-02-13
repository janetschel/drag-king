package de.ergodirekt.drag.gui;

import javax.swing.*;
import java.awt.*;

public class HilfeGUI extends JDialog {

    public HilfeGUI(JFrame fenster) {
        super(fenster);
        init();
        setLocationRelativeTo(fenster);
        setVisible(true);
    }


    private void init() {
        getContentPane().setLayout(new GridBagLayout());
        setTitle("Hilfe");
        setModal(true);
        setSize(400, 300);

    }
}