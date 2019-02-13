package de.ergodirekt.drag.utils.fileicon;

import org.junit.Assert;
import org.junit.Test;

import javax.swing.ImageIcon;

public class GetIconFromFilePathTest {
    private String validPath = System.getProperty("user.dir");
    private String validPath2 = ".";
    private String notValidPath = null;

    private ImageIcon iconFromPath1 = null;
    private ImageIcon iconFromPath2 = null;
    private ImageIcon iconFromPath3 = null;

    @Test
    public void getIconFromFilePath() {
        try {
            iconFromPath1 = GetIconFromFilePath.getIconFromFilePath(validPath, 100, 100);
            iconFromPath2 = GetIconFromFilePath.getIconFromFilePath(validPath2, 100, 100);
            // Hier ist alles richtig
        } catch (DateiExistiertNichtException e) { Assert.fail("Icon sollte bekommen werden, Pfad ist richtig!"); }

        // Diese beiden Statements dürfen nicht null sein, da sie von einen Pfad kommen, der richtig ist
        Assert.assertNotNull(iconFromPath1);
        Assert.assertNotNull(iconFromPath2);

        try {
            iconFromPath3 = GetIconFromFilePath.getIconFromFilePath(notValidPath, 100, 100);
            Assert.fail("Icon sollte nicht bekommen werden, Pfad ist falsch!");
        } catch (DateiExistiertNichtException | NullPointerException e) { /* Hier ist alles richig */ }

        // Soll null sein, da der Pfad nicht richtig war
        Assert.assertNull(iconFromPath3);
    }

    @Test
    public void getIconFromFilePathWithNoArguments() {
        try {
            iconFromPath1 = GetIconFromFilePath.getIconFromFilePath(validPath);
            iconFromPath2 = GetIconFromFilePath.getIconFromFilePath(validPath2);
            // Hier ist alles richtig
        } catch (DateiExistiertNichtException e) { Assert.fail("Icon sollte bekommen werden, Pfad ist richtig!"); }

        // Diese beiden Statements dürfen nicht null sein, da sie von einen Pfad kommen, der richtig ist
        Assert.assertNotNull(iconFromPath1);
        Assert.assertNotNull(iconFromPath2);

        try {
            iconFromPath3 = GetIconFromFilePath.getIconFromFilePath(notValidPath);
            Assert.fail("Icon sollte nicht bekommen werden, Pfad ist falsch!");
        } catch (DateiExistiertNichtException | NullPointerException e) { /* Hier ist alles richig */ }

        // Soll null sein, da der Pfad nicht richtig war
        Assert.assertNull(iconFromPath3);
    }
}