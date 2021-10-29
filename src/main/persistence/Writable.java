package persistence;

import org.json.JSONObject;

// Code source from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public interface Writable {
    //EFFECTS: return this as JSON object
    JSONObject toJson();
}
