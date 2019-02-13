package de.ergodirekt.drag.utils.fileicon;

import me.marnic.jiconextract.extractor.IconSize;
import me.marnic.jiconextract.extractor.JIconExtractor;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class GetIconFromFilePath {
    private static final int FILEPATH = 1;

    /**
     * Gibt das ImageIcon zur Datei - skaliert auf width und height - zurück
     * @param filePath Pfad zu der Datei
     * @param width Skalierung in x-Richtung
     * @param height Skalierung in y-Richtung
     * @return ImageIcon zum setzen auf ein Label oder Ähnliches, Standard ist ein ImageIcon von einem Ordner
     * @throws DateiExistiertNichtException, wenn die Standarddatei nicht existiert
     */
    public static ImageIcon getIconFromFilePath(String filePath, int width, int height) throws DateiExistiertNichtException {
        String standardFilePath;
        BufferedImage image;
        String[] filePathParts = filePath.split("\\.");
        String fileEnding = "." + (filePathParts.length > 1 ? filePathParts[filePathParts.length - 1] : "");

        if(!fileEnding.equals(".")){
            image = JIconExtractor.getJIconExtractor().extractIconFromFile(fileEnding, IconSize.JUMBO);
        }else{
            try {
                standardFilePath = GetIconFromFilePath.class.getClassLoader().getResource("icons/folder.png").toString().split("^file:/")[FILEPATH];
                image = ImageIO.read(new File(standardFilePath));
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