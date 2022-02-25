package persistence;

import org.json.JSONObject;

public interface JsonCreator {
    // EFFECTS: returns this as a JSON Object
    public JSONObject toJson();
}
