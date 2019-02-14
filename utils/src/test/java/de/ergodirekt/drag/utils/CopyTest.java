package de.ergodirekt.drag.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class CopyTest {

    @Test
    public void copyTest() {
        String srcFolder = "C:\\Users\\Administrator\\Desktop\\hello.png";
        String destFolder = "C:\\Users\\Administrator\\Documents\\newHello.png";

        try{
            Copy.copy(srcFolder, destFolder);
        } catch (DragException e) {
        Assert.fail("Sollte kein Fehler sein!");
        }

        srcFolder = "";
        try{
            Copy.copy(srcFolder, destFolder);
            Assert.fail("Sollte Fehler sein!");
        } catch (DragException e) {
            // Alles richtig
        }
    }
}