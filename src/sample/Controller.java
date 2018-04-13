package sample;

import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Controller {
    public Label aircraftSpecsLabel;
    public String Template;
    public int lineLen;
    public String allAircraftData;
    public int lines;
    public static String newline = System.getProperty("line.separator");

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

            allAircraftData = Files.readAllLines(Paths.get("Resources/aircraft")).toString();
            //System.out.println(allAircraftData);

        } catch (IOException e) {
            e.printStackTrace();
        }

        lines = allAircraftData.split(";").length;

        String [][] aircraft = new String[lineLen][lines];

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

        updateLabel(aircraft);

    }

    private void updateLabel(String[][] aircraft) {
        String labelText = null;
        for (int plane = 1;plane<lines;plane++){
            for (int attribute = 0; attribute < aircraft.length;attribute++){
                labelText = labelText + aircraft[plane][attribute];
            }
        }
        labelText = labelText.replace("null","").replace("[","").replace("]","\n");

        aircraftSpecsLabel.setText(labelText);
    }
}