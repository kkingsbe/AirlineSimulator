package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StartScreenController {

    public TextField airlineNameInput;
    public ComboBox homeAirportSelector;
    public Button startGameBtn;
    String airlineName;
    String homeAirport;
    String[] Template;
    int lineLen;
    String[] airports;

    public void initialize(){
        airports = getAirports();

    }

    private String[] getAirports() {
        String allAirportData = "";
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("Resources/airports"));
            Template = reader.readLine().split(",");
            lineLen = Template.length;
            System.out.println("Template file has " + lineLen + " fields");
            allAirportData = Files.readAllLines(Paths.get("Resources/airports")).toString();
            System.out.println(allAirportData);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return allAirportData.split(",");
    }

    public void startGame(ActionEvent actionEvent) {
        airlineName = airlineNameInput.getText();
        homeAirport = homeAirportSelector.getValue().toString();
    }
}
