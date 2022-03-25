package ui;

import java.io.FileNotFoundException;

// Main Class used to run the GUI
public class MainGui {
    public static void main(String[] args) {
        try {
            new AppFrame();
        } catch (FileNotFoundException e) {
            System.out.println("Error. File location cannot be found.");
        }
    }
}