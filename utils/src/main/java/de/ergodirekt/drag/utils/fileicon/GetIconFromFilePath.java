package de.ergodirekt.drag.utils.fileicon;

import me.marnic.jiconextract.extractor.IconSize;
import me.marnic.jiconextract.extractor.JIconExtractor;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public abstract class GetIconFromFilePath {
    private static final int FILEPATH = 1;
    private static File standardFile = null;
    private static BufferedImage bufferedImage;

    public static void main(String[] args) {
        try {
            getIconFromFilePath("");
        } catch (DateiExistiertNichtException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gibt das ImageIcon zur Datei - skaliert auf width und height - zurück
     * @param filePath Pfad zu der Datei
     * @param width Skalierung in x-Richtung
     * @param height Skalierung in y-Richtung
     * @return ImageIcon zum setzen auf ein Label oder Ähnliches, Standard ist ein ImageIcon von einem Ordner
     * @throws DateiExistiertNichtException, wenn die Standarddatei nicht existiert
     */
    public static ImageIcon getIconFromFilePath(String filePath, int width, int height) throws DateiExistiertNichtException {
        String[] filePathParts = filePath.split("\\.");
        String fileEnding = "." + (filePathParts.length > 1 ? filePathParts[filePathParts.length - 1] : "");

        if(!fileEnding.equals(".")) bufferedImage = JIconExtractor.getJIconExtractor().extractIconFromFile(fileEnding, IconSize.JUMBO);
        else bufferedImage = getStandardBufferedImage();

        return new ImageIcon(width == 0 || height == 0 ? bufferedImage : bufferedImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH), null);
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

    // Returnt ein Standard-BufferedImage, wenn kein anderes Icon existiert
    private static BufferedImage getStandardBufferedImage() throws DateiExistiertNichtException {
        if (bufferedImage == null){
            standardFile = getStandardFile();
            try {
                bufferedImage = ImageIO.read(standardFile);
            } catch (IOException | IllegalArgumentException e) {
                throw new DateiExistiertNichtException("ICO für Standarddatei existiert nicht!");
            }
        }
        return bufferedImage;
    }

    private static File getStandardFile() {
        URL url = GetIconFromFilePath.class.getClassLoader().getResource("icons/folder.png");
        return standardFile == null ? (url == null ? null : new File(url.toString().split("^file:/")[FILEPATH]) ) : standardFile;
    }
}