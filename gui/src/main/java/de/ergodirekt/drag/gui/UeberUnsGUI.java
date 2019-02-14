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
        ImageIcon icon = createImageIcon("/images/logo.png",
                "a pretty but meaningless splat");

        JLabel programmV= new JLabel("<html><center><span style=\"color:blue\"><h2>Programm Version 1.0.0.1 </h2></span>" +
                "<h4> wir Sind Azubis von Die Erste lehrjahr</span></h4>" +
                "<h3>Jan Etschel, Manuel Wälztein" +
                "<br>Mohammad Ali Elbokaie, Habib Akroush</br>" +
                "<br> Obada Al Refai</h3></br>" +
                "</center></html>");

        programmV.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));


        ueber.add(programmV);


        return ueber;
    }
    protected ImageIcon createImageIcon(String path,
                                        String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
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