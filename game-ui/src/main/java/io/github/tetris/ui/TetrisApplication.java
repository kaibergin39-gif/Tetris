package io.github.tetris.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Temporary JavaFX entry point that will eventually render immutable snapshots
 * from the core module.
 */
public final class TetrisApplication extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Tetris (prototype)");
        StackPane root = new StackPane(
                new Label("UI scaffolding in progress")
        );
        stage.setScene(new Scene(root, 480, 640));
        stage.show();
    }

    /**
     * Launches the JavaFX runtime.
     *
     * @param args standard JVM arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
