package de.ergodirekt.drag.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DragGUIView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/dragGUI.fxml"));

        Scene scene = new Scene(root, 800, 500);

        primaryStage.setTitle("Drag Master");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
