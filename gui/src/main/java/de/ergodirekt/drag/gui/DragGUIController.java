package de.ergodirekt.drag.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.Pane;


public class DragGUIController {

@FXML
private Pane pane1;
    @FXML
private Button senden;
    @FXML
private SplitMenuButton benutzername;

    public void doLogin(ActionEvent actionEvent) {
        System.out.println(benutzername.getText());

    }

}
