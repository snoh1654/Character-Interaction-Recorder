package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new ChampionsGuideApp();
        } catch (FileNotFoundException e) {
            System.out.println("Error. File location cannot be found.");
        }
    }
}
