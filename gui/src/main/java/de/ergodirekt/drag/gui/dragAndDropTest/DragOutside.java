package de.ergodirekt.drag.gui.dragAndDropTest;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class DragOutside {
    private JDialog dialog;

    public DragOutside()
    {
        dialog = new JDialog();
        initCenter();
        initFrame();
    }

    private void initCenter()
    {
        ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "/images/Icon.png"); //TODO Icon der Datei auf dem Public Laufwerk
        JLabel label = new JLabel(icon);
        label.setTransferHandler(new ListTransferHandler());
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mEvt) { //Notwendig, da Drag and Drop normalerweise mit Label nich möglich
                JComponent component = (JComponent) mEvt.getSource();
                TransferHandler tHandler = component.getTransferHandler();
                tHandler.exportAsDrag(component, mEvt, TransferHandler.COPY);
            }
        });
        dialog.add(label);
    }

    private void initFrame()
    {
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE) ;
        dialog.setTitle("Ihnen wurden Dateien geschickt!");
        dialog.setSize(300,300);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public static void main(String[] args)
    {
        new DragOutside();
    }
} // end class