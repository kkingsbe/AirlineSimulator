package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class PurchaseAircraftController {
    public Label aircraftSpecsLabel;
    public String Template;
    public int lineLen;
    public String allAircraftData;
    public int lines;
    public static String newline = System.getProperty("line.separator");
    public ComboBox aircraftSelector;
    public ImageView aircraftImage;
    public Label planeDescription;
    public String[][] aircraft;
    public Button purchaseAircraftBtn;
    public Label costLabel;

    public void initialize(){

        getAircraftData();

    }

    private void getAircraftData() {
        BufferedReader reader;
        
        try {
            reader = new BufferedReader(new FileReader("Resources/aircraft"));
            Template = reader.readLine();
            lineLen = Template.split(",").length;
            System.out.println("Template file has " + lineLen + " fields");
            aircraft = new String[lineLen][lines];
            allAircraftData = Files.readAllLines(Paths.get("Resources/aircraft")).toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        lines = allAircraftData.split(";").length;

        aircraft = new String[lineLen][lines];

        for (int line = 1; line < lines; line++){
            String currentLine = null;

            try{
                currentLine = allAircraftData.split(";")[line].substring(2);
                System.out.println(currentLine);
            } catch (java.lang.StringIndexOutOfBoundsException e) {}

            try{
                if (currentLine != null) {
                    System.out.println(line);
                    aircraft[line] = currentLine.split(",");
                }
            } catch(NullPointerException e){
                System.out.println("OOF");
            } catch (java.lang.ArrayIndexOutOfBoundsException e){}

        }
        populateComboBox(aircraft);
    }

    public void infoUpdate(ActionEvent actionEvent) {
        updateImage(aircraftSelector.getValue().toString());
        int plane = planeLookUp(aircraftSelector.getValue().toString());
        updateAircraftData(aircraft[plane]);
        costLabel.setText("Cost: " + aircraft[plane][7]);
    }

    private int planeLookUp(String planeString) {
        int planeIndex = 0;
        for (int x = 1; x < lines;x++){
            if (aircraft[x][1] == planeString){
                planeIndex = x;
                break;
            }
        }
        return planeIndex;
    }

    public void populateComboBox(String [][] aircraft){
        try {
            System.out.println("combo");
            for(int row = 0; row < lines; row++)
            {
                aircraftSelector.getItems().addAll(aircraft[row][1]);
            }

        } catch (java.lang.ArrayIndexOutOfBoundsException e) {}
    }

    private void updateAircraftData(String[] aircraft) {
        String labelText = "The " + aircraft[1] + " costs " + aircraft[7] + " and has a cruising speed of " + aircraft[2] + " MPH while carrying " + aircraft[3] + " passangers. It consumes " + aircraft[5] + " at a rate of " + aircraft[4] + " galons per hour with a capacity of " + aircraft[6] + " galons.";

        /*
        for (int attribute = 1; attribute < aircraft.length;attribute++){
            try {
                labelText = labelText + aircraft[attribute];
            } catch (java.lang.ArrayIndexOutOfBoundsException ignored){}
        }
        labelText += "\n";
        */
        planeDescription.setText(labelText);

    }

    private void updateImage(String plane) {
        File file = new File("Resources/Images/"+plane+".jpg");
        System.out.println(file);
        Image airplane = null;
        try {
            airplane = new Image(file.toURI().toURL().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        aircraftImage.setImage(airplane);
    }

    public void purchaseAircraft(ActionEvent actionEvent) {

    }
}