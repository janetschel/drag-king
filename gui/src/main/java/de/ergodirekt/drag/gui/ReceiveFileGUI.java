package de.ergodirekt.drag.gui;

import de.ergodirekt.drag.logic.ListTransferHandler;
import de.ergodirekt.drag.utils.FileWatcher;
import de.ergodirekt.drag.utils.FileWatcherListener;
import de.ergodirekt.drag.utils.Files;
import de.ergodirekt.drag.utils.fileicon.DateiExistiertNichtException;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.*;

public class ReceiveFileGUI implements FileWatcherListener{
    private static final int ICONS_PER_ROW = 4;
    private String filePath;
    private JLabel statusLabel;
    private IconPanel[] iconList;
    private JPanel iconsPanel;
    private boolean isMousePressed = false;
    private StringBuilder errorMessage = new StringBuilder();

    public ReceiveFileGUI(String filePath) {
        this.filePath = filePath;
        new FileWatcher(filePath, this);
    }

    private JScrollPane getCenter(String filePath) {
        final int INSET = 5;
        iconsPanel = new JPanel();
        iconsPanel.setLayout(new GridBagLayout());

        java.util.List<String> filePaths = Files.getFilePathsFromDirectory(filePath);

        java.util.List<GridBagConstraints> gbcList = new ArrayList<>();

        int helper = ((float) filePaths.size() / ICONS_PER_ROW) % 1 == 0 ? filePaths.size() / ICONS_PER_ROW : filePaths.size() / ICONS_PER_ROW + 1;
        GridBagConstraints gbc;
        for (int j = 0; j < helper; j++) {
            for (int i = 0; i < ICONS_PER_ROW; i++) {
                gbc = new GridBagConstraints();
                gbc.gridx = i;
                gbc.gridy = j;
                gbc.insets = new Insets(INSET, INSET, INSET, INSET);
                gbcList.add(gbc);
            }
        }

        iconList = new IconPanel[filePaths.size()];
        for (int i = 0; i < filePaths.size(); i++) {
            int iconNumber = i;
            try {
                iconList[i] = new IconPanel(filePaths.get(i));
                iconList[i].addMouseListener(new MouseAdapter() {
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
            } catch (DateiExistiertNichtException e) {
                errorMessage.append(errorMessage.toString().equals("") ? ListTransferHandler.ERROR_MESSAGE : "");
                errorMessage.append(filePaths.get(i)).append("<br/>");
            }
        }

        if (!errorMessage.toString().equals("")) {
            statusLabel.setText(errorMessage.toString() + "</html>");
        } else {


            for (int i = 0; i < iconList.length; i++) {
                iconsPanel.add(iconList[i], gbcList.get(i));
            }
        }

        JScrollPane iconScrollPane = new JScrollPane(iconsPanel);
        iconScrollPane.setPreferredSize(
                new Dimension(
                        ICONS_PER_ROW*(IconPanel.ICON_WIDTH + 2*INSET)
                                + 20 /*Spacing an Seite*/,
                        400));
        iconScrollPane.getVerticalScrollBar().setUnitIncrement(20);
        return(iconScrollPane);
    }

    private void initFrame() {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());

        statusLabel = new JLabel();

        frame.add(getCenter(filePath), BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Ihnen wurden Dateien geschickt!");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
        frame.setAlwaysOnTop(false);
    }

    private void dragNDrop(MouseEvent mEvt) {
        java.util.List<String> selectedItems = new ArrayList<>();
        for (IconPanel icon : iconList) {
            if (icon.isClicked()) {
                selectedItems.add(icon.getFilePath());
            }
        }
        statusLabel.setText(addTransferHandler(iconsPanel, selectedItems));
        TransferHandler tHandler = iconsPanel.getTransferHandler();
        tHandler.exportAsDrag(iconsPanel, mEvt, TransferHandler.COPY);
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

    @Override
    public void hasFileChanged(boolean folderChanged) {
        initFrame();
    }
}
