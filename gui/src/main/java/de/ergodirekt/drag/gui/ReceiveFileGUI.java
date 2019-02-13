package de.ergodirekt.drag.gui;

import de.ergodirekt.drag.logic.ListTransferHandler;
import de.ergodirekt.drag.utils.Files;
import de.ergodirekt.drag.utils.fileicon.DateiExistiertNichtException;
import de.ergodirekt.drag.utils.fileicon.GetIconFromFilePath;

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
    private IconPanel[] iconList;
    private JPanel iconPanel;
    private boolean isMousePressed = false;
    private StringBuilder errorMessage = new StringBuilder();

    public ReceiveFileGUI() {
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        statusLabel = new JLabel();
        frame.add(getCenter(), BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.SOUTH);
        initFrame();
    }

    private JScrollPane getCenter() {
        final int INSET = 5;
        iconPanel = new JPanel();
        iconPanel.setLayout(new GridBagLayout());

        java.util.List<String> filePaths = Files.getFilePathsFromDirectory("C:/Users/Administrator/Desktop"); //TODO Pfad auf Laufwerk

        iconList = new IconPanel[filePaths.size()];
        for (int i = 0; i < filePaths.size(); i++) {
            int iconNumber = i;
            try {
                iconList[i] = new IconPanel(filePaths.get(i));
            } catch (DateiExistiertNichtException e) {
                errorMessage.append(errorMessage.toString().equals("") ? ListTransferHandler.ERROR_MESSAGE : "");
                errorMessage.append(filePaths.get(i)).append("<br/>");
            }

            iconList[i].getAsJPanel().addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent mEvt) {
                    new Thread(() -> {
                        isMousePressed = true;
                        while (isMousePressed) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                errorMessage.append(errorMessage.toString().equals("") ? "<html>" : "");
                                errorMessage.append("<br/>").append(e.getMessage()).append("<br/>");
                            }

                            if (isMousePressed) {
                                dragNDrop(mEvt);
                                isMousePressed = false;
                            } else {
                                iconList[iconNumber].switchClicked();
                            }
                        }
                    }).start();
                }

                @Override
                public void mouseReleased(MouseEvent mEvt) {
                    isMousePressed = false;
                }
            });
        }

        if (!errorMessage.toString().equals("")) {
            statusLabel.setText(errorMessage.toString() + "</html>");
        }

        java.util.List<GridBagConstraints> gbcList = new ArrayList<>();



        int helper = ((float) iconList.length / ICONS_PER_ROW) % 1 == 0 ? iconList.length / ICONS_PER_ROW : iconList.length / ICONS_PER_ROW + 1;
        GridBagConstraints gbc;
        for (int j = 0; j < helper; j++) {
            for (int i = 0; i < ICONS_PER_ROW; i++) {
                gbc = new GridBagConstraints();
                gbc.gridx = i;
                gbc.gridy = j;
                gbc.insets = new Insets(INSET,INSET,INSET,INSET);
                gbcList.add(gbc);
            }
        }

        for (int i = 0; i < iconList.length; i++) {
            iconPanel.add(iconList[i].getAsJPanel(), gbcList.get(i));
        }

        JScrollPane iconScrollPane = new JScrollPane(iconPanel);
        iconScrollPane.setPreferredSize(
                new Dimension(
                        ICONS_PER_ROW*(IconPanel.ICON_WIDTH + 2*INSET)
                                + 20 /*Spacing an Seite*/,
                        400));
        iconScrollPane.getVerticalScrollBar().setUnitIncrement(20);
        return(iconScrollPane);
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
