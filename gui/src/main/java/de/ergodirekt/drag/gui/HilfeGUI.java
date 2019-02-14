package de.ergodirekt.drag.gui;

import javax.swing.*;
import java.awt.*;


public class HilfeGUI extends StandartDialogGUI {
    private JFrame fenster2;
    public HilfeGUI(JFrame fenster) {
        super(fenster, "Hilfe");
        fenster2 = fenster;
        fenster2=new JFrame();
        fenster2.add(hauptPanel(), BorderLayout.CENTER);
        fenster2.setVisible(true);


    }

    private JPanel hauptPanel() {

        JPanel hilfePanel=new JPanel();
        JLabel email= new JLabel("jan.etschel@ergodirekt.de");
        hilfePanel.add(email);

        return hilfePanel;
    }
}