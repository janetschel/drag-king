package de.ergodirekt.drag.gui;

import javax.swing.*;
import java.awt.*;

public class StandartDialogGUI extends JDialog {

    public StandartDialogGUI(JFrame fenster, String fensterName) {
        super(fenster);
        init(fensterName);
        setLocationRelativeTo(fenster);
        setVisible(true);
    }

    private void init(String fensterName) {

        getContentPane().setLayout(new GridBagLayout());
        setTitle(fensterName);
        setModal(true);
        setSize(400, 300);

    }
}