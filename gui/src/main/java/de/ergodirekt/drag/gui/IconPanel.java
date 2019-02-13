package de.ergodirekt.drag.gui;

import de.ergodirekt.drag.utils.fileicon.DateiExistiertNichtException;
import de.ergodirekt.drag.utils.fileicon.GetIconFromFilePath;

import javax.swing.*;
import java.awt.*;

class IconPanel {
    private boolean clicked = true;
    private JPanel iconPanel;
    private String filePath;
    private static final Color CLICKED_COLOR = new Color(0x6076afe8);
    private static final Color UNCLICKED_COLOR = null;
    static final int ICON_WIDTH = 90;

    IconPanel(String filePath) throws DateiExistiertNichtException {
        this.filePath = filePath;
        iconPanel = new JPanel(new BorderLayout());
        iconPanel.add(new JLabel(GetIconFromFilePath.getIconFromFilePath(filePath, 70, 70)), BorderLayout.NORTH);
        String[] filePathParts = filePath.replace("\\", "/").split("/");
        iconPanel.add(new JLabel(filePathParts[filePathParts.length-1], SwingConstants.CENTER), BorderLayout.CENTER);
        iconPanel.setPreferredSize(new Dimension(ICON_WIDTH,100));
        iconPanel.setBackground(CLICKED_COLOR);
        iconPanel.setToolTipText(filePath);
    }

    void switchClicked() {
        this.clicked = !this.clicked;
        iconPanel.setBackground(clicked ? CLICKED_COLOR : UNCLICKED_COLOR);
    }

    JPanel getAsJPanel() {
        return iconPanel;
    }

    boolean isClicked() {
        return clicked;
    }

    String getFilePath() {
        return filePath;
    }
}
