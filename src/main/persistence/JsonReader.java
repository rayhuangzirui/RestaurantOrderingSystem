package persistence;

import model.Order;
import model.Item;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Code source from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that reads order from JSON data stored in file
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads order from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public Order read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);

        return parseOrder(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses order from JSON object and returns it
    private Order parseOrder(JSONObject jsonObject) {
        String name = jsonObject.getString("orderName");
        Order order = new Order(name);
        //stub
        addItems(order, jsonObject);
        return order;
    }

    // MODIFIES: order
    // EFFECTS: parses items from JSON object and adds them to order
    private void addItems(Order order, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Items");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItem(order, nextItem);
        }
    }

    // MODIFIES: order
    // EFFECTS: parses items from JSON object and adds it to order
    private void addItem(Order order, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Item item = new Item(name);
        order.addItem(item);
    }
}
