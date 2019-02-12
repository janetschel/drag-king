package de.ergodirekt.drag.gui;

import de.ergodirekt.drag.logic.ListTransferHandler;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;

public class ReceiveFileGUI {
    private JDialog dialog;

    public ReceiveFileGUI()
    {
        dialog = new JDialog();
        initCenter();
        initFrame();
    }

    private void initCenter()
    {
        ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "/images/Icon.png"); //TODO Icon der Datei auf dem Public Laufwerk
        JLabel label = new JLabel(icon);
        label.setTransferHandler(new ListTransferHandler(getSelectedItems()));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mEvt) { //Notwendig, da Drag and Drop normalerweise mit Label nicht möglich
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

    private java.util.List<String> getSelectedItems() { //TODO wirklich ausgewählte Pfade hinzufügen
        java.util.List<String> selectedItems = new ArrayList<>();
        selectedItems.add(System.getProperty("user.dir") + "/images/Icon.png");
        selectedItems.add(System.getProperty("user.dir") + "/images/Icon2.png");

        return selectedItems;
    }

    public static void main(String[] args)
    {
        new ReceiveFileGUI();
    }
}
