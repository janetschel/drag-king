package de.ergodirekt.drag.services;

import me.marnic.jiconextract.extractor.IconSize;
import me.marnic.jiconextract.extractor.JIconExtractor;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class GetIconFromFilePath {
    /**
     * Gibt das ImageIcon zur Datei - skaliert auf width und height - zurück
     * @param filePath Pfad zu der Datei
     * @param width Skalierung in x-Richtung
     * @param height Skalierung in y-Richtung
     * @return ImageIcon zum setzen auf ein Label oder Ähnliches, Standard ist ein ImageIcon von einem Ordner
     * @throws DateiExistiertNichtException, wenn die Standarddatei nicht existiert
     */
    public static ImageIcon getIconFromFilePath(String filePath, int width, int height) throws DateiExistiertNichtException {
        BufferedImage image;
        String[] filePathParts = filePath.split("\\.");
        String fileEnding = "." + (filePathParts.length > 1 ? filePathParts[filePathParts.length - 1] : "");

        if(!fileEnding.equals(".")){
            image = JIconExtractor.getJIconExtractor().extractIconFromFile(fileEnding, IconSize.JUMBO);
        }else{
            try {
                image = ImageIO.read(new File("gui/src/main/java/de/ergodirekt/drag/services/ico/folder.png"));
            } catch (IOException e) {
                throw new DateiExistiertNichtException("ICO Datei für Ordner existiert nicht!");
            }
        }

        return new ImageIcon(width == 0 || height == 0 ? image : image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH), null);
    }

    /**
     * Gibt das ImageIcon zur Datei zurück
     * @param filePath Pfad zu der Datei
     * @return ImageIcon zum setzen auf ein Label oder Ähnliches
     * @throws DateiExistiertNichtException, wenn die Standarddatei nicht existiert
     */
    public static ImageIcon getIconFromFilePath(String filePath) throws DateiExistiertNichtException {
        return getIconFromFilePath(filePath, 0, 0);
    }
}