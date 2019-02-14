package de.ergodirekt.drag.gui;

import de.ergodirekt.drag.logic.ListTransferHandler;
import de.ergodirekt.drag.utils.*;
import de.ergodirekt.drag.utils.fileicon.DateiExistiertNichtException;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.*;

public class ReceiveFileGUI implements FileWatcherListener{
    private static final int ICONS_PER_ROW = 4;
    private JFrame frame;
    private String folderPath;
    private JLabel statusLabel;
    private IconPanel[] iconList;
    private JPanel iconsPanel;
    private boolean isMousePressed = false;
    private StringBuilder errorMessage = new StringBuilder();

    public ReceiveFileGUI(String folderPath) {
        this.folderPath = folderPath;
        new FileWatcher(folderPath, this);
    }

    private JScrollPane getCenter(String filePath) {
        final int INSET = 5;
        iconsPanel = new JPanel();
        iconsPanel.setLayout(new GridBagLayout());

        java.util.List<String> filePaths = Datei.getFilePathsFromDirectory(filePath);

        java.util.List<GridBagConstraints> gbcList = GridBagConstraintsCreator.createGridBagConstraints(filePaths, ICONS_PER_ROW);


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

        iconsPanel.setBackground(new Color(0xffffffff));

        JScrollPane iconScrollPane = new JScrollPane(iconsPanel);
        iconScrollPane.setPreferredSize(
                new Dimension(
                        ICONS_PER_ROW*(IconPanel.ICON_WIDTH + 2*INSET)
                                + 20 /*Spacing an Seite*/,
                        400));
        iconScrollPane.getVerticalScrollBar().setUnitIncrement(20);
        return iconScrollPane;
    }

    private void initFrame() {
        frame = new JFrame();
        frame.setLayout(new BorderLayout());


        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                int selectedOption = JOptionPane.showConfirmDialog(frame, "Möchten Sie die empfangenen Dateien von ihrem Transferlaufwerk löschen?", "Dateien löschen?", JOptionPane.YES_NO_CANCEL_OPTION);
                if (selectedOption == JOptionPane.YES_OPTION) {
                    Datei.deleteAllFilesFromDirectory(folderPath);
                    frame.dispose();
                } else if (selectedOption == JOptionPane.NO_OPTION) {
                    frame.dispose();
                }
            }
        });
        frame.setTitle("Ihnen wurden Dateien geschickt!");

        statusLabel = new JLabel();

        frame.add(getCenter(folderPath), BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.SOUTH);
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
        if (frame != null) frame.dispose();
        initFrame();
    }
}
