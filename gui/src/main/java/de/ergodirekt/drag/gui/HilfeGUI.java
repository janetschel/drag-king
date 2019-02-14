package de.ergodirekt.drag.gui;

import javax.swing.*;
import java.awt.*;


public class HilfeGUI extends StandartDialogGUI {

    public HilfeGUI(JFrame fenster) {
        super(fenster, "Hilfe");




    }

    private JPanel hauptPanel() {

        JPanel hilfePanel=new JPanel();
        JLabel text= new JLabel("<html><center><span style=\"color:blue\"><br><h3>Für Fragen und Hilfe bitte kontaktiren mit uns  :</h3></span>" +
                "<h2><a href = \"mailto:jan.etschel@ergodirekt.de\">jan.etschel@ergodirekt.de</a><h2></br>" +
                "</center></html>");
        hilfePanel.add(text);

        return hilfePanel;
    }

    @Override
    protected void initKomponenten() {
        getContentPane().setLayout(new BorderLayout());

        add(hauptPanel(), BorderLayout.CENTER);
    }
}