package de.ergodirekt.drag.utils;

import javax.swing.*;

public abstract class ErrorMessage {
    public static void showErrorDialog(JFrame frame, Throwable t, String message) {
        JOptionPane.showMessageDialog(frame, t.getMessage(), message, JOptionPane.ERROR_MESSAGE);
    }
}
