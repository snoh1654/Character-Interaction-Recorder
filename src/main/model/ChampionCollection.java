package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonCreator;

import java.util.ArrayList;
// A collection of champions that the user can find information about.

public class ChampionCollection implements JsonCreator {
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

    // MODIFIES: this
    // EFFECTS: if Champion with championName is in the collection, change its information to newInfo and return
    // "championName's information was changed." Otherwise, return "That champion does not exist in the collection."
    public String changeChampionInfo(String championName, String newInfo) {
        for (Champion champion : this.championsGuide) {
            if (champion.getName().equals(championName)) {
                champion.setChampionInfo(newInfo);
                return championName + "'s information was changed.";
            }
        }
        return "That Champion does not exist in the collection.";
    }

    // MODIFIES: this
    // EFFECTS: if a champion called championName exists within the collection, add information about its interaction
    //          with the inputted opposing champion and return "Added new Interaction Information for championName."
    //          Otherwise, return "championName does not exist in the collection."
    public String createChampionInteraction(String championName, OpposingChampion opposingChampion) {
        for (Champion champion : this.championsGuide) {
            if (champion.getName().equals(championName)) {
                champion.addChampionInteraction(opposingChampion);
                return "Added new Interaction Information for " + championName + ".";
            }
        }
        return championName + " does not exist in the collection.";
    }

    // MODIFIES: this
    // EFFECTS: change champion called championName's interaction information with an opposing Champion named
    //          opposingChampionName to newInteractionInfo and produce "Interaction Information Changed." Otherwise,
    //          return, "Either the championName or opposingChampionName does not exist."

    public String changeChampionInteraction(String championName, String opposingChampionName, String newInfo) {
        for (Champion champion: this.championsGuide) {
            if (champion.getName().equals(championName)) {
                champion.editChampionInteraction(opposingChampionName, newInfo);
                return "Interaction Information Changed.";
            }
        }
        return "Either the Champion or Opposing Champion does not exist.";
    }


    // EFFECTS: returns the name of all Champions in the collection
    public String listAvailableChampions() {
        String listOfNames = "";
        for (Champion champion : this.championsGuide) {
            listOfNames = listOfNames + champion.getName() + ", ";
        }
        return listOfNames;
    }

    // EFFECTS: returns the name, difficulty, general information and interactions with other champions of this champion
    public String viewChampion(String championName) {
        String championInfo = "That Champion is not in the Collection.";
        for (Champion champion : championsGuide) {
            if (champion.getName().equals(championName)) {
                championInfo = championName + "\nDifficulty: " + champion.getDifficulty()
                        + "\nInfo: " + champion.getChampionInfo()
                        + "\nInteractions with Other Champions:\n\n"
                        + champion.viewChampionInteraction();
                return championInfo;
            }
        }
        return championInfo;
    }

    // EFFECTS: returns collection of Champions
    public ArrayList<Champion> getChampionsGuide() {
        return championsGuide;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Champions Guide", championToJson());
        return jsonObject;
    }

    // EFFECTS: returns championsGuide as a JSON array
    public JSONArray championToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Champion c : this.championsGuide) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}
