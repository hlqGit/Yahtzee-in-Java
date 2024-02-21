package dev.hlq;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

public class SecondaryController {

    private static Scene scene;

    @FXML public void resolution1Pressed(InputEvent e) throws IOException{
        Stage mainStage = new Stage();
        scene = new Scene(App.loadFXML("Main"));
        mainStage.setScene(scene);
        mainStage.show();
        mainStage.setResizable(false);

        final Node source = (Node) e.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    @FXML public void resolution2Pressed(InputEvent e) throws IOException{
        Stage mainStage = new Stage();
        scene = new Scene(App.loadFXML("Larger"));
        mainStage.setScene(scene);
        mainStage.show();
        mainStage.setResizable(false);

        final Node source = (Node) e.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
