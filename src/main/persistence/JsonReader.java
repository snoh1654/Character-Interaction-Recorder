package persistence;

import model.Champion;
import model.ChampionCollection;
import model.OpposingChampion;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// Represents a reader that interprets JSON information from a file into a ChampionCollection object
public class JsonReader {
    private String fileLocation;


    // EFFECTS: constructs a reader that interprets information from file at fileLocation
    public JsonReader(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    // EFFECTS: reads file at fileLocation and returns a ChampionCollection object from the JSON data,
    //          throws IOException if error occurs while reading the data from the file at fileLocation
    public ChampionCollection read() throws IOException {
        JSONObject jsonObject = new JSONObject(readFile(fileLocation));
        ChampionCollection championCollection = toChampionCollection(jsonObject);
        return championCollection;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();

        // Method taken from JSONReader class in
        // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    }

    // EFFECTS: converts JSON data into a ChampionCollection object and returns it
    private ChampionCollection toChampionCollection(JSONObject jsonObject) {
        ChampionCollection championCollection = new ChampionCollection();
        addChampionData(championCollection, jsonObject);
        return championCollection;
    }

    // MODIFIES: championCollection
    // EFFECTS: adds champions to championCollection from JSON data
    private void addChampionData(ChampionCollection championCollection, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Champions Guide");

        for (Object jsonData : jsonArray) {
            JSONObject jsonChampion = (JSONObject) jsonData;

            String championName = jsonChampion.getString("Champion Name");
            int championDifficulty = jsonChampion.getInt("Difficulty");
            Champion champion = new Champion(championName, championDifficulty);

            String championInformation = jsonChampion.getString("Champion Information");
            champion.setChampionInfo(championInformation);

            addInteractionList(jsonChampion, champion);

            championCollection.addChampion(champion);
        }
    }

    // MODIFIES: champion
    // EFFECTS: adds champion interaction for champion from JSON data
    private void addInteractionList(JSONObject jsonObject, Champion champion) {
        JSONArray jsonArray = jsonObject.getJSONArray("Interaction With Other Champions");

        for (Object jsonData : jsonArray) {
            JSONObject jsonOpposingChampion = (JSONObject) jsonData;

            String opposingChampionName = jsonOpposingChampion.getString("Opposing Champion Name");
            int opposingChampionDifficulty = jsonOpposingChampion.getInt("Difficulty To Play Against");
            OpposingChampion opposingChampion = new OpposingChampion(opposingChampionName, opposingChampionDifficulty);

            String interactionDetail = jsonOpposingChampion.getString("Interaction Detail");
            opposingChampion.setInteractionDetail(interactionDetail);

            champion.addChampionInteraction(opposingChampion);
        }
    }
}
