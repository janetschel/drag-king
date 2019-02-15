package de.ergodirekt.drag;

import de.ergodirekt.drag.gui.ReceiveFileGUI;
import de.ergodirekt.drag.gui.RegistrationGUI;
import de.ergodirekt.drag.gui.SendFileGUI;
import de.ergodirekt.drag.utils.DirCreater;
import de.ergodirekt.drag.utils.DragProperties;

public class ApplicationStarter {
    public static void main(String[] args) {
        DragProperties properties = new DragProperties();
        if (properties.getProperty("registeredUser") == null) {
            new RegistrationGUI(properties);
        }
        while (properties.getProperty("registeredUser") == null) {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        new SendFileGUI(properties.getProperty("destinationFolder"));
        new ReceiveFileGUI(properties.getProperty("transferFolder")); //TODO Ersetzen durch Pfad auf eigenen Ordner
    }
}
