package ui;


import model.Event;
import model.EventLog;
import model.Item;
import model.Order;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

//Restaurant ordering application
public class OrderApp {
    private static final String JSON_STORE = "./data/order.json";
    private Order order;
    private Scanner input;
    private String name;
    private Item coke;
    private Item juice;
    private Item chickenWing;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: run the ordering application
    public OrderApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runOrderApp();

    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runOrderApp() {
        boolean keepGoing = true;
        String command;
        init();
        System.out.println("Enter name on order:");
        name = input.next();
        order = new Order(name);

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                cancelOrder();
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nThank you! Goodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    public void processCommand(String command) {
        if (command.equals("ac")) {
            addItem("c");
        } else if (command.equals("aw")) {
            addItem("w");
        } else if (command.equals("aj")) {
            addItem("j");
        } else if (command.equals("rc")) {
            removeItem("c");
        } else if (command.equals("rw")) {
            removeItem("w");
        } else if (command.equals("rj")) {
            removeItem("j");
        } else if (command.equals("p")) {
            makeAPayment();
        } else if (command.equals("vo")) {
            printOrder();
        } else if (command.equals("s")) {
            saveOrder();
        } else if (command.equals("l")) {
            loadOrder();
        } else {
            System.out.println("Invalid input...");
        }
    }

    // EFFECTS: initialize items and order
    public void init() {
        coke = new Item("coke");
        juice = new Item("juice");
        chickenWing = new Item("chicken wings");

        coke.setPrice(3.0);
        juice.setPrice(4.0);
        chickenWing.setPrice(10.0);


        coke.setDescription("This is coke");
        juice.setDescription("This is juice");
        chickenWing.setDescription("This is chicken wings");

        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    public void displayMenu() {
        System.out.println("\n Select items from:");
        System.out.println("\tac -> add coke");
        System.out.println("\taw -> add chicken wings");
        System.out.println("\taj -> add juice");
        System.out.println("\trc -> remove coke");
        System.out.println("\trw -> remove chicken wings");
        System.out.println("\trj -> remove juice");
        System.out.println("\tvo -> view my order");
        System.out.println("\tp -> make a payment");
        System.out.println("\ts -> save order to file");
        System.out.println("\tl -> load order from file");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: add items to the order
    public void addItem(String command) {
        if (command.equals("c")) {
            order.addItem(coke);
            System.out.println("Successfully added " + coke.getItemName());
        } else if (command.equals("w")) {
            order.addItem(chickenWing);
            System.out.println("Successfully added " + chickenWing.getItemName());
        } else if (command.equals("j")) {
            order.addItem(juice);
            System.out.println("Successfully added " + juice.getItemName());
        }
    }

    // EFFECTS: remove item by name
    public void removeItem(String command) {
        if (command.equals("c")) {
            order.removeItem(coke);
            System.out.println("Successfully removed " + coke.getItemName());
        } else if (command.equals("w")) {
            order.removeItem(chickenWing);
            System.out.println("Successfully removed " + chickenWing.getItemName());
        } else if (command.equals("j")) {
            order.removeItem(juice);
            System.out.println("Successfully removed " + juice.getItemName());
        }
    }

    // EFFECTS: pay the total price of the order
    public void makeAPayment() {
        if (order.totalPrice() == 0.0) {
            System.out.println("Add items first!");
        } else {
            order.makeAPayment();
            System.out.println("Payment complete!");
        }
    }

    // EFFECTS: cancel the order, clear all items and prices
    public void cancelOrder() {
        order.cancelOrder();
        System.out.println("The order has been cancelled!");
    }

    // EFFECTS: prints all the thingies in order to the console
    public void printOrder() {
        System.out.println("Order: " + order.getOrderName());
        List<Item> items = order.getItemList();

        for (Item i : items) {
            System.out.println(i);
        }

        System.out.println("Total price: $" + order.totalPrice());
        System.out.println("Payment status: " + order.isPaid());
    }


    // Code source from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: saves the order to file
    public void saveOrder() {
        try {
            jsonWriter.open();
            jsonWriter.write(order);
            jsonWriter.close();
            System.out.println("Saved order#" + order.getOrderName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // Code source from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: loads workroom from file
    public void loadOrder() {
        try {
            order = jsonReader.read();
            System.out.println("Loaded order#" + order.getOrderName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
