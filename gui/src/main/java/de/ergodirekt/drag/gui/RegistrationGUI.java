package de.ergodirekt.drag.gui;

import de.ergodirekt.drag.utils.DragProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RegistrationGUI {
    public RegistrationGUI (DragProperties properties) {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        JLabel label = new JLabel("Bitte registrieren Sie sich!");
        frame.add(label, BorderLayout.NORTH);
        JTextField username = new JTextField(20);
        username.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                properties.addToProperties("transferFolder", (properties.getProperties().getProperty("destinationFolder")) + "\\" + username.getText());
                properties.addToProperties("loggedInUser", username.getText());
                Path transferFolderPath = Paths.get(properties.getProperty("transferFolder"));
                transferFolderPath.toFile().mkdirs();
                frame.dispose();
            }
        });
        frame.add(username);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
