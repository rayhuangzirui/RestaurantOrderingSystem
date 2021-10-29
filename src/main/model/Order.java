package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents an order having an order number, a list of items ordered, the total price of the items
// and if the order is paid or not
public class Order implements Writable {
    private String nameOnOrder;
    private List<Item> itemList;
    private double totalPrice;
    private boolean isPaid;

    // MODIFIES: this
    // EFFECTS: create the name of the order
    //          create a new empty item list
    public Order(String nameOnOrder) {
        itemList = new ArrayList<>();
        this.nameOnOrder = nameOnOrder;
    }

    // EFFECTS: return to order number
    public String getOrderName() {
//        orderNum = nextOrderNum++;
        return nameOnOrder;
    }

    // REQUIRES: The item list is not empty, the index of item is smaller or equal to the item list size;
    // MODIFIES: this
    // EFFECTS: get the item name from the item list by calling its index;
    //          if the list is empty, return null;
    public String getItemName(int itemIndex) {
        if (isEmpty()) {
            return null;
        }

        return itemList.get(itemIndex).getItemName();
    }

    // EFFECTS: returns an unmodifiable list of items in this order
    public List<Item> getItemList() {
        return Collections.unmodifiableList(itemList);
    }

    // MODIFIES: this
    // EFFECTS: add an item to the item list, the item can be repeated;
    public void addItem(Item item) {
        itemList.add(item);
    }

    // MODIFIES: this
    // EFFECTS: remove an item from the list
    public void removeItem(Item item) {
        itemList.remove(item);
    }

    // EFFECTS: if the item list is empty, return true, false otherwise;
    public boolean isEmpty() {
        return itemList.isEmpty();
    }

    // EFFECTS: returns number of thingies in this workroom
    public int numItems() {
        return itemList.size();
    }

    // REQUIRES: the total price should not be negative
    // EFFECTS: set initial total price as 0.0, if the item list is not empty,
    //          let total price be the sum of the prices of each item in the list;
    //          return the total price
    public double totalPrice() {
        totalPrice = 0.0;
        if (!itemList.isEmpty()) {
            for (Item e : itemList) {
                totalPrice = totalPrice + e.getPrice();
            }
        }
        return totalPrice;
    }

    //  EFFECTS: set isPaid = true and totalPrice = 0.0;
    public void makeAPayment() {
        isPaid = true;
        totalPrice = 0.0;
        itemList.clear();
    }

    // EFFECTS: return the payment status
    public boolean isPaid() {
        return isPaid;
    }

    // REQUIRES: the item list is not empty
    // EFFECTS: if the item list is empty, do nothing;
    //          otherwise, set isPaid to be false, totalPrice to be 0.0, and clear the item list
    public void cancelOrder() {
        if (!isEmpty()) {
            isPaid = false;
            totalPrice = 0.0;
            itemList.clear();
        }
    }

    // EFFECTS: return a string representation of order
    @Override
    public String toString() {
        String totalPriceStr = String.format("%.2f", totalPrice);
        return "[orderName = " + nameOnOrder
                + ", itemList = " + itemList
                + ", totalPrice = $" + totalPriceStr
                + ", isPaid = " + isPaid + ']';
    }

    // Code source from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("orderName", nameOnOrder);
        json.put("totalPrice", totalPrice);
        json.put("Items", itemsToJson());
        return json;
    }

    // Code source from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: returns things in this order as a JSON array
    private JSONArray itemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item i : itemList) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }
}
