package de.ergodirekt.drag.gui;

import com.sun.glass.ui.Size;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class UeberUnsGUI extends StandartDialogGUI {

    public UeberUnsGUI(JFrame fenster) {
        super(fenster, "Über");
    }



    private JPanel hauptPanel() {

        JPanel ueber=new JPanel();
        JLabel programmV= new JLabel("<html><center><span style=\"color:blue\"><h2>Programm Version 1.0.0.1 </h2></span>" +
                "<h4> wir Sind Azubis von Die Erste lehrjahr</span></h4>" +
                "<h3>Jan Etschel, Manuel Wälztein" +
                "<br>Mohammad Ali Elbokaie, Habib Akroush</br>" +
                "<br> Obada Al Refai</h3></br>" +
                "</center></html>");


        ueber.add(programmV);


        return ueber;
    }

    @Override
    protected void initKomponenten() {
        getContentPane().setLayout(new BorderLayout());
        add(hauptPanel(), BorderLayout.CENTER);
        Action closeAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        };

        KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0);
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(esc, "closex");
        getRootPane().getActionMap().put("closex", closeAction);
    }
}