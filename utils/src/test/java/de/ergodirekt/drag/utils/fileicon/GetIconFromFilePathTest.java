package de.ergodirekt.drag.utils.fileicon;

import org.junit.Assert;
import org.junit.Test;

import javax.swing.ImageIcon;

public class GetIconFromFilePathTest {
    private String validPath = System.getProperty("user.dir");
    private String validPath2 = ".";
    private String notValidPath = null;

    @Test
    public void getIconFromFilePath() {
        ImageIcon iconFromPath1;
        ImageIcon iconFromPath2;
        ImageIcon iconFromPath3;

        try {
            iconFromPath1 = GetIconFromFilePath.getIconFromFilePath(validPath, 100, 100);
            Assert.assertNotNull(iconFromPath1);

            iconFromPath2 = GetIconFromFilePath.getIconFromFilePath(validPath2, 100, 100);
            Assert.assertNotNull(iconFromPath2);

        } catch (DateiExistiertNichtException e) { Assert.fail("Icon sollte bekommen werden, Pfad ist richtig!"); }

        try {
            iconFromPath3 = GetIconFromFilePath.getIconFromFilePath(notValidPath, 100, 100);
            Assert.assertNull(iconFromPath3);

            Assert.fail("Icon sollte nicht bekommen werden, Pfad ist falsch!");
        } catch (DateiExistiertNichtException | NullPointerException e) { /* Hier ist alles richig */ }
    }

    @Test
    public void getIconFromFilePathWithNoArguments() {
        ImageIcon iconFromPath1;
        ImageIcon iconFromPath2;
        ImageIcon iconFromPath3;

        try {
            iconFromPath1 = GetIconFromFilePath.getIconFromFilePath(validPath);
            Assert.assertNotNull(iconFromPath1);

            iconFromPath2 = GetIconFromFilePath.getIconFromFilePath(validPath2);
            Assert.assertNotNull(iconFromPath2);

            // Hier ist alles richtig
        } catch (DateiExistiertNichtException e) { Assert.fail("Icon sollte bekommen werden, Pfad ist richtig!"); }

        try {
            iconFromPath3 = GetIconFromFilePath.getIconFromFilePath(notValidPath);
            Assert.assertNull(iconFromPath3);

            Assert.fail("Icon sollte nicht bekommen werden, Pfad ist falsch!");
        } catch (DateiExistiertNichtException | NullPointerException e) { /* Hier ist alles richig */ }
    }
}