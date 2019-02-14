package de.ergodirekt.drag.gui;

import com.sun.glass.ui.Size;

import javax.swing.*;
import java.awt.*;

public class UeberUnsGUI extends StandartDialogGUI {

    public UeberUnsGUI(JFrame fenster) {
        super(fenster, "Über");
    }



    private JPanel hauptPanel() {

        JPanel ueber=new JPanel();
        JLabel programmV= new JLabel("<html><center><span style=\"color:blue\"><br><h1>Programm Version 1.0.0.1 </h1></br></span>" +
                "<br><h3><span style=\"color:red\"> wir Sind Azubis von Die Erstelehrjahr</span></h3></br>" +
                "<br><h2>Jan Etschel, Manuel Wälztein</br>" +
                "<br>Mohammad Ali Elbokaie, Habib Akroush</br>" +
                "<br> Obada Al Refai</h2></br>" +
                "</center></html>");


        ueber.add(programmV);


        return ueber;
    }

    @Override
    protected void initKomponenten() {
        getContentPane().setLayout(new BorderLayout());
        add(hauptPanel(), BorderLayout.CENTER);

    }
}