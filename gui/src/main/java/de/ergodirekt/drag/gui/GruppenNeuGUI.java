package de.ergodirekt.drag.gui;

import javax.swing.*;
import java.awt.*;

public class GruppenNeuGUI extends JDialog {

    public GruppenNeuGUI(JFrame fenster) {
        super(fenster);
        init();
        setLocationRelativeTo(fenster);
        setVisible(true);
    }

    private void init() {
        getContentPane().setLayout(new GridBagLayout());
        setTitle("Neu");
        setModal(true);
        setSize(400, 300);

    }
}