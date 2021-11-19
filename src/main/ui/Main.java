package ui;

//import javax.swing.*;
//import java.awt.*;
import java.io.FileNotFoundException;
//import

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        new Frame();
        try {
            new OrderApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
