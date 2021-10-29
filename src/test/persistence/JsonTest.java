package persistence;

import model.Item;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Code source from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonTest {
    protected void checkItem(String name, Item item) {
        assertEquals(name, item.getItemName());
    }
}
