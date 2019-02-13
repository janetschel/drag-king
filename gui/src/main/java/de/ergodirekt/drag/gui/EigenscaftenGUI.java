package de.ergodirekt.drag.gui;

import javax.swing.*;
import java.awt.*;

public class EigenscaftenGUI extends JDialog {

    public EigenscaftenGUI(JFrame fenster) {
        super(fenster);
        init();
        setLocationRelativeTo(fenster);
        setVisible(true);
    }

    private void init() {
        getContentPane().setLayout(new GridBagLayout());
        setTitle("Einstellungen");
        setModal(true);
        setSize(400, 300);

    }
}