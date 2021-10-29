package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an item having a name, price, and description
public class Item implements Writable {
    private String itemName;
    private double price;
    private String description;

    // EFFECTS: construct an item and set its name
    public Item(String itemName) {
        this.itemName = itemName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    // EFFECTS: return a string representation of item
    @Override
    public String toString() {
        String priceStr = String.format("%.2f", price);
        return "[itemName = " + itemName + ", price = $" + priceStr + ", description = " + description + ']';
    }

    // Code source from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", itemName);
        json.put("price", price);
        json.put("description", description);
        return json;

    }
}
