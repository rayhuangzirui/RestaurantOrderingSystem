package model;

import java.util.ArrayList;

// Represents an order having an order number, a list of items ordered, the total price of the items
// and if the order is paid or not
public class Order {
    private static int nextOrderNum = 1;
    private int orderNum;
    ArrayList<Item> itemArrayList;
    private double totalPrice;
    private boolean isPaid;

    // REQUIRES: orderNum is a positive number
    // EFFECTS: the orderNum increment by 1 as the nextOrderNum increment;
    //          create a new empty item list
    public Order() {
        itemArrayList = new ArrayList<>();
    }

    // EFFECTS: return to order number
    public int getOrderNum() {
        orderNum = nextOrderNum++;
        return orderNum;
    }

    // REQUIRES: The item list is not empty, the index of item is smaller or equal to the item list size;
    // MODIFIES: this
    // EFFECTS: get the item name from the item list by calling its index;
    //          if the list is empty, return null;
    public String getItemName(int itemIndex) {
        if (isEmpty()) {
            return null;
        }

        return itemArrayList.get(itemIndex).getItemName();
    }

    // MODIFIES: this
    // EFFECTS: add an item to the item list, the item can be repeated;
    public void addItem(Item item) {
        itemArrayList.add(item);
    }

    // MODIFIES: this
    // EFFECTS: remove an item from the list
    public void removeItem(Item item) {
        itemArrayList.remove(item);
    }

    // EFFECTS: if the item list is empty, return true, false otherwise;
    public boolean isEmpty() {
        return itemArrayList.isEmpty();
    }

    // REQUIRES: the total price should not be negative
    // EFFECTS: set initial total price as 0.0, if the item list is not empty,
    //          let total price be the sum of the prices of each item in the list;
    //          return the total price
    public double totalPrice() {
        totalPrice = 0.0;
        if (!itemArrayList.isEmpty()) {
            for (Item e : itemArrayList) {
                totalPrice = totalPrice + e.getPrice();
            }
        }
        return totalPrice;
    }

    //  EFFECTS: set isPaid = true and totalPrice = 0.0;
    public void makeAPayment() {
        isPaid = true;
        totalPrice = 0.0;
        itemArrayList.clear();
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
            itemArrayList.clear();
        }
    }

    // EFFECTS: return a string representation of order
    @Override
    public String toString() {
        String totalPriceStr = String.format("%.2f", totalPrice);
        return "[orderNum = " + orderNum
                + ", itemArrayList = " + itemArrayList
                + ", totalPrice = $" + totalPriceStr
                + ", isPaid = " + isPaid + ']';
    }
}
