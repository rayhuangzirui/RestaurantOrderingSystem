package ui;


import model.Item;
import model.Order;

import java.util.Scanner;

//Restaurant ordering application
public class OrderApp {
    private Order order;
    private Scanner input;
    private Item coke;
    private Item juice;
    private Item chickenWing;
    private Item fries;

    // EFFECTS: run the ordering application
    public OrderApp() {
        runOrderApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runOrderApp() {
        boolean keepGoing = true;
        String command;
        init();

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
    private void processCommand(String command) {
        if (command.equals("ac")) {
            addItem("c");
        } else if (command.equals("aw")) {
            addItem("w");
        } else if (command.equals("aj")) {
            addItem("j");
        } else if (command.equals("af")) {
            addItem("f");
        } else if (command.equals("rc")) {
            removeItem("c");
        } else if (command.equals("rw")) {
            removeItem("w");
        } else if (command.equals("rj")) {
            removeItem("j");
        } else if (command.equals("rf")) {
            removeItem("f");
        } else if (command.equals("p")) {
            makeAPayment();
        } else {
            System.out.println("Invalid input...");
        }
    }

    // EFFECTS: initialize items and order
    private void init() {
        order = new Order();

        coke = new Item("coke");
        juice = new Item("juice");
        chickenWing = new Item("chicken wings");
        fries = new Item("fries");

        coke.setPrice(3.0);
        juice.setPrice(4.0);
        chickenWing.setPrice(10.0);
        fries.setPrice(5.0);

        coke.setDescription("This is coke");
        juice.setDescription("This is juice");
        chickenWing.setDescription("This is chicken wings");
        fries.setDescription("This is fries");

        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\n Select items from:");
        System.out.println("\tac -> add coke");
        System.out.println("\taw -> add chicken wings");
        System.out.println("\taj -> add juice");
        System.out.println("\taf -> add fries");
        System.out.println("\trc -> remove coke");
        System.out.println("\trw -> remove chicken wings");
        System.out.println("\trj -> remove juice");
        System.out.println("\trf -> remove fries");
        System.out.println("\tp -> make a payment");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: add items to the order
    private void addItem(String command) {
        if (command.equals("c")) {
            order.addItem(coke);
            System.out.println("Successfully added " + coke.getItemName());
        } else if (command.equals("w")) {
            order.addItem(chickenWing);
            System.out.println("Successfully added " + chickenWing.getItemName());
        } else if (command.equals("j")) {
            order.addItem(juice);
            System.out.println("Successfully added " + juice.getItemName());
        } else if (command.equals("f")) {
            order.addItem(fries);
            System.out.println("Successfully added " + fries.getItemName());
        }
    }

    // EFFECTS: remove item by name
    private void removeItem(String command) {
        if (command.equals("c")) {
            order.removeItem(coke);
            System.out.println("Successfully removed " + coke.getItemName());
        } else if (command.equals("w")) {
            order.removeItem(chickenWing);
            System.out.println("Successfully removed " + chickenWing.getItemName());
        } else if (command.equals("j")) {
            order.removeItem(juice);
            System.out.println("Successfully removed " + juice.getItemName());
        } else if (command.equals("f")) {
            order.removeItem(fries);
            System.out.println("Successfully removed " + fries.getItemName());
        }
    }

    // EFFECTS: pay the total price of the order
    private void makeAPayment() {
        if (order.totalPrice() == 0.0) {
            System.out.println("Add items first!");
        } else {
            order.makeAPayment();
            System.out.println("Payment complete!");
        }
    }

    // EFFECTS: cancel the order, clear all items and prices
    private void cancelOrder() {
        order.cancelOrder();
        System.out.println("The order has been cancelled!");
    }
}
