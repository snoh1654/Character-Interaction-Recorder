package ui;

import java.io.FileNotFoundException;

// Main Class used to run the console-based Champion Collection
public class MainConsole {
    public static void main(String[] args) {
        try {
            new ChampionsGuideApp();
        } catch (FileNotFoundException e) {
            System.out.println("Error. File location cannot be found.");
        }
    }
}
