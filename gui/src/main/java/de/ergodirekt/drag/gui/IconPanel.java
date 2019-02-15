package de.ergodirekt.drag.gui;

import de.ergodirekt.drag.utils.Datei;
import de.ergodirekt.drag.utils.fileicon.DateiExistiertNichtException;
import de.ergodirekt.drag.utils.fileicon.GetIconFromFilePath;

import javax.swing.*;
import java.awt.*;

class IconPanel extends JPanel {
    private boolean clicked = true;
    private String filePath;
    private static final Color CLICKED_COLOR = new Color(0x6076afe8);
    private static final Color UNCLICKED_COLOR = null;
    static final int ICON_WIDTH = 90;

    IconPanel(String filePath) throws DateiExistiertNichtException {
        this.filePath = filePath;
        new JPanel(new BorderLayout());
        add(new JLabel(GetIconFromFilePath.getIconFromFilePath(filePath, 70, 70)), BorderLayout.NORTH);

        String fileName = Datei.getFileNameFromPath(filePath);

        JLabel iconText = new JLabel(fileName, SwingConstants.CENTER);
        iconText.setPreferredSize(new Dimension(ICON_WIDTH,20));
        add(iconText, BorderLayout.CENTER);

        setPreferredSize(new Dimension(ICON_WIDTH,100));
        setBackground(CLICKED_COLOR);
        setToolTipText(fileName);
    }

    void switchClicked() {
        this.clicked = !this.clicked;
        setBackground(clicked ? CLICKED_COLOR : UNCLICKED_COLOR);
    }

    boolean isClicked() {
        return clicked;
    }

    String getFilePath() {
        return filePath;
    }
}
