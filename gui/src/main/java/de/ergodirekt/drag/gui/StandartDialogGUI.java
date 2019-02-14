package de.ergodirekt.drag.gui;

import javax.swing.*;
import java.awt.*;

public abstract class StandartDialogGUI extends JDialog {

    public StandartDialogGUI(JFrame fenster, String fensterName) {
        super(fenster);
        init(fensterName);
        setLocationRelativeTo(fenster);
        initKomponenten();
        setVisible(true);
    }

    protected abstract void initKomponenten();

    private void init(String fensterName) {

        getContentPane().setLayout(new GridBagLayout());
        setTitle(fensterName);
        setModal(true);
        setSize(370, 250);

    }
}