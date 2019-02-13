package de.ergodirekt.drag.gui;

import de.ergodirekt.drag.logic.ListTransferHandler;
import de.ergodirekt.drag.utils.Files;
import de.ergodirekt.drag.utils.fileicon.DateiExistiertNichtException;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.*;


public class ReceiveFileGUI {
    private static final int ICONS_PER_ROW = 4;
    private JFrame frame;
    private JLabel statusLabel;

    public ReceiveFileGUI() {
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        statusLabel = new JLabel();
        frame.add(getCenter(), BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.SOUTH);
        initFrame();
    }

    private JLabel getCenter() {
        ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "/images/Icon.png"); //TODO Icon der Datei auf dem Public Laufwerk
        JLabel label = new JLabel(icon);
        try {
            label.setTransferHandler(new ListTransferHandler(getSelectedItems()));
        } catch (FileNotFoundException e) {
            statusLabel.setText(e.getMessage());
        }
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mEvt) { //Notwendig, da Drag and Drop normalerweise mit Label nicht möglich
                JComponent component = (JComponent) mEvt.getSource();
                TransferHandler tHandler = component.getTransferHandler();
                tHandler.exportAsDrag(component, mEvt, TransferHandler.COPY);
            }
        });
        return(label);
    }

    private void initFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
        frame.setTitle("Ihnen wurden Dateien geschickt!");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void dragNDrop(MouseEvent mEvt) {
        java.util.List<String> selectedItems = new ArrayList<>();
        for (IconPanel icon : iconList) {
            if (icon.isClicked()) {
                selectedItems.add(icon.getFilePath());
            }
        }
        statusLabel.setText(addTransferHandler(iconPanel, selectedItems));
        TransferHandler tHandler = iconPanel.getTransferHandler();
        tHandler.exportAsDrag(iconPanel, mEvt, TransferHandler.COPY);
    }

    private String addTransferHandler(JComponent component, java.util.List<String> selectedItems) {
        try {
            component.setTransferHandler(new ListTransferHandler(this, selectedItems));
        } catch (FileNotFoundException e) {
            return e.getMessage();
        }
        return null;
    }

    public void setMousePressed(boolean mousePressed) {
        isMousePressed = mousePressed;
    }

    public static void main(String[] args) {
        new ReceiveFileGUI();
    }
}
