package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private Order testOrder;

    @BeforeEach
    void runBefore() {
        testOrder = new Order();
    }

    @Test
    void testConstructor() {
        assertEquals(1, testOrder.getOrderNum());
        assertTrue(testOrder.isEmpty());

        // create more orders objects to see if the order number increase by 1
        Order order = new Order();
        assertEquals(2, order.getOrderNum());
        Order order1 = new Order();
        assertEquals(3, order1.getOrderNum());
    }

    @Test
    void testGetItem() {
        // while the itemList is empty, get the first item of the itemList, get null
        assertNull(testOrder.getItemName(0));

        // create some new items;
        Item coke = new Item("coke");
        Item chickenWing = new Item("chicken wings");
        Item fries = new Item("fries");

        // add item into the list
        testOrder.addItem(coke);
        testOrder.addItem(chickenWing);
        testOrder.addItem(fries);

        // test if getItem() can get each item
        assertEquals("coke", testOrder.getItemName(0));
        assertEquals("chicken wings", testOrder.getItemName(1));
        assertEquals("fries", testOrder.getItemName(2));
    }

    @Test
    void testAddItem() {
        Item coke = new Item("coke");
        Item chickenWing = new Item("chicken wings");
        Item fries = new Item("fries");

        // add item into the list
        testOrder.addItem(coke);
        testOrder.addItem(chickenWing);
        testOrder.addItem(fries);

        //test if items are added correctly
        assertEquals("coke", testOrder.getItemName(0));
        assertEquals("chicken wings", testOrder.getItemName(1));
        assertEquals("fries", testOrder.getItemName(2));
    }

    @Test
    void testRemoveItem() {
        Item coke = new Item("coke");
        Item chickenWing = new Item("chicken wings");
        Item fries = new Item("fries");

        testOrder.addItem(coke);
        testOrder.addItem(chickenWing);
        testOrder.addItem(fries);

        // remove coke from the first place of the list
        testOrder.removeItem(coke);
        // test if the first element deleted
        assertEquals("chicken wings", testOrder.getItemName(0));

        testOrder.removeItem(fries);
        assertEquals("chicken wings", testOrder.getItemName(0));

        testOrder.removeItem(chickenWing);
        assertNull(testOrder.getItemName(0));
    }

    @Test
    void testIsEmpty() {
        assertTrue(testOrder.isEmpty());

        Item coke = new Item("coke");
        testOrder.addItem(coke);

        assertFalse(testOrder.isEmpty());
    }

    @Test
    void testTotalPrice() {
        assertTrue(testOrder.isEmpty());
        assertEquals(0.0, testOrder.totalPrice());

        Item coke = new Item("coke");
        Item chickenWing = new Item("chicken wings");
        Item fries = new Item("fries");

        coke.setPrice(3.0);
        chickenWing.setPrice(10.0);
        fries.setPrice(5.0);

        testOrder.addItem(coke);
        testOrder.addItem(chickenWing);
        testOrder.addItem(fries);

        assertEquals(18.0, testOrder.totalPrice());
    }

    @Test
    void testMakeAPayment() {
        testOrder.makeAPayment();
        assertTrue(testOrder.isPaid());
        assertEquals(0.0, testOrder.totalPrice());
        assertTrue(testOrder.isEmpty());
    }

    @Test
    void testIsPaid() {
        assertFalse(testOrder.isPaid());
    }

    @Test
    void testCancelOrder() {
        testOrder.cancelOrder();
        assertTrue(testOrder.isEmpty());

        Item coke = new Item("coke");
        coke.setPrice(3.0);
        testOrder.addItem(coke);
        assertFalse(testOrder.isEmpty());
        assertEquals(3.0, testOrder.totalPrice());

        testOrder.cancelOrder();
        assertFalse(testOrder.isPaid());
        assertEquals(0.0, testOrder.totalPrice());
        assertTrue(testOrder.isEmpty());
    }

    @Test
    void testToString() {
        assertTrue(testOrder.toString().contains("[orderNum = 0, itemArrayList = [], totalPrice = $0.00, isPaid = false]"));
    }
}
