package persistence;

import model.Item;
import model.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Code source from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Order order = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyOrder() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyOrder.json");
        try {
            Order order = reader.read();
            assertEquals("My order", order.getOrderName());
            assertEquals(0, order.numItems());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralOrder() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralOrder.json");
        try {
            Order order = reader.read();
            assertEquals("My order", order.getOrderName());
            List<Item> items = order.getItemList();
            assertEquals(2, items.size());
            checkItem("coke", items.get(0));
            checkItem("chicken wing", items.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
