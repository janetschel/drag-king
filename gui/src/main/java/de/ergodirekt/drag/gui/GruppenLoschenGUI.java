package de.ergodirekt.drag.gui;

import javax.swing.*;
import java.awt.*;

public class GruppenLoschenGUI extends JDialog {

    public GruppenLoschenGUI(JFrame fenster) {
        super(fenster);
        init();
        setLocationRelativeTo(fenster);
        setVisible(true);
    }

    private void init() {
        getContentPane().setLayout(new GridBagLayout());
        setTitle("Löschen");
        setModal(true);
        setSize(400, 300);

    }
}