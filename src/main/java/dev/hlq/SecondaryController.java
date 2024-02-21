package dev.hlq;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SecondaryController {
    @FXML
    private Button resolution1;
    @FXML
    private Button resolution2;

    private static Scene scene;

    public void resolution1Pressed() throws IOException{
        Stage mainStage = new Stage();
        scene = new Scene(App.loadFXML("Main"));
        mainStage.setScene(scene);
        mainStage.show();
        mainStage.setResizable(false);
    }
    public void resolution2Pressed() throws IOException{
        Stage mainStage = new Stage();
        scene = new Scene(App.loadFXML("Larger"));
        mainStage.setScene(scene);
        mainStage.show();
        mainStage.setResizable(false);
    }
}
