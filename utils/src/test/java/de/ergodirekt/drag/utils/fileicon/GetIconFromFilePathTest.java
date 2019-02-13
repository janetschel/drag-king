package de.ergodirekt.drag.utils.fileicon;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GetIconFromFilePathTest {
    private String validPath, validPath2, notValidPath;

    @Before
    public void before() {
        validPath = System.getProperty("user.dir");
        validPath2 = ".";
        notValidPath = null;
    }

    @Test
    public void getIconFromFilePath() {
        try {
            GetIconFromFilePath.getIconFromFilePath(validPath, 100, 100);
            GetIconFromFilePath.getIconFromFilePath(validPath2, 100, 100);
            // Hier ist alles richtig
        } catch (DateiExistiertNichtException e) {
            Assert.fail("Icon sollte bekommen werden, Pfad ist richtig!");
        }


        try {
            GetIconFromFilePath.getIconFromFilePath(notValidPath, 100, 100);
            Assert.fail("Icon sollte nicht bekommen werden, Pfad ist falsch!");
        } catch (DateiExistiertNichtException | NullPointerException e) { /* Hier ist alles richig */ }
    }

    @Test
    public void getIconFromFilePathWithNoArguments() {
        try {
            GetIconFromFilePath.getIconFromFilePath(validPath);
            GetIconFromFilePath.getIconFromFilePath(validPath2);
            // Hier ist alles richtig
        } catch (DateiExistiertNichtException e) {
            Assert.fail("Icon sollte bekommen werden, Pfad ist richtig!");
        }

        try {
            GetIconFromFilePath.getIconFromFilePath(notValidPath);
            Assert.fail("Icon sollte nicht bekommen werden, Pfad ist falsch!");
        } catch (DateiExistiertNichtException | NullPointerException e) { /* Hier ist alles richig */ }
    }
}