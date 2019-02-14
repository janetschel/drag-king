package de.ergodirekt.drag.gui;

import javax.swing.*;
import java.awt.*;


public class HilfeGUI extends StandartDialogGUI {

    public HilfeGUI(JFrame fenster) {
        super(fenster, "Hilfe");




    }

    private JPanel hauptPanel() {

        JPanel hilfePanel=new JPanel();
        JLabel text= new JLabel("Für Fragen und Hilfe bitte senden ein E-mail an :       ");
        hilfePanel.add(text);
        JLabel email= new JLabel("jan.etschel@ergodirekt.de");
        email.setForeground(Color.BLUE);
        hilfePanel.add(email);

        return hilfePanel;
    }

    @Override
    protected void initKomponenten() {
        getContentPane().setLayout(new BorderLayout());

        add(hauptPanel(), BorderLayout.CENTER);
    }
}