package de.ergodirekt.drag.gui;

import javax.swing.*;
import java.awt.*;

public class UeberUnsGUI extends StandartDialogGUI {

    public UeberUnsGUI(JFrame fenster) {
        super(fenster, "Über");

        fenster.add(hauptPanel(), BorderLayout.CENTER);
        fenster.setVisible(true);
    }



    private JPanel hauptPanel() {

        JPanel ueber=new JPanel();
        JLabel programmV= new JLabel("Programm Version 1.0.0.1");
        ueber.add(programmV);
        JLabel vorstellung= new JLabel("wir Sind Azubis von Die Erstelehrjahr");
        ueber.add(vorstellung);

        JLabel name= new JLabel("Jan Etschel, Manuel Wälztein, Habib Akroush, Mohammad Ali Elbokaie, Obada Al Refai");
        ueber.add(name);

        return ueber;
    }}