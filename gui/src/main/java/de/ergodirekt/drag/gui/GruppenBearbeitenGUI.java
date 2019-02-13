package de.ergodirekt.drag.gui;

import javax.swing.*;
import java.awt.*;

public class GruppenBearbeitenGUI extends JDialog {

    public GruppenBearbeitenGUI(JFrame fenster) {
        super(fenster);
        init();
        setLocationRelativeTo(fenster);
        setVisible(true);
    }

    private void init() {
        getContentPane().setLayout(new GridBagLayout());
        setTitle("Bearbeiten");
        setModal(true);
        setSize(400, 300);

    }
}