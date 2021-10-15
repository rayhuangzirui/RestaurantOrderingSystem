package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    private Item testItem;

    @BeforeEach
    void runBefore() {
        testItem = new Item("testItem");
    }

    @Test
    void testConstructor() {
        assertEquals("testItem", testItem.getItemName());
    }

    @Test
    void testSetPrice() {
        testItem.setPrice(100.0);
        assertEquals(100.0, testItem.getPrice());
    }

    @Test
    void testGetItemName() {
        assertEquals("testItem", testItem.getItemName());
    }

    @Test
    void testGetPrice() {
        testItem.setPrice(1.9);
        assertEquals(1.9, testItem.getPrice());
    }

    @Test
    void testSetDescription() {
        testItem.setDescription("this is an testItem");
        assertEquals("this is an testItem", testItem.getDescription());
    }

    @Test
    void testGetDescription() {
        testItem.setDescription("this is testing for an Item");
        assertEquals("this is testing for an Item", testItem.getDescription());
    }

    @Test
    void testToString() {
        assertTrue(testItem.toString().contains("[itemName = testItem, price = $0.00, description = null"));
    }
}
