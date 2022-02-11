package model;
// An opposing champion that the user's champion compares itself to. It contains its name, difficulty to play against,
// and information about how it interacts with the user's champion.

public class OpposingChampion {
    private final String name;
    private final int difficultyToPlayAgainst;
    private String interactionDetail;

    // EFFECTS: Constructs an opposing champion with given name and rating that rates how difficult it is to play
    // against and an empty section on the details about its interaction with the user's champion.
    public OpposingChampion(String name, int difficultyToPlayAgainst) {
        this.name = name;
        this.difficultyToPlayAgainst = difficultyToPlayAgainst;
        this.interactionDetail = "";
    }

    // MODIFIES: this
    // EFFECTS: interaction of OpposingChampion with user's champion is changed to newInteractionDetail
    public void editInteractionDetail(String newInteractionDetail) {
        this.interactionDetail = newInteractionDetail;
    }

    // EFFECTS: returns the name of the opposing champion
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns the difficulty rating to play against the opposing champion as the user's champion
    public int getDifficultyToPlayAgainst() {
        return this.difficultyToPlayAgainst;
    }

    // EFFECTS: returns the details about how this opposing champion interacts with the user's selected champion
    public String getInteractionDetail() {
        return this.interactionDetail;
    }

    // Effects: returns a string representation of OpposingChampion
    public String toString() {
        return name + "\nDifficulty to Face: " + difficultyToPlayAgainst + "\nInteraction Details: "
                + interactionDetail;
    }
}
