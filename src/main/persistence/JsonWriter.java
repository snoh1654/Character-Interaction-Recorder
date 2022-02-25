package persistence;

import model.ChampionCollection;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer that converts ChampionCollection objects into JSON representations in a file
public class JsonWriter {
    private static final int SPACING = 4;

    private PrintWriter writer;
    private String fileLocation;

    // EFFECTS: constructs writer to write in the specified destination
    public JsonWriter(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    // MODIFIES: this
    // EFFECTS: creates a new file at destination and the writer is placed there;
    //          throw FileNotFoundException if the destination does not exist
    public void begin() throws FileNotFoundException {
        writer = new PrintWriter(new File(fileLocation));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of championCollection to the file at destination
    public void write(ChampionCollection championCollection) {
        JSONObject jsonObject = championCollection.toJson();
        writer.print(jsonObject.toString(SPACING));
    }

    // MODIFIES: this
    // EFFECTS: stops the writer from changing the file at destination
    public void close() {
        this.writer.close();
    }
}
