package model;

import java.util.ArrayList;

// A champion with a name, difficulty rating, information about itself, and a compilation of information about
// its interaction with other champions.

public class Champion {
    private final String name;
    private final int difficulty;
    private String info;
    private ArrayList<OpposingChampion> interactionList;

    // REQUIRES: 1 >= difficulty >= 10
    // EFFECTS: Constructs a champion with given name and difficulty and an empty section for its information and
    //          interaction with other champions
    public Champion(String name, int difficulty) {
        this.name = name;
        this.difficulty = difficulty;
        this.info = "";
        this.interactionList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Adds new info about the champion's interactions with another opposing champion
    public void addChampionInteraction(OpposingChampion opposingChampion) {
        this.interactionList.add(opposingChampion);
    }

    // MODIFIES: this
    // EFFECTS: if opposingChampionName is a name of an opposing champion, removes info about that opposing champion
    //          from interaction list and return "Done!." Otherwise, return "Champion is not in Interaction List."
    public String removeChampionInteraction(String opposingChampionName) {
        for (int i = 0; i < this.interactionList.size(); i++) {
            if (this.interactionList.get(i).getName().equals(opposingChampionName)) {
                this.interactionList.remove(i);
                return "Done!";
            }
        }
        return "Champion is not in Interaction List.";
    }

    // REQUIRES: interactionList.size() > 0
    // MODIFIES: this
    // EFFECTS: if opposingChampionName is a name of an opposing champion, edits info about that opposing champion
    //          with this champion and return "Updated Interaction List." Otherwise, return
    //          "Champion is not in Interaction List."
    public String editChampionInteraction(String opposingChampionName, String addedInfo) {
        for (OpposingChampion opposingChampion : this.interactionList) {
            if (opposingChampion.getName().equals(opposingChampionName)) {
                opposingChampion.setInteractionDetail(addedInfo);
                return "Updated Interaction List.";
            }
        }
        return "Champion is not in Interaction List.";
    }

    // EFFECTS: returns interaction details of all the opposing champions
    public String viewChampionInteraction() {
        String championInteractions = "";
        for (OpposingChampion opposingChampion : this.interactionList) {
            championInteractions = championInteractions + opposingChampion.toString() + "\n\n";
        }
        return championInteractions;
    }

    // MODIFIES: this
    // EFFECTS: changes Champion's information into newInfo
    public void setChampionInfo(String newInfo) {
        this.info = newInfo;
    }

    // EFFECTS: returns champion's name
    public String getName() {
        return name;
    }

    // EFFECTS: returns champion's difficulty rating
    public int getDifficulty() {
        return difficulty;
    }

    // EFFECTS: returns general information on how to play the selected champion
    public String getChampionInfo() {
        return info;
    }

    // EFFECTS: returns champion's list of interactions with opposing champions
    public ArrayList<OpposingChampion> getInteractionList() {
        return interactionList;
    }
}
