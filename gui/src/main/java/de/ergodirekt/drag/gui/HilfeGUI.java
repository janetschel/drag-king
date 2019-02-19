package de.ergodirekt.drag.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


public class HilfeGUI extends StandartDialogGUI {

    public HilfeGUI(JFrame fenster) {
        super(fenster, "Hilfe");




    }

    private JPanel hauptPanel() {

        JPanel hilfePanel=new JPanel();
        JLabel text= new JLabel("<html><center><span style=\"color:blue\"><br><h3>Für Fragen und Hilfe bitte kontaktiren mit uns  :</h3></span>" +
                "<h2><a href = \"mailto:jan.etschel@ergodirekt.de\">jan.etschel@ergodirekt.de</a><h2></br>" +
                "</center></html>");
        text.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/kontakt.png")).getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
        hilfePanel.add(text);

        return hilfePanel;
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