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
        JLabel programmV= new JLabel("         Programm Version 1.0.0.1             ");
        JLabel leer= new JLabel("                                                       ");
   ;
        programmV.setForeground(Color.BLUE);
        ueber.add(programmV);

        JLabel vorstellung= new JLabel("wir Sind Azubis von Die Erstelehrjahr");
        vorstellung.setForeground(Color.BLACK);
        ueber.add(leer);
        ueber.add(vorstellung);
        ueber.add(leer);
        JLabel name= new JLabel("Jan Etschel, Manuel Wälztein, Habib Akroush");
        name.setForeground(Color.RED);
        ueber.add(name);
        JLabel name2= new JLabel("Mohammad Ali Elbokaie, Obada Al Refai");
        name2.setForeground(Color.RED);
        ueber.add(name2);

        return ueber;
    }

    @Override
    protected void initKomponenten() {
        getContentPane().setLayout(new BorderLayout());
        add(hauptPanel(), BorderLayout.CENTER);

    }
}