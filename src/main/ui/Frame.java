package ui;

import model.Item;
import model.Order;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

// A class that use Swing to create GUI
public class Frame {
    private static final String JSON_STORE = "./data/order.json";
    private Item coke;
    private Item juice;
    private Item chickenWing;
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);

    private JPanel panel1 = new JPanel();
    private JComboBox comboBox = new JComboBox();
    private JLabel label = new JLabel("Enter the name of order: ");
    private JLabel showDetail = new JLabel();
    private JLabel showInfo = new JLabel();
    private JTextField textField1 = new JTextField(20);
    private JTextField textField2 = new JTextField(20);
    private JButton addOrder = new JButton("Add an order");
    private JButton addFood = new JButton("Add");
    private JButton removeFood = new JButton("Remove");
    private JButton viewOrder = new JButton("View order");
    private JButton save = new JButton("Save");
    private JButton load = new JButton("Load");
    private JButton back = new JButton("Back");
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel(new CardLayout());
    private JPanel panel4 = new JPanel();
    private JPanel panel5 = new JPanel();
    private CardLayout c1 = (CardLayout) (panel3.getLayout());
    private String name;
    private Order order = new Order(name);
    private JLabel image = new JLabel();

    // EFFECTS: Build a frame object to show order app in window
    // call to set panels of the frame and call action listeners
    public Frame() throws FileNotFoundException {
        init();
        JFrame frame = new JFrame("Order App");

        setPanel1();

        addOrder.addActionListener(new MyActionListener());
        setPanel2();
        setPanel3();
        setPanel4();
        setPanel5();

        addFood.addActionListener(new MyActionListener());
        removeFood.addActionListener(new MyActionListener());
        viewOrder.addActionListener(new MyActionListener());
        comboBox.addItemListener(new MyItemListener());
        save.addActionListener(new MyActionListener());
        back.addActionListener(new MyActionListener());
        load.addActionListener(new MyActionListener());

        frame.add(panel3);
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }

    // EFFECTS: set the first panel welcome page
    public void setPanel1() {
        panel1.add(label);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setBounds(230, 100, 100, 30);
        panel1.add(textField1);
        panel1.add(addOrder);
        addOrder.setFont(new Font("Arial", Font.BOLD, 14));
        addOrder.setLocation(500, 300);
        panel1.add(load);
        load.setFont(new Font("Arial", Font.BOLD, 14));

        showDetail.setFont(new Font("Arial", Font.BOLD, 14));

        showInfo.setFont(new Font("Arial", Font.BOLD, 14));
    }

    // EFFECTS: set the sescond panel shows food and allows you to add foods into order
    public void setPanel2() {
        comboBox.addItem("Choose from below");
        comboBox.addItem("Coke");
        comboBox.addItem("Chicken Wing");
        comboBox.addItem("Juice");
        comboBox.setFont(new Font("Arial", Font.BOLD, 14));
        panel2.add(comboBox);

        panel2.add(textField2);
        panel2.add(addFood);
        addFood.setFont(new Font("Arial", Font.BOLD, 14));

        panel2.add(removeFood);
        removeFood.setFont(new Font("Arial", Font.BOLD, 14));

        panel2.add(viewOrder);
        viewOrder.setFont(new Font("Arial", Font.BOLD, 14));

    }

    // EFFECTS: panel 3 stores other panels and show the first panel initially
    public void setPanel3() {
        panel3.add(panel1, "panel1");
        panel3.add(panel2, "panel2");
        panel3.add(panel4, "panel4");
        panel3.add(panel5, "panel5");

        c1.show(panel3, "panel1");
    }

    // EFFECTS: set a panel for to display the order details and allows you to save the order
    public void setPanel4() {
        panel4.add(new JLabel("All item ordered: "));
        panel4.add(save);
        save.setLocation(500, 300);
        save.setFont(new Font("Arial", Font.BOLD, 14));
        panel4.add(back);
        back.setFont(new Font("Arial", Font.BOLD, 14));
    }

    // EFFECTS: set a panel to view the loaded order
    public void setPanel5() {
        loadOrder();
        panel5.add(new JLabel("Previous saved order: "));
        panel5.add(new JLabel(orderDetail()));
    }

    // MODIFIES: this, e
    // EFFECTS: an action listener for the button named "add an order". Click this button,
    //          if textField1.getText().length() != 0, goes to panel 2; otherwise, show text: "
    //          Please enter name of the order first!"
    public void setAddOrderListener(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Add an order")) {
            if (textField1.getText().length() != 0) {
                c1.show(panel3, "panel2");
                name = textField1.getText();
            } else {
                panel1.add(showInfo);
                showInfo.setText("Please enter name of the order first!");
            }
        }
    }

    // EFFECTS: an action listener for the button named "add". Click this button,
    //          if textField1.getText().length() != 0, add food entered to the order; otherwise,
    //          show text: "Please enter name of the food first!"
    public void setAddFoodListener(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Add")) {
            if (textField2.getText().length() != 0) {
                if (Objects.equals(textField2.getText(), "Coke")) {
                    order.addItem(coke);
                } else if (Objects.equals(textField2.getText(), "Chicken Wing")) {
                    order.addItem(chickenWing);
                } else if (Objects.equals(textField2.getText(), "Juice")) {
                    order.addItem(juice);
                }

                panel4.add(new JLabel(orderDetail()));
                panel2.add(showDetail);
                showDetail.setText("Added " + textField2.getText() + " successfully!");
            } else {
                panel2.add(showDetail);
                showDetail.setText("Please enter name of the food first!");
            }

        }
    }

    // EFFECTS: an action listener for the button named "remove". Click this button,
    //          if textField1.getText().length() != 0, remove the food entered to the order; otherwise,
    //          show text: "Please enter name of the food first!"
    public void setRemoveFoodListener(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Remove")) {
            if (textField2.getText().length() != 0) {
                if (Objects.equals(textField2.getText(), "Coke")) {
                    order.removeItem(coke);
                } else if (Objects.equals(textField2.getText(), "Chicken Wing")) {
                    order.removeItem(chickenWing);
                } else if (Objects.equals(textField2.getText(), "Juice")) {
                    order.removeItem(juice);
                }
                panel4.add(new JLabel(orderDetail()));
                panel2.add(showDetail);
                showDetail.setText("Removed " + textField2.getText() + " successfully!");
            } else {
                panel2.add(showDetail);
                showDetail.setText("Please enter name of the food first!");
            }
        }
    }

    // EFFECTS: an action listener for the button named "view order". Click this button, goes to panel 4
    public void setViewOrderListener(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("View order")) {
            c1.show(panel3, "panel4");
        }
    }

    // EFFECTS: an action listener for the button named "save". Click this button, save the order
    public void setSaveListener(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Save")) {
            saveOrder();
        }
    }

    // EFFECTS: an action listener for the button named "back". Click this button, goes to panel 2
    public void setBackListener(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Back")) {
            c1.show(panel3, "panel2");
        }
    }

    // EFFECTS: an action listener for the button named "load". Click this button, goes to panel 5
    public void setLoadListener(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Load")) {
            c1.show(panel3, "panel5");
        }
    }

    // MyActionListener class calls all buttons listeners
    class MyActionListener implements ActionListener {
        // EFFECTS: performed action
        @Override
        public void actionPerformed(ActionEvent e) {
            setAddOrderListener(e);
            setAddFoodListener(e);
            setRemoveFoodListener(e);
            setViewOrderListener(e);
            setSaveListener(e);
            setBackListener(e);
            setLoadListener(e);
        }
    }

    // a class for itemListener
    class MyItemListener implements ItemListener {
        // EFFECTS: when change the item selected, show the image and details of the item
        @Override
        public void itemStateChanged(ItemEvent e) {
            String str = e.getItem().toString();
            panel2.add(showDetail);
            panel2.add(image);
            image.setBounds(0, 150, 700, 350);
            if (Objects.equals(str, "Coke")) {
                showDetail.setText("Item name: " + str + ", Price: $3.00, Description: This is " + str);
                image.setIcon(new ImageIcon("data/Coke.jpg"));
            } else if (Objects.equals(str, "Chicken Wing")) {
                showDetail.setText("Item name: " + str + ", Price: $10.00, Description: This is " + str);
                image.setIcon(new ImageIcon("data/chickenWing.jpg"));
            } else if (Objects.equals(str, "Juice")) {
                showDetail.setText("Item name: " + str + ", Price: $4.00, Description: This is " + str);
                image.setIcon(new ImageIcon("data/juice.jpg"));
            }
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
    }

    // EFFECTS: print the order detail
    public String orderDetail() {
        return order.toString();
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
