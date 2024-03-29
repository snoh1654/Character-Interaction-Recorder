package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Application containing information about Champions from League of Legends
public class ChampionsGuideConsole {
    private static final String JSON_LOCATION = "./data/championsGuide.json";

    private Scanner input;
    private ChampionCollection champions;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the Champions Guide application
    public ChampionsGuideConsole() throws FileNotFoundException {
        runChampionGuide();
    }

    // MODIFIES: this
    private void runChampionGuide() throws FileNotFoundException {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nProgram Closed.");

        for (Event next : EventLog.getInstance()) {
            System.out.println("\n" + next);
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the scanner and Champion Collection
    private void init() throws FileNotFoundException {
        input = new Scanner(System.in);
        champions = new ChampionCollection();
        jsonWriter = new JsonWriter(JSON_LOCATION);
        jsonReader = new JsonReader(JSON_LOCATION);
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void processCommand(String command) {
        if (command.equals("a")) {
            addChampion();
        } else if (command.equals("r")) {
            removeChampion();
        } else if (command.equals("e")) {
            editChampionInfo();
        } else if (command.equals("c")) {
            addChampionInteraction();
        } else if (command.equals("m")) {
            modifyChampionInteraction();
        } else if (command.equals("v")) {
            viewChampionInfo();
        } else if (command.equals("t")) {
            checkAvailableChampions();
        } else if (command.equals("s")) {
            saveChampionsGuide();
        } else if (command.equals("l")) {
            loadChampionsGuide();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: displays the menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t a -> add Champion");
        System.out.println("\t r -> remove Champion");
        System.out.println("\t e -> edit a Champion's Information");
        System.out.println("\t c -> add information about a Champion's interaction with an opposing Champion");
        System.out.println("\t m -> modify information regarding a Champion's interaction with an opposing Champion");
        System.out.println("\t v -> view Champion Info");
        System.out.println("\t t -> check list of added champions");
        System.out.println("\t s -> save Champions Guide");
        System.out.println("\t l -> load Champions Guide");
        System.out.println("\t q -> quit");
    }

    // MODIFIES: this
    // EFFECTS: adds a new Champion that the user can view
    private void addChampion() {
        System.out.println("Write the name of the Champion you want to add.");
        String championName = input.nextLine();
        System.out.println("Give a brief overview of what the champion does.");
        String championDescription = input.nextLine();
        System.out.println("How difficult is this Champion from a scale of 1-10?.");
        int difficulty = input.nextInt();
        input.nextLine();
        Champion newChampion = new Champion(championName, difficulty);
        newChampion.setChampionInfo(championDescription);
        champions.addChampion(newChampion);

        System.out.println("New Champion Added to Collection to View. To add its interactions with other champions, "
                + "press e.");
    }

    // MODIFIES: this
    // EFFECTS: removes a champion from the viewing list
    private void removeChampion() {
        System.out.println("Please precisely write the name of the Champion you want to delete. If you want to cancel "
                + "this process, press c");
        String deleteChampionName = input.nextLine();
        if (!deleteChampionName.equals("c")) {
            System.out.println("Are you sure you want to delete" + deleteChampionName + "?"
                    + "\nInput y to finalize your decision");
            String finalDecision = input.nextLine();
            if (finalDecision.equals("y")) {
                System.out.println(champions.removeChampion(deleteChampionName));
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: edits a champion's information
    private void editChampionInfo() {
        System.out.println("Enter the name of the Champion that you want to edit.");
        String championName = input.nextLine();
        System.out.println("Enter what you want to change " + championName + "'s information to.");
        String newInfo = input.nextLine();
        System.out.println(champions.changeChampionInfo(championName, newInfo));
    }

    // MODIFIES: this
    // EFFECTS: adds a champion's interaction with an opposing champion
    private void addChampionInteraction() {
        System.out.println("Enter the name of the Champion you want to add interaction information to.");
        String championName = input.nextLine();
        System.out.println("Enter the name of the Opposing Champion.");
        String opposingChampionName = input.nextLine();
        System.out.println("Enter the difficulty rating playing against the Opposing Champion from a scale of 1-10.");
        int difficulty = input.nextInt();
        input.nextLine();
        OpposingChampion newOpposingChampion = new OpposingChampion(opposingChampionName, difficulty);
        System.out.println(champions.createChampionInteraction(championName, newOpposingChampion));
    }

    // MODIFIES: this
    // EFFECTS: edits a champion's interaction with an opposing champion
    private void modifyChampionInteraction() {
        System.out.println("Enter the name of the Champion that you want to modify.");
        String championName = input.nextLine();
        System.out.println("Enter the name of the Opposing Champion.");
        String opposingChampionName = input.nextLine();
        System.out.println("Enter the new interaction information between the champion and its opposition.");
        String newInteractionInfo = input.nextLine();
        System.out.println(champions.changeChampionInteraction(championName, opposingChampionName, newInteractionInfo));
    }

    // EFFECTS: shows information about a champion
    private void viewChampionInfo() {
        System.out.println("Please precisely write the name of the Champion you want to view.");
        String championName = input.nextLine();
        System.out.println(champions.viewChampion(championName));
    }

    // EFFECTS: shows a list of champions available to view
    private void checkAvailableChampions() {
        System.out.println("List of Available Options:");
        System.out.println(champions.listAvailableChampions());
    }

    // EFFECTS: saves the champions guide to file at JSON_LOCATION
    private void saveChampionsGuide() {
        try {
            jsonWriter.begin();
            jsonWriter.write(champions);
            jsonWriter.close();
            System.out.println("Saved Champion Guide to " + JSON_LOCATION);
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist at that location");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads champions guide from file at JSON_LOCATION
    private void loadChampionsGuide() {
        try {
            this.champions = jsonReader.read();
            System.out.println("Loaded Champions Guide from " + JSON_LOCATION);
        } catch (IOException e) {
            System.out.println("Unable to read from file at " + JSON_LOCATION);
        }
    }
}