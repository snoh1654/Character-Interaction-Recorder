package model;

import java.util.ArrayList;
// A collection of champions that the user can find information about.

public class ChampionCollection {
    private ArrayList<Champion> championsGuide;

    // EFFECTS: Constructs an empty champion collection
    public ChampionCollection() {
        this.championsGuide = new ArrayList<>();
    }

    // REQUIRES: champion must not already be in championGuides
    // MODIFIES: this
    // EFFECTS: adds a new champion that the user can learn about to the collection
    public void addChampion(Champion champion) {
        this.championsGuide.add(champion);
    }

    // MODIFIES: this
    // EFFECTS: if championName is the name of a Champion in the collection, remove the Champion with that name from
    // the collection and return "Removed the Champion." Otherwise, produce "That Champion is not in the Collection."
    public String removeChampion(String championName) {
        for (int i = 0; i < this.championsGuide.size(); i++) {
            if (this.championsGuide.get(i).getName().equals(championName)) {
                this.championsGuide.remove(i);
                return "Removed the Champion.";
            }
        }
        return "That Champion is not in the Collection.";
    }

    // EFFECTS: returns the name of all Champions in the collection
    public String listAvailableChampions() {
        String listOfNames = "";
        for (Champion champion : this.championsGuide) {
            listOfNames = listOfNames + champion.getName() + ", ";
        }
        int stringEnd = listOfNames.length() - 2;
        return listOfNames.substring(0, stringEnd);
    }

    public String viewChampion(String championName) {
        String championInfo = "That Champion is not in the Collection.";
        for (Champion champion : championsGuide) {
            if (champion.getName().equals(championName)) {
                championInfo = championName + "\nDifficulty: " + champion.getDifficulty()
                        + "\nInfo: " + champion.getChampionInfo()
                        + "\nInteractions with Other Champions:\n"
                        + champion.viewChampionInteraction();
                return championInfo;
            }
        }
        return championInfo;
    }

    public ArrayList<Champion> getChampionsGuide() {
        return championsGuide;
    }
}
