package de.ergodirekt.drag;

import de.ergodirekt.drag.gui.ReceiveFileGUI;
import de.ergodirekt.drag.gui.SendFileGUI;
import de.ergodirekt.drag.utils.Datei;
import de.ergodirekt.drag.utils.DirCreater;

import java.util.Properties;

public class ApplicationStarter {
    public static void main(String[] args) {
        Properties properties = Datei.getProperties();
        new SendFileGUI(properties.getProperty("destinationFolder"));
        DirCreater.createDir(properties.getProperty("austauschOrdner"));
        new ReceiveFileGUI(properties.getProperty("austauschOrdner")); //TODO Ersetzen durch Pfad auf eigenen Ordner
    }
}
