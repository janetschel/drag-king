package de.ergodirekt.drag;

import de.ergodirekt.drag.gui.ReceiveFileGUI;
import de.ergodirekt.drag.gui.SendFileGUI;

/**
 * @author tz
 */
public class ApplicationStarter {
    public static void main(String[] args) {
        new SendFileGUI();
        new ReceiveFileGUI(System.getProperty("user.home"));
    }
}
