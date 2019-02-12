package de.ergodirekt.drag.services;

import sun.awt.shell.ShellFolder;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

public class GetIconFromFilePath {

    public static void main(String[] args) {
        new GetIconFromFilePath();
    }

    private GetIconFromFilePath() {
        JFrame frame = new JFrame("Test");
        JLabel label = null;
        try {
            label = new JLabel(getIconFromFilePath("C:\\Users\\Unterwegs\\Documents\\GitHub\\AdventureGame\\res\\cloud.png", 100, 100));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        frame.setLayout(new BorderLayout());
        frame.add(label, BorderLayout.CENTER);
        frame.setSize(new Dimension(500, 500));
        frame.setVisible(true);
    }

    /**
     * Gibt das ImageIcon zur Datei - skaliert auf width und height - zurück
     * @param filePath Pfad zu der Datei
     * @param width Skalierung in x-Richtung
     * @param height Skalierung in y-Richtung
     * @return ImageIcon zum setzen auf ein Label oder Ähnliches
     * @throws FileNotFoundException, wenn die Datei nicht existiert
     */
    public ImageIcon getIconFromFilePath(String filePath, int width, int height) throws FileNotFoundException {
        Image image = ShellFolder.getShellFolder(new File(filePath)).getIcon(true);
        return new ImageIcon(width == 0 || height == 0 ? image : image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH), null);
    }

    /**
     * Gibt das ImageIcon zur Datei zurück
     * @param filePath Pfad zu der Datei
     * @return ImageIcon zum setzen auf ein Label oder Ähnliches
     * @throws FileNotFoundException, wenn die Datei nicht existiert
     */
    public ImageIcon getIconFromFilePath(String filePath) throws FileNotFoundException {
        return getIconFromFilePath(filePath, 0, 0);
    }
}