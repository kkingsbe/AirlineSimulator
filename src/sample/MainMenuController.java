package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    public void switchToBuyVehicles(ActionEvent actionEvent) throws IOException {
        Parent buyAircraftParent = FXMLLoader.load(getClass().getResource("Purchase Aircraft.fxml"));
        Scene buyAircraftScene = new Scene(buyAircraftParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(buyAircraftScene);
        window.show();
    }
}
