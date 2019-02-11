package de.ergodirekt.drag.gui.dragAndDropTest;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DragInDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox dragTarget = new VBox(); //Panel, auf welches gedraggt wird.
        dragTarget.setOnDragOver( //Bei Drag auf Panel. Ist nötig, damit Dateien akzeptiert werden können.
                event -> {
                    if (event.getGestureSource() != dragTarget
                            && event.getDragboard().hasFiles()) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    }
                    event.consume();
                }
        );

        dragTarget.setOnDragDropped( //Bei Drop auf Panel.
                event -> {
                    Dragboard db = event.getDragboard();
                    boolean success = false;
                    if (db.hasFiles()) {
                        System.out.println(db.getFiles().toString());
                        success = true;
                    }
                    event.setDropCompleted(success);

                    event.consume();
                }
        );

        //Nur für Testzwecke
        StackPane root = new StackPane();
        root.getChildren().add(dragTarget);

        Scene scene = new Scene(root, 500, 250);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}